package chapter5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** 応答生成を行う */

public class ResponseGenerator {
    /** キーワード照合ルールのリスト */
    public List<KeywordMatchingRule> ruleList;
    Random random = new Random();

    /** コンストラクタ：キーワード照合ルールリストの初期化 */
    public ResponseGenerator() {
        // ファイルに1行1ルールの形式で格納されている照合ルールを読み込み、リストに格納する
        ruleList = new ArrayList<KeywordMatchingRule>();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("kw_matching_rule.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\t");
                KeywordMatchingRule rule = new KeywordMatchingRule();
                rule.keyword = split[0];
                rule.response = split[1];
                ruleList.add(rule);
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /** 応答文を生成する */
    public String generate(Utterance utterance) {
        // 応答候補リスト
        List<ResponseCandidate> candidateList = new ArrayList<ResponseCandidate>();

        // 複数の方法での応答候補生成を行う
        // それぞれの方法で生成した応答候補はcandidateListに順次追加していく
        generateResponseByRule(utterance, candidateList);
        generateResponseBySpeechActType(utterance, candidateList);
        generateOtherResponse(candidateList);

        // スコア最大の応答候補を選択する
        String response = "";
        double maxScore = -1.0;
        for (ResponseCandidate cdd : candidateList) {
            if (cdd.score > maxScore) {
                response = cdd.response;
                maxScore = cdd.score;
            }
        }
        return response;
    }

    /** キーワード照合ルールを利用した応答候補の生成 */
    public void generateResponseByRule(Utterance utterance, List<ResponseCandidate> candidateList) {
        for (KeywordMatchingRule rule : ruleList) {
            if (utterance.text.contains(rule.keyword)) { // ユーザ入力文がキーワードを含んでいたら
                ResponseCandidate cdd = new ResponseCandidate(); // 応答候補を生成する
                cdd.response = rule.response;
                cdd.score = 1.0 + random.nextDouble(); // スコア設定
                candidateList.add(cdd);
            }
        }
    }

    /** ユーザの発話行為タイプを考慮した応答候補の生成 */
    public void generateResponseBySpeechActType(Utterance utterance, List<ResponseCandidate> candidateList) {
        ResponseCandidate cdd = new ResponseCandidate();

        if (utterance.speechActType.equals("質問")) {
            if (utterance.text.contains("いつ") ||
                    utterance.text.contains("どこ") ||
                    utterance.text.contains("誰") ||
                    utterance.text.contains("何")) { // WH疑問文
                if (utterance.topic != null) {
                    cdd.response = utterance.topic + "のことは詳しくありません．";
                } else {
                    cdd.response = "わからないです．";
                }
            } else { // Yes-No疑問文
                cdd.response = "はい！";
            }
        } else { // 陳述の場合
            if (utterance.topic != null) {
                cdd.response = utterance.topic + "ってなんですか？";
            } else {
                cdd.response = "そうですね．";
            }
        }
        cdd.score = 0.7 + random.nextDouble(); // スコア設定
        candidateList.add(cdd);
    }

    /** その他の応答候補の生成 */
    public void generateOtherResponse(List<ResponseCandidate> candidateList) {
        // この例では簡単のため、2種類の無難な応答から1つをランダムに選択する
        ResponseCandidate cdd = new ResponseCandidate();
        if (random.nextBoolean()) {
            cdd.response = "なるほど．";
        } else {
            cdd.response = "はい．";
        }
        cdd.score = 0.4 + random.nextDouble(); // スコア設定
        candidateList.add(cdd);
    }
}
