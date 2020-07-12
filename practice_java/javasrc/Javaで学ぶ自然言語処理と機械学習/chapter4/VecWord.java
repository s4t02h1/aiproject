package chapter4;

/** 単語の分散表現を表すクラス */

public class VecWord {
    /** 通し番号（コーパスでの出現回数が多いほど若い番号） */
    public int id;
    /** 単語の見出し */
    public String text;
    /** 単語の分散表現ベクトル */
    public float[] vec;

    /** コンストラクタ：ベクトルの次元数を指定して初期化する */
    public VecWord(int layerSize) {
        vec = new float[layerSize];
    }

    /** 2つの単語が同じかどうか判定する */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VecWord word = (VecWord)obj;

        return (id == word.id);
    }

    /** コサイン類似度を求める */
    public double similarity(VecWord word) {
        double xx = 0.0, xy = 0.0, yy = 0.0;

        for (int i = 0; i < vec.length; i++) {
            xx += vec[i] * vec[i];
            xy += vec[i] * word.vec[i];
            yy += word.vec[i] * word.vec[i];
        }

        return xy / Math.sqrt(xx) / Math.sqrt(yy);
    }

    /** ベクトルの加算 */
    public VecWord add(VecWord word) {
        int layerSize = vec.length;
        VecWord sumWord = new VecWord(layerSize);

        for (int i = 0; i < layerSize; i++) {
            sumWord.vec[i] = vec[i] + word.vec[i];
        }

        return sumWord;
    }

    /** ベクトルの減算 */
    public VecWord subtract(VecWord word) {
        int layerSize = vec.length;
        VecWord diffWord = new VecWord(layerSize);

        for (int i = 0; i < layerSize; i++) {
            diffWord.vec[i] = vec[i] - word.vec[i];
        }

        return diffWord;
    }
}
