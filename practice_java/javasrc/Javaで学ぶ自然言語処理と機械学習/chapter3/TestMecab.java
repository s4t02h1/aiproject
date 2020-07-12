package chapter3;

import java.util.List;

/** MeCabによる形態素解析を開始するメインプログラム */
public class TestMecab {

    public static void main(String[] args) {
        
        //形態素解析を実行
        MeCab mecab = MeCab.getInstance();
        List<Word> wordList = mecab.analyze("私は学校へ行きます");
        mecab.close();

        //形態素解析の結果を標準出力に出力
        for (Word w : wordList) {
            w.print();
        }
    }
}
