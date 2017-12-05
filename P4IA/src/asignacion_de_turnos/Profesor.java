package asignacion_de_turnos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import aima.core.util.datastructure.XYLocation;

public class Profesor{

	public String nombre;
	private List<Integer> restricciones;
	private List<Integer> preferencias;
	private List<Integer> locatedAt;
	
	public Profesor(String n){
		this.nombre = n;
		restricciones = new ArrayList<Integer>();
		preferencias = new ArrayList<Integer>();
		locatedAt = new ArrayList<Integer>();
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


	public List<Integer> getLocatedAt() {
		return locatedAt;
	}


	public void addLocatedAt(Integer loc) {
		locatedAt.add(loc);
	}
	public void removeLocatedAt(Integer loc){
		Iterator<Integer> it = locatedAt.iterator();
		while (it.hasNext()){
			if (it.next() == loc){
				it.remove();
			}
		}
	}
}
