package sample;

class Sample2_10 {
	public static void main(String[] args) {
		System.out.println(args[0]);
		
		System.out.println("args.length =" + args.length);
		System.out.println(args[1]);
		System.out.println(args[2]);
		System.out.println(args[1] + args[2]);
		
		int num = Integer.parseInt(args[1]);
		int num2 = Integer.parseInt(args[2]);
		System.out.println(num + num2);
	}
}