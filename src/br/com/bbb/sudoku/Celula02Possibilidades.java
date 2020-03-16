package br.com.bbb.sudoku;

public class Celula02Possibilidades {

	// atributos
	private int x;
	private int y;
	private int possibilidade1;
	private int possibilidade2;

	// construtor
	public Celula02Possibilidades(int x, int y, int possib1, int possib2) {
		this.x = x;
		this.y = y;
		this.possibilidade1 = possib1;
		this.possibilidade2 = possib2;
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

	public int getPossibilidade1() {
		return possibilidade1;
	}

	public void setPossibilidade1(int possibilidade1) {
		this.possibilidade1 = possibilidade1;
	}

	public int getPossibilidade2() {
		return possibilidade2;
	}

	public void setPossibilidade2(int possibilidade2) {
		this.possibilidade2 = possibilidade2;
	}

}
