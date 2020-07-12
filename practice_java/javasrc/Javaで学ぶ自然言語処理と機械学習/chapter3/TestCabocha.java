package chapter3;

import java.util.List;

/** CaboChaによる係り受け解析を開始するメインプログラム */
public class TestCabocha {

    public static void main(String[] args) {

        //CaboChaによる係り受け解析を行う
        CaboCha cabocha = CaboCha.getInstance();
        List<Chunk> chunkList = cabocha.analyze("今日はおいしいご飯を食べたい。");
        cabocha.close();

        //文の最後に係る文節を列挙して表示する例
        for (Chunk c : chunkList) {
            if (c.dependNo == -1) {
                System.out.println("「" + c.text + "」に係っている文節");
                for(Chunk c2 : c.dependents){
                    System.out.println(c2.text);
                }
            }
        }
    }
 }
