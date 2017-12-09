package asignacion_de_turnos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
		List<Integer> posYaUsadas = new ArrayList<>();

		//esto simplemente asigna profesores en turnos siempre que no esten en sus reestricciones
		
		int count = 0;
		
		while (count < goal){
			int i = new Random().nextInt(16) + 1;
	
			if (!posYaUsadas.contains(i)){
					Profesor profe = prof.get(new Random().nextInt(prof.size()));
					
					if (!profe.getRestricciones().contains(i)){
						individualRepresentation.add(new Profesor(profe.nombre, i, profe.getRestricciones(), profe.getPreferencias()));
						count++;
						posYaUsadas.add(i);
					}
			}
		}
		
		Individual<Profesor> individual = new Individual<Profesor>(individualRepresentation);
		
		return individual;
	}
	
	public static Collection<Profesor> getFiniteAlphabetForBoardOfSize(int size, List<Profesor> p) {
		Collection<Profesor> fab = new ArrayList<Profesor>();

		for (int i = 0; i < p.size(); i++) {
			fab.add(p.get(i));
		}

		return fab;
	}
	
	public static Horario getBoardForIndividual(Individual<Profesor> individual) {
		Horario board = new Horario();
		List<Profesor> representation = individual.getRepresentation();

		for (int i = 0; i < representation.size(); i++) {
				Profesor p = representation.get(i);
				
				if (p.getLocatedAt() != -1){
					board.addProfesorAt(board.getCoordinate(p.getLocatedAt()), p);
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
			HashMap<String, List<Integer>> profes = new HashMap<String, List<Integer>>(); //para ver cada profe en qué posiciones está asignado
			
			for (int i = 0;  i < posiciones.size(); i++){ //recorre cada posición en la que hay profesores
		
				List<Integer> pref = new ArrayList<>();
				
				XYLocation pos = posiciones.get(i);
				Profesor p = board.getProfesorAt(pos); //coge al profesor de esa posición
				pref = p.getPreferencias();
				
				//compruebo si esta en una preferente
				
				int turno = board.getTurnoAt(pos);
				if (pref.contains(turno)) fitness += 1.0;
				
				//lo meto en el hash map
				if (!profes.containsKey(p)){
					List<Integer> x = new ArrayList<Integer>();
					x.add(p.getLocatedAt());
					profes.put(p.nombre, x);
				}
				else{
					profes.get(p.nombre).add(p.getLocatedAt());
				}
			}
			
			//tenemos en el hash map los profesores con una lista de las posiciones en las que estan
			//si el tamaño de esa lista supera turnosPorProfe restamos fitness
			
			for (HashMap.Entry<String, List<Integer>> entry : profes.entrySet()) {
				if (entry.getValue().size() > turnosPorProfe){
					fitness -= 1;
				}
			}
			
			return fitness > 0 ? fitness :0;
		
		}

		
	}

	public static class asignacion_de_turnosGoalTest implements GoalTest{
		
		@Override
		public boolean isGoalState(Object state) {
			Individual<Profesor> e = (Individual<Profesor>) state;
			Horario board = getBoardForIndividual(e);
			
			return goal == board.getTurnosYaAsignados() ? true : false;
		}

	}

}
