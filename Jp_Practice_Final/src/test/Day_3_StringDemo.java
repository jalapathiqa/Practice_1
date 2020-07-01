package test;

import java.util.stream.IntStream;

public class Day_3_StringDemo {

	public static void main(String[] args) {

		String tool = "Selenium WebDriver";
		String tool1 = "Selenium webDriver";

		IntStream x = tool.chars();
		System.out.println(x);

		System.out.println(tool.startsWith("Selenium"));
		System.out.println(tool.endsWith("Selenium"));
		System.out.println(tool.endsWith("WebDriver"));
		System.out.println(tool.toUpperCase());
		System.out.println(tool.contains("ele"));
		System.out.println("equals: " + tool.equals(tool1));
		System.out.println("equal ignorecase: " + tool.equalsIgnoreCase(tool1));

	}
}
