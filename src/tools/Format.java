package tools;

public class Format {

	public static String getBlanks(int length) {
		String blanks = "";
		for (int i = 0; i < length; i++) {
			blanks += "　";
		}
		return blanks;
	}
}
