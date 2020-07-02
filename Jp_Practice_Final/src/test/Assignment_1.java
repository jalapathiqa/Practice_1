package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.testng.annotations.Test;

public class Assignment_1 {

	public static void main(String[] args) throws Exception {

		Properties configProp = new Properties();
		File fileconfigProp = new File("C:\\config.properties");
		FileInputStream fileConfig = new FileInputStream(fileconfigProp);
		configProp.load(fileConfig);

		System.out.println(configProp.getProperty("url"));

	}
}
