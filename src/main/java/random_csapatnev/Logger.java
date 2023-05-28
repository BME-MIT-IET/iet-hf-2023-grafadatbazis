package random_csapatnev;

public class Logger {
	static int tabCount = 0;

	private Logger() {
		// Statikus osztály
	}

	public static String tabber(int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append("\t");
		}
		return sb.toString();
	}

	public static void out(java.util.logging.Level inplevel, String inp) {
		java.util.logging.Logger.getGlobal().log(inplevel, inp);
	}
}
