package test;

public class Day_2_ClassObjects {

	public static void main(String[] args) {

		System.out.println("hello this is Main method");

		Day_2_ClassObjects obj = new Day_2_ClassObjects();

		obj.carEMI();
		obj.bikeEMI();

		int x = obj.mothlyReturn();
		System.out.println(x);
		String y = obj.myName();
		System.out.println(y);
	}

	public void carEMI() {
		System.out.println("this is Car EMI");
	}

	public void bikeEMI() {
		System.out.println("this is Bike EMI");
	}

	public int mothlyReturn() {

		int a = 100;
		return a;
	}

	public String myName() {
		String n = "Jalapathi";
		return n;
	}
}
