package br.com.loteria.jogo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import br.com.loteria.combinacoes.Combinacoes;
import br.com.loteria.lotofacil.Estatisticas;
import br.com.loteria.lotofacil.Filtro;
import br.com.loteria.util.Utils;

public class Gerador {
	public void gerarJogosCSV(int quantidade, boolean gerarArquivos) throws FileNotFoundException, IOException {
	
			System.out.println("gerando jogos...");
			Filtro filtro = new Filtro();
			Estatisticas estatisticas = new Estatisticas();
			estatisticas.iniciarListas();
			filtro.iniciaListas();
			filtro.setaListaTodosSorteios(estatisticas.lerTodosOsJogos());
			
//			System.out.println("Combinando todos os números...");		
//			Jogo todosNumeros = new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,20, 21, 22, 23, 24, 25));
//			Combinacoes combinacoes = new Combinacoes(todosNumeros.getJogo(), 15);
//			filtro.setListaJogosCombinadosCompleto(combinacoes.geraListaCombinacoes());
			Combinacoes combinacoes = new Combinacoes();
			filtro.setListaJogosCombinadosCompleto(combinacoes.todosCombinacoesLotoFacil());
			
			List<Jogo> jogos = filtro.bucaListaJogosFiltrados();
	
			List<Jogo> jogosSaida = new ArrayList<Jogo>();
	
			Random random = new Random();
			List<Integer> posicoes = new ArrayList<Integer>();
	
			System.out.println("sorteando...");
	
			while (jogosSaida.size() < quantidade) {
				Integer posicao = random.nextInt(jogos.size());
					if (!posicoes.contains(posicao) && !filtro.repetido(jogos.get(posicao))) {
						posicoes.add(posicao);
						jogosSaida.add(jogos.get(posicao));
					}
			}
	
			Jogo pares = estatisticas.buscarNumerosPares();
			Jogo primos = estatisticas.buscarNumerosPrimos();
			Jogo fibonacci = estatisticas.buscarNumerosSequenciaDeFibonacci();
			Jogo quadrado = estatisticas.buscarNumerosQuadrado();
			Jogo multiplosDeTres = estatisticas.buscarNumerosMultiplosDeTres();
	
			List<String> resultCSV = new ArrayList<String>();
	
			for (
	
			Jogo jogo : jogosSaida) {
	
				Set<Integer> intersectionRepetidos = new HashSet<Integer>(jogo.getJogo());
				intersectionRepetidos.retainAll(
						filtro.buscaListaTodosSorteios().get(filtro.buscaListaTodosSorteios().size() - 1).getJogo());
	
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
	
				Map<Integer, Integer> mapaEstatisticasJogos = estatisticas
						.estatisticasJogos(filtro.buscaListaTodosSorteios().size());
				
				Set<Integer> intersectionDezMais = new HashSet<Integer>(jogo.getJogo());
				intersectionDezMais.retainAll(estatisticas.buscarDezMais(filtro.buscaListaTodosSorteios()).getJogo());
				
				Set<Integer> primeiraLinha = new HashSet<Integer>(jogo.getJogo());
				primeiraLinha.retainAll(estatisticas.buscarNumerosLinha1().getJogo());

				Set<Integer> segundaLinha = new HashSet<Integer>(jogo.getJogo());
				segundaLinha.retainAll(estatisticas.buscarNumerosLinha2().getJogo());

				Set<Integer> terceiraLinha = new HashSet<Integer>(jogo.getJogo());
				terceiraLinha.retainAll(estatisticas.buscarNumerosLinha3().getJogo());

				Set<Integer> quartaLinha = new HashSet<Integer>(jogo.getJogo());
				quartaLinha.retainAll(estatisticas.buscarNumerosLinha4().getJogo());

				Set<Integer> quintaLinha = new HashSet<Integer>(jogo.getJogo());
				quintaLinha.retainAll(estatisticas.buscarNumerosLinha5().getJogo());
				
				
				Set<Integer> primeiraColuna = new HashSet<Integer>(jogo.getJogo());
				primeiraColuna.retainAll(estatisticas.buscarNumerosColuna1().getJogo());

				Set<Integer> segundaColuna = new HashSet<Integer>(jogo.getJogo());
				segundaColuna.retainAll(estatisticas.buscarNumerosColuna2().getJogo());

				Set<Integer> terceiraColuna = new HashSet<Integer>(jogo.getJogo());
				terceiraColuna.retainAll(estatisticas.buscarNumerosColuna3().getJogo());

				Set<Integer> quartaColuna = new HashSet<Integer>(jogo.getJogo());
				quartaColuna.retainAll(estatisticas.buscarNumerosColuna4().getJogo());

				Set<Integer> quintaColuna = new HashSet<Integer>(jogo.getJogo());
				quintaColuna.retainAll(estatisticas.buscarNumerosColuna5().getJogo());
	
	//			Jogo dezPrimeiros = estatisticas.dezJogosMaisFrequentes(mapaEstatisticasJogos);
	//			Jogo cincoDoMeio = estatisticas.cincoJogosDoMeio(mapaEstatisticasJogos);
	//			Jogo dezUltimos = estatisticas.dezJogosMenosFrequentes(mapaEstatisticasJogos);
	//
	//			Set<Integer> intersectionDezPrimeiros = new HashSet<Integer>(jogo.getJogo());
	//			intersectionDezPrimeiros.retainAll(dezPrimeiros.getJogo());
	//
	//			Set<Integer> intersectionCincoDoMeio = new HashSet<Integer>(jogo.getJogo());
	//			intersectionCincoDoMeio.retainAll(cincoDoMeio.getJogo());
	//
	//			Set<Integer> intersectionDezUltimos = new HashSet<Integer>(jogo.getJogo());
	//			intersectionDezUltimos.retainAll(dezUltimos.getJogo());
	
	//			String strJogos = "jogos.add(new Jogo(Arrays.asList("
	//					+ jogo.getJogo().toString().replace("[", "").replace("]", "") + ")));";
	//			resultCSV.add(strJogos + "#Repetidos#" + intersectionRepetidos.size() + "#Pares#" + intersectionPares.size()
	//					+ "#Primos#" + intersectionPrimos.size() + "#Fibonacci#" + intersectionFibonacci.size()
	//					+ "#Quadrado#" + intersectionQuadrado.size() + "#Multiplos de Tres#"
	//					+ intersectionMultiplosDeTres.size() + "#Dez primeiros est.#" + intersectionDezPrimeiros.size()
	//					+ "#Cinco do meio est.#" + intersectionCincoDoMeio.size() + "#Dez ultimos est.#" + intersectionDezUltimos.size()
	//					
	//					);
				
				String strJogos = "jogos.add(new Jogo(Arrays.asList("+ jogo.getJogo().toString().replace("[", "").replace("]", "") + ")));";
				
				if (gerarArquivos){
				resultCSV.add(strJogos 
						+ "#Repetidos#" + intersectionRepetidos.size() 
						+ "#Pares#" + intersectionPares.size()
						+ "#Primos#" + intersectionPrimos.size() 
						+ "#Fibonacci#" + intersectionFibonacci.size()
						+ "#Quadrado#" + intersectionQuadrado.size() 
						+ "#Multiplos de Tres#" + intersectionMultiplosDeTres.size() 
						+ "#Dez Mais#" + intersectionDezMais.size()
						+ "#Linhas#" + primeiraLinha.size() + "" + segundaLinha.size() +  "" + terceiraLinha.size() +  "" + quartaLinha.size() +  "" + quintaLinha.size()
					 	+ "#Colunas#" + primeiraColuna.size() + "" + segundaColuna.size() +  "" + terceiraColuna.size() +  "" + quartaColuna.size() +  "" + quintaColuna.size()
						);
				} else {
					resultCSV.add(strJogos);
				}
			}
	
			filtro.limpaListas();
			estatisticas.limparListas();
			if (gerarArquivos){ 
			Utils.gerarCSV(resultCSV, "jogos");
			System.out.println("Arquivos salvos com sucesso!");
			} else {
				System.out.println("List<Jogo> jogos = new ArrayList<Jogo>();");
				for (String string : resultCSV) {
					System.out.println(string);
					
				}
				System.out.println("consultaSorteio(jogos);");
			}
			
			
	
		}

