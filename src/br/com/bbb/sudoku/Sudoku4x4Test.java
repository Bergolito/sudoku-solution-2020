package br.com.bbb.sudoku;


public class Sudoku4x4Test {

	/**
		0 [ 1 _ _ 4 ]
		1 [ _ 2 _ _ ]
		2 [ _ _ 4 _ ]
		3 [ 3 _ _ _ ]
		
		0 [ 1 3 2 4 ]
		1 [ 4 2 3 1 ]
		2 [ 2 1 4 3 ]
		3 [ 3 4 1 2 ]		

	 * @param args
	 */
	public static void main(String[] args) {
		int linhas = 4;
		int colunas = 4;
		String LOG_LEVEL_DEBUG = "DEBUG";
		int[][] matriz = getMatrizInicial(linhas, colunas);
		int[][] matrizVerificacao = getMatrizPreenchida(linhas, colunas);
		long inicio = System.currentTimeMillis();
		
		Sudoku sudoku = new Sudoku(linhas, colunas, matriz, LOG_LEVEL_DEBUG);
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
		matriz[0][0] = 1;
		matriz[0][3] = 4;
		matriz[1][1] = 2;
		matriz[2][2] = 4;
		matriz[3][0] = 3;
		
		return matriz;
	}
		
	public static int[][] getMatrizPreenchida(int linhas, int colunas) {
		int[][] matrizVerificacao = new int [linhas][colunas];

		//
		matrizVerificacao[0][0] = 1;
		matrizVerificacao[0][1] = 3;
		matrizVerificacao[0][2] = 2;
		matrizVerificacao[0][3] = 4;
		//
		matrizVerificacao[1][0] = 4;
		matrizVerificacao[1][1] = 2;
		matrizVerificacao[1][2] = 3;
		matrizVerificacao[1][3] = 1;		
		//
		matrizVerificacao[2][0] = 2;
		matrizVerificacao[2][1] = 1;
		matrizVerificacao[2][2] = 4;
		matrizVerificacao[2][3] = 3;
		//
		matrizVerificacao[3][0] = 3;
		matrizVerificacao[3][1] = 4;
		matrizVerificacao[3][2] = 1; 
		matrizVerificacao[3][3] = 2;
		
		return matrizVerificacao;
	}
			
}
