package newjava;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("cherry", "チェリー");
		map.put("apple", "リンゴ");
		map.put("pear", "ナシ");
		map.put("banana", "バナナ");
		map.put("grape", "ブドウ");

		System.out.println(map);
		System.out.println(map.get("banana"));
		System.out.println(map.getOrDefault("pineapple", "未登録"));

	}

}
