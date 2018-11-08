package br.com.sorteio.lotofacil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import br.com.sorteio.combinacoes.Combinacao;
import br.com.sorteio.numeros.Jogo;
import br.com.sorteio.util.Estatisticas;

public class Fechamento extends Filtro {
	
	private Jogo ultimoJogo;
	private List<Jogo> listaJogos18;
	private List<Jogo> listaJogosTemporaria;

	public Fechamento(Jogo jogo) {
	//	System.err.println("*************Fechamento**********************");
		this.ultimoJogo = jogo;
		this.listaJogos18 = new ArrayList<Jogo>();
		this.listaJogosTemporaria = new ArrayList<Jogo>();
		combinacoes();
	}
	
	public Fechamento(){
	//	System.err.println("*************Fechamento**********************");
		this.listaJogos18 = new ArrayList<Jogo>();
		this.listaJogosTemporaria = new ArrayList<Jogo>();
	}
	
	public void fechamento18(Jogo jogo){
		
	}

	@Override
	public void combinacoes() {
		Combinacao combinacoes = new Combinacao(ultimoJogo.getJogo(), 15);
		setListaDeJogos(combinacoes.geraListaCombinacoes());

	}
	
	public List<Jogo> getListaJogos(){
		return getListaDeJogos();
	}

	@Override
	public void filtrar() throws FileNotFoundException, IOException {
		gerarLista18();
		
		for (Jogo jogo : listaJogos18) {
			Combinacao combinacoes = new Combinacao(jogo.getJogo(), 15);
			listaJogosTemporaria.addAll(combinacoes.geraListaCombinacoes());
		}
		setListaDeJogos(listaJogosTemporaria);
	}
	
