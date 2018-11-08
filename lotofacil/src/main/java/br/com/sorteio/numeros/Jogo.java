package br.com.sorteio.numeros;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Jogo {

	private List<Integer> jogo;
	private int elimina;
	private Integer codHash;

	public void setaCodHash(Integer codHash){
		this.codHash = codHash;
	}
	
	public Integer buscaCodHash(){
		return this.codHash;
	}
	public Jogo(List<Integer> numeros) {
		setJogo(numeros);
		setaCodHash(numeros.hashCode());
		elimina = 0;
		
	}

	public int buscaElimina(){
		return elimina;
	}
	
	public void somaElimina(){
		this.elimina++;
	}
	
	public Jogo() {
		jogo = new ArrayList<Integer>();
	}

	public List<Integer> getJogo() {
		return jogo;
	}

	public void setJogo(List<Integer> numeros) {
		this.jogo = numeros;
	}

	
	public Jogo numerosNaoSorteados(Jogo strUltimoSorteio) {
		Jogo strTodosNumeros = new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
				19, 20, 21, 22, 23, 24, 25));
		Set<Integer> difference = new HashSet<Integer>(strTodosNumeros.getJogo());
		difference.removeAll(strUltimoSorteio.getJogo());
		Iterator<Integer> iterator = difference.iterator();
		List<Integer> lista = new ArrayList<Integer>();
		if (iterator.hasNext()) {
			while (iterator.hasNext()) {
				lista.add(iterator.next());
			}
		}
		Jogo strNumerosNaoSorteados = new Jogo(lista);

		return strNumerosNaoSorteados;
	}

	public Jogo numerosNaoSorteados() {
		Jogo strTodosNumeros = new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
				19, 20, 21, 22, 23, 24, 25));
		Set<Integer> difference = new HashSet<Integer>(strTodosNumeros.getJogo());
		difference.removeAll(getJogo());
		Iterator<Integer> iterator = difference.iterator();
		List<Integer> lista = new ArrayList<Integer>();
		if (iterator.hasNext()) {
			while (iterator.hasNext()) {
				lista.add(iterator.next());
			}
		}
		this.setJogo(lista);

		return this;
	}
}
