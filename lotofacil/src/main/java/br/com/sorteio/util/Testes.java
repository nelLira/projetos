package br.com.sorteio.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import br.com.sorteio.combinacoes.Combinacao;
import br.com.sorteio.lotofacil.FiltroJogos;
import br.com.sorteio.numeros.Jogo;

public class Testes {

	public static List<Jogo> listaTodosJogos;

	public static void main(String[] args) throws IOException {

		 Estatisticas est = new Estatisticas();
		 List<Jogo> todosJogos = est.lerTodosOsJogos();
		 listaTodosJogos = new ArrayList<Jogo>();
		 
		 int cont = 0;
		 int contPosicoes = 0;
		 for (Jogo jogo : todosJogos) {
			 listaTodosJogos.add(jogo);
			cont++;
			 if (cont < 10){
				continue;
			}
			 
			 Map<Integer,Integer> mapaEstatisticas = estatisticasJogos();
			 List<Integer> listaEstatisticasPosicoes = new ArrayList<Integer>();
			 int posicao = 0;
			 for (Integer chave : mapaEstatisticas.keySet()) {
				mapaEstatisticas.put(chave, ++posicao);
			}
			
			
			for (int i = 0 ; i < jogo.getJogo().size(); i ++) {
				listaEstatisticasPosicoes.add(mapaEstatisticas.get(jogo.getJogo().get(i).intValue()));
				
			}
			Collections.sort(listaEstatisticasPosicoes);
			Map<String, Integer> mapaPosicoes = new TreeMap<String, Integer>();
			mapaPosicoes.put("primeiroGrupo", 0);
			mapaPosicoes.put("segundoGrupo", 0);
			
			for (Integer integer : listaEstatisticasPosicoes) {
				if (integer < 13){
					mapaPosicoes.put("primeiroGrupo", mapaPosicoes.get("primeiroGrupo").intValue()+1);
				}else {
					mapaPosicoes.put("segundoGrupo", mapaPosicoes.get("segundoGrupo").intValue()+1);
				}
			}
			if(mapaPosicoes.get("primeiroGrupo").intValue() > mapaPosicoes.get("segundoGrupo").intValue()) contPosicoes++;
			//System.out.println(listaEstatisticasPosicoes + " - " + mapaPosicoes);
			System.out.println(mapaPosicoes);
		}
		 System.out.println(contPosicoes);
		//simulaGeraJogoDezenoveNumeros();

		// Estatisticas est = new Estatisticas();
		// List<Jogo> todosJogos = est.lerTodosOsJogos();
		// FiltroJogos filtro = new FiltroJogos();
		// FiltroJogos.iniciaListaJogosPorDemanda();
		//
		// Jogo jogoAtual = null;
		//
		// for (Jogo proximoJogo : todosJogos) {
		//
		// if (jogoAtual == null) {
		// jogoAtual = proximoJogo;
		// } else {
		//
		// FiltroJogos.setJogolistaJogosPorDemanda(jogoAtual);
		// jogoAtual = proximoJogo;
		//
		// if (filtro.getListaJogosPorDemanda().size() < 10) {
		// continue;
		// }
		// Jogo dezPrimeiros =
		// cincoJogosDoMeio(filtro.getListaJogosPorDemanda());
		//
		// Set<Integer> intersectionRepetidos = new
		// HashSet<Integer>(proximoJogo.getJogo());
		// intersectionRepetidos.retainAll(dezPrimeiros.getJogo());
		//
		// System.out.println(intersectionRepetidos.size());
		//
		// }
		//
		// }

		/*
		 * Estatisticas est = new Estatisticas(); List<Jogo> todosJogos =
		 * est.lerTodosOsJogos(); FiltroJogos filtro = new FiltroJogos();
		 * FiltroJogos.iniciaListaJogosPorDemanda(); int intContDif = 0; int
		 * intContInt = 0;
		 * 
		 * for (Jogo jogo : todosJogos) {
		 * 
		 * filtro.getListaJogosPorDemanda().add(jogo);
		 * 
		 * if (filtro.getListaJogosPorDemanda().size() < 10) continue;
		 * 
		 * Jogo strUltimoSorteio =
		 * filtro.getListaJogosPorDemanda().get(filtro.getListaJogosPorDemanda()
		 * .size() - 2); Jogo strPenUltimoSorteio =
		 * filtro.getListaJogosPorDemanda().get(filtro.getListaJogosPorDemanda()
		 * .size() - 3); Jogo strNoveSorteiosAtras =
		 * filtro.getListaJogosPorDemanda().get(filtro.getListaJogosPorDemanda()
		 * .size() - 10);
		 * 
		 * Jogo strNumerosNaoSorteados =
		 * strUltimoSorteio.numerosNaoSorteados(strUltimoSorteio);
		 * 
		 * Set<Integer> differenceUltimoPenUltimo = new
		 * HashSet<Integer>(strPenUltimoSorteio.getJogo());
		 * differenceUltimoPenUltimo.removeAll(strUltimoSorteio.getJogo());
		 * 
		 * Set<Integer> intersectionNoveJogosAtras = new
		 * HashSet<Integer>(strNoveSorteiosAtras.getJogo());
		 * intersectionNoveJogosAtras.retainAll(differenceUltimoPenUltimo);
		 * 
		 * for (Integer numero : differenceUltimoPenUltimo) { if
		 * (jogo.getJogo().contains(numero)) { intContDif++; }
		 * 
		 * }
		 * 
		 * for (Integer numero : intersectionNoveJogosAtras) { if
		 * (jogo.getJogo().contains(numero)) { intContInt++; }
		 * 
		 * }
		 * 
		 * if (intContDif > 1 && intContInt > 0) {
		 * System.out.println(jogo.getJogo()); }
		 * 
		 * intContDif = 0; intContInt = 0;
		 * 
		 * }
		 */
		// System.out.println("fim");

	}