	public void gerarLista18() throws FileNotFoundException, IOException{
		
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();
		
		int qTDJogosAnalisados = 8;

		Integer primeiroQuinto = 0;
		Integer segundoQuinto = 0;
		Integer terceiroQuinto = 0;
		Integer quartoQuinto = 0;
		Integer quintoQuinto = 0;

		TreeMap<Integer, Integer> mapaQuintos = new TreeMap<Integer, Integer>();

		for (int posicao = todosJogos.size() - 1; posicao > todosJogos.size() - (qTDJogosAnalisados + 1); posicao--) {
			for (Integer numero : todosJogos.get(posicao).getJogo()) {

				if (numero > 0 && numero < 6) {
					primeiroQuinto++;
				}

				if (numero > 5 && numero < 11) {
					segundoQuinto++;
				}

				if (numero > 10 && numero < 16) {
					terceiroQuinto++;
				}

				if (numero > 15 && numero < 21) {
					quartoQuinto++;
				}

				if (numero > 20 && numero < 26) {
					quintoQuinto++;
				}

			}
		}

		mapaQuintos.put(1, primeiroQuinto);
		mapaQuintos.put(2, segundoQuinto);
		mapaQuintos.put(3, terceiroQuinto);
		mapaQuintos.put(4, quartoQuinto);
		mapaQuintos.put(5, quintoQuinto);
		// Collections.sort(mapaQuintos);
		TreeMap<Integer, List<Integer>> mapaGruposQuintos = new TreeMap<Integer, List<Integer>>();
		mapaGruposQuintos.put(1, Arrays.asList(1, 2, 3, 4, 5));
		mapaGruposQuintos.put(2, Arrays.asList(6, 7, 8, 9, 10));
		mapaGruposQuintos.put(3, Arrays.asList(11, 12, 13, 14, 15));
		mapaGruposQuintos.put(4, Arrays.asList(16, 17, 18, 19, 20));
		mapaGruposQuintos.put(5, Arrays.asList(21, 22, 23, 24, 25));

		List<Jogo> listaCombinacoesPrimeiroQuintoA = new ArrayList<Jogo>();
		List<Jogo> listaCombinacoesPrimeiroQuintoB = new ArrayList<Jogo>();

		List<Jogo> listaCombinacoesSegundoQuintoA = new ArrayList<Jogo>();
		List<Jogo> listaCombinacoesSegundoQuintoB = new ArrayList<Jogo>();

		List<Jogo> listaCombinacoesTerceiroQuintoA = new ArrayList<Jogo>();
		List<Jogo> listaCombinacoesTerceiroQuintoB = new ArrayList<Jogo>();

		List<Jogo> listaCombinacoesQuartoQuintoA = new ArrayList<Jogo>();
		List<Jogo> listaCombinacoesQuartoQuintoB = new ArrayList<Jogo>();

		List<Jogo> listaCombinacoesQuintoQuintoA = new ArrayList<Jogo>();
		List<Jogo> listaCombinacoesQuintoQuintoB = new ArrayList<Jogo>();

		SortedMap<Integer, Integer> listaOrdenada = new TreeMap<Integer, Integer>();

		for (Integer chave : mapaQuintos.keySet()) {
			listaOrdenada.put(mapaQuintos.get(chave), chave);
		}

		int ordemQuintos = 1;

		for (Integer chave : mapaQuintos.keySet()) {
			float quantidadeEmCadaQuinto = mapaQuintos.get(chave) / qTDJogosAnalisados;
		//	System.out.println(chave + " " + mapaQuintos.get(chave) + " " + quantidadeEmCadaQuinto);

			int intQuantidadeEmCadaQuinto = (int) ((int) quantidadeEmCadaQuinto == 5 ? 4 : quantidadeEmCadaQuinto);

			if (chave == 1) {
				Combinacao combinacoesPrimeiroQuintoA = new Combinacao(mapaGruposQuintos.get(chave),
						(int) intQuantidadeEmCadaQuinto);
				listaCombinacoesPrimeiroQuintoA.addAll(combinacoesPrimeiroQuintoA.geraListaCombinacoes());
				Combinacao combinacoesPrimeiroQuintoB = new Combinacao(mapaGruposQuintos.get(chave),
						(int) intQuantidadeEmCadaQuinto + 1);
				listaCombinacoesPrimeiroQuintoB.addAll(combinacoesPrimeiroQuintoB.geraListaCombinacoes());
			}
			if (chave == 2) {
				Combinacao combinacoesSegundoQuintoA = new Combinacao(mapaGruposQuintos.get(chave),
						(int) intQuantidadeEmCadaQuinto);
				listaCombinacoesSegundoQuintoA.addAll(combinacoesSegundoQuintoA.geraListaCombinacoes());
				Combinacao combinacoesSegundoQuintoB = new Combinacao(mapaGruposQuintos.get(chave),
						(int) intQuantidadeEmCadaQuinto + 1);
				listaCombinacoesSegundoQuintoB.addAll(combinacoesSegundoQuintoB.geraListaCombinacoes());
			}
			if (chave == 3) {
				Combinacao combinacoesTerceiroQuintoA = new Combinacao(mapaGruposQuintos.get(chave),
						(int) intQuantidadeEmCadaQuinto);
				listaCombinacoesTerceiroQuintoA.addAll(combinacoesTerceiroQuintoA.geraListaCombinacoes());
				Combinacao combinacoesTerceiroQuintoB = new Combinacao(mapaGruposQuintos.get(chave),
						(int) intQuantidadeEmCadaQuinto + 1);
				listaCombinacoesTerceiroQuintoB.addAll(combinacoesTerceiroQuintoB.geraListaCombinacoes());
			}
			if (chave == 4) {
				Combinacao combinacoesQuartoQuintoA = new Combinacao(mapaGruposQuintos.get(chave),
						(int) intQuantidadeEmCadaQuinto);
				listaCombinacoesQuartoQuintoA.addAll(combinacoesQuartoQuintoA.geraListaCombinacoes());
				Combinacao combinacoesQuartoQuintoB = new Combinacao(mapaGruposQuintos.get(chave),
						(int) intQuantidadeEmCadaQuinto + 1);
				listaCombinacoesQuartoQuintoB.addAll(combinacoesQuartoQuintoB.geraListaCombinacoes());
			}
			if (chave == 5) {
				Combinacao combinacoesQuintoQuintoA = new Combinacao(mapaGruposQuintos.get(chave),
						(int) intQuantidadeEmCadaQuinto);
				listaCombinacoesQuintoQuintoA.addAll(combinacoesQuintoQuintoA.geraListaCombinacoes());
				Combinacao combinacoesQuintoQuintoB = new Combinacao(mapaGruposQuintos.get(chave),
						(int) intQuantidadeEmCadaQuinto + 1);
				listaCombinacoesQuintoQuintoB.addAll(combinacoesQuintoQuintoB.geraListaCombinacoes());
			}
		}

		List<Jogo> listaAux = new ArrayList<Jogo>();
		List<Jogo> combinacoes = new ArrayList<Jogo>();
		for (Jogo n1 : listaCombinacoesPrimeiroQuintoA) {

			for (Jogo n2 : listaCombinacoesSegundoQuintoA) {
				combinacoes.add(new Jogo(union(n1.getJogo(), n2.getJogo())));
			}

			for (Jogo n2 : listaCombinacoesSegundoQuintoB) {
				combinacoes.add(new Jogo(union(n1.getJogo(), n2.getJogo())));
			}
		}
		listaAux.addAll(combinacoes);

		combinacoes.clear();

		for (Jogo n1 : listaCombinacoesPrimeiroQuintoB) {
			for (Jogo n2 : listaCombinacoesSegundoQuintoA) {
				combinacoes.add(new Jogo(union(n1.getJogo(), n2.getJogo())));
			}

			for (Jogo n2 : listaCombinacoesSegundoQuintoB) {
				combinacoes.add(new Jogo(union(n1.getJogo(), n2.getJogo())));
			}
		}
		listaAux.addAll(combinacoes);

		combinacoes.clear();

		for (Jogo n1 : listaAux) {
			for (Jogo n2 : listaCombinacoesTerceiroQuintoA) {
				combinacoes.add(new Jogo(union(n1.getJogo(), n2.getJogo())));
			}

			for (Jogo n2 : listaCombinacoesTerceiroQuintoB) {
				combinacoes.add(new Jogo(union(n1.getJogo(), n2.getJogo())));
			}
		}

		listaAux.addAll(combinacoes);

		combinacoes.clear();

		for (Jogo n1 : listaAux) {
			for (Jogo n2 : listaCombinacoesQuartoQuintoA) {
				combinacoes.add(new Jogo(union(n1.getJogo(), n2.getJogo())));
			}

			for (Jogo n2 : listaCombinacoesQuartoQuintoB) {
				combinacoes.add(new Jogo(union(n1.getJogo(), n2.getJogo())));
			}
		}

		listaAux.addAll(combinacoes);

		combinacoes.clear();

		for (Jogo n1 : listaAux) {
			for (Jogo n2 : listaCombinacoesQuintoQuintoA) {
				combinacoes.add(new Jogo(union(n1.getJogo(), n2.getJogo())));
			}

			for (Jogo n2 : listaCombinacoesQuintoQuintoB) {
				combinacoes.add(new Jogo(union(n1.getJogo(), n2.getJogo())));
			}
		}

		listaAux.addAll(combinacoes);
		
		List<Jogo> listaFinal = new ArrayList<Jogo>();
		Jogo listaPares = new Jogo(Arrays.asList(2,4,6,8,10,12,14,16,18,20,22,24));
		Jogo strUltimoSorteio = todosJogos.get(todosJogos.size() - 1);
		
		for (Jogo jogo : listaAux) {
			
			Set<Integer> intersectionRepetidos = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersectionRepetidos.retainAll(jogo.getJogo());
			int qtdRepetidos = intersectionRepetidos.size();
			
			Set<Integer> intersectionPares = new HashSet<Integer>(listaPares.getJogo());
			intersectionPares.retainAll(jogo.getJogo());
			int qtdPares = intersectionPares.size();
			
			boolean testeRepetidos = qtdRepetidos == 11; 
			boolean testePares = qtdPares  == 9;
			
			if (jogo.getJogo().size() == 18 && testeRepetidos && testePares){
				listaFinal.add(jogo);
			}
		}
		
		this.listaJogos18.addAll(listaFinal);
		
		
//		for (Jogo jogo : listaFinal) {
//			System.out.println(jogo.getJogo());
//		}
		
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
}
