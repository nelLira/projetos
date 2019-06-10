package br.com.loteria.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import br.com.loteria.combinacoes.Combinacoes;
import br.com.loteria.jogo.Jogo;
import br.com.loteria.lotofacil.Estatisticas;
import br.com.loteria.lotofacil.Filtro;

public class Testes {

	public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException {
	
		teste12();
		
		Filtro filtro = new Filtro();
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();
		filtro.iniciaListas();
		filtro.setaListaTodosSorteios(estatisticas.lerTodosOsJogos());
		
		Jogo pares = estatisticas.buscarNumerosPares();
		Jogo primos = estatisticas.buscarNumerosPrimos();
		Jogo fibonacci = estatisticas.buscarNumerosSequenciaDeFibonacci();
		Jogo quadrado = estatisticas.buscarNumerosQuadrado();
		Jogo multiplosDeTres = estatisticas.buscarNumerosMultiplosDeTres();
		Jogo numerosImportantes = estatisticas.buscarNumerosImportantes();

	

		for (Jogo jogo : estatisticas.lerTodosOsJogos()) {

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

			Set<Integer> intersectionDezMais = new HashSet<Integer>(jogo.getJogo());
			intersectionDezMais.retainAll(estatisticas.buscarDezMais(filtro.buscaListaTodosSorteios()).getJogo());
			
			Set<Integer> intersectionNumerosImportantes = new HashSet<Integer>(jogo.getJogo());
			intersectionNumerosImportantes.retainAll(numerosImportantes.getJogo());
			
			List<Integer> list = new ArrayList<>();
			list.add(intersectionPares.size());
			list.add(intersectionPrimos.size());
			list.add(intersectionFibonacci.size());
			list.add(intersectionQuadrado.size());
			list.add(intersectionMultiplosDeTres.size());
			list.add(intersectionDezMais.size());
			list.add(intersectionNumerosImportantes.size());
			
			
			int maxNumeroFiltroRepetido = 0;
			
			for (int j = 1; j < 13;j++) { 
				maxNumeroFiltroRepetido = (maxNumeroFiltroRepetido > Collections.frequency(list, j)) ? maxNumeroFiltroRepetido : Collections.frequency(list, j);
			}
		//System.out.println(list.toString() + " - " + maxNumeroFiltroRepetido);
			System.out.println(maxNumeroFiltroRepetido);
			
		}
	}
	
	public static void teste13() throws FileNotFoundException, IOException, URISyntaxException{
		Filtro filtro = new Filtro();
		Estatisticas estatisticas = new Estatisticas();
		List<Jogo> todosJogos = estatisticas.lerTodosOsJogos();
		Map<Integer, List<Integer>> mapaSequencias = new HashMap<Integer, List<Integer>>();
		int contTotal = 0;
		int numeroJogo = 0;
		for (Jogo jogo : todosJogos) {
			numeroJogo++;
			int cont = 0;
			int numero = 0;
			int maiorSequencia = 0;
			List<Integer> sequencias = new ArrayList<>();
			for (Integer n : jogo.getJogo()) {

				if (numero != 0) {
					if (numero + 1 == n) {
						cont++;
//						if (maiorSequencia < cont) {
//							maiorSequencia = cont;
//						}
					} else {
						sequencias.add(cont + 1);
						cont = 0;
					}

				}
				numero = n;
			}
			sequencias.add(cont + 1);
			//mapaSequencias.put(numeroJogo, sequencias);
			List<Integer> verificar = new ArrayList<>(Arrays.asList(1));
			Set<Integer> intersectionRepetidos = new HashSet<Integer>(sequencias);
			int contRepetidos = 0;
			intersectionRepetidos.retainAll(verificar);
			for (Integer n : sequencias) 
				if (n == 4) contRepetidos++;
			
			Collections.sort(sequencias);
			//	System.out.println(numeroJogo + " - " + sequencias.toString() + " - " + contRepetidos);
			System.out.println(contRepetidos);
			sequencias.clear();
			
		}
	//	System.out.println(mapaSequencias);
		}
	
