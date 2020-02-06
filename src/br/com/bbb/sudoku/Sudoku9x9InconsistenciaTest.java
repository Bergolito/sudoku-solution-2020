package br.com.bbb.sudoku;


public class Sudoku9x9InconsistenciaTest {

	/**
		0 [ 1 2 3 4 5 6 7 8 9 ]
		1 [ 9 1 2 3 4 5 6 7 8 ]
		2 [ 8 9 1 2 3 4 5 6 7 ]
		3 [ 7 8 9 1 2 3 4 5 6 ]
		4 [ 6 7 8 9 1 2 3 4 5 ]
		5 [ 5 6 7 8 9 1 2 3 4 ]
		6 [ 4 5 6 7 8 9 1 2 3 ]
		7 [ 3 4 5 6 7 8 9 1 2 ]
		8 [ 2 3 4 5 6 7 8 9 1 ]
	 */
	public static void main(String[] args) {
		int linhas = 9;
		int colunas = 9;
		int[][] matriz = new int [linhas][colunas];

		//0 [ 1 2 3 4 5 6 7 8 9 ]
		matriz[0][0] = 1;
		matriz[0][1] = 2;
		matriz[0][2] = 3;
		matriz[0][3] = 4;
		matriz[0][4] = 5;
		matriz[0][5] = 6;
		matriz[0][6] = 7;
		matriz[0][7] = 8;
		matriz[0][8] = 9;
		//1 [ 9 1 2 3 4 5 6 7 8 ]
		matriz[1][0] = 9;
		matriz[1][1] = 1;
		matriz[1][2] = 2;
		matriz[1][3] = 3;
		matriz[1][4] = 4;
		matriz[1][5] = 5;
		matriz[1][6] = 6;
		matriz[1][7] = 7;
		matriz[1][8] = 8;
		//2 [ 8 9 1 2 3 4 5 6 7 ]
		matriz[2][0] = 8;
		matriz[2][1] = 9;
		matriz[2][2] = 1;
		matriz[2][3] = 2;
		matriz[2][4] = 3;
		matriz[2][5] = 4;
		matriz[2][6] = 5;
		matriz[2][7] = 6;
		matriz[2][8] = 7;
		//3 [ 7 8 9 1 2 3 4 5 6 ]
		matriz[3][0] = 7;
		matriz[3][1] = 8;
		matriz[3][2] = 9;
		matriz[3][3] = 1;
		matriz[3][4] = 2;
		matriz[3][5] = 3;
		matriz[3][6] = 4;
		matriz[3][7] = 5;
		matriz[3][8] = 6;
		//4 [ 6 7 8 9 1 2 3 4 5 ]
		matriz[4][0] = 6;
		matriz[4][1] = 7;
		matriz[4][2] = 8;
		matriz[4][3] = 9;
		matriz[4][4] = 1;
		matriz[4][5] = 2;
		matriz[4][6] = 3;
		matriz[4][7] = 4;
		matriz[4][8] = 5;
		//5 [ 5 6 7 8 9 1 2 3 4 ]
		matriz[5][0] = 5;
		matriz[5][1] = 6;
		matriz[5][2] = 7;
		matriz[5][3] = 8;
		matriz[5][4] = 9;
		matriz[5][5] = 1;
		matriz[5][6] = 2;
		matriz[5][7] = 3;
		matriz[5][8] = 4;
		//6 [ 4 5 6 7 8 9 1 2 3 ]
		matriz[6][0] = 4;
		matriz[6][1] = 5;
		matriz[6][2] = 6;
		matriz[6][3] = 7;
		matriz[6][4] = 8;
		matriz[6][5] = 9;
		matriz[6][6] = 1;
		matriz[6][7] = 2;
		matriz[6][8] = 3;
		//7 [ 3 4 5 6 7 8 9 1 2 ]
		matriz[7][0] = 3;
		matriz[7][1] = 4;
		matriz[7][2] = 5;
		matriz[7][3] = 6;
		matriz[7][4] = 7;
		matriz[7][5] = 8;
		matriz[7][6] = 9;
		matriz[7][7] = 1;
		matriz[7][8] = 2;
		//8 [ 2 3 4 5 6 7 8 9 1 ]
		matriz[8][0] = 2;
		matriz[8][1] = 3;
		matriz[8][2] = 4;
		matriz[8][3] = 5;
		matriz[8][4] = 6;
		matriz[8][5] = 7;
		matriz[8][6] = 8;
		matriz[8][7] = 9;
		matriz[8][8] = 1;

		//System.out.println("Existe inconsistencia na matriz = "+SudokuUtil.existeInconsistenciaMatriz(matriz));
	}
	
}
