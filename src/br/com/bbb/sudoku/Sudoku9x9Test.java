package br.com.bbb.sudoku;


public class Sudoku9x9Test {

	/**
		0 [ _ _ _ _ _ _ _ _ _ ]
		1 [ 8 9 4 2 _ 6 _ _ 1 ]
		2 [ _ _ 2 _ _ _ 6 _ 8 ]
		3 [ _ _ _ 3 _ 9 _ _ _ ]
		4 [ 1 _ _ _ 4 _ _ _ 2 ]
		5 [ 9 _ 7 6 _ 2 3 _ _ ]
		6 [ 7 _ _ _ _ 4 2 _ _ ]
		7 [ _ _ _ _ 7 _ 4 1 5 ]
		8 [ 3 _ _ _ 2 _ _ 6 _ ]
		
		Matriz Resolvida
		0 [ 6 7 1 4 8 5 9 2 3 ]
		1 [ 8 9 4 2 3 6 7 5 1 ]
		2 [ 5 3 2 1 9 7 6 4 8 ]
		3 [ 4 2 8 3 5 9 1 7 6 ]
		4 [ 1 6 3 7 4 8 5 9 2 ]
		5 [ 9 5 7 6 1 2 3 8 4 ]
		6 [ 7 1 5 8 6 4 2 3 9 ]
		7 [ 2 8 6 9 7 3 4 1 5 ]
		8 [ 3 4 9 5 2 1 8 6 7 ]		
	 */
	public static void main(String[] args) {
		int linhas = 9;
		int colunas = 9;
		int[][] matriz = new int [linhas][colunas];
		
		//
		matriz[1][0] = 8;
		matriz[1][1] = 9;
		matriz[1][2] = 4;
		matriz[1][3] = 2;
		matriz[1][5] = 6;
		matriz[1][8] = 1;
		//
		matriz[2][2] = 2;
		matriz[2][6] = 6;
		matriz[2][8] = 8;
		//
		matriz[3][3] = 3;
		matriz[3][5] = 9;
		//
		matriz[4][0] = 1;
		matriz[4][4] = 4;
		matriz[4][8] = 2;
		//
		matriz[5][0] = 9;
		matriz[5][2] = 7;
		matriz[5][3] = 6;
		matriz[5][5] = 2;
		matriz[5][6] = 3;
		//
		matriz[6][0] = 7;
		matriz[6][5] = 4;
		matriz[6][6] = 2;
		//
		matriz[7][4] = 7;
		matriz[7][6] = 4;
		matriz[7][7] = 1;
		matriz[7][8] = 5;
		//
		matriz[8][0] = 3;
		matriz[8][4] = 2;
		matriz[8][7] = 6;

		Sudoku sudoku = new Sudoku(linhas, colunas, matriz);
		SudokuUtil.imprimeMatriz(matriz);
		sudoku.analisaSolucao(matriz);
	}
	
}