	public static Jogo jogoVinteNumeros() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();

		int qTDJogosAnalisados = 8;
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

	public static List<Integer> union(List<Integer> lista1, List<Integer> lista2) {
		TreeSet<Integer> hashedArray = new TreeSet<Integer>();
		for (Integer entry : lista1) {
			hashedArray.add(entry);
		}

		for (Integer entry : lista2) {
			hashedArray.add(entry);
		}

		List<Integer> list = new ArrayList<Integer>();

		for (Integer t : hashedArray) {
			list.add(t);

		}
		return list;
	}

	public static void testeAgrupamento18() throws FileNotFoundException, IOException {

		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();

		int qTDJogosAnalisados = 9;

		Integer primeiroQuinto = 0;
		Integer segundoQuinto = 0;
		Integer terceiroQuinto = 0;
		Integer quartoQuinto = 0;
		Integer quintoQuinto = 0;

		TreeMap<Integer, Integer> mapaQuintos = new TreeMap<Integer, Integer>();

		for (int posicao = todosJogos.size() - 2; posicao > todosJogos.size() - (qTDJogosAnalisados + 2); posicao--) {
			System.out.println(todosJogos.get(posicao).getJogo());
			for (Integer numero : todosJogos.get(posicao).getJogo()) {

				if (numero > 0 && numero < 6) {
					primeiroQuinto++;
				}

				if (numero > 5 && numero < 11) {
					segundoQuinto++;
				}

				if (numero > 10 && numero < 16) {
					terceiroQuinto++;
				}

				if (numero > 15 && numero < 21) {
					quartoQuinto++;
				}

				if (numero > 20 && numero < 26) {
					quintoQuinto++;
				}

			}
		}

		mapaQuintos.put(1, primeiroQuinto);
		mapaQuintos.put(2, segundoQuinto);
		mapaQuintos.put(3, terceiroQuinto);
		mapaQuintos.put(4, quartoQuinto);
		mapaQuintos.put(5, quintoQuinto);
		// Collections.sort(mapaQuintos);
		TreeMap<Integer, List<Integer>> mapaGruposQuintos = new TreeMap<Integer, List<Integer>>();
		mapaGruposQuintos.put(1, Arrays.asList(1, 2, 3, 4, 5));
		mapaGruposQuintos.put(2, Arrays.asList(6, 7, 8, 9, 10));
		mapaGruposQuintos.put(3, Arrays.asList(11, 12, 13, 14, 15));
		mapaGruposQuintos.put(4, Arrays.asList(16, 17, 18, 19, 20));
		mapaGruposQuintos.put(5, Arrays.asList(21, 22, 23, 24, 25));

		List<Jogo> listaCombinacoesPrimeiroQuinto = new ArrayList<Jogo>();

		List<Jogo> listaCombinacoesSegundoQuinto = new ArrayList<Jogo>();

		List<Jogo> listaCombinacoesTerceiroQuinto = new ArrayList<Jogo>();

		List<Jogo> listaCombinacoesQuartoQuinto = new ArrayList<Jogo>();

		List<Jogo> listaCombinacoesQuintoQuinto = new ArrayList<Jogo>();

		SortedMap<Integer, Integer> listaOrdenada = new TreeMap<Integer, Integer>();

		for (Integer chave : mapaQuintos.keySet()) {
			listaOrdenada.put(mapaQuintos.get(chave), chave);
		}

		for (Integer chave : mapaQuintos.keySet()) {
			float quantidadeEmCadaQuinto = mapaQuintos.get(chave) / qTDJogosAnalisados;
			// System.out.println(chave + " " + mapaQuintos.get(chave) + " " +
			// quantidadeEmCadaQuinto);

			int intQuantidadeEmCadaQuinto = (int) ((int) quantidadeEmCadaQuinto == 5 ? 4 : quantidadeEmCadaQuinto);

			for (int i = intQuantidadeEmCadaQuinto; i < 6; i++) {
				Combinacao combinacoes = new Combinacao(mapaGruposQuintos.get(chave), i);
				if (chave == 1) {
					listaCombinacoesPrimeiroQuinto.addAll(combinacoes.geraListaCombinacoes());
				}
				if (chave == 2) {
					listaCombinacoesSegundoQuinto.addAll(combinacoes.geraListaCombinacoes());
				}
				if (chave == 3) {
					listaCombinacoesTerceiroQuinto.addAll(combinacoes.geraListaCombinacoes());
				}
				if (chave == 4) {
					listaCombinacoesQuartoQuinto.addAll(combinacoes.geraListaCombinacoes());
				}
				if (chave == 5) {
					listaCombinacoesQuintoQuinto.addAll(combinacoes.geraListaCombinacoes());
				}
			}
		}

		List<Jogo> listaAux = new ArrayList<Jogo>();
		List<Jogo> combinacoes = new ArrayList<Jogo>();
		for (Jogo n1 : listaCombinacoesPrimeiroQuinto) {

			for (Jogo n2 : listaCombinacoesSegundoQuinto) {
				combinacoes.add(new Jogo(union(n1.getJogo(), n2.getJogo())));
			}

		}

		listaAux.addAll(combinacoes);

		combinacoes.clear();

		for (Jogo n1 : listaAux) {
			for (Jogo n2 : listaCombinacoesTerceiroQuinto) {
				combinacoes.add(new Jogo(union(n1.getJogo(), n2.getJogo())));
			}
		}

		listaAux.clear();
		listaAux.addAll(combinacoes);

		combinacoes.clear();

		for (Jogo n1 : listaAux) {
			for (Jogo n2 : listaCombinacoesQuartoQuinto) {
				combinacoes.add(new Jogo(union(n1.getJogo(), n2.getJogo())));
			}
		}

		listaAux.clear();
		listaAux.addAll(combinacoes);

		combinacoes.clear();

		for (Jogo n1 : listaAux) {
			for (Jogo n2 : listaCombinacoesQuintoQuinto) {
				combinacoes.add(new Jogo(union(n1.getJogo(), n2.getJogo())));
			}
		}

		listaAux.clear();
		listaAux.addAll(combinacoes);

		List<Jogo> listaFinal = new ArrayList<Jogo>();
		Jogo listaPares = new Jogo(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24));
		Jogo strPnultimoSorteio = todosJogos.get(todosJogos.size() - 2);

