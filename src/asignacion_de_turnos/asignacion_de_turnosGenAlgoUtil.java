package asignacion_de_turnos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import aima.core.search.framework.problem.GoalTest;
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
		
		//esto simplemente asigna profesores en turnos siempre que no estï¿½ en sus reestricciones
		
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
					//recorro el tablero y si una pos no estï¿½ en sus restricciones y sï¿½ en sus preferencias y no hay nadie en esa casilla lo introduzlo
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
	
	public Collection<Profesor> getFiniteAlphabetForBoardOfSize(int size, List<Profesor> p) {
		Collection<Profesor> fab = new ArrayList<Profesor>();

		for (int i = 0; i < size; i++) {
			fab.add(p.get(i));
		}

		return fab;
	}
	
	public static Horario getBoardForIndividual(Individual<Profesor> individual) {
		Horario board = new Horario();
		List<Profesor> representation = individual.getRepresentation();
		for (int i = 0; i < representation.size(); i++) {
				Profesor p = representation.get(i);

					List<Integer> l = p.getLocatedAt();
					if (l.size() > 0){
						for (int j = 0; j < l.size(); j++){
							board.addProfesorAt(board.getCoordinate(l.get(j)), p);
						}
					}
				
		}

		return board;
	}
	
	public static class asignacion_de_turnosFitnessFunction implements FitnessFunction<Profesor>{
		
		public double apply(Individual<Profesor> individual) {
			
			double fitness = 0.0;
			Horario board = getBoardForIndividual(individual);
			//nï¿½mero de profesores asignados en horarios de su preferencia y que no estï¿½n en sus restricciones
			double turnosPorProfe = Math.ceil((double) goal / numeroDeProfesores);
			
			List<XYLocation> posiciones = board.getProfesorPositions();
			
			for (int i = 0;  i < posiciones.size(); i++){ //recorre cada posición en la que hay profesores
				List<Integer> pref = new ArrayList<>();
				
				XYLocation pos = posiciones.get(i);
				Profesor p = board.getProfesorAt(pos); //coge al profesor de esa posición
				pref = p.getPreferencias();
				
				//compruebo si esta en una preferente
				
				int turno = board.getTurnoAt(pos);
				if (pref.contains(turno)) fitness += 1.0;
				
				//si el profe esta mas veces de las que dberï¿½a -> restamos
				if (p.getLocatedAt().size() > turnosPorProfe){
					fitness-= 1.0;
				}
				else fitness += 0.5;
			}
			
			return fitness > 0 ? fitness :0;
		
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
