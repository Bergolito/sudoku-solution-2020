package br.com.bbb.sudoku;


public class Sudoku9x9FacilN06Test {

	/**
		Matriz de Entrada:
	  	  =========================
		0 [ 6 1 8 | _ _ _ | 4 _ _ ]
		1 [ _ _ _ | _ 9 6 | _ _ _ ]
		2 [ _ _ _ | 8 _ _ | _ _ 7 ]
		  =========================
		3 [ _ _ _ | 6 8 9 | _ _ 3 ]
		4 [ 4 _ _ | 1 _ _ | _ _ _ ]
		5 [ 9 _ _ | _ 4 _ | _ _ 8 ]
		  =========================
		6 [ _ _ _ | _ _ _ | 7 _ _ ]
		7 [ _ 7 _ | _ _ 3 | _ _ 6 ]
		8 [ _ 4 _ | 2 _ _ | _ _ 5 ]
		  =========================

		Matriz Resolvida:
		  =========================
		0 [ 6 1 8 | 7 2 5 | 4 3 9 ]		
		1 [ 7 5 4 | 3 9 6 | 2 8 1 ]
		2 [ 3 9 2 | 8 1 4 | 6 5 7 ]
	  	  =========================
		3 [ 1 2 7 | 6 8 9 | 5 4 3 ]
		4 [ 4 8 5 | 1 3 7 | 9 6 2 ]
		5 [ 9 6 3 | 5 4 2 | 1 7 8 ]
    	  =========================
		6 [ 5 3 1 | 9 6 8 | 7 2 4 ]
		7 [ 2 7 9 | 4 5 3 | 8 1 6 ]
		8 [ 8 4 6 | 2 7 1 | 3 9 5 ]
    	  =========================
	 */
	public static void main(String[] args) {
		int linhas = 9;
		int colunas = 9;
		String LOG_LEVEL_DEBUG = "DEBUG";
		int[][] matriz = getMatrizInicial(linhas, colunas);
		int[][] matrizVerificacao = getMatrizPreenchida(linhas, colunas);
		long inicio = System.currentTimeMillis();
		
		Sudoku sudoku = new Sudoku(linhas, colunas, matriz, null);
		System.out.println("Matriz inicial:");
		SudokuUtil.imprimeMatriz(matriz);
		System.out.println("Qtd celulas vazias = "+SudokuUtil.qtdCelulasVazias(matriz));
		sudoku.analisaSolucao(matriz);
		SudokuUtil.verificaSolucaoFinal(matriz, matrizVerificacao);
		long fim = System.currentTimeMillis();
		long duracao = (long)fim - inicio;
		System.out.println("Achou a solução em "+duracao+" ms");
	}
	
	public static int[][] getMatrizInicial(int linhas, int colunas) {
		int[][] matriz = new int [linhas][colunas];
		
		// 0 [ 6 1 8 | _ _ _ | 4 _ _ ]
		matriz[0][0] = 6;
		matriz[0][1] = 1;
		matriz[0][2] = 8;
		matriz[0][6] = 4;
		// TODO
		//matriz[0][8] = 9;
		
		// 1 [ _ _ _ | _ 9 6 | _ _ _ ]
		matriz[1][4] = 9;
		matriz[1][5] = 6;
		// 2 [ _ _ _ | 8 _ _ | _ _ 7 ]
		matriz[2][3] = 8;
		matriz[2][8] = 7;
		// 3 [ _ _ _ | 6 8 9 | _ _ 3 ]
		matriz[3][3] = 6;
		matriz[3][4] = 8;
		matriz[3][5] = 9;
		matriz[3][8] = 3;
		// 4 [ 4 _ _ | 1 _ _ | _ _ _ ]
		matriz[4][0] = 4;
		matriz[4][3] = 1;
		// TODO
		//matriz[4][8] = 2;
		
		// 5 [ 9 _ _ | _ 4 _ | _ _ 8 ]
		matriz[5][0] = 9;
		matriz[5][4] = 4;
		matriz[5][8] = 8;

		// 6 [ _ _ _ | _ _ _ | 7 _ _ ]
		matriz[6][6] = 7;
		
		// 7 [ _ 7 _ | _ _ 3 | _ _ 6 ]
		matriz[7][1] = 7;
		matriz[7][5] = 3;
		matriz[7][8] = 6;

		// 8 [ _ 4 _ | 2 _ _ | _ _ 5 ]
		matriz[8][1] = 4;
		matriz[8][3] = 2;
		matriz[8][8] = 5;
		
		return matriz;
	}
	
