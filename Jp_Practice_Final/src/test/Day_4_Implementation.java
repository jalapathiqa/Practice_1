package test;

public class Day_4_Implementation implements Day_4_Interface {

	public static void main(String[] args) {

		Day_4_Implementation x = new Day_4_Implementation();
		x.submitLeave();
		x.approveLeave();
		x.submitTravelExpense();
		x.approveTravelExpenses();
		x.notInterfaceMethod();

		// invalid - can not create object off an interface
		// if your class is not fully implemented then you can not create object of that
		// class
		// Day_4_Interface y=new Day_4_Interface();

		Day_4_Interface z = new Day_4_Implementation();
		x.submitLeave();
		x.approveLeave();
		x.submitTravelExpense();
		x.approveTravelExpenses();

	}

	@Override
	public void submitLeave() {
		System.out.println("leave submitted");
	}

	@Override
	public void approveLeave() {
		System.out.println("leave approved");

	}

	@Override
	public void submitTravelExpense() {
		System.out.println("travel expenses submitted");

	}

	@Override
	public void approveTravelExpenses() {
		System.out.println("travel expenses approved");

	}

	public void notInterfaceMethod() {
		System.out.println("notInterfaceMetod is not implemented yet");

	}

}
