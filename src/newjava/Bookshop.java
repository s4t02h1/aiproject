package newjava;

public class Bookshop implements Readable {
		private static String name;
		public Bookshop(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public void read() {
			System.out.println("Readable");
	}

}

