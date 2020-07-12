package chapter4;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chapter3.Counter;

/** オブジェクトごとに回数のほか実数値も保持できる計数器 */

public class CounterWithWeight<T> extends Counter<T> {
    /** オブジェクトに実数値を対応付ける写像 */
    Map<T, Double> weightMap;

    /** コンストラクタ */
    public CounterWithWeight() {
        super();
        weightMap = new HashMap<T, Double>();
    }

    /** オブジェクトに実数値を対応付ける */
    public void putWeight(T obj, double weight) {
        weightMap.put(obj, weight);
    }

    /** オブジェクトに対応付けられた実数値を返す */
    public double getWeight(T obj) {
        Double weight = weightMap.get(obj);
        if (weight != null) {
            return weight.doubleValue();
        } else {
            return 0.0;
        }
    }

    /** 実数値が大きい順にオブジェクトをソートしたリストを作って返す */
    public List<T> getObjectListSortedByWeight() {
        List<T> objectList = getObjectList();
        Collections.sort(objectList, new Comparator<T>() {
            public int compare(T obj1, T obj2) {
                double diff = weightMap.get(obj2) - weightMap.get(obj1);
                return (diff > 0) ? 1 : (diff == 0 ? 0 : -1);
            }
        });
        return objectList;
    }
}