	public void gerarJogosCSV(int quantidade) throws FileNotFoundException, IOException {

		System.out.println("gerando jogos...");
		Filtro filtro = new Filtro();
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();
		filtro.iniciaListas();
		filtro.setaListaTodosSorteios(estatisticas.lerTodosOsJogos());

		List<Jogo> jogos = filtro.bucaListaJogosFiltrados();

		List<Jogo> jogosSaida = new ArrayList<Jogo>();

		Random random = new Random();
		List<Integer> posicoes = new ArrayList<Integer>();

		System.out.println("sorteando...");

		while (jogosSaida.size() < quantidade) {
			Integer posicao = random.nextInt(jogos.size());
				if (!posicoes.contains(posicao) && !filtro.repetido(jogos.get(posicao))) {
					posicoes.add(posicao);
					jogosSaida.add(jogos.get(posicao));
				}
		}

		Jogo pares = estatisticas.buscarNumerosPares();
		Jogo primos = estatisticas.buscarNumerosPrimos();
		Jogo fibonacci = estatisticas.buscarNumerosSequenciaDeFibonacci();
		Jogo quadrado = estatisticas.buscarNumerosQuadrado();
		Jogo multiplosDeTres = estatisticas.buscarNumerosMultiplosDeTres();

		List<String> resultCSV = new ArrayList<String>();

		for (

		Jogo jogo : jogosSaida) {

			Set<Integer> intersectionRepetidos = new HashSet<Integer>(jogo.getJogo());
			intersectionRepetidos.retainAll(
					filtro.buscaListaTodosSorteios().get(filtro.buscaListaTodosSorteios().size() - 1).getJogo());

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

			Map<Integer, Integer> mapaEstatisticasJogos = estatisticas
					.estatisticasJogos(filtro.buscaListaTodosSorteios().size());

//			Jogo dezPrimeiros = estatisticas.dezJogosMaisFrequentes(mapaEstatisticasJogos);
//			Jogo cincoDoMeio = estatisticas.cincoJogosDoMeio(mapaEstatisticasJogos);
//			Jogo dezUltimos = estatisticas.dezJogosMenosFrequentes(mapaEstatisticasJogos);
//
//			Set<Integer> intersectionDezPrimeiros = new HashSet<Integer>(jogo.getJogo());
//			intersectionDezPrimeiros.retainAll(dezPrimeiros.getJogo());
//
//			Set<Integer> intersectionCincoDoMeio = new HashSet<Integer>(jogo.getJogo());
//			intersectionCincoDoMeio.retainAll(cincoDoMeio.getJogo());
//
//			Set<Integer> intersectionDezUltimos = new HashSet<Integer>(jogo.getJogo());
//			intersectionDezUltimos.retainAll(dezUltimos.getJogo());

//			String strJogos = "jogos.add(new Jogo(Arrays.asList("
//					+ jogo.getJogo().toString().replace("[", "").replace("]", "") + ")));";
//			resultCSV.add(strJogos + "#Repetidos#" + intersectionRepetidos.size() + "#Pares#" + intersectionPares.size()
//					+ "#Primos#" + intersectionPrimos.size() + "#Fibonacci#" + intersectionFibonacci.size()
//					+ "#Quadrado#" + intersectionQuadrado.size() + "#Multiplos de Tres#"
//					+ intersectionMultiplosDeTres.size() + "#Dez primeiros est.#" + intersectionDezPrimeiros.size()
//					+ "#Cinco do meio est.#" + intersectionCincoDoMeio.size() + "#Dez ultimos est.#" + intersectionDezUltimos.size()
//					
//					);
			
			String strJogos = "jogos.add(new Jogo(Arrays.asList("
					+ jogo.getJogo().toString().replace("[", "").replace("]", "") + ")));";
			resultCSV.add(strJogos + "#Repetidos#" + intersectionRepetidos.size() + "#Pares#" + intersectionPares.size()
					+ "#Primos#" + intersectionPrimos.size() + "#Fibonacci#" + intersectionFibonacci.size()
					+ "#Quadrado#" + intersectionQuadrado.size() + "#Multiplos de Tres#");
		}

		filtro.limpaListas();
		estatisticas.limparListas();
		Utils.gerarCSV(resultCSV, "jogos");
		//estatisticas.estatisticasUltimosSorteioCSV(50,true);
		System.out.println("Arquivos salvos com sucesso!");

	}

