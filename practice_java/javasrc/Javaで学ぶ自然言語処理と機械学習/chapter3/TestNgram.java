package chapter3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/** N-gramの頻度を求めるメインプログラム */
public class TestNgram {

    public static void main(String[] args) {

        //MeCabの解析
        MeCab mecab = MeCab.getInstance();
        //1-gramの計数
        Counter<Word> wordCounter = new Counter<Word>();
        //2-gramの計数
        Counter<Bigram> bigramCounter = new Counter<Bigram>();

        try {
            //ファイルから読み出す
            BufferedReader br = new BufferedReader(new FileReader("natsume/kokoro_filtered.txt"));
            String line;
            //1行ずつ取得して形態素解析し，1-gramと2-gramを作成
            while ((line = br.readLine()) != null) {
                List<Word> wordList = mecab.analyze(line);

                //1-gramの作成
                for (Word w : wordList) {
                    wordCounter.add(w);
                }

                //2-gramの作成
                for (int i = 0; i < wordList.size() - 1; i++) {
                    Bigram bg = new Bigram(wordList.get(i), wordList.get(i + 1));
                    bigramCounter.add(bg);
                }
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        mecab.close();

        //1-gramと2-gramをそれぞれ出現頻度でソートして出力
        List<Word> unigramResultList = wordCounter.getSortedObjectList();
        System.out.println("*** 1-gram ***");
        for (Word w : unigramResultList) {
            System.out.println(wordCounter.getNumber(w) + "\t" + w.basicForm + "(" + w.pos + ")");
        }

        List<Bigram> bigramResultList = bigramCounter.getSortedObjectList();
        System.out.println("*** 2-gram ***");
        for (Bigram b : bigramResultList) {
            System.out.println(bigramCounter.getNumber(b) + "\t"
                    + b.w1.basicForm + "(" + b.w1.pos + ")" + "\t"
                    + b.w2.basicForm + "(" + b.w2.pos + ")");
        }
    }
}
