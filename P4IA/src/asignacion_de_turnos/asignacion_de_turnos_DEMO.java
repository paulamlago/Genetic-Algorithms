package asignacion_de_turnos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import aima.core.search.framework.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;


public class asignacion_de_turnos_DEMO {
	
	private static List<Profesor> profesores;
	private final static int boardSize = 4;
	private static int turnosNecesarios;
	
	public static void main(String[] args) {

		asignacion_de_turnos_Demo();
	}
	

	public static void asignacion_de_turnos_Demo() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String[] nombres, restricciones, preferencias;
		int j;
		String linea;
		
		profesores = new ArrayList<Profesor>();

		turnosNecesarios = Integer.parseInt(scanner.nextLine());
		
		linea = scanner.nextLine();
		nombres = linea.split(", ");
		
		//leemos las reestricciones
		j = 0;
		while (j < nombres.length){
			List<Integer> r = new ArrayList<Integer>();
			
			linea = scanner.nextLine();
			restricciones = linea.split(":");
			
			if (restricciones.length > 1){ //hay restricciones
				restricciones = restricciones[1].split(",");
				
				restricciones[0] = restricciones[0].substring(1); //para quitarle el espacio inicial
				
				for(int i = 0; i < restricciones.length; i++){
					r.add(Integer.valueOf(restricciones[i]));
				}
				
			}
			
			Profesor e = new Profesor(nombres[j]);
			e.setRestricciones(r);
			profesores.add(e);
			
			j++;
		}
		
		Iterator<Profesor> it = profesores.iterator();
		
		//leemos las preferencias
		j = 0;
		while (j < nombres.length && it.hasNext()){
			List<Integer> p = new ArrayList<Integer>();
			
			linea = scanner.nextLine();
			preferencias = linea.split(":");
			
			if (preferencias.length > 1){
				preferencias = preferencias[1].split(",");
				
				preferencias[0] = preferencias[0].substring(1); //para quitarle el espacio inicial
				
				for(int i = 0; i < preferencias.length; i++){
					p.add(Integer.valueOf(preferencias[i]));
				}
				
				Profesor e = it.next();
				e.setPreferencias(p);
			}
			
			j++;
		}
		
		asignacion_de_turnos_DEMO_GeneticAlgorithmSearch();
	}

	public static void asignacion_de_turnos_DEMO_GeneticAlgorithmSearch() {
		System.out.println("\n asignacion_de_turnos_DEMO GeneticAlgorithm  -->");
		
		try {
			FitnessFunction<Profesor> fitnessFunction = asignacion_de_turnosGenAlgoUtil.getFitnessFunction();
			GoalTest goalTest = asignacion_de_turnosGenAlgoUtil.getGoalTest(turnosNecesarios, profesores.size());
			// Generate an initial population
			Set<Individual<Profesor>> population = new HashSet<Individual<Profesor>>();
			for (int i = 0; i < 50; i++) {
				population.add(asignacion_de_turnosGenAlgoUtil.generateRandomIndividual(boardSize, profesores));
			}

			GeneticAlgorithm<Profesor> ga = new GeneticAlgorithm<Profesor>(boardSize,
					(Set<Profesor>) asignacion_de_turnosGenAlgoUtil.getFiniteAlphabetForBoardOfSize(boardSize, profesores), 0.15);

			// Run for a set amount of time
			Individual<Profesor> bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 1000L);

			System.out.println("Max Time (1 second) Best Individual=\n"
					+ asignacion_de_turnosGenAlgoUtil.getBoardForIndividual(bestIndividual));
			System.out.println("Board Size      = " + boardSize);
			System.out.println("# Board Layouts = " + (new BigDecimal(boardSize).pow(boardSize)));
			System.out.println("Fitness         = " + fitnessFunction.getValue(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");

			// Run till goal is achieved
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 0L);

			System.out.println("");
			System.out
					.println("Goal Test Best Individual=\n" + asignacion_de_turnosGenAlgoUtil.getBoardForIndividual(bestIndividual));
			System.out.println("Board Size      = " + boardSize);
			System.out.println("# Board Layouts = " + (new BigDecimal(boardSize).pow(boardSize)));
			System.out.println("Fitness         = " + fitnessFunction.getValue(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
