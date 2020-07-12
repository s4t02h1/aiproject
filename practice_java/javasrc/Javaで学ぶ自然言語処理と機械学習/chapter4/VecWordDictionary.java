package chapter4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** 単語分散表現を格納する辞書 */

public class VecWordDictionary {
    /** 単語リスト本体 */
    public List<VecWord> wordList;
    /** 見出しから単語オブジェクトへのMap */
    public Map<String, VecWord> wordMap;

    /** コンストラクタ：空の辞書を作成する */
    public VecWordDictionary() {
        wordList = new ArrayList<VecWord>();
        wordMap = new HashMap<String, VecWord>();
    }

    /** 単語IDを指定して単語オブジェクトを取得する */
    public VecWord getWord(int n) {
        return wordList.get(n);
    }

    /** 単語見出しを指定して単語オブジェクトを取得する */
    public VecWord getWord(String text) {
        return wordMap.get(text);
    }

    /** 辞書中の全単語を順に取り出すためのイテレータ */
    public Iterator<VecWord> iterator() {
        return wordList.iterator();
    }

    /** 辞書に含まれる単語数を返す */
    public int size() {
        return wordList.size();
    }
}
