package chapter4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chapter3.Counter;
import chapter3.MeCab;
import chapter3.Word;

/** コーパスにおける共起語を求める */

public class CooccurringWords {
    /** 単語ごとの各単語との共起回数およびPMI */
    public Map<String, CounterWithWeight<String>> coocWordCounterMap;
    /** 単語ごとの出現回数 */
    public Counter<String> wordCounter;
    /** 総単語数 */
    public int totalWordCount;
    /** 単語ごとの頻出共起語集合 */
    public Map<String, List<String>> coocWordListMap;
    /** 頻出共起語集合に含める共起語のPMIの閾値 */
    public static final double minPMI = 3.0;
    /** 頻出共起語集合に含める共起語の出現回数の閾値 */
    public static final int minCount = 5;

    /** 指定したコーパスから共起語を求める */
    public void countWordsInCorpus(String corpusFileName) {

        // 単語ごとに出現回数および各単語との共起回数を数える
        coocWordCounterMap = new HashMap<String, CounterWithWeight<String>>();
        wordCounter = new Counter<String>();
        totalWordCount = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(corpusFileName));
            MeCab mecab = MeCab.getInstance();
            String line;
            while ((line = br.readLine()) != null) {
                // コーパスを行ごと（文ごと）に形態素解析し、出現単語リストを作る
                List<Word> wordList = mecab.analyze(line);
                List<String> wordsInSentence = new ArrayList<String>();
                for (Word w : wordList) {
                    if (w.pos.equals("名詞-一般") || w.pos.equals("名詞-サ変接続")) {
                        wordsInSentence.add(w.text);
                    }
                }
                // 出現単語リストを用いて、単語の共起回数と出現回数を更新する
                for (String word : wordsInSentence) {
                    CounterWithWeight<String> counter = coocWordCounterMap.get(word);
                    if (counter == null) {
                        counter = new CounterWithWeight<String>();
                        coocWordCounterMap.put(word, counter);
                    }
                    for (String coocWord : wordsInSentence) {
                        if (coocWord == word) continue;
                        counter.add(coocWord);
                    }
                    wordCounter.add(word);
                }
                totalWordCount += wordsInSentence.size();
            }
            br.close();
            mecab.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // 単語ごとに頻出共起語リストを作成する
        coocWordListMap = new HashMap<String, List<String>>();

        for (String word : coocWordCounterMap.keySet()) {
            int count = wordCounter.getNumber(word);
            CounterWithWeight<String> counter = coocWordCounterMap.get(word);
            // 共起語ごとにPMIを計算
            for (String coocWord : counter.getObjectList()) {
                double pmi = 0.0;
                if (wordCounter.getNumber(coocWord) >= minCount) {
                    pmi = Math.log(1.0 * totalWordCount * counter.getNumber(coocWord) /
                            count / wordCounter.getNumber(coocWord));
                }
                counter.putWeight(coocWord, pmi);
            }
            // 共起語をPMIでソートし、PMIが閾値以上の共起語をリストに加える
            List<String> frequentCoocWordList = new ArrayList<String>();
            for (String coocWord : counter.getObjectListSortedByWeight()) {
                if (counter.getWeight(coocWord) >= minPMI) {
                    frequentCoocWordList.add(coocWord);
                } else {
                    break;
                }
            }
            coocWordListMap.put(word, frequentCoocWordList);
        }
    }

    /** 動作確認用mainメソッド */
    public static void main(String[] args) {
        CooccurringWords coocWords = new CooccurringWords();
        coocWords.countWordsInCorpus("natsume/bocchan_filtered.txt");
        String word = "学校";
        System.out.println("「" + word + "」の頻出共起語リスト");
        System.out.println(coocWords.coocWordListMap.get(word));
    }
}