		for (Jogo jogo : listaAux) {

			Set<Integer> intersectionRepetidos = new HashSet<Integer>(strPnultimoSorteio.getJogo());
			intersectionRepetidos.retainAll(jogo.getJogo());
			int qtdRepetidos = intersectionRepetidos.size();

			Set<Integer> intersectionPares = new HashSet<Integer>(listaPares.getJogo());
			intersectionPares.retainAll(jogo.getJogo());
			int qtdPares = intersectionPares.size();

			boolean testeRepetidos = qtdRepetidos == 11;
			boolean testePares = qtdPares == 9;

			if (jogo.getJogo().size() == 18 && testeRepetidos && testePares) {
				listaFinal.add(jogo);
			}
		}

		TreeMap<Integer, String> trmapResultados = new TreeMap<Integer, String>();
		Integer posicao = 0;
		Jogo strUltimoSorteio = todosJogos.get(todosJogos.size() - 1);

		for (Jogo jogo : listaFinal) {
			Combinacao comb = new Combinacao(jogo.getJogo(), 15);
			List<Jogo> listaJogosTemporaria = new ArrayList<Jogo>();
			listaJogosTemporaria.addAll(comb.geraListaCombinacoes());

			TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();

			for (Jogo jogo2 : listaJogosTemporaria) {
				Set<Integer> intersection = new HashSet<Integer>(jogo2.getJogo());
				intersection.retainAll(strUltimoSorteio.getJogo());

				int chave = intersection.size();
				if (trmap.containsKey(chave)) {
					int valor = trmap.get(chave).intValue() + 1;
					trmap.put(chave, valor);

				} else {
					trmap.put(chave, 1);
				}
				chave = 0;
				intersection.clear();
			}
			// System.out.println(jogo.getJogo() + " - " + trmap.toString());
			// if (trmap.containsKey(11)){
			trmapResultados.put(posicao, trmap.toString());
			// }
			trmap.clear();
			posicao++;
			// break;

		}

