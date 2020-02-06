package br.com.bbb.sudoku;


public class Sudoku4x4Test {

	/*
		0 [ 1 _ _ 4 ]
		1 [ _ 2 _ _ ]
		2 [ _ _ 4 _ ]
		3 [ 3 _ _ _ ]
	 */
	public static void main(String[] args) {
		int linhas = 4;
		int colunas = 4;
		int[][] matriz = new int [linhas][colunas];
		matriz[0][0] = 1;
		matriz[0][3] = 4;
		matriz[1][1] = 2;
		matriz[2][2] = 4;
		matriz[3][0] = 3;

		Sudoku sudoku = new Sudoku(linhas, colunas, matriz);

		SudokuUtil.imprimeMatriz(matriz);
		
		int valor = -1;
		int contador = 0;
		do {
			
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz[i].length; j++) {
					
					if(matriz[i][j] == 0) {
						if(SudokuUtil.qtdPossibilidadesCelula(i, j, matriz).size() == 1) {
							valor = SudokuUtil.qtdPossibilidadesCelula(i, j, matriz).get(0);
							SudokuUtil.setValorNaLinhaColuna(valor, i, j, matriz, "RG01");
						} 
					}
				}
			}
			
		} while(SudokuUtil.existeCelulaVazia(matriz) && SudokuUtil.existeCelula01Possib(matriz) && contador < (linhas*colunas));
		
		SudokuUtil.imprimeMatriz(matriz);
	}
	
}
