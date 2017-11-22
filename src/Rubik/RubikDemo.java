package Rubik;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.QueueSearch;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;

public class RubikDemo {
	static EstadoCubo estadoInicial;

	public static void main(String[] args) {
		RubikBFSDemo();
	}

	private static void RubikBFSDemo() {

		System.out.println("\nRubikBFSDemo-->");
		System.out.println("Enter number of movements to disassemble the cube: ");
		Scanner scanner = new Scanner(System.in);
		int moveNum = scanner.nextInt();
		System.out.println("Enter the " + moveNum + " movements separated by a blank space:");
		scanner.nextLine();
		
		Set<Action> moves = new LinkedHashSet<Action>();
		String in =	scanner.nextLine();
		String[] inputs = in.split(" ");
		for(int i = 0; i < moveNum; i++) {
			moves.add((Action)new DynamicAction(inputs[i]));
		}
		estadoInicial = new EstadoCubo(moves);
		try {
			// Crear un objeto Problem con la representación de estados y operadores


				Problem problem = new Problem(estadoInicial, RubikFunctionFactory.getActionsFunction(),	RubikFunctionFactory.getResultFunction(), new RubikGoalTest());

			new RubikStepCostFunction();
			
			// indicar el tipo de búsqueda
			QueueSearch gs = new GraphSearch();
			Search search = new DepthFirstSearch(gs);
			
			// crear un agente que realice la búsqueda sobre el problema
			SearchAgent agent = new SearchAgent(problem, search);
			
			// escribir la solución encontrada (operadores aplicados) e información sobre los recursos utilizados
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}

	}

	private static void printActions(List<Action> actions) {
		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			System.out.println(action);
		}
	}

}