	public static int[][] getMatrizPreenchida(int linhas, int colunas) {
		int[][] matrizVerificacao = new int [linhas][colunas];
		// =================================
		// matriz de Verificação
		// 0 [ 6 1 8 | 7 2 5 | 4 3 9 ]		
		matrizVerificacao[0][0] = 6;
		matrizVerificacao[0][1] = 1;
		matrizVerificacao[0][2] = 8;
		matrizVerificacao[0][3] = 7;
		matrizVerificacao[0][4] = 2;
		matrizVerificacao[0][5] = 5;
		matrizVerificacao[0][6] = 4;
		matrizVerificacao[0][7] = 3;
		matrizVerificacao[0][8] = 9;
		// 1 [ 7 5 4 | 3 9 6 | 2 8 1 ]
		matrizVerificacao[1][0] = 7;
		matrizVerificacao[1][1] = 5;
		matrizVerificacao[1][2] = 4;
		matrizVerificacao[1][3] = 3;
		matrizVerificacao[1][4] = 9;
		matrizVerificacao[1][5] = 6;
		matrizVerificacao[1][6] = 2;
		matrizVerificacao[1][7] = 8;
		matrizVerificacao[1][8] = 1;
		// 2 [ 3 9 2 | 8 1 4 | 6 5 7 ]
		matrizVerificacao[2][0] = 3;
		matrizVerificacao[2][1] = 9;
		matrizVerificacao[2][2] = 2;
		matrizVerificacao[2][3] = 8;
		matrizVerificacao[2][4] = 1;
		matrizVerificacao[2][5] = 4;
		matrizVerificacao[2][6] = 6;
		matrizVerificacao[2][7] = 5;
		matrizVerificacao[2][8] = 7;
		// 3 [ 1 2 7 | 6 8 9 | 5 4 3 ]
		matrizVerificacao[3][0] = 1;
		matrizVerificacao[3][1] = 2;
		matrizVerificacao[3][2] = 7;
		matrizVerificacao[3][3] = 6;
		matrizVerificacao[3][4] = 8;
		matrizVerificacao[3][5] = 9;
		matrizVerificacao[3][6] = 5;
		matrizVerificacao[3][7] = 4;
		matrizVerificacao[3][8] = 3;
		// 4 [ 4 8 5 | 1 3 7 | 9 6 2 ]
		matrizVerificacao[4][0] = 4;
		matrizVerificacao[4][1] = 8;
		matrizVerificacao[4][2] = 5;
		matrizVerificacao[4][3] = 1;
		matrizVerificacao[4][4] = 3;
		matrizVerificacao[4][5] = 7;
		matrizVerificacao[4][6] = 9;
		matrizVerificacao[4][7] = 6;
		matrizVerificacao[4][8] = 2;
		// 5 [ 9 6 3 | 5 4 2 | 1 7 8 ]
		matrizVerificacao[5][0] = 9;
		matrizVerificacao[5][1] = 6;
		matrizVerificacao[5][2] = 3;
		matrizVerificacao[5][3] = 5;
		matrizVerificacao[5][4] = 4;
		matrizVerificacao[5][5] = 2;
		matrizVerificacao[5][6] = 1;
		matrizVerificacao[5][7] = 7;
		matrizVerificacao[5][8] = 8;

		// 6 [ 5 3 1 | 9 6 8 | 7 2 4 ]
		matrizVerificacao[6][0] = 5;
		matrizVerificacao[6][1] = 3;
		matrizVerificacao[6][2] = 1;
		matrizVerificacao[6][3] = 9;
		matrizVerificacao[6][4] = 6;
		matrizVerificacao[6][5] = 8;
		matrizVerificacao[6][6] = 7;
		matrizVerificacao[6][7] = 2;
		matrizVerificacao[6][8] = 4;
		
		// 7 [ 2 7 9 | 4 5 3 | 8 1 6 ]
		matrizVerificacao[7][0] = 2;
		matrizVerificacao[7][1] = 7;
		matrizVerificacao[7][2] = 9;
		matrizVerificacao[7][3] = 4;
		matrizVerificacao[7][4] = 5;
		matrizVerificacao[7][5] = 3;
		matrizVerificacao[7][6] = 8;
		matrizVerificacao[7][7] = 1;
		matrizVerificacao[7][8] = 6;

		// 8 [ 8 4 6 | 2 7 1 | 3 9 5 ]
		matrizVerificacao[8][0] = 8;
		matrizVerificacao[8][1] = 4;
		matrizVerificacao[8][2] = 6;
		matrizVerificacao[8][3] = 2;
		matrizVerificacao[8][4] = 7;
		matrizVerificacao[8][5] = 1;
		matrizVerificacao[8][6] = 3;
		matrizVerificacao[8][7] = 9;
		matrizVerificacao[8][8] = 5;
		
		return matrizVerificacao;
	}
	
}
