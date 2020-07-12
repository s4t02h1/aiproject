package chapter3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/** CaboChaによる係り受け解析を行う */
public class CaboCha {

    //シングルトンパターン
    private static CaboCha cabocha = null;

    /** Cabocha実行のパスとオプションの指定 */
    private final String PATH = "cabocha";
    private final String OPT = "-f1";

    /** Cabochaの実行プロセスと入出力 */
    Process process;
    BufferedReader br;
    PrintWriter pw;

    /** コンストラクタ */
    private CaboCha() {

        try {
            /* Cabochaのプロセスを起動 */
            ProcessBuilder pb = new ProcessBuilder(PATH, OPT);
            process = pb.start();
            System.out.println("*** Cabocha解析中 ***");
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            pw = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(process.getOutputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /** 入力文の文字列を与えると解析結果を文節リストとして戻す */
    public List<Chunk> analyze(String inputText) {

        System.out.println("解析文：" + inputText);

        /* 入力テキストinputTextをCabochaに送る */
        pw.println(inputText);
        pw.flush();

        /* Cabochaの解析結果を１行ずつ取り出す*/
        List<Chunk> chunkList = new ArrayList<Chunk>();
        String line;

        Chunk chunk=null;
        try {
            //1行ずつ読み込む
            while ((line = br.readLine()) != null) {
                //確認のため解析内容を出力
                System.out.println(line);

                if (line.equals("EOS")) { //もしEOSならば終了
                    break;
                } else if (line.startsWith("*")) { //文節情報の取得
                    chunk = new Chunk();
                    String[] split = line.split(" ", 4);
                    int index = split[2].indexOf("D");
                    chunk.dependNo = Integer.parseInt(split[2].substring(0, index));
                    chunkList.add(chunk);
                } else { //形態素情報の取得
                    Word word = MeCab.createWordFromLine(line);
                    chunk.words.add(word);
                    chunk.text += word.text;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //係り先の文節と係り元情報の追加
        for (Chunk c : chunkList) {
            if (c.dependNo != -1) {
                Chunk c2 = chunkList.get(c.dependNo);
                c.depend = c2;
                c2.dependents.add(c);
            }
        }
        return chunkList;
    }

    /** 解析終了後の後処理 */
    public void close() {
        try {
            br.close();
            pw.close();
            process.destroy();
            cabocha = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("*** Cabocha解析終了 ***");
    }

    /** CaboChaクラスのインスタンスを戻す */
    public static CaboCha getInstance() {
        if(cabocha == null){
            cabocha = new CaboCha();
        }
        return cabocha;
    }
}
