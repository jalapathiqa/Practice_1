package test;

public class Day_4_Inheritance_HomeLoanCalc extends Day_4_Inheritance_EMIBaseClass {

	public static void main(String[] args) {

		Day_4_Inheritance_HomeLoanCalc a = new Day_4_Inheritance_HomeLoanCalc();

		a.emiCalc();
		a.personalLoanEMI();
		a.carEMI();

		Day_4_Inheritance_EMIBaseClass b = new Day_4_Inheritance_EMIBaseClass();
		b.emiCalc();
		b.personalLoanEMI();

		// reference base but creating object of child class
		Day_4_Inheritance_EMIBaseClass c = new Day_4_Inheritance_HomeLoanCalc();
		c.emiCalc();
		c.personalLoanEMI();

	}

	public void carEMI() {
		System.out.println("car emi calculator");
	}

}
