package misioneros_y_canibales.demos;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.QueueSearch;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.UniformCostSearch;
import misioneros_y_canibales.EstadoMisioneros;
import misioneros_y_canibales.MisionerosFunctionFactory;
import misioneros_y_canibales.MisionerosGoalTest;

public class DemoBusquedasNoInformadas {

	static EstadoMisioneros estadoInicial = new EstadoMisioneros();
	
	public DemoBusquedasNoInformadas() {
	}

	public void MisionerosDFSDemo(){
		System.out.println("\nMisionerosDFSDemo-->");
		try{
		// Crear un objeto Problem con la representaci�n de estados y operadores
		Problem problem = new Problem(estadoInicial,
		MisionerosFunctionFactory.getActionsFunction(), MisionerosFunctionFactory.getResultFunction(),
		new MisionerosGoalTest()); // si no hay funci�n de coste en el constructor se usa el coste por defecto
		// si hay funci�n de coste hay que a�adir el objeto correspondiente: new MisionerosStepCostFunction()
		// indicar el tipo de b�squeda
		QueueSearch gs = new GraphSearch();
		Search search = new DepthFirstSearch(gs); // b�squeda en anchura
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
	
	public void MisionerosBFSDemo(){
		System.out.println("\nMisionerosBFSDemo-->");
		try{
		// Crear un objeto Problem con la representaci�n de estados y operadores
		Problem problem = new Problem(estadoInicial,
		MisionerosFunctionFactory.getActionsFunction(), MisionerosFunctionFactory.getResultFunction(),
		new MisionerosGoalTest()); // si no hay funci�n de coste en el constructor se usa el coste por defecto
		// si hay funci�n de coste hay que a�adir el objeto correspondiente: new MisionerosStepCostFunction()
		// indicar el tipo de b�squeda
		
		Search search = new BreadthFirstSearch(); // b�squeda en anchura
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
	
	public void MisionerosUCSDemo(){
		System.out.println("\nMisionerosUCSDemo-->");
		try{
		// Crear un objeto Problem con la representaci�n de estados y operadores
		Problem problem = new Problem(estadoInicial,
		MisionerosFunctionFactory.getActionsFunction(), MisionerosFunctionFactory.getResultFunction(),
		new MisionerosGoalTest()); // si no hay funci�n de coste en el constructor se usa el coste por defecto
		// si hay funci�n de coste hay que a�adir el objeto correspondiente: new MisionerosStepCostFunction()
		// indicar el tipo de b�squeda
		
		Search search = new UniformCostSearch(); // b�squeda en anchura
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
