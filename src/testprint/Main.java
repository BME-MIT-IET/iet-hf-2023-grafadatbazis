package testprint;

public class Main {

	static int tabCount = 0;
	
	public static void main(String[] args) {
		Main.Teszt();
	}
	
	public static void Teszt() {
		System.out.println(Main.Tabber(Main.tabCount) + "-------> Main.Teszt()");
		
		Alma a = new Alma();
		Barack b = new Barack();
		
		a.Alfa();
		a.Alfafa(1);
		a.AlmaBarackbanBeta(2);
		
		b.Beta();
		b.Betafa(1);
		
		a.AlmabanAlma(5);
		
		System.out.println(Main.Tabber(Main.tabCount) + "<------- Main.Teszt()");
	}
	
	public static String Tabber(int count) {
		StringBuilder sb = new StringBuilder(); 
	    for (int i = 0; i < count; i++) {
	        sb.append("\t");
	    }
	    return sb.toString();
	}
}
