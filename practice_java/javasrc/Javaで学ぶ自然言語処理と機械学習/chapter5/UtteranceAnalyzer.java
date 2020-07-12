package chapter5;

import chapter3.MeCab;
import chapter3.Word;

/** 発話理解を行う */

public class UtteranceAnalyzer {
    public MeCab mecab;

    /** コンストラクタ */
    public UtteranceAnalyzer() {
        mecab = MeCab.getInstance();
    }

    /** 発話文を解析してUtteranceオブジェクトを作る */
    public Utterance analyze(String text) {
        Utterance utterance = new Utterance();
        utterance.text = text;
        utterance.wordList = mecab.analyze(text);

        // 単純な発話行為タイプ判定：文末が「？」ならば質問とする
        if (text.length() > 0 && text.charAt(text.length() - 1) == '？') {
            utterance.speechActType = "質問";
        } else {
            utterance.speechActType = "陳述";
        }

        // 単純な話題抽出：文中の最初の名詞を抽出（無ければnull）
        utterance.topic = null;
        for (Word w : utterance.wordList) {
            if (w.pos.equals("名詞-一般") ||
                    w.pos.equals("名詞-サ変接続") ||
                    w.pos.startsWith("名詞-固有名詞")) {
                utterance.topic = w.basicForm;
                break;
            }
        }
        return utterance;
    }
}
