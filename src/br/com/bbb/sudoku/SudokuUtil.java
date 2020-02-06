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
	
	public static Integer[] arrayNumerosPossiveis = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	
	private SudokuUtil() {
		//
	}

	public static String retornaNumerosPossibs(int i, int j, int[][] matriz) {
		StringBuilder str = new StringBuilder();
		for (int num : qtdPossibilidadesCelula(i, j, matriz)) {
			str.append(" "+num);
		}
		
		return str.toString();
	}
	
	public static boolean isUnique(int[] nums){
	    Set<Integer> set = new HashSet<>(nums.length);

	    for (int a : nums) {
	        if (!set.add(a))
	            return false;
	    }

	    return true;
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
	
	public static int celulasRestantesQuadrante(int linha, int[][] matriz) {
		return -1;
	}
	
	public static List<Integer> retornaColunasQuadrante(int quadrante, int[][] matriz) {
		List<Integer> colunas = new ArrayList<>();
		
		// linhas 0, 1, 2 
		if(quadrante == 1 || quadrante == 4 || quadrante == 7) {
			colunas.add(0);colunas.add(1);colunas.add(2);
		
		} else if(quadrante == 2 || quadrante == 5 || quadrante == 8) {
			colunas.add(3);colunas.add(4);colunas.add(5);
			
		} else if(quadrante == 3 || quadrante == 6 || quadrante == 9) {
			colunas.add(6);colunas.add(7);colunas.add(8);
		}

		return colunas;
	}	

	public static List<Integer> retornaLinhasQuadrante(int quadrante, int[][] matriz) {
		List<Integer> linhas = new ArrayList<>();
		
		// linhas 0, 1, 2 
		if(quadrante == 1 || quadrante == 2 || quadrante == 3) {
			linhas.add(0);linhas.add(1);linhas.add(2);
		
		} else if(quadrante == 4 || quadrante == 5 || quadrante == 6) {
			linhas.add(3);linhas.add(4);linhas.add(5);
			
		} else if(quadrante == 7 || quadrante == 8 || quadrante == 9) {
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
		
		// linhas 0, 1, 2 
		if(quadrante == 1) {
			
			linhaInicio = 0;
			linhaFim = 2;
			colunaInicio = 0;
			colunaFim = 2;
		}
		else if(quadrante == 2) {

			linhaInicio = 0;
			linhaFim = 2;
			colunaInicio = 3;
			colunaFim = 5;
		}
		else if(quadrante == 3) {
			
			linhaInicio = 0;
			linhaFim = 2;
			colunaInicio = 6;
			colunaFim = 8;
		}
		// linhas 3, 4, 5 
		else if(quadrante == 4) {
			
			linhaInicio = 3;
			linhaFim = 5;
			colunaInicio = 0;
			colunaFim = 2;
		}
		else if(quadrante == 5) {
			
			linhaInicio = 3;
			linhaFim = 5;
			colunaInicio = 3;
			colunaFim = 5;
		}
		else if(quadrante == 6) {
			
			linhaInicio = 3;
			linhaFim = 5;
			colunaInicio = 6;
			colunaFim = 8;
		}
		// linhas 6, 7, 8 
		else if(quadrante == 7) {
			
			linhaInicio = 6;
			linhaFim = 8;
			colunaInicio = 0;
			colunaFim = 2;
		}
		else if(quadrante == 8) {
			
			linhaInicio = 6;
			linhaFim = 8;
			colunaInicio = 3;
			colunaFim = 5;
		}
		else if(quadrante == 9) {
			
			linhaInicio = 6;
			linhaFim = 8;
			colunaInicio = 6;
			colunaFim = 8;
		}
		
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
		
		// linhas 0, 1, 2 
		if(quadrante == 1) {
			
			linhaInicio = 0;
			linhaFim = 2;
			colunaInicio = 0;
			colunaFim = 2;
		} 
		else if(quadrante == 2) {
			
			linhaInicio = 0;
			linhaFim = 2;
			colunaInicio = 3;
			colunaFim = 5;
		}
		else if(quadrante == 3) {
			
			linhaInicio = 0;
			linhaFim = 2;
			colunaInicio = 6;
			colunaFim = 8;

		}
		
		// linhas 3, 4, 5 
		else if(quadrante == 4) {
			
			linhaInicio = 3;
			linhaFim = 5;
			colunaInicio = 0;
			colunaFim = 2;
			
		}
		else if(quadrante == 5) {
			
			linhaInicio = 3;
			linhaFim = 5;
			colunaInicio = 3;
			colunaFim = 5;
			
		}
		else if(quadrante == 6) {
			
			linhaInicio = 3;
			linhaFim = 5;
			colunaInicio = 6;
			colunaFim = 8;

		}
		
		// linhas 6, 7, 8 
		else if(quadrante == 7) {
			
			linhaInicio = 6;
			linhaFim = 8;
			colunaInicio = 0;
			colunaFim = 2;
			
		}
		else if(quadrante == 8) {
			
			linhaInicio = 6;
			linhaFim = 8;
			colunaInicio = 3;
			colunaFim = 5;
			
		}
		else if(quadrante == 9) {
			
			linhaInicio = 6;
			linhaFim = 8;
			colunaInicio = 6;
			colunaFim = 8;
			
		}
		
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

	public static int qtdTotalPossibilidadesMatriz(int[][] matriz) {
		int qtdTotal = 0;
	
		int qtdCelula = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				qtdCelula = qtdPossibilidadesCelula(i, j, matriz).size();
				qtdTotal += qtdCelula;
			}
		}
		return qtdTotal;
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
		List<Integer> numerosPossiveis = Arrays.asList(arrayNumerosPossiveis);

		return numerosPossiveis.stream()
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
		
		if(linha == 4 && coluna == 4) {
			conjuntoElementos = pegaElementosQuadrante4X4(linha, coluna, matriz);
			
		} else if(linha == 9 && coluna == 9) {
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
		
		if(quadrante == 1 ) {
			linhaInicio = 0;
			linhaFim = 2;
			colunaInicio = 0;
			colunaFim = 2;
		}
		if(quadrante == 2 ) {
			linhaInicio = 0;
			linhaFim = 2;
			colunaInicio = 3;
			colunaFim = 5;
		}
		if(quadrante == 3 ) {
			linhaInicio = 0;
			linhaFim = 2;
			colunaInicio = 6;
			colunaFim = 8;
		}

		if(quadrante == 4 ) {
			linhaInicio = 3;
			linhaFim = 5;
			colunaInicio = 0;
			colunaFim = 2;
		}
		if(quadrante == 5 ) {
			linhaInicio = 3;
			linhaFim = 5;
			colunaInicio = 3;
			colunaFim = 5;
		}
		if(quadrante == 6 ) {
			linhaInicio = 3;
			linhaFim = 5;
			colunaInicio = 6;
			colunaFim = 8;
		}

		if(quadrante == 7 ) {
			linhaInicio = 6;
			linhaFim = 8;
			colunaInicio = 0;
			colunaFim = 2;
		}
		if(quadrante == 8 ) {
			linhaInicio = 6;
			linhaFim = 8;
			colunaInicio = 3;
			colunaFim = 5;
		}
		if(quadrante == 9 ) {
			linhaInicio = 6;
			linhaFim = 8;
			colunaInicio = 6;
			colunaFim = 8;
		}
		
		
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
		
		// linhas 0, 1, 2 
		if(quadrante == 1) {
			
			linhaInicio = 0;
			linhaFim = 2;
			colunaInicio = 0;
			colunaFim = 2;
		} 
		else if(quadrante == 2) {
			
			linhaInicio = 0;
			linhaFim = 2;
			colunaInicio = 3;
			colunaFim = 5;
		}
		else if(quadrante == 3) {
			
			linhaInicio = 0;
			linhaFim = 2;
			colunaInicio = 6;
			colunaFim = 8;
		}
		
		// linhas 3, 4, 5 
		else if(quadrante == 4) {
			
			linhaInicio = 3;
			linhaFim = 5;
			colunaInicio = 0;
			colunaFim = 2;
		}
		else if(quadrante == 5) {
			
			linhaInicio = 3;
			linhaFim = 5;
			colunaInicio = 3;
			colunaFim = 5;
		}
		else if(quadrante == 6) {
			
			linhaInicio = 3;
			linhaFim = 5;
			colunaInicio = 6;
			colunaFim = 8;
		}
		
		// linhas 6, 7, 8 
		else if(quadrante == 7) {
			
			linhaInicio = 6;
			linhaFim = 8;
			colunaInicio = 0;
			colunaFim = 2;
		}
		else if(quadrante == 8) {
			
			linhaInicio = 6;
			linhaFim = 8;
			colunaInicio = 3;
			colunaFim = 5;
		}
		else if(quadrante == 9) {
			
			linhaInicio = 6;
			linhaFim = 8;
			colunaInicio = 6;
			colunaFim = 8;
		}
		
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
	
	public static void imprimeMatriz(int[][] matriz) {
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

	public static boolean existeColuna01PosicaoRestante(int coluna, int[][] matriz) {
		boolean achouColuna01restante = false;
		int contador = 0;
		for (int i = 0; i < matriz.length; i++) {
				if( (matriz[i][coluna] == 0) ) {
					contador++;
				}
		}
		if(contador == 1) {
			achouColuna01restante = true;
		}
		return achouColuna01restante;
	}

	public static boolean existeLinha01PosicaoRestante(int linha, int[][] matriz) {
		boolean achouLinha01restante = false;
		int contador = 0;
		for (int j = 0; j < matriz.length; j++) {
				if( (matriz[linha][j] == 0) ) {
					contador++;
				}
		}
		if(contador == 1) {
			achouLinha01restante = true;
		}
		return achouLinha01restante;
	}

	public static boolean existeLinha02PosicoesRestantes(int linha, int[][] matriz) {
		boolean achouLinha03restantes = false;
		int contador = 0;
		for (int j = 0; j < matriz.length; j++) {
				if( (matriz[linha][j] == 0) ) {
					contador++;
				}
		}
		if(contador == 2) {
			achouLinha03restantes = true;
		}
		return achouLinha03restantes;
	}

	public static boolean existeColuna02PosicoesRestantes(int coluna, int[][] matriz) {
		boolean achouColuna02restantes = false;
		int contador = 0;
		for (int i = 0; i < matriz.length; i++) {
				if( (matriz[i][coluna] == 0) ) {
					contador++;
				}
		}
		if(contador == 2) {
			achouColuna02restantes = true;
		}
		return achouColuna02restantes;
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
		
        Integer[] arrayNumerosPossiveis = null;
		if(matriz.length == 4) {
			arrayNumerosPossiveis = new Integer[] { 1, 2, 3, 4 };
		} else if(matriz.length == 9) {
			arrayNumerosPossiveis = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		}

		List<Integer> numerosPossiveis = Arrays.asList(arrayNumerosPossiveis);			
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
		
        Integer[] arrayNumerosPossiveis = null;
		if(matriz.length == 4) {
			arrayNumerosPossiveis = new Integer[] { 1, 2, 3, 4 };
		} else if(matriz.length == 9) {
			arrayNumerosPossiveis = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		}

		List<Integer> numerosPossiveis = Arrays.asList(arrayNumerosPossiveis);			
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
		
        Integer[] arrayNumerosPossiveis = null;
		if(matriz.length == 4) {
			arrayNumerosPossiveis = new Integer[] { 1, 2, 3, 4 };
		} else if(matriz.length == 9) {
			arrayNumerosPossiveis = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		}

		List<Integer> numerosPossiveis = Arrays.asList(arrayNumerosPossiveis);			
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
		//
		int linhaInicio = 0;
		int linhaFim = 0;
		int colunaInicio = 0;
		int colunaFim = 0;
		
		if(restanteQuad1 == 1 || restanteQuad2 == 1 || restanteQuad3 == 1 ||
				restanteQuad4 == 1 || restanteQuad5 == 1 || restanteQuad6 == 1 ||
				restanteQuad7 == 1 || restanteQuad8 == 1 || restanteQuad9 == 1) {
			
			if(restanteQuad1 == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad1.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				linhaInicio = 0;
				linhaFim = 2;
				colunaInicio = 0;
				colunaFim = 2;
			}
			else if(restanteQuad2 == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad2.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				linhaInicio = 0;
				linhaFim = 2;
				colunaInicio = 3;
				colunaFim = 5;
			}
			else if(restanteQuad3 == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad3.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				linhaInicio = 0;
				linhaFim = 2;
				colunaInicio = 6;
				colunaFim = 8;
			}
			else if(restanteQuad4 == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad4.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				linhaInicio = 3;
				linhaFim = 5;
				colunaInicio = 0;
				colunaFim = 2;
			}
			else if(restanteQuad5 == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad5.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				linhaInicio = 3;
				linhaFim = 5;
				colunaInicio = 3;
				colunaFim = 5;
			}
			else if(restanteQuad6 == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad6.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				linhaInicio = 3;
				linhaFim = 5;
				colunaInicio = 6;
				colunaFim = 8;
			}
			else if(restanteQuad7 == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad7.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				linhaInicio = 6;
				linhaFim = 8;
				colunaInicio = 0;
				colunaFim = 2;
			}
			else if(restanteQuad8 == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad8.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				linhaInicio = 6;
				linhaFim = 8;
				colunaInicio = 3;
				colunaFim = 5;
			}
			else if(restanteQuad9 == 1) {
				numeroSetar = numerosPossiveis.stream()
						.distinct().
						filter(aObject -> !elementosQuad9.contains(aObject)).
						collect(Collectors.toList()).get(0);
				
				linhaInicio = 6;
				linhaFim = 8;
				colunaInicio = 6;
				colunaFim = 8;
			}
			
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
		
		return new Posicao(linhaSetar, colunaSetar, numeroSetar);
	}

	public static void resolveQuadrante02PosicoesRestantes(int[][] matriz) {
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
		
        Integer[] arrayNumerosPossiveis = null;
		if(matriz.length == 4) {
			arrayNumerosPossiveis = new Integer[] { 1, 2, 3, 4 };
		} else if(matriz.length == 9) {
			arrayNumerosPossiveis = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		}

		List<Integer> numerosPossiveis = Arrays.asList(arrayNumerosPossiveis);
		
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
				
				linhaInicio = 0;
				linhaFim = 2;
				colunaInicio = 0;
				colunaFim = 2;
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
				
				linhaInicio = 0;
				linhaFim = 2;
				colunaInicio = 3;
				colunaFim = 5;
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

				linhaInicio = 0;
				linhaFim = 2;
				colunaInicio = 6;
				colunaFim = 8;
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

				linhaInicio = 3;
				linhaFim = 5;
				colunaInicio = 0;
				colunaFim = 2;
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

				linhaInicio = 3;
				linhaFim = 5;
				colunaInicio = 3;
				colunaFim = 5;
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

				linhaInicio = 3;
				linhaFim = 5;
				colunaInicio = 6;
				colunaFim = 8;
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

				linhaInicio = 6;
				linhaFim = 8;
				colunaInicio = 0;
				colunaFim = 2;
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

				linhaInicio = 6;
				linhaFim = 8;
				colunaInicio = 3;
				colunaFim = 5;
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

				linhaInicio = 6;
				linhaFim = 8;
				colunaInicio = 6;
				colunaFim = 8;
			}
			
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
				SudokuUtil.setValorNaLinhaColuna(numeroAnalisado01, linhaNumeroAnalisado01, colunaNumeroAnalisado01, matriz, "RG09");
				SudokuUtil.setValorNaLinhaColuna(numeroAnalisado02, linhaNumeroAnalisado02, colunaNumeroAnalisado02, matriz, "RG09");
			}
		}
	}
	
	public static boolean existeLinha03PosicoesRestantes(int linha, int[][] matriz) {
		boolean achouLinha03restantes = false;
		int contador = 0;
		for (int j = 0; j < matriz.length; j++) {
				if( (matriz[linha][j] == 0) ) {
					contador++;
				}
		}
		if(contador == 3) {
			achouLinha03restantes = true;
		}
		return achouLinha03restantes;
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

        Integer[] arrayNumerosPossiveis = null;
		if(matriz.length == 4) {
			arrayNumerosPossiveis = new Integer[] { 1, 2, 3, 4 };
		} else if(matriz.length == 9) {
			arrayNumerosPossiveis = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		}

		List<Integer> numerosPossiveis = Arrays.asList(arrayNumerosPossiveis);		
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

        Integer[] arrayNumerosPossiveis = null;
		if(matriz.length == 4) {
			arrayNumerosPossiveis = new Integer[] { 1, 2, 3, 4 };
		} else if(matriz.length == 9) {
			arrayNumerosPossiveis = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		}

		List<Integer> numerosPossiveis = Arrays.asList(arrayNumerosPossiveis);		
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
