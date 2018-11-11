package br.com.sorteio.lotofacil;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.com.sorteio.combinacoes.Combinacao;
import br.com.sorteio.numeros.Jogo;

public class FiltroNove extends Filtro {
	
	private Jogo ultimoJogo;

	public FiltroNove(Jogo jogo) {
		System.err.println("*************Filtrando a lista de números [9]**********************");
		this.ultimoJogo = jogo;
		combinacoes();
	}

	@Override
	public void combinacoes() {
		Combinacao combinacoes = new Combinacao(ultimoJogo.getJogo(), 9);
		setListaDeJogos(combinacoes.geraListaCombinacoes());

	}

	@Override
	public void filtrar() throws FileNotFoundException, IOException {
		/*
		 * COMBINAÇÕES combinações de 9 números a partir da lista do último
		 * resultado
		 */
		/* 34% dos jogos repetem 9 números do jogo anterior */
		/*
		 * PARES: dos jogos tem nove números repetidos, ente os nove número 77%
		 * das vezes 3,4,5 são pares
		 */
		//pares(0,10); //{1=3, 2=30, 3=99, 4=157, 5=153, 6=71, 7=16, 8=1}
		//primo(0,10); //{2=2, 3=31, 4=92, 5=165, 6=139, 7=79, 8=21, 9=1}
		
	}

}
