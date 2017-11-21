package Rubik;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;

public class RubikActionsFunction implements aima.core.search.framework.ActionsFunction {

	@Override
	public Set<Action> actions(Object arg0) {
		Set<Action> actions = new LinkedHashSet<Action>();
		actions.add(EstadoCubo.U);
		actions.add(EstadoCubo.F);
		actions.add(EstadoCubo.D);
		actions.add(EstadoCubo.L);
		actions.add(EstadoCubo.R);
		actions.add(EstadoCubo.B);
		actions.add(EstadoCubo.U_);
		actions.add(EstadoCubo.F_);
		actions.add(EstadoCubo.D_);
		actions.add(EstadoCubo.L_);
		actions.add(EstadoCubo.R_);
		actions.add(EstadoCubo.B_);

		return actions;
	}

}
