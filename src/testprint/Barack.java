package testprint;

public class Barack {
	Barack() {}
	
	public void Beta() {
		Main.tabCount++;
		System.out.println(Main.Tabber(Main.tabCount) + "-------> Barack.Beta()");
		System.out.println(Main.Tabber(Main.tabCount) + "<------- Barack.Beta()");
		Main.tabCount--;
	}
	
	public void Betafa(int i) {
		Main.tabCount++;
		System.out.println(Main.Tabber(Main.tabCount) + "-------> Barack.Betafa(int i)");
		System.out.println(Main.Tabber(Main.tabCount) + "<------- Barack.Betafa(int i)");
		Main.tabCount--;
	}
}
