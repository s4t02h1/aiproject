package chapter5;

import java.util.List;

import chapter3.Word;

/** 発話の内容を表すクラス */

public class Utterance {
    /** 発話文 */
    public String text;
    /** 単語リスト */
    public List<Word> wordList;

    /** 発話行為タイプ */
    public String speechActType;
    /** 話題 */
    public String topic;
}
