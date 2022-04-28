package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	public static void printMessage(String msg) {
		System.out.println(msg);
	}

	public static void printStars() {
		printMessage("*************************");
	}

	public static boolean isNumber(String string) {
		try {
			Long.parseLong(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
		Pattern p = Pattern.compile("^[7689]\\d{9}$");
		Matcher m = p.matcher(phoneNumber);
		return (m.matches());
	}

	public static boolean isValidAccountNumber(String accountNumber) {
		Pattern p = Pattern.compile("^\\d{5}$");
		Matcher m = p.matcher(accountNumber);
		return (m.matches());
	}

	public static boolean isAlphabets(String string) {
		return string.matches("[a-zA-Z]+");
	}

	public static String getProviderLocation() {
		return "C:\\Users\\Administrator\\eclipse-workspace\\PayTM\\src\\resources\\providerAndPlans";
	}

}
