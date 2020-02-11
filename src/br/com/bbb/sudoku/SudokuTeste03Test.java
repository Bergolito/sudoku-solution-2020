package br.com.bbb.sudoku;

import org.junit.Test;

import junit.framework.TestCase;

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
public class SudokuTeste03Test extends TestCase {
	
	public static final int LINHAS = 9;
	public static final int COLUNAS = 9;
	
	// assigning the values
	protected void setUp() {
	}

	public void testSudoku9X9Valido() {
		String LOG_LEVEL_DEBUG = "DEBUG";
		int[][] matriz = getMatrizInicial(LINHAS, COLUNAS);
		int[][] matrizVerificacao = getMatrizPreenchida(LINHAS, COLUNAS);
		long inicio = System.currentTimeMillis();
		
		Sudoku sudoku = new Sudoku(LINHAS, COLUNAS, matriz, null);
		System.out.println("Matriz inicial:");
		SudokuUtil.imprimeMatriz(matriz);
		System.out.println("Qtd celulas vazias = "+SudokuUtil.qtdCelulasVazias(matriz));
		sudoku.analisaSolucao(matriz);
		
		boolean resultado = SudokuUtil.verificaSolucaoFinal(matriz, matrizVerificacao); 
		long fim = System.currentTimeMillis();
		long duracao = (long)fim - inicio;
		System.out.println("Achou a solução em "+duracao+" ms");

		assertTrue(resultado);
	}
	
	@Test
	public void testQtdCelulasVazias() {
		int[][] matriz = getMatrizInicial(LINHAS, COLUNAS);
		assertEquals(53, SudokuUtil.qtdCelulasVazias(matriz));
		int[][] matrizVerificacao = getMatrizPreenchida(LINHAS, COLUNAS);
		assertEquals(0, SudokuUtil.qtdCelulasVazias(matrizVerificacao));
	}

	public void testRetornaColunasQuadrante() {
		assertEquals(0, SudokuUtil.retornaColunasQuadrante(1).get(0).intValue());
		assertEquals(3, SudokuUtil.retornaColunasQuadrante(2).get(0).intValue());
		assertEquals(6, SudokuUtil.retornaColunasQuadrante(6).get(0).intValue());
	}
	
	public void testRetornaLinhasQuadrante() {
		assertEquals(0, SudokuUtil.retornaLinhasQuadrante(2).get(0).intValue());
		assertEquals(3, SudokuUtil.retornaLinhasQuadrante(5).get(0).intValue());
		assertEquals(6, SudokuUtil.retornaLinhasQuadrante(9).get(0).intValue());
	}

	public void testExisteNumeroQuadrante() {
		int[][] matriz = getMatrizInicial(LINHAS, COLUNAS);
		assertEquals(1, SudokuUtil.existeNumeroQuadrante(8, 1, matriz));
	}

	public void testRetornaCoordenadasQuadrante() {
		int[][] matriz = getMatrizInicial(LINHAS, COLUNAS);
		int[] coordenadas = SudokuUtil.retornaCoordenadasQuadrante(1, matriz);
		assertEquals(0, coordenadas[0]);
		assertEquals(2, coordenadas[1]);
		assertEquals(0, coordenadas[2]);
		assertEquals(2, coordenadas[3]);
	}

	public void testQualLinhaNumeroEstaNoQuadrante() {
		int[][] matriz = getMatrizInicial(LINHAS, COLUNAS);
		assertEquals(3,SudokuUtil.qualLinhaNumeroEstaNoQuadrante(5, 4, matriz));
		assertEquals(8,SudokuUtil.qualLinhaNumeroEstaNoQuadrante(7, 9, matriz));
	}

	public void testExisteNumeroNaLinha() {
		int[][] matriz = getMatrizInicial(LINHAS, COLUNAS);
		assertEquals(1, SudokuUtil.existeNumeroNaLinha(6, 1, matriz));
		assertEquals(0, SudokuUtil.existeNumeroNaLinha(8, 4, matriz));
	}

	public void testExisteNumeroNaColuna() {
		int[][] matriz = getMatrizInicial(LINHAS, COLUNAS);
		assertEquals(1, SudokuUtil.existeNumeroNaColuna(6, 2, matriz));
		assertEquals(0, SudokuUtil.existeNumeroNaColuna(2, 5, matriz));
	}
	
