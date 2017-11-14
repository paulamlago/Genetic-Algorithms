package misioneros_y_canibales;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;

public class EstadoMisioneros {
	private int nMisioneros; // numMisioneros en orilla izquierda
	private int nCanibales; // numCanibales en orilla izquierda
	private boolean barcaIzq; // true si barca en orilla izquierda
	
	public static Action M = new DynamicAction("M");
	public static Action MM = new DynamicAction("MM");
	public static Action C = new DynamicAction("C");
	public static Action CC = new DynamicAction("CC");
	public static Action MC = new DynamicAction("MC");

	public EstadoMisioneros() {
		this(3, 3, true);
	}
	public EstadoMisioneros(EstadoMisioneros mc) {
		this(mc.nMisioneros, mc.nCanibales, mc.barcaIzq);
	}
	
	public EstadoMisioneros(int nMisioneros, int nCanibales, boolean barcaIzq) {
		this.nMisioneros = nMisioneros;
		this.nCanibales = nCanibales;
		this.barcaIzq = barcaIzq;
	}
	
	public boolean isBarcaIzq() {
		return barcaIzq;
	}
	public int getnumCanibales() {
		return nCanibales;
	}
	public int getnumMisioneros() {
		return nMisioneros;
	}
	
	//OPERADORES:
	public void moveM() {
		if (barcaIzq) nMisioneros--;
		else nMisioneros++;
		cambiarDeOrilla();
	}
	
	public void moveMM() {
		if (barcaIzq) nMisioneros -= 2;
		else nMisioneros += 2;
		cambiarDeOrilla();
	}
	
	public void moveC() {
		if (barcaIzq) nCanibales--;
		else nCanibales++;
			cambiarDeOrilla();
	}
	
	public void moveCC() {
		if (barcaIzq) nCanibales -= 2;
		else nCanibales += 2;
		cambiarDeOrilla();
	}
	
	public void moveMC() {
		if (barcaIzq) {
			nCanibales --;
			nMisioneros --;
		}
		else {
			nCanibales ++;
			nMisioneros ++;
		}
		
		cambiarDeOrilla();
	}
	
	private void cambiarDeOrilla() {
		barcaIzq = !barcaIzq;
	}
	
	public boolean movimientoValido(Action where) {
		boolean valido = false;
		if (where.equals(M)) {
			if (((barcaIzq && nMisioneros > 0) || (!barcaIzq && nMisioneros < 3))) {
				EstadoMisioneros estadoSiguiente = new EstadoMisioneros(this);
				estadoSiguiente.moveM();
				valido = !estadoSiguiente.peligro();
			}
			else valido = false;
		}
		else if (where.equals(MM)) {
			if (((barcaIzq && nMisioneros > 1) || (!barcaIzq && nMisioneros < 2))) {
				EstadoMisioneros estadoSiguiente = new EstadoMisioneros(this);
				estadoSiguiente.moveMM();
				valido = !estadoSiguiente.peligro();
			}
			else valido = false;
		}
		else if (where.equals(C)) {
			if (((barcaIzq && nCanibales > 0) || (!barcaIzq && nCanibales < 3))) {
				EstadoMisioneros estadoSiguiente = new EstadoMisioneros(this);
				estadoSiguiente.moveC();
				valido = !estadoSiguiente.peligro();
			}
			else valido = false;
		}
		
		else if (where.equals(CC)) {
			if (((barcaIzq && nCanibales > 1) || (!barcaIzq && nCanibales < 2))) {
				EstadoMisioneros estadoSiguiente = new EstadoMisioneros(this);
				estadoSiguiente.moveCC();
				valido = !estadoSiguiente.peligro();
			}
			else valido = false;
		}
		else { //where.equals(MC)
			if (((barcaIzq && nCanibales > 0 && nMisioneros > 0) || (!barcaIzq && nCanibales < 3 && nMisioneros < 3))) {
				EstadoMisioneros estadoSiguiente = new EstadoMisioneros(this);
				estadoSiguiente.moveMC();
				valido = !estadoSiguiente.peligro();
			}
			else valido = false;
		}
		
		return valido;
	}
	
	private boolean peligro() {
		if (((nMisioneros < nCanibales) && (nMisioneros != 0)) || ((nMisioneros > nCanibales) && (nMisioneros != 3)))
			return true;
		else
			return false;
	}
	
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		}
		EstadoMisioneros otroEstado = (EstadoMisioneros) o;
		if ((this.nMisioneros == otroEstado.getnumMisioneros()) && (this.nCanibales == otroEstado.getnumCanibales()) && (this.barcaIzq == otroEstado.isBarcaIzq()))
			return true;
		else
			return false;
	}
	
	public int hashCode() {
		return (100 * nMisioneros) + (10 * nCanibales) + (barcaIzq ? 1 : 0);
	}
	
}