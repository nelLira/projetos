package br.com.loteria.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import br.com.loteria.jogo.Jogo;
import br.com.loteria.lotofacil.Estatisticas;
import br.com.loteria.lotofacil.Filtro;

public class Testes {

	public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException {
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();
		List<Jogo> listaTodosJogos = estatisticas.lerTodosOsJogos();
		Jogo primeiroJogo = null;
		int contJogos=0;
		int contNumero=0;
		int qtdMax = 0;
		//1=6; 2=6; 3=6; 4=6
		for (Jogo jogo : listaTodosJogos) {
			if (!jogo.getJogo().contains(4)) {
				contNumero++;
			} else {
				if (contNumero > 2) {
				System.out.println(contNumero);
				}
//				if (qtdMax < contNumero) {
//					qtdMax = contNumero;
//				}
				contNumero=0;
			}
		}
		

	}
	
	public void teste5() throws FileNotFoundException, IOException {
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();
		List<Jogo> listaTodosJogos = estatisticas.lerTodosOsJogos();
		Jogo primeiroJogo = null;
		int contJogos=0;
		int contNumero=0;
		int qtdMax = 0;
		//1=11; 2=11; 3=11; 4=10; 5=11; 6=10; 7=10; 8=9;
		for (Jogo jogo : listaTodosJogos) {
			if (jogo.getJogo().contains(8)) {
				contNumero++;
			} else {
				if (contNumero > 5) {
				System.out.println(contNumero);
				}
//				if (qtdMax < contNumero) {
//					qtdMax = contNumero;
//				}
				contNumero=0;
			}
		}
		
	}
	
	public void teste4() throws FileNotFoundException, IOException {
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();
		List<Jogo> listaTodosJogos = estatisticas.lerTodosOsJogos();
		Jogo primeiroJogo = null;
		int contJogos=0;
		for (Jogo jogo : listaTodosJogos) {
			int cont=0;
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
