package asignacion_de_turnos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import aima.core.search.framework.problem.GoalTest;
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
		System.out.println("Please, introduce the sample: \n");
		turnosNecesarios = Integer.parseInt(scanner.nextLine());

		linea = scanner.nextLine();
		nombres = linea.split(", ");

		// leemos las reestricciones
		j = 0;
		while (j < nombres.length) {
			List<Integer> r = new ArrayList<Integer>();

			linea = scanner.nextLine();
			restricciones = linea.split(":");

			if (restricciones.length > 1) { // hay restricciones
				restricciones = restricciones[1].split(",");

				restricciones[0] = restricciones[0].substring(1); // para quitarle el espacio inicial

				for (int i = 0; i < restricciones.length; i++) {

					if (!restricciones[i].equals(""))
						r.add(Integer.valueOf(restricciones[i].replace(" ", "")));
				}

			}

			Profesor e = new Profesor(nombres[j]);
			e.setRestricciones(r);
			profesores.add(e);

			j++;
		}

		Iterator<Profesor> it = profesores.iterator();

		// leemos las preferencias
		j = 0;
		while (j < nombres.length && it.hasNext()) {
			List<Integer> p = new ArrayList<Integer>();

			linea = scanner.nextLine();
			preferencias = linea.split(":");

			if (preferencias.length > 1) {
				preferencias = preferencias[1].split(",");

				preferencias[0] = preferencias[0].substring(1); // para quitarle el espacio inicial

				for (int i = 0; i < preferencias.length; i++) {

					if (!preferencias[i].equals(""))
						p.add(Integer.valueOf(preferencias[i].replace(" ", "")));
				}

				Profesor e = it.next();
				e.setPreferencias(p);
			} else
				it.next();

			j++;
		}
		boolean exit = false; 
		do {
		System.out.println("\nPlease, choose an option:\n"
				+ "1.Print a 100 trials of best individual search with statistics.\n"
				+ "2.Print 3 trials of genetic algorithm search with different crossover probabilities\n"
				+ "3.Print a trial of genetic algorithm search when obtaining 2 children from the crossover\n"
				+ "4.Print a trial of genetic algorithm search with destructive vs.non destructive strategy\n ");
		int num = scanner.nextInt();
		scanner.nextLine();

		switch (num) {
		case 1:
			asignacion_de_turnos_DEMO_GeneticAlgorithmSearch100Sample();
			break;
		case 2:
			System.out.println("Enter the 3 probabilities separated by a blank space");
			String in = scanner.nextLine();
			String[] inputs = in.split(" ");
			
			for (int i = 0; i < 3; i++)
				asignacion_de_turnos_DEMO_GeneticAlgorithmSearchDoubleSample(Double.parseDouble(inputs[i]), false, false);
			break;
		case 3:
			asignacion_de_turnos_DEMO_GeneticAlgorithmSearchDoubleSample(0.9, true, false);
			break;
		case 4:
			asignacion_de_turnos_DEMO_GeneticAlgorithmSearchDoubleSample(0.9, true, true);
			break;
		default:
			exit = true;
			break;
		}
		}while (!exit);
	}

	public static void asignacion_de_turnos_DEMO_GeneticAlgorithmSearch100Sample() {
		System.out.println("\n asignacion_de_turnos_DEMO GeneticAlgorithm  -->");

		try {
			double fitnessTotal = 0, fitnessMinima = 0, fitnessMaxima = 0, tiempoMinimo = 0, tiempoMaximo = 0,
					tiempoTotal = 0;
			int iteracionesTotal = 0, itMax = 0, itMin = 0;

			for (int j = 0; j < 50; j++) { // para las estadísticas de la memoria
				FitnessFunction<Profesor> fitnessFunction = asignacion_de_turnosGenAlgoUtil.getFitnessFunction();
				GoalTest goalTest = asignacion_de_turnosGenAlgoUtil.getGoalTest(turnosNecesarios, profesores.size());
				// Generate an initial population
				Set<Individual<Profesor>> population = new HashSet<Individual<Profesor>>();

				for (int i = 0; i < 50; i++) {
					Individual<Profesor> p = asignacion_de_turnosGenAlgoUtil.generateRandomIndividual(boardSize,
							profesores);

					population.add(p);
				}

				GeneticAlgorithm<Profesor> ga = new GeneticAlgorithm<Profesor>(turnosNecesarios,
						asignacion_de_turnosGenAlgoUtil.getFiniteAlphabetForBoardOfSize(profesores), 0.15);

				Individual<Profesor> bestIndividual;

				//run for 5 seconds
				bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 5000L);

				double fitness = fitnessFunction.apply(bestIndividual);
				double tiempo = ga.getTimeInMilliseconds();
				int iteraciones = ga.getIterations();

				System.out.println(j + " \n");
				System.out.println("Goal Test (5 seconds) Best Individual=\n"
						+ asignacion_de_turnosGenAlgoUtil.getBoardForIndividual(bestIndividual));
				System.out.println("Board Size      = " + boardSize);
				System.out.println("# Board Layouts = " + (new BigDecimal(boardSize).pow(boardSize)));
				System.out.println("Fitness         = " + fitness);
				System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
				System.out.println("Population Size = " + ga.getPopulationSize());
				System.out.println("Itertions       = " + iteraciones);
				System.out.println("Took            = " + tiempo + "ms.");

				fitnessTotal += fitness;
				if (j == 0) {
					fitnessMinima = fitness;
					tiempoMinimo = tiempo;
					itMin = iteraciones;
				}

				if (fitness > fitnessMaxima) {
					fitnessMaxima = fitness;
				} else if (fitness < fitnessMinima) {
					fitnessMinima = fitness;
				}

				tiempoTotal += tiempo;
				if (tiempo < tiempoMinimo) {
					tiempoMinimo = tiempo;
				} else if (tiempo > tiempoMaximo) {
					tiempoMaximo = tiempo;
				}

				iteracionesTotal += iteraciones;
				if (iteraciones > itMax) {
					itMax = iteraciones;
				} else if (iteraciones < itMin) {
					itMin = iteraciones;
				}
			}

			System.out.println("-----ESTADISTICAS-----");
			System.out.println("\n Fitness media: " + fitnessTotal / 50 + "Fitness máxima: " + fitnessMaxima
					+ "\n Fitness minima: " + fitnessMinima);
			System.out.println("\nTiempo medio: " + tiempoTotal / 50 + "\n Tiempo máximo: " + tiempoMaximo
					+ "\n Tiempo minimo: " + tiempoMinimo);
			System.out.println(
					"\nIteraciones media: " + iteracionesTotal / 50 + "\n It max: " + itMax + "\n It min: " + itMin);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void asignacion_de_turnos_DEMO_GeneticAlgorithmSearchDoubleSample(double crossoverProbability, boolean children, boolean destructive) {
		System.out.println("\n asignacion_de_turnos_DEMO GeneticAlgorithm  -->");

		try {
			FitnessFunction<Profesor> fitnessFunction = asignacion_de_turnosGenAlgoUtil.getFitnessFunction();
			GoalTest goalTest = asignacion_de_turnosGenAlgoUtil.getGoalTest(turnosNecesarios, profesores.size());
			// Generate an initial population
			Set<Individual<Profesor>> population = new HashSet<Individual<Profesor>>();

			for (int i = 0; i < 100; i++) {
				Individual<Profesor> p = asignacion_de_turnosGenAlgoUtil.generateRandomIndividual(boardSize,
						profesores);

				population.add(p);
			}

			MyGeneticAlgorithm<Profesor> ga = new MyGeneticAlgorithm<Profesor>(turnosNecesarios,
					asignacion_de_turnosGenAlgoUtil.getFiniteAlphabetForBoardOfSize(profesores), 0.15, crossoverProbability, children, destructive);

			// Run for a set amount of time
			Individual<Profesor> bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 1000L);

			System.out.println("Max Time (1 second) Best Individual=\n"
					+ asignacion_de_turnosGenAlgoUtil.getBoardForIndividual(bestIndividual));
			System.out.println("Board Size      = " + boardSize);
			System.out.println("# Board Layouts = " + (new BigDecimal(boardSize).pow(boardSize)));
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");

			// Run till goal is achieved
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 5000L);

			System.out.println("");
			System.out.println("Goal Test (5 seconds) Best Individual=\n"
					+ asignacion_de_turnosGenAlgoUtil.getBoardForIndividual(bestIndividual));
			System.out.println("Board Size      = " + boardSize);
			System.out.println("# Board Layouts = " + (new BigDecimal(boardSize).pow(boardSize)));
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
