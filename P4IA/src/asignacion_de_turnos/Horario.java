package asignacion_de_turnos;

import java.util.ArrayList;
import java.util.List;

import aima.core.util.datastructure.XYLocation;

public class Horario {

	private Integer[][] horario;
	private int size;
	
	public Horario(int size) {
		this.size = size;
		horario = new Integer[size][size];
		for(int i = 0; i < size; i++){ //Lunes  Martes Miercoles Jueves
			for (int j = 0; j < size; j++){
				horario[i][j] = -1;
			}
		}
	}
	
	public void addProfesorAt(XYLocation l, int p){
		if (!ProfesorExistsAt(l)){
			horario[l.getXCoOrdinate()][l.getYCoOrdinate()] = p;
		}
	}

	public boolean ProfesorExistsAt(XYLocation l) {
		return (ProfesorExistsAt(l.getXCoOrdinate(), l.getYCoOrdinate()));
	}
	private boolean ProfesorExistsAt(int x, int y) {
		return (horario[x][y] != -1);
	}

	public int getSize() {
		return size;
	}

	public List<XYLocation> getProfesorPositions() {
		ArrayList<XYLocation> result = new ArrayList<XYLocation>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (ProfesorExistsAt(i, j))
					result.add(new XYLocation(i, j));
			}
		}
		return result;
	}
}
