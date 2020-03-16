package br.com.bbb.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SudokuUtil {

	public static int inferencias = 0;
	
	private SudokuUtil() {
		//
	}
	
	public static boolean verificaSolucaoFinal(int[][] matriz, int[][] matrizVerificacao) {
		boolean encontrouSolucao = true;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if(matriz[i][j] != matrizVerificacao[i][j]) {
					System.out.print("Matriz de solução está INCORRETA. ");
					System.out.print("| Errada=("+i+","+j+")="+matriz[i][j]);
					System.out.print("| Correta=("+i+","+j+")="+matrizVerificacao[i][j]+"\n");
					encontrouSolucao = false;
					break;
				}
			}
		}
		
		if(encontrouSolucao) {
			System.out.println("Matriz de solução está CORRETA. PARABÉNS!!!");
		}
		return encontrouSolucao;
	}
	
	public static int qtdCelulasVazias(int[][] matriz) {
		int contador = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if(matriz[i][j] == 0) {
					contador++;	
				}
			}
		}
		return contador;
	}
	
	public static List<Integer> retornaColunasQuadrante(int quadrante) {
		List<Integer> colunas = new ArrayList<>();
		
		if(quadrante == 1 || quadrante == 4 || quadrante == 7) {
			colunas.add(0);colunas.add(1);colunas.add(2);
		
		} else if(quadrante == 2 || quadrante == 5 || quadrante == 8) {
			colunas.add(3);colunas.add(4);colunas.add(5);
			
		} else if(quadrante == 3 || quadrante == 6 || quadrante == 9) {
			colunas.add(6);colunas.add(7);colunas.add(8);
		}

		return colunas;
	}	

	public static List<Integer> retornaLinhasQuadrante(int quadrante) {
		List<Integer> linhas = new ArrayList<>();
		
		if(quadrante == 1 || quadrante == 2 || quadrante == 3) {// linhas 0, 1, 2
			linhas.add(0);linhas.add(1);linhas.add(2);
		
		} else if(quadrante == 4 || quadrante == 5 || quadrante == 6) { // linhas 3, 4, 5
			linhas.add(3);linhas.add(4);linhas.add(5);
			
		} else if(quadrante == 7 || quadrante == 8 || quadrante == 9) { // linhas 6, 7, 8
			linhas.add(6);linhas.add(7);linhas.add(8);
		}

		return linhas;
	}	

	public static int existeNumeroQuadrante(int numero, int quadrante, int[][] matriz) {
		int achou = 0;
		int linhaInicio = -1;
		int linhaFim = -1;
		int colunaInicio = -1;
		int colunaFim = -1;
		int[] coordenadas = null;
		 
		if(quadrante == 1) { // linhas 0, 1, 2
			coordenadas = retornaCoordenadasQuadrante(1, matriz);
		}
		else if(quadrante == 2) {
			coordenadas = retornaCoordenadasQuadrante(2, matriz);			
		}
		else if(quadrante == 3) {
			coordenadas = retornaCoordenadasQuadrante(3, matriz);			
		}
		else if(quadrante == 4) { // linhas 3, 4, 5 
			coordenadas = retornaCoordenadasQuadrante(4, matriz);
		}
		else if(quadrante == 5) {
			coordenadas = retornaCoordenadasQuadrante(5, matriz);
		}
		else if(quadrante == 6) {
			coordenadas = retornaCoordenadasQuadrante(6, matriz);
		}
		else if(quadrante == 7) { // linhas 6, 7, 8
			coordenadas = retornaCoordenadasQuadrante(7, matriz);
		}
		else if(quadrante == 8) {
			coordenadas = retornaCoordenadasQuadrante(8, matriz);			
		}
		else if(quadrante == 9) {
			coordenadas = retornaCoordenadasQuadrante(9, matriz);			
		}

		linhaInicio = coordenadas[0];
		linhaFim = coordenadas[1];
		colunaInicio = coordenadas[2];
		colunaFim = coordenadas[3];

		for (int i = linhaInicio; i <= linhaFim; i++) {
			for (int j = colunaInicio; j <= colunaFim; j++) {
				if(matriz[i][j] == numero) {
					achou = 1;
					break;
				}
			}
		}
		
		return achou;
	}

	public static int[] retornaCoordenadasQuadrante(int quadrante, int[][] matriz) {
		int[] coordenadas = new int[4];
		
		// linhas 0, 1, 2 
		if(quadrante == 1) {
			coordenadas[0] = 0;
			coordenadas[1] = 2;
			coordenadas[2] = 0;
			coordenadas[3] = 2;
		} 
		else if(quadrante == 2) {
			coordenadas[0] = 0;
			coordenadas[1] = 2;
			coordenadas[2] = 3;
			coordenadas[3] = 5;
		}
		else if(quadrante == 3) {
			coordenadas[0] = 0;
			coordenadas[1] = 2;
			coordenadas[2] = 6;
			coordenadas[3] = 8;
		}
		// linhas 3, 4, 5 
		else if(quadrante == 4) {
			coordenadas[0] = 3;
			coordenadas[1] = 5;
			coordenadas[2] = 0;
			coordenadas[3] = 2;
		}
		else if(quadrante == 5) {
			coordenadas[0] = 3;
			coordenadas[1] = 5;
			coordenadas[2] = 3;
			coordenadas[3] = 5;
		}
		else if(quadrante == 6) {
			coordenadas[0] = 3;
			coordenadas[1] = 5;
			coordenadas[2] = 6;
			coordenadas[3] = 8;
		}
		// linhas 6, 7, 8 
		else if(quadrante == 7) {
			coordenadas[0] = 6;
			coordenadas[1] = 8;
			coordenadas[2] = 0;
			coordenadas[3] = 2;
		}
		else if(quadrante == 8) {
			coordenadas[0] = 6;
			coordenadas[1] = 8;
			coordenadas[2] = 3;
			coordenadas[3] = 5;
		}
		else if(quadrante == 9) {
			coordenadas[0] = 6;
			coordenadas[1] = 8;
			coordenadas[2] = 6;
			coordenadas[3] = 8;
		}
		
		return coordenadas;		
	}
		
	public static int qualLinhaNumeroEstaNoQuadrante(int numeroAnalisado, int quadrante, int[][] matriz) {
		int linha = -1;
		int[] coordenadas = retornaCoordenadasQuadrante(quadrante, matriz);
		int linhaInicio = coordenadas[0];
		int linhaFim = coordenadas[1];
		int colunaInicio = coordenadas[2];
		int colunaFim = coordenadas[3];

		for (int i = linhaInicio; i <= linhaFim; i++) {
			for (int j = colunaInicio; j <= colunaFim; j++) {
				if(matriz[i][j] == numeroAnalisado) {
					linha = i;
					break;
				}
			}
		}
		
		return linha;		
	}
	
	public static int qtdCelulasPreenchidasNasLinhasNaColuna(List<Integer> linhasQuadrante, int coluna, int[][] matriz) {
		int qtd = 0;
		if(matriz[linhasQuadrante.get(0)][coluna] != 0) qtd++;
		if(matriz[linhasQuadrante.get(1)][coluna] != 0) qtd++;
		if(matriz[linhasQuadrante.get(2)][coluna] != 0) qtd++;
		return qtd;
	}

	public static int qtdCelulasPreenchidasNasColunasNaLinha(List<Integer> colunasQuadrante, int linha, int[][] matriz) {
		int qtd = 0;
		if(matriz[linha][colunasQuadrante.get(0)] != 0) qtd++;
		if(matriz[linha][colunasQuadrante.get(1)] != 0) qtd++;
		if(matriz[linha][colunasQuadrante.get(2)] != 0) qtd++;
		return qtd;
	}

	public static List<Integer> qtdPossibilidadesCelula(int linha, int coluna, int[][] matriz) {
		Set<Integer> listaElementos = new HashSet<>();
		List<Integer> elementosRetorno = new ArrayList<>();
		
		// pega elementos da linha 
		listaElementos.addAll(pegaElementosLinha(linha, coluna, matriz));
		
		// pega elementos da coluna
		listaElementos.addAll(pegaElementosColuna(linha, coluna, matriz));
		
		// pega elementos do quadrante
		listaElementos.addAll(pegaElementosQuadrante(linha, coluna, matriz));
		
        // Creating an iterator 
        Iterator<Integer> numeros = listaElementos.iterator(); 
  
        // Displaying the values after iterating through the iterator 
        while (numeros.hasNext()) { 
        	elementosRetorno.add(numeros.next());
        } 
        
        Collections.sort(elementosRetorno);

		List<Integer> possibs = SudokuUtil.retornaNumerosPossiveis(matriz).stream()
				.distinct().
				filter(aObject -> !elementosRetorno.contains(aObject)).
				collect(Collectors.toList());
		 
		 return possibs;
	}
	
	public static Set<Integer> pegaElementosLinha(int linha, int coluna, int[][] matriz) {
		Set<Integer> listaElementos = new HashSet<>();
		
		for (int j = 0; j < matriz[linha].length; j++) {
			if(matriz[linha][j] != 0) {
				listaElementos.add(matriz[linha][j]);
			}
		}
		return listaElementos;
	}

	public static Set<Integer> pegaElementosColuna(int linha, int coluna, int[][] matriz) {
		Set<Integer> listaElementos = new HashSet<>();
		
		for (int i = 0; i < matriz.length; i++) {
			if(matriz[i][coluna] != 0) {
				listaElementos.add(matriz[i][coluna]);
			}
		}
		
		return listaElementos;
	}
	
	public static Set<Integer> pegaElementosQuadrante(int linha, int coluna, int[][] matriz) {
		Set<Integer> conjuntoElementos = new HashSet<>();
		
		if(matriz.length == 4 && matriz[0].length == 4) {
			conjuntoElementos = pegaElementosQuadrante4X4(linha, coluna, matriz);
			
		} else if(matriz.length == 9 && matriz[0].length == 9) {
			conjuntoElementos = pegaElementosQuadrante9X9(linha, coluna, matriz);
		}
		
		return conjuntoElementos;
	}
	
	public static Set<Integer> pegaElementosQuadrante4X4(int linha, int coluna, int[][] matriz) {
		Set<Integer> listaElementos = new HashSet<>();
		
		int linhaInicio = 0;
		int linhaFim = 0;
		int colunaInicio = 0;
		int colunaFim = 0;
		
		if(linha < 2) {
			if(coluna < 2) {
				linhaInicio = 0;
				linhaFim = 2;
				colunaInicio = 0;
				colunaFim = 2;
			} else {
				linhaInicio = 0;
				linhaFim = 2;
				colunaInicio = 2;
				colunaFim = 4;
			}
			
		} else {

			if(coluna < 2) {
				linhaInicio = 2;
				linhaFim = 4;
				colunaInicio = 0;
				colunaFim = 2;
			} else {
				linhaInicio = 2;
				linhaFim = 4;
				colunaInicio = 2;
				colunaFim = 4;
			}
		}
		for (int i = linhaInicio; i < linhaFim; i++) {
			for (int j = colunaInicio; j < colunaFim; j++) {
				if(i != linha && j != coluna && matriz[i][j] != 0) {
					listaElementos.add(matriz[i][j]);
				}
			}
		}
		
		return listaElementos;
	}
	
	public static Set<Integer> pegaElementosQuadrante9X9(int linha, int coluna, int[][] matriz) {
		List<Integer> elementos = null;
		Set<Integer> resultado = new HashSet<>();
		
		if(linha >= 0 && linha <= 2) {
			
			if(coluna >= 0 && coluna <= 2) {
				elementos = SudokuUtil.retornaElementosQuadrante(1, matriz);
			} else if(coluna >= 3 && coluna <= 5) {
				elementos = SudokuUtil.retornaElementosQuadrante(2, matriz);
			} else if(coluna >= 6 && coluna <= 8) {
				elementos = SudokuUtil.retornaElementosQuadrante(3, matriz);
			}
			
		} else if(linha >= 3 && linha <= 5) {
			
			if(coluna >= 0 && coluna <= 2) {
				elementos = SudokuUtil.retornaElementosQuadrante(4, matriz);
			} else if(coluna >= 3 && coluna <= 5) {
				elementos = SudokuUtil.retornaElementosQuadrante(5, matriz);
			} else if(coluna >= 6 && coluna <= 8) {
				elementos = SudokuUtil.retornaElementosQuadrante(6, matriz);
			}
			
		} else if(linha >= 6 && linha <= 8) {
			
			if(coluna >= 0 && coluna <= 2) {
				elementos = SudokuUtil.retornaElementosQuadrante(7, matriz);
			} else if(coluna >= 3 && coluna <= 5) {
				elementos = SudokuUtil.retornaElementosQuadrante(8, matriz);
			} else if(coluna >= 6 && coluna <= 8) {
				elementos = SudokuUtil.retornaElementosQuadrante(9, matriz);
			}
			
		}
		
		elementos.forEach(elem->resultado.add(elem));
		
		return resultado;
	}
	
	public static Set<Integer> OLDpegaElementosQuadrante9X9OLD(int linha, int coluna, int[][] matriz) {
		Set<Integer> listaElementos = new HashSet<>();
		int linhaInicio = 0;
		int linhaFim = 0;
		int colunaInicio = 0;
		int colunaFim = 0;
		
		if(linha < 3) {
			linhaInicio = 0;
			linhaFim = 3;

			if(coluna < 3) {
				colunaInicio = 0;
				colunaFim = 3;
			} else if(coluna > 3 && coluna < 6) {
				colunaInicio = 3;
				colunaFim = 6;
			} else {
				colunaInicio = 6;
				colunaFim = 9;
			}
			
		} else if(linha >= 3 && linha < 6) {

			linhaInicio = 3;
			linhaFim = 6;

			if(coluna < 3) {
				colunaInicio = 0;
				colunaFim = 3;
			} else if(coluna > 3 && coluna < 6) {
				colunaInicio = 3;
				colunaFim = 6;
			} else if(coluna > 6 && coluna < 9) {
				colunaInicio = 6;
				colunaFim = 9;
			}

		} else if(linha >= 6 && linha < 9) {
			
			linhaInicio = 6;
			linhaFim = 9;

			if(coluna < 3) {
				colunaInicio = 0;
				colunaFim = 3;
			} else if(coluna > 3 && coluna < 6) {
				colunaInicio = 3;
				colunaFim = 6;
			} else if(coluna > 6 && coluna < 9) {
				colunaInicio = 6;
				colunaFim = 9;
			}
		}
		
		for (int i = linhaInicio; i < linhaFim; i++) {
			for (int j = colunaInicio; j < colunaFim; j++) {
				if(i != linha && j != coluna && matriz[i][j] != 0) {
					listaElementos.add(matriz[i][j]);
				}
			}
		}
		
		return listaElementos;
	}

	public static List<Integer> retornaElementosQuadrante(int quadrante, int[][] matriz) {
		List<Integer> listaElementos = new ArrayList<>();
		
		int linhaInicio = 0;
		int linhaFim = 0;
		int colunaInicio = 0;
		int colunaFim = 0;
		int[] coordenadas = null;
		
		if(quadrante == 1 ) {
			coordenadas = retornaCoordenadasQuadrante(1, matriz);
		}
		else if(quadrante == 2 ) {
			coordenadas = retornaCoordenadasQuadrante(2, matriz);
		}
		else if(quadrante == 3 ) {
			coordenadas = retornaCoordenadasQuadrante(3, matriz);
		}
		else if(quadrante == 4 ) {
			coordenadas = retornaCoordenadasQuadrante(4, matriz);
		}
		else if(quadrante == 5 ) {
			coordenadas = retornaCoordenadasQuadrante(5, matriz);
		}
		else if(quadrante == 6 ) {
			coordenadas = retornaCoordenadasQuadrante(6, matriz);
		}
		else if(quadrante == 7 ) {
			coordenadas = retornaCoordenadasQuadrante(7, matriz);
		}
		else if(quadrante == 8 ) {
			coordenadas = retornaCoordenadasQuadrante(8, matriz);
		}
		else if(quadrante == 9 ) {
			coordenadas = retornaCoordenadasQuadrante(9, matriz);
		}
		
		linhaInicio = coordenadas[0];
		linhaFim = coordenadas[1];
		colunaInicio = coordenadas[2];
		colunaFim = coordenadas[3];
		
		for (int i = linhaInicio; i <= linhaFim; i++) {
			for (int j = colunaInicio; j <= colunaFim; j++) {
				listaElementos.add(matriz[i][j]);
			}
		}
		
		return listaElementos;
	}
	
	public static int qualColunaNumeroEstaNoQuadrante(int numeroAnalisado, int quadrante, int[][] matriz) {
		int coluna = -1;
		int linhaInicio = -1;
		int linhaFim = -1;
		int colunaInicio = -1;
		int colunaFim = -1;
		int[] coordenadas = null;
		
		// linhas 0, 1, 2 
		if(quadrante == 1) {
			coordenadas = retornaCoordenadasQuadrante(1, matriz);
		} 
		else if(quadrante == 2) {
			coordenadas = retornaCoordenadasQuadrante(2, matriz);
		}
		else if(quadrante == 3) {
			coordenadas = retornaCoordenadasQuadrante(3, matriz);
		}
		// linhas 3, 4, 5 
		else if(quadrante == 4) {
			coordenadas = retornaCoordenadasQuadrante(4, matriz);
		}
		else if(quadrante == 5) {
			coordenadas = retornaCoordenadasQuadrante(5, matriz);
		}
		else if(quadrante == 6) {
			coordenadas = retornaCoordenadasQuadrante(6, matriz);
		}
		// linhas 6, 7, 8 
		else if(quadrante == 7) {
			coordenadas = retornaCoordenadasQuadrante(7, matriz);
		}
		else if(quadrante == 8) {
			coordenadas = retornaCoordenadasQuadrante(8, matriz);
		}
		else if(quadrante == 9) {
			coordenadas = retornaCoordenadasQuadrante(9, matriz);
		}

		linhaInicio = coordenadas[0];
		linhaFim = coordenadas[1];
		colunaInicio = coordenadas[2];
		colunaFim = coordenadas[3];

		for (int i = linhaInicio; i <= linhaFim; i++) {
			for (int j = colunaInicio; j <= colunaFim; j++) {
				if(matriz[i][j] == numeroAnalisado) {
					coluna = j;
					break;
				}
			}
		}
		
		return coluna;		
	}
	
	public static int retornaColunaVaziaNaLinhaNoQuadrante(int linha, List<Integer> linhasQuadrante, int[][] matriz) {
		int colunaVazia = -1;
		if(matriz[linha][linhasQuadrante.get(0)] == 0) colunaVazia = linhasQuadrante.get(0);  	
		if(matriz[linha][linhasQuadrante.get(1)] == 0) colunaVazia = linhasQuadrante.get(1);  	
		if(matriz[linha][linhasQuadrante.get(2)] == 0) colunaVazia = linhasQuadrante.get(2);  	
		return colunaVazia;
	}

	public static int existeNumeroNaLinha(int numero, int linha, int[][] matriz) {
		int retorno = 0;
		for (int j = 0; j < matriz.length; j++) {
			if(matriz[linha][j] == numero) {
				retorno = 1;
				break;
			}
		}
		return retorno;
	}	
	
	public static int existeNumeroNaColuna(int numero, int coluna, int[][] matriz) {
		int retorno = 0;
		for (int i = 0; i < matriz.length; i++) {
			if(matriz[i][coluna] == numero) {
				retorno = 1;
				break;
			}
		}
		return retorno;
	}
	
	public static int retornaLinhaVaziaNaColunaNoQuadrante(int coluna, List<Integer> linhasQuadrante, int[][] matriz) {
		int linhaVazia = -1;
		if(matriz[linhasQuadrante.get(0)][coluna] == 0) linhaVazia = linhasQuadrante.get(0);  	
		if(matriz[linhasQuadrante.get(1)][coluna] == 0) linhaVazia = linhasQuadrante.get(1);  	
		if(matriz[linhasQuadrante.get(2)][coluna] == 0) linhaVazia = linhasQuadrante.get(2);  	
		return linhaVazia;
	}

	public static void setValorNaLinhaColuna(int valor, int linha, int coluna, int[][] matriz, String regraId) throws Exception {
		if(matriz[linha][coluna] == 0) {
			System.out.println("|>>>>>>>>>>  Setando valor => ("+linha+","+coluna+")="+valor+" Regra="+regraId+" ]");
			matriz[linha][coluna] = valor;	
			inferencias++;
			imprimeMatriz(matriz);
		} else {
			String msg = "\nSudoku: Não é possível setar numero em celula preenchida.  " +
					" | => ("+linha+","+coluna+")="+valor+" Regra="+regraId+" ]";
			throw new Exception("Sudoku Error::"+msg);
		}
	}

	public static boolean existeCelulaVazia(int[][] matriz) {
		boolean achouCelulaVazia = false;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if(matriz[i][j] == 0) {
					achouCelulaVazia = true;
					break;
				}
			}
		}
		return achouCelulaVazia;
	}
	
	public static void imprimeMatriz(int[][] matriz) {
		if(matriz.length == 4) {
			imprimeMatriz4X4(matriz);
		} else if(matriz.length == 9) {
			imprimeMatriz9X9(matriz);
		}
	}
	
	public static void imprimeMatriz9X9(int[][] matriz) {
		System.out.print("=================================\n");
		for (int i = 0; i < matriz.length; i++) {
			
			if(i != 3 && i != 6) {
				System.out.print(i+" [");
			} else if(i == 3 || i == 6) {
				System.out.println("   ------------------------");
				System.out.print(i+" [");
			}
			for (int j = 0; j < matriz[i].length; j++) {
				
				if(j != matriz[i].length-1) {
					
					if(matriz[i][j] != 0) {
						
						if(j != 3 && j != 6 ) {
							System.out.print(" "+matriz[i][j]);	
						} else if(j == 3 || j == 6) {
							System.out.print(" | "+matriz[i][j]);
						}
							
					} else {
						
						if(j != 3 && j != 6 ) {
							System.out.print(" _");	
						} else if(j == 3 || j == 6) {
							System.out.print(" | _");
						}
						
					}
					
				}
				else {
					
					if(matriz[i][j] != 0) {
						System.out.println(" "+matriz[i][j]+" ]");	

					} else {
						System.out.println(" _ ]");
					}
				}
			}
		}
	}

	public static void imprimeMatrizPossibilidades9X9(int[][] matriz) {
		int qtdPossib = -1;
		List<Integer> possibs = null;
		List<Posicao> posicoes = new ArrayList<>();

		System.out.print("=================================\n");
		System.out.print("Matriz de Possibilidades         \n");
		System.out.print("=================================\n");

		for (int i = 0; i < matriz.length; i++) {
			
			if(i != 3 && i != 6) {
				System.out.print(i+" [");
			} else if(i == 3 || i == 6) {
				System.out.println("   ------------------------");
				System.out.print(i+" [");
			}
			posicoes.clear();
			for (int j = 0; j < matriz[i].length; j++) {
				
				possibs = SudokuUtil.qtdPossibilidadesCelula(i, j, matriz);
				qtdPossib = possibs.size();
				if(matriz[i][j] == 0 && qtdPossib == 1) {
					posicoes.add(new Posicao(i, j, possibs.get(0)));
				}
				if(j != matriz[i].length-1) {
					
					if(matriz[i][j] == 0) {
						
						
						if(j != 3 && j != 6 ) {
							System.out.print(" "+qtdPossib+"");	
						} else if(j == 3 || j == 6) {
							System.out.print(" | "+qtdPossib+"");
						}
							
					} else {
						
						if(j != 3 && j != 6 ) {
							System.out.print(" _");	
						} else if(j == 3 || j == 6) {
							System.out.print(" | _");
						}
						
					}
					
				}
				else {
					
					if(matriz[i][j] == 0) {
						System.out.println(" "+qtdPossib+" ]");	

					} else {
						System.out.println(" _ ]");
					}
				}
			}

		}
		
		if(posicoes.size() >= 1) {
			System.out.println("======================================");
			System.out.println(" Células com 01 possibilidade         ");
			System.out.println("======================================\n");
			posicoes.forEach(pos->System.out.println("("+pos.getX()+","+pos.getY()+")="+pos.getValor()));
		}
		
		System.out.println("======================================");
		System.out.println(" Células com 02 possibilidades        ");
		//
		possibs.clear();
		int contador = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if(matriz[i][j] == 0) {
					possibs = SudokuUtil.qtdPossibilidadesCelula(i, j, matriz);
					if(possibs.size() == 2) {
						System.out.println("("+i+","+j+") => ["+possibs+"]");
						contador++;
					}
				}
			}
		}
		System.out.println(" Células com 02 possibilidades = "+contador);
		System.out.println("======================================");
	}

	public static void imprimeMatriz4X4(int[][] matriz) {
		System.out.print("=================================\n");
		for (int i = 0; i < matriz.length; i++) {
			
			if(i != 2 ) {
				System.out.print(i+" [");
			} 
			else if(i == 2) {
				System.out.println("  -------------");
				System.out.print(i+" [");
			}
			for (int j = 0; j < matriz[i].length; j++) {
				
				if(j != matriz[i].length-1) {
					
					if(matriz[i][j] != 0) {
						
						if(j != 2 ) {
							System.out.print(" "+matriz[i][j]);	
						} else if(j == 2) {
							System.out.print(" | "+matriz[i][j]);
						}
							
					} else {
						
						if(j != 2 ) {
							System.out.print(" _");	
						} else if(j == 2) {
							System.out.print(" | _");
						}
						
					}
					
				}
				else {
					
					if(matriz[i][j] != 0) {
						System.out.println(" "+matriz[i][j]+" ]");	

					} else {
						System.out.println(" _ ]");
					}
				}
			}
		}
	}
	
	public static boolean existeCelulaQtdPossib(int qtdPossibs, int[][] matriz) {
		boolean achouCelulaQtdPossibs = false;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if( (matriz[i][j] == 0) &&  (SudokuUtil.qtdPossibilidadesCelula(i, j, matriz).size() == qtdPossibs) ) {
					achouCelulaQtdPossibs = true;
					break;
				}
			}
		}
		return achouCelulaQtdPossibs;
		
	}
	public static boolean existeCelula01Possib(int[][] matriz) {
		return existeCelulaQtdPossib(1, matriz);
	}

	public static boolean existeColuna01PosicaoRestante(int coluna, int[][] matriz) {
		return existeColunaQtdPosicoesRestantes(coluna, 1, matriz);
	}

	public static boolean existeLinha01PosicaoRestante(int linha, int[][] matriz) {
		return existeLinhaQtdPosicoesRestantes(linha, 1, matriz);
	}

	public static boolean existeLinha02PosicoesRestantes(int linha, int[][] matriz) {
		return existeLinhaQtdPosicoesRestantes(linha, 2, matriz);
	}

	public static boolean existeColunaQtdPosicoesRestantes(int coluna, int qtdPosicoes, int[][] matriz) {
		boolean achouColunaQtdrestantes = false;
		int contador = 0;
		for (int i = 0; i < matriz.length; i++) {
				if( (matriz[i][coluna] == 0) ) {
					contador++;
				}
		}
		if(contador == qtdPosicoes) {
			achouColunaQtdrestantes = true;
		}
		return achouColunaQtdrestantes;
	}
	
	public static boolean existeColuna02PosicoesRestantes(int coluna, int[][] matriz) {
		return existeColunaQtdPosicoesRestantes(coluna, 2, matriz);
	}
	
	public static boolean existeQuadranteQtdPosicoesRestantes(int qtdPosicoes, int[][] matriz) {
		boolean achouQuadranteQtdPosicoesRestantes = false;
		int[] restantes = retornaElementosRestantesQuadrante(matriz);
		if(restantes[0] == qtdPosicoes || restantes[1] == qtdPosicoes || restantes[2] == qtdPosicoes ||
		   restantes[3] == qtdPosicoes || restantes[4] == qtdPosicoes || restantes[5] == qtdPosicoes ||
		   restantes[6] == qtdPosicoes || restantes[7] == qtdPosicoes || restantes[8] == qtdPosicoes) {
			
			achouQuadranteQtdPosicoesRestantes = true;
		}
		return achouQuadranteQtdPosicoesRestantes;
	}

	public static List<Integer> retornaColunasQtdPosicoesRestantes(int qtdPosicoes, int[][] matriz) {
		List<Integer> listaColunas = new ArrayList<>();
		int contador = 0;
		for (int j = 0; j < matriz.length; j++) {
			listaColunas.clear();
			contador = 0;
			for (int i = 0; i < matriz.length; i++) {
				if(matriz[i][j] == 0) {
					contador++;
				}
			}
			if(contador == qtdPosicoes) {
				listaColunas.add(j);
			}
		}
		return listaColunas;
	}
	
	public static List<Integer> retornaColunas03PosicoesRestantes(int[][] matriz) {
		return retornaColunasQtdPosicoesRestantes(3, matriz);
	}
	
	public static boolean existeQuadrante01PosicaoRestante(int[][] matriz) {
		return existeQuadranteQtdPosicoesRestantes(1, matriz);
	}

	public static boolean existeQuadrante02PosicoesRestantes(int[][] matriz) {
		return existeQuadranteQtdPosicoesRestantes(2, matriz);
	}
	
	public static void exibirMsg(String msg, String mode) {
		if("DEBUG".equals(mode)) {
			System.out.println(msg);
		}
	}
	
	public static int[] retornaElementosRestantesQuadrante(int[][] matriz) {
		int[] retornos = new int[9];
		// quadrantes 1,2,3 
		List<Integer> elementosQuad1 = retornaElementosQuadrante(1, matriz);
		List<Integer> elementosQuad2 = retornaElementosQuadrante(2, matriz);
		List<Integer> elementosQuad3 = retornaElementosQuadrante(3, matriz);

		// quadrantes 4,5,6
		List<Integer> elementosQuad4 = retornaElementosQuadrante(4, matriz);
		List<Integer> elementosQuad5 = retornaElementosQuadrante(5, matriz);
		List<Integer> elementosQuad6 = retornaElementosQuadrante(6, matriz);
		// quadrantes 7,8,9
		List<Integer> elementosQuad7 = retornaElementosQuadrante(7, matriz);
		List<Integer> elementosQuad8 = retornaElementosQuadrante(8, matriz);
		List<Integer> elementosQuad9 = retornaElementosQuadrante(9, matriz);
		
		List<Integer> numerosPossiveis = SudokuUtil.retornaNumerosPossiveis(matriz);
		retornos[0] = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad1.contains(aObject)).
				collect(Collectors.toList()).size();
		retornos[1] = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad2.contains(aObject)).
				collect(Collectors.toList()).size();
		retornos[2] = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad3.contains(aObject)).
				collect(Collectors.toList()).size();
		retornos[3] = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad4.contains(aObject)).
				collect(Collectors.toList()).size();
		retornos[4] = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad5.contains(aObject)).
				collect(Collectors.toList()).size();
		retornos[5] = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad6.contains(aObject)).
				collect(Collectors.toList()).size();
		retornos[6] = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad7.contains(aObject)).
				collect(Collectors.toList()).size();
		retornos[7] = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad8.contains(aObject)).
				collect(Collectors.toList()).size();
		retornos[8] = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad9.contains(aObject)).
				collect(Collectors.toList()).size();
		
		return retornos;
	}
	
	public static Posicao retornaQuadrante01PosicaoRestante(int[][] matriz) {
		// quadrantes 1,2,3 
		List<Integer> elementosQuad1 = retornaElementosQuadrante(1, matriz);
		List<Integer> elementosQuad2 = retornaElementosQuadrante(2, matriz);
		List<Integer> elementosQuad3 = retornaElementosQuadrante(3, matriz);

		// quadrantes 4,5,6
		List<Integer> elementosQuad4 = retornaElementosQuadrante(4, matriz);
		List<Integer> elementosQuad5 = retornaElementosQuadrante(5, matriz);
		List<Integer> elementosQuad6 = retornaElementosQuadrante(6, matriz);
		// quadrantes 7,8,9
		List<Integer> elementosQuad7 = retornaElementosQuadrante(7, matriz);
		List<Integer> elementosQuad8 = retornaElementosQuadrante(8, matriz);
		List<Integer> elementosQuad9 = retornaElementosQuadrante(9, matriz);
		
		List<Integer> numerosPossiveis = SudokuUtil.retornaNumerosPossiveis(matriz);

		//
		int numeroSetar = -1;
		int linhaSetar = -1;
		int colunaSetar = -1;
		int linhaInicio = 0;
		int linhaFim = 0;
		int colunaInicio = 0;
		int colunaFim = 0;
		int[] coordenadas = null;
		int[] restantes = retornaElementosRestantesQuadrante(matriz);
		
		if(restantes[0] == 1 || restantes[1] == 1 || restantes[2] == 1 ||
				restantes[3] == 1 || restantes[4] == 1 || restantes[5] == 1 ||
						restantes[6] == 1 || restantes[7] == 1 || restantes[8] == 1) {
			
			if(restantes[0] == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad1.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				coordenadas = retornaCoordenadasQuadrante(1, matriz);
			}
			else if(restantes[1] == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad2.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				coordenadas = retornaCoordenadasQuadrante(2, matriz);
			}
			else if(restantes[2] == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad3.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				coordenadas = retornaCoordenadasQuadrante(3, matriz);
			}
			else if(restantes[3] == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad4.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				coordenadas = retornaCoordenadasQuadrante(4, matriz);
			}
			else if(restantes[4] == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad5.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				coordenadas = retornaCoordenadasQuadrante(5, matriz);
			}
			else if(restantes[5] == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad6.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				coordenadas = retornaCoordenadasQuadrante(6, matriz);
			}
			else if(restantes[6] == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad7.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				coordenadas = retornaCoordenadasQuadrante(7, matriz);
			}
			else if(restantes[7] == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad8.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				coordenadas = retornaCoordenadasQuadrante(8, matriz);
			}
			else if(restantes[8] == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad9.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				coordenadas = retornaCoordenadasQuadrante(9, matriz);
			}
			
			linhaInicio = coordenadas[0];
			linhaFim = coordenadas[1];
			colunaInicio = coordenadas[2];
			colunaFim = coordenadas[3];

			for (int i = linhaInicio; i <= linhaFim; i++) {
				for (int j = colunaInicio; j <= colunaFim; j++) {
					if(matriz[i][j] == 0) {
						linhaSetar = i;
						colunaSetar = j;
						break;
					}
				}
			}			
		}
		
		if(linhaSetar != -1 && colunaSetar != -1 && numeroSetar != -1) {
			return new Posicao(linhaSetar, colunaSetar, numeroSetar);	
		} else {
			return null;
		}
		
	}

	public static List<Integer> retornaNumerosPossiveis(int[][] matriz) {
	    Integer[] arrayNumerosPossiveis = null;
		if(matriz.length == 4) {
			arrayNumerosPossiveis = new Integer[] { 1, 2, 3, 4 };
		} else if(matriz.length == 9) {
			arrayNumerosPossiveis = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		}

		return Arrays.asList(arrayNumerosPossiveis);
	}

	// TODO
	public static void resolveColuna03PosicoesRestantes(int[][] matriz, int coluna) throws Exception {
		List<Integer> numerosPossiveis = SudokuUtil.retornaNumerosPossiveis(matriz);
		List<Integer> numerosEncontradosColuna = new ArrayList<>();
		List<Integer> linhasEmBranco = new ArrayList<>();
		for (int i = 0; i < matriz.length; i++) {
			if(matriz[i][coluna] != 0) {
				numerosEncontradosColuna.add(matriz[i][coluna]);
			}
			if(matriz[i][coluna] == 0) {
				linhasEmBranco.add(i);
			}
		}
		List<Integer> numerosRestantesColuna = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !numerosEncontradosColuna.contains(aObject)).
				collect(Collectors.toList());
		
		int existeNumLinhaRestante01 = -1;
		int existeNumLinhaRestante02 = -1;
		int existeNumLinhaRestante03 = -1;
		int linhaSetar = -1;
		for (Integer numero : numerosRestantesColuna) {
			existeNumLinhaRestante01 = SudokuUtil.existeNumeroNaLinha(numero, linhasEmBranco.get(0), matriz);
			existeNumLinhaRestante02 = SudokuUtil.existeNumeroNaLinha(numero, linhasEmBranco.get(1), matriz);
			existeNumLinhaRestante03 = SudokuUtil.existeNumeroNaLinha(numero, linhasEmBranco.get(2), matriz);
			
			if ((existeNumLinhaRestante01+existeNumLinhaRestante02+existeNumLinhaRestante03 == 2)) {
				// Achou a condição em que o numero só tem uma linha restante possível
				
				if(SudokuUtil.existeNumeroNaLinha(numero, linhasEmBranco.get(0), matriz) == 0) {
					linhaSetar = linhasEmBranco.get(0);
					
				} else if(SudokuUtil.existeNumeroNaLinha(numero, linhasEmBranco.get(1), matriz) == 0) {
					linhaSetar = linhasEmBranco.get(1);
					
				} else if(SudokuUtil.existeNumeroNaLinha(numero, linhasEmBranco.get(2), matriz) == 0) {
					linhaSetar = linhasEmBranco.get(2);
				}
				// Achou onde estava faltando
				SudokuUtil.setValorNaLinhaColuna(numero, linhaSetar, coluna, matriz, "REGRA12_03_Posicoes");
			}
		}
	}

	public static void resolveColuna02PosicoesRestantes(int[][] matriz, int coluna) throws Exception {
		List<Integer> numerosPossiveis = SudokuUtil.retornaNumerosPossiveis(matriz);
		List<Integer> numerosEncontradosColuna = new ArrayList<>();
		List<Integer> linhasEmBranco = new ArrayList<>();
		for (int i = 0; i < matriz.length; i++) {
			if(matriz[i][coluna] != 0) {
				numerosEncontradosColuna.add(matriz[i][coluna]);
			}
			if(matriz[i][coluna] == 0) {
				linhasEmBranco.add(i);
			}
		}
		List<Integer> numerosRestantesColuna = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !numerosEncontradosColuna.contains(aObject)).
				collect(Collectors.toList());
		
		int existeNumLinhaRestante01 = -1;
		int existeNumLinhaRestante02 = -1;
		int linhaSetar = -1;
		for (Integer numero : numerosRestantesColuna) {
			existeNumLinhaRestante01 = SudokuUtil.existeNumeroNaLinha(numero, linhasEmBranco.get(0), matriz);
			existeNumLinhaRestante02 = SudokuUtil.existeNumeroNaLinha(numero, linhasEmBranco.get(1), matriz);
			
			if ((existeNumLinhaRestante01+existeNumLinhaRestante02 == 1)) {
				// Achou a condição em que o numero só tem uma linha restante possível
				
				if(SudokuUtil.existeNumeroNaLinha(numero, linhasEmBranco.get(0), matriz) == 0) {
					linhaSetar = linhasEmBranco.get(0);
					
				} else if(SudokuUtil.existeNumeroNaLinha(numero, linhasEmBranco.get(1), matriz) == 0) {
					linhaSetar = linhasEmBranco.get(1);
					
				} 

				// Achou onde estava faltando
				SudokuUtil.setValorNaLinhaColuna(numero, linhaSetar, coluna, matriz, "REGRA12_02_Posicoes");
			}
		}

	}
	
	// TODO Reduzir de 32 para 15
	public static void resolveQuadrante02PosicoesRestantes(int[][] matriz) throws Exception {
		// quadrantes 1,2,3 
		List<Integer> elementosQuad1 = retornaElementosQuadrante(1, matriz);
		List<Integer> elementosQuad2 = retornaElementosQuadrante(2, matriz);
		List<Integer> elementosQuad3 = retornaElementosQuadrante(3, matriz);

		// quadrantes 4,5,6
		List<Integer> elementosQuad4 = retornaElementosQuadrante(4, matriz);
		List<Integer> elementosQuad5 = retornaElementosQuadrante(5, matriz);
		List<Integer> elementosQuad6 = retornaElementosQuadrante(6, matriz);
		// quadrantes 7,8,9
		List<Integer> elementosQuad7 = retornaElementosQuadrante(7, matriz);
		List<Integer> elementosQuad8 = retornaElementosQuadrante(8, matriz);
		List<Integer> elementosQuad9 = retornaElementosQuadrante(9, matriz);
		
		List<Integer> numerosPossiveis = SudokuUtil.retornaNumerosPossiveis(matriz);

		//
		int numeroAnalisado01 = -1;
		int numeroAnalisado02 = -1;
		int linhaNumeroAnalisado01 = -1;
		int linhaNumeroAnalisado02 = -1;
		int colunaNumeroAnalisado01 = -1;
		int colunaNumeroAnalisado02 = -1;
		int linhaInicio = 0;
		int linhaFim = 0;
		int colunaInicio = 0;
		int colunaFim = 0;
		int[] coordenadas = null;
		int[] restantes = retornaElementosRestantesQuadrante(matriz);
		
		if(restantes[0] == 2 || restantes[1] == 2 || restantes[2] == 2 ||
		   restantes[3] == 2 || restantes[4] == 2 || restantes[5] == 2 ||
		   restantes[6] == 2 || restantes[7] == 2 || restantes[8] == 2) {
			
			if(restantes[0] == 2) {
				numeroAnalisado01 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad1.contains(aObject)).
						collect(Collectors.toList()).get(0);

				numeroAnalisado02 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad1.contains(aObject)).
						collect(Collectors.toList()).get(1);
				
				coordenadas = retornaCoordenadasQuadrante(1, matriz);
			}
			else if(restantes[1] == 2) {
				numeroAnalisado01 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad2.contains(aObject)).
						collect(Collectors.toList()).get(0);
				numeroAnalisado02 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad2.contains(aObject)).
						collect(Collectors.toList()).get(1);
				
				coordenadas = retornaCoordenadasQuadrante(2, matriz);
			}
			else if(restantes[2] == 2) {
				numeroAnalisado01 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad3.contains(aObject)).
						collect(Collectors.toList()).get(0);
				numeroAnalisado02 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad3.contains(aObject)).
						collect(Collectors.toList()).get(1);

				coordenadas = retornaCoordenadasQuadrante(3, matriz);
			}
			else if(restantes[3] == 2) {
				numeroAnalisado01 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad4.contains(aObject)).
						collect(Collectors.toList()).get(0);
				numeroAnalisado02 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad4.contains(aObject)).
						collect(Collectors.toList()).get(1);

				coordenadas = retornaCoordenadasQuadrante(4, matriz);
			}
			else if(restantes[4] == 2) {
				numeroAnalisado01 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad5.contains(aObject)).
						collect(Collectors.toList()).get(0);
				numeroAnalisado02 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad5.contains(aObject)).
						collect(Collectors.toList()).get(1);

				coordenadas = retornaCoordenadasQuadrante(5, matriz);				
			}
			else if(restantes[5] == 2) {
				numeroAnalisado01 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad6.contains(aObject)).
						collect(Collectors.toList()).get(0);
				numeroAnalisado02 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad6.contains(aObject)).
						collect(Collectors.toList()).get(1);

				coordenadas = retornaCoordenadasQuadrante(6, matriz);						
			}
			else if(restantes[6] == 2) {
				numeroAnalisado01 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad7.contains(aObject)).
						collect(Collectors.toList()).get(0);
				numeroAnalisado02 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad7.contains(aObject)).
						collect(Collectors.toList()).get(1);

				coordenadas = retornaCoordenadasQuadrante(7, matriz);				
			}
			else if(restantes[7] == 2) {
				numeroAnalisado01 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad8.contains(aObject)).
						collect(Collectors.toList()).get(0);
				numeroAnalisado02 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad8.contains(aObject)).
						collect(Collectors.toList()).get(1);

				coordenadas = retornaCoordenadasQuadrante(8, matriz);				
			}
			else if(restantes[8] == 2) {
				numeroAnalisado01 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad9.contains(aObject)).
						collect(Collectors.toList()).get(0);
				numeroAnalisado02 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad9.contains(aObject)).
						collect(Collectors.toList()).get(1);

				coordenadas = retornaCoordenadasQuadrante(9, matriz);				
			}

			linhaInicio = coordenadas[0];
			linhaFim = coordenadas[1];
			colunaInicio = coordenadas[2];
			colunaFim = coordenadas[3];

			// Analisando numeroSetar01
			// Analisando numeroSetar01
			List<Posicao> posicoesVazias = new ArrayList<>();
			for (int i = linhaInicio; i <= linhaFim; i++) {
				for (int j = colunaInicio; j <= colunaFim; j++) {
					if(matriz[i][j] == 0) {
						posicoesVazias.add(new Posicao(i, j, 0));
					}
				}
			}	

			//
			if(!posicoesVazias.isEmpty() && posicoesVazias.size() == 2) {
				
				linhaNumeroAnalisado01 = posicoesVazias.get(0).getX();
				colunaNumeroAnalisado01 = posicoesVazias.get(0).getY();
				//
				linhaNumeroAnalisado02 = posicoesVazias.get(1).getX();
				colunaNumeroAnalisado02 = posicoesVazias.get(1).getY();
	
				if(SudokuUtil.existeNumeroNaLinha(numeroAnalisado01, linhaNumeroAnalisado01, matriz) == 1) {
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado01, linhaNumeroAnalisado02, colunaNumeroAnalisado02, matriz, "RG09");
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado02, linhaNumeroAnalisado01, colunaNumeroAnalisado01, matriz, "RG09");
				} else if(SudokuUtil.existeNumeroNaLinha(numeroAnalisado01, linhaNumeroAnalisado02, matriz) == 1) {
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado01, linhaNumeroAnalisado01, colunaNumeroAnalisado01, matriz, "RG09");
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado02, linhaNumeroAnalisado02, colunaNumeroAnalisado02, matriz, "RG09");
				}
				
				if(SudokuUtil.existeNumeroNaLinha(numeroAnalisado02, linhaNumeroAnalisado01, matriz) == 1) {
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado02, linhaNumeroAnalisado02, colunaNumeroAnalisado02, matriz, "RG09");
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado01, linhaNumeroAnalisado01, colunaNumeroAnalisado01, matriz, "RG09");
				} else if(SudokuUtil.existeNumeroNaLinha(numeroAnalisado02, linhaNumeroAnalisado02, matriz) == 1) {
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado01, linhaNumeroAnalisado02, colunaNumeroAnalisado02, matriz, "RG09");
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado02, linhaNumeroAnalisado01, colunaNumeroAnalisado01, matriz, "RG09");
				}
			}
		}
	}
	
	public static boolean existeLinhaQtdPosicoesRestantes(int linha, int qtdPosicoes, int[][] matriz) {
		boolean achouLinhaQtdrestantes = false;
		int contador = 0;
		for (int j = 0; j < matriz.length; j++) {
				if( (matriz[linha][j] == 0) ) {
					contador++;
				}
		}
		if(contador == qtdPosicoes) {
			achouLinhaQtdrestantes = true;
		}
		return achouLinhaQtdrestantes;
	}
	
	public static boolean existeLinha03PosicoesRestantes(int linha, int[][] matriz) {
		return existeLinhaQtdPosicoesRestantes(linha, 3, matriz);
	}
	
	public static Posicao posicaoColuna01PosicaoRestante(int coluna, int[][] matriz) {
		List<Integer> numerosEncontrados = new ArrayList<>();
		int linhaVazia = -1;
		for (int i = 0; i < matriz.length; i++) {
				if( matriz[i][coluna] != 0 ) {
					numerosEncontrados.add(matriz[i][coluna]);
				} else if( matriz[i][coluna] == 0 ) {
					linhaVazia = i;
				}
		}

		List<Integer> numerosPossiveis = SudokuUtil.retornaNumerosPossiveis(matriz);
		int valor = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !numerosEncontrados.contains(aObject)).
				collect(Collectors.toList()).get(0);
		
		return new Posicao(linhaVazia, coluna, valor);
	}

	public static Posicao posicaoLinha01PosicaoRestante(int linha, int[][] matriz) {
		List<Integer> numerosEncontrados = new ArrayList<>();
		int colunaVazia = -1;
		for (int j = 0; j < matriz.length; j++) {
				if( matriz[linha][j] != 0 ) {
					numerosEncontrados.add(matriz[linha][j]);
				} else if( matriz[linha][j] == 0 ) {
					colunaVazia = j;
				}
		}

		List<Integer> numerosPossiveis = SudokuUtil.retornaNumerosPossiveis(matriz);
		int valor = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !numerosEncontrados.contains(aObject)).
				collect(Collectors.toList()).get(0);
		
		return new Posicao(linha, colunaVazia, valor);
	}
	
	public static Posicao retornaCelula01Possib(int[][] matriz) {
		Posicao pos = null;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if( (matriz[i][j] == 0) && (SudokuUtil.qtdPossibilidadesCelula(i, j, matriz).size() == 1) ) {
					pos = new Posicao(i, j, SudokuUtil.qtdPossibilidadesCelula(i, j, matriz).get(0));
					break;
				}
			}
		}
		return pos;
	}
	
}
