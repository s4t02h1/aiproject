package chapter3;

/** Bigramを表現するクラス */
public class Bigram {

    /** Bigramを構成する2つの単語 */
    public Word w1;
    public Word w2;

    /** コンストラクタ */
    public Bigram(Word w1, Word w2) {
        this.w1 = w1;
        this.w2 = w2;
    }

    /** 2つのBigramが同じかどうか判定する */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Bigram)) {
            return false;
        }
        Bigram b = (Bigram) o;

        return w1.equals(b.w1) && w2.equals(b.w2);
    }

    @Override
    public int hashCode() {
        return w1.hashCode() + w2.hashCode();
    }
}
