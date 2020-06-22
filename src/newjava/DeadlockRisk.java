package newjava;

public class DeadlockRisk {
	private static class Resource{
		public int value;
	}
	private Resource resourceA = new Resource();
	private Resource resourceB = new Resource();
	public int read(){
		synchronized(resourceA){
			try {
			Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(resourceB){
				return resourceB.value + resourceA.value;
			}
		}
	}
	public void write(int a, int b){
		synchronized(resourceB){
			synchronized(resourceA){
				resourceA.value = a;
				resourceB.value = b;
				System.out.println("resourceA.value:"+ resourceA.value
						+ " resourceB.value" + resourceB.value);
			}
		}
	}
	public static void main(String[] args) {
		DeadlockRisk dr = new DeadlockRisk();
		new Thread(() ->{
			int n = dr.read();
			System.out.println("resourceB.value + resourceA.value:" + n);
		}).start();
		new Thread(() ->{
			dr.write(100, 200);
		}).start();;
	}
}
