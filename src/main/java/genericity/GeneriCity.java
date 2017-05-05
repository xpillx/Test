package genericity;


public class GeneriCity<T> {
	private T t;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
	
	public static void main(String[] args) {
		GeneriCity<Integer> gcint=new GeneriCity<Integer>();
		gcint.setT(1);
		System.out.println(gcint.getT());
		GeneriCity<Double> gcDouble=new GeneriCity<Double>();
		gcDouble.setT(1.1);
		System.out.println(gcDouble.getT());
		GeneriCity<String> gcString=new GeneriCity<String>();
		gcString.setT("sjdis");
		System.out.println(gcString.getT());
	}
}