	//public static int qtdCelulasPreenchidasNasLinhasNaColuna(List<Integer> linhasQuadrante, int coluna, int[][] matriz) {
	public void testqtdCelulasPreenchidasNasLinhasNaColuna() {
		//SudokuUtil.qtdCelulasPreenchidasNasLinhasNaColuna(linhasQuadrante, coluna, matriz)
	}

	//public static int qtdCelulasPreenchidasNasColunasNaLinha(List<Integer> colunasQuadrante, int linha, int[][] matriz) {
	public void testqtdCelulasPreenchidasNasColunasNaLinha() {
		//SudokuUtil.qtdCelulasPreenchidasNasColunasNaLinha(colunasQuadrante, linha, matriz)
	}

	//public static List<Integer> qtdPossibilidadesCelula(int linha, int coluna, int[][] matriz) {
	public void testQtdPossibilidadesCelula() {
		//SudokuUtil.qtdPossibilidadesCelula(linha, coluna, matriz)
	}

	//public static Set<Integer> pegaElementosLinha(int linha, int coluna, int[][] matriz) {
	public void testpegaElementosLinha() {
		//SudokuUtil.pegaElementosLinha(linha, coluna, matriz)
	}

	//public static Set<Integer> pegaElementosColuna(int linha, int coluna, int[][] matriz) {
	public void testpegaElementosColuna() {
		//SudokuUtil.pegaElementosColuna(linha, coluna, matriz)
	}

	//public static Set<Integer> pegaElementosQuadrante(int linha, int coluna, int[][] matriz) {
	public void testpegaElementosQuadrante() {
		//SudokuUtil.pegaElementosQuadrante(linha, coluna, matriz)
	}

	//public static Set<Integer> pegaElementosQuadrante4X4(int linha, int coluna, int[][] matriz) {
	public void testpegaElementosQuadrante4X4() {
		//SudokuUtil.pegaElementosQuadrante4X4(linha, coluna, matriz)
	}

	//public static Set<Integer> pegaElementosQuadrante9X9(int linha, int coluna, int[][] matriz) {
	public void testpegaElementosQuadrante9X9() {
		//SudokuUtil.pegaElementosQuadrante9X9(linha, coluna, matriz)
	}

	//public static List<Integer> retornaElementosQuadrante(int quadrante, int[][] matriz) {
	public void testretornaElementosQuadrante() {
		//SudokuUtil.retornaElementosQuadrante(quadrante, matriz)
	}

	//public static int qualColunaNumeroEstaNoQuadrante(int numeroAnalisado, int quadrante, int[][] matriz) {
	public void testqualColunaNumeroEstaNoQuadrante() {
		//SudokuUtil.qualColunaNumeroEstaNoQuadrante(numeroAnalisado, quadrante, matriz)
	}

	//public static int retornaColunaVaziaNaLinhaNoQuadrante(int linha, List<Integer> linhasQuadrante, int[][] matriz) {
	public void testretornaColunaVaziaNaLinhaNoQuadrante() {
		//SudokuUtil.retornaColunaVaziaNaLinhaNoQuadrante(linha, linhasQuadrante, matriz)
	}

	//public static int retornaLinhaVaziaNaColunaNoQuadrante(int coluna, List<Integer> linhasQuadrante, int[][] matriz) {
	public void testretornaLinhaVaziaNaColunaNoQuadrante() {
		//SudokuUtil.retornaLinhaVaziaNaColunaNoQuadrante(coluna, linhasQuadrante, matriz)
	}

	//public static boolean existeCelulaVazia(int[][] matriz) {
	public void testexisteCelulaVazia() {
		//SudokuUtil.existeCelulaVazia(matriz)
	}


	//public static boolean existeCelulaQtdPossib(int qtdPossibs, int[][] matriz) {
	public void testexisteCelulaQtdPossib() {
		//SudokuUtil.existeCelulaQtdPossib(qtdPossibs, matriz)
	}

	//public static boolean existeCelula01Possib(int[][] matriz) {
	public void testexisteCelula01Possib() {
		//SudokuUtil.existeCelula01Possib(matriz)
	}

