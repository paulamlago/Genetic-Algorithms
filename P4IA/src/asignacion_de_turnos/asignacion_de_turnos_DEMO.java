package asignacion_de_turnos;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import aima.core.search.framework.GoalTest;
import aima.core.search.framework.Problem;
import aima.core.search.framework.SearchAgent;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;
import aima.core.search.uninformed.DepthFirstSearch;

public class asignacion_de_turnos_DEMO {
	
	
	public static void main(String[] args) {

		asignacion_de_turnos_Demo();
	}
	
	@SuppressWarnings("null")
	public static void asignacion_de_turnos_Demo() {
		Scanner scanner = new Scanner(System.in);
		Profesor profesores[] = null;
		String[] nombres, restricciones, preferencias;
		int j, turnos;
		String linea;
		int r[], p[];
		
		System.out.println("\n ¿Cuantos turnos hay?: ");
		
		turnos = scanner.nextInt();
		asignacion_de_turnosState state = new asignacion_de_turnosState(turnos);
		
		System.out.println("\n Listado de profesores: ");
		linea = scanner.nextLine();
		nombres = linea.split(", ");
		
		//leemos las reestricciones
		j = 0;
		while (j < nombres.length){
			r = null;
			linea = scanner.nextLine();
			restricciones = linea.split(": ");
			
			if (restricciones[1] != ""){
				restricciones = restricciones[1].split(", ");
				
				for(int i = 0; i < restricciones.length; i++){
					r[i] = Integer.valueOf(restricciones[i]);
					i++;
				}
				
			}
			
			profesores[j] = new Profesor(nombres[j]);
			profesores[j].setRestricciones(r);
			j++;
		}
		
		//leemos las preferencias
		j = 0;
		while (j < nombres.length){
			p = null;
			linea = scanner.nextLine();
			preferencias = linea.split(": ");
			
			if (preferencias[1] != ""){
				preferencias = preferencias[1].split(", ");
				
				for(int i = 0; i < preferencias.length; i++){
					p[i] = Integer.valueOf(preferencias[i]);
					i++;
				}
	
				profesores[j].setPreferencias(p);
			}
			j++;
		}
		
		asignacion_de_turnos_DEMO_GeneticAlgorithmSearch();
	}

	public static void asignacion_de_turnos_DEMO_GeneticAlgorithmSearch() {
		System.out.println("\nasignacion_de_turnos_DEMO GeneticAlgorithm  -->");
		asignacion_de_turnosState state = new asignacion_de_turnosState(4);
		
		try {
			FitnessFunction<Integer> fitnessFunction = asignacion_de_turnosGenAlgoUtil.getFitnessFunction();
			GoalTest goalTest = asignacion_de_turnosGenAlgoUtil.getGoalTest();
			// Generate an initial population
			Set<Individual<Integer>> population = new HashSet<Individual<Integer>>();
			for (int i = 0; i < 50; i++) {
				population.add(asignacion_de_turnosGenAlgoUtil.generateRandomIndividual(state.getHorario().getSize()));
			}

			GeneticAlgorithm<Integer> ga = new GeneticAlgorithm<Integer>(state.getNumTurnos(),
					asignacion_de_turnosGenAlgoUtil.getFiniteAlphabetForBoardOfSize(state.getHorario().getSize()), 0.15);

			// Run for a set amount of time
			Individual<Integer> bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 1000L);

			System.out.println("Max Time (1 second) Best Individual=\n"
					+ asignacion_de_turnosGenAlgoUtil.getBoardForIndividual(bestIndividual));
			System.out.println("Board Size      = " + state.getHorario().getSize());
			System.out.println("# Board Layouts = " + (new BigDecimal(state.getHorario().getSize())).pow(state.getHorario().getSize()));
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");

			// Run till goal is achieved
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 0L);

			System.out.println("");
			System.out
					.println("Goal Test Best Individual=\n" + asignacion_de_turnosGenAlgoUtil.getBoardForIndividual(bestIndividual));
			System.out.println("Board Size      = " + state.getHorario().getSize());
			System.out.println("# Board Layouts = " + (new BigDecimal(state.getHorario().getSize())).pow(state.getHorario().getSize()));
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
