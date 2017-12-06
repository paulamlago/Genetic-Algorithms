package asignacion_de_turnos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import aima.core.search.framework.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.Individual;
import aima.core.util.datastructure.XYLocation;

public class asignacion_de_turnosGenAlgoUtil{

	public static int goal;
	public static int numeroDeProfesores;
	
	public asignacion_de_turnosGenAlgoUtil(){
		goal = 0;
		numeroDeProfesores = 0;
	}
	
	public static FitnessFunction<Profesor> getFitnessFunction() {
		return new asignacion_de_turnosFitnessFunction();
	}
	
	public static GoalTest getGoalTest(int turnos, int numP){
		goal = turnos;
		numeroDeProfesores = numP;
		
		return new asignacion_de_turnosGoalTest();
	}
	
	public static Individual<Profesor> generateRandomIndividual(int boardSize, List<Profesor> prof) {
		List<Profesor> individualRepresentation = new ArrayList<Profesor>();
		List<Profesor> yaUsados = new ArrayList<>();
		List<Integer> posYaUsadas = new ArrayList<>();
		//int turnosPorProfe = (int) Math.ceil(goal / numeroDeProfesores);
		
		//esto simplemente asigna profesores en turnos siempre que no esté en sus reestricciones
		
		int count = 0;
		
		while (count < goal){
			int i = new Random().nextInt(15);
	
			if (!posYaUsadas.contains(i)){
					Profesor profe = prof.get(new Random().nextInt(prof.size()));
					
					if (!yaUsados.contains(profe)) profe.setLocatedAt(new ArrayList<>());
							
					if (!profe.getRestricciones().contains(i + 1)){
						profe.addLocatedAt(i + 1);
						individualRepresentation.add(profe);
						count++;
						yaUsados.add(profe);
						posYaUsadas.add(i);
					}
			}
		}
		
		/**
		int count = 0;
		while (count < goal){
			Profesor profe = prof.get(new Random().nextInt(prof.size()));
			
			if (!yaUsados.contains(profe)){
				profe.setLocatedAt(new ArrayList<>());
				yaUsados.add(profe);
				if (profe.getRestricciones().size() + turnosPorProfe >= boardSize*boardSize){
					turnosPorProfe++; //para cubrir el de este profe
				}
			}
			
			if (profe.getLocatedAt().size() <= turnosPorProfe){ //si puedo trabajar con este profe..
				List<Integer> p = profe.getPreferencias();
				List<Integer> r = profe.getRestricciones();
				
				//trabajamos primero con los que tienen preferencias
				if (!p.isEmpty()){
					//recorro el tablero y si una pos no está en sus restricciones y sí en sus preferencias y no hay nadie en esa casilla lo introduzlo
					int i = 1;
					while (i <= boardSize*boardSize && profe.getLocatedAt().size() <= turnosPorProfe){
						if (!posYaUsadas.contains(i) && !r.contains(i) && p.contains(i)){
							profe.addLocatedAt(i);
							individualRepresentation.add(profe);
							count++;
							posYaUsadas.add(i);
						}
						i++;
					}
				}
				
				//si tras asignar turnos a los que tienen preferencias nos faltan turnos que asignar:
				if ( count < goal && yaUsados.size() == prof.size()){ 
					int i = 1;
	
					while (i <= boardSize*boardSize && profe.getLocatedAt().size() <= turnosPorProfe){
						if (!posYaUsadas.contains(i) && !r.contains(i)){
							profe.addLocatedAt(i);
							individualRepresentation.add(profe);
							count++;
							posYaUsadas.add(i);
						}
						i++;
					}
				}
			}
		}*/
		
		Individual<Profesor> individual = new Individual<Profesor>(individualRepresentation);
		
		return individual;
	}
	
	public static Collection<Profesor> getFiniteAlphabetForBoardOfSize(int size, List<Profesor> p) {
		Collection<Profesor> fab = new HashSet<Profesor>();

		for (int i = 0; i < size; i++) {
			fab.add(p.get(i));
		}

		return fab;
	}
	
	public static Horario getBoardForIndividual(Individual<Profesor> individual) { //???
		int boardSize = individual.length();
		
		Horario board = new Horario(boardSize);
		
		for (int i = 0; i < boardSize; i++) {
				Profesor p = individual.getRepresentation().get(i);
				if (p.nombre != ""){
					List<Integer> l = p.getLocatedAt();
					if (l.size() > 0){
						for (int j = 0; j < l.size(); j++){
							board.addProfesorAt(board.getCoordinate(l.get(j)), p);
						}
					}
				}
		}

		return board;
	}
	
	public static class asignacion_de_turnosFitnessFunction implements FitnessFunction<Profesor>{
		
		public double apply(Individual<Profesor> individual) {
			
			double fitness = 0.0;
			Horario board = getBoardForIndividual(individual);
			//número de profesores asignados en horarios de su preferencia y que no estén en sus restricciones
			double turnosPorProfe = Math.ceil((double) goal / numeroDeProfesores);
			
			List<XYLocation> posiciones = board.getProfesorPositions();
			
			for (int i = 0;  i < posiciones.size(); i++){
				List<Integer> pref = new ArrayList<>();
				
				XYLocation pos = posiciones.get(i);
				Profesor p = board.getProfesorAt(pos);
				pref = p.getPreferencias();
				
				//compruebo si esá en una preferente
				
				int turno = board.getTurnoAt(pos);
				if (pref.contains(turno)) fitness += 1.0;
				
				//si el profe está más veces de las que dbería -> restamos
				if (p.getLocatedAt().size() > turnosPorProfe){
					fitness-= 1.0;
				}
				else fitness += 0.5;
			}
			
			return fitness > 0 ? fitness :0;
		}

		@Override
		public double getValue(Individual<Profesor> arg0) {
			
			return apply(arg0);
		}

	}

	public static class asignacion_de_turnosGoalTest implements GoalTest{
		
		@Override
		public boolean isGoalState(Object state) {
			Individual<Profesor> e = (Individual<Profesor>) state;
			Horario h = getBoardForIndividual(e);
			
			return goal == h.getTurnosYaAsignados() ? true : false;
		}

	}

}