	//public static boolean existeColuna01PosicaoRestante(int coluna, int[][] matriz) {
	public void testexisteColuna01PosicaoRestante() {
		//SudokuUtil.existeColuna01PosicaoRestante(coluna, matriz)
	}

	//public static boolean existeLinha01PosicaoRestante(int linha, int[][] matriz) {
	public void testexisteLinha01PosicaoRestante() {
		//SudokuUtil.existeLinha01PosicaoRestante(linha, matriz)
	}

	//public static boolean existeLinha02PosicoesRestantes(int linha, int[][] matriz) {
	public void testexisteLinha02PosicoesRestantes() {
		//SudokuUtil.existeLinha02PosicoesRestantes(linha, matriz)
	}

	//public static boolean existeColunaQtdPosicoesRestantes(int coluna, int qtdPosicoes, int[][] matriz) {
	public void testexisteColunaQtdPosicoesRestantes() {
		//SudokuUtil.existeColunaQtdPosicoesRestantes(coluna, qtdPosicoes, matriz)
	}

	//public static boolean existeColuna02PosicoesRestantes(int coluna, int[][] matriz) {
	public void testexisteColuna02PosicoesRestantes() {
		//SudokuUtil.existeColuna02PosicoesRestantes(coluna, matriz)
	}

	//public static boolean existeQuadranteQtdPosicoesRestantes(int qtdPosicoes, int[][] matriz) {
	public void testexisteQuadranteQtdPosicoesRestantes() {
		//SudokuUtil.existeQuadranteQtdPosicoesRestantes(qtdPosicoes, matriz)
	}

	//public static boolean existeQuadrante01PosicaoRestante(int[][] matriz) {
	public void testexisteQuadrante01PosicaoRestante() {
		//SudokuUtil.existeQuadrante01PosicaoRestante(matriz)
	}

	//public static boolean existeQuadrante02PosicoesRestantes(int[][] matriz) {
	public void testexisteQuadrante02PosicoesRestantes() {
		//SudokuUtil.existeQuadrante02PosicoesRestantes(matriz)
	}

	//public static void exibirMsg(String msg, String mode) {
	public void testexibirMsg() {
		//SudokuUtil.exibirMsg(msg, mode);
	}

	//public static int[] retornaElementosReestantesQuadrante(int[][] matriz) {
	public void testretornaElementosReestantesQuadrante() {
		//SudokuUtil.retornaElementosReestantesQuadrante(matriz)
	}

	//public static Posicao retornaQuadrante01PosicaoRestante(int[][] matriz) {
	public void testretornaQuadrante01PosicaoRestante() {
		//SudokuUtil.retornaQuadrante01PosicaoRestante(matriz)
	}

	//public static List<Integer> retornaNumerosPossiveis(int[][] matriz) {
	public void testretornaNumerosPossiveis() {
		//SudokuUtil.retornaNumerosPossiveis(matriz)
	}

	//public static void resolveQuadrante02PosicoesRestantes(int[][] matriz) {
	public void testresolveQuadrante02PosicoesRestantes() {
		//SudokuUtil.resolveQuadrante02PosicoesRestantes(matriz);
	}

	//public static boolean existeLinhaQtdPosicoesRestantes(int linha, int qtdPosicoes, int[][] matriz) {
	public void testexisteLinhaQtdPosicoesRestantes() {
		//SudokuUtil.existeLinhaQtdPosicoesRestantes(linha, qtdPosicoes, matriz)
	}

	//public static boolean existeLinha03PosicoesRestantes(int linha, int[][] matriz) {
	public void testexisteLinha03PosicoesRestantes() {
		//SudokuUtil.existeLinha03PosicoesRestantes(linha, matriz)
	}

	//public static Posicao posicaoColuna01PosicaoRestante(int coluna, int[][] matriz) {
	public void testposicaoColuna01PosicaoRestante() {
		//SudokuUtil.posicaoColuna01PosicaoRestante(coluna, matriz)
	}
	
	//public static Posicao posicaoLinha01PosicaoRestante(int linha, int[][] matriz) {
	public void testposicaoLinha01PosicaoRestante() {
		//SudokuUtil.posicaoLinha01PosicaoRestante(linha, matriz)
	}

	//public static Posicao retornaCelula01Possib(int[][] matriz) {
	public void testretornaCelula01Possib() {
		//SudokuUtil.retornaCelula01Possib(matriz)
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
