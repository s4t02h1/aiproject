package newjava;

import java.util.List;

public class BookExec {
	public static void main(String[] args) {
		List<Book> list = Book.getList();
		list.stream()
		//	.filter(s->s.getGenre()== Genre.SCIENCE)
			.filter(s-> Genre.SCIENCE.equals(s.getGenre()))
		//	.map(Book::getTitle)
			.forEach(book-> System.out.println(book.getTitle()));
		//	.forEach(System.out::println);
	}
}