		for (Integer p : trmapResultados.keySet()) {
			System.out.println(listaFinal.get(p).getJogo() + " ->  " + trmapResultados.get(p).toString());
		}

	}

	public static TreeMap<Integer, Integer> estatisticasLinhas() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();

		int qTDJogosAnalisados = 9;

		Integer primeiroQuinto = 0;
		Integer segundoQuinto = 0;
		Integer terceiroQuinto = 0;
		Integer quartoQuinto = 0;
		Integer quintoQuinto = 0;

		TreeMap<Integer, Integer> mapaQuintos = new TreeMap<Integer, Integer>();

		for (int posicao = todosJogos.size() - 1; posicao > todosJogos.size() - (qTDJogosAnalisados + 1); posicao--) {
			// System.out.println(todosJogos.get(posicao).getJogo());
			for (Integer numero : todosJogos.get(posicao).getJogo()) {

				if (numero > 0 && numero < 6) {
					primeiroQuinto++;
				}

				if (numero > 5 && numero < 11) {
					segundoQuinto++;
				}

				if (numero > 10 && numero < 16) {
					terceiroQuinto++;
				}

				if (numero > 15 && numero < 21) {
					quartoQuinto++;
				}

				if (numero > 20 && numero < 26) {
					quintoQuinto++;
				}

			}
		}

		mapaQuintos.put(1, primeiroQuinto / qTDJogosAnalisados);
		mapaQuintos.put(2, segundoQuinto / qTDJogosAnalisados);
		mapaQuintos.put(3, terceiroQuinto / qTDJogosAnalisados);
		mapaQuintos.put(4, quartoQuinto / qTDJogosAnalisados);
		mapaQuintos.put(5, quintoQuinto / qTDJogosAnalisados);

		return mapaQuintos;
	}

	public static TreeMap<Integer, Integer> estatisticasColunas() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();

		int qTDJogosAnalisados = 9;

		Integer primeiroQuinto = 0;
		Integer segundoQuinto = 0;
		Integer terceiroQuinto = 0;
		Integer quartoQuinto = 0;
		Integer quintoQuinto = 0;

		TreeMap<Integer, Integer> mapaQuintos = new TreeMap<Integer, Integer>();

		for (int posicao = todosJogos.size() - 1; posicao > todosJogos.size() - (qTDJogosAnalisados + 1); posicao--) {
			// System.out.println(todosJogos.get(posicao).getJogo());
			for (Integer numero : todosJogos.get(posicao).getJogo()) {

				if (numero == 1 || numero == 6 || numero == 11 || numero == 16 || numero == 21) {
					primeiroQuinto++;
				}

				if (numero == 2 || numero == 7 || numero == 12 || numero == 17 || numero == 22) {
					segundoQuinto++;
				}

				if (numero == 3 || numero == 8 || numero == 13 || numero == 18 || numero == 23) {
					terceiroQuinto++;
				}

				if (numero == 4 || numero == 9 || numero == 14 || numero == 19 || numero == 24) {
					quartoQuinto++;
				}

				if (numero == 5 || numero == 10 || numero == 15 || numero == 20 || numero == 25) {
					quintoQuinto++;
				}

			}
		}

		mapaQuintos.put(1, primeiroQuinto / qTDJogosAnalisados);
		mapaQuintos.put(2, segundoQuinto / qTDJogosAnalisados);
		mapaQuintos.put(3, terceiroQuinto / qTDJogosAnalisados);
		mapaQuintos.put(4, quartoQuinto / qTDJogosAnalisados);
		mapaQuintos.put(5, quintoQuinto / qTDJogosAnalisados);

		return mapaQuintos;
	}

	public static void gruposQuintos() throws FileNotFoundException, IOException {
		TreeMap<Integer, Integer> mapaQuintos = estatisticasLinhas();
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();
		int total = 0;

		for (Jogo jogo : todosJogos) {
			int cont = 0;
			int contOcorreciaGrupos = 0;
			if (jogo.getJogo().contains(1)) {
				cont++;
			}
			if (jogo.getJogo().contains(2)) {
				cont++;
			}
			if (jogo.getJogo().contains(3)) {
				cont++;
			}
			if (jogo.getJogo().contains(4)) {
				cont++;
			}
			if (jogo.getJogo().contains(5)) {
				cont++;
			}
			if (cont >= mapaQuintos.get(1).intValue()) {
				contOcorreciaGrupos++;
			}

			cont = 0;
			if (jogo.getJogo().contains(6)) {
				cont++;
			}
			if (jogo.getJogo().contains(7)) {
				cont++;
			}
			if (jogo.getJogo().contains(8)) {
				cont++;
			}
			if (jogo.getJogo().contains(9)) {
				cont++;
			}
			if (jogo.getJogo().contains(10)) {
				cont++;
			}
			if (cont >= mapaQuintos.get(2).intValue()) {
				contOcorreciaGrupos++;
			}

			cont = 0;
			if (jogo.getJogo().contains(11)) {
				cont++;
			}
			if (jogo.getJogo().contains(12)) {
				cont++;
			}
			if (jogo.getJogo().contains(13)) {
				cont++;
			}
			if (jogo.getJogo().contains(14)) {
				cont++;
			}
			if (jogo.getJogo().contains(15)) {
				cont++;
			}
			if (cont >= mapaQuintos.get(3).intValue()) {
				contOcorreciaGrupos++;
			}

			cont = 0;
			if (jogo.getJogo().contains(16)) {
				cont++;
			}
			if (jogo.getJogo().contains(17)) {
				cont++;
			}
			if (jogo.getJogo().contains(18)) {
				cont++;
			}
			if (jogo.getJogo().contains(19)) {
				cont++;
			}
			if (jogo.getJogo().contains(20)) {
				cont++;
			}
			if (cont >= mapaQuintos.get(4).intValue()) {
				contOcorreciaGrupos++;
			}

			cont = 0;
			if (jogo.getJogo().contains(21)) {
				cont++;
			}
			if (jogo.getJogo().contains(22)) {
				cont++;
			}
			if (jogo.getJogo().contains(23)) {
				cont++;
			}
			if (jogo.getJogo().contains(24)) {
				cont++;
			}
			if (jogo.getJogo().contains(25)) {
				cont++;
			}
			if (cont >= mapaQuintos.get(5).intValue()) {
				contOcorreciaGrupos++;
			}

			if (contOcorreciaGrupos > 3) {
				total++;
			}
			contOcorreciaGrupos = 0;
		}
		System.out.println(total);
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

	public static Map<Integer, Integer> estatisticasJogos() throws FileNotFoundException, IOException {

		Estatisticas est = new Estatisticas();
		// List<Jogo> todosJogos = est.lerTodosOsJogos();
		List<Jogo> todosJogos = listaTodosJogos;
		Map<Integer, Integer> mapaNumeros = new TreeMap<Integer, Integer>();
		Integer qtdJogosAnalisados = 9;

		for (int i = todosJogos.size() - qtdJogosAnalisados; i < todosJogos.size(); i++) {
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

	public static List<Jogo> combinaDezUltimosPrimeiosMeio(List<Jogo> todosJogos)
			throws FileNotFoundException, IOException {
		Map<Integer, Integer> mapaNumeros = estatisticasJogos();

		Jogo dezPrimeiros = dezJogosMaisFrequentes(mapaNumeros);
		Jogo dezUltimos = dezJogosMenosFrequentes(mapaNumeros);
		Jogo cincoDoMeio = cincoJogosDoMeio(mapaNumeros);

		Combinacao comb = new Combinacao(dezPrimeiros.getJogo(), 4);
		List<Jogo> combinacoesQuatroDezPrimeiros = new ArrayList<Jogo>();
		combinacoesQuatroDezPrimeiros.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezPrimeiros.getJogo(), 5);
		List<Jogo> combinacoesCincoDezPrimeiros = new ArrayList<Jogo>();
		combinacoesCincoDezPrimeiros.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezPrimeiros.getJogo(), 6);
		List<Jogo> combinacoesSeisDezPrimeiros = new ArrayList<Jogo>();
		combinacoesSeisDezPrimeiros.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezPrimeiros.getJogo(), 7);
		List<Jogo> combinacoesSeteDezPrimeiros = new ArrayList<Jogo>();
		combinacoesSeteDezPrimeiros.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezPrimeiros.getJogo(), 8);
		List<Jogo> combinacoesOitoDezPrimeiros = new ArrayList<Jogo>();
		combinacoesOitoDezPrimeiros.addAll(comb.geraListaCombinacoes());

		comb = new Combinacao(dezUltimos.getJogo(), 4);
		List<Jogo> combinacoesQuatroDezUltimos = new ArrayList<Jogo>();
		combinacoesQuatroDezUltimos.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezUltimos.getJogo(), 5);
		List<Jogo> combinacoesCincoDezUltimos = new ArrayList<Jogo>();
		combinacoesCincoDezUltimos.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezUltimos.getJogo(), 6);
		List<Jogo> combinacoesSeisDezUltimos = new ArrayList<Jogo>();
		combinacoesSeisDezUltimos.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezUltimos.getJogo(), 7);
		List<Jogo> combinacoesSeteDezUltimos = new ArrayList<Jogo>();
		combinacoesSeteDezUltimos.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezUltimos.getJogo(), 8);
		List<Jogo> combinacoesOitoDezUltimos = new ArrayList<Jogo>();
		combinacoesOitoDezUltimos.addAll(comb.geraListaCombinacoes());

		comb = new Combinacao(cincoDoMeio.getJogo(), 1);
		List<Jogo> combinacoesUmCincoMeio = new ArrayList<Jogo>();
		combinacoesUmCincoMeio.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(cincoDoMeio.getJogo(), 2);
		List<Jogo> combinacoesDoisCincoMeio = new ArrayList<Jogo>();
		combinacoesDoisCincoMeio.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(cincoDoMeio.getJogo(), 3);
		List<Jogo> combinacoesTresCincoMeio = new ArrayList<Jogo>();
		combinacoesTresCincoMeio.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(cincoDoMeio.getJogo(), 4);
		List<Jogo> combinacoesQuatroCincoMeio = new ArrayList<Jogo>();
		combinacoesQuatroCincoMeio.addAll(comb.geraListaCombinacoes());

		List<Jogo> listaCominacoes = new ArrayList<Jogo>();
		FiltroJogos filtro = new FiltroJogos();
		// 4 + 6 + 5
		filtro = new FiltroJogos(combinacoesQuatroDezPrimeiros, combinacoesSeisDezUltimos);
		for (Jogo jogo : filtro.getListaDeJogos()) {
			jogo.getJogo().addAll(cincoDoMeio.getJogo());
			listaCominacoes.add(jogo);
		}

		// 4 + 7 + 4
		filtro = new FiltroJogos(combinacoesQuatroDezPrimeiros, combinacoesSeteDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesQuatroCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 4 + 8 + 3
		filtro = new FiltroJogos(combinacoesQuatroDezPrimeiros, combinacoesOitoDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesTresCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 5 * 5 + 5
		filtro = new FiltroJogos(combinacoesCincoDezPrimeiros, combinacoesCincoDezUltimos);
		for (Jogo jogo : filtro.getListaDeJogos()) {
			jogo.getJogo().addAll(cincoDoMeio.getJogo());
			listaCominacoes.add(jogo);
		}
		// 5 + 6 + 4
		filtro = new FiltroJogos(combinacoesCincoDezPrimeiros, combinacoesSeisDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesQuatroCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 5 + 7 + 3
		filtro = new FiltroJogos(combinacoesCincoDezPrimeiros, combinacoesSeteDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesTresCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 5 + 8 + 2
		filtro = new FiltroJogos(combinacoesCincoDezPrimeiros, combinacoesOitoDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesDoisCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 6 + 4 + 5
		filtro = new FiltroJogos(combinacoesSeisDezPrimeiros, combinacoesQuatroDezUltimos);
		for (Jogo jogo : filtro.getListaDeJogos()) {
			jogo.getJogo().addAll(cincoDoMeio.getJogo());
			listaCominacoes.add(jogo);
		}
		// 6 + 5 + 4
		filtro = new FiltroJogos(combinacoesSeisDezPrimeiros, combinacoesCincoDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesQuatroCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 6 + 6 + 3
		filtro = new FiltroJogos(combinacoesSeisDezPrimeiros, combinacoesSeisDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesTresCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 6 + 7 + 2
		filtro = new FiltroJogos(combinacoesSeisDezPrimeiros, combinacoesSeteDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesDoisCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 6 + 8 + 1
		filtro = new FiltroJogos(combinacoesSeisDezPrimeiros, combinacoesOitoDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesUmCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 7 + 4 + 4
		filtro = new FiltroJogos(combinacoesSeteDezPrimeiros, combinacoesQuatroDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesQuatroCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 7 + 5 + 3
		filtro = new FiltroJogos(combinacoesSeteDezPrimeiros, combinacoesCincoDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesTresCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 7 + 6 + 2
		filtro = new FiltroJogos(combinacoesSeteDezPrimeiros, combinacoesSeisDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesDoisCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 7 + 7 + 1
		filtro = new FiltroJogos(combinacoesSeteDezPrimeiros, combinacoesSeteDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesUmCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 8 + 4 + 3
		filtro = new FiltroJogos(combinacoesOitoDezPrimeiros, combinacoesQuatroDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesTresCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 8 + 5 + 2
		filtro = new FiltroJogos(combinacoesOitoDezPrimeiros, combinacoesCincoDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesDoisCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 8 + 6 + 1
		filtro = new FiltroJogos(combinacoesOitoDezPrimeiros, combinacoesSeisDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesUmCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());

		return listaCominacoes;
	}

	public static void estatisticas() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		listaTodosJogos = est.lerTodosOsJogos();
		// listaTodosJogos = est.lerTodosOsJogos();

		FiltroJogos.iniciaListaJogosPorDemanda();

		Jogo strUltimoSorteio = listaTodosJogos.get(listaTodosJogos.size() - 1);
		Jogo strSorteioAnterior = listaTodosJogos.get(listaTodosJogos.size() - 2);
		listaTodosJogos.remove(listaTodosJogos.size() - 1);
		FiltroJogos filtro = new FiltroJogos();
		FiltroJogos.setAllJogolistaJogosPorDemanda(listaTodosJogos);
		Jogo premiado = new Jogo();

		List<Jogo> listaJogosFiltrados = combinaDezUltimosPrimeiosMeio(filtro.getListaJogosPorDemanda());

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

	private static void simulacao() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();
		// listaTodosJogos = todosJogos;
		FiltroJogos.iniciaListaJogosPorDemanda();
		Jogo primeiroJogo = null;
		int contAcertos = 0;
		List<Long> numeroSorteio = new ArrayList<Long>();
		List<Long> posicoesSorteio = new ArrayList<Long>();
		List<Jogo> jogosAcertados = new ArrayList<Jogo>();
		FiltroJogos filtro = new FiltroJogos();
		long qtdJogos = 0;
		long posicaoJogo = 0;
		for (Jogo proximoJogo : todosJogos) {
			if (primeiroJogo == null) {
				primeiroJogo = proximoJogo;
			} else {
				FiltroJogos.setJogolistaJogosPorDemanda(primeiroJogo);
				primeiroJogo = proximoJogo;
				qtdJogos++;
				if (qtdJogos < 10) {
					continue;
				}

				System.err.println("Jogo: " + qtdJogos + " de " + todosJogos.size());
				List<Jogo> listOpcoesJogos = combinaDezUltimosPrimeiosMeio(filtro.getListaJogosPorDemanda());
				posicaoJogo = 0;
				TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();
				for (Jogo opcoesJogos : listOpcoesJogos) {
					Set<Integer> intersection = new HashSet<Integer>(opcoesJogos.getJogo());
					intersection.retainAll(proximoJogo.getJogo());

					int chave = intersection.size();
					if (trmap.containsKey(chave)) {
						int valor = trmap.get(chave).intValue() + 1;
						trmap.put(chave, valor);

					} else {
						trmap.put(chave, 1);
					}

					if (opcoesJogos.getJogo().equals(proximoJogo.getJogo())) {
						contAcertos++;
					}
				}
				System.out.println(trmap.toString());

			}

		}
		System.out.println("Total de jogos premiados: " + contAcertos);
	}

	public static int analisaPrimeiraLinha(int numero) {
		if (numero == 1)
			return 1;
		if (numero == 2)
			return 2;
		if (numero == 3)
			return 3;
		if (numero == 4)
			return 4;
		if (numero == 5)
			return 5;

		return 0;
	}

	public static int analisaSegundaLinha(int numero) {
		if (numero == 6)
			return 6;
		if (numero == 7)
			return 7;
		if (numero == 8)
			return 8;
		if (numero == 9)
			return 9;
		if (numero == 10)
			return 10;

		return 0;
	}

	public static int analisaTerceiraLinha(int numero) {
		if (numero == 11)
			return 11;
		if (numero == 12)
			return 12;
		if (numero == 13)
			return 13;
		if (numero == 14)
			return 14;
		if (numero == 15)
			return 15;

		return 0;
	}

	public static int analisaQuartaLinha(int numero) {
		if (numero == 16)
			return 16;
		if (numero == 17)
			return 17;
		if (numero == 18)
			return 18;
		if (numero == 19)
			return 19;
		if (numero == 20)
			return 20;

		return 0;
	}

	public static int analisaQuintaLinha(int numero) {
		if (numero == 21)
			return 21;
		if (numero == 22)
			return 22;
		if (numero == 23)
			return 23;
		if (numero == 24)
			return 24;
		if (numero == 25)
			return 25;

		return 0;
	}

	public static int analisaPrimeiraColuna(int numero) {
		if (numero == 1)
			return 1;
		if (numero == 6)
			return 6;
		if (numero == 11)
			return 11;
		if (numero == 16)
			return 16;
		if (numero == 21)
			return 21;

		return 0;
	}

	public static int analisaSegundaColuna(int numero) {
		if (numero == 2)
			return 2;
		if (numero == 7)
			return 7;
		if (numero == 12)
			return 12;
		if (numero == 17)
			return 17;
		if (numero == 22)
			return 22;

		return 0;
	}

	public static int analisaTerceiraColuna(int numero) {
		if (numero == 3)
			return 3;
		if (numero == 8)
			return 8;
		if (numero == 13)
			return 13;
		if (numero == 18)
			return 18;
		if (numero == 23)
			return 23;

		return 0;
	}

	public static int analisaQuartaColuna(int numero) {
		if (numero == 4)
			return 4;
		if (numero == 9)
			return 9;
		if (numero == 14)
			return 14;
		if (numero == 19)
			return 19;
		if (numero == 24)
			return 24;

		return 0;
	}

	public static int analisaQuintaColuna(int numero) {
		if (numero == 5)
			return 5;
		if (numero == 10)
			return 10;
		if (numero == 15)
			return 15;
		if (numero == 20)
			return 20;
		if (numero == 25)
			return 25;

		return 0;
	}

	public static Jogo analiseEstatisticasJogo(List<Jogo> todosJogos) throws FileNotFoundException, IOException {

		listaTodosJogos = todosJogos;
		Map<Integer, Integer> mapaEstatisticasJogos = estatisticasJogos();
		Map<Integer, Integer> mapaEstatisticasLinhas = estatisticasLinhas();
		Map<Integer, Integer> mapaEstatisticasColunas = estatisticasColunas();

		Jogo dezPrimeiros = dezJogosMaisFrequentes(mapaEstatisticasJogos);
		Jogo cincoDoMeio = cincoJogosDoMeio(mapaEstatisticasJogos);
		Jogo dezUltimos = dezJogosMenosFrequentes(mapaEstatisticasJogos);

		// Estatisticas est = new Estatisticas();
		// List<Jogo> todosJogos = est.lerTodosOsJogos();
		Jogo ultimoJogo = todosJogos.get(todosJogos.size() - 1);

		Set<Integer> intersectionDezPrimeiros = new HashSet<Integer>(ultimoJogo.getJogo());
		intersectionDezPrimeiros.retainAll(dezPrimeiros.getJogo());
		Set<Integer> intersectionCincoDoMeio = new HashSet<Integer>(ultimoJogo.getJogo());
		intersectionCincoDoMeio.retainAll(cincoDoMeio.getJogo());
		Set<Integer> intersectionDezUltimos = new HashSet<Integer>(ultimoJogo.getJogo());
		intersectionDezUltimos.retainAll(dezUltimos.getJogo());

		// System.err.println(">>>>>> LINHAS <<<<<<");
		// System.out.println("Linhas: " + estatisticasLinhas());
		// System.err.println(">>>>>> COLUNAS <<<<<<");
		// System.out.println("Colunas: " + estatisticasColunas());
		// System.err.println(">>>>>> RANKING DOS DEZ ÚLTIMOS JOGOS <<<<<<");
		// System.out.println("Dez primeiros: " + dezPrimeiros.getJogo());
		// System.out.println("Cinco do meio: " + cincoDoMeio.getJogo());
		// System.out.println("Dez últimos: " + dezUltimos.getJogo());
		// System.err.println(">>>>>> COMPARAÇÃO DO RANKING COM O ÚLITMO JOGO
		// <<<<<<");
		// System.out.println("Dez primeiros: " +
		// intersectionDezPrimeiros.size());
		// System.out.println("Cinco do meio: " +
		// intersectionCincoDoMeio.size());
		// System.out.println("Dez últimos: " + intersectionDezUltimos.size());

		Jogo primeiroGrupo = new Jogo();
		Jogo segundoGrupo = new Jogo();
		Jogo terceiroGrupo = new Jogo();

		int primeiraLinha = mapaEstatisticasLinhas.get(1);
		int segundaLinha = mapaEstatisticasLinhas.get(2);
		int terceiraLinha = mapaEstatisticasLinhas.get(3);
		int quartaLinha = mapaEstatisticasLinhas.get(4);
		int quintaLinha = mapaEstatisticasLinhas.get(5);

		int primeiraColuna = mapaEstatisticasColunas.get(1);
		int segundaColuna = mapaEstatisticasColunas.get(2);
		int terceiraColuna = mapaEstatisticasColunas.get(3);
		int quartaColuna = mapaEstatisticasColunas.get(4);
		int quintaColuna = mapaEstatisticasColunas.get(5);

		int ultimoGrupo = 3;
		boolean existe = false;
		int j = 0;
		int k = 0;
		int m = 1;
		List<Integer> linhasColunas = new ArrayList<Integer>();
		linhasColunas.add(primeiraLinha);
		linhasColunas.add(primeiraColuna);
		linhasColunas.add(segundaLinha);
		linhasColunas.add(segundaColuna);
		linhasColunas.add(terceiraLinha);
		linhasColunas.add(terceiraColuna);
		linhasColunas.add(quartaLinha);
		linhasColunas.add(quartaColuna);
		linhasColunas.add(quintaLinha);
		linhasColunas.add(quintaColuna);

		Jogo numerosPrimeiraLinha = new Jogo(Arrays.asList(1, 2, 3, 4, 5));
		Jogo numerosSegundaLinha = new Jogo(Arrays.asList(6, 7, 8, 9, 10));
		Jogo numerosTerceiraLinha = new Jogo(Arrays.asList(11, 12, 13, 14, 15));
		Jogo numerosQuartaLinha = new Jogo(Arrays.asList(16, 17, 18, 19, 20));
		Jogo numerosQuintaLinha = new Jogo(Arrays.asList(21, 22, 23, 24, 25));

		Jogo numerosPrimeiraColuna = new Jogo(Arrays.asList(1, 6, 11, 16, 21));
		Jogo numerosSegundaColuna = new Jogo(Arrays.asList(2, 7, 12, 17, 22));
		Jogo numerosTerceiraColuna = new Jogo(Arrays.asList(3, 8, 13, 18, 23));
		Jogo numerosQuartaColuna = new Jogo(Arrays.asList(4, 9, 14, 19, 24));
		Jogo numerosQuintaColuna = new Jogo(Arrays.asList(5, 10, 15, 20, 25));

		List<Integer> numeros = new ArrayList<Integer>(mapaEstatisticasJogos.keySet());
		while (k < linhasColunas.size()) {
			int tamanhoLaco = linhasColunas.get(k);

			for (Integer l = 1; l <= tamanhoLaco; l++) {

				int qtdNumeros = 0;
				Jogo linhaOuColuna = new Jogo();

				if (k == 0) {
					linhaOuColuna.getJogo().addAll(numerosPrimeiraLinha.getJogo());
				}
				if (k == 1) {
					linhaOuColuna.getJogo().addAll(numerosPrimeiraColuna.getJogo());
				}
				if (k == 2) {
					linhaOuColuna.getJogo().addAll(numerosSegundaLinha.getJogo());
				}
				if (k == 3) {
					linhaOuColuna.getJogo().addAll(numerosSegundaColuna.getJogo());
				}
				if (k == 4) {
					linhaOuColuna.getJogo().addAll(numerosTerceiraLinha.getJogo());
				}
				if (k == 5) {
					linhaOuColuna.getJogo().addAll(numerosTerceiraColuna.getJogo());
				}
				if (k == 6) {
					linhaOuColuna.getJogo().addAll(numerosQuartaLinha.getJogo());
				}
				if (k == 7) {
					linhaOuColuna.getJogo().addAll(numerosQuartaColuna.getJogo());
				}
				if (k == 8) {
					linhaOuColuna.getJogo().addAll(numerosQuintaLinha.getJogo());
				}
				if (k == 9) {
					linhaOuColuna.getJogo().addAll(numerosQuintaColuna.getJogo());
				}

				for (Integer integer : primeiroGrupo.getJogo()) {
					if (linhaOuColuna.getJogo().contains(integer)) {
						qtdNumeros++;
					}
				}

				for (Integer integer : segundoGrupo.getJogo()) {
					if (linhaOuColuna.getJogo().contains(integer)) {
						qtdNumeros++;
					}
				}

				for (Integer integer : terceiroGrupo.getJogo()) {
					if (linhaOuColuna.getJogo().contains(integer)) {
						qtdNumeros++;
					}
				}

				if (qtdNumeros == tamanhoLaco) {
					break;
				}

				if (ultimoGrupo == 3) {
					j = 0;
				} else if (ultimoGrupo == 1) {
					j = 10;
				} else {
					j = 15;
				}
				for (int i = j; i < numeros.size(); i++) {

					existe = linhaOuColuna.getJogo().contains(numeros.get(i));

					if (existe && i < 10 && ultimoGrupo == 3 && !primeiroGrupo.getJogo().contains(numeros.get(i))) {
						ultimoGrupo = 1;
						primeiroGrupo.getJogo().add(numeros.get(i));
						break;
					} else {
						ultimoGrupo = 1;
					}
					if (existe && i > 9 && i < 15 && ultimoGrupo == 1
							&& !segundoGrupo.getJogo().contains(numeros.get(i))) {
						ultimoGrupo = 2;
						segundoGrupo.getJogo().add(numeros.get(i));
						break;

					} else {
						ultimoGrupo = 2;
					}

					if (existe && i > 14 && ultimoGrupo == 2 && !terceiroGrupo.getJogo().contains(numeros.get(i))) {
						ultimoGrupo = 3;
						terceiroGrupo.getJogo().add(numeros.get(i));
						break;

					} else {
						ultimoGrupo = 3;
					}

				}
			}
			k++;
		}

		// }

		Jogo numerosPrimeiroGrupo = new Jogo();
		Jogo numerosSegundoGrupo = new Jogo();
		Jogo numerosTerceiroGrupo = new Jogo();
		Jogo jogoRetorno = new Jogo();
		int contNumeros = 1;
		for (Integer integer : numeros) {

			if (contNumeros < 13)
				jogoRetorno.getJogo().add(integer);
			//if (contNumeros > 9 && contNumeros < 15)
			if (contNumeros > 16) jogoRetorno.getJogo().add(integer);
			//if (contNumeros > 14 && contNumeros < 25)
				//numerosTerceiroGrupo.getJogo().add(integer);
			if (jogoRetorno.getJogo().size() > 18) break;
			contNumeros++;
		}

//		while (primeiroGrupo.getJogo().size() + segundoGrupo.getJogo().size() + terceiroGrupo.getJogo().size() < 19) {
//			if (primeiroGrupo.getJogo().size() < 10) {
//				for (Integer integer : numeros) {
//					if (!primeiroGrupo.getJogo().contains(integer)) {
//						primeiroGrupo.getJogo().add(integer);
//						break;
//					}
//				}
//				continue;
//			}
//
//			if (segundoGrupo.getJogo().size() < 3) {
//				for (Integer integer : numeros) {
//					if (!segundoGrupo.getJogo().contains(integer)) {
//						segundoGrupo.getJogo().add(integer);
//						break;
//					}
//				}
//				continue;
//
//			}
//
//			if (terceiroGrupo.getJogo().size() < 6) {
//				for (Integer integer : numeros) {
//					if (!terceiroGrupo.getJogo().contains(integer)) {
//						terceiroGrupo.getJogo().add(integer);
//						break;
//					}
//				}
//				continue;
//
//			}
//		}

		// while (jogoRetorno.getJogo().size() < 19){
		// for (Integer integer : numeros) {
		// if (!jogoRetorno.getJogo().contains(integer)){
		// jogoRetorno.getJogo().add(integer);
		// break;
		// }
		// }
		//
		// }

		

		//jogoRetorno.getJogo().addAll(primeiroGrupo.getJogo());
		//jogoRetorno.getJogo().addAll(segundoGrupo.getJogo());
		//jogoRetorno.getJogo().addAll(terceiroGrupo.getJogo());
		
			

		return jogoRetorno;

	}

	public static void simulaGeraJogoDezenoveNumeros() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();
		// listaTodosJogos = todosJogos;
		FiltroJogos.iniciaListaJogosPorDemanda();
		Jogo primeiroJogo = null;
		int contAcertos = 0;
		List<Long> numeroSorteio = new ArrayList<Long>();
		List<Long> posicoesSorteio = new ArrayList<Long>();
		List<Jogo> jogosAcertados = new ArrayList<Jogo>();
		FiltroJogos filtro = new FiltroJogos();
		long qtdJogos = 0;
		long posicaoJogo = 0;
		Map<Integer,Integer> mapaResultado = new TreeMap<Integer, Integer>();
		for (Jogo proximoJogo : todosJogos) {
			if (primeiroJogo == null) {
				primeiroJogo = proximoJogo;
			} else {
				FiltroJogos.setJogolistaJogosPorDemanda(primeiroJogo);
				primeiroJogo = proximoJogo;
				qtdJogos++;
				if (qtdJogos < 10) {
					continue;
				}

				// System.err.println("Jogo: " + qtdJogos + " de " +
				// todosJogos.size());
				Jogo opcoesJogos = analiseEstatisticasJogo(filtro.getListaJogosPorDemanda());
				posicaoJogo = 0;
				TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();

				Set<Integer> intersection = new HashSet<Integer>(opcoesJogos.getJogo());
				intersection.retainAll(proximoJogo.getJogo());
				int chave = intersection.size();
				if (mapaResultado.containsKey(chave)){
					mapaResultado.put(chave, mapaResultado.get(chave).intValue()+1);
				}else {
					mapaResultado.put(chave,1);
				}
				
				//System.out.println(intersection.size());

			}

		}
		System.out.println(mapaResultado);
	}
}
