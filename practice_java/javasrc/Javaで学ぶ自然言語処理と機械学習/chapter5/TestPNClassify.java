package chapter5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chapter3.MeCab;
import chapter3.Word;

/** 文のポジネガ分類データを学習データ用のファイル形式に変換して保存する */

public class TestPNClassify {

    public static void main(String[] args) {
        final String corpusFileName = "diary_corpus.txt"; // 文のポジネガ分類データ（元データ）
        final String learningDataFileName = "diary_learning_data.txt"; // 機械学習用データ（変換先）
        final String featureFileName = "diary_learning_features.txt"; // 特徴indexと単語の関係を保存
        List<String> featureList = new ArrayList<String>(); // 特徴として使用する単語のリスト

        try {
            BufferedReader br = new BufferedReader(new FileReader(corpusFileName));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(learningDataFileName)));
            MeCab mecab = MeCab.getInstance();
            String line;

            // 文のポジネガ分類データを1行ごとに処理する
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\t");
                if (split[2].equals("1") || split[2].equals("2")) { // ポジティブ
                    pw.print("1 ");
                } else if (split[2].equals("4") || split[2].equals("5")) { // ネガティブ
                    pw.print("2 ");
                } else { // 評価が3のデータは使用しない
                    continue;
                }
                pw.print(split[1]); // 学習データの名前欄に、元の文を出力する

                // 文中の単語リストを作成
                List<Word> wordList = new ArrayList<Word>();
                List<Word> list = mecab.analyze(split[1]);
                for (Word w : list) {
                    if (w.pos.equals("名詞-一般") ||
                            w.pos.startsWith("名詞-固有名詞") ||
                            w.pos.equals("名詞-サ変接続") ||
                            w.pos.equals("名詞-形容動詞語幹") ||
                            w.pos.equals("動詞-自立") ||
                            w.pos.equals("形容詞-自立")) {
                        if (!wordList.contains(w)) {
                            wordList.add(w);
                        }
                    }
                }

                // 単語リストから特徴indexのリストを作る
                List<Integer> featureIndexList = new ArrayList<Integer>();
                for (Word w : wordList) {
                    String feature = w.basicForm + "(" + w.pos + ")"; // 特徴量は単語の原形＋品詞
                    int index = featureList.indexOf(feature);
                    if (index == -1) {
                        featureList.add(feature);
                        index = featureList.size() - 1;
                    }
                    featureIndexList.add(index);
                }
                Collections.sort(featureIndexList); // indexを小さい順にソート
                for (int index : featureIndexList) {
                    pw.print(" " + (index + 1) + ":1"); // 学習データの形式で出力する
                }
                pw.println();
            }
            br.close();
            pw.close();
            mecab.close();

            // 各特徴indexに対応する単語の内容をファイル保存する
            pw = new PrintWriter(new BufferedWriter(new FileWriter(featureFileName)));
            for (int i = 0; i < featureList.size(); i++) {
                pw.println((i + 1) + "\t" + featureList.get(i));
            }
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
