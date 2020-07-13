/** 演習java:Library
 * 図書の貸出と返却の処理をするプログラムである。
 * 図書には書籍と雑誌があり、利用者が借りることができる図書の冊数には上限がある。
 *
 * （1） 抽象クラスBookは、図書に共通の属性と振舞を定義する。
 * 属性nameは図書の名前であり、属性idは図書に一意につけられた識別子である。
 * （２） クラスRegularBookは書籍を表す。
 * （３）クラスMagazineは雑誌を表す。
 * 属性issuNoは雑誌の創刊号からの号数で、古い号から昇順の値を持つ。
 * 各雑誌について、issueNoが最大のものが最新号である。
 * （４） クラスUserは図書の利用者を表す。
 * 属性nameは利用者の名前であり、属性idは利用者に一意につけられた識別子である。
 * （５） クラスLibraryは、貸出と返却の処理をする。
 * 属性availablesは貸出可能な図書の集合を表し、属性checkOutは貸出中の図書の集合を表す。
 * 属性limitは利用者が借りることのできる図書の冊数の上限を与える。
 * コンストラクタで、引数で渡される図書Book[]の各要素を、属性availablesに追加する。
 * メソッドcheckedoutBookは、貸出処理を実行する。指定された図書がすでに貸出されている場合と利用者がすでに
 * 貸出冊数の上限まで図書を借りている場合、例外を投げる。
 * メソッドreturnBookは返却処理を実行する。
*/
package newjava;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 抽象図書クラス
 * 図書に共通の属性と振舞を定義する。
 */

abstract class Book1{

	/**図書名*/
	String name;
	/**図書の一意な識別子*/
	String id;

	/**
	 *コンストラクタ
	 *初期値を設定する。
	 *
	 * @param name 図書名
	 * @param id
	 * @return 図書の一意な識別子
	 */
	public Book1(String name, String id) {
		this.name = name;
		this.id = id;
	}

	/**
	 * 同値判定を行う。
	 *
	 * @param name 判定対象オブジェクト
	 */

	public boolean equals(Object object) {
		return (object instanceof Book)
				&& id.equals(((Book)object).id);
	}

	/**
	 * ハッシュコードを返す。
	 */

	public int hashCode() {
		return id.hashCode();
	}

}

/**
 * 書籍クラス
 * @author eeb-tosh-16
 *
 */

class RegularBook extends Book {

	/**
	 * コンストラクタ
	 * 初期値を設定する。
	 *
	 * @param name 図書名
	 * @param id 図書の一意な識別子
	 */
	public RegularBook(String name, String id) {
		super();
	}

	/**
	 * 文字列変換処理を行う。
	 */

	public String toString() {
		String name = null;
		return "Book: " + name + "(" + id + ")";
	}
}

/**
 * 雑誌クラス
 * @author eeb-tosh-16
 *
 */

class Magazine extends Book{

	/**号数*/
	int issueNo;
	public Object name;

	/**
	 * コンストラクタ
	 * 初期値を設定する。
	 *
	 * @param name
	 * @param issueNo
	 * @param id
	 */
	public Magazine(String name, int issueNo, String id) {
		super();
		this.issueNo = issueNo;
	}

	public String toString() {
		String name = null;
		return "Magazine: " + name + ", No." + issueNo
									+ "(" + id + ")";
	}
}

/**
 * 利用者クラス
 *
 * @author eeb-tosh-16
 *
 */

class User{

	/** 利用者名 */
	String name;
	/** 利用者の一意な識別子*/
	String id;

	/**
	 * コンストラクタ
	 * 初期値を設定する。
	 *
	 * @param name 利用者名
	 * @param id 利用者の一意な識別子
	 */

	User(String name, String id){
		this.name = name;
		this.id = id;
	}

	/**
	 * 文字列変換処理を行う。
	 */
	public String toString() {
		return "User: " + name + "(" + id + ")";
	}
}

/**
 * 図書館クラス
 * 図書の貸出と返却の処理を行う。
 *
 * @author eeb-tosh-16
 *
 */
public class Library {

	/** 貸出可能な図書の集合*/
	Set<Book> availables = new HashSet<>();
	Set<Magazine> latestMagazines = new HashSet<Magazine>();

	/** 貸出中の図書の集合*/
	Map<Book,User> checkedOut = new HashMap<>();
	/** 利用者が借りることができる冊数の上限*/
	int limit;

	/**
	 * コンストラクタ
	 * 初期値を設定する。
	 *
	 * @param books 図書の配列
	 * @param limit 利用者が借りることができる冊数の上限
	 */
	public Library(Book[] books, int limit) {
		for (Book book : books)register(book);
		this.limit = limit;
	}

	/**
	 * 引数で渡された図書を貸出可能な図書の集合に追加する。
	 *
	 * @param book 貸出可能な図書の集合に追加する図書
	 */
	void register(Book book) {
		availables.add(book);
		if (book instanceof Magazine) {
			updateLatestMagazineList((Magazine)book);
		}
	}

	void checkoutBook(User user, Book book) throws Exception {

		if (!availables.contains(book)) {
			throw new Exception("unavailables");
		}

		if (book instanceof Magazine) {
			Magazine magazine = (Magazine)book;
			if (latestMagazines.contains(magazine)) {
				throw new Exception("latest issue");
			}
		}

		int count = 0;

		for (Map.Entry<Book, User> entry : checkedOut.entrySet()) {
			if (entry.getValue().equals(user)) count++;
		}
		if (count >= limit) {
			throw new  Exception("exceeding checkout limit");
		}

		availables.add(book);
		availables.remove(book);
	}
	void returnBook(Book book) {

		for (Map.Entry<Book, User> entry : checkedOut.entrySet()) {
			if (entry.getKey().equals(book)) {
				checkedOut.remove(book);
				availables.add(book);
				return;
			}
		}
	}

	void  updateLatestMagazineList(Magazine magazine) {
		for (Magazine magazine2 : latestMagazines) {
			if (magazine2.name.equals(magazine.name)) {
				return;
			} else {
				latestMagazines.remove(magazine2);
				break;
			}
		}
		latestMagazines.add(magazine);
	}


	public static void main(String[] args) {

		Book java = new RegularBook("Java Programming", "P001");
		Book perl = new RegularBook("Perl Programming", "P002");
		Book ruby = new RegularBook("Ruby Programming", "P003");
		Book jit39_12 = new Magazine("JITEC News", 3912, "M001");
		Book jit40_01 = new Magazine("JITEC News", 4001, "M002");
		Book[] books = {java, perl, ruby, jit39_12, jit40_01};

		Library library = new Library(books, 3);

		User taro = new User("Taro", "ID-01");
		User hana = new User("Hana", "ID-02");

		Book[] books1 = {jit39_12, jit40_01, java, ruby};
		for (Book book : books1) {
			try {
				System.out.println("Taro is checking out " + book);
				library.checkoutBook(taro, book);
				System.out.println();
			} catch (Exception e) {
				System.out.println("\n" + " => failed" + e.getMessage());
			}
		}
		library.returnBook(jit39_12);
		System.out.println("Taro returned " + jit39_12);

		Book[] books2 = {jit39_12, java};
		for (Book book :books2) {
			try {
				System.out.println("Hana is checking out " + book);
				library.checkoutBook(hana, book);
				System.out.println();
			} catch (Exception e) {
				System.out.println("\n" + " => failed " + e.getMessage());
			}
		}
	}
}
