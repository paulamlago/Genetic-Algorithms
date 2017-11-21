
package misioneros_y_canibales.demos;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.Problem;
import aima.core.search.framework.QueueSearch;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import misioneros_y_canibales.EstadoMisioneros;
import misioneros_y_canibales.MisionerosFunctionFactory;
import misioneros_y_canibales.MisionerosGoalTest;
import misioneros_y_canibales.heurísticas.CanibalesNoComenMisionerosHeuristic;
import misioneros_y_canibales.heurísticas.DesplazadosHeuristic;

public class DemoBusquedasInformadas {

	static EstadoMisioneros estadoInicial = new EstadoMisioneros();
	
	public DemoBusquedasInformadas() {
		// TODO Auto-generated constructor stub
	}

	public void MisionerosASDesplazadosDemo(){
		System.out.println("\nMisionerosA* DesplazadosHeuristic Demo-->");
		try{
		// Crear un objeto Problem con la representaci�n de estados y operadores
		Problem problem = new Problem(estadoInicial,
		MisionerosFunctionFactory.getActionsFunction(), MisionerosFunctionFactory.getResultFunction(),
		new MisionerosGoalTest()); // si no hay funci�n de coste en el constructor se usa el coste por defecto
		// si hay funci�n de coste hay que a�adir el objeto correspondiente: new MisionerosStepCostFunction()
		// indicar el tipo de b�squeda
		QueueSearch gs = new GraphSearch();
		HeuristicFunction hf = new DesplazadosHeuristic();
		
		Search search = new AStarSearch(gs, hf); // b�squeda en anchura
		// crear un agente que realice la b�squeda sobre el problema
		SearchAgent agent = new SearchAgent(problem, search);
		// escribir la soluci�n encontrada (operadores aplicados) e informaci�n sobre los recursos utilizados
		printActions(agent.getActions());
		printInstrumentation(agent.getInstrumentation());
		// Hay que implementar estos m�todos en la clase Demo (copiar de aima.gui.demo.search.EightPuzzleDemo)
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
		
	public void MisionerosASCNCMDemo(){
		System.out.println("\nMisionerosA* DesplazadosHeuristic Demo-->");
		try{
		// Crear un objeto Problem con la representaci�n de estados y operadores
		Problem problem = new Problem(estadoInicial,
		MisionerosFunctionFactory.getActionsFunction(), MisionerosFunctionFactory.getResultFunction(),
		new MisionerosGoalTest()); // si no hay funci�n de coste en el constructor se usa el coste por defecto
		// si hay funci�n de coste hay que a�adir el objeto correspondiente: new MisionerosStepCostFunction()
		// indicar el tipo de b�squeda
		QueueSearch gs = new GraphSearch();
		HeuristicFunction hf = new CanibalesNoComenMisionerosHeuristic();
		
		Search search = new AStarSearch(gs, hf); // b�squeda en anchura
		// crear un agente que realice la b�squeda sobre el problema
		SearchAgent agent = new SearchAgent(problem, search);
		// escribir la soluci�n encontrada (operadores aplicados) e informaci�n sobre los recursos utilizados
		printActions(agent.getActions());
		printInstrumentation(agent.getInstrumentation());
		// Hay que implementar estos m�todos en la clase Demo (copiar de aima.gui.demo.search.EightPuzzleDemo)
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void MisionerosVorazDesplazadosDemo(){
		System.out.println("\nMisioneros Voraz DesplazadosHeuristic Demo-->");
		try{
		// Crear un objeto Problem con la representaci�n de estados y operadores
		Problem problem = new Problem(estadoInicial,
		MisionerosFunctionFactory.getActionsFunction(), MisionerosFunctionFactory.getResultFunction(),
		new MisionerosGoalTest()); // si no hay funci�n de coste en el constructor se usa el coste por defecto
		// si hay funci�n de coste hay que a�adir el objeto correspondiente: new MisionerosStepCostFunction()
		// indicar el tipo de b�squeda
		QueueSearch gs = new GraphSearch();
		HeuristicFunction hf = new DesplazadosHeuristic();
		
		Search search = new GreedyBestFirstSearch(gs, hf); // b�squeda en anchura
		// crear un agente que realice la b�squeda sobre el problema
		SearchAgent agent = new SearchAgent(problem, search);
		// escribir la soluci�n encontrada (operadores aplicados) e informaci�n sobre los recursos utilizados
		printActions(agent.getActions());
		printInstrumentation(agent.getInstrumentation());
		// Hay que implementar estos m�todos en la clase Demo (copiar de aima.gui.demo.search.EightPuzzleDemo)
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void MisionerosVorazCNCMDemo(){
		System.out.println("\nMisioneros Voraz CNCM Heuristic Demo-->");
		try{
		// Crear un objeto Problem con la representaci�n de estados y operadores
		Problem problem = new Problem(estadoInicial,
		MisionerosFunctionFactory.getActionsFunction(), MisionerosFunctionFactory.getResultFunction(),
		new MisionerosGoalTest()); // si no hay funci�n de coste en el constructor se usa el coste por defecto
		// si hay funci�n de coste hay que a�adir el objeto correspondiente: new MisionerosStepCostFunction()
		// indicar el tipo de b�squeda
		QueueSearch gs = new GraphSearch();
		HeuristicFunction hf = new CanibalesNoComenMisionerosHeuristic();
		
		Search search = new GreedyBestFirstSearch(gs, hf); // b�squeda en anchura
		// crear un agente que realice la b�squeda sobre el problema
		SearchAgent agent = new SearchAgent(problem, search);
		// escribir la soluci�n encontrada (operadores aplicados) e informaci�n sobre los recursos utilizados
		printActions(agent.getActions());
		printInstrumentation(agent.getInstrumentation());
		// Hay que implementar estos m�todos en la clase Demo (copiar de aima.gui.demo.search.EightPuzzleDemo)
		}
		catch (Exception e){
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
