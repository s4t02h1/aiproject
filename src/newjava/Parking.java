package newjava;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Parking {
	private int pnumber;
	private LocalDate date;
	private String number;

	public Parking(int pnumber, LocalDate date, String number) {
		this.pnumber = pnumber;
		this.date = date;
		this.number = number;
	}

	public int getPnumber() {
		return pnumber;
	}

	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pnumber;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parking other = (Parking) obj;
		if (pnumber != other.pnumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parking [pnumber=" + pnumber + ", date=" + date + ", number=" + number + "]";
	}
}

class PnumberComparator implements Comparator<Parking>{
	@Override
	public int compare(Parking o1, Parking o2) {
		return Integer.compare(o1.getPnumber(), o2.getPnumber());
	}
}

class NumberComparator implements Comparator<Parking>{
	@Override
	public int compare(Parking o1, Parking o2) {
		return Integer.compare(o1.getNumber() - o2.getNumber());
	}

}

class ParkingList<E> {
	public static void main(String[] args) {
	List<Parking> list = new ArrayList<>();
		list.add(new Parking(102, LocalDate.of(2021, 7, 8), "Y-111-222"));
		list.add(new Parking(205, LocalDate.of(2021, 10, 1), "Z-111-222"));
		list.add(new Parking(101, LocalDate.of(2020, 3, 12), "X-111-222"));
		list.sort(new PnumberComparator());
		for (Parking pk : list) {
			System.out.println(pk);
		}
	}
}
