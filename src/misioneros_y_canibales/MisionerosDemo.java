package misioneros_y_canibales;

import misioneros_y_canibales.demos.DemoBusquedasInformadas;
import misioneros_y_canibales.demos.DemoBusquedasNoInformadas;

public class MisionerosDemo {
	
	public static void main(String[] args) {
		DemoBusquedasNoInformadas noInf = new DemoBusquedasNoInformadas();
		DemoBusquedasInformadas inf = new DemoBusquedasInformadas();
		
		/**noInf.MisionerosDFSDemo();
		noInf.MisionerosBFSDemo();
		noInf.MisionerosUCSDemo();*/
		
		inf.MisionerosASDemo();
	}
	
}