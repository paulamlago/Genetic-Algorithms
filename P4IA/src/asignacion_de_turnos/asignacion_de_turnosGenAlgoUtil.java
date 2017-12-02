package asignacion_de_turnos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import aima.core.search.framework.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.Individual;
import aima.core.util.datastructure.XYLocation;

public class asignacion_de_turnosGenAlgoUtil{

	public static FitnessFunction<Integer> getFitnessFunction() {
		return new asignacion_de_turnosFitnessFunction();
	}
	
	public static GoalTest getGoalTest(){
		return new asignacion_de_turnosGoalTest();
	}
	
	public static Individual<Integer> generateRandomIndividual(int boardSize) {
		List<Integer> individualRepresentation = new ArrayList<Integer>();
		for (int i = 0; i < boardSize; i++) {
			individualRepresentation.add(new Random().nextInt(boardSize));
		}
		Individual<Integer> individual = new Individual<Integer>(individualRepresentation);
		return individual;
	}
	public static Set<Integer> getFiniteAlphabetForBoardOfSize(int size) {
		Set<Integer> fab = new HashSet<Integer>();

		for (int i = 0; i < size; i++) {
			fab.add(i);
		}

		return fab;
	}
	
	public static Horario getBoardForIndividual(Individual<Integer> individual) {
		int boardSize = individual.length();
		
		Horario board = new Horario(boardSize);
		
		for (int i = 0; i < boardSize; i++) {
			int pos = individual.getRepresentation().get(i);
			board.addProfesorAt(new XYLocation(i, pos), pos);
		}

		return board;
	}
	
	public static class asignacion_de_turnosFitnessFunction implements FitnessFunction<Integer>{

		public double apply(Individual<Integer> individual) {
			double fitness = 0;

			Horario board = getBoardForIndividual(individual);
			int boardSize = board.getSize();

			List<XYLocation> qPositions = board.getProfesorPositions();
			
			//..
			
			return fitness;
		}

		@Override
		public double getValue(Individual<Integer> arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}

	public static class asignacion_de_turnosGoalTest implements GoalTest{

		@Override
		public boolean isGoalState(Object arg0) {
			
			return false;
		}

	}

}
