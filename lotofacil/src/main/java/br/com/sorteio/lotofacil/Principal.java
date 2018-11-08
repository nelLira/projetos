package br.com.sorteio.lotofacil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import com.opencsv.CSVWriter;

import br.com.sorteio.combinacoes.Combinacao;
import br.com.sorteio.numeros.Jogo;
import br.com.sorteio.util.Estatisticas;

public class Principal {

	public static List<Jogo> listaTodosJogos;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// https://www.youtube.com/watch?v=iU8djC2h_x8
		// https://www.youtube.com/watch?v=myIsZUHeS-k
		
		Estatisticas est = new Estatisticas();
		listaTodosJogos = est.lerTodosOsJogos();
		
		// 8:214[428]
		// 9:308 [567]
		// 10:170[361]
		// 7;8;9;10;11:849[1658]
		// 8;9;10:692[1368]
		// 11:66[139]
		// novaSimulacao();
		// simulacaoBoleanos();
		// consultaSorteio();
		// estatisticasUltimosSorteio(50);
		// jogos();
		// gerarJogos(50);
		// gerarJogosArq(5);
		   gerarJogosCSV(50);
		// consultarSorteioArq();
		// estatisticasUltimosSorteioCSV(50);
		// filtaListaJogosBoleanos();
	    // List<Jogo> jogos = new ArrayList<Jogo>();
		// consultaSorteio(jogos);
		// estatSorteio();
		
