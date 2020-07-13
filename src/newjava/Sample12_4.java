package newjava;

import java.util.Locale;
import java.util.ResourceBundle;

public class Sample12_4 {

	public static void main(String[] args) {
		Locale japan = Locale.getDefault();
		Locale us = new Locale("en", "US");
		Locale[] locArray = {japan, us};
		for (Locale locale : locArray) {
			ResourceBundle obj1
					= ResourceBundle.getBundle("MyResources", locale);
			System.out.println("send   : " + obj1.getString("send"));
			System.out.println("cancel : " + obj1.getString("cancel"));
		}

	}

}
