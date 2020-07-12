package chapter5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 簡単な雑談対話システム */

public class TestChat {
    /** 発話理解部 */
    public UtteranceAnalyzer analyzer;
    /** 応答生成部 */
    public ResponseGenerator generator;

    public static void main(String[] args) {
        TestChat testChat = new TestChat();
    }

    /** コンストラクタ：雑談対話システムのメインフロー */
    public TestChat() {
        analyzer = new UtteranceAnalyzer();
        generator = new ResponseGenerator();
        String output = "こんにちは！"; // システムの応答文

        // 最初の挨拶
        System.out.println("システム：" + output);

        try {
            // 標準入力の準備
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("ユーザ　：");
            String input;
            while((input = br.readLine()) != null) { // ユーザによる文の入力を受け取る
                // ユーザ発話の理解
                Utterance utterance = analyzer.analyze(input);
                // システム応答の生成、出力
                output = generator.generate(utterance);
                System.out.println("システム：" + output);
                System.out.print("ユーザ　：");
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
