package asignacion_de_turnos;

import java.util.ArrayList;
import java.util.List;

import aima.core.util.datastructure.XYLocation;

public class Horario {

	private Profesor[][] horario;
	private int[][] turnos;
	
	private int size;
	private int turnosYaAsignados;
	private int goal;
	
	public Horario(int size) {
		this.size = size;
		this.setTurnosYaAsignados(0);
		horario = new Profesor[size][size];
		turnos = new int[size][size];
		
		int t;
		
		for(int i = 0; i < size; i++){ //Lunes  Martes Miercoles Jueves
			t = i + 1;
			for (int j = 0; j < size; j++){
				horario[i][j] = new Profesor("");
				turnos[i][j] = t;
				t +=4;
			}
		}
	}
	
	
	public void addProfesorAt(XYLocation loc, Profesor p){
	
		if (!ProfesorExistsAt(loc)){
			int i = loc.getXCoOrdinate();
			int j = loc.getYCoOrdinate();
			if(!p.getRestricciones().contains(turnos[i][j])){
				p.addLocatedAt(turnos[i][j]);
				horario[i][j] = p;
				this.setTurnosYaAsignados(this.getTurnosYaAsignados() + 1);
			}
		}
	}

	public void removeProfesorAt(XYLocation loc){
		if (ProfesorExistsAt(loc)){
			int i = loc.getXCoOrdinate();
			int j = loc.getYCoOrdinate();
			horario[i][j].removeLocatedAt(turnos[i][j]);
			horario[i][j] = new Profesor(""); //dejamos un profesor vacío
		}
	}
	
	public boolean ProfesorExistsAt(XYLocation l) {
		return (ProfesorExistsAt(l.getXCoOrdinate(), l.getYCoOrdinate()));
	}
	private boolean ProfesorExistsAt(int x, int y) {
		return (horario[x][y].nombre != "");
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

	public int getTurnosYaAsignados() {
		return turnosYaAsignados;
	}
		
	public Profesor getProfesorAt(XYLocation loc){
		int x = loc.getXCoOrdinate();
		int y = loc.getYCoOrdinate();
		return horario[x][y];
	}
	
	public int getTurnoAt(XYLocation loc){
		int x = loc.getXCoOrdinate();
		int y = loc.getYCoOrdinate();
		return turnos[x][y];
	}
	
	public XYLocation getCoordinate(int loc){

		XYLocation sol = null;
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				if (turnos[i][j] == loc){
					sol = new XYLocation(i, j);
				}
			}
		}
		
		return sol;
	}
	
	public int getGoal() {
		return goal;
	}

	public void setTurnosYaAsignados(int turnosYaAsignados) {
		this.turnosYaAsignados = turnosYaAsignados;
	}

	
	public void setGoal(int g){
		this.goal = g;
	}
}
