package newjava;

import java.util.Locale;

public class Sample12_1 {

	public static void main(String[] args) {
		Locale japan = Locale.getDefault();
		System.out.println(japan.getDisplayCountry() + " : " +
						   japan.getDisplayLanguage());
		Locale us = new Locale("en", "US");
		System.out.println(us.getDisplayCountry() + " : " +
						   us.getDisplayLanguage());
		System.out.println(us.getDisplayCountry(us) + " : " +
				   us.getDisplayLanguage(us));
		System.out.println(us.getDisplayCountry() + " : " +
				   us.getDisplayLanguage());
		Locale lb = new Locale.Builder().setLanguage("ja")
				                        .setScript("Jpan")
				                        .setRegion("JP").build();
        System.out.println(lb.getCountry() + " : " +
				           lb.getLanguage());
	}

}
