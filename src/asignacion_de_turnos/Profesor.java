package asignacion_de_turnos;

import java.util.ArrayList;
import java.util.List;

public class Profesor{

	public String nombre;
	private List<Integer> restricciones;
	private List<Integer> preferencias;
	private int locatedAt;
	
	public Profesor(String n){
		this.nombre = n;
		restricciones = new ArrayList<Integer>();
		preferencias = new ArrayList<Integer>();
		locatedAt = -1;
	}
	
	public Profesor(String n, int pos, List<Integer> restricciones, List<Integer> preferencias){
		this.nombre = n;
		this.restricciones = restricciones;
		this.preferencias = preferencias;
		locatedAt = pos;
	}
	
	public void setRestricciones(List<Integer> r){
		this.restricciones = r;
	}
	
	public List<Integer> getRestricciones(){
		return restricciones;
	}

	public void setPreferencias(List<Integer> p){
		this.preferencias = p;
	}
	
	public List<Integer> getPreferencias(){
		return preferencias;
	}


	public /**List<Integer>*/ int getLocatedAt() {
		return locatedAt;
	}

	public void setLocatedAt(/**List<Integer>*/ int  e){
		this.locatedAt = e;
	}
	
	public String toString(){
		String s = this.locatedAt + " ";
		return s;
	}
}
