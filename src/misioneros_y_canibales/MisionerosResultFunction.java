<<<<<<< HEAD
package misioneros_y_canibales;
import aima.core.agent.Action;
import aima.core.search.framework.ResultFunction;

public class MisionerosResultFunction implements ResultFunction{

	@Override
	public Object result(Object s, Action a) {
		EstadoMisioneros estado = (EstadoMisioneros) s;
		if (EstadoMisioneros.M.equals(a)) {
			EstadoMisioneros nuevoEstado = new EstadoMisioneros(estado);
			nuevoEstado.moveM();
			return nuevoEstado;
		}
		else if(EstadoMisioneros.MM.equals(a)) {
			EstadoMisioneros nuevoEstado = new EstadoMisioneros(estado);
			nuevoEstado.moveMM();
			return nuevoEstado;
		}
		else if(EstadoMisioneros.C.equals(a)) {
			EstadoMisioneros nuevoEstado = new EstadoMisioneros(estado);
			nuevoEstado.moveC();
			return nuevoEstado;
		}
		else if(EstadoMisioneros.CC.equals(a)) {
			EstadoMisioneros nuevoEstado = new EstadoMisioneros(estado);
			nuevoEstado.moveCC();
			return nuevoEstado;
		}
		else if(EstadoMisioneros.MC.equals(a)) {
			EstadoMisioneros nuevoEstado = new EstadoMisioneros(estado);
			nuevoEstado.moveMC();
			return nuevoEstado;
		}
		// The Action is not understood or is a NoOp
		// the result will be the current state.
		return s;
	}

}
=======
package misioneros_y_canibales;
import aima.core.agent.Action;
import aima.core.search.framework.ResultFunction;

public class MisionerosResultFunction implements ResultFunction{

	@Override
	public Object result(Object s, Action a) {
		EstadoMisioneros estado = (EstadoMisioneros) s;
		if (EstadoMisioneros.M.equals(a)) {
			EstadoMisioneros nuevoEstado = new EstadoMisioneros(estado);
			nuevoEstado.moveM();
			return nuevoEstado;
		}
		else if(EstadoMisioneros.MM.equals(a)) {
			EstadoMisioneros nuevoEstado = new EstadoMisioneros(estado);
			nuevoEstado.moveMM();
			return nuevoEstado;
		}
		else if(EstadoMisioneros.C.equals(a)) {
			EstadoMisioneros nuevoEstado = new EstadoMisioneros(estado);
			nuevoEstado.moveC();
			return nuevoEstado;
		}
		else if(EstadoMisioneros.CC.equals(a)) {
			EstadoMisioneros nuevoEstado = new EstadoMisioneros(estado);
			nuevoEstado.moveCC();
			return nuevoEstado;
		}
		else if(EstadoMisioneros.MC.equals(a)) {
			EstadoMisioneros nuevoEstado = new EstadoMisioneros(estado);
			nuevoEstado.moveMC();
			return nuevoEstado;
		}
		// The Action is not understood or is a NoOp
		// the result will be the current state.
		return s;
	}

}
>>>>>>> c6472cd247846c5c0a8163acd23ed462d132476f
