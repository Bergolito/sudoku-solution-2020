package br.com.bbb.sudoku;


public class Posicao {

	// atributos
	private int x;
	private int y;
	private int valor;

	// construtores
	public Posicao() {
		super();
	}

	public Posicao(int x, int y, int v) {
		super();
		this.x = x;
		this.y = y;
		this.valor = v;
	}
	
	// métodos
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}

}
