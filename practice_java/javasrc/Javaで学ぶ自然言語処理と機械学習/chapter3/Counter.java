package chapter3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 型Tのオブジェクトの種類ごとの出現回数を数える計数器 */
public class Counter<T> {
    //オブジェクトに出現回数を対応付ける写像

    Map<T, Integer> objectMap;

    /** コンストラクタ */
    public Counter() {
        objectMap = new HashMap<T, Integer>();
    }

    /** オブジェクトの出現情報を1回分追加する */
    public void add(T obj) {
        Integer count = objectMap.get(obj);
        if (count != null) { // マップに登録済み（つまり最低1回は出現済み）の場合は
            int newCount = count.intValue() + 1; // 記録されている回数に１を足して
            objectMap.put(obj, newCount); // マップに上書き登録する
        } else { // 今まで出現していない単語の場合は
            objectMap.put(obj, 1); // マップに出現回数1と登録する
        }
    }

    /** オブジェクトの出現回数を返す */
    public int getNumber(T obj) {
        Integer count = objectMap.get(obj);
        if (count != null) {
            return count.intValue();
        } else {
            return 0;
        }
    }

    /** 出現したオブジェクトのリストを作って返す */
    public List<T> getObjectList() {
        return new ArrayList<T>(objectMap.keySet());
    }

    /** 出現回数順にオブジェクトをソートしたリストを作って返す */
    public List<T> getSortedObjectList() {
        List<T> objectList = getObjectList();
        Collections.sort(objectList, new Comparator<T>() {
            public int compare(T obj1, T obj2) {
                return objectMap.get(obj2) - objectMap.get(obj1);
            }
        });
        return objectList;
    }
}