		// próximo 1726

	}

	private static void filtaListaJogosBoleanos() throws FileNotFoundException, IOException {
		List<Jogo> listaJogosSemFiltro = new ArrayList<Jogo>();
		List<Jogo> listaJogosComFiltro = new ArrayList<Jogo>();

		FiltroJogos filtro = new FiltroJogos();

		// listaJogosComFiltro.addAll(filtro.filtraListaBoolean(listaJogosSemFiltro));

	}

	private static void simulacaoBoleanos() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		List<Jogo> listaTodosResultados = est.lerTodosOsJogos();
		FiltroJogos filtro = new FiltroJogos();
		FiltroJogos.iniciaListaJogosPorDemanda();

		Jogo primeiroJogo = null;
		int cont = 0;
		for (Jogo proximoJogo : listaTodosResultados) {

			FiltroJogos.setJogolistaJogosPorDemanda(proximoJogo);
			if (primeiroJogo == null) {
				primeiroJogo = proximoJogo;
			} else {

				if (filtro.getListaJogosPorDemanda().size() > 9) {
					filtro.filtraBoolean(primeiroJogo);
				}
				primeiroJogo = proximoJogo;
			}
		}

	}

	private static void simulacao() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		// List<Jogo> todosJogos = est.lerTodosOsJogos();
		// listaTodosJogos = todosJogos;
		FiltroJogos filtro = new FiltroJogos();
		FiltroJogos.iniciaListaJogosPorDemanda();
		Jogo primeiroJogo = null;
		int contAcertos = 0;
		List<Long> numeroSorteio = new ArrayList<Long>();
		List<Long> posicoesSorteio = new ArrayList<Long>();
		List<Jogo> jogosAcertados = new ArrayList<Jogo>();
		long qtdJogos = 0;
		long posicaoJogo = 0;
		for (Jogo proximoJogo : listaTodosJogos) {
			if (primeiroJogo == null) {
				primeiroJogo = proximoJogo;
			} else {
				FiltroJogos.setJogolistaJogosPorDemanda(primeiroJogo);
				primeiroJogo = proximoJogo;
				qtdJogos++;

//				if (qtdJogos < 10) {
//					continue;
//				}
				System.err.println("Jogo: " + qtdJogos + " de " + listaTodosJogos.size());
				// List<Jogo> listOpcoesJogos = jogos(new
				// Jogo(primeiroJogo.getJogo()));
				List<Jogo> listOpcoesJogos = jogos(new Jogo(filtro.listaJogosPorDemanda().get(filtro.listaJogosPorDemanda().size() - 1).getJogo()));

				posicaoJogo = 0;
				for (Jogo opcoesJogos : listOpcoesJogos) {
					if (opcoesJogos.getJogo().equals(proximoJogo.getJogo())) {
						contAcertos++;
						posicoesSorteio.add(posicaoJogo);
						numeroSorteio.add(qtdJogos + 1);
						jogosAcertados.add(opcoesJogos);
					}
					posicaoJogo++;
				}

			}

		}

		int sorteado = 0;
		System.err.println("Quantidade: " + contAcertos);
		for (Jogo jogo : jogosAcertados) {

			Jogo strUltimoSorteio = listaTodosJogos.get(numeroSorteio.get(sorteado).intValue() - 1);
			Jogo strSorteioAnterior = listaTodosJogos.get(numeroSorteio.get(sorteado).intValue() - 2);

			System.out.println("Sorteio: " + numeroSorteio.get(sorteado) + "; Posicao -> "
					+ posicoesSorteio.get(sorteado) + "; Jogo: " + jogo.getJogo() + " Repetidos: "
					+ est.quantidadeNumerosRepetidos(strUltimoSorteio, strSorteioAnterior));
			sorteado++;
		}
	}

	private static void novaSimulacao() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		// List<Jogo> todosJogos = est.lerTodosOsJogos();
		// listaTodosJogos = todosJogos;
		FiltroJogos.iniciaListaJogosPorDemanda();
		Jogo primeiroJogo = null;
		int contAcertos = 0;
		List<Long> numeroSorteio = new ArrayList<Long>();
		List<Long> posicoesSorteio = new ArrayList<Long>();
		List<Jogo> jogosAcertados = new ArrayList<Jogo>();
		long qtdJogos = 0;
		long posicaoJogo = 0;
		for (Jogo proximoJogo : listaTodosJogos) {
			if (primeiroJogo == null) {
				primeiroJogo = proximoJogo;
			} else {
				FiltroJogos.setJogolistaJogosPorDemanda(primeiroJogo);
				qtdJogos++;
				System.err.println("Jogo: " + qtdJogos + " de " + listaTodosJogos.size());
				List<Jogo> listOpcoesJogos = jogosBoleanos(new Jogo(primeiroJogo.getJogo()));
				posicaoJogo = 0;
				for (Jogo opcoesJogos : listOpcoesJogos) {
					if (opcoesJogos.getJogo().equals(proximoJogo.getJogo())) {
						contAcertos++;
						posicoesSorteio.add(posicaoJogo);
						numeroSorteio.add(qtdJogos + 1);
						jogosAcertados.add(opcoesJogos);
					}
					posicaoJogo++;
				}
				primeiroJogo = proximoJogo;
			}

		}

		int sorteado = 0;
		System.err.println("Quantidade: " + contAcertos);
		for (Jogo jogo : jogosAcertados) {

			Jogo strUltimoSorteio = listaTodosJogos.get(numeroSorteio.get(sorteado).intValue() - 1);
			Jogo strSorteioAnterior = listaTodosJogos.get(numeroSorteio.get(sorteado).intValue() - 2);

			System.out.println("Sorteio: " + numeroSorteio.get(sorteado) + "; Posicao -> "
					+ posicoesSorteio.get(sorteado) + "; Jogo: " + jogo.getJogo() + " Repetidos: "
					+ est.quantidadeNumerosRepetidos(strUltimoSorteio, strSorteioAnterior));
			sorteado++;
		}
	}

	private static void consultaSorteio() throws FileNotFoundException, IOException {

		// Estatisticas est = new Estatisticas();
		// listaTodosJogos = est.lerTodosOsJogos();

		FiltroJogos.iniciaListaJogosPorDemanda();
		FiltroJogos.setAllJogolistaJogosPorDemanda(listaTodosJogos);

		Jogo strUltimoSorteio = listaTodosJogos.get(listaTodosJogos.size() - 1);
		Jogo strSorteioAnterior = listaTodosJogos.get(listaTodosJogos.size() - 2);

		List<Jogo> listaJogosFiltrados = jogos(strSorteioAnterior);

		for (Jogo jogo : listaJogosFiltrados) {
			// System.out.println(jogo.getJogo());
			if (jogo.getJogo().equals(strUltimoSorteio.getJogo())) {
				System.out.println(jogo.getJogo());
				System.out.println("Acertou");
			}
		}

		System.out.println("FIM");

	}

	private static void consultaSorteio(List<Jogo> jogos) throws FileNotFoundException, IOException {

		// Estatisticas est = new Estatisticas();
		// listaTodosJogos = est.lerTodosOsJogos();

		Jogo strUltimoSorteio = listaTodosJogos.get(listaTodosJogos.size() - 1);

		System.err.println("Último sorteio:" + strUltimoSorteio.getJogo() + "\n");
		for (Jogo jogo : jogos) {

			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(jogo.getJogo());
			if (intersection.size() == 15) {
				System.err.println(jogo.getJogo() + ": " + intersection.size());
			} else {
				System.out.println(jogo.getJogo() + ": " + intersection.size());
			}

		}

		System.out.println("FIM");

	}

	private static void consultarSorteioArq() throws FileNotFoundException, IOException {

		Long numeroJogo = (long) (listaTodosJogos.size());
		String nomeArquivo = "jogo_" + numeroJogo + ".txt";
		String path = ClassLoader.getSystemResource("").getPath() + nomeArquivo;
		File file = new File(path);
		byte[] bytes = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);

		fis.read(bytes);
		fis.close();

		String[] valueStr = new String(bytes).trim().split("\\s+");

		List<Jogo> jogos = new ArrayList<Jogo>();

		List<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < valueStr.length; i++) {
			lista.add(Integer.parseInt(valueStr[i]));
			if (lista.size() == 15) {
				jogos.add(new Jogo(lista));
				lista = new ArrayList<Integer>();
			}
		}

		consultaSorteio(jogos);

	}

	public static void estatisticasUltimosSorteio(int qtdJogos) throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();
		Jogo pares = new Jogo(Arrays.asList(1,3,5,7,9,11,13,15,17,19,21,23,25));
		Jogo primos = new Jogo(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23));
		Jogo fibonacci = new Jogo(Arrays.asList(1, 2, 3, 5, 8, 13, 21));
		Jogo quadrado = new Jogo(Arrays.asList(1,2,3,4,5,6,11,16,21,22,23,24,25,20,15,10));
		Jogo multiplosDeTres = new Jogo(Arrays.asList(3,6,9,12,15,18,21,24));
		//Map<Integer, Integer> mapaEstatisticasUlitmos =  estatisticasJogos();
		for (int i = todosJogos.size() - (qtdJogos); i < todosJogos.size(); i++) {
			Set<Integer> intersectionRepetidos = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionRepetidos.retainAll(todosJogos.get(i-1).getJogo());
						
			Set<Integer> intersectionPares = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionPares.retainAll(pares.getJogo());
			
			Set<Integer> intersectionPrimos = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionPrimos.retainAll(primos.getJogo());
			
			Set<Integer> intersectionFibonacci = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionFibonacci.retainAll(fibonacci.getJogo());
			
			Set<Integer> intersectionQuadrado = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionQuadrado.retainAll(quadrado.getJogo());
			
			Set<Integer> intersectionMultiplosDeTres = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionMultiplosDeTres.retainAll(multiplosDeTres.getJogo());
			
			Map<Integer, Integer> mapaEstatisticasJogos = estatisticasJogos(i);

			Jogo dezPrimeiros = dezJogosMaisFrequentes(mapaEstatisticasJogos);
			Jogo cincoDoMeio = cincoJogosDoMeio(mapaEstatisticasJogos);
			Jogo dezUltimos = dezJogosMenosFrequentes(mapaEstatisticasJogos);
			
			Set<Integer> intersectionDezPrimeiros = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionDezPrimeiros.retainAll(dezPrimeiros.getJogo());
			
			Set<Integer> intersectionCincoDoMeio = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionCincoDoMeio.retainAll(cincoDoMeio.getJogo());
			
			Set<Integer> intersectionDezUltimos = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionDezUltimos.retainAll(dezUltimos.getJogo());
			
			System.out.println(
					//"Jogo: " + todosJogos.get(i).getJogo() + 
					" Repetidos: " + intersectionRepetidos.size() 
								+ " Pares -> " + intersectionPares.size() 
							 	+ " Primos -> " + intersectionPrimos.size() 
							 	+ " Fibonacci -> " + intersectionFibonacci.size() 
							 	+ " Quadrado -> " + intersectionQuadrado.size()
							 	+ " Multiplos de Três -> " + intersectionMultiplosDeTres.size() 
							 	+ " Dez primeiros est. -> " + intersectionDezPrimeiros.size()
							 	+ " Cinco do meio esta. ->  " + intersectionCincoDoMeio.size()
							 	+ " Dez últimos est. -> " + intersectionDezUltimos.size()
								);
			
			
		}
		System.out.println(estatisticasJogos(todosJogos.size()));
	}
	
	
	public static void estatisticasUltimosSorteioCSV(int qtdJogos) throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();
		Jogo pares = new Jogo(Arrays.asList(1,3,5,7,9,11,13,15,17,19,21,23,25));
		Jogo primos = new Jogo(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23));
		Jogo fibonacci = new Jogo(Arrays.asList(1, 2, 3, 5, 8, 13, 21));
		Jogo quadrado = new Jogo(Arrays.asList(1,2,3,4,5,6,11,16,21,22,23,24,25,20,15,10));
		Jogo multiplosDeTres = new Jogo(Arrays.asList(3,6,9,12,15,18,21,24));
		//Map<Integer, Integer> mapaEstatisticasUlitmos =  estatisticasJogos();
		List<String> resultCSV = new ArrayList<String>();
		for (int i = todosJogos.size() - (qtdJogos); i < todosJogos.size(); i++) {
			Set<Integer> intersectionRepetidos = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionRepetidos.retainAll(todosJogos.get(i-1).getJogo());
						
			Set<Integer> intersectionPares = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionPares.retainAll(pares.getJogo());
			
			Set<Integer> intersectionPrimos = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionPrimos.retainAll(primos.getJogo());
			
			Set<Integer> intersectionFibonacci = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionFibonacci.retainAll(fibonacci.getJogo());
			
			Set<Integer> intersectionQuadrado = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionQuadrado.retainAll(quadrado.getJogo());
			
			Set<Integer> intersectionMultiplosDeTres = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionMultiplosDeTres.retainAll(multiplosDeTres.getJogo());
			
			Map<Integer, Integer> mapaEstatisticasJogos = estatisticasJogos(i);

			Jogo dezPrimeiros = dezJogosMaisFrequentes(mapaEstatisticasJogos);
			Jogo cincoDoMeio = cincoJogosDoMeio(mapaEstatisticasJogos);
			Jogo dezUltimos = dezJogosMenosFrequentes(mapaEstatisticasJogos);
			
			Set<Integer> intersectionDezPrimeiros = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionDezPrimeiros.retainAll(dezPrimeiros.getJogo());
			
			Set<Integer> intersectionCincoDoMeio = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionCincoDoMeio.retainAll(cincoDoMeio.getJogo());
			
			Set<Integer> intersectionDezUltimos = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionDezUltimos.retainAll(dezUltimos.getJogo());
			
			resultCSV.add (i +
					//"Jogo: " + todosJogos.get(i).getJogo() + 
					"#Repetidos#" + intersectionRepetidos.size() 
								+ "#Pares#" + intersectionPares.size() 
							 	+ "#Primos#" + intersectionPrimos.size() 
							 	+ "#Fibonacci#" + intersectionFibonacci.size() 
							 	+ "#Quadrado#" + intersectionQuadrado.size()
							 	+ "#Multiplos de Tres#" + intersectionMultiplosDeTres.size() 
							 	+ "#Dez primeiros est.#" + intersectionDezPrimeiros.size()
							 	+ "#Cinco do meio est.#" + intersectionCincoDoMeio.size()
							 	+ "#Dez ultimos est.#" + intersectionDezUltimos.size()
								);
			
			
		}
		resultCSV.add(estatisticasJogos(todosJogos.size()).toString());
		
		
		
		gerarCSV(resultCSV,"estatísticas"); 
		
	}

	public static List<Jogo> jogos(Jogo strUltimoSorteio) throws FileNotFoundException, IOException {

		FiltroJogos jogos = FiltroDeJogos(strUltimoSorteio);

		return jogos.getListaDeJogos();
		// System.out.println(numerosCombinadosFiltrados.size());
	}

	public static List<Jogo> jogosBoleanos(Jogo strUltimoSorteio) throws FileNotFoundException, IOException {

		FiltroJogos jogos = FiltroDeJogosBoleano(strUltimoSorteio);

		return jogos.getListaDeJogos();
		// System.out.println(numerosCombinadosFiltrados.size());
	}

	public static List<Jogo> jogos() throws FileNotFoundException, IOException {

		// Estatisticas est = new Estatisticas();
		// listaTodosJogos = est.lerTodosOsJogos();

		Jogo strUltimoSorteio = listaTodosJogos.get(listaTodosJogos.size() - 1);

		FiltroJogos jogos = FiltroDeJogos(strUltimoSorteio);

		////////////// * FIM -> COMBINAÇÕES DOS DOIS GRUPOS, 6 e
		////////////// 9*////////////////////
		return jogos.getListaDeJogos();
		// System.out.println(numerosCombinadosFiltrados.size());
	}

	public static FiltroJogos FiltroDeJogos(Jogo strUltimoSorteio) throws FileNotFoundException, IOException {
		Jogo strNumerosNaoSorteados = strUltimoSorteio.numerosNaoSorteados(strUltimoSorteio);
		FiltroJogos jogos = new FiltroJogos();

		// Fechamento fechamento = new Fechamento();
		// fechamento.filtrar();
		// jogos.getListaDeJogos().addAll(fechamento.getListaJogos());

		// if (jogos.getListaJogosPorDemanda().size() > 10) {
		// jogos.retiraNumeros(strNumerosNaoSorteados,
		// jogos.getListaJogosPorDemanda());
		// jogos.retiraNumeros(strUltimoSorteio,
		// jogos.getListaJogosPorDemanda());
		// }

		//
		// jogos.getListaDeJogos().addAll(CombinacoesSeteOito(strUltimoSorteio,
		// strNumerosNaoSorteados));
		//

		///
		 jogos.getListaDeJogos().addAll(CombinacoesOitoSete(strUltimoSorteio, strNumerosNaoSorteados));

		// Jogo numeros = new Jogo(jogoVinteNumeros().getJogo());
		// System.out.println(numeros.getJogo());
		// List<Jogo> listOpcoesJogos = combinacoes(numeros);
		// jogos.getListaDeJogos().addAll(listOpcoesJogos);

		// jogos.getListaDeJogos().addAll(jogos.combinaDezUltimosPrimeiosMeio());

		// jogos.getListaDeJogos().addAll(CombinacoesNoveSeis(strUltimoSorteio,strNumerosNaoSorteados));
		//jogos.getListaDeJogos().addAll(jogos.analiseEstatisticasJogo());

		// jogos.getListaDeJogos().addAll(CombinacoesDezCinco(strUltimoSorteio,
		// strNumerosNaoSorteados));

		//

		//
		// jogos.getListaDeJogos().addAll(CombinacoesOnzeQuatro(strUltimoSorteio,
		// strNumerosNaoSorteados));
		//

		/*
		 * testes com o total de números Jogo jogo = new Jogo(Arrays.asList(1,
		 * 2, 3, 4, 5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25));
		 * Combinacao combinacoes = new Combinacao(jogo.getJogo(), 15);
		 * jogos.getListaDeJogos().addAll(combinacoes.geraListaCombinacoes());
		 */

		System.out.println("Tamanho total da lista:  " + jogos.getListaDeJogos().size());
		jogos.setListaTodosJogos(listaTodosJogos);
		jogos.filtrar();
		return jogos;
	}

	private static List<Jogo> combinacoes(Jogo numeros) {
		Combinacao combinacoes = new Combinacao(numeros.getJogo(), 15);
		return combinacoes.geraListaCombinacoes();
	}

	public static FiltroJogos FiltroDeJogosBoleano(Jogo strUltimoSorteio) throws FileNotFoundException, IOException {
		Jogo strNumerosNaoSorteados = strUltimoSorteio.numerosNaoSorteados(strUltimoSorteio);
		FiltroJogos jogos = new FiltroJogos();

		// if (jogos.getListaJogosPorDemanda().size() > 10) {
		// jogos.retiraNumeros(strNumerosNaoSorteados,
		// jogos.getListaJogosPorDemanda());
		// jogos.retiraNumeros(strUltimoSorteio,
		// jogos.getListaJogosPorDemanda());
		// }

		//
		// jogos.getListaDeJogos().addAll(CombinacoesSeteOito(strUltimoSorteio,
		// strNumerosNaoSorteados));
		//

		///
		// jogos.getListaDeJogos().addAll(CombinacoesOitoSete(strUltimoSorteio,
		// strNumerosNaoSorteados));
		jogos.getListaDeJogos().addAll(CombinacoesNoveSeis(strUltimoSorteio, strNumerosNaoSorteados));
		// jogos.getListaDeJogos().addAll(CombinacoesDezCinco(strUltimoSorteio,
		// strNumerosNaoSorteados));

		//

		//
		// jogos.getListaDeJogos().addAll(CombinacoesOnzeQuatro(strUltimoSorteio,
		// strNumerosNaoSorteados));
		//

		/*
		 * testes com o total de números Jogo jogo = new Jogo(Arrays.asList(1,
		 * 2, 3, 4, 5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25));
		 * Combinacao combinacoes = new Combinacao(jogo.getJogo(), 15);
		 * jogos.getListaDeJogos().addAll(combinacoes.geraListaCombinacoes());
		 */

		System.out.println("Tamanho total da lista:  " + jogos.getListaDeJogos().size());
		jogos.setListaTodosJogos(listaTodosJogos);
		jogos.filtraListaBoolean();
		return jogos;
	}

	private static Collection<? extends Jogo> CombinacoesOnzeQuatro(Jogo strUltimoSorteio, Jogo strNumerosNaoSorteados)
			throws FileNotFoundException, IOException {

		FiltroOnze filtroOnze = new FiltroOnze(strUltimoSorteio);
		filtroOnze.filtrar();

		FiltroQuatro filtroQuatro = new FiltroQuatro(strNumerosNaoSorteados);
		filtroQuatro.filtrar();

		System.err.println("*************Filtrando a lista das combinações de 11 e 4 jogo*********");
		FiltroJogos jogosOnzeQuatro = new FiltroJogos(filtroOnze.getListaDeJogos(), filtroQuatro.getListaDeJogos());
		System.out.println("Tamanho da lista de 11 e 4:  " + jogosOnzeQuatro.getListaDeJogos().size());

		return jogosOnzeQuatro.getListaDeJogos();
	}

	private static List<Jogo> CombinacoesSeteOito(Jogo strUltimoSorteio, Jogo strNumerosNaoSorteados)
			throws FileNotFoundException, IOException {

		FiltroSete filtroSete = new FiltroSete(strUltimoSorteio);
		filtroSete.filtrar();

		FiltroOito filtroOito = new FiltroOito(strNumerosNaoSorteados);
		filtroOito.filtrar();

		System.err.println("*************Filtrando a lista das combinações de 7 e 8 jogo*********");
		FiltroJogos jogosSeteOito = new FiltroJogos(filtroSete.getListaDeJogos(), filtroOito.getListaDeJogos());
		System.out.println("Tamanho da lista de 7 e 8:  " + jogosSeteOito.getListaDeJogos().size());

		return jogosSeteOito.getListaDeJogos();

	}

	public static List<Jogo> CombinacoesNoveSeis(Jogo strUltimoSorteio, Jogo strNumerosNaoSorteados)
			throws FileNotFoundException, IOException {
		////////////// *NOVE NUMEROS A PARTIR DO ULTIMO
		////////////// RESULTADO*////////////////////////
		FiltroNove filtroNove = new FiltroNove(strUltimoSorteio);
		filtroNove.filtrar();
		///////////// * FIM -> NOVE NUMEROS A PARTIR DO ULTIMO
		///////////// RESULTADO*////////////////

		/////////////// *SEIS NUMEROS A PARTIR DO ULTIMO
		/////////////// RESULTADO*///////////////////
		FiltroSeis filtroSeis = new FiltroSeis(strNumerosNaoSorteados);
		filtroSeis.filtrar();
		////////// * FIM -> SEIS NUMEROS A PARTIR DO ULTIMO RESULTADO*//////////

		///// *COMBINAÇÕES DOS DOIS GRUPOS, 6 e 9*////////////////////////
		System.err.println("*************Filtrando a lista das combinações de 9 e 6 jogo*********");
		FiltroJogos jogosNoveSeis = new FiltroJogos(filtroNove.getListaDeJogos(), filtroSeis.getListaDeJogos());
		System.out.println("Tamanho da lista de 9 e 6:  " + jogosNoveSeis.getListaDeJogos().size());
		//////////////////////////////////////////////
		return jogosNoveSeis.getListaDeJogos();

	}

	public static List<Jogo> CombinacoesDezCinco(Jogo strUltimoSorteio, Jogo strNumerosNaoSorteados)
			throws FileNotFoundException, IOException {
		////////////// *DEZ NUMEROS A PARTIR DO ULTIMO
		////////////// RESULTADO*////////////////////////
		FiltroDez filtroDez = new FiltroDez(strUltimoSorteio);
		filtroDez.filtrar();
		///////////// * FIM -> DEZ NUMEROS A PARTIR DO ULTIMO
		///////////// RESULTADO*////////////////

		/////////////// *CINCO NUMEROS A PARTIR DO ULTIMO
		/////////////// RESULTADO*///////////////////
		FiltroCinco filtroCinco = new FiltroCinco(strNumerosNaoSorteados);
		filtroCinco.filtrar();
		////////// * FIM -> CINCO NUMEROS A PARTIR DO ULTIMO
		////////// RESULTADO*//////////

		///// *COMBINAÇÕES DOS DOIS GRUPOS, 5 e 10*////////////////////////
		System.err.println("*************Filtrando a lista das combinações de 10 e 5 jogo*********");
		FiltroJogos jogosDezCinco = new FiltroJogos(filtroDez.getListaDeJogos(), filtroCinco.getListaDeJogos());
		System.out.println("Tamanho da lista de 10 e 5:  " + jogosDezCinco.getListaDeJogos().size());
		return jogosDezCinco.getListaDeJogos();
	}

	public static List<Jogo> CombinacoesOitoSete(Jogo strUltimoSorteio, Jogo strNumerosNaoSorteados)
			throws FileNotFoundException, IOException {
		////////////// *NOVE NUMEROS A PARTIR DO ULTIMO
		////////////// RESULTADO*////////////////////////
		FiltroOito filtroOito = new FiltroOito(strUltimoSorteio);
		filtroOito.filtrar();
		///////////// * FIM -> OITO NUMEROS A PARTIR DO ULTIMO
		///////////// RESULTADO*////////////////

		/////////////// *SETE NUMEROS A PARTIR DO ULTIMO
		/////////////// RESULTADO*///////////////////
		FiltroSete filtroSete = new FiltroSete(strNumerosNaoSorteados);
		filtroSete.filtrar();
		////////// * FIM -> SETE NUMEROS A PARTIR DO ULTIMO RESULTADO*//////////

		///// *COMBINAÇÕES DOS DOIS GRUPOS, 7 e 8*////////////////////////
		System.err.println("*************Filtrando a lista das combinações de 8 e 7 jogo*********");
		FiltroJogos jogosOitoSete = new FiltroJogos(filtroOito.getListaDeJogos(), filtroSete.getListaDeJogos());
		System.out.println("Tamanho da lista de 8 e 7:  " + jogosOitoSete.getListaDeJogos().size());
		//////////////////////////////////////////////
		return jogosOitoSete.getListaDeJogos();

	}

	// public static void gerarJogos(int quantidade) throws
	// FileNotFoundException, IOException {
	//
	// FiltroJogos.iniciaListaJogosPorDemanda();
	// FiltroJogos.setAllJogolistaJogosPorDemanda(listaTodosJogos);
	//
	// List<Jogo> jogos = jogos();
	// List<Jogo> jogosSaida = new ArrayList<Jogo>();
	//
	// Random random = new Random();
	// List<Integer> posicoes = new ArrayList<Integer>();
	//
	// System.out.println("sorteando...");
	//
	// while (jogosSaida.size() < quantidade) {
	// Integer posicao = random.nextInt(jogos.size());
	// if (!posicoes.contains(posicao) || repetido(jogos.get(jogos.size() - 1)))
	// {
	// posicoes.add(posicao);
	// jogosSaida.add(jogos.get(posicao));
	// }
	// }
	// System.out.println("List<Jogo> jogos = new ArrayList<Jogo>();");
	// for (Jogo jogo : jogosSaida) {
	// System.out.println("jogos.add(new Jogo(Arrays.asList("
	// + jogo.getJogo().toString().replace("[", "").replace("]", "") + ")));");
	//
	// }
	//
	// System.out.println("consultaSorteio(jogos);");
	// System.out.println("fim");
	// }

	public static void gerarJogos(int quantidade) throws FileNotFoundException, IOException {

		FiltroJogos.iniciaListaJogosPorDemanda();
		FiltroJogos.setAllJogolistaJogosPorDemanda(listaTodosJogos);

		List<Jogo> jogos = jogos();
		List<Jogo> jogosSaida = new ArrayList<Jogo>();

		Random random = new Random();
		List<Integer> posicoes = new ArrayList<Integer>();

		List<Integer> listaPosicoesSorteadas = listaPosicoesSorteadas();

		System.out.println("sorteando...");

		while (jogosSaida.size() < quantidade) {
			Integer posicao = random.nextInt(jogos.size());
			// if (posicao > 3100 && posicao < 169410) { //7
			// if (posicao > 1335 && posicao < 437000) { //8
			if (posicao > 7055 && posicao < 557530) { // 9
				// if (posicao > 2355 && posicao < 432000) { //10
				// if (posicao > 3100 && posicao < 1705010) { //8,9,10
				// if (posicao > 2600 && posicao < 147970) { //11
				if (!posicoes.contains(posicao) && !repetido(jogos.get(posicao))
						&& !listaPosicoesSorteadas.contains(posicao)) {
					posicoes.add(posicao);
					jogosSaida.add(jogos.get(posicao));
				}
			}
		}
		
		Jogo pares = new Jogo(Arrays.asList(1,3,5,7,9,11,13,15,17,19,21,23,25));
		Jogo primos = new Jogo(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23));
		Jogo fibonacci = new Jogo(Arrays.asList(1, 2, 3, 5, 8, 13, 21));
		Jogo quadrado = new Jogo(Arrays.asList(1,2,3,4,5,6,11,16,21,22,23,24,25,20,15,10));
		Jogo multiplosDeTres = new Jogo(Arrays.asList(3,6,9,12,15,18,21,24));

		System.out.println("List<Jogo> jogos = new ArrayList<Jogo>();");
		for (

		Jogo jogo : jogosSaida) {
			System.out.println("jogos.add(new Jogo(Arrays.asList("
					+ jogo.getJogo().toString().replace("[", "").replace("]", "") + ")));");
			
			
			Set<Integer> intersectionRepetidos = new HashSet<Integer>(jogo.getJogo());
			intersectionRepetidos.retainAll(listaTodosJogos.get(listaTodosJogos.size()-1).getJogo());
						
			Set<Integer> intersectionPares = new HashSet<Integer>(jogo.getJogo());
			intersectionPares.retainAll(pares.getJogo());
			
			Set<Integer> intersectionPrimos = new HashSet<Integer>(jogo.getJogo());
			intersectionPrimos.retainAll(primos.getJogo());
			
			Set<Integer> intersectionFibonacci = new HashSet<Integer>(jogo.getJogo());
			intersectionFibonacci.retainAll(fibonacci.getJogo());
			
			Set<Integer> intersectionQuadrado = new HashSet<Integer>(jogo.getJogo());
			intersectionQuadrado.retainAll(quadrado.getJogo());
			
			Set<Integer> intersectionMultiplosDeTres = new HashSet<Integer>(jogo.getJogo());
			intersectionMultiplosDeTres.retainAll(multiplosDeTres.getJogo());
			
			Map<Integer, Integer> mapaEstatisticasJogos = estatisticasJogos(listaTodosJogos.size());

			Jogo dezPrimeiros = dezJogosMaisFrequentes(mapaEstatisticasJogos);
			Jogo cincoDoMeio = cincoJogosDoMeio(mapaEstatisticasJogos);
			Jogo dezUltimos = dezJogosMenosFrequentes(mapaEstatisticasJogos);
			
			Set<Integer> intersectionDezPrimeiros = new HashSet<Integer>(jogo.getJogo());
			intersectionDezPrimeiros.retainAll(dezPrimeiros.getJogo());
			
			Set<Integer> intersectionCincoDoMeio = new HashSet<Integer>(jogo.getJogo());
			intersectionCincoDoMeio.retainAll(cincoDoMeio.getJogo());
			
			Set<Integer> intersectionDezUltimos = new HashSet<Integer>(jogo.getJogo());
			intersectionDezUltimos.retainAll(dezUltimos.getJogo());
			
			System.out.println(
					//"Jogo: " + todosJogos.get(i).getJogo() + 
					" Repetidos: " + intersectionRepetidos.size() 
								+ " Pares -> " + intersectionPares.size() 
							 	+ " Primos -> " + intersectionPrimos.size() 
							 	+ " Fibonacci -> " + intersectionFibonacci.size() 
							 	+ " Quadrado -> " + intersectionQuadrado.size()
							 	+ " Multiplos de Três -> " + intersectionMultiplosDeTres.size() 
							 	+ " Dez primeiros est. -> " + intersectionDezPrimeiros.size()
							 	+ " Cinco do meio esta. ->  " + intersectionCincoDoMeio.size()
							 	+ " Dez últimos est. -> " + intersectionDezUltimos.size()
								);
		
			
			// "jogos.add(new Jogo(Arrays.asList("
			// +jogos.get(0).getJogo().toString().replace("[", "").replace("]",
			// "") + ")));"

		}

		// while (posicoes.size() < quantidade) {
		// Integer posicao = random.nextInt(jogos.size());
		// if (!posicoes.contains(posicao))
		// posicoes.add(posicao);
		// }
		//
		// System.out.println("List<Jogo> jogos = new ArrayList<Jogo>();");
		// for (Integer p : posicoes) {
		// // System.out.println(jogos.get(p).getJogo());
		// System.out.println("jogos.add(new Jogo(Arrays.asList("
		// + jogos.get(p).getJogo().toString().replace("[", "").replace("]", "")
		// + ")));");
		// // "jogos.add(new Jogo(Arrays.asList("
		// // +jogos.get(0).getJogo().toString().replace("[", "").replace("]",
		// // "") + ")));"
		// }
		System.out.println("consultaSorteio(jogos);");

	}
	
	public static void gerarJogosCSV(int quantidade) throws FileNotFoundException, IOException {

		FiltroJogos.iniciaListaJogosPorDemanda();
		FiltroJogos.setAllJogolistaJogosPorDemanda(listaTodosJogos);

		List<Jogo> jogos = jogos();
		List<Jogo> jogosSaida = new ArrayList<Jogo>();

		Random random = new Random();
		List<Integer> posicoes = new ArrayList<Integer>();

		List<Integer> listaPosicoesSorteadas = listaPosicoesSorteadas();

		System.out.println("sorteando...");

		while (jogosSaida.size() < quantidade) {
			Integer posicao = random.nextInt(jogos.size());
			// if (posicao > 3100 && posicao < 169410) { //7
			// if (posicao > 1335 && posicao < 437000) { //8
			if (posicao > 7055 && posicao < 557530) { // 9
				// if (posicao > 2355 && posicao < 432000) { //10
				// if (posicao > 3100 && posicao < 1705010) { //8,9,10
				// if (posicao > 2600 && posicao < 147970) { //11
				if (!posicoes.contains(posicao) && !repetido(jogos.get(posicao))
						&& !listaPosicoesSorteadas.contains(posicao)) {
					posicoes.add(posicao);
					jogosSaida.add(jogos.get(posicao));
				}
			}
		}
		
		Jogo pares = new Jogo(Arrays.asList(1,3,5,7,9,11,13,15,17,19,21,23,25));
		Jogo primos = new Jogo(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23));
		Jogo fibonacci = new Jogo(Arrays.asList(1, 2, 3, 5, 8, 13, 21));
		Jogo quadrado = new Jogo(Arrays.asList(1,2,3,4,5,6,11,16,21,22,23,24,25,20,15,10));
		Jogo multiplosDeTres = new Jogo(Arrays.asList(3,6,9,12,15,18,21,24));
		
		List<String> resultCSV = new ArrayList<String>();
		
		//System.out.println("List<Jogo> jogos = new ArrayList<Jogo>();");
		for (

		Jogo jogo : jogosSaida) {
			
			
			
			Set<Integer> intersectionRepetidos = new HashSet<Integer>(jogo.getJogo());
			intersectionRepetidos.retainAll(listaTodosJogos.get(listaTodosJogos.size()-1).getJogo());
						
			Set<Integer> intersectionPares = new HashSet<Integer>(jogo.getJogo());
			intersectionPares.retainAll(pares.getJogo());
			
			Set<Integer> intersectionPrimos = new HashSet<Integer>(jogo.getJogo());
			intersectionPrimos.retainAll(primos.getJogo());
			
			Set<Integer> intersectionFibonacci = new HashSet<Integer>(jogo.getJogo());
			intersectionFibonacci.retainAll(fibonacci.getJogo());
			
			Set<Integer> intersectionQuadrado = new HashSet<Integer>(jogo.getJogo());
			intersectionQuadrado.retainAll(quadrado.getJogo());
			
			Set<Integer> intersectionMultiplosDeTres = new HashSet<Integer>(jogo.getJogo());
			intersectionMultiplosDeTres.retainAll(multiplosDeTres.getJogo());
			
			Map<Integer, Integer> mapaEstatisticasJogos = estatisticasJogos(listaTodosJogos.size());

			Jogo dezPrimeiros = dezJogosMaisFrequentes(mapaEstatisticasJogos);
			Jogo cincoDoMeio = cincoJogosDoMeio(mapaEstatisticasJogos);
			Jogo dezUltimos = dezJogosMenosFrequentes(mapaEstatisticasJogos);
			
			Set<Integer> intersectionDezPrimeiros = new HashSet<Integer>(jogo.getJogo());
			intersectionDezPrimeiros.retainAll(dezPrimeiros.getJogo());
			
			Set<Integer> intersectionCincoDoMeio = new HashSet<Integer>(jogo.getJogo());
			intersectionCincoDoMeio.retainAll(cincoDoMeio.getJogo());
			
			Set<Integer> intersectionDezUltimos = new HashSet<Integer>(jogo.getJogo());
			intersectionDezUltimos.retainAll(dezUltimos.getJogo());
			
			//System.out.println("jogos.add(new Jogo(Arrays.asList(" + jogo.getJogo().toString().replace("[", "").replace("]", "") + ")))|");
			
			String strJogos = "jogos.add(new Jogo(Arrays.asList(" + jogo.getJogo().toString().replace("[", "").replace("]", "") + ")));";
			resultCSV.add(strJogos +
					//"Jogo: " + todosJogos.get(i).getJogo() + 
					"#Repetidos#" + intersectionRepetidos.size() 
								+ "#Pares#" + intersectionPares.size() 
							 	+ "#Primos#" + intersectionPrimos.size() 
							 	+ "#Fibonacci#" + intersectionFibonacci.size() 
							 	+ "#Quadrado#" + intersectionQuadrado.size()
							 	+ "#Multiplos de Tres#" + intersectionMultiplosDeTres.size() 
							 	+ "#Dez primeiros est.#" + intersectionDezPrimeiros.size()
							 	+ "#Cinco do meio est.#" + intersectionCincoDoMeio.size()
							 	+ "#Dez ultimos est.#" + intersectionDezUltimos.size()
								);
		
			
			// "jogos.add(new Jogo(Arrays.asList("
			// +jogos.get(0).getJogo().toString().replace("[", "").replace("]",
			// "") + ")));"

		}
		
		gerarCSV(resultCSV,"jogos"); 
		estatisticasUltimosSorteioCSV(50);
		System.out.println("Arquivos salvos com sucesso!");
		// while (posicoes.size() < quantidade) {
		// Integer posicao = random.nextInt(jogos.size());
		// if (!posicoes.contains(posicao))
		// posicoes.add(posicao);
		// }
		//
		// System.out.println("List<Jogo> jogos = new ArrayList<Jogo>();");
		// for (Integer p : posicoes) {
		// // System.out.println(jogos.get(p).getJogo());
		// System.out.println("jogos.add(new Jogo(Arrays.asList("
		// + jogos.get(p).getJogo().toString().replace("[", "").replace("]", "")
		// + ")));");
		// // "jogos.add(new Jogo(Arrays.asList("
		// // +jogos.get(0).getJogo().toString().replace("[", "").replace("]",
		// // "") + ")));"
		// }
	//	System.out.println("consultaSorteio(jogos);");

	}

	private static void gerarCSV(List<String> resultCSV, String nomArq) {
		try { 
			String arquivo = "c:\\temp\\" + nomArq +".csv";
			File file = new File(arquivo); 
	        // create FileWriter object with file as parameter 
	        FileWriter outputfile = new FileWriter(file); 

	        // create CSVWriter with ';' as separator 
	        CSVWriter writer = new CSVWriter(outputfile, ';', 
	                                         CSVWriter.NO_QUOTE_CHARACTER, 
	                                         CSVWriter.DEFAULT_ESCAPE_CHARACTER, 
	                                         CSVWriter.DEFAULT_LINE_END); 

	        // create a List which contains Data 
	        List<String[]> data = new ArrayList<String[]>(); 
	        
		for (String string : resultCSV) {
			 String row = string; //sc.nextLine(); 
             String[] rowdata = row.split("#"); 
             data.add(rowdata); 
		}
		  writer.writeAll(data); 

	        // closing writer connection 
		  writer.close();
		  java.awt.Desktop.getDesktop().open( new File(arquivo) );

	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    }
	}

	public static void gerarJogosArq(int quantidade) throws FileNotFoundException, IOException {

		FiltroJogos.iniciaListaJogosPorDemanda();
		FiltroJogos.setAllJogolistaJogosPorDemanda(listaTodosJogos);

		List<Jogo> jogos = jogos();
		List<Jogo> jogosSaida = new ArrayList<Jogo>();

		Random random = new Random();
		List<Integer> posicoes = new ArrayList<Integer>();

		System.out.println("sorteando...");

		while (jogosSaida.size() < quantidade) {
			Integer posicao = random.nextInt(jogos.size());
			if (!posicoes.contains(posicao) && !repetido(jogos.get(posicao))) {
				posicoes.add(posicao);
				jogosSaida.add(jogos.get(posicao));
			}
		}
		Long numeroJogo = (long) (listaTodosJogos.size() + 1);
		String nomeArquivo = "jogo_" + numeroJogo + ".txt";
		String path = ClassLoader.getSystemResource("").getPath() + "/" + nomeArquivo;
		File file = new File(path);
		String nome = "";
		String conteudo;

		try {
			FileWriter f = new FileWriter(file, true);
			for (Jogo jogo : jogosSaida) {
				nome = jogo.getJogo().toString().replace("[", "").replace("]", "").replace(",", "");
				conteudo = nome;
				conteudo += "\r\n";
				f.write(conteudo);
			}
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("jogos salvos em " + nomeArquivo);
	}

	public static boolean repetido(Jogo jogo) throws FileNotFoundException, IOException {

		for (Jogo tj : listaTodosJogos) {
			Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
			intersection.retainAll(tj.getJogo());

			if (intersection.size() > 14) {
				return true;
			}
		}
		return false;
	}

	private static void estatSorteio() throws FileNotFoundException, IOException {

		Estatisticas est = new Estatisticas();
		// listaTodosJogos = est.lerTodosOsJogos();

		FiltroJogos.iniciaListaJogosPorDemanda();

		Jogo strUltimoSorteio = listaTodosJogos.get(listaTodosJogos.size() - 1);
		Jogo strSorteioAnterior = listaTodosJogos.get(listaTodosJogos.size() - 2);
		listaTodosJogos.remove(listaTodosJogos.size() - 1);
		FiltroJogos.setAllJogolistaJogosPorDemanda(listaTodosJogos);
		Jogo premiado = new Jogo();

		List<Jogo> listaJogosFiltrados = jogos(strSorteioAnterior);

		TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();

		for (Jogo jogo : listaJogosFiltrados) {
			Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
			intersection.retainAll(strUltimoSorteio.getJogo());

			int chave = intersection.size();
			if (trmap.containsKey(chave)) {
				int valor = trmap.get(chave).intValue() + 1;
				trmap.put(chave, valor);

			} else {
				trmap.put(chave, 1);
			}
			if (chave == 15) {
				premiado = jogo;
			}
		}

		System.err.println(premiado.getJogo());

		System.out.println(trmap.toString());

		System.out.println("Repetidos: " + est.quantidadeNumerosRepetidos(strUltimoSorteio, strSorteioAnterior));

		System.out.println("FIM");

	}

	public static List<Integer> listaPosicoesSorteadas() throws FileNotFoundException, IOException {
		String path = ClassLoader.getSystemResource("").getPath() + "/posicoes.txt";

		File file = new File(path);
		byte[] bytes = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);

		fis.read(bytes);
		fis.close();

		String[] valueStr = new String(bytes).trim().split("\\s+");

		List<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < valueStr.length; i++) {
			lista.add(Integer.parseInt(valueStr[i]));
			// System.out.println(valueStr[i]);
		}

		return lista;
	}

	public static Jogo jogoVinteNumeros() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		FiltroJogos filtro = new FiltroJogos();
		List<Jogo> todosJogos = filtro.getListaJogosPorDemanda();

		int qTDJogosAnalisados = 10;
		TreeMap<Integer, Integer> mapaRanking1 = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> mapaRanking2 = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> mapaRanking3 = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> mapaRanking4 = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> mapaRanking5 = new TreeMap<Integer, Integer>();

		for (int posicao = todosJogos.size() - 1; posicao > todosJogos.size() - (qTDJogosAnalisados + 1); posicao--) {
			for (Integer numero : todosJogos.get(posicao).getJogo()) {

				if (numero > 0 && numero < 6) {

					if (mapaRanking1.containsKey(numero)) {
						int valor = mapaRanking1.get(numero).intValue() + 1;
						mapaRanking1.put(numero, valor);
					} else {
						mapaRanking1.put(numero, 1);
					}

				}

				if (numero > 5 && numero < 11) {
					if (mapaRanking2.containsKey(numero)) {
						int valor = mapaRanking2.get(numero).intValue() + 1;
						mapaRanking2.put(numero, valor);
					} else {
						mapaRanking2.put(numero, 1);
					}
				}

				if (numero > 10 && numero < 16) {
					if (mapaRanking3.containsKey(numero)) {
						int valor = mapaRanking3.get(numero).intValue() + 1;
						mapaRanking3.put(numero, valor);
					} else {
						mapaRanking3.put(numero, 1);
					}
				}

				if (numero > 15 && numero < 21) {
					if (mapaRanking4.containsKey(numero)) {
						int valor = mapaRanking4.get(numero).intValue() + 1;
						mapaRanking4.put(numero, valor);
					} else {
						mapaRanking4.put(numero, 1);
					}
				}

				if (numero > 20 && numero < 26) {
					if (mapaRanking5.containsKey(numero)) {
						int valor = mapaRanking5.get(numero).intValue() + 1;
						mapaRanking5.put(numero, valor);
					} else {
						mapaRanking5.put(numero, 1);
					}
				}
			}
		}

		Map<Integer, Integer> sortedMapRanking1 = sortByValue(mapaRanking1);
		Map<Integer, Integer> sortedMapRanking2 = sortByValue(mapaRanking2);
		Map<Integer, Integer> sortedMapRanking3 = sortByValue(mapaRanking3);
		Map<Integer, Integer> sortedMapRanking4 = sortByValue(mapaRanking4);
		Map<Integer, Integer> sortedMapRanking5 = sortByValue(mapaRanking5);

		Jogo jogo = new Jogo();

		int i = 1;
		for (Integer numero : sortedMapRanking1.keySet()) {
			if (i < 5) {
				jogo.getJogo().add(numero);
			}
			i++;
		}

		i = 1;
		for (Integer numero : sortedMapRanking2.keySet()) {
			if (i < 5) {
				jogo.getJogo().add(numero);
			}
			i++;
		}

		i = 1;
		for (Integer numero : sortedMapRanking3.keySet()) {
			if (i < 5) {
				jogo.getJogo().add(numero);
			}
			i++;
		}

		i = 1;
		for (Integer numero : sortedMapRanking4.keySet()) {
			if (i < 5) {
				jogo.getJogo().add(numero);
			}
			i++;
		}

		i = 1;
		for (Integer numero : sortedMapRanking5.keySet()) {
			if (i < 5) {
				jogo.getJogo().add(numero);
			}
			i++;
		}

		Collections.sort(jogo.getJogo());
		return jogo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<Integer, Integer> sortByValue(Map<Integer, Integer> map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {

			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		Map result = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	public static Map<Integer, Integer> estatisticasJogos(int QtdJogos) throws FileNotFoundException, IOException {

		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();
		//List<Jogo> todosJogos = listaTodosJogos;
		Map<Integer, Integer> mapaNumeros = new TreeMap<Integer, Integer>();
		Integer qtdJogosAnalisados = 9;

		for (int i = QtdJogos - qtdJogosAnalisados; i < QtdJogos; i++) {
			for (Integer numero : todosJogos.get(i).getJogo()) {
				if (mapaNumeros.containsKey(numero)) {
					mapaNumeros.put(numero, mapaNumeros.get(numero).intValue() + 1);
				} else {
					mapaNumeros.put(numero, 1);
				}
			}

		}

		Map<Integer, Integer> mapaNumerosOrdenados = sortByValue(mapaNumeros);

		for (Integer chave : mapaNumerosOrdenados.keySet()) {
			mapaNumerosOrdenados.put(chave, (mapaNumerosOrdenados.get(chave).intValue() * 100) / qtdJogosAnalisados);
		}

		// System.out.println(mapaNumerosOrdenados);

		return mapaNumerosOrdenados;

	}
	
	public static Jogo dezJogosMaisFrequentes(Map<Integer, Integer> mapaNumeros)
			throws FileNotFoundException, IOException {

		// 2=0.17 ; 3=1.88; 4=8.46; 5=23.61; 6=30.72; 7=23.38; 8=10.10; 9=1.64

		Jogo dezPrimeiros = new Jogo();

		int dezPrimeiras = 10;
		int cont = 0;

		for (Integer chave : mapaNumeros.keySet()) {
			cont++;
			dezPrimeiros.getJogo().add(chave);

			if (cont == dezPrimeiras) {
				break;
			}
		}

		return dezPrimeiros;

		// for (Integer chave : mapaNumerosOrdenados.keySet()) {
		// System.out.println(chave + "-> " +
		// (mapaNumerosOrdenados.get(chave).intValue() * 100)/qtdJogosAnalisados
		// + "%");
		// }

	}

	public static Jogo dezJogosMenosFrequentes(Map<Integer, Integer> mapaNumeros)
			throws FileNotFoundException, IOException {

		// 2=0.23; 3=1.99; 4=8.87; 5=24.32; 6=30.37; 7=23.14; 8=9.51; 9=1.52

		Jogo dezUltimos = new Jogo();

		int cont = 0;

		for (Integer chave : mapaNumeros.keySet()) {
			cont++;
			if (cont > 15) {
				dezUltimos.getJogo().add(chave);
			}
		}

		return dezUltimos;

	}

	public static Jogo cincoJogosDoMeio(Map<Integer, Integer> mapaNumeros) throws FileNotFoundException, IOException {

		Jogo cincoDoMeio = new Jogo();

		int cont = 0;

		for (Integer chave : mapaNumeros.keySet()) {
			cont++;
			if (cont > 10 && cont < 16) {
				cincoDoMeio.getJogo().add(chave);
			}
		}

		return cincoDoMeio;

	}

}