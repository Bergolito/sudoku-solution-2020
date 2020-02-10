package br.com.bbb.sudoku;

import junit.framework.TestCase;

public class SudokuUtilTeste01Test extends TestCase {
	
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
	private static final int LINHAS = 9;
	private static final int COLUNAS = 9;
	
	// assigning the values
	protected void setUp() {
	}

	public void existeNumeroQuadrante() {
		
		int[][] matriz = getMatrizInicial(LINHAS, COLUNAS);
		assertEquals(SudokuUtil.existeNumeroQuadrante(8, 1, matriz) , 1);
		assertEquals(SudokuUtil.existeNumeroQuadrante(9, 4, matriz) , 0);
	}

	public void retornaColunasQuadrante() {
		assertEquals(SudokuUtil.retornaColunasQuadrante(1).get(0).intValue(), 0);
		assertEquals(SudokuUtil.retornaColunasQuadrante(9).get(0).intValue(), 6);
	}

	public void retornaLinhasQuadrante() {
		assertEquals(SudokuUtil.retornaLinhasQuadrante(3).get(0).intValue(), 0);
		assertEquals(SudokuUtil.retornaLinhasQuadrante(7).get(0).intValue(), 6);
	}

	public void retornaCoordenadasQuadrante() {
		int[][] matriz = getMatrizInicial(LINHAS, COLUNAS);
		int[] quadrantes = SudokuUtil.retornaCoordenadasQuadrante(1, matriz);
		assertEquals(quadrantes[0], 0);
		assertEquals(quadrantes[1], 2);
		assertEquals(quadrantes[2], 0);
		assertEquals(quadrantes[3], 2);
	}
	
	//		public static int qualLinhaNumeroEstaNoQuadrante(int numeroAnalisado, int quadrante, int[][] matriz) {
	public void qualLinhaNumeroEstaNoQuadrante() {
		
	}

	//	public static int qtdCelulasPreenchidasNasLinhasNaColuna(List<Integer> linhasQuadrante, int coluna, int[][] matriz) {
	public void qtdCelulasPreenchidasNasLinhasNaColuna() {
		
	}
	//	public static List<Integer> retornaElementosQuadrante(int quadrante, int[][] matriz) {
	public void retornaElementosQuadrante() {
		
	}

	//	public static Set<Integer> pegaElementosQuadrante(int linha, int coluna, int[][] matriz) {
	public void pegaElementosQuadrante() {
		
	}
	//	public static Set<Integer> pegaElementosColuna(int linha, int coluna, int[][] matriz) {
	public void pegaElementosColuna() {
		
	}
	//	public static int qtdCelulasPreenchidasNasColunasNaLinha(List<Integer> colunasQuadrante, int linha, int[][] matriz) {
	public void qtdCelulasPreenchidasNasColunasNaLinha() {
		
	}
	//	public static List<Integer> qtdPossibilidadesCelula(int linha, int coluna, int[][] matriz) {
	public void qtdPossibilidadesCelula() {
		
	}
	//	public static Set<Integer> pegaElementosLinha(int linha, int coluna, int[][] matriz) {
	public void pegaElementosLinha() {
		
	}
	
	//	public static boolean existeCelulaQtdPossib(int qtdPossibs, int[][] matriz) {
	public void existeCelulaQtdPossib() {
		
	}
	public void existeCelulaVazia() {
		int[][] matriz = getMatrizInicial(LINHAS, COLUNAS);
		int[][] matrizVerificacao = getMatrizPreenchida(LINHAS, COLUNAS);
		
		assertTrue(SudokuUtil.existeCelulaVazia(matriz));
		assertFalse(SudokuUtil.existeCelulaVazia(matrizVerificacao));
	}
	
	//	public static int retornaLinhaVaziaNaColunaNoQuadrante(int coluna, List<Integer> linhasQuadrante, int[][] matriz) {
	public void retornaLinhaVaziaNaColunaNoQuadrante() {
		
	}

	public void existeNumeroNaLinha() {
		int[][] matriz = getMatrizInicial(LINHAS, COLUNAS);
		
		assertEquals(SudokuUtil.existeNumeroNaLinha(8, 6, matriz), 1);
		assertEquals(SudokuUtil.existeNumeroNaLinha(7, 3, matriz), 0);
	}
	
	public void existeNumeroNaColuna() {
		int[][] matriz = getMatrizInicial(LINHAS, COLUNAS);
		assertEquals(SudokuUtil.existeNumeroNaColuna(6, 2, matriz), 1);
		assertEquals(SudokuUtil.existeNumeroNaColuna(9, 6, matriz), 0);
		
	}

	//	public static int retornaColunaVaziaNaLinhaNoQuadrante(int linha, List<Integer> linhasQuadrante, int[][] matriz) {
	public void retornaColunaVaziaNaLinhaNoQuadrante() {
		
	}
	//	public static int qualColunaNumeroEstaNoQuadrante(int numeroAnalisado, int quadrante, int[][] matriz) {
	public void qualColunaNumeroEstaNoQuadrante() {
		
	}
	
	//	public static boolean existeQuadranteQtdPosicoesRestantes(int qtdPosicoes, int[][] matriz) {
	public void existeQuadranteQtdPosicoesRestantes() {
		
	}

	//	public static boolean existeColuna02PosicoesRestantes(int coluna, int[][] matriz) {
	public void existeColuna02PosicoesRestantes() {
		
	}

	//	public static boolean existeColunaQtdPosicoesRestantes(int coluna, int qtdPosicoes, int[][] matriz) {
	public void existeColunaQtdPosicoesRestantes() {
		
	}

	//	public static boolean existeLinha02PosicoesRestantes(int linha, int[][] matriz) {
	public void existeLinha02PosicoesRestantes() {
		
	}

	//	public static boolean existeLinha01PosicaoRestante(int linha, int[][] matriz) {
	public void existeLinha01PosicaoRestante() {
		
	}

	//	public static boolean existeColuna01PosicaoRestante(int coluna, int[][] matriz) {
	public void existeColuna01PosicaoRestante() {
		
	}

	//	public static boolean existeQuadrante02PosicoesRestantes(int[][] matriz) {
	public void existeQuadrante02PosicoesRestantes() {
		
	}

	//	public static boolean existeQuadrante01PosicaoRestante(int[][] matriz) {
	public void existeQuadrante01PosicaoRestante() {
		
	}


	//public static Posicao posicaoColuna01PosicaoRestante(int coluna, int[][] matriz) {
	public void posicaoColuna01PosicaoRestante() {
		
	}
	
	//	public static boolean existeLinha03PosicoesRestantes(int linha, int[][] matriz) {
	public void existeLinha03PosicoesRestantes() {
		
	}

	//	public static boolean existeLinhaQtdPosicoesRestantes(int linha, int qtdPosicoes, int[][] matriz) {
	public void existeLinhaQtdPosicoesRestantes() {
		
	}

	//	public static void resolveQuadrante02PosicoesRestantes(int[][] matriz) {
	public void resolveQuadrante02PosicoesRestantes() {
		
	}

	//	public static List<Integer> retornaNumerosPossiveis(int[][] matriz) {
	public void retornaNumerosPossiveis() {
		
	}

	//	public static Posicao retornaQuadrante01PosicaoRestante(int[][] matriz) {
	public void retornaQuadrante01PosicaoRestante() {
		
	}

	//	public static int[] retornaElementosReestantesQuadrante(int[][] matriz) {
	public void retornaElementosReestantesQuadrante() {
		
	}

	//	public static Posicao posicaoLinha01PosicaoRestante(int linha, int[][] matriz) {
	public void posicaoLinha01PosicaoRestante() {
		
	}

	//	public static Posicao retornaCelula01Possib(int[][] matriz) {
	public void retornaCelula01Possib() {
		
	}

	public void existeCelula01Possib() {
		int[][] matriz = getMatrizInicial(LINHAS, COLUNAS);
		int[][] matrizVerificacao = getMatrizPreenchida(LINHAS, COLUNAS);

		assertTrue(SudokuUtil.existeCelula01Possib(matriz));
		//verificaSolucaoFinal(int[][] matriz, int[][] matrizVerificacao)
		assertEquals(SudokuUtil.qtdCelulasVazias(matriz), 53);
		assertEquals(SudokuUtil.qtdCelulasVazias(matrizVerificacao), 0);
	}
	
	public static int[][] getMatrizInicial(int linhas, int colunas) {
		int[][] matriz = new int [linhas][colunas];
		
		// 0 [ _ _ 3 | _ _ _ | _ _ _ ]
		matriz[0][2] = 3;
		// 1 [ _ 8 _ | _ 1 7 | 3 6 _ ]
		matriz[1][1] = 8;
		matriz[1][4] = 1;
		matriz[1][5] = 7;
		matriz[1][6] = 3;
		matriz[1][7] = 6;
		// 2 [ _ 2 _ | _ 9 3 | _ _ 8 ]
		matriz[2][1] = 2;
		matriz[2][4] = 9;
		matriz[2][5] = 3;
		matriz[2][8] = 8;
		// 3 [ _ 5 2 | _ _ _ | _ _ _ ]
		matriz[3][1] = 5;
		matriz[3][2] = 2;
		// 4 [ _ 6 4 | _ _ _ | 1 3 _ ]
		matriz[4][1] = 6;
		matriz[4][2] = 4;
		matriz[4][6] = 1;
		matriz[4][7] = 3;
		// 5 [ _ _ _ | _ _ _ | 5 4 _ ]
		matriz[5][6] = 5;
		matriz[5][7] = 4;
		// 6 [ 1 _ _ | 5 7 _ | _ 8 _ ]
		matriz[6][0] = 1;
		matriz[6][3] = 5;
		matriz[6][4] = 7;
		matriz[6][7] = 8;
		// 7 [ _ 7 6 | 1 4 _ | _ 2 _ ]
		matriz[7][1] = 7;
		matriz[7][2] = 6;
		matriz[7][3] = 1;
		matriz[7][4] = 4;
		matriz[7][7] = 2;
		// 8 [ _ _ _ | _ _ _ | 7 _ _ ]
		matriz[8][6] = 7;
		
		return matriz;
	}
	
