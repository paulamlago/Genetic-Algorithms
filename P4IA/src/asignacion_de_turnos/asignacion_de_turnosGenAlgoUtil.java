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
	
	public static Individual<Profesor> generateRandomIndividual(int boardSize, List<Profesor> p) {
		List<Profesor> individualRepresentation = new ArrayList<Profesor>();
		for (int i = 0; i < boardSize; i++) {
			Profesor profe = p.get(new Random().nextInt(p.size()));
			if (!profe.getRestricciones().contains(i + 1)){
				individualRepresentation.add(profe);
			}
			else individualRepresentation.add(new Profesor(""));
		}
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
				List<XYLocation> l = p.getLocatedAt();
				for (int j = 0; j < l.size(); j++){
					board.addProfesorAt(l.get(j), p);
				}
		}

		return board;
	}
	
	public static class asignacion_de_turnosFitnessFunction implements FitnessFunction<Profesor>{

		public double apply(Individual<Profesor> individual) {
			
			double fitness = 0.0;
			Horario board = getBoardForIndividual(individual);
			//número de profesores asignados en horarios de su preferencia y que no estén en sus restricciones
			
			List<XYLocation> posiciones = board.getProfesorPositions();
		
			for (int i = 0; i < posiciones.size(); i++){
				XYLocation pos = posiciones.get(i);
				Profesor p = board.getProfesorAt(pos);
				List<Integer> pref = p.getPreferencias();
				
				//compruebo si esá en una preferente
				
				for (int j = 0; j < pref.size(); j++){
					if (pref.get(j) == board.getTurnoAt(pos)){
						fitness += 1.0;
					}
				}
				
				//tengo que comprobar que no haya repeticiones, que sea un reparto homogéneo
				//necesito el número de turnos necesarios (objetivo) y el número de profesores
				
				if (p.getLocatedAt().size() > Math.ceil(goal / numeroDeProfesores)){
					fitness -= 2.0; //por ejemplo
				}
			}
			
			return fitness;
		}

		@Override
		public double getValue(Individual<Profesor> arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

	public static class asignacion_de_turnosGoalTest implements GoalTest{
		
		@Override
		public boolean isGoalState(Object state) {
			Individual<Profesor> e = (Individual<Profesor>) state;
			
			return goal == e.getRepresentation().size() ? true : false;
		}

	}

}