	public static void teste12() throws FileNotFoundException, IOException {
		
		Filtro filtro = new Filtro();
		Estatisticas estatisticas = new Estatisticas();
		List<Jogo> todosJogos = estatisticas.lerTodosOsJogos();
		Jogo anterior = null;
		int cont = 0;
		for (Jogo atual : todosJogos) {

			if (anterior != null) {
				Set<Integer> intersectionRepetidos = new HashSet<Integer>(anterior.getJogo());
				intersectionRepetidos.retainAll(atual.getJogo());

				if (intersectionRepetidos.size() == 9) {
					
					cont++;
				}
			}
			anterior = atual;
		
	}
		System.out.println(cont);

	}

	public static void teste11() throws FileNotFoundException, IOException, URISyntaxException {
		Filtro filtro = new Filtro();
		Estatisticas estatisticas = new Estatisticas();
		List<Jogo> todosJogos = estatisticas.lerTodosOsJogos();
		// 1 [7,8];
		// 2[7,8];3[6,7,8];4[8];5[8];6[7,8];7[6,7,8];8[7];9[7];10[8,9];11[7,8,9,10];12[7,8,9];13[8,9];14[7,8,9];15[6,7,8];16[7,8];17[6,7,8];18[7,8,9];19[7,8];20[7,8];21[7,8];22[6,7];23[7,8];24[7,8,9];25[7,8,9];

		// int numero = 2;
		List<Integer> listaNumeros = new ArrayList<>();

		List<Integer> numeros = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
				18, 19, 20, 21, 22, 23, 24, 25));
		for (Integer numero : numeros) {
			Map<Integer, Integer> mapaNumeros = new TreeMap<Integer, Integer>();
			int frequencia = 0;
			for (Jogo jogo : todosJogos) {
				if (jogo.getJogo().contains(numero)) {
					frequencia++;
				} else {
					if (mapaNumeros.containsKey(frequencia)) {
						mapaNumeros.put(frequencia, mapaNumeros.get(frequencia).intValue() + 1);
					} else {
						mapaNumeros.put(frequencia, 1);
					}
					frequencia = 0;
				}

			}
			System.out.println(numero + " - " + mapaNumeros);
		}

		// 1 - {0=269, 1=172, 2=120, 3=62, 4=34, 5=19, 6=12, 7=10, 8=5, 9=3, 10=1, 11=4,
		// 17=1}
		// 2 - {0=291, 1=166, 2=92, 3=61, 4=43, 5=15, 6=22, 7=6, 8=9, 9=2, 10=3, 11=3}
		// 3 - {0=263, 1=186, 2=120, 3=56, 4=41, 5=22, 6=9, 7=6, 8=5, 9=4, 11=3, 12=1,
		// 18=1}
		// 4 - {0=274, 1=177, 2=115, 3=56, 4=39, 5=20, 6=12, 7=15, 8=5, 9=1, 10=2, 12=2}
		// 5 - {0=290, 1=160, 2=125, 3=64, 4=29, 5=21, 6=14, 7=7, 8=8, 9=2, 10=2, 11=2,
		// 14=1}
		// 6 - {0=311, 1=208, 2=99, 3=57, 4=28, 5=23, 6=16, 7=9, 8=5, 9=1, 10=1, 12=2,
		// 14=1}
		// 7 - {0=286, 1=200, 2=114, 3=70, 4=30, 5=24, 6=6, 7=10, 8=4, 9=1, 10=2, 11=1,
		// 14=1}
		// 8 - {0=322, 1=201, 2=135, 3=62, 4=27, 5=16, 6=12, 7=9, 8=3, 9=3}
		// 9 - {0=302, 1=189, 2=106, 3=56, 4=35, 5=26, 6=15, 7=10, 8=3, 9=3, 10=1, 13=1}
		// 10 - {0=262, 1=166, 2=120, 3=61, 4=30, 5=27, 6=9, 7=12, 8=7, 9=6, 10=1, 12=1}
		// 11 - {0=283, 1=164, 2=98, 3=65, 4=31, 5=22, 6=18, 7=8, 8=4, 9=5, 10=6, 12=1,
		// 13=1}
		// 12 - {0=291, 1=185, 2=106, 3=73, 4=36, 5=17, 6=5, 7=11, 8=7, 9=4, 10=1, 14=1,
		// 15=1}
		// 13 - {0=268, 1=165, 2=81, 3=65, 4=42, 5=27, 6=17, 7=12, 8=6, 9=4, 10=1, 11=1,
		// 12=1}
		// 14 - {0=285, 1=192, 2=84, 3=65, 4=32, 5=21, 6=16, 7=7, 8=5, 9=4, 10=2, 11=2,
		// 13=2, 17=1}
		// 15 - {0=302, 1=163, 2=108, 3=60, 4=46, 5=20, 6=9, 7=12, 8=7, 9=2, 10=1, 11=1,
		// 13=1}
		// 16 - {0=320, 1=204, 2=121, 3=56, 4=40, 5=17, 6=10, 7=6, 8=4, 9=1, 10=2, 11=1}
		// 17 - {0=301, 1=181, 2=88, 3=65, 4=41, 5=24, 6=12, 7=10, 8=5, 9=1, 10=3, 14=2}
		// 18 - {0=290, 1=161, 2=112, 3=68, 4=38, 5=28, 6=13, 7=3, 8=6, 9=4, 10=2, 11=1}
		// 19 - {0=280, 1=194, 2=92, 3=65, 4=44, 5=18, 6=14, 7=8, 8=4, 9=3, 10=3, 11=2}
		// 20 - {0=274, 1=170, 2=111, 3=67, 4=35, 5=15, 6=10, 7=11, 8=8, 9=2, 10=2,
		// 11=1, 12=2, 13=2}
		// 21 - {0=317, 1=158, 2=102, 3=63, 4=34, 5=34, 6=15, 7=3, 8=7, 10=2, 11=2,
		// 15=1}
		// 22 - {0=289, 1=170, 2=110, 3=67, 4=44, 5=21, 6=12, 7=10, 8=3, 9=1, 10=2,
		// 11=1, 12=1}
		// 23 - {0=287, 1=176, 2=110, 3=64, 4=38, 5=23, 6=17, 7=9, 8=6, 9=1, 12=1}
		// 24 - {0=271, 1=184, 2=79, 3=58, 4=32, 5=34, 6=18, 7=7, 8=6, 9=6, 10=1, 12=1,
		// 13=1, 14=1}
		// 25 - {0=281, 1=185, 2=96, 3=57, 4=25, 5=24, 6=19, 7=11, 8=6, 9=6, 10=2, 11=1,
		// 13=1}

	}

	public static void teste10() throws FileNotFoundException, IOException, URISyntaxException {
		Filtro filtro = new Filtro();
		Estatisticas estatisticas = new Estatisticas();
		List<Jogo> todosJogos = estatisticas.lerTodosOsJogos();

		int contTotal = 0;
		int numeroJogo = 0;

		for (Jogo jogo : todosJogos) {
			numeroJogo++;
			int cont = 0;
			int numero = 0;
			int maiorSequencia = 0;
			for (Integer n : jogo.getJogo()) {

				if (numero != 0) {
					if (numero + 1 == n) {
						cont++;
						if (maiorSequencia < cont) {
							maiorSequencia = cont;
						}
					} else {
						cont = 0;
					}

				}
				numero = n;
			}
			System.out.println(maiorSequencia + 1 + " - " + jogo.getJogo());
		}
	}

	public static void teste9() throws FileNotFoundException, IOException {
		System.out.println("gerando jogos...");
		Filtro filtro = new Filtro();
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();
		filtro.iniciaListas();
		filtro.setaListaTodosSorteios(estatisticas.lerTodosOsJogos());

		// Combinacoes combinacoes = new Combinacoes();
		// filtro.setListaJogosCombinadosCompleto(combinacoes.todosCombinacoesLotoFacil());

		// List<Jogo> jogos = filtro.bucaListaJogosFiltrados();

		List<Jogo> jogosSaida = new ArrayList<Jogo>();

		Random random = new Random();
		List<Integer> posicoes = new ArrayList<Integer>();

		System.out.println("sorteando...");

		// jogosSaida.addAll(estatisticas.lerTodosOsJogos());
		jogosSaida.add(new Jogo(Arrays.asList(1, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 10, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9, 10, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 6, 7, 8, 9, 10, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 4, 5, 6, 7, 8, 9, 10, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 3, 4, 5, 6, 7, 8, 9, 10, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 11, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9, 11, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 6, 7, 8, 9, 11, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 4, 5, 6, 7, 8, 9, 11, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 3, 4, 5, 6, 7, 8, 9, 11, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 6, 7, 8, 10, 11, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 3, 4, 5, 6, 7, 8, 10, 11, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 4, 6, 7, 8, 9, 10, 11, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 3, 4, 6, 7, 8, 9, 10, 11, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 4, 5, 6, 7, 8, 9, 10, 11, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9, 12, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 8, 9, 12, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 5, 6, 7, 8, 9, 12, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 12, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 10, 12, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 8, 10, 12, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 5, 6, 7, 8, 10, 12, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 10, 12, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5, 7, 9, 10, 12, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 6, 7, 9, 10, 12, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 3, 4, 5, 6, 7, 9, 10, 12, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5, 8, 9, 10, 12, 14, 15, 16, 17, 19, 22)));
		jogosSaida.add(new Jogo(Arrays.asList(1, 2, 3, 4, 6, 8, 9, 10, 12, 14, 15, 16, 17, 19, 22)));

		Jogo pares = estatisticas.buscarNumerosPares();
		Jogo primos = estatisticas.buscarNumerosPrimos();
		Jogo fibonacci = estatisticas.buscarNumerosSequenciaDeFibonacci();
		Jogo quadrado = estatisticas.buscarNumerosQuadrado();
		Jogo multiplosDeTres = estatisticas.buscarNumerosMultiplosDeTres();
		Jogo numerosImportantes = estatisticas.buscarNumerosImportantes();

		List<String> resultCSV = new ArrayList<String>();

		for (Jogo jogo : jogosSaida) {

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

			Set<Integer> intersectionDezMais = new HashSet<Integer>(jogo.getJogo());
			intersectionDezMais.retainAll(estatisticas.buscarDezMais(filtro.buscaListaTodosSorteios()).getJogo());

			Set<Integer> intersectionNumerosImportantes = new HashSet<Integer>(jogo.getJogo());
			intersectionNumerosImportantes.retainAll(numerosImportantes.getJogo());

			List<Integer> list = new ArrayList<>();
			list.add(intersectionPares.size());
			list.add(intersectionPrimos.size());
			list.add(intersectionFibonacci.size());
			list.add(intersectionQuadrado.size());
			list.add(intersectionMultiplosDeTres.size());
			list.add(intersectionDezMais.size());
			list.add(intersectionNumerosImportantes.size());

			int qtd = 5;
			if (Collections.frequency(list, 1) > qtd || Collections.frequency(list, 2) > qtd
					|| Collections.frequency(list, 3) > qtd || Collections.frequency(list, 4) > qtd
					|| Collections.frequency(list, 5) > qtd || Collections.frequency(list, 6) > qtd
					|| Collections.frequency(list, 7) > qtd || Collections.frequency(list, 8) > qtd
					|| Collections.frequency(list, 9) > qtd || Collections.frequency(list, 10) > qtd
					|| Collections.frequency(list, 11) > qtd || Collections.frequency(list, 12) > qtd

			) {
				System.out.println("entrou no if");
			} else {
				System.out.println("entrou no else");
			}

			List<Integer> lista = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
			if (!lista.contains(Collections.frequency(list, 1)) && !lista.contains(Collections.frequency(list, 2))
					&& !lista.contains(Collections.frequency(list, 3))
					&& !lista.contains(Collections.frequency(list, 4))
					&& !lista.contains(Collections.frequency(list, 5))
					&& !lista.contains(Collections.frequency(list, 6))
					&& !lista.contains(Collections.frequency(list, 7))
					&& !lista.contains(Collections.frequency(list, 8))
					&& !lista.contains(Collections.frequency(list, 9))
					&& !lista.contains(Collections.frequency(list, 10))
					&& !lista.contains(Collections.frequency(list, 11))
					&& !lista.contains(Collections.frequency(list, 12))

			) {
				System.out.println("entrou no if");
			} else {
				System.out.println("entrou no else");
			}

		}

	}

	public static void teste8() throws FileNotFoundException, IOException {
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

		jogosSaida.addAll(estatisticas.lerTodosOsJogos());

		Jogo pares = estatisticas.buscarNumerosPares();
		Jogo primos = estatisticas.buscarNumerosPrimos();
		Jogo fibonacci = estatisticas.buscarNumerosSequenciaDeFibonacci();
		Jogo quadrado = estatisticas.buscarNumerosQuadrado();
		Jogo multiplosDeTres = estatisticas.buscarNumerosMultiplosDeTres();
		Jogo numerosImportantes = estatisticas.buscarNumerosImportantes();

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

			Set<Integer> intersectionDezMais = new HashSet<Integer>(jogo.getJogo());
			intersectionDezMais.retainAll(estatisticas.buscarDezMais(filtro.buscaListaTodosSorteios()).getJogo());

			Set<Integer> intersectionNumerosImportantes = new HashSet<Integer>(jogo.getJogo());
			intersectionNumerosImportantes.retainAll(numerosImportantes.getJogo());

			List<Integer> lista = new ArrayList<>();
			lista.add(intersectionPares.size());
			lista.add(intersectionPrimos.size());
			lista.add(intersectionFibonacci.size());
			lista.add(intersectionQuadrado.size());
			lista.add(intersectionMultiplosDeTres.size());
			lista.add(intersectionDezMais.size());
			lista.add(intersectionNumerosImportantes.size());

			Integer contaUm = 0;
			Integer contaDois = 0;
			Integer contaTres = 0;
			Integer contaQuatro = 0;
			Integer contaCinco = 0;
			Integer contaSeis = 0;
			Integer contaSete = 0;
			Integer contaOito = 0;
			Integer contaNove = 0;
			Integer contaDez = 0;
			Integer contaOnze = 0;
			Integer contaDoze = 0;
			Integer qtd = 3;

			for (Integer numero : lista) {
				System.out.println("numero: " + numero);
				if (numero == 1)
					contaUm++;
				if (numero == 2)
					contaDois++;
				if (numero == 3)
					contaTres++;
				if (numero == 4)
					contaQuatro++;
				if (numero == 5)
					contaCinco++;
				if (numero == 6)
					contaSeis++;
				if (numero == 7)
					contaSete++;
				if (numero == 8)
					contaOito++;
				if (numero == 9)
					contaNove++;
				if (numero == 10)
					contaDez++;
				if (numero == 11)
					contaOnze++;
				if (numero == 12)
					contaDoze++;

				if (contaUm > qtd || contaDois > qtd || contaTres > qtd || contaQuatro > qtd || contaCinco > qtd
						|| contaSeis > qtd || contaSete > qtd || contaOito > qtd || contaNove > qtd || contaDez > qtd
						|| contaOnze > qtd || contaDoze > qtd) {
					System.out.println(Integer.compare(contaQuatro, qtd) > 0);

				}

				if (contaQuatro > qtd) {
					System.out.println(Integer.compare(contaQuatro, qtd) > 0);
				}
			}

			System.out.println("################### \n");
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

			String strJogos = "jogos.add(new Jogo(Arrays.asList("
					+ jogo.getJogo().toString().replace("[", "").replace("]", "") + ")));";

			if (true) {
				resultCSV.add(strJogos + "#Repetidos#" + intersectionRepetidos.size() + "#Pares#"
						+ intersectionPares.size() + "#Primos#" + intersectionPrimos.size() + "#Fibonacci#"
						+ intersectionFibonacci.size() + "#Quadrado#" + intersectionQuadrado.size()
						+ "#Multiplos de Tres#" + intersectionNumerosImportantes.size() + "#Dez Mais#"
						+ intersectionDezMais.size() + "#Numeros Importantes#" + intersectionNumerosImportantes.size()
						+ "#Linhas#" + primeiraLinha.size() + "" + segundaLinha.size() + "" + terceiraLinha.size() + ""
						+ quartaLinha.size() + "" + quintaLinha.size() + "#Colunas#" + primeiraColuna.size() + ""
						+ segundaColuna.size() + "" + terceiraColuna.size() + "" + quartaColuna.size() + ""
						+ quintaColuna.size());
			} else {
				resultCSV.add(strJogos);
			}
		}

		filtro.limpaListas();
		estatisticas.limparListas();
		if (true) {
			Utils.gerarCSV(resultCSV, "teste");
			System.out.println("Arquivos salvos com sucesso!");
		} else {
			System.out.println("List<Jogo> jogos = new ArrayList<Jogo>();");
			for (String string : resultCSV) {
				System.out.println(string);

			}
			System.out.println("consultaSorteio(jogos);");
		}
	}

	public void teste7() throws FileNotFoundException, IOException {
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();
		List<Jogo> listaTodosJogos = estatisticas.lerTodosOsJogos();

		Jogo primeiroGrupo = new Jogo(Arrays.asList(5, 6, 7, 12, 13, 14, 19, 20, 21)); // {1=2, 2=12, 3=84, 4=303,
																						// 5=538, 6=549, 7=254, 8=50,
																						// 9=3}
		Jogo segundoGrupo = new Jogo(Arrays.asList(1, 2, 3, 4, 8, 9, 10, 11, 15, 16, 17, 18, 22, 23, 24, 25)); // {6=3,
																												// 7=50,
																												// 8=254,
																												// 9=549,
																												// 10=538,
																												// 11=303,
																												// 12=84,
																												// 13=12,
																												// 14=2}
		HashMap<Integer, Integer> mapaPrimeiroGrupo = new HashMap<>();
		int conte = 0;

		for (Jogo jogo : listaTodosJogos) {
			Set<Integer> intersectionPrimeiroGrupo = new HashSet<Integer>(jogo.getJogo());
			intersectionPrimeiroGrupo.retainAll(primeiroGrupo.getJogo());
			Integer chavePrimeiroGrupo = intersectionPrimeiroGrupo.size();

			Set<Integer> intersectionSegundoGrupo = new HashSet<Integer>(jogo.getJogo());
			intersectionSegundoGrupo.retainAll(segundoGrupo.getJogo());
			Integer chaveSegundoGrupo = intersectionSegundoGrupo.size();

			if ((chavePrimeiroGrupo > 2 && chavePrimeiroGrupo < 9)
					&& (chaveSegundoGrupo > 6 && chaveSegundoGrupo < 13)) {
				conte++;
			}

			// if (chavePrimeiroGrupo > 0 && chaveSegundoGrupo > 0) conte++;

			// if (mapaPrimeiroGrupo.containsKey(chaveSegundoGrupo)) {
			//
			// Integer valor = mapaPrimeiroGrupo.get(chaveSegundoGrupo).intValue() + 1;
			// mapaPrimeiroGrupo.put(chaveSegundoGrupo, valor);
			//
			// }else {
			// mapaPrimeiroGrupo.put(chaveSegundoGrupo, 1);
			// }
		}
		//
		System.out.println(conte);
	}

	public void testes6() throws FileNotFoundException, IOException {
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();
		List<Jogo> listaTodosJogos = estatisticas.lerTodosOsJogos();
		Jogo primeiroJogo = null;
		int contJogos = 0;
		int contNumero = 0;
		int numeroAnterior = 0;
		int sequencia = 0;
		int qtdMax = 0;
		String sequencias = null;
		// 1=6; 2=6; 3=6; 4=6
		for (Jogo jogo : listaTodosJogos) {
			for (Integer numero : jogo.getJogo()) {
				if (numeroAnterior > 0) {
					if (numero == numeroAnterior + 1) {
						sequencia++;
					} else {
						if (sequencias == null) {
							sequencias = Integer.toString(sequencia);
						} else {
							sequencias = sequencias + " - " + Integer.toString(sequencia);
						}
						sequencia = 0;
					}
				}
				numeroAnterior = numero;
			}
			System.out.println(sequencias);
			sequencias = null;
		}
	}

	public void teste5() throws FileNotFoundException, IOException {
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();
		List<Jogo> listaTodosJogos = estatisticas.lerTodosOsJogos();
		Jogo primeiroJogo = null;
		int contJogos = 0;
		int contNumero = 0;
		int qtdMax = 0;
		// 1=11; 2=11; 3=11; 4=10; 5=11; 6=10; 7=10; 8=9;
		for (Jogo jogo : listaTodosJogos) {
			if (jogo.getJogo().contains(8)) {
				contNumero++;
			} else {
				if (contNumero > 5) {
					System.out.println(contNumero);
				}
				// if (qtdMax < contNumero) {
				// qtdMax = contNumero;
				// }
				contNumero = 0;
			}
		}

	}

	public void teste4() throws FileNotFoundException, IOException {
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();
		List<Jogo> listaTodosJogos = estatisticas.lerTodosOsJogos();
		Jogo primeiroJogo = null;
		int contJogos = 0;
		for (Jogo jogo : listaTodosJogos) {
			int cont = 0;
			if (jogo.getJogo().contains(1) || jogo.getJogo().contains(2) || jogo.getJogo().contains(6)
					|| jogo.getJogo().contains(7)) {
				cont++;
			}

			if (jogo.getJogo().contains(4) || jogo.getJogo().contains(5) || jogo.getJogo().contains(9)
					|| jogo.getJogo().contains(10)) {
				cont++;
			}

			if (jogo.getJogo().contains(16) || jogo.getJogo().contains(17) || jogo.getJogo().contains(21)
					|| jogo.getJogo().contains(22)) {
				cont++;
			}

			if (jogo.getJogo().contains(19) || jogo.getJogo().contains(20) || jogo.getJogo().contains(24)
					|| jogo.getJogo().contains(25)) {
				cont++;
			}

			if (cont > 3) {
				contJogos++;
			}
		}
		System.out.println(contJogos);
	}

	public void teste3() throws FileNotFoundException, IOException, URISyntaxException {
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();

		List<Jogo> listaTodosJogos = estatisticas.lerTodosOsJogos();
		Jogo primeiroJogo = null;
		int contJogos = 0;
		int soma = 0;
		List<Jogo> listaJogosFiltradosNove = new ArrayList<Jogo>();

		Filtro filtro = new Filtro();
		filtro.iniciaListas();

		List<Integer> lista = Arrays.asList(4, 5, 6, 7, 8); // {2=2, 3=24,
															// 4=140, 5=387,
															// 6=567, 7=415,
															// 8=165, 9=24,
															// 10=3}
		Map<Integer, Integer> mapaNumeros = new TreeMap<Integer, Integer>();
		for (Jogo proximoJogo : listaTodosJogos) {
			if (primeiroJogo == null) {
				primeiroJogo = proximoJogo;
			} else {

				int repetidos = 0;
				filtro.setaJogoListaTodosSorteios(primeiroJogo);
				for (Integer i : proximoJogo.getJogo()) {
					if (primeiroJogo.getJogo().contains(i)) {
						repetidos++;
					}
				}
				if (repetidos != 9) {
					continue;
				}

				primeiroJogo = proximoJogo;
				if (filtro.buscaListaTodosSorteios().size() < 15)
					continue;

				Map<Integer, Integer> jogosEst = estatisticas
						.estatisticasJogosListaDinamica(filtro.buscaListaTodosSorteios(), 15);

				List<Integer> dezMais = new ArrayList<Integer>();
				int j = 0;
				for (Integer integer : jogosEst.keySet()) {

					if (j == 10) {
						break;
					}

					dezMais.add(integer);
					j++;

				}

				int interseciton = 0;

				for (Integer i : proximoJogo.getJogo()) {
					if (dezMais.contains(i)) {
						interseciton++;
					}
				}

				if (mapaNumeros.containsKey(interseciton)) {
					mapaNumeros.put(interseciton, mapaNumeros.get(interseciton).intValue() + 1);
				} else {
					mapaNumeros.put(interseciton, 1);
				}
				// if (interseciton == 9){
				// listaJogosFiltradosNove.add(proximoJogo);
				// }
				if (lista.contains(interseciton)) {
					contJogos++;
				}
				interseciton = 0;

			}

		}

		// List<Jogo> listaJogosFiltradosPares = new ArrayList<Jogo>();
		// List<Integer> lista = Arrays.asList(3,4,5,6, 7);
		// int contJogos = 0;
		// for (Jogo jogo : listaJogosFiltradosNove) {
		// int cont = filtro.retornaIntersecao(jogo,
		// estatisticas.buscarNumerosMultiplosDeTres());
		// if (lista.contains(cont)) {
		// contJogos++;
		//
		// }
		// }
		//
		System.out.println(contJogos + " " + mapaNumeros);

	}

	public static void teste1() throws FileNotFoundException, IOException, URISyntaxException {
		/// pega o último jogo, divide em três grupos de cinco [na sequência
		/// mesmo) e verifica quanto de cada grupo saem no próximo jogo

		Estatisticas estatisticas = new Estatisticas();

		List<Jogo> listaTodosJogos = estatisticas.lerTodosOsJogos();

		Jogo primeiroJogo = null;

		for (Jogo proximoJogo : listaTodosJogos) {
			if (primeiroJogo == null) {
				primeiroJogo = proximoJogo;
			} else {

				List<Integer> primeiroQuinto = new ArrayList<Integer>();
				List<Integer> segundoQuinto = new ArrayList<Integer>();
				List<Integer> terceiroQuinto = new ArrayList<Integer>();

				for (int i = 0; i < primeiroJogo.getJogo().size(); i++) {
					if (i < 5) {
						primeiroQuinto.add(primeiroJogo.getJogo().get(i));
					} else if (i > 4 && i < 10) {
						segundoQuinto.add(primeiroJogo.getJogo().get(i));
					} else {
						terceiroQuinto.add(primeiroJogo.getJogo().get(i));
					}
				}

				Set<Integer> intersectionPrimeiroQuinto = new HashSet<Integer>(proximoJogo.getJogo());
				intersectionPrimeiroQuinto.retainAll(primeiroQuinto);

				Set<Integer> intersectionSegundoQuinto = new HashSet<Integer>(proximoJogo.getJogo());
				intersectionSegundoQuinto.retainAll(segundoQuinto);

				Set<Integer> intersectionTerceiroQuinto = new HashSet<Integer>(proximoJogo.getJogo());
				intersectionTerceiroQuinto.retainAll(terceiroQuinto);

				System.out.println(intersectionPrimeiroQuinto.size() + " ; " + intersectionSegundoQuinto.size() + " ; "
						+ intersectionTerceiroQuinto.size());

				primeiroJogo = proximoJogo;

			}

		}
	}

	public static void teste2() throws FileNotFoundException, IOException, URISyntaxException {
		Estatisticas estatisticas = new Estatisticas();

		List<Jogo> listaTodosJogos = estatisticas.lerTodosOsJogos();

		Jogo primeiroJogo = null;

		for (Jogo proximoJogo : listaTodosJogos) {
			if (primeiroJogo == null) {
				primeiroJogo = proximoJogo;
			} else {

				List<Integer> primeiroQuinto = new ArrayList<Integer>();
				List<Integer> segundoQuinto = new ArrayList<Integer>();

				Jogo numerosNaoSorteados = primeiroJogo.numerosNaoSorteados(primeiroJogo);

				for (int i = 0; i < numerosNaoSorteados.getJogo().size(); i++) {
					if (i < 5) {
						primeiroQuinto.add(numerosNaoSorteados.getJogo().get(i));
					} else {
						segundoQuinto.add(numerosNaoSorteados.getJogo().get(i));
					}
				}

				Set<Integer> intersectionPrimeiroQuinto = new HashSet<Integer>(proximoJogo.getJogo());
				intersectionPrimeiroQuinto.retainAll(primeiroQuinto);

				Set<Integer> intersectionSegundoQuinto = new HashSet<Integer>(proximoJogo.getJogo());
				intersectionSegundoQuinto.retainAll(segundoQuinto);

				System.out.println(intersectionPrimeiroQuinto.size() + " ; " + intersectionSegundoQuinto.size());

				primeiroJogo = proximoJogo;

			}

		}
	}

}