	public static int[][] getMatrizPreenchida(int linhas, int colunas) {
		int[][] matrizVerificacao = new int [linhas][colunas];
		// =================================
		// matriz de Verificação
		// 0 - [ 6 9 3 | 8 5 4 | 2 7 1 ]
		matrizVerificacao[0][0] = 6;
		matrizVerificacao[0][1] = 9;
		matrizVerificacao[0][2] = 3;
		matrizVerificacao[0][3] = 8;
		matrizVerificacao[0][4] = 5;
		matrizVerificacao[0][5] = 4;
		matrizVerificacao[0][6] = 2;
		matrizVerificacao[0][7] = 7;
		matrizVerificacao[0][8] = 1;

		// 1 [ 4 8 5 | 2 1 7 | 3 6 9 ]
		matrizVerificacao[1][0] = 4;
		matrizVerificacao[1][1] = 8;
		matrizVerificacao[1][2] = 5;
		matrizVerificacao[1][3] = 2;
		matrizVerificacao[1][4] = 1;
		matrizVerificacao[1][5] = 7;
		matrizVerificacao[1][6] = 3;
		matrizVerificacao[1][7] = 6;
		matrizVerificacao[1][8] = 9;
		
		// 2 [ 7 2 1 | 6 9 3 | 4 5 8 ]
		matrizVerificacao[2][0] = 7;
		matrizVerificacao[2][1] = 2;
		matrizVerificacao[2][2] = 1;
		matrizVerificacao[2][3] = 6;
		matrizVerificacao[2][4] = 9;
		matrizVerificacao[2][5] = 3;
		matrizVerificacao[2][6] = 4;
		matrizVerificacao[2][7] = 5;
		matrizVerificacao[2][8] = 8;
		
		// 3 [ 3 5 2 | 4 6 1 | 8 9 7 ]
		matrizVerificacao[3][0] = 3;
		matrizVerificacao[3][1] = 5;
		matrizVerificacao[3][2] = 2;
		matrizVerificacao[3][3] = 4;
		matrizVerificacao[3][4] = 6;
		matrizVerificacao[3][5] = 1;
		matrizVerificacao[3][6] = 8;
		matrizVerificacao[3][7] = 9;
		matrizVerificacao[3][8] = 7;
		
		// 4 [ 9 6 4 | 7 8 5 | 1 3 2 ]
		matrizVerificacao[4][0] = 9;
		matrizVerificacao[4][1] = 6;
		matrizVerificacao[4][2] = 4;
		matrizVerificacao[4][3] = 7;
		matrizVerificacao[4][4] = 8;
		matrizVerificacao[4][5] = 5;
		matrizVerificacao[4][6] = 1;
		matrizVerificacao[4][7] = 3;
		matrizVerificacao[4][8] = 2;
		
		// 5 [ 8 1 7 | 3 2 9 | 5 4 6 ]
		matrizVerificacao[5][0] = 8;
		matrizVerificacao[5][1] = 1;
		matrizVerificacao[5][2] = 7;
		matrizVerificacao[5][3] = 3;
		matrizVerificacao[5][4] = 2;
		matrizVerificacao[5][5] = 9;
		matrizVerificacao[5][6] = 5;
		matrizVerificacao[5][7] = 4;
		matrizVerificacao[5][8] = 6;
		
		// 6 [ 1 3 9 | 5 7 2 | 6 8 4 ]
		matrizVerificacao[6][0] = 1;
		matrizVerificacao[6][1] = 3;
		matrizVerificacao[6][2] = 9;
		matrizVerificacao[6][3] = 5;
		matrizVerificacao[6][4] = 7;
		matrizVerificacao[6][5] = 2;
		matrizVerificacao[6][6] = 6;
		matrizVerificacao[6][7] = 8;
		matrizVerificacao[6][8] = 4;
		
		// 7 [ 5 7 6 | 1 4 8 | 9 2 3 ]
		matrizVerificacao[7][0] = 5;
		matrizVerificacao[7][1] = 7;
		matrizVerificacao[7][2] = 6;
		matrizVerificacao[7][3] = 1;
		matrizVerificacao[7][4] = 4;
		matrizVerificacao[7][5] = 8;
		matrizVerificacao[7][6] = 9;
		matrizVerificacao[7][7] = 2;
		matrizVerificacao[7][8] = 3;
		
		// 8 [ 2 4 8 | 9 3 6 | 7 1 5 ]
		matrizVerificacao[8][0] = 2;
		matrizVerificacao[8][1] = 4;
		matrizVerificacao[8][2] = 8;
		matrizVerificacao[8][3] = 9;
		matrizVerificacao[8][4] = 3;
		matrizVerificacao[8][5] = 6;
		matrizVerificacao[8][6] = 7;
		matrizVerificacao[8][7] = 1;
		matrizVerificacao[8][8] = 5;
		
		return matrizVerificacao;
	}
	
}
