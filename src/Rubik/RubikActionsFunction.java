package Rubik;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;

public class RubikActionsFunction implements aima.core.search.framework.ActionsFunction {

	@Override
	public Set<Action> actions(Object state) {
		EstadoCubo estado = (EstadoCubo) state;

		Set<Action> actions = new LinkedHashSet<Action>();

		if (estado.movimientoValido(EstadoCubo.U)) {
			actions.add(EstadoCubo.U);
		}

		if (estado.movimientoValido(EstadoCubo.F)) {

			actions.add(EstadoCubo.F);
		}

		if (estado.movimientoValido(EstadoCubo.D)) {

			actions.add(EstadoCubo.D);
		}
		if (estado.movimientoValido(EstadoCubo.L)) {

			actions.add(EstadoCubo.L);
		}
		if (estado.movimientoValido(EstadoCubo.R)) {

			actions.add(EstadoCubo.R);
		}
		if (estado.movimientoValido(EstadoCubo.B)) {

			actions.add(EstadoCubo.B);
		}
		if (estado.movimientoValido(EstadoCubo.U_)) {

			actions.add(EstadoCubo.U_);
		}
		if (estado.movimientoValido(EstadoCubo.F_)) {

			actions.add(EstadoCubo.F_);
		}
		if (estado.movimientoValido(EstadoCubo.D_)) {

			actions.add(EstadoCubo.D_);
		}
		if (estado.movimientoValido(EstadoCubo.L_)) {

			actions.add(EstadoCubo.L_);
		}
		if (estado.movimientoValido(EstadoCubo.R_)) {

			actions.add(EstadoCubo.R_);
		}
		if (estado.movimientoValido(EstadoCubo.B_)) {

			actions.add(EstadoCubo.B_);
		}
		return actions;
	}

}
