package misioneros_y_canibales.heurísticas;

import aima.core.search.framework.HeuristicFunction;
import misioneros_y_canibales.EstadoMisioneros;

public class DesplazadosHeuristic implements HeuristicFunction{

	@Override
	public double h(Object state) {
		EstadoMisioneros tablero = (EstadoMisioneros) state;
		
		return getDesplazados(tablero);
	}
	
	public int getDesplazados(EstadoMisioneros tablero){
		return tablero.getnumCanibales() + tablero.getnumMisioneros();
	}

}
