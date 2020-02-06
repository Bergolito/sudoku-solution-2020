package br.com.bbb.sudoku;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sudoku {

	// constantes
	private static final String DEBUG_MODE = "DEBUG";
	private static final String PRODUCAO_MODE = "PROD";
	private static final String REGRA01 = "RG01";
	private static final String REGRA02_AN_HORIZONTAL = "RG02-Análise Horizontal";
	private static final String REGRA03_AN_VERTICAL = "RG03-Análise Vertical";
	private static final String REGRA04 = "RG04";
	private static final String REGRA05 = "RG05";
	private static final String REGRA06_LINHA = "RG06-Linha";
	private static final String REGRA06_COLUNA = "RG06-Coluna";
	private static final String REGRA08 = "RG08";
	
	// atributos
	private List<Integer> numerosPossiveis = new ArrayList<>();
	private String nivelLog;			
	
	public String getNivelLog() {
		return nivelLog;
	}

	public void setNivelLog(String nivelLog) {
		this.nivelLog = nivelLog;
	}

	public Sudoku() {
		//
	}

	public Sudoku(int lin, int col, int[][] mat) {
		mat = new int[lin][col];
		numerosPossiveis = SudokuUtil.retornaNumerosPossiveis(mat);
	}

	public Sudoku(int lin, int col, int[][] mat, String logLevel) {
		mat = new int[lin][col];
		// nível do log
		if(null != logLevel) {
			this.nivelLog = DEBUG_MODE; 
		}else {
			this.nivelLog = PRODUCAO_MODE; 
		}
		numerosPossiveis = SudokuUtil.retornaNumerosPossiveis(mat);
	}
	
	/**
	 * Existe uma posicao no tabuleiro que possui apenas uma única possibilidade de número.
	 * Quando isso acontece, seta o número na posição.
	 * 
	 * @param matriz
	 * @return
	 */
	private void regra01(int[][] matriz) {
		if(SudokuUtil.existeCelula01Possib(matriz)) {
			Posicao posicao = SudokuUtil.retornaCelula01Possib(matriz);
			SudokuUtil.setValorNaLinhaColuna(posicao.getValor(), posicao.getX(), posicao.getY(), matriz, REGRA01);
		}
	}
	
	/**
	 * Realiza a análise na horizontal (nas linhas)
	 * @param matriz
	 */
	private void regra02(int[][] matriz) {
		// Analisa os quadrantes 1,2,3
		analisaCamadaHorizontal01DoisQuadrantesPreenchidos(matriz);
		
		// Analisa os quadrantes 4,5,6
		analisaCamadaHorizontal02DoisQuadrantesPreenchidos(matriz);
		
		// Analisa os quadrantes 7,8,9
		analisaCamadaHorizontal03DoisQuadrantesPreenchidos(matriz);
		
		// Analisa os quadrantes 1,2,3
		analisaCamadaHorizontal01UmQuadrantePreenchido(matriz);
		
		// Analisa os quadrantes 4,5,6
		analisaCamadaHorizontal02UmQuadrantePreenchido(matriz);
		
		// Analisa os quadrantes 7,8,9
		analisaCamadaHorizontal03UmQuadrantePreenchido(matriz);
	}

	/**
	 * Realiza a análise na vertical (nas colunas)
	 * @param matriz
	 */
	private void regra03(int[][] matriz) {

		// Analisa os quadrantes 1,4,7
		analisaCamadaVertical01DoisQuadrantesPreenchidos(matriz);
		
		// Analisa os quadrantes 2,5,8
		analisaCamadaVertical02DoisQuadrantesPreenchidos(matriz);
		
		// Analisa os quadrantes 3,6,9
		analisaCamadaVertical03DoisQuadrantesPreenchidos(matriz);
		
		// Analisa os quadrantes 1,4,7
		analisaCamadaVertical01UmQuadrantePreenchido(matriz);
		
		// Analisa os quadrantes 2,5,8
		analisaCamadaVertical02UmQuadrantePreenchido(matriz);
		
		// Analisa os quadrantes 3,6,9
		analisaCamadaVertical03UmQuadrantePreenchido(matriz);
	}

	/**
	 * 
	 * @param matriz
	 */
	private void regra04(int[][] matriz) {
		int coluna = -1;
		for (int j = 0; j < matriz.length; j++) {
			if(SudokuUtil.existeColuna01PosicaoRestante(j, matriz)) {
				coluna = j;
				Posicao posicao = SudokuUtil.posicaoColuna01PosicaoRestante(coluna, matriz);
				SudokuUtil.setValorNaLinhaColuna(posicao.getValor(), posicao.getX(), posicao.getY(), matriz, REGRA04);
			}
		}
	}	

	private void regra05(int[][] matriz) {
		regra05Linha(matriz);
		regra05Coluna(matriz);
	}	

	private void regra06(int[][] matriz) {
		regra06Linha(matriz);
		regra06Coluna(matriz);
	}
	
	private void regra05Linha(int[][] matriz) {
		int linha = -1;
		for (int i = 0; i < matriz.length; i++) {
			if(SudokuUtil.existeLinha01PosicaoRestante(i, matriz)) {
				linha = i;
				Posicao posicao = SudokuUtil.posicaoLinha01PosicaoRestante(linha, matriz);
				SudokuUtil.setValorNaLinhaColuna(posicao.getValor(), posicao.getX(), posicao.getY(), matriz, REGRA05);
			}
		}
	}	
	
	private void regra05Coluna(int[][] matriz) {
		int coluna = -1;
		for (int i = 0; i < matriz.length; i++) {
			if(SudokuUtil.existeColuna01PosicaoRestante(i, matriz)) {
				coluna = i;
				Posicao posicao = SudokuUtil.posicaoColuna01PosicaoRestante(coluna, matriz);
				SudokuUtil.setValorNaLinhaColuna(posicao.getValor(), posicao.getX(), posicao.getY(), matriz, REGRA05);
			}
		}
	}	
	
	/**
	 * Analisa linhas que possuem 02 posicoes restantes
	 * @param matriz
	 */
	private void regra06Linha(int[][] matriz) {
		int linhaAnalisada = -1;
		int colunaVazia01 = -1;
		int colunaVazia02 = -1;
		List<Integer> colunasVazias = new ArrayList<>();
		List<Integer> numerosEncontrados = new ArrayList<>();

		for (int i = 0; i < matriz.length; i++) {
			if(SudokuUtil.existeLinha02PosicoesRestantes(i, matriz)) {
				linhaAnalisada = i;
				break;
			}
		}
		if(linhaAnalisada != -1) {
			numerosEncontrados.clear();
			for (int j = 0; j < matriz.length; j++) {
				if(matriz[linhaAnalisada][j] != 0) {
					numerosEncontrados.add(matriz[linhaAnalisada][j]);
				}
				if(matriz[linhaAnalisada][j] == 0) {
					colunasVazias.add(j);
				}
			}
			
			//
			colunaVazia01 = colunasVazias.get(0);
			colunaVazia02 = colunasVazias.get(1);
			
			List<Integer> numerosRestantes = numerosPossiveis.stream()
					.distinct().
					filter(aObject -> !numerosEncontrados.contains(aObject)).			
					collect(Collectors.toList());
			
			int possib01 = numerosRestantes.get(0);
			int possib02 = numerosRestantes.get(1);

			if(SudokuUtil.existeNumeroNaColuna(possib01, colunaVazia01, matriz) == 1) {
				
				SudokuUtil.setValorNaLinhaColuna(possib01, linhaAnalisada, colunaVazia02, matriz, REGRA06_LINHA);
				SudokuUtil.setValorNaLinhaColuna(possib02, linhaAnalisada, colunaVazia01, matriz, REGRA06_LINHA);
				
			} else if(SudokuUtil.existeNumeroNaColuna(possib02, colunaVazia01, matriz) == 1) {
				
				SudokuUtil.setValorNaLinhaColuna(possib02, linhaAnalisada, colunaVazia01, matriz, REGRA06_LINHA);
				SudokuUtil.setValorNaLinhaColuna(possib01, linhaAnalisada, colunaVazia02, matriz, REGRA06_LINHA);
			}
		}
	}	

	/**
	 * Analisa linhas que possuem 02 posicoes restantes
	 * @param matriz
	 */
	private void regra06Coluna(int[][] matriz) {
		int colunaAnalisada = -1;
		int linhaVazia01 = -1;
		int linhaVazia02 = -1;
		List<Integer> linhasVazias = new ArrayList<>();
		List<Integer> numerosEncontrados = new ArrayList<>();
		
		for (int i = 0; i < matriz.length; i++) {
			if(SudokuUtil.existeColuna02PosicoesRestantes(i, matriz)) {
				colunaAnalisada = i;
				break;
			}
		}
		if(colunaAnalisada != -1) {
			numerosEncontrados.clear();
			for (int i = 0; i < matriz.length; i++) {
				
				if(matriz[i][colunaAnalisada] != 0) {
					numerosEncontrados.add(matriz[i][colunaAnalisada]);
				}
				if(matriz[i][colunaAnalisada] == 0) {
					linhasVazias.add(i);
				}
				
			}
			
			//
			linhaVazia01 = linhasVazias.get(0);
			linhaVazia02 = linhasVazias.get(1);
			
			List<Integer> numerosRestantes = numerosPossiveis.stream()
					.distinct().
					filter(aObject -> !numerosEncontrados.contains(aObject)).			
					collect(Collectors.toList());
			
			if(numerosRestantes.size() == 2) {
				int possib01 = numerosRestantes.get(0);
				int possib02 = numerosRestantes.get(1);

				if(SudokuUtil.existeNumeroNaLinha(possib01, linhaVazia01, matriz) == 1) {
					
					SudokuUtil.setValorNaLinhaColuna(possib01, colunaAnalisada, linhaVazia02, matriz, REGRA06_COLUNA);
					SudokuUtil.setValorNaLinhaColuna(possib02, colunaAnalisada, linhaVazia01, matriz, REGRA06_COLUNA);
					
				} else if(SudokuUtil.existeNumeroNaLinha(possib02, linhaVazia01, matriz) == 1) {
					
					SudokuUtil.setValorNaLinhaColuna(possib02, colunaAnalisada, linhaVazia01, matriz, REGRA06_COLUNA);
					SudokuUtil.setValorNaLinhaColuna(possib01, colunaAnalisada, linhaVazia02, matriz, REGRA06_COLUNA);
				}
			}
		}
	}	
	
	// TODO Reduzir de 41 para 15
	private void regra07(int[][] matriz) {
		int linhaAnalisada = -1;
		int colunaVazia01 = -1;
		int colunaVazia02 = -1;
		int colunaVazia03 = -1;
		List<Integer> colunasVazias = new ArrayList<>();
		List<Integer> numerosEncontrados = new ArrayList<>();
		
		for (int i = 0; i < matriz.length; i++) {
			
			if(SudokuUtil.existeLinha03PosicoesRestantes(i, matriz)) {
				linhaAnalisada = i;
		
				if(linhaAnalisada != -1) {
					
					numerosEncontrados.clear();
					colunasVazias.clear();
					for (int j = 0; j < matriz.length; j++) {
						
						if(matriz[linhaAnalisada][j] != 0) {
							numerosEncontrados.add(matriz[linhaAnalisada][j]);
						}
		
						if(matriz[linhaAnalisada][j] == 0) {
							colunasVazias.add(j);
						}
					}
					
					colunaVazia01 = colunasVazias.get(0);
					colunaVazia02 = colunasVazias.get(1);
					colunaVazia03 = colunasVazias.get(2);
		
					if(colunaVazia01 != -1 && colunaVazia02 != -1 && colunaVazia03 != -1) {
			
						List<Integer> numerosRestantes = numerosPossiveis.stream()
								.distinct().
								filter(aObject -> !numerosEncontrados.contains(aObject)).
								collect(Collectors.toList());
						
						if(numerosRestantes.size() == 3) {
							int possibs1 = numerosRestantes.get(0);
							
							if(SudokuUtil.existeNumeroNaColuna(possibs1, colunaVazia01, matriz) == 0 &&
									SudokuUtil.existeNumeroNaColuna(possibs1, colunaVazia02, matriz) == 1 && 
									SudokuUtil.existeNumeroNaColuna(possibs1, colunaVazia03, matriz) == 1) {
				
								SudokuUtil.setValorNaLinhaColuna(possibs1, linhaAnalisada, colunaVazia01, matriz, REGRA06_LINHA);
								
							}
							else if(SudokuUtil.existeNumeroNaColuna(possibs1, colunaVazia02, matriz) == 0 &&
									SudokuUtil.existeNumeroNaColuna(possibs1, colunaVazia01, matriz) == 1 && 
									SudokuUtil.existeNumeroNaColuna(possibs1, colunaVazia03, matriz) == 1) {
				
								SudokuUtil.setValorNaLinhaColuna(possibs1, linhaAnalisada, colunaVazia02, matriz, REGRA06_LINHA);
							}
							else if(SudokuUtil.existeNumeroNaColuna(possibs1, colunaVazia03, matriz) == 0 &&
									SudokuUtil.existeNumeroNaColuna(possibs1, colunaVazia01, matriz) == 1 && 
									SudokuUtil.existeNumeroNaColuna(possibs1, colunaVazia02, matriz) == 1) {
				
								SudokuUtil.setValorNaLinhaColuna(possibs1, linhaAnalisada, colunaVazia03, matriz, REGRA06_LINHA);
							}
						}
					}
				}
			}
		}	
	}	
	
	
	private void regra08(int[][] matriz) {
		Posicao pos = null;
		
		if(SudokuUtil.existeQuadrante01PosicaoRestante(matriz)) {
			pos = SudokuUtil.retornaQuadrante01PosicaoRestante(matriz);
			if(null != pos) {
				SudokuUtil.setValorNaLinhaColuna(pos.getValor(), pos.getX(), pos.getY(), matriz, REGRA08);
			}
		}
	}

	private void regra09(int[][] matriz) {
		
		if(SudokuUtil.existeQuadrante02PosicoesRestantes(matriz)) {
			SudokuUtil.resolveQuadrante02PosicoesRestantes(matriz);
		}
	}
	
	private void analisaCamadaHorizontal01DoisQuadrantesPreenchidos(int[][] matriz) {
		List<Integer> linhasPossiveis = new ArrayList<>();
		linhasPossiveis.add(0); 
		linhasPossiveis.add(1); 
		linhasPossiveis.add(2);
		
		analisaCamadaHorizontal02QuadrantesPreenchidos(matriz, linhasPossiveis);
	}

	private void analisaCamadaHorizontal01UmQuadrantePreenchido(int[][] matriz) {
		List<Integer> linhasPossiveis = new ArrayList<>();
		linhasPossiveis.add(0); 
		linhasPossiveis.add(1); 
		linhasPossiveis.add(2);
		
		analisaCamadaHorizontal01QuadrantePreenchido(matriz, linhasPossiveis);
	}

	private void analisaCamadaHorizontal02UmQuadrantePreenchido(int[][] matriz) {
		List<Integer> linhasPossiveis = new ArrayList<>();
		linhasPossiveis.add(3); 
		linhasPossiveis.add(4); 
		linhasPossiveis.add(5);
		
		analisaCamadaHorizontal01QuadrantePreenchido(matriz, linhasPossiveis);
	}
	
	private void analisaCamadaHorizontal03UmQuadrantePreenchido(int[][] matriz) {
		List<Integer> linhasPossiveis = new ArrayList<>();
		linhasPossiveis.add(6); 
		linhasPossiveis.add(7); 
		linhasPossiveis.add(8);
		
		analisaCamadaHorizontal01QuadrantePreenchido(matriz, linhasPossiveis);
	}
	
	public void analisaCamadaHorizontal02DoisQuadrantesPreenchidos(int[][] matriz) {
		List<Integer> linhasPossiveis = new ArrayList<>();
		linhasPossiveis.add(3); 
		linhasPossiveis.add(4); 
		linhasPossiveis.add(5);
		
		analisaCamadaHorizontal02QuadrantesPreenchidos(matriz, linhasPossiveis);
	}
	
	private void analisaCamadaHorizontal03DoisQuadrantesPreenchidos(int[][] matriz) {
		List<Integer> linhasPossiveis = new ArrayList<>();
		linhasPossiveis.add(6); 
		linhasPossiveis.add(7); 
		linhasPossiveis.add(8);
		
		analisaCamadaHorizontal02QuadrantesPreenchidos(matriz, linhasPossiveis);
	}
	
	private void analisaCamadaVertical01DoisQuadrantesPreenchidos(int[][] matriz) {
		List<Integer> colunasPossiveis = new ArrayList<>();
		colunasPossiveis.add(0); 
		colunasPossiveis.add(1); 
		colunasPossiveis.add(2);
		
		analisaCamadaVertical02QuadrantesPreenhidos(matriz, colunasPossiveis);
	}
	
	private void analisaCamadaVertical02DoisQuadrantesPreenchidos(int[][] matriz) {
		List<Integer> colunasPossiveis = new ArrayList<>();
		colunasPossiveis.add(3); 
		colunasPossiveis.add(4); 
		colunasPossiveis.add(5);
		
		analisaCamadaVertical02QuadrantesPreenhidos(matriz, colunasPossiveis);
	}
	
	private void analisaCamadaVertical03DoisQuadrantesPreenchidos(int[][] matriz) {
		List<Integer> colunasPossiveis = new ArrayList<>();
		colunasPossiveis.add(6); 
		colunasPossiveis.add(7); 
		colunasPossiveis.add(8);
		
		analisaCamadaVertical02QuadrantesPreenhidos(matriz, colunasPossiveis);
	}

	private void analisaCamadaVertical01UmQuadrantePreenchido(int[][] matriz) {
		List<Integer> colunasPossiveis = new ArrayList<>();
		colunasPossiveis.add(0); 
		colunasPossiveis.add(1); 
		colunasPossiveis.add(2);
		
		analisaCamadaVerticalUmQuadrantePreenchido(matriz, colunasPossiveis);
	}

	private void analisaCamadaVertical02UmQuadrantePreenchido(int[][] matriz) {
		List<Integer> colunasPossiveis = new ArrayList<>();
		colunasPossiveis.add(3); 
		colunasPossiveis.add(4); 
		colunasPossiveis.add(5);
		
		analisaCamadaVerticalUmQuadrantePreenchido(matriz, colunasPossiveis);
	}
	
	private void analisaCamadaVertical03UmQuadrantePreenchido(int[][] matriz) {
		List<Integer> colunasPossiveis = new ArrayList<>();
		colunasPossiveis.add(6); 
		colunasPossiveis.add(7); 
		colunasPossiveis.add(8);
		
		analisaCamadaVerticalUmQuadrantePreenchido(matriz, colunasPossiveis);
	}
	
	private List<Integer> retornaListaQuadrantesHorizontais(int i) {
		List<Integer> quandrantesAnalisados = new ArrayList<>();
		if(i >= 0 && i <= 2) {
			quandrantesAnalisados.add(1);
			quandrantesAnalisados.add(2);
			quandrantesAnalisados.add(3);
			
		} else if(i >= 3 && i <= 5) {
			quandrantesAnalisados.add(4);
			quandrantesAnalisados.add(5);
			quandrantesAnalisados.add(6);
			
		} else if(i >= 6 && i <= 8) {
			quandrantesAnalisados.add(7);
			quandrantesAnalisados.add(8);
			quandrantesAnalisados.add(9);
		}
		return quandrantesAnalisados;
	}

	private List<Integer> retornaListaQuadrantesVerticais(int j) {
		List<Integer> quandrantesAnalisados = new ArrayList<>();
		if(j >= 0 && j <= 2) {
			quandrantesAnalisados.add(1);
			quandrantesAnalisados.add(4);
			quandrantesAnalisados.add(7);
			
		} else if(j >= 3 && j <= 5) {
			quandrantesAnalisados.add(2);
			quandrantesAnalisados.add(5);
			quandrantesAnalisados.add(8);
			
		} else if(j >= 6 && j <= 8) {
			quandrantesAnalisados.add(3);
			quandrantesAnalisados.add(6);
			quandrantesAnalisados.add(9);
		}	
		return quandrantesAnalisados;
	}
	
	public void analisaSolucao(int[][] matriz) {
		
		do {

			// Regra 01
			regra01(matriz);
			
			// Regra 02
			regra02(matriz);

			// Regra 03
			regra03(matriz);

			// Regra 04
			regra04(matriz);
			
			// Regra 05
			regra05(matriz);
			
			// Regra 06
			regra06(matriz);
			
			// Regra 07
			regra07(matriz);
			
			// Regra 08
			regra08(matriz);
			
			// Regra 09
			regra09(matriz);

		} while (SudokuUtil.existeCelulaVazia(matriz));
		
		
		System.out.println("\n\n TERMINOU ANALISE. Qtd infericoes = "+(++SudokuUtil.infericoes));
	}
	
	private List<Integer> linhasQuadrantesNaoPreenchido(
			int quadrante1, int quadrante2, int quadrante3) {
			List<Integer> linhasQuadrante = new ArrayList<>();
			
		// analisa o numero verticalmente no quadrante que faltou
		if(quadrante1 == 0) {
			linhasQuadrante.add(0);
			linhasQuadrante.add(1);
			linhasQuadrante.add(2);
			
		} else if(quadrante2 == 0) {
			linhasQuadrante.add(3);
			linhasQuadrante.add(4);
			linhasQuadrante.add(5);
			
		} else if(quadrante3 == 0) {
			linhasQuadrante.add(6);
			linhasQuadrante.add(7);
			linhasQuadrante.add(8);
		}
		return linhasQuadrante;
	}
	
	private List<Integer> colunasQuadrantesNaoPreenchido(
		int quadrante1, int quadrante2, int quadrante3) {
		List<Integer> colunasQuadrante = new ArrayList<>();
		
		// analisa o numero verticalmente no quadrante que faltou
		if(quadrante1 == 0) {
			colunasQuadrante.add(0);
			colunasQuadrante.add(1);
			colunasQuadrante.add(2);
			
		} else if(quadrante2 == 0) {
			colunasQuadrante.add(3);
			colunasQuadrante.add(4);
			colunasQuadrante.add(5);
			
		} else if(quadrante3 == 0) {
			colunasQuadrante.add(6);
			colunasQuadrante.add(7);
			colunasQuadrante.add(8);
		}
		return colunasQuadrante;
	}

	
	private List<Integer> retornaNumerosEncontrados(int linhaQuadrante01, int linhaQuadrante02, int linhaQuadrante03) {
		List<Integer> numerosEncontrados = new ArrayList<>();
		
		// quadrante 01
		if(linhaQuadrante01 == -1) {
			numerosEncontrados.add(linhaQuadrante02);
			numerosEncontrados.add(linhaQuadrante03);
		}
		// quadrante 02
		else if(linhaQuadrante02 == -1) {
			numerosEncontrados.add(linhaQuadrante01);
			numerosEncontrados.add(linhaQuadrante03);
		}
		// quadrante 03
		else if(linhaQuadrante03 == -1) {
			numerosEncontrados.add(linhaQuadrante01);
			numerosEncontrados.add(linhaQuadrante02);
		}
		return numerosEncontrados;
	}
	
	private List<Integer> retornaLinhasRestantes(int linhaAnalisada, List<Integer> linhasPossiveis) {
		return linhasPossiveis.stream().
				filter(num -> linhaAnalisada != num).collect(Collectors.toList());     
	}	

	private List<Integer> retornaColunasRestantes(int colunaAnalisada, List<Integer> colunasPossiveis) {
		return colunasPossiveis.stream().
				filter(num -> colunaAnalisada != num).collect(Collectors.toList()); 
	}	
	
	private int retornaLinhaRestante(int linhaAnalisada, int linhaEncontradaQuadrante, List<Integer> linhasPossiveis) {
		return linhasPossiveis.stream().
				filter(num -> (linhaAnalisada != num && linhaEncontradaQuadrante != num)).
				collect(Collectors.toList()).get(0); 
	}	

	private int retornaColunaRestante(int colunaAnalisada, int colunaEncontradaQuadrante, List<Integer> colunasPossiveis) {
		return colunasPossiveis.stream().
				filter(num -> (colunaAnalisada != num && colunaEncontradaQuadrante != num)).     
                collect(Collectors.toList()).get(0);
	}	
	
	private void analisaCamadaHorizontal01CelulaPrenchida(
			int[][] matriz, List<Integer> colunasQuadrante,
			int numeroAnalisado, int linhaFalta, int colunaFalta) {
	
		int colunaAnalisar01 = -1;
		int colunaAnalisar02 = -1;
		int colunaVazia01 = -1;
		int colunaVazia02 = -1;

		// deve analisar apenas as colunas que estão vazias naquela linha
		if(matriz[linhaFalta][colunasQuadrante.get(0)] == 0 && matriz[linhaFalta][colunasQuadrante.get(1)] == 0) {
			colunaVazia01 = colunasQuadrante.get(0);
			colunaVazia02 = colunasQuadrante.get(1);
		}
		else if(matriz[linhaFalta][colunasQuadrante.get(0)] == 0 && matriz[linhaFalta][colunasQuadrante.get(2)] == 0) {
			colunaVazia01 = colunasQuadrante.get(0);
			colunaVazia02 = colunasQuadrante.get(2);
		}
		else if(matriz[linhaFalta][colunasQuadrante.get(1)] == 0 && matriz[linhaFalta][colunasQuadrante.get(2)] == 0) {
			colunaVazia01 = colunasQuadrante.get(1);
			colunaVazia02 = colunasQuadrante.get(2);
		}
		
		// nas linhas não preenchidas, verifica se numero existe na linha
		colunaAnalisar01 = SudokuUtil.existeNumeroNaColuna(numeroAnalisado, colunaVazia01, matriz); 
		colunaAnalisar02 = SudokuUtil.existeNumeroNaColuna(numeroAnalisado, colunaVazia02, matriz);
		
		if( (colunaAnalisar01 + colunaAnalisar02 ) == 1) {
			if(colunaAnalisar01 == 0) {
				colunaFalta = colunaVazia01;
			} else if(colunaAnalisar02 == 0) {
				colunaFalta = colunaVazia02;
			} 
			//
			SudokuUtil.setValorNaLinhaColuna(numeroAnalisado, linhaFalta, colunaFalta, matriz, REGRA02_AN_HORIZONTAL);
		}
	}

	private void analisaCamadaHorizontalNenhumaCelulaPrenchida(
			int[][] matriz, List<Integer> colunasQuadrante,
			int numeroAnalisado, int linhaFalta, int colunaFalta) {
	
		int colunaAnalisar01 = -1;
		int colunaAnalisar02 = -1;
		int colunaAnalisar03 = -1;
		int colunaVazia01 = colunasQuadrante.get(0);
		int colunaVazia02 = colunasQuadrante.get(1);
		int colunaVazia03 = colunasQuadrante.get(2);

		// nas linhas não preenchidas, verifica se numero existe na linha
		colunaAnalisar01 = SudokuUtil.existeNumeroNaColuna(numeroAnalisado, colunaVazia01, matriz); 
		colunaAnalisar02 = SudokuUtil.existeNumeroNaColuna(numeroAnalisado, colunaVazia02, matriz);
		colunaAnalisar03 = SudokuUtil.existeNumeroNaColuna(numeroAnalisado, colunaVazia03, matriz);
		
		if( (colunaAnalisar01 + colunaAnalisar02 + colunaAnalisar03 ) == 2) {
			if(colunaAnalisar01 == 0) {
				colunaFalta = colunaVazia01;
			} else if(colunaAnalisar02 == 0) {
				colunaFalta = colunaVazia02;
			} else if(colunaAnalisar03 == 0) {
				colunaFalta = colunaVazia03;
			} 

			SudokuUtil.setValorNaLinhaColuna(numeroAnalisado, linhaFalta, colunaFalta, matriz, REGRA02_AN_HORIZONTAL);
		}
	}
	
	private void analisaCamadaVertical01CelulaPrenchida(
			int[][] matriz,  
			List<Integer> linhasQuadrante,
			int numeroAnalisado, int colunaFalta) {
	
		int linhaAnalisar01 = -1;
		int linhaAnalisar02 = -1;
		int linhaVazia01 = -1;
		int linhaVazia02 = -1;		
		
		if(matriz[linhasQuadrante.get(0)][colunaFalta] == 0 && matriz[linhasQuadrante.get(1)][colunaFalta] == 0) {
			linhaVazia01 = linhasQuadrante.get(0);
			linhaVazia02 = linhasQuadrante.get(1);		
		}
		if(matriz[linhasQuadrante.get(0)][colunaFalta] == 0 && matriz[linhasQuadrante.get(2)][colunaFalta] == 0) {
			linhaVazia01 = linhasQuadrante.get(0);
			linhaVazia02 = linhasQuadrante.get(2);		
		}
		if(matriz[linhasQuadrante.get(1)][colunaFalta] == 0 && matriz[linhasQuadrante.get(2)][colunaFalta] == 0) {
			linhaVazia01 = linhasQuadrante.get(1);
			linhaVazia02 = linhasQuadrante.get(2);		
		}
		
		// nas linhas não preenchidas, verifica se numero existe na linha
		linhaAnalisar01 = SudokuUtil.existeNumeroNaLinha(numeroAnalisado, linhaVazia01, matriz); 
		linhaAnalisar02 = SudokuUtil.existeNumeroNaLinha(numeroAnalisado, linhaVazia02, matriz);		
		
		int linhaFalta = -1; 
		if( (linhaAnalisar01 + linhaAnalisar02 ) == 1) {
			
			if(linhaAnalisar01 == 0) {
				linhaFalta = linhaVazia01;
			} else if(linhaAnalisar02 == 0) {
				linhaFalta = linhaVazia02;
			} 
			
			//
			SudokuUtil.setValorNaLinhaColuna(numeroAnalisado, linhaFalta, colunaFalta, matriz, REGRA03_AN_VERTICAL);
		}
	}
	
	private void analisaCamadaHorizontal02CelulasPrenchidas(
			int[][] matriz, 
			List<Integer> colunasQuadrante,
			int numeroAnalisado, int linhaFalta) {
			
		int colunaFalta = SudokuUtil.retornaColunaVaziaNaLinhaNoQuadrante(linhaFalta, colunasQuadrante, matriz);
		SudokuUtil.setValorNaLinhaColuna(numeroAnalisado, linhaFalta, colunaFalta, matriz, REGRA02_AN_HORIZONTAL);
	}

	private void analisaCamadaVertical02CelulasPrenchidas(
			int[][] matriz, 
			List<Integer> colunasQuadrante,
			int numeroAnalisado, int colunaFalta) {

		int linhaFalta = SudokuUtil.retornaLinhaVaziaNaColunaNoQuadrante(colunaFalta, colunasQuadrante, matriz);
		SudokuUtil.setValorNaLinhaColuna(numeroAnalisado, linhaFalta, colunaFalta, matriz, REGRA03_AN_VERTICAL);
	}
	
	private int retornaQuadrantesVazios(int linhaQuadrante01, int linhaQuadrante02, int linhaQuadrante03) {
		int contadorQuadrantesVazios = 0;
		if(linhaQuadrante01 == -1) contadorQuadrantesVazios++;
		if(linhaQuadrante02 == -1) contadorQuadrantesVazios++;
		if(linhaQuadrante03 == -1) contadorQuadrantesVazios++;		
		
		return contadorQuadrantesVazios;
	}

	// Reduzir de 79 para 15
	private void analisaCamadaHorizontal01QuadrantePreenchido(
		int[][] matriz, List<Integer> linhasPossiveis) {
	
		int numeroAnalisado = 0;
		int quadrante1 = 0;
		int quadrante2 = 0;
		int quadrante3 = 0;	
		int linhaInicio = linhasPossiveis.get(0);
		int linhaFim = linhasPossiveis.get(2);
		int quadranteNaoPreenchido01 = -1;
		int quadranteNaoPreenchido02 = -1;
		List<Integer> colunasQuadrante = new ArrayList<>();
		List<Integer> quandrantesPossiveis = new ArrayList<>();
		
		for (int i = linhaInicio; i <= linhaFim; i++) {
			for (int j = 0; j < matriz.length; j++) {
				
				if(matriz[i][j] != 0) {
					numeroAnalisado = matriz[i][j];
					
					quandrantesPossiveis.clear();
					quandrantesPossiveis.addAll(retornaListaQuadrantesHorizontais(i));	
					
					quadrante1 = SudokuUtil.existeNumeroQuadrante(numeroAnalisado, quandrantesPossiveis.get(0), matriz);
					quadrante2 = SudokuUtil.existeNumeroQuadrante(numeroAnalisado, quandrantesPossiveis.get(1), matriz);
					quadrante3 = SudokuUtil.existeNumeroQuadrante(numeroAnalisado, quandrantesPossiveis.get(2), matriz);
					
					if( (quadrante1 + quadrante2 + quadrante3) == 1) {
						
						exibirMsg("Analisando na Horizontal (01-Quad-Preenc) ("+i+","+j+") = "+numeroAnalisado+"... ", getNivelLog());
						
						// verifica os 02 quadrantes nao preenchidos
						if(quadrante1 == 1) {
							quadranteNaoPreenchido01 = quandrantesPossiveis.get(1);
							quadranteNaoPreenchido02 = quandrantesPossiveis.get(2);
							
						} else if(quadrante2 == 1) {
							quadranteNaoPreenchido01 = quandrantesPossiveis.get(0);
							quadranteNaoPreenchido02 = quandrantesPossiveis.get(2);
							
						} else if(quadrante3 == 1) {
							quadranteNaoPreenchido01 = quandrantesPossiveis.get(0);
							quadranteNaoPreenchido02 = quandrantesPossiveis.get(1);
						}
						
						int linhaNumeroAnalisado = i;
						List<Integer> linhasRestantesParaAnalise = new ArrayList<>();
						linhasRestantesParaAnalise.addAll(retornaLinhasRestantes(linhaNumeroAnalisado, linhasPossiveis)); 
						
						// verifica numero analisado nas linhas restantes e no quadranteNaoPreenchido01
						int linhaQuadrante01 = analisaCamadaHorizontal01QuadrantePreenchidoRetornaLinhaQuandrante(
								matriz, 
								numeroAnalisado,
								quadranteNaoPreenchido01, 
								linhasRestantesParaAnalise);
						
						int linhaQuadrante02 = -1;
						
						if(linhaQuadrante01 != -1) {
							
							// linhaQuadrante02 será a diferença entre linhaNumeroAnalisado e linhaQuadrante01
							linhaQuadrante02 = retornaLinhaRestante(linhaNumeroAnalisado, linhaQuadrante01, linhasPossiveis);
							
							// O numero analisado deve estar na linhaQuadrante02
							// verifica a qtd celulas preenchidas na linha no quadrante
							
							colunasQuadrante = SudokuUtil.retornaColunasQuadrante(quadranteNaoPreenchido02, matriz);

							int qtdNumPreenchidosLinha = 
								qtdNumPreenchidosNaLinhaNoQuadrante(linhaQuadrante02, quadranteNaoPreenchido02, matriz);
							int colunaVazia01 = -1;
							int colunaVazia02 = -1;
							
							// se qtdNumPreenchidosLinha == 2 seta o valor na coluna vazia
							if(qtdNumPreenchidosLinha == 2) {
								
								if(matriz[linhaQuadrante02][colunasQuadrante.get(0)] == 0) {
									colunaVazia01 = colunasQuadrante.get(0);	
								}
								else if(matriz[linhaQuadrante02][colunasQuadrante.get(1)] == 0) {
									colunaVazia01 = colunasQuadrante.get(1);	
								}
								else if(matriz[linhaQuadrante02][colunasQuadrante.get(2)] == 0) {
									colunaVazia01 = colunasQuadrante.get(2);	
								}
								
								SudokuUtil.setValorNaLinhaColuna(numeroAnalisado, linhaQuadrante02, colunaVazia01, matriz, "RG02-AH-(01-Quad-Preenc)");
								
							}
							// se for == 1 analisa
							else if(qtdNumPreenchidosLinha == 1) {

								colunasQuadrante = SudokuUtil.retornaColunasQuadrante(quadranteNaoPreenchido02, matriz);
								
								if(matriz[linhaQuadrante02][colunasQuadrante.get(0)] == 0 && matriz[linhaQuadrante02][colunasQuadrante.get(1)] == 0) {
									colunaVazia01 = colunasQuadrante.get(0);	
									colunaVazia02 = colunasQuadrante.get(1);
								}
								if(matriz[linhaQuadrante02][colunasQuadrante.get(0)] == 0 && matriz[linhaQuadrante02][colunasQuadrante.get(2)] == 0) {
									colunaVazia01 = colunasQuadrante.get(0);	
									colunaVazia02 = colunasQuadrante.get(2);
								}
								if(matriz[linhaQuadrante02][colunasQuadrante.get(1)] == 0 && matriz[linhaQuadrante02][colunasQuadrante.get(2)] == 0) {
									colunaVazia01 = colunasQuadrante.get(1);	
									colunaVazia02 = colunasQuadrante.get(2);
								}
								// 
								if(SudokuUtil.existeNumeroNaColuna(numeroAnalisado, colunaVazia01, matriz) == 0 &&
										SudokuUtil.existeNumeroNaColuna(numeroAnalisado, colunaVazia02, matriz) == 1) {
									
									// a coluna 01 está vazia e a coluna 02 está preenchida
									SudokuUtil.setValorNaLinhaColuna(numeroAnalisado, linhaQuadrante02, colunaVazia01, matriz, "RG02-AH-(01-Quad-Preenc)");
								}
								else if(SudokuUtil.existeNumeroNaColuna(numeroAnalisado, colunaVazia01, matriz) == 1 &&
										SudokuUtil.existeNumeroNaColuna(numeroAnalisado, colunaVazia02, matriz) == 0) {
									
									// a coluna 01 está preenhida e a coluna 02 está vazia
									SudokuUtil.setValorNaLinhaColuna(numeroAnalisado, linhaQuadrante02, colunaVazia02, matriz, "RG02-AH-(01-Quad-Preenc)");
								}
							}
							
						} else if(linhaQuadrante01 == -1) {

							// verifica numero analisado nas linhas restantes e no quadranteNaoPreenchido01
							linhaQuadrante02 = analisaCamadaHorizontal01QuadrantePreenchidoRetornaLinhaQuandrante(
									matriz, 
									numeroAnalisado,
									quadranteNaoPreenchido02, 
									linhasRestantesParaAnalise);

							if(linhaQuadrante02 != -1) {
								
								// linhaQuadrante01 será a diferença entre linhaNumeroAnalisado e linhaQuadrante02
								linhaQuadrante01 = retornaLinhaRestante(linhaNumeroAnalisado, linhaQuadrante02, linhasPossiveis);
							}
						}
					}	
				}
			}
		}	
	}

	private void analisaCamadaVerticalUmQuadrantePreenchido(
		int[][] matriz, List<Integer> colunasPossiveis) {
	
		int numeroAnalisado = 0;
		int quadrante1 = 0;
		int quadrante2 = 0;
		int quadrante3 = 0;	
		int colunaInicio = colunasPossiveis.get(0);
		int colunaFim = colunasPossiveis.get(2);
		int quadranteNaoPreenchido01 = -1;
		int quadranteNaoPreenchido02 = -1;
		List<Integer> linhasQuadrante = null;
		List<Integer> quandrantesPossiveis = new ArrayList<>();
			
			for (int i = 0; i < matriz.length; i++) {
				for (int j = colunaInicio; j <= colunaFim; j++) {
					
					if(matriz[i][j] != 0) {
						numeroAnalisado = matriz[i][j];
						
						quandrantesPossiveis.clear();
						quandrantesPossiveis.addAll(retornaListaQuadrantesVerticais(j));	
						
						quadrante1 = SudokuUtil.existeNumeroQuadrante(numeroAnalisado, quandrantesPossiveis.get(0), matriz);
						quadrante2 = SudokuUtil.existeNumeroQuadrante(numeroAnalisado, quandrantesPossiveis.get(1), matriz);
						quadrante3 = SudokuUtil.existeNumeroQuadrante(numeroAnalisado, quandrantesPossiveis.get(2), matriz);
						
						if( (quadrante1 + quadrante2 + quadrante3) == 1) {
							
							exibirMsg("Analisando na Vertical (01-Quad-Preenc) ("+i+","+j+") = "+numeroAnalisado+"... ", getNivelLog());
							
							// verifica os 02 quadrantes nao preenchidos
							if(quadrante1 == 1) {
								quadranteNaoPreenchido01 = quandrantesPossiveis.get(1);
								quadranteNaoPreenchido02 = quandrantesPossiveis.get(2);
								
							} else if(quadrante2 == 1) {
								quadranteNaoPreenchido01 = quandrantesPossiveis.get(0);
								quadranteNaoPreenchido02 = quandrantesPossiveis.get(2);
								
							} else if(quadrante3 == 1) {
								quadranteNaoPreenchido01 = quandrantesPossiveis.get(0);
								quadranteNaoPreenchido02 = quandrantesPossiveis.get(1);
							}
							
							int colunaNumeroAnalisado = i;
							List<Integer> colunasRestantesParaAnalise = new ArrayList<>();
							colunasRestantesParaAnalise.addAll(retornaColunasRestantes(colunaNumeroAnalisado, colunasPossiveis)); 
							
							// verifica numero analisado nas linhas restantes e no quadranteNaoPreenchido01
							int colunaQuadrante01 = analisaCamadaVertical01QuadrantePreenchido_retornaColunaQuandrante(
									matriz, 
									numeroAnalisado,
									quadranteNaoPreenchido01, 
									colunasRestantesParaAnalise);
							
							int colunaQuadrante02 = -1;
							
							if(colunaQuadrante01 != -1) {
								
								// linhaQuadrante02 será a diferença entre linhaNumeroAnalisado e linhaQuadrante01
								colunaQuadrante02 = retornaColunaRestante(colunaNumeroAnalisado, colunaQuadrante01, colunasPossiveis);
								
								// O numero analisado deve estar na linhaQuadrante02
								// verifica a qtd celulas preenchidas na linha no quadrante
								linhasQuadrante = SudokuUtil.retornaLinhasQuadrante(quadranteNaoPreenchido02, matriz);
								
								int qtdNumPreenchidosColuna = 
									qtdNumPreenchidosNaColunaNoQuadrante(colunaQuadrante02, quadranteNaoPreenchido02, matriz);

								int linhaVazia01 = -1;
								int linhaVazia02 = -1;
								
								// se qtdNumPreenchidosLinha == 2 seta o valor na coluna vazia
								if(qtdNumPreenchidosColuna == 2) {
									
									if(matriz[linhasQuadrante.get(0)][colunaQuadrante02] == 0) {
										linhaVazia01 = linhasQuadrante.get(0);	
									}
									else if(matriz[linhasQuadrante.get(1)][colunaQuadrante02] == 0) {
										linhaVazia01 = linhasQuadrante.get(1);	
									}
									else if(matriz[linhasQuadrante.get(2)][colunaQuadrante02] == 0) {
										linhaVazia01 = linhasQuadrante.get(2);	
									}
									
									SudokuUtil.setValorNaLinhaColuna(numeroAnalisado, linhaVazia01, colunaQuadrante02, matriz, "RG02-AV-(01-Quad-Preenc)");
									
								}
								// se for == 1 analisa
								else if(qtdNumPreenchidosColuna == 1) {

									linhasQuadrante = SudokuUtil.retornaLinhasQuadrante(quadranteNaoPreenchido02, matriz);
									
									if(matriz[linhasQuadrante.get(0)][colunaQuadrante02] == 0 && 
										matriz[linhasQuadrante.get(1)][colunaQuadrante02] == 0) {
										linhaVazia01 = linhasQuadrante.get(0);	
										linhaVazia02 = linhasQuadrante.get(1);
									}
									if(matriz[linhasQuadrante.get(0)][colunaQuadrante02] == 0 && 
										matriz[linhasQuadrante.get(2)][colunaQuadrante02] == 0) {
										linhaVazia01 = linhasQuadrante.get(0);	
										linhaVazia02 = linhasQuadrante.get(2);
									}
									if(matriz[linhasQuadrante.get(1)][colunaQuadrante02] == 0 && 
										matriz[linhasQuadrante.get(2)][colunaQuadrante02] == 0) {
										linhaVazia01 = linhasQuadrante.get(1);	
										linhaVazia02 = linhasQuadrante.get(2);
									}
									// 
									if(SudokuUtil.existeNumeroNaLinha(numeroAnalisado, linhaVazia01, matriz) == 0 &&
											SudokuUtil.existeNumeroNaLinha(numeroAnalisado, linhaVazia02, matriz) == 1) {
										
										// a coluna 01 está vazia e a coluna 02 está preenchida
										SudokuUtil.setValorNaLinhaColuna(numeroAnalisado, colunaQuadrante02, linhaVazia01, matriz, "RG02-AV-(01-Quad-Preenc)");
									}
									else if(SudokuUtil.existeNumeroNaLinha(numeroAnalisado, linhaVazia01, matriz) == 1 &&
											SudokuUtil.existeNumeroNaLinha(numeroAnalisado, linhaVazia02, matriz) == 0) {
										
										// a coluna 01 está preenhida e a coluna 02 está vazia
										SudokuUtil.setValorNaLinhaColuna(numeroAnalisado, linhaVazia02, colunaQuadrante02, matriz, "RG02-AV-(01-Quad-Preenc)");
									}
								}
								
							} else if(colunaQuadrante01 == -1) {

								// verifica numero analisado nas linhas restantes e no quadranteNaoPreenchido01
								colunaQuadrante02 = analisaCamadaVertical01QuadrantePreenchido_retornaColunaQuandrante(
										matriz, 
										numeroAnalisado,
										quadranteNaoPreenchido02, 
										colunasRestantesParaAnalise);
							}
						}	
					}
				}
			}	
		}
	
	private int analisaCamadaHorizontal01QuadrantePreenchidoRetornaLinhaQuandrante(
			int[][] matriz, 
			int numeroAnalisado,
			int quadranteNaoPreenchido01, 
			List<Integer> linhasRestantesParaAnalise) {

		int linhaNumeroAnalisadoPrimeiroQuadrante = -1;
		int numerosPreenchidosLinha01 = 
				qtdNumPreenchidosNaLinhaNoQuadrante(linhasRestantesParaAnalise.get(0), quadranteNaoPreenchido01, matriz);
		int numerosPreenchidosLinha02 = 
				qtdNumPreenchidosNaLinhaNoQuadrante(linhasRestantesParaAnalise.get(1), quadranteNaoPreenchido01, matriz);
		
		int colunaVazia = -1;
		List<Integer> colunasQuadrante = new ArrayList<>();
		colunasQuadrante = SudokuUtil.retornaColunasQuadrante(quadranteNaoPreenchido01, matriz);
		
		if(numerosPreenchidosLinha01 == 2) {
			if(matriz[linhasRestantesParaAnalise.get(0)][colunasQuadrante.get(0)] == 0) {
				colunaVazia = colunasQuadrante.get(0);	
			}
			if(matriz[linhasRestantesParaAnalise.get(0)][colunasQuadrante.get(1)] == 0) {
				colunaVazia = colunasQuadrante.get(1);	
			}
			if(matriz[linhasRestantesParaAnalise.get(0)][colunasQuadrante.get(2)] == 0) {
				colunaVazia = colunasQuadrante.get(2);	
			}
			if(SudokuUtil.existeNumeroNaColuna(numeroAnalisado, colunaVazia, matriz) == 1) {
				linhaNumeroAnalisadoPrimeiroQuadrante = linhasRestantesParaAnalise.get(1);
			}
		} else if(numerosPreenchidosLinha02 == 2) {
			if(matriz[linhasRestantesParaAnalise.get(1)][colunasQuadrante.get(0)] == 0) {
				colunaVazia = colunasQuadrante.get(0);	
			}
			if(matriz[linhasRestantesParaAnalise.get(1)][colunasQuadrante.get(1)] == 0) {
				colunaVazia = colunasQuadrante.get(1);	
			}
			if(matriz[linhasRestantesParaAnalise.get(1)][colunasQuadrante.get(2)] == 0) {
				colunaVazia = colunasQuadrante.get(2);	
			}
			if(SudokuUtil.existeNumeroNaColuna(numeroAnalisado, colunaVazia, matriz) == 1) {
				linhaNumeroAnalisadoPrimeiroQuadrante = linhasRestantesParaAnalise.get(0);
			}
		}
		return linhaNumeroAnalisadoPrimeiroQuadrante;
	}
	
	private int analisaCamadaVertical01QuadrantePreenchido_retornaColunaQuandrante(
			int[][] matriz, 
			int numeroAnalisado,
			int quadranteNaoPreenchido01, 
			List<Integer> colunasRestantesParaAnalise) {
		
		return -1;
	}
	
	private int qtdNumPreenchidosNaLinhaNoQuadrante(int linhaAnalisada, int quadrante, int[][] matriz) {
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

	private int qtdNumPreenchidosNaColunaNoQuadrante(int colunaAnalisada, int quadrante, int[][] matriz) {
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
	
	private static void exibirMsg(String msg, String mode) {
		if(DEBUG_MODE.equals(mode)) {
			System.out.println(msg);
		}
	}
	
	// TODO Reduzir de 33 para 15
	private void analisaCamadaHorizontal02QuadrantesPreenchidos(
			int[][] matriz, List<Integer> linhasPossiveis) {
		//
		int numeroAnalisado = 0;
		int quadrante1 = 0;
		int quadrante2 = 0;
		int quadrante3 = 0;	
		int linhaFalta = -1;	
		int colunaFalta = -1;
		int linhaQuadrante01 = -1;
		int linhaQuadrante02 = -1;
		int linhaQuadrante03 = -1;
		int linhaInicio = linhasPossiveis.get(0);
		int linhaFim = linhasPossiveis.get(2);
		List<Integer> colunasQuadrante = new ArrayList<>();
		List<Integer> quandrantesPossiveis = new ArrayList<>();
		
		for (int i = linhaInicio; i <= linhaFim; i++) {
			for (int j = 0; j < matriz.length; j++) {
				
				if(matriz[i][j] != 0) {
					numeroAnalisado = matriz[i][j];
					
					quandrantesPossiveis.clear();
					quandrantesPossiveis.addAll(retornaListaQuadrantesHorizontais(i));
					
					quadrante1 = SudokuUtil.existeNumeroQuadrante(numeroAnalisado, quandrantesPossiveis.get(0), matriz);
					quadrante2 = SudokuUtil.existeNumeroQuadrante(numeroAnalisado, quandrantesPossiveis.get(1), matriz);
					quadrante3 = SudokuUtil.existeNumeroQuadrante(numeroAnalisado, quandrantesPossiveis.get(2), matriz);
					
					if( (quadrante1 + quadrante2 + quadrante3) == 2) {
						
						exibirMsg("Analisando na Horizontal (02-Quads-Preenc) ("+i+","+j+") = "+numeroAnalisado+"... ", getNivelLog());
						
						// analisa 
						linhaQuadrante01 = SudokuUtil.qualLinhaNumeroEstaNoQuadrante(numeroAnalisado, quandrantesPossiveis.get(0), matriz); // 1
						linhaQuadrante02 = SudokuUtil.qualLinhaNumeroEstaNoQuadrante(numeroAnalisado, quandrantesPossiveis.get(1), matriz); // -1
						linhaQuadrante03 = SudokuUtil.qualLinhaNumeroEstaNoQuadrante(numeroAnalisado, quandrantesPossiveis.get(2), matriz); // 2
						
						int contadorQuadrantesVazios = 					
								retornaQuadrantesVazios(linhaQuadrante01, linhaQuadrante02, linhaQuadrante03); 
						
						if(contadorQuadrantesVazios == 1) {

							List<Integer> linhasEncontradas = retornaNumerosEncontrados(linhaQuadrante01, linhaQuadrante02, linhaQuadrante03);
							
							// ver a diferenca
							linhaFalta =  linhasPossiveis.stream()
									.distinct().
									filter(aObject -> !linhasEncontradas.contains(aObject)).
									collect(Collectors.toList()).get(0);
							
							// analisa o numero verticalmente no quadrante que faltou
							colunasQuadrante.clear();
							colunasQuadrante.addAll(colunasQuadrantesNaoPreenchido(quadrante1, quadrante2, quadrante3));
							
							// Verifica quantas celulas estão preenchidas naquele quadrante e naquela coluna
							int celulasPrenchidas = SudokuUtil.qtdCelulasPreenchidasNasColunasNaLinha(colunasQuadrante, linhaFalta, matriz);
							
							if(celulasPrenchidas == 0) {
								
								analisaCamadaHorizontalNenhumaCelulaPrenchida(
									matriz,  
									colunasQuadrante,
									numeroAnalisado, 
									linhaFalta, 
									colunaFalta); 

							}
							if(celulasPrenchidas == 1) {
								
							    // nas linhas não preenchidas, verifica se numero existe na linha
								analisaCamadaHorizontal01CelulaPrenchida(
										matriz,  
										colunasQuadrante,
										numeroAnalisado, 
										linhaFalta, 
										colunaFalta); 
								
							} // fim de if(celulasPrenchidas == 1) {
							
							// Se celulasPrenchidas == 2 OK, só tem uma celula vazia na colua
							// seta o valor
							if(celulasPrenchidas == 2) {

								analisaCamadaHorizontal02CelulasPrenchidas(
									matriz, 
									colunasQuadrante,
									numeroAnalisado, 
									linhaFalta);
								
							} // fim de if(celulasPrenchidas == 2) {
						}
					}
				}
			}
		}
	}

	// TODO Reduzir de 27 para 15
	private void analisaCamadaVertical02QuadrantesPreenhidos(
			int[][] matriz, List<Integer> colunasPossiveis) {
		//
		int numeroAnalisado = 0;
		int quadrante1 = 0;
		int quadrante2 = 0;
		int quadrante3 = 0;	
		int colunaFalta = -1;
		int colunaQuadrante01 = -1;
		int colunaQuadrante02 = -1;
		int colunaQuadrante03 = -1;
		int colunaInicio = colunasPossiveis.get(0);
		int colunaFim = colunasPossiveis.get(2);
		List<Integer> linhasQuadrante = new ArrayList<>();
		List<Integer> quandrantesPossiveis = new ArrayList<>();
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = colunaInicio; j <= colunaFim; j++) {
				
				if(matriz[i][j] != 0) {
					numeroAnalisado = matriz[i][j];
					
					quandrantesPossiveis.clear();
					quandrantesPossiveis.addAll(retornaListaQuadrantesVerticais(j));
					
					quadrante1 = SudokuUtil.existeNumeroQuadrante(numeroAnalisado, quandrantesPossiveis.get(0), matriz);
					quadrante2 = SudokuUtil.existeNumeroQuadrante(numeroAnalisado, quandrantesPossiveis.get(1), matriz);
					quadrante3 = SudokuUtil.existeNumeroQuadrante(numeroAnalisado, quandrantesPossiveis.get(2), matriz);
					
					if( (quadrante1 + quadrante2 + quadrante3) == 2) {
						
						exibirMsg("Analisando na Vertical (02-Quads-Preench) ("+i+","+j+") = "+numeroAnalisado+"... ", getNivelLog());
						
						// analisa 
						colunaQuadrante01 = SudokuUtil.qualColunaNumeroEstaNoQuadrante(numeroAnalisado, quandrantesPossiveis.get(0), matriz); // 1
						colunaQuadrante02 = SudokuUtil.qualColunaNumeroEstaNoQuadrante(numeroAnalisado, quandrantesPossiveis.get(1), matriz); // -1
						colunaQuadrante03 = SudokuUtil.qualColunaNumeroEstaNoQuadrante(numeroAnalisado, quandrantesPossiveis.get(2), matriz); // 2
						
						int contadorQuadrantesVazios = 					
								retornaQuadrantesVazios(colunaQuadrante01, colunaQuadrante02, colunaQuadrante03); 
						
						if(contadorQuadrantesVazios == 1) {

							List<Integer> colunasEncontradas = retornaNumerosEncontrados(colunaQuadrante01, colunaQuadrante02, colunaQuadrante03);
							
							// ver a diferenca
							colunaFalta =  colunasPossiveis.stream()
									.distinct().
									filter(aObject -> !colunasEncontradas.contains(aObject)).
									collect(Collectors.toList()).get(0);
							
							// analisa o numero verticalmente no quadrante que faltou
							linhasQuadrante.clear();
							linhasQuadrante.addAll(linhasQuadrantesNaoPreenchido(quadrante1, quadrante2, quadrante3));
							
							// verifica quantas celulas estão preenchidas naquele quadrante e naquela coluna
							int celulasPrenchidas = SudokuUtil.qtdCelulasPreenchidasNasLinhasNaColuna(linhasQuadrante, colunaFalta, matriz);
							
							if(celulasPrenchidas == 1) {
								
							    // nas linhas não preenchidas, verifica se numero existe na linha
								analisaCamadaVertical01CelulaPrenchida(
									matriz, 
									linhasQuadrante,
									numeroAnalisado, 
									colunaFalta); 
								
							} // fim de if(celulasPrenchidas == 1) {
							
							// Se celulasPrenchidas == 2 OK, só tem uma celula vazia na colua
							// seta o valor
							if(celulasPrenchidas == 2) {

								analisaCamadaVertical02CelulasPrenchidas(
									matriz, 
									linhasQuadrante,
									numeroAnalisado, 
									colunaFalta); 
								
							} // fim de if(celulasPrenchidas == 2) {
						}
					}
				}
			}
		}
	}
}
