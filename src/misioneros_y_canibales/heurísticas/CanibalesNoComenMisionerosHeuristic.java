<<<<<<< HEAD
package misioneros_y_canibales.heurísticas;

import aima.core.search.framework.HeuristicFunction;
import misioneros_y_canibales.EstadoMisioneros;

public class CanibalesNoComenMisionerosHeuristic implements HeuristicFunction{

	@Override
	public double h(Object state) {
		EstadoMisioneros tablero = (EstadoMisioneros) state;	
		return 2*(tablero.getnumCanibales() + tablero.getnumMisioneros() - (tablero.isBarcaIzq() ? 1 : 0));
	}
=======
package misioneros_y_canibales.heurísticas;

import aima.core.search.framework.HeuristicFunction;
import misioneros_y_canibales.EstadoMisioneros;

public class CanibalesNoComenMisionerosHeuristic implements HeuristicFunction{

	@Override
	public double h(Object state) {
		EstadoMisioneros tablero = (EstadoMisioneros) state;	
		return 2*(tablero.getnumCanibales() + tablero.getnumMisioneros() - (tablero.isBarcaIzq() ? 1 : 0));
	}
>>>>>>> c6472cd247846c5c0a8163acd23ed462d132476f
}