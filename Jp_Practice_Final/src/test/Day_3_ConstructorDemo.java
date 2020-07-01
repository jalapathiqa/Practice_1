package test;

public class Day_3_ConstructorDemo {

	public static void main(String[] args) {

		Day_3_ConstructorDemo obj = new Day_3_ConstructorDemo();
		obj.calcEMI();
		obj.calcEMI(10);
		obj.calcEMI("name");
		obj.calcEMI(10, 1);
		obj.calcEMI(1, "address");
		obj.calcEMI("name", "address");
		obj.calcEMI("address", "name");

	}

	public void calcEMI() {

		System.out.println("with zero arguments");
	}

	public void calcEMI(int sum) {

		System.out.println("with integer arguments");

	}

	public void calcEMI(int sum, int a) {
		System.out.println("with 2 integers arguments");

	}

	public void calcEMI(String name) {
		System.out.println("with string arguments");

	}

	public void calcEMI(String name, int address) {
		System.out.println("with String & int arguments");

	}

	public void calcEMI(String address, String name) {
		System.out.println("with 2 strings arguments");

	}

	public void calcEMI(int name, String address) {
		System.out.println("with int & string arguments");

	}
}
