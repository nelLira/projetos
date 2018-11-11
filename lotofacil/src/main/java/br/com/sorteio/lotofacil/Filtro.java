package br.com.sorteio.lotofacil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.com.sorteio.numeros.Jogo;
import br.com.sorteio.util.Estatisticas;

public abstract class Filtro {

	private List<Jogo> listaDeJogos;
	private HashMap<Integer, Integer> mapaHashJogoEliminados;
	private HashMap<Integer, Jogo> mapaJogoEliminados;

//	public void pares(int maiorQue, int menorQue) throws FileNotFoundException, IOException {
//		System.out.println("Filtrando os números pares...");
//		int cont = 0;
//
//		List<Jogo> listaAuxiliar = new ArrayList<Jogo>();
//		listaAuxiliar.addAll(listaDeJogos);
//		List<Jogo> numResult = new ArrayList<Jogo>();
//
//		for (Jogo n : listaAuxiliar) {
//			for (Integer numero : n.getJogo()) {
//				if (numero % 2 == 0) {
//					cont++;
//				}
//			}
//			/*
//			 * if (cont < menorQue || cont > maiorQue ) {
//			 * listaDeJogos.remove(n); }
//			 */
//
//			if (cont > maiorQue && cont < menorQue) {
//				numResult.add(n);
//			}
//			cont = 0;
//		}
//		if (listaDeJogos.size() == 0) {
//			System.out.println("filtraNumerosParesDentroDaEstatistica [sem lista de números]");
//		}
//		;
//		getListaDeJogos().clear();
//		getListaDeJogos().addAll(numResult);
//		System.out.println("Tamanho da lista depois de filtrar os números pares... " + listaDeJogos.size());
//	}

	

	
	public HashMap<Integer, Integer> buscaMapaJogosEliminados(){
		return this.mapaHashJogoEliminados;
	}
	
	public void iniciaMapaJogosEliminados (){
		mapaHashJogoEliminados = new HashMap<Integer, Integer>();
		mapaJogoEliminados  = new HashMap<Integer, Jogo>();
	}
	
	public void setaJogosEliminados(Jogo jogo) {
		
		
		if (mapaHashJogoEliminados.containsKey(jogo.buscaCodHash())) {
			Integer valor = mapaHashJogoEliminados.get(jogo.buscaCodHash()).intValue() + 1;
			mapaHashJogoEliminados.put(jogo.buscaCodHash(), valor);
		
		} else {
			mapaHashJogoEliminados.put(jogo.buscaCodHash(), 1);
			mapaJogoEliminados.put(jogo.buscaCodHash(),jogo);
		}
		
		
	}
	
	public HashMap<Integer, Jogo> buscaJogosEliminados(){
		return mapaJogoEliminados;
	}
	
	public void setListaDeJogos(List<Jogo> listaDeJogos) {
		if (this.listaDeJogos == null)
			this.listaDeJogos = new ArrayList<Jogo>();
		this.listaDeJogos = listaDeJogos;
	}

	public abstract void combinacoes();

	public abstract void filtrar() throws FileNotFoundException, IOException;

	public List<Jogo> getListaDeJogos() {
		return listaDeJogos;
	}

	public void iniciaLista() {
		this.listaDeJogos = new ArrayList<Jogo>();
	}

	public Jogo retiraNumeros(Jogo jogo, List<Jogo> listaJogosPorDemanda) {

		Map<Integer, Integer> trmap = dezUltimosSorteios(listaJogosPorDemanda);

		List<Integer> listaMenosSorteados = MaisEMenosSorteados(trmap, false);

		jogo.getJogo().removeAll(listaMenosSorteados);

		return jogo;

	}

	protected Map<Integer, Integer> dezUltimosSorteios(List<Jogo> listaJogosPorDemanda) {
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = new ArrayList<Jogo>();

		TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();

		todosJogos.addAll(listaJogosPorDemanda);

		for (int i = todosJogos.size() - 10; i < todosJogos.size(); i++) {

			for (Integer numero : todosJogos.get(i).getJogo()) {

				if (trmap.containsKey(numero)) {
					int valor = trmap.get(numero).intValue() + 1;
					trmap.put(numero, valor);

				} else {
					trmap.put(numero, 1);
				}

			}
		}
		Map<Integer, Integer> sortedMap = sortByValue(trmap);
		return sortedMap;
	}

	public static List<Integer> MaisEMenosSorteados(Map<Integer, Integer> trmap, boolean bol) {

		List<Integer> listaMaisSorteados = new ArrayList<Integer>();
		List<Integer> listaMenosSorteados = new ArrayList<Integer>();

		int qtdMaisSorteados = 1;
		int qtdMenosSorteados = 1;
		int valorAnterior = -1;

		if (bol) {

			for (Map.Entry<Integer, Integer> entry : trmap.entrySet()) {

				if (qtdMaisSorteados < 4 || entry.getValue() == valorAnterior) {
					listaMaisSorteados.add(entry.getKey());
					valorAnterior = entry.getValue();
				}

				qtdMaisSorteados++;
			}
			return listaMaisSorteados;
	
		} else {

			List keys = new ArrayList(trmap.keySet());
			Collections.reverse(keys);
			Iterator itKeys = keys.iterator();

			while (itKeys.hasNext()) {

				Integer key = (Integer) itKeys.next();
				Integer value = trmap.get(key);

				if (qtdMenosSorteados <= 1 || value == valorAnterior) {
					listaMenosSorteados.add(key);
					valorAnterior = value;
				}
				qtdMenosSorteados++;

			}

			return listaMenosSorteados;
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<Integer, Integer> sortByValue(Map<Integer, Integer> map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {

			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		Map result = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}
