package Rubik;

import aima.core.agent.Action;
import aima.core.search.framework.ResultFunction;

public class RubikResultFunction implements ResultFunction{

	@Override
	public Object result(Object state, Action a) {
		EstadoCubo estado = (EstadoCubo) state;
		
		if(EstadoCubo.U.equals(a)) {
			EstadoCubo nuevosEstado = new EstadoCubo(estado);
			nuevosEstado.moveU();
			return nuevosEstado;		
		}
		if(EstadoCubo.F.equals(a)) {
			EstadoCubo nuevosEstado = new EstadoCubo(estado);
			nuevosEstado.moveF();
			return nuevosEstado;		
		}
		
		if(EstadoCubo.D.equals(a)) {
			EstadoCubo nuevosEstado = new EstadoCubo(estado);
			nuevosEstado.moveD();
			return nuevosEstado;		
		}
		
		if(EstadoCubo.L.equals(a)) {
			EstadoCubo nuevosEstado = new EstadoCubo(estado);
			nuevosEstado.moveL();
			return nuevosEstado;		
		}
		
		if(EstadoCubo.R.equals(a)) {
			EstadoCubo nuevosEstado = new EstadoCubo(estado);
			nuevosEstado.moveR();
			return nuevosEstado;		
		}
		
		if(EstadoCubo.B.equals(a)) {
			EstadoCubo nuevosEstado = new EstadoCubo(estado);
			nuevosEstado.moveB();
			return nuevosEstado;		
		}

		if(EstadoCubo.U_.equals(a)) {
			EstadoCubo nuevosEstado = new EstadoCubo(estado);
			nuevosEstado.moveU_();
			return nuevosEstado;		
		}
		if(EstadoCubo.F_.equals(a)) {
			EstadoCubo nuevosEstado = new EstadoCubo(estado);
			nuevosEstado.moveF_();
			return nuevosEstado;		
		}
		
		if(EstadoCubo.D_.equals(a)) {
			EstadoCubo nuevosEstado = new EstadoCubo(estado);
			nuevosEstado.moveD_();
			return nuevosEstado;		
		}
		
		if(EstadoCubo.L_.equals(a)) {
			EstadoCubo nuevosEstado = new EstadoCubo(estado);
			nuevosEstado.moveL_();
			return nuevosEstado;		
		}
		
		if(EstadoCubo.R_.equals(a)) {
			EstadoCubo nuevosEstado = new EstadoCubo(estado);
			nuevosEstado.moveR_();
			return nuevosEstado;		
		}
		
		if(EstadoCubo.B_.equals(a)) {
			EstadoCubo nuevosEstado = new EstadoCubo(estado);
			nuevosEstado.moveB_();
			return nuevosEstado;		
		}
		// The Action is not understood or is a NoOp
		// the result will be the current state.
		return state;
	}

}
