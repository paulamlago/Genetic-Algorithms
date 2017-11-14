<<<<<<< HEAD
package misioneros_y_canibales;
import aima.core.search.framework.GoalTest;

public class MisionerosGoalTest implements GoalTest{
	EstadoMisioneros goal = new EstadoMisioneros(0, 0, false);
	
	@Override
	public boolean isGoalState(Object state) {
		EstadoMisioneros estado = (EstadoMisioneros) state;
		return estado.equals(goal);
	}
}
=======
package misioneros_y_canibales;
import aima.core.search.framework.GoalTest;

public class MisionerosGoalTest implements GoalTest{
	EstadoMisioneros goal = new EstadoMisioneros(0, 0, false);
	
	@Override
	public boolean isGoalState(Object state) {
		EstadoMisioneros estado = (EstadoMisioneros) state;
		return estado.equals(goal);
	}
}
>>>>>>> c6472cd247846c5c0a8163acd23ed462d132476f
