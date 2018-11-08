package br.com.sorteio.lotofacil;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.com.sorteio.combinacoes.Combinacao;
import br.com.sorteio.numeros.Jogo;

public class FiltroSeis extends Filtro{
	
	private Jogo numerosNaoSorteados;

	public FiltroSeis(Jogo jogo) {
		System.err.println("**************Filtrando a lista de números [6]**********************");
		this.numerosNaoSorteados = jogo;
		combinacoes();
	}

	@Override
	public void combinacoes() {
		Combinacao combinacoes = new Combinacao(numerosNaoSorteados.getJogo(), 6);
		setListaDeJogos(combinacoes.geraListaCombinacoes());

	}

	@Override
	public void filtrar() throws FileNotFoundException, IOException {
		/*
		 * COMBINAÇÕES: combinações de 6 números dos números não sorteados no último
		 * resultado
		 */
		/*
		 * PARES: dos números que não sairam para os nove repetidos, entre os seis números 77%
		 * das vezes 3,4,5,6 são pares
		 */
		//pares(0,10); //{1=2, 2=16, 3=60, 4=143, 5=160, 6=99, 7=44, 8=5, 9=1}
		//primo(0,8); //{0=1, 1=21, 2=79, 3=139, 4=165, 5=92, 6=31, 7=2}

	}
	
	

}
