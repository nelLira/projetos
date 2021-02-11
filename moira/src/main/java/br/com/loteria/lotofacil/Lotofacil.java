package br.com.loteria.lotofacil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.loteria.jogo.Gerador;
import br.com.loteria.jogo.Jogo;
import br.com.loteria.simulacao.Simulacao;

public class Lotofacil {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		 //simulacao(); //simulação com 9 -> 459 de 660 [27/06/2020] [672]
		 //[458 com pares 4000 / 458 com quadrado 200 / ]
		//gerarJogos(200, true);
		//estisticasUltimoSorteio();
		//estatisticasUltimosSorteios(50,true);
		//ranking();
	
		List<Jogo> jogos = new ArrayList<Jogo>();
		
		jogos.add(new Jogo(Arrays.asList(1, 2, 4, 5, 6, 12, 13, 14, 16, 17, 19, 21, 23, 24, 25)));
		jogos.add(new Jogo(Arrays.asList(4, 6, 8, 11, 12, 13, 15, 16, 17, 18, 19, 21, 22, 23, 25)));
		jogos.add(new Jogo(Arrays.asList(1, 2, 6, 8, 9, 10, 11, 12, 13, 17, 19, 20, 21, 23, 25)));
		jogos.add(new Jogo(Arrays.asList(2, 3, 7, 8, 9, 10, 11, 13, 14, 15, 17, 21, 22, 24, 25)));
		jogos.add(new Jogo(Arrays.asList(1, 2, 3, 4, 7, 9, 10, 11, 12, 14, 15, 16, 19, 21, 25)));
		jogos.add(new Jogo(Arrays.asList(1, 3, 4, 7, 9, 12, 13, 16, 17, 19, 20, 22, 23, 24, 25)));
		jogos.add(new Jogo(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 13, 17, 18, 22, 23, 24, 25)));
		jogos.add(new Jogo(Arrays.asList(2, 3, 4, 5, 6, 9, 11, 12, 13, 14, 17, 20, 23, 24, 25)));


	
		consultaSorteio(jogos);
	
	}

	private static void consultaSorteio(List<Jogo> jogos) throws FileNotFoundException, IOException {

		Estatisticas estatisticas = new Estatisticas();
		List<Jogo> listaTodosJogos = estatisticas.lerTodosOsJogos();

		Jogo strUltimoSorteio = listaTodosJogos.get(listaTodosJogos.size() - 1);

		System.err.println("último sorteio:" + strUltimoSorteio.getJogo() + "\n");
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

	private static void estatisticasUltimosSorteios(int i, Boolean geraArq) throws FileNotFoundException, IOException {
		Estatisticas estatisticas = new Estatisticas();

		estatisticas.estatisticasUltimosSorteioCSV(i, geraArq);

	}

	private static void estisticasUltimoSorteio() throws FileNotFoundException, IOException {
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.estatSorteio();

	}

	private static void gerarJogos(int i, Boolean geraArq) throws FileNotFoundException, IOException {
		Gerador gerador = new Gerador();

		gerador.gerarJogosCSV(i, geraArq);

	}

	public static void simulacao() throws FileNotFoundException, IOException {
		Simulacao simulacao = new Simulacao();
		simulacao.simularFiltrosTodosSorteios();
	}
	
	public static void ranking() throws FileNotFoundException, IOException {
		
		Estatisticas est = new Estatisticas();

		List<Jogo> todosJogos = est.lerTodosOsJogos();
		
		Jogo jogoAtual = todosJogos.get(todosJogos.size() - 1 );
		
		System.out.println("úlimo jogo: " + jogoAtual.getJogo());
		
		Map<Integer, Integer>  mapaRanking = est.estatisticasJogos(todosJogos.size());
		 
		List<Integer> maisSaem = new ArrayList<>();
		 
		 for (Integer chave : mapaRanking.keySet()) {
			
			 Integer valor = mapaRanking.get(chave);
			 
			 if (valor > 11) {
				 
				 maisSaem.add(chave); 
			 }
			 
		}
		

		 
		todosJogos.remove(todosJogos.size() - 1);
		
		System.out.println("Mais saem: " + maisSaem);
		
		mapaRanking = est.estatisticasJogos(todosJogos.size());
		
		List<Integer> maisSaemJogoAnterior = new ArrayList<>();
		
		List<Integer> menosSaemJogoAnterior = new ArrayList<Integer>();
		
		for (Integer chave : mapaRanking.keySet()) {
			
			 Integer valor = mapaRanking.get(chave);
			 
			 if (valor > 11) {
				 
				 maisSaemJogoAnterior.add(chave); 
		
			 } else {
			
				 menosSaemJogoAnterior.add(chave);
			 
			 }
			 
		}
		
		
		List<Integer> maisSaemNaoSairam = new ArrayList<>();
		
		for (Integer n : maisSaemJogoAnterior) {
			if (!jogoAtual.getJogo().contains(n)) {
				maisSaemNaoSairam.add(n);
			}
		}
		
		List<Integer> menosSaemNaoSairam = new ArrayList<>();
				
		for (Integer n : menosSaemJogoAnterior) {
			
			if (!jogoAtual.getJogo().contains(n)) {
				menosSaemNaoSairam.add(n);
			}
		}
		
		System.out.println("Mais saem não sairam: " + maisSaemNaoSairam);
		
		System.out.println("Menos saem não sairam: " + menosSaemNaoSairam);
		
	}

}
