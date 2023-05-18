package testprint;

public class Alma {
	Alma(){}
	
	public void Alfa() {
		Main.tabCount++;
		System.out.println(Main.Tabber(Main.tabCount) + "-------> Alma.Alfa()");
		System.out.println(Main.Tabber(Main.tabCount) + "<------- Alma.Alfa()");
		Main.tabCount--;
	}
	
	public void Alfafa(int i) {
		Main.tabCount++;
		System.out.println(Main.Tabber(Main.tabCount) + "-------> Alma.Alfafa(int i)");
		System.out.println(Main.Tabber(Main.tabCount) + "<------- Alma.Alfafa(int i)");
		Main.tabCount--;
	}
	
	public int AlmaBarackbanBeta(int i) {
		Main.tabCount++;
		System.out.println(Main.Tabber(Main.tabCount) + "-------> Alma.AlmaBarackbanBeta(int i)");
		
		Barack b = new Barack();
		b.Beta();

		System.out.println(Main.Tabber(Main.tabCount) + "<---i--- Alma.AlmaBarackbanBeta(int i)");
		Main.tabCount--;
		return i;
	}
	
	public void AlmabanAlma(int almak) {
		for(int i = 0; i < almak; i++) {
			Main.tabCount++;
			System.out.println(Main.Tabber(Main.tabCount) + "-------> Alma.AlmabanAlma(int almak)");
		}
		
		for(int i = almak; i > 0; i--) {
			System.out.println(Main.Tabber(Main.tabCount) + "<------- Alma.AlmabanAlma(int almak)");
			Main.tabCount--;
		}
	}
}
