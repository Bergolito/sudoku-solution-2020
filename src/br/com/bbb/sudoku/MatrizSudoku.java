package br.com.bbb.sudoku;

public class MatrizSudoku {

	private int[][] matriz;
	
	public MatrizSudoku() {
		
	}
	
	public MatrizSudoku(int[][] mat) {
		matriz = new int[mat.length][mat.length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				matriz[i][j] = mat[i][j];		
			}
		}
	}

	public int[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(int[][] matriz) {
		this.matriz = matriz;
	}
	
	
	
}
