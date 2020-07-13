package sample;

class Sample5_8 {
	int instanceVal;
	static int staticVal;

	int methodA() {return instanceVal; }			//OK
	int methodB() {return staticVal; }			//OK
	// static int methodC() {return instanceVal;}	//NG
	static int methodD() {
		staticVal = 100;
		return staticVal;}	//OK
	static int methodE() {							//OK
		Sample5_8 obj = new Sample5_8();
		obj.instanceVal = 200;
		return obj.instanceVal;
	}

	public static void main(String[] args) {
		System.out.println(Sample5_8.methodD());
		System.out.println(Sample5_8.methodE());

		Sample5_8 obj = new Sample5_8();
		System.out.println(obj.methodA());
		System.out.println(obj.methodB());
	}
}
