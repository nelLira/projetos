package br.com.loteria.simulacao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.loteria.combinacoes.Combinacoes;
import br.com.loteria.jogo.Jogo;
import br.com.loteria.lotofacil.Estatisticas;
import br.com.loteria.lotofacil.Filtro;


public class Simulacao {
	
	private List<Jogo> listaTodosJogos;
	
	public Simulacao(){
		Estatisticas est = new Estatisticas();
		try {
			this.listaTodosJogos = est.lerTodosOsJogos();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void simularFiltrosTodosSorteios() throws FileNotFoundException, IOException {
		
		int tamanhoListaTodosJogos = listaTodosJogos.size();
		
		Filtro filtro = new Filtro();
		
		Jogo primeiroJogo = null;
		int contAcertos = 0;
		List<Long> numeroSorteio = new ArrayList<Long>();
		List<Long> posicoesSorteio = new ArrayList<Long>();
		List<Jogo> jogosAcertados = new ArrayList<Jogo>();
		long qtdJogos = 0;
		long posicaoJogo = 0;

		Combinacoes combinacoes = new Combinacoes();
		filtro.setListaJogosCombinadosCompleto(combinacoes.todosCombinacoesLotoFacil());
		
		for (Jogo proximoJogo : listaTodosJogos) {
			if (primeiroJogo == null) {
				primeiroJogo = proximoJogo;
			} else {
				filtro.iniciaListas();
				filtro.setaJogoListaTodosSorteios(primeiroJogo);
				qtdJogos++;
				filtro.setJogoAtual((int)qtdJogos);
				System.err.println("Jogo: " + qtdJogos + " de " + tamanhoListaTodosJogos);
				List<Jogo> listOpcoesJogos = filtro.bucaListaJogosFiltrados();

				posicaoJogo = 0;
				for (Jogo opcoesJogos : listOpcoesJogos) {
					if (opcoesJogos.getJogo().equals(proximoJogo.getJogo())) {
						contAcertos++;
						posicoesSorteio.add(posicaoJogo);
						numeroSorteio.add(qtdJogos + 1);
						jogosAcertados.add(opcoesJogos);
					}
					posicaoJogo++;
				}
				primeiroJogo = proximoJogo;
				filtro.limpaListas();
			}

		}
		filtro = null;
		int sorteado = 0;
		
		for (Jogo jogo : jogosAcertados) {

			Jogo strUltimoSorteio = listaTodosJogos.get(numeroSorteio.get(sorteado).intValue() - 1);
			Jogo strSorteioAnterior = listaTodosJogos.get(numeroSorteio.get(sorteado).intValue() - 2);

			System.out.println("Sorteio: " + numeroSorteio.get(sorteado) + "; Posicao -> "
					+ posicoesSorteio.get(sorteado) + "; Jogo: " + jogo.getJogo());
			sorteado++;
		}
		System.err.println("Quantidade: " + contAcertos);
	}
}
