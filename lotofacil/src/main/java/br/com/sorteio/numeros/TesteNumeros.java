package br.com.sorteio.numeros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.sorteio.util.Estatisticas;

public class TesteNumeros {

	public static void main(String[] args) throws IOException {
		
		Estatisticas est = new Estatisticas();
		List<Jogo> num = est.lerTodosOsJogos();
		List<Jogo> numRep = est.Repetidos(num, 9);
		List<Jogo> listNumNaoSorteados = new ArrayList<Jogo>();
		
		for (Jogo jogo : numRep) {
			listNumNaoSorteados.add(new Jogo(jogo.numerosNaoSorteados().getJogo()));
		}
		
		 
			est.numerosPares(listNumNaoSorteados);
		
		
			
		
		
		
		

	}


}
