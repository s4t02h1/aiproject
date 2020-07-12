package chapter4;

import java.util.Iterator;

/** 単語の分散表現を利用するメインプログラムの例 */

public class TestWord2Vec {

    public static void main(String[] args) {
        VecWordDictionary dic;

        // バイナリ形式のデータファイルから単語分散表現辞書を構築する場合
        dic = VecWordDictionaryFactory.createFromBinaryFile("jvectors.bin");
        // テキスト形式のデータファイルから単語分散表現辞書を構築する場合
        // dic = VecWordDictionaryFactory.createFromTextFile("jvectors.txt");
        // 大きなデータファイルの一部のみを使いたい場合
        // dic = VecWordDictionaryFactory.createFromBinaryFile("jvectors.bin", 10000);

        // 指定した2単語の類似度を出力する例
        String str1 = "学校";
        VecWord word1 = getVecWord(str1, dic);
        String str2 = "図書館";
        VecWord word2 = getVecWord(str2, dic);

        if (word1 != null && word2 != null) {
            System.out.println("「" + str1 + "」と「" + str2 + "」の類似度は" + word1.similarity(word2));
        }

        // 指定した単語との類似度が高い上位N単語を出力する
        if (word1 != null) {
            System.out.println("「" + str1 + "」と類似度が高い単語：");
            printSimilarWords(word1, dic);
        }
    }

    /** 単語の見出しを指定して分散表現を取得する */
    public static VecWord getVecWord(String text, VecWordDictionary dic) {
        VecWord word = dic.getWord(text);
        if (word == null) {
            System.out.println("単語「" + text + "」は見つかりませんでした");
        }
        return word;
    }

    /** 与えられた分散表現との類似度が高い分散表現をもつ上位N単語を出力する */
    public static void printSimilarWords(VecWord word, VecWordDictionary dic) {
        int N = 10;
        VecWord[] maxWord = new VecWord[N];
        double[] max = new double[N];

        for (Iterator<VecWord> it = dic.iterator(); it.hasNext(); ) {
            VecWord w = it.next();
            if (w == word) {
                continue;
            }
            double sim = word.similarity(w);

            for (int i = 0; i < N; i++) {
                if (sim > max[i]) {
                    for (int j = N - 1; j > i; j--) {
                        maxWord[j] = maxWord[j-1];
                        max[j] = max[j-1];
                    }
                    maxWord[i] = w;
                    max[i] = sim;
                    break;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            System.out.println((i+1) + "\t" + maxWord[i].text + "\t" + max[i]);
        }
    }
}
