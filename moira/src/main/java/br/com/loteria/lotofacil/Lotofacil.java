package br.com.loteria.lotofacil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.loteria.jogo.Gerador;
import br.com.loteria.jogo.Jogo;
import br.com.loteria.simulacao.Simulacao;

public class Lotofacil {

	public static void main(String[] args) throws FileNotFoundException, IOException { 

	//	simulacao();
		//gerarJogos(200,true);
		// estisticasUltimoSorteio();
	//	estatisticasUltimosSorteios(50, true);

		List<Jogo> jogos = new ArrayList<Jogo>();
		jogos.add(new Jogo(Arrays.asList(1, 5, 8, 10, 11, 12, 13, 15, 17, 18, 19, 21, 22, 24, 25)));
		jogos.add(new Jogo(Arrays.asList(2, 5, 9, 10, 11, 12, 14, 15, 16, 17, 19, 21, 22, 23, 24)));
		jogos.add(new Jogo(Arrays.asList(1, 3, 6, 8, 10, 11, 13, 14, 17, 18, 20, 21, 22, 23, 25)));
		jogos.add(new Jogo(Arrays.asList(1, 2, 4, 5, 8, 10, 12, 13, 15, 16, 17, 21, 23, 24, 25)));
		jogos.add(new Jogo(Arrays.asList(1, 5, 7, 8, 10, 12, 13, 15, 17, 20, 21, 22, 23, 24, 25)));
		jogos.add(new Jogo(Arrays.asList(2, 4, 6, 7, 9, 10, 11, 15, 16, 18, 19, 20, 21, 23, 25)));
		jogos.add(new Jogo(Arrays.asList(2, 3, 5, 7, 10, 11, 12, 14, 15, 16, 17, 19, 20, 22, 24)));
		consultaSorteio(jogos);
		


		


	}

	private static void consultaSorteio(List<Jogo> jogos) throws FileNotFoundException, IOException {

		Estatisticas estatisticas = new Estatisticas();
		List<Jogo> listaTodosJogos = estatisticas.lerTodosOsJogos();

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

}
