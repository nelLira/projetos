package br.com.loteria.jogo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Jogo {
	
	private Integer qtdRepetidos;

	private List<Integer> jogo;
	private int quantidadeFiltros;
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
		quantidadeFiltros = 0;
		
	}

	public int buscaQuantidadeFiltros(){
		return quantidadeFiltros;
	}
	
	public void somaQuantidadeFiltros(){
		this.quantidadeFiltros++;
	}
	
	public Jogo() {
		jogo = new ArrayList<Integer>();
	}

	public List<Integer> getJogo() {
		return jogo;
	}

	public void setJogo(List<Integer> numeros) {
		
		if (jogo != null ){
			jogo.clear();
		}
		this.jogo = numeros;
	}

	
	public Jogo numerosNaoSorteados(Jogo ultimosSorteodos) {
		Jogo strTodosNumeros = new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
				19, 20, 21, 22, 23, 24, 25));
		Set<Integer> difference = new HashSet<Integer>(strTodosNumeros.getJogo());
		difference.removeAll(ultimosSorteodos.getJogo());
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

	public Integer getqtdRepetidos() {
		return qtdRepetidos;
	}

	public void setqtdRepetidos(Integer qtdRepetidos) {
		this.qtdRepetidos = qtdRepetidos;
	}
}
