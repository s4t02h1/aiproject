interface Employee
{
	public int salary();
	public int bonus();
}

class Manager implements Employee
{
	private static final int mgrSal = 40000;
	private static final int mgrBonus = 0;
	public int salary()
	{
		return mgrSal;
	}
	public int bonus()
	{
		return mgrBonus;
	}
}

class Programmer implements Employee
{
	private static final int prgSal = 50000;
	private static final int prgBonus = 10000;
	public int salary()
	{
		return prgSal;
	}
	
	public int bonus()
	{
		return prgBonus;
	}
}

class Payroll
{
	
	public int calcPayroll(Employee emp)
	{
//		int money = emp.salary();
//		if (emp instanceof Programmer)
//			money += ((Programmer)emp).bonus();
//		return money;
		return emp.salary() + emp.bonus();
	}
	
	public static void main(String[] args)
	{
		long start = System.nanoTime();
		Payroll pr = new Payroll();
		Programmer prg = new Programmer();
		Manager mgr = new Manager();
		System.out.println("Payroll for Programmer is " +
							pr.calcPayroll(prg));
		System.out.println("Payroll for Manager is " +
							pr.calcPayroll(mgr));
		long end = System.nanoTime();
		long time = end - start;
		System.out.println("That took " + time + " nanoseconds");	
	}
		
}