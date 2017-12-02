package asignacion_de_turnos;

public class Profesor {

	public String nombre;
	private int[] restricciones;
	private int[] preferencias;
	
	public Profesor(String n){
		this.nombre = n;
	}
	
	
	public void setRestricciones(int[] r){
		this.restricciones = r;
	}
	
	public int[] getRestricciones(){
		return restricciones;
	}

	public void setPreferencias(int[] p){
		this.preferencias = p;
	}
	
	public int[] getPreferencias(){
		return preferencias;
	}
}
