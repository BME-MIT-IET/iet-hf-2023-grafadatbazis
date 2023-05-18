package random_csapatnev;

public class Logger {
	static int tabCount = 0;
	
	public static String Tabber(int count) {
		StringBuilder sb = new StringBuilder(); 
	    for (int i = 0; i < count; i++) {
	        sb.append("\t");
	    }
	    return sb.toString();
	}
	
	public static void Out(String inp) {
		System.out.println(Tabber(tabCount) + inp);
	}
}
