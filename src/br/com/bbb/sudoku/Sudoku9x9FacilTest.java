package br.com.bbb.sudoku;


public class Sudoku9x9FacilTest {

	/**
		0 [ _ _ 3 | _ _ _ | _ _ _ ]
		1 [ _ 8 _ | _ 1 7 | 3 6 _ ]
		2 [ _ 2 _ | _ 9 3 | _ _ 8 ]
		  ========================= 
		3 [ _ 5 2 | _ _ _ | _ _ _ ]
		4 [ _ 6 4 | _ _ _ | 1 3 _ ]
		5 [ _ _ _ | _ _ _ | 5 4 _ ]
		  =========================
		6 [ 1 _ _ | 5 7 _ | _ 8 _ ]
		7 [ _ 7 6 | 1 4 _ | _ 2 _ ]
		8 [ _ _ _ | _ _ _ | 7 _ _ ]
		
		Matriz Resolvida
		0 [ 6 9 3 | 8 5 4 | 2 7 1 ]
		1 [ 4 8 5 | 2 1 7 | 3 6 9 ]
		2 [ 7 2 1 | 6 9 3 | 4 5 8 ]
		  =========================
		3 [ 3 5 2 | 4 6 1 | 8 9 7 ]
		4 [ 9 6 4 | 7 8 5 | 1 3 2 ]
		5 [ 8 1 7 | 3 2 9 | 5 4 6 ]
		  =========================
		6 [ 1 3 9 | 5 7 2 | 6 8 4 ]
		7 [ 5 7 6 | 1 4 8 | 9 2 3 ]
		8 [ 2 4 8 | 9 3 6 | 7 1 5 ]
	 */
	public static void main(String[] args) {
		int linhas = 9;
		int colunas = 9;
		String LOG_LEVEL_DEBUG = "DEBUG";
		int[][] matriz = new int [linhas][colunas];

		//
		matriz[0][2] = 3;
		//
		matriz[1][1] = 8;
		matriz[1][4] = 1;
		matriz[1][5] = 7;
		matriz[1][6] = 3;
		matriz[1][7] = 6;
		//
		matriz[2][1] = 2;
		matriz[2][4] = 9;
		matriz[2][5] = 3;
		matriz[2][8] = 8;
		//
		matriz[3][1] = 5;
		matriz[3][2] = 2;
		//
		matriz[4][1] = 6;
		matriz[4][2] = 4;
		matriz[4][6] = 1;
		matriz[4][7] = 3;
		//
		matriz[5][6] = 5;
		matriz[5][7] = 4;
		//
		matriz[6][0] = 1;
		matriz[6][3] = 5;
		matriz[6][4] = 7;
		matriz[6][7] = 8;
		//
		matriz[7][1] = 7;
		matriz[7][2] = 6;
		matriz[7][3] = 1;
		matriz[7][4] = 4;
		matriz[7][7] = 2;
		//
		matriz[8][6] = 7;

		long inicio = System.currentTimeMillis();
		Sudoku sudoku = new Sudoku(linhas, colunas, matriz, null);
		System.out.println("Matriz inicial:");
		SudokuUtil.imprimeMatriz(matriz);
		System.out.println("Qtd celulas vazias = "+SudokuUtil.qtdCelulasVazias(matriz));
		sudoku.analisaSolucao(matriz);
		long fim = System.currentTimeMillis();
		long duracao = (long)fim - inicio;
		System.out.println("Achou a solução em "+duracao+" ms");
	}
	
}
