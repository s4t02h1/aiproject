package chapter5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chapter3.Counter;
import chapter3.MeCab;
import chapter3.Word;
import chapter4.CounterWithWeight;

/** TF-IDFの値を計算する */

public class TestTfIdf {

    public static void main(String[] args) {
        final String documentFolderPath = "natsume"; // 文書集合が格納されているフォルダ
        final String targetFilePath = "natsume/bocchan_filtered.txt"; // TF-IDFを計算する対象文書

        // まず、各単語のDFの値を求める
        Counter<Word> dfCounter = new Counter<Word>();
        File[] files = new File(documentFolderPath).listFiles();
        int numDocument = files.length;

        for (File f : files) {
            List<Word> wordList = extractWordsInFile(f);
            for (Word w : wordList) {
                dfCounter.add(w);
            }
        }

        // 対象文書に出現する単語のTFの値およびTF-IDFの値を求める
        CounterWithWeight<Word> wordCounter = countWordsInFile(new File(targetFilePath));
        for (Word w : wordCounter.getObjectList()) {
            double idf = Math.log(numDocument / dfCounter.getNumber(w));
            double tfidf = wordCounter.getNumber(w) * idf;
            wordCounter.putWeight(w, tfidf);
        }

        // TF-IDFの値が大きい順に20単語を出力する
        List<Word> sortedWordList = wordCounter.getObjectListSortedByWeight(); // TF-IDFの値でソート
        for (int i = 0; i < 20; i++) {
            Word w = sortedWordList.get(i);
            System.out.print(w.basicForm + "(" + w.pos + ")\t" + wordCounter.getWeight(w));
            System.out.println("\t" + wordCounter.getNumber(w) + "\t" + dfCounter.getNumber(w));
        }
    }

    /** 文書ファイルに含まれる単語を抽出する */
    public static List<Word> extractWordsInFile(File f) {
        List<Word> wordList = new ArrayList<Word>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            MeCab mecab = MeCab.getInstance();
            String line;
            while ((line = br.readLine()) != null) {
                List<Word> list = mecab.analyze(line);
                for (Word w : list) {
                    if (w.pos.startsWith("名詞") || w.pos.startsWith("動詞") || w.pos.startsWith("形容詞")) {
                        if (!wordList.contains(w)) {
                            wordList.add(w);
                        }
                    }
                }
            }
            br.close();
            mecab.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return wordList;
    }

    /** 文書ファイルに含まれる単語の出現回数を数える */
    public static CounterWithWeight<Word> countWordsInFile(File f) {
        CounterWithWeight<Word> wordCounter = new CounterWithWeight<Word>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            MeCab mecab = MeCab.getInstance();
            String line;
            while ((line = br.readLine()) != null) {
                List<Word> list = mecab.analyze(line);
                for (Word w : list) {
                    if (w.pos.startsWith("名詞") || w.pos.startsWith("動詞") || w.pos.startsWith("形容詞")) {
                        wordCounter.add(w);
                    }
                }
            }
            br.close();
            mecab.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return wordCounter;
    }
}
