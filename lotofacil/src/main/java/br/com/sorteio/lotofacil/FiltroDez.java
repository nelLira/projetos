package br.com.sorteio.lotofacil;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.com.sorteio.combinacoes.Combinacao;
import br.com.sorteio.numeros.Jogo;

public class FiltroDez extends Filtro {
	
	private Jogo ultimoJogo;

	public FiltroDez(Jogo jogo) {
		System.err.println("*************Filtrando a lista de números [10]**********************");
		this.ultimoJogo = jogo;
		combinacoes();
	}

	@Override
	public void combinacoes() {
		Combinacao combinacoes = new Combinacao(ultimoJogo.getJogo(), 10);
		setListaDeJogos(combinacoes.geraListaCombinacoes());

	}

	@Override
	public void filtrar() throws FileNotFoundException, IOException {
		


	}

}
