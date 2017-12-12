package asignacion_de_turnos;

import java.util.ArrayList;
import java.util.List;

public class Profesor implements Comparable<Profesor>{

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


	public int getLocatedAt() {
		return locatedAt;
	}

	public void setLocatedAt(int  e){
		this.locatedAt = e;
	}
	
	public String toString(){
		String s = this.locatedAt + " ";
		return s;
	}
	
	public Boolean locatedAtPreference(){
		int i = 0;
		Boolean preferencia = false;
		while (i < preferencias.size()){
			if (preferencias.get(i) == locatedAt){
				preferencia = true;
			}
			i++;
		}
		//if (preferencias.size() == 0) return true;
		return preferencia;
	}
	
	@Override
	public int compareTo(Profesor arg0) {
		if (this.locatedAt < arg0.locatedAt){
			return -1;
		}
		return 1;
	}
}