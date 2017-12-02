package asignacion_de_turnos;

public class asignacion_de_turnosState {

	private int numTurnos;
	private Horario horario;
	
	public asignacion_de_turnosState(int n) {
		this.numTurnos = n;
		horario = new Horario(4);
	}
	
	public Horario getHorario(){
		return horario;
	}

	public int getNumTurnos(){
		return numTurnos;
	}
}
