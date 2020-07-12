package chapter3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * MeCabによる形態素解析を行う
 */
public class MeCab {

    //シングルトンパターン
    private static MeCab mecab = null;

    //MeCabの実行パス
    private final String PATH = "mecab";

    Process process;    //MeCabの実行プロセス
    BufferedReader br;  //解析結果読み出しのためのReader
    PrintWriter pw;     //文字列をMeCabに送るためのWriter

    /** コンストラクタ */
    private MeCab() {
        try {
            /* MeCabのプロセスを起動 */
            ProcessBuilder pb = new ProcessBuilder(PATH);
            process = pb.start();

            System.out.println("*** MeCab解析中 ***");
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            pw = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(process.getOutputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** MeCabによる形態素解析を行い単語リストを返す */
    public List<Word> analyze(String inputText) {

        System.out.println("解析文：" + inputText);

        //解析結果の単語情報を保持するリスト
        List<Word> wordList = new ArrayList<Word>();

        //入力テキストinputTextをMeCabに送る
        pw.println(inputText);
        pw.flush();

        String line;
        try {
            while ((line = br.readLine()) != null) { // 解析結果をMeCabから受信
                if (line.equals("EOS")) { // EOSは文の終わりを表す
                    break;
                } else {
                    //MeCab解析して，単語をwordListに追加
                    Word word = createWordFromLine(line);
                    wordList.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }
    
    /** MeCabの解析結果からWordオブジェクトを作成する */
    public static Word createWordFromLine(String line){
        Word word = new Word();

        //解析結果をタブで区切り，表層形を取得
        String[] split = line.split("\t");
        word.text = split[0];

        //解析結果をカンマで区切り，各情報を取得
        String[] split2 = split[1].split(",");

        //品詞情報を取得
        word.pos = split2[0];
        for(int i=1; i<=3; i++) {
            if (split2[i].equals("*")){
                break;
            }
            word.pos += "-" + split2[i];
        }
        
        //活用型，活用形，原形，読みを取得
        word.conjType = split2[4];
        word.conjForm = split2[5];

        if (split2[6].equals("*")) {
            word.basicForm = word.text;
            word.reading = "";
        } else {
            word.basicForm = split2[6];
            word.reading = split2[7];
        }
        
        return word;
    }

    /**
     * 解析終了後の後処理
     */
    public void close() {
        try {
            br.close();
            pw.close();
            process.destroy();
            mecab = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("*** MeCab解析終了 ***");
    }

    /**
     * MeCabクラスのインスタンスを戻す
     */
    public static MeCab getInstance() {
        if(mecab == null){
            mecab = new MeCab();
        }
        return mecab;
    }
}
