package newjava;

public class Seiseki implements Comparable<Seiseki>{
	private String name;
	private int kokugo;
	private int suugaku;
	public Seiseki(String name, int kokugo, int suugaku) {
		this.name = name;
		this.kokugo = kokugo;
		this.suugaku = suugaku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKokugo() {
		return kokugo;
	}

	public void setKokugo(int kokugo) {
		this.kokugo = kokugo;
	}

	public int getSuugaku() {
		return suugaku;
	}

	public void setSuugaku(int suugaku) {
		this.suugaku = suugaku;
	}

	@Override
	public int compareTo(Seiseki s) {
		return Integer.compare((kokugo + suugaku) , s.kokugo + s.suugaku);
	}

	@Override
	public String toString() {
		return  name + "/" + (kokugo + suugaku);
	}

}
