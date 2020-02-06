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

	public static int infericoes = 0;
	public static Integer[] arrayNumerosPossiveis9X9 = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	public static Integer[] arrayNumerosPossiveis4X4 = new Integer[] { 1, 2, 3, 4 };
	
	private SudokuUtil() {
		//
	}

	public static int retornaQuadrantesVazios(int linhaQuadrante01, int linhaQuadrante02, int linhaQuadrante03) {
		int contadorQuadrantesVazios = 0;
		if(linhaQuadrante01 == -1) contadorQuadrantesVazios++;
		if(linhaQuadrante02 == -1) contadorQuadrantesVazios++;
		if(linhaQuadrante03 == -1) contadorQuadrantesVazios++;		
		
		return contadorQuadrantesVazios;
	}
	
	public static int celulasRestantesLinha(int linha, int[][] matriz) {
		int contador = 0;
		for (int j = 0; j < matriz.length; j++) {
			if(matriz[linha][j] == 0) {
				contador++;	
			}
		}
		return contador;
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
		return ++contador;
	}
	
	public static void linha01celulasRestantes(int linha, int[][] matriz) {
		if(celulasRestantesLinha(linha, matriz) == 1) {
			
		}
	}	

	public static int celulasRestantesColuna(int coluna, int[][] matriz) {
		int contador = 0;
		for (int i = 0; i < matriz.length; i++) {
			if(matriz[i][coluna] == 0) {
				contador++;	
			}
		}
		return contador;
	}
	
	public static List<Integer> retornaColunasQuadrante(int quadrante, int[][] matriz) {
		List<Integer> colunas = new ArrayList<>();
		 
		if(quadrante == 1 || quadrante == 4 || quadrante == 7) {
			colunas.add(0);colunas.add(1);colunas.add(2); // colunas 0, 1, 2
		} else if(quadrante == 2 || quadrante == 5 || quadrante == 8) {
			colunas.add(3);colunas.add(4);colunas.add(5); // colunas 3, 4, 5
		} else if(quadrante == 3 || quadrante == 6 || quadrante == 9) {
			colunas.add(6);colunas.add(7);colunas.add(8); // colunas 6, 7, 8
		}

		return colunas;
	}	

	public static List<Integer> retornaLinhasQuadrante(int quadrante, int[][] matriz) {
		List<Integer> linhas = new ArrayList<>();
		 
		if(quadrante == 1 || quadrante == 2 || quadrante == 3) {
			linhas.add(0);linhas.add(1);linhas.add(2); // linhas 0, 1, 2
		} else if(quadrante == 4 || quadrante == 5 || quadrante == 6) {
			linhas.add(3);linhas.add(4);linhas.add(5); // linhas 3, 4, 5
		} else if(quadrante == 7 || quadrante == 8 || quadrante == 9) {
			linhas.add(6);linhas.add(7);linhas.add(8); // linhas 6, 7, 8
		}

		return linhas;
	}	

	public static int existeNumeroQuadrante(int numero, int quadrante, int[][] matriz) {
		int achou = 0;
		int linhaInicio = -1;
		int linhaFim = -1;
		int colunaInicio = -1;
		int colunaFim = -1;
		
		int[] coordenadas = retornaCoordenadasQuadrante(quadrante, matriz);
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

	public static int qualLinhaNumeroEstaNoQuadrante(int numeroAnalisado, int quadrante, int[][] matriz) {
		int linha = -1;
		int linhaInicio = -1;
		int linhaFim = -1;
		int colunaInicio = -1;
		int colunaFim = -1;
		int[] coordenadas = retornaCoordenadasQuadrante(quadrante, matriz);
		
		linhaInicio = coordenadas[0];
		linhaFim = coordenadas[1];
		colunaInicio = coordenadas[2];
		colunaFim = coordenadas[3];
		
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
		return retornaNumerosPossiveis(matriz).stream()
				.distinct().
				filter(aObject -> !elementosRetorno.contains(aObject)).
				collect(Collectors.toList());
	}
	
	public static Set<Integer> pegaElementosLinha(int linha, int coluna, int[][] matriz) {
		Set<Integer> listaElementos = new HashSet<>();
		
 		for (int j = 0; j < matriz[0].length; j++) {
			if(j != coluna &&  matriz[linha][j] != 0) {
				listaElementos.add(matriz[linha][j]);
			}
		}
 		
		return listaElementos;
	}

	public static Set<Integer> pegaElementosColuna(int linha, int coluna, int[][] matriz) {
		Set<Integer> listaElementos = new HashSet<>();
		
		for (int i = 0; i < matriz.length; i++) {
			if(i != linha && matriz[i][coluna] != 0) {
				listaElementos.add(matriz[i][coluna]);
			}
		}
 		
		return listaElementos;
	}
	
	public static Set<Integer> pegaElementosQuadrante(int linha, int coluna, int[][] matriz) {
		Set<Integer> conjuntoElementos = new HashSet<>();
		
		if(matriz.length == 4) {
			conjuntoElementos = pegaElementosQuadrante4X4(linha, coluna, matriz);
		} else if(matriz.length == 9) {
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
	
	// TODO
	public static Set<Integer> pegaElementosQuadrante9X9(int linha, int coluna, int[][] matriz) {
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
		int[] coordenadas = retornaCoordenadasQuadrante(quadrante, matriz);

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
		int[] coordenadas = retornaCoordenadasQuadrante(quadrante, matriz);

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
	
	public static void setValorNaLinhaColuna(int valor, int linha, int coluna, int[][] matriz, String regraId) {

		if(matriz[linha][coluna] == 0) {
			System.out.println("|>>>>>>>>>>  Setando valor => ("+linha+","+coluna+")="+valor+" Regra="+regraId+" ]");
			matriz[linha][coluna] = valor;	
			infericoes++;
			imprimeMatriz(matriz);
		} else {
			System.out.print("\nSudoku: Não é possível setar numero em celula preenchida.  ");
			System.out.println(" | => ("+linha+","+coluna+")="+valor+" Regra="+regraId+" ]");
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
	
	public static void imprimeMatriz(int[][] matriz) {
		//
		if(matriz.length == 4) {
			imprimeMatriz4X4(matriz);
		}
		else if(matriz.length == 9) {
			imprimeMatriz9X9(matriz);
		}
	}
	
	public static void imprimeMatriz4X4(int[][] matriz) {
		System.out.print("==================\n");
		for (int i = 0; i < matriz.length; i++) {
			
			if(i != 2 ) {
				System.out.print(i+" [");
			} else if(i == 2) {
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

	public static boolean existeCelula01Possib(int[][] matriz) {
		boolean achouCelula01Possib = false;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if( (matriz[i][j] == 0) &&  (SudokuUtil.qtdPossibilidadesCelula(i, j, matriz).size() == 1) ) {
					achouCelula01Possib = true;
					break;
				}
			}
		}
		return achouCelula01Possib;
	}

	public static boolean existeColunaQtqPosicoesRestantes(int coluna, int qtdPosicoes, int[][] matriz) {
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

	public static boolean existeColuna01PosicaoRestante(int coluna, int[][] matriz) {
		return existeColunaQtqPosicoesRestantes(coluna, 1, matriz);
	}

	public static boolean existeColuna02PosicoesRestantes(int coluna, int[][] matriz) {
		return existeColunaQtqPosicoesRestantes(coluna, 2, matriz);
	}

	public static boolean existeLinhaQtdPosicoesRestantes(int linha, int qtdPosicoes, int[][] matriz) {
		boolean achouLinhaQtdrestante = false;
		int contador = 0;
		for (int j = 0; j < matriz.length; j++) {
				if( (matriz[linha][j] == 0) ) {
					contador++;
				}
		}
		if(contador == qtdPosicoes) {
			achouLinhaQtdrestante = true;
		}
		return achouLinhaQtdrestante;
	}
	
	public static boolean existeLinha01PosicaoRestante(int linha,  int[][] matriz) {
		return existeLinhaQtdPosicoesRestantes(linha, 1, matriz);
	}

	public static boolean existeLinha02PosicoesRestantes(int linha, int[][] matriz) {
		return existeLinhaQtdPosicoesRestantes(linha, 2, matriz);
	}
	
	public static boolean existeLinha03PosicoesRestantes(int linha, int[][] matriz) {
		return existeLinhaQtdPosicoesRestantes(linha, 3, matriz);	
	}

	public static boolean existeQuadrante01PosicaoRestante(int[][] matriz) {
		boolean achouQuadrante01restante = false;
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
		
		List<Integer> numerosPossiveis = retornaNumerosPossiveis(matriz);
		
		int restanteQuad1 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad1.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad2 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad2.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad3 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad3.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad4 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad4.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad5 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad5.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad6 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad6.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad7 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad7.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad8 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad8.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad9 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad9.contains(aObject)).
				collect(Collectors.toList()).size();
		
		if(restanteQuad1 == 1 || restanteQuad2 == 1 || restanteQuad3 == 1 ||
				restanteQuad4 == 1 || restanteQuad5 == 1 || restanteQuad6 == 1 ||
				restanteQuad7 == 1 || restanteQuad8 == 1 || restanteQuad9 == 1) {
			
			achouQuadrante01restante = true;
		}
		return achouQuadrante01restante;
	}

	public static boolean existeQuadrante02PosicoesRestantes(int[][] matriz) {
		boolean achouQuadrante02restantes = false;
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
		
		List<Integer> numerosPossiveis = retornaNumerosPossiveis(matriz);
		int restanteQuad1 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad1.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad2 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad2.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad3 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad3.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad4 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad4.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad5 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad5.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad6 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad6.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad7 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad7.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad8 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad8.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad9 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad9.contains(aObject)).
				collect(Collectors.toList()).size();
		
		if(restanteQuad1 == 2 || restanteQuad2 == 2 || restanteQuad3 == 2 ||
				restanteQuad4 == 2 || restanteQuad5 == 2 || restanteQuad6 == 2 ||
				restanteQuad7 == 2 || restanteQuad8 == 2 || restanteQuad9 == 2) {
			
			achouQuadrante02restantes = true;
		}
		return achouQuadrante02restantes;
	}
	
	public static void exibirMsg(String msg, String mode) {
		if("DEBUG".equals(mode)) {
			System.out.println(msg);
		}
	}
	
	public static int[] retornaNumeroSetarECoordenadasQuadrante(
		int[][] matriz,
		List<Integer> numerosPossiveis,
		int restanteQuad1, int restanteQuad2, int restanteQuad3,
		int restanteQuad4, int restanteQuad5, int restanteQuad6,
		int restanteQuad7, int restanteQuad8, int restanteQuad9,
		List<Integer> elementosQuad1, List<Integer> elementosQuad2, List<Integer> elementosQuad3,
		List<Integer> elementosQuad4, List<Integer> elementosQuad5, List<Integer> elementosQuad6,
		List<Integer> elementosQuad7, List<Integer> elementosQuad8, List<Integer> elementosQuad9) {
		
		int[] coordenadas = null;
		int numeroSetar = -1;
		int[] retorno = new int[5];
		
		if(restanteQuad1 == 1) {
			numeroSetar = numerosPossiveis.stream()
					.distinct().
					filter(aObject -> !elementosQuad1.contains(aObject)).
					collect(Collectors.toList()).get(0);
			coordenadas = retornaCoordenadasQuadrante(1, matriz);
		}
		else if(restanteQuad2 == 1) {
			numeroSetar = numerosPossiveis.stream()
					.distinct().
					filter(aObject -> !elementosQuad2.contains(aObject)).
					collect(Collectors.toList()).get(0);
			coordenadas = retornaCoordenadasQuadrante(2, matriz);
		}
		else if(restanteQuad3 == 1) {
			numeroSetar = numerosPossiveis.stream()
					.distinct().
					filter(aObject -> !elementosQuad3.contains(aObject)).
					collect(Collectors.toList()).get(0);
			coordenadas = retornaCoordenadasQuadrante(3, matriz);
		}
		else if(restanteQuad4 == 1) {
			numeroSetar = numerosPossiveis.stream()
					.distinct().
					filter(aObject -> !elementosQuad4.contains(aObject)).
					collect(Collectors.toList()).get(0);
			coordenadas = retornaCoordenadasQuadrante(4, matriz);
		}
		else if(restanteQuad5 == 1) {
			numeroSetar = numerosPossiveis.stream()
					.distinct().
					filter(aObject -> !elementosQuad5.contains(aObject)).
					collect(Collectors.toList()).get(0);
			coordenadas = retornaCoordenadasQuadrante(5, matriz);
		}
		else if(restanteQuad6 == 1) {
			numeroSetar = numerosPossiveis.stream()
					.distinct().
					filter(aObject -> !elementosQuad6.contains(aObject)).
					collect(Collectors.toList()).get(0);
			coordenadas = retornaCoordenadasQuadrante(6, matriz);
		}
		else if(restanteQuad7 == 1) {
			numeroSetar = numerosPossiveis.stream()
					.distinct().
					filter(aObject -> !elementosQuad7.contains(aObject)).
					collect(Collectors.toList()).get(0);
			coordenadas = retornaCoordenadasQuadrante(7, matriz);
		}
		else if(restanteQuad8 == 1) {
			numeroSetar = numerosPossiveis.stream()
					.distinct().
					filter(aObject -> !elementosQuad8.contains(aObject)).
					collect(Collectors.toList()).get(0);
			coordenadas = retornaCoordenadasQuadrante(8, matriz);
		}
		else if(restanteQuad9 == 1) {
			numeroSetar = numerosPossiveis.stream()
					.distinct().
					filter(aObject -> !elementosQuad9.contains(aObject)).
					collect(Collectors.toList()).get(0);
			coordenadas = retornaCoordenadasQuadrante(9, matriz);
		}
		retorno[0] = numeroSetar;
		retorno[1] = coordenadas[0]; 
		retorno[2] = coordenadas[1];
		retorno[3] = coordenadas[2];
		retorno[4] = coordenadas[3];
		
		return retorno;
	}
	
	// TODO Diminuir de 26 para 15
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
		
		List<Integer> numerosPossiveis = retornaNumerosPossiveis(matriz);
		int restanteQuad1 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad1.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad2 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad2.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad3 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad3.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad4 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad4.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad5 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad5.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad6 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad6.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad7 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad7.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad8 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad8.contains(aObject)).
				collect(Collectors.toList()).size();
		int restanteQuad9 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad9.contains(aObject)).
				collect(Collectors.toList()).size();
		
		//
		int numeroSetar = -1;
		int linhaSetar = -1;
		int colunaSetar = -1;
		int linhaInicio = 0;
		int linhaFim = 0;
		int colunaInicio = 0;
		int colunaFim = 0;
		
		if(restanteQuad1 == 1 || restanteQuad2 == 1 || restanteQuad3 == 1 ||
				restanteQuad4 == 1 || restanteQuad5 == 1 || restanteQuad6 == 1 ||
				restanteQuad7 == 1 || restanteQuad8 == 1 || restanteQuad9 == 1) {
			
			int[] retorno = retornaNumeroSetarECoordenadasQuadrante(
					matriz, numerosPossiveis,
					restanteQuad1,  restanteQuad2,  restanteQuad3,
					restanteQuad4,  restanteQuad5,  restanteQuad6,
					restanteQuad7,  restanteQuad8,  restanteQuad9,
					elementosQuad1, elementosQuad2, elementosQuad3,
					elementosQuad4, elementosQuad5, elementosQuad6,
					elementosQuad7, elementosQuad8, elementosQuad9);
			
			numeroSetar = retorno[0];
			linhaInicio = retorno[1];
			linhaFim = retorno[2];
			colunaInicio = retorno[3];
			colunaFim = retorno[4];

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

	public static int[] retornaCoordenadasQuadrante(int quadrante, int[][] matriz) {
		int[] coordenadas = new int[4];

		if(matriz.length == 4) {
			if(quadrante == 1) {
				coordenadas[0]=0;
				coordenadas[1]=1;
				coordenadas[2]=0;
				coordenadas[3]=1;
			}
			if(quadrante == 2) {
				coordenadas[0]=0;
				coordenadas[1]=1;
				coordenadas[2]=2;
				coordenadas[3]=3;
			}
			if(quadrante == 3) {
				coordenadas[0]=2;
				coordenadas[1]=3;
				coordenadas[2]=0;
				coordenadas[3]=1;
			}
			if(quadrante == 4) {
				coordenadas[0]=2;
				coordenadas[1]=3;
				coordenadas[2]=2;
				coordenadas[3]=3;
			}
			
		} else if(matriz.length == 9) {

			if(quadrante == 1) {
				coordenadas[0]=0;
				coordenadas[1]=2;
				coordenadas[2]=0;
				coordenadas[3]=2;
			}
			else if(quadrante == 2) {
				coordenadas[0]=0;
				coordenadas[1]=2;
				coordenadas[2]=3;
				coordenadas[3]=5;
			}
			else if(quadrante == 3) {
				coordenadas[0]=0;
				coordenadas[1]=2;
				coordenadas[2]=6;
				coordenadas[3]=8;
			}
			else if(quadrante == 4) {
				coordenadas[0]=3;
				coordenadas[1]=5;
				coordenadas[2]=0;
				coordenadas[3]=2;
			}
			else if(quadrante == 5) {
				coordenadas[0]=3;
				coordenadas[1]=5;
				coordenadas[2]=3;
				coordenadas[3]=5;
			}
			else if(quadrante == 6) {
				coordenadas[0]=3;
				coordenadas[1]=5;
				coordenadas[2]=6;
				coordenadas[3]=8;
			}
			else if(quadrante == 7) {
				coordenadas[0]=6;
				coordenadas[1]=8;
				coordenadas[2]=0;
				coordenadas[3]=2;
			}
			else if(quadrante == 8) {
				coordenadas[0]=6;
				coordenadas[1]=8;
				coordenadas[2]=3;
				coordenadas[3]=5;
			}
			else if(quadrante == 9) {
				coordenadas[0]=6;
				coordenadas[1]=8;
				coordenadas[2]=6;
				coordenadas[3]=8;
			}
			
		}
		
		return coordenadas;
	}
	
	public static List<Integer> retornaNumerosPossiveis(int[][] matriz) {
		Integer[] arrayNumerosPossiveis = null;
		if(matriz.length == 4) {
			arrayNumerosPossiveis = arrayNumerosPossiveis4X4;
		} else if(matriz.length == 9) {
			arrayNumerosPossiveis = arrayNumerosPossiveis9X9;
		}
		return Arrays.asList(arrayNumerosPossiveis);
	}
	
	public static void resolveQuadrante02PosicoesRestantes(int[][] matriz) {
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
		Integer[] arrayNumerosPossiveis = null;
		
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
        
		List<Integer> numerosPossiveis = retornaNumerosPossiveis(matriz);
		
		List<Integer> restanteQuad1 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad1.contains(aObject)).
				collect(Collectors.toList());
		List<Integer> restanteQuad2 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad2.contains(aObject)).
				collect(Collectors.toList());
		List<Integer> restanteQuad3 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad3.contains(aObject)).
				collect(Collectors.toList());
		List<Integer> restanteQuad4 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad4.contains(aObject)).
				collect(Collectors.toList());
		List<Integer> restanteQuad5 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad5.contains(aObject)).
				collect(Collectors.toList());
		List<Integer> restanteQuad6 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad6.contains(aObject)).
				collect(Collectors.toList());
		List<Integer> restanteQuad7 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad7.contains(aObject)).
				collect(Collectors.toList());
		List<Integer> restanteQuad8 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad8.contains(aObject)).
				collect(Collectors.toList());
		List<Integer> restanteQuad9 = numerosPossiveis.stream()
				.distinct().
				filter(aObject -> !elementosQuad9.contains(aObject)).
				collect(Collectors.toList());
		//
		if(restanteQuad1.size() == 2 || restanteQuad2.size() == 2 || restanteQuad3.size() == 2 ||
				restanteQuad4.size() == 2 || restanteQuad5.size() == 2 || restanteQuad6.size() == 2 ||
				restanteQuad7.size() == 2 || restanteQuad8.size() == 2 || restanteQuad9.size() == 2) {
			
			if(restanteQuad1.size() == 2) {
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
			else if(restanteQuad2.size() == 2) {
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
			else if(restanteQuad3.size() == 2) {
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
			else if(restanteQuad4.size() == 2) {
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
			else if(restanteQuad5.size() == 2) {
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
			else if(restanteQuad6.size() == 2) {
				numeroAnalisado01 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad6.contains(aObject)).
						collect(Collectors.toList()).get(0);
				numeroAnalisado02 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad6.contains(aObject)).
						collect(Collectors.toList()).get(1);

				coordenadas = retornaCoordenadasQuadrante(6, matriz);;
			}
			else if(restanteQuad7.size() == 2) {
				numeroAnalisado01 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad7.contains(aObject)).
						collect(Collectors.toList()).get(0);
				numeroAnalisado02 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad7.contains(aObject)).
						collect(Collectors.toList()).get(1);

				coordenadas = retornaCoordenadasQuadrante(7, matriz);;
			}
			else if(restanteQuad8.size() == 2) {
				numeroAnalisado01 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad8.contains(aObject)).
						collect(Collectors.toList()).get(0);
				numeroAnalisado02 = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad8.contains(aObject)).
						collect(Collectors.toList()).get(1);

				coordenadas = retornaCoordenadasQuadrante(8, matriz);;
			}
			else if(restanteQuad9.size() == 2) {
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
			
			// Analisando numeroSetar01
			// Analisando numeroSetar01
			
			linhaInicio = coordenadas[0];
			linhaFim = coordenadas[1];
			colunaInicio = coordenadas[2];
			colunaFim = coordenadas[3];
			List<Posicao> posicoesVazias = new ArrayList<>();
			for (int i = linhaInicio; i <= linhaFim; i++) {
				for (int j = colunaInicio; j <= colunaFim; j++) {
					if(matriz[i][j] == 0) {
						posicoesVazias.add(new Posicao(i, j, 0));
					}
				}
			}	

			//
			if(!posicoesVazias.isEmpty() && posicoesVazias.size() == 2 ) {

				linhaNumeroAnalisado01 = posicoesVazias.get(0).getX();
				colunaNumeroAnalisado01 = posicoesVazias.get(0).getY();
				//
				linhaNumeroAnalisado02 = posicoesVazias.get(1).getX();
				colunaNumeroAnalisado02 = posicoesVazias.get(1).getY();

				// Tenta inferir pelas linhas
				boolean inferiuPelasLinhas = false;
				if(SudokuUtil.existeNumeroNaLinha(numeroAnalisado01, linhaNumeroAnalisado01, matriz) == 1) {
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado01, linhaNumeroAnalisado02, colunaNumeroAnalisado02, matriz, "RG09");
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado02, linhaNumeroAnalisado01, colunaNumeroAnalisado01, matriz, "RG09");
					inferiuPelasLinhas = true;
					
				} else if(SudokuUtil.existeNumeroNaLinha(numeroAnalisado01, linhaNumeroAnalisado02, matriz) == 1) {
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado01, linhaNumeroAnalisado01, colunaNumeroAnalisado01, matriz, "RG09");
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado02, linhaNumeroAnalisado02, colunaNumeroAnalisado02, matriz, "RG09");
					inferiuPelasLinhas = true;
				}
				
				if(SudokuUtil.existeNumeroNaLinha(numeroAnalisado02, linhaNumeroAnalisado01, matriz) == 1) {
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado02, linhaNumeroAnalisado02, colunaNumeroAnalisado02, matriz, "RG09");
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado01, linhaNumeroAnalisado01, colunaNumeroAnalisado01, matriz, "RG09");
					inferiuPelasLinhas = true;

				} else if(SudokuUtil.existeNumeroNaLinha(numeroAnalisado02, linhaNumeroAnalisado02, matriz) == 1) {
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado01, linhaNumeroAnalisado02, colunaNumeroAnalisado02, matriz, "RG09");
					SudokuUtil.setValorNaLinhaColuna(numeroAnalisado02, linhaNumeroAnalisado01, colunaNumeroAnalisado01, matriz, "RG09");
					inferiuPelasLinhas = true;

				}
			}
		}
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

		int valor = retornaNumerosPossiveis(matriz).stream()
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

		int valor = retornaNumerosPossiveis(matriz).stream()
				.distinct().
				filter(aObject -> !numerosEncontrados.contains(aObject)).
				collect(Collectors.toList()).get(0);
		
		return new Posicao(linha, colunaVazia, valor);
	}
	
	public static Posicao retornaCelula01Possib(int[][] matriz) {
		return retornaCelulaQtdPossibilidades(matriz, 1);
	}
	
	public static Posicao retornaCelulaQtdPossibilidades(int[][] matriz, int qdtPossibs) {
		Posicao pos = null;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if( (matriz[i][j] == 0) && (SudokuUtil.qtdPossibilidadesCelula(i, j, matriz).size() == qdtPossibs) ) {
					pos = new Posicao(i, j, SudokuUtil.qtdPossibilidadesCelula(i, j, matriz).get(0));
					break;
				}
			}
		}
		return pos;
	}
	
	public static int qtdNumPreenchidosNaLinhaNoQuadrante(int linhaAnalisada, int quadrante, int[][] matriz) {
		int contador = 0;
		int colunaInicio = -1;
		int colunaFim = -1;
		
		if(quadrante == 1 || quadrante == 4 || quadrante == 7) {
			
			colunaInicio = 0;
			colunaFim = 2;
		} 
		else if(quadrante == 2 || quadrante == 5 || quadrante == 8) {
			
			colunaInicio = 3;
			colunaFim = 5;
		}
		else if(quadrante == 3 || quadrante == 6 || quadrante == 9) {
			
			colunaInicio = 6;
			colunaFim = 8;
		}

		for (int j = colunaInicio; j <= colunaFim; j++) {
			if(matriz[linhaAnalisada][j] != 0) {
				contador++;
			}
		}
		return contador;
	}

	public static int qtdNumPreenchidosNaColunaNoQuadrante(int colunaAnalisada, int quadrante, int[][] matriz) {
		int contador = 0;
		int linhaInicio = -1;
		int linhaFim = -1;
		
		if(quadrante == 1 || quadrante == 2 || quadrante == 3) {
			
			linhaInicio = 0;
			linhaFim = 2;
		} 
		else if(quadrante == 4 || quadrante == 5 || quadrante == 6) {
			
			linhaInicio = 3;
			linhaFim = 5;
		}
		else if(quadrante == 7 || quadrante == 8 || quadrante == 8) {
			
			linhaInicio = 6;
			linhaFim = 8;
		}

		for (int i = linhaInicio; i <= linhaFim; i++) {
			if(matriz[i][colunaAnalisada] != 0) {
				contador++;
			}
		}
		return contador;
	}	
}
