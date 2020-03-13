package br.com.bbb.sudoku;


public class Sudoku9x9FacilN10Test {

	/**


	/**
		Matriz de Entrada:
	  	  =========================
		0 [ 2 6 _ | _ _ 9 | 4 _ _ ]
		1 [ _ _ _ | 3 _ 4 | _ _ _ ]
		2 [ _ _ 4 | _ _ _ | 7 9 _ ]
		  =========================
		3 [ _ _ _ | _ 1 _ | 9 7 _ ]
		4 [ 7 5 _ | _ 9 _ | 1 2 _ ]
		5 [ 4 _ _ | _ _ 8 | _ _ _ ]
		  =========================
		6 [ _ _ 7 | 2 _ 6 | 3 8 _ ]
		7 [ _ _ 3 | _ _ _ | _ 6 _ ]
		8 [ 8 _ _ | _ _ 7 | _ _ _ ]
		  =========================

		Matriz Resolvida:
	  	  =========================
		0 [ 2 6 5 | 8 7 9 | 4 1 3 ]
		1 [ 1 7 9 | 3 6 4 | 8 5 2 ]
		2 [ 3 8 4 | 1 5 2 | 7 9 6 ]
		  =========================
		3 [ 6 3 2 | 4 1 5 | 9 7 8 ]
		4 [ 7 5 8 | 6 9 3 | 1 2 4 ]
		5 [ 4 9 1 | 7 2 8 | 6 3 5 ]
		  =========================
		6 [ 5 1 7 | 2 4 6 | 3 8 9 ]
		7 [ 9 4 3 | 5 8 1 | 2 6 7 ]
		8 [ 8 2 6 | 9 3 7 | 5 4 1 ]
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

		//0 [ 2 6 _ | _ _ 9 | 4 _ _ ]
		matriz[0][0] = 2;
		matriz[0][1] = 6;
		matriz[0][5] = 9;
		matriz[0][6] = 4;
		
		//1 [ _ _ _ | 3 _ 4 | _ _ _ ]
		matriz[1][3] = 3;
		matriz[1][5] = 4;
		
		//2 [ _ _ 4 | _ _ _ | 7 9 _ ]
		matriz[2][2] = 4;
		matriz[2][6] = 7;
		matriz[2][7] = 9;

		//3 [ _ _ _ | _ 1 _ | 9 7 _ ]
		matriz[3][4] = 1;
		matriz[3][6] = 9;
		matriz[3][7] = 7;

		//4 [ 7 5 _ | _ 9 _ | 1 2 _ ]
		matriz[4][0] = 7;
		matriz[4][1] = 5;
		matriz[4][4] = 9;
		matriz[4][6] = 1;
		matriz[4][7] = 2;

		//5 [ 4 _ _ | _ _ 8 | _ _ _ ]
		matriz[5][0] = 4;
		matriz[5][5] = 8;
		
		//6 [ _ _ 7 | 2 _ 6 | 3 8 _ ]
		matriz[6][2] = 7;
		matriz[6][3] = 2;
		matriz[6][5] = 6;
		matriz[6][6] = 3;
		matriz[6][7] = 8;
		
		//7 [ _ _ 3 | _ _ _ | _ 6 _ ]
		matriz[7][2] = 3;
		matriz[7][7] = 6;
		
		//8 [ 8 _ _ | _ _ 7 | _ _ _ ]
		matriz[8][0] = 8;
		matriz[8][5] = 7;
		
		return matriz;
	}
	
	public static int[][] getMatrizPreenchida(int linhas, int colunas) {
		int[][] matrizVerificacao = new int [linhas][colunas];
		// =================================
		// matriz de Verificação

		//0 [ 2 6 5 | 8 7 9 | 4 1 3 ]
		matrizVerificacao[0][0] = 2;
		matrizVerificacao[0][1] = 6;
		matrizVerificacao[0][2] = 5;
		matrizVerificacao[0][3] = 8;
		matrizVerificacao[0][4] = 7;
		matrizVerificacao[0][5] = 9;
		matrizVerificacao[0][6] = 4;
		matrizVerificacao[0][7] = 1;
		matrizVerificacao[0][8] = 3;
		
		//1 [ 1 7 9 | 3 6 4 | 8 5 2 ]
		matrizVerificacao[1][0] = 1;
		matrizVerificacao[1][1] = 7;
		matrizVerificacao[1][2] = 9;
		matrizVerificacao[1][3] = 3;
		matrizVerificacao[1][4] = 6;
		matrizVerificacao[1][5] = 4;
		matrizVerificacao[1][6] = 8;
		matrizVerificacao[1][7] = 5;
		matrizVerificacao[1][8] = 2;
		
		//2 [ 3 8 4 | 1 5 2 | 7 9 6 ]
		matrizVerificacao[2][0] = 3;
		matrizVerificacao[2][1] = 8;
		matrizVerificacao[2][2] = 4;
		matrizVerificacao[2][3] = 1;
		matrizVerificacao[2][4] = 5;
		matrizVerificacao[2][5] = 2;
		matrizVerificacao[2][6] = 7;
		matrizVerificacao[2][7] = 9;
		matrizVerificacao[2][8] = 6;
		
		//3 [ 6 3 2 | 4 1 5 | 9 7 8 ]
		matrizVerificacao[3][0] = 6;
		matrizVerificacao[3][1] = 3;
		matrizVerificacao[3][2] = 2;
		matrizVerificacao[3][3] = 4;
		matrizVerificacao[3][4] = 1;
		matrizVerificacao[3][5] = 5;
		matrizVerificacao[3][6] = 9;
		matrizVerificacao[3][7] = 7;
		matrizVerificacao[3][8] = 8;
		
		//4 [ 7 5 8 | 6 9 3 | 1 2 4 ]
		matrizVerificacao[4][0] = 7;
		matrizVerificacao[4][1] = 5;
		matrizVerificacao[4][2] = 8;
		matrizVerificacao[4][3] = 6;
		matrizVerificacao[4][4] = 9;
		matrizVerificacao[4][5] = 3;
		matrizVerificacao[4][6] = 1;
		matrizVerificacao[4][7] = 2;
		matrizVerificacao[4][8] = 4;
		
		//5 [ 4 9 1 | 7 2 8 | 6 3 5 ]
		matrizVerificacao[5][0] = 4;
		matrizVerificacao[5][1] = 9;
		matrizVerificacao[5][2] = 1;
		matrizVerificacao[5][3] = 7;
		matrizVerificacao[5][4] = 2;
		matrizVerificacao[5][5] = 8;
		matrizVerificacao[5][6] = 6;
		matrizVerificacao[5][7] = 3;
		matrizVerificacao[5][8] = 5;
		
		//6 [ 5 1 7 | 2 4 6 | 3 8 9 ]
		matrizVerificacao[6][0] = 5;
		matrizVerificacao[6][1] = 1;
		matrizVerificacao[6][2] = 7;
		matrizVerificacao[6][3] = 2;
		matrizVerificacao[6][4] = 4;
		matrizVerificacao[6][5] = 6;
		matrizVerificacao[6][6] = 3;
		matrizVerificacao[6][7] = 8;
		matrizVerificacao[6][8] = 9;
			
		//7 [ 9 4 3 | 5 8 1 | 2 6 7 ]
		matrizVerificacao[7][0] = 9;
		matrizVerificacao[7][1] = 4;
		matrizVerificacao[7][2] = 3;
		matrizVerificacao[7][3] = 5;
		matrizVerificacao[7][4] = 8;
		matrizVerificacao[7][5] = 1;
		matrizVerificacao[7][6] = 2;
		matrizVerificacao[7][7] = 6;
		matrizVerificacao[7][8] = 7;
		
		//8 [ 8 2 6 | 9 3 7 | 5 4 1 ]
		matrizVerificacao[8][0] = 8;
		matrizVerificacao[8][1] = 2;
		matrizVerificacao[8][2] = 6;
		matrizVerificacao[8][3] = 9;
		matrizVerificacao[8][4] = 3;
		matrizVerificacao[8][5] = 7;
		matrizVerificacao[8][6] = 5;
		matrizVerificacao[8][7] = 4;
		matrizVerificacao[8][8] = 1;
		
		return matrizVerificacao;
	}
	
}
