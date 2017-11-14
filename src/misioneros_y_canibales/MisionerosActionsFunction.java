
package misioneros_y_canibales;
import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;

public class MisionerosActionsFunction implements aima.core.search.framework.ActionsFunction{

	@Override
	public Set<Action> actions(Object state) {
		EstadoMisioneros estado = (EstadoMisioneros) state;
		// lista de acciones posibles
		Set<Action> actions = new LinkedHashSet<Action>();
		// si se cumplen las precondiciones y no se va a un estado de peligro entonces
		// se añade la acción a la lista de acciones posibles
		if (estado.movimientoValido(EstadoMisioneros.M))
			actions.add(EstadoMisioneros.M);
		if (estado.movimientoValido(EstadoMisioneros.MM))
			actions.add(EstadoMisioneros.MM);
		if (estado.movimientoValido(EstadoMisioneros.C))
			actions.add(EstadoMisioneros.C);
		if (estado.movimientoValido(EstadoMisioneros.CC))
			actions.add(EstadoMisioneros.CC);
		if (estado.movimientoValido(EstadoMisioneros.MC))
			actions.add(EstadoMisioneros.MC);
		return actions;
	}

}
