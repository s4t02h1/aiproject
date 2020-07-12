package chapter3;

import java.util.ArrayList;
import java.util.List;

/** 文節情報を表現するクラス */
public class Chunk {

    public String text="";           //表層テキスト
    public List<Word> words;         //構成要素の単語リスト
    public int dependNo;             //係り先の文節番号
    public Chunk depend;             //係り先の文節
    public List<Chunk> dependents;   //係り元の文節リスト
    
    /** コンストラクタ */
    public Chunk(){
        words = new ArrayList<Word>();
        dependents = new ArrayList<Chunk>();
    }
}
