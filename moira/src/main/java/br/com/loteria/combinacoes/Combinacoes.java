package br.com.loteria.combinacoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.loteria.jogo.Jogo;

public class Combinacoes {
	private int r;
	private List<Integer> entrada;
	private int MAX;
	private int N;

	/**
	 * se r e' zero entao iremos fazer todas as combinacoes (com qualquer
	 * quantidade de elementos).
	 */
	public Combinacoes(List<Integer> entrada, int r) {
		combinar(entrada,r);
	}
	
	public void combinar(List<Integer> entrada, int r){
		this.r = r;
		this.entrada = entrada;
		this.MAX = ~(1 << entrada.size());
		this.N = 1;
	}

	public Combinacoes() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Jogo> todosCombinacoesLotoFacil(){
		System.out.println("Combinando todos os números...");
		List<Integer> todosNumeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,20, 21, 22, 23, 24, 25);
		combinar(todosNumeros,15);
		return geraListaCombinacoes();
	}

	public List<Jogo> todosCombinacoesLotoFacil18(){
		System.out.println("Combinando todos os números...");
		List<Integer> todosNumeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,20, 21, 22, 23, 24, 25);
		combinar(todosNumeros,18);
		return geraListaCombinacoes();
	}
	/**
	 * Retorna true quando ha pelo menos uma combinacao disponivel.
	 */
	public boolean hasNext() {
		if (r != 0) {
			while (((this.N & this.MAX) != 0) && (countbits() != r))
				N += 1;
		}

		return (this.N & this.MAX) != 0;
	}

	/**
	 * Retorna a quantidade de bits ativos (= 1) de N.
	 */
	private int countbits() {
		int i;
		int c;

		i = 1;
		c = 0;
		while ((this.MAX & i) != 0) {
			if ((this.N & i) != 0) {
				c++;
			}
			i = i << 1;
		}

		return c;
	}

	/**
	 * Util para obter o tamanho da saida. Esse tamanho e' o numero de posicoes
	 * do vetor retornado por next.
	 */
	public int getSaidaLength() {
		if (r != 0) {
			return r;
		}

		return this.countbits();
	}

	/**
	 * Retorna uma combinacao.
	 *
	 * ATENCAO: Sempre use next() quando se tem certeza que ha uma combinacao
	 * disponivel. Ou seja, sempre use next() quando hasNext() retornar true.
	 */
	public List<Integer> next() {
		int saida_index, entrada_index, i;

		List<Integer> saida = new ArrayList();

		entrada_index = 0;
		saida_index = 0;
		i = 1;

		while ((this.MAX & i) != 0) {
			if ((this.N & i) != 0) {
				saida.add(entrada.get(entrada_index));
				saida_index += 1;
			}
			entrada_index += 1;
			i = i << 1;
		}

		N += 1;

		return saida;
	}

	public List<Jogo> geraListaCombinacoes() {
		// System.out.println("Gerando a lista de combinaÃ§Ãµes...");
		List<Integer> saida;
		List<Jogo> jogos = new ArrayList<Jogo>();

		while (hasNext()) {
			saida = next();
			jogos.add(new Jogo(saida));
		}
		return jogos;
	}

	public void listaNumeros(List<Jogo> jogos) {
		int y;
		y = 1;
		for (Jogo n : jogos) {
			System.out.print("Linha " + y + ": ");
			for (int i = 0; i < n.getJogo().size(); i++) {
				System.out.print(n.getJogo().get(i) + ", ");
			}
			System.out.println("");
			y++;
		}
	}

	public List<Integer> union(List<Integer> lista1, List<Integer> lista2) {
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

	public List<Jogo> combinacoes(List<Jogo> primeiraListaDeNumeros, List<Jogo> segundaListaDeNumeros) {
		List<Jogo> combinacoes = new ArrayList<Jogo>();
		Jogo jogo = null;
		
		for (Jogo n1 : primeiraListaDeNumeros) {
			for (Jogo n2 : segundaListaDeNumeros) {
				List<Integer> listaInteger = new ArrayList<Integer>(); //union(n1.getJogo(), n2.getJogo());
				Stream<Integer> combinedStream = Stream.of(n1.getJogo(), n2.getJogo()).flatMap(Collection::stream);
				listaInteger =  combinedStream.collect(Collectors.toList());
				combinacoes.add(new Jogo(listaInteger));
				
			}
		}
		
			return combinacoes;
	}

}
