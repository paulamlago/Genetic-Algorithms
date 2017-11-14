package misioneros_y_canibales.heurísticas;

import aima.core.search.framework.HeuristicFunction;
import misioneros_y_canibales.EstadoMisioneros;

public class CanibalesNoComenMisionerosHeuristic implements HeuristicFunction{

	@Override
	public double h(Object state) {
		EstadoMisioneros tablero = (EstadoMisioneros) state;	
		return 2*(tablero.getnumCanibales() + tablero.getnumMisioneros() - (tablero.isBarcaIzq() ? 1 : 0));
	}
}