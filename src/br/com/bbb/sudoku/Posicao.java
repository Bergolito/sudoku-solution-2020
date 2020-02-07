package br.com.bbb.sudoku;

public class Posicao {

	// atributos
	private int x;
	private int y;
	private int valor;

	// construtor
	public Posicao(int x, int y, int v) {
		this.x = x;
		this.y = y;
		this.valor = v;
	}
	
	// métodos
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getValor() {
		return valor;
	}
}
