package Rubik;

import aima.core.search.framework.GoalTest;

public class RubikGoalTest implements GoalTest{

	@Override
	public boolean isGoalState(Object state) {
		EstadoCubo goal = new EstadoCubo();
		EstadoCubo mycube = (EstadoCubo) state;
		if(mycube.equals(goal)) return true;
		else return false;
	}

}
