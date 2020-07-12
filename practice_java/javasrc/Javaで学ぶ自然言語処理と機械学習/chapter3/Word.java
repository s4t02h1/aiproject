package chapter3;

/** 単語情報を表現するクラス */
public class Word {

    public String text;        //表層形
    public String basicForm;   //原形
    public String pos;         //品詞
    public String reading;     //読み
    public String conjType;    //活用型
    public String conjForm;    //活用形

    /** 単語の情報を出力 */
    public void print() {
        System.out.println(text + " | " + basicForm + " | " + pos);
    }

    /** 2つの単語が同じかどうか判定する */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Word)) {
            return false;
        }
        Word w = (Word) o;
        // 2つのWordの原形と品詞が等しければ同一とみなす
        return (this.basicForm.equals(w.basicForm) && this.pos.equals(w.pos));
    }

    @Override
    public int hashCode() {
        return basicForm.hashCode() + pos.hashCode();
    }
}