	public void gerarJogos(int quantidade) throws FileNotFoundException, IOException {
		System.out.println("gerando jogos...");
		Filtro filtro = new Filtro();
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();
		filtro.iniciaListas();
		filtro.setaListaTodosSorteios(estatisticas.lerTodosOsJogos());
		
		Combinacoes combinacoes = new Combinacoes();
		filtro.setListaJogosCombinadosCompleto(combinacoes.todosCombinacoesLotoFacil());

		List<Jogo> jogos = filtro.bucaListaJogosFiltrados();

		List<Jogo> jogosSaida = new ArrayList<Jogo>();

		Random random = new Random();
		List<Integer> posicoes = new ArrayList<Integer>();

		System.out.println("sorteando...");

		while (jogosSaida.size() < quantidade) {
			Integer posicao = random.nextInt(jogos.size());
			if (posicao > 7055 && posicao < 557530) { // 9

				if (!posicoes.contains(posicao) && !filtro.repetido(jogos.get(posicao))) {
					posicoes.add(posicao);
					jogosSaida.add(jogos.get(posicao));
				}
			}
		}

		System.out.println("List<Jogo> jogos = new ArrayList<Jogo>();");
		for (

		Jogo jogo : jogosSaida) {
			System.out.println("jogos.add(new Jogo(Arrays.asList("
					+ jogo.getJogo().toString().replace("[", "").replace("]", "") + ")));");
		}
	
		System.out.println("consultaSorteio(jogos);");

	}
}
