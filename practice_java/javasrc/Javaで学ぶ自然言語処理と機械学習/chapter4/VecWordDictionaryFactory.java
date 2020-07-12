package chapter4;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/** 単語分散表現辞書のインスタンスをファイルから構築する */

public class VecWordDictionaryFactory {

    /** word2vecが出力したバイナリ形式のファイルを読み込んで、単語分散表現辞書を構築する */
    public static VecWordDictionary createFromBinaryFile(String dicFileName) {
        return createFromBinaryFile(dicFileName, Integer.MAX_VALUE);
    }

    /** 同（使用する最大単語数を指定） */
    public static VecWordDictionary createFromBinaryFile(String dicFileName, int vocabSize) {
        VecWordDictionary dic = new VecWordDictionary();

        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(dicFileName));

            int vocabSizeOrigin = Integer.parseInt(new String(getToken(bis)));
            int layerSize = Integer.parseInt(new String(getToken(bis)));
            if (vocabSizeOrigin < vocabSize) {
                vocabSize = vocabSizeOrigin;
            }

            byte[] floatBytes = new byte[4];

            for (int i = 0; i < vocabSize; i++) {
                VecWord word = new VecWord(layerSize);
                word.text = new String(getToken(bis), "UTF-8"); // 見出しを読み取る

                for (int j = 0; j < layerSize; j++) { // バイナリ形式の数値を順に読み取る
                    bis.read(floatBytes);
                    ByteBuffer buf = ByteBuffer.wrap(floatBytes);
                    buf.order(ByteOrder.LITTLE_ENDIAN);
                    word.vec[j] = buf.getFloat();
                }
                dic.wordList.add(word);
                dic.wordMap.put(word.text, word);
            }
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return dic;
    }

    /** 入力ストリームからトークンを1個読み取る */
    public static byte[] getToken(BufferedInputStream bis) throws IOException {
        byte[] tokenBytes = new byte[1024];
        int count = 0;

        while (true) {
            int d = bis.read();
            if (d == ' ' || d == '\n') {
                continue;
            } else {
                tokenBytes[count++] = (byte)d;
                break;
            }
        }

        while (true) {
            int d = bis.read();
            if (d == ' ' || d == '\n') {
                break;
            }
            tokenBytes[count++] = (byte)d;
        }

        return Arrays.copyOf(tokenBytes, count);
    }

    /** word2vecが出力したテキスト形式のファイルを読み込んで、単語ベクトル辞書を構築する */
    public static VecWordDictionary createFromTextFile(String dicFileName) {
        return createFromTextFile(dicFileName, Integer.MAX_VALUE);
    }

    /** 同（使用する最大単語数を指定） */
    public static VecWordDictionary createFromTextFile(String dicFileName, int vocabSize) {
        VecWordDictionary dic = new VecWordDictionary();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dicFileName), "UTF-8"));

            String[] split = br.readLine().split(" ");
            int vocabSizeOrigin = Integer.parseInt(split[0]);
            int layerSize = Integer.parseInt(split[1]);
            if (vocabSizeOrigin < vocabSize) {
                vocabSize = vocabSizeOrigin;
            }

            for (int i = 0; i < vocabSize; i++) {
                split = br.readLine().split(" ");
                VecWord word = new VecWord(layerSize);
                word.text = split[0];
                for (int j = 0; j < layerSize; j++) {
                    word.vec[j] = Float.parseFloat(split[j+1]);
                }
                dic.wordList.add(word);
                dic.wordMap.put(word.text, word);
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return dic;
    }
}
