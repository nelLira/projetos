package br.com.sorteio.combinacoes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import br.com.sorteio.lotofacil.FiltroJogos;
import br.com.sorteio.numeros.Jogo;
import br.com.sorteio.util.Estatisticas;

public class Teste {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		
		
		
		Estatisticas est = new Estatisticas();
		List<Jogo> listaTodosJogos = est.lerTodosOsJogos();
		FiltroJogos.iniciaListaJogosPorDemanda();
		Jogo primeiroJogo = null;
		int contAcertos = 0;
		List<Long> numeroSorteio = new ArrayList<Long>();
		List<Long> posicoesSorteio = new ArrayList<Long>();
		List<Jogo> jogosAcertados = new ArrayList<Jogo>();
		long qtdJogos = 0;
		long posicaoJogo = 0;
		for (Jogo proximoJogo : listaTodosJogos) {
			if (primeiroJogo == null) {
				primeiroJogo = proximoJogo;
			} else {
				FiltroJogos.setJogolistaJogosPorDemanda(primeiroJogo);
				qtdJogos++;
				//System.err.println("Jogo: " + qtdJogos + " de " + listaTodosJogos.size());
				List<Jogo> listOpcoesJogos = combincaoDe20();
				posicaoJogo = 0;
				TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();
				for (Jogo jogo : listOpcoesJogos) {
					Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
					intersection.retainAll(proximoJogo.getJogo());

					int chave = intersection.size();
					if (trmap.containsKey(chave)) {
						int valor = trmap.get(chave).intValue() + 1;
						trmap.put(chave, valor);

					} else {
						trmap.put(chave, 1);
					}
				}
				primeiroJogo = proximoJogo;
				System.out.println(trmap.toString());
			}
			
		}

		int sorteado = 0;
		System.err.println("Quantidade: " + contAcertos);
		for (Jogo jogo : jogosAcertados) {

			Jogo strUltimoSorteio = listaTodosJogos.get(numeroSorteio.get(sorteado).intValue() - 1);
			Jogo strSorteioAnterior = listaTodosJogos.get(numeroSorteio.get(sorteado).intValue() - 2);

			System.out.println("Sorteio: " + numeroSorteio.get(sorteado) + "; Posicao -> "
					+ posicoesSorteio.get(sorteado) + "; Jogo: " + jogo.getJogo() + " Repetidos: "
					+ est.quantidadeNumerosRepetidos(strUltimoSorteio, strSorteioAnterior));
			sorteado++;
		}
	   
	    
	    
		
		
		
		
		
		
		//soma();

//		Estatisticas est = new Estatisticas();
//		List<Jogo> todosJogos = est.lerTodosOsJogos();
//		List<Jogo> todosJogosAux = est.lerTodosOsJogos();
//		FiltroJogos.iniciaListaJogosPorDemanda();
//		int contAcertos = 0;
//
//		List<Jogo> jogosAcertados = new ArrayList<Jogo>();
//		int qtdJogos = 0;
//		for (Jogo primeiroJogo : todosJogos) {
//			TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();
//			for (Jogo jogo : todosJogosAux) {
//				Set<Integer> intersection = new HashSet<Integer>(primeiroJogo.getJogo());
//				intersection.retainAll(jogo.getJogo());
//				int chave = intersection.size();
//				if (trmap.containsKey(chave)) {
//					int valor = trmap.get(chave).intValue() + 1;
//					trmap.put(chave, valor);
//
//				} else {
//					trmap.put(chave, 1);
//				}
//			}
//			System.out.println(trmap);
//		}

		

		// Estatisticas est = new Estatisticas();
		// List<Jogo> todosJogos;
		//
		// List<Integer> tercaParte = Arrays.asList(6, 7, 8, 9, 10, 11, 12, 13,
		// 14, 15, 16, 17, 18, 19, 20);
		//
		// try {
		// todosJogos = est.lerTodosOsJogos();
		//
		// for (Integer parte : tercaParte) {
		//
		// TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();
		//
		// for (Jogo jogo : todosJogos) {
		//
		// for (int numero = 0; numero < jogo.getJogo().size() - 1; numero ++) {
		//
		// if (parte == jogo.getJogo().get(numero).intValue()) {
		// int chave = numero + 1;
		// if (trmap.containsKey(chave)) {
		// int valor = trmap.get(chave).intValue() + 1;
		// trmap.put(chave, valor);
		//
		// } else {
		// trmap.put(chave, 1);
		// }
		//
		// }
		//
		// }
		//
		// }
		// System.out.println(parte + " => " + trmap.toString());
		// }
		//
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// 6 => {1=4, 2=63, 3=221, 4=338, 5=248, 6=48} 1 a 5
		// 7 => {2=29, 3=134, 4=304, 5=280, 6=156, 7=18}
		// 8 => {2=10, 3=65, 4=207, 5=303, 6=211, 7=90, 8=7}
		// 9 => {2=3, 3=25, 4=129, 5=274, 6=301, 7=156, 8=42, 9=4}
		// 10 => {3=8, 4=59, 5=207, 6=295, 7=258, 8=108, 9=21, 10=1}
		// 11 => {3=2, 4=15, 5=114, 6=271, 7=304, 8=193, 9=77, 10=6}
		// 12 => {4=7, 5=43, 6=173, 7=286, 8=277, 9=120, 10=38, 11=3} 6 a 10
		// 13 => {5=12, 6=83, 7=263, 8=299, 9=224, 10=86, 11=14, 12=1} 6 a 10
		// 14 => {5=2, 6=27, 7=121, 8=308, 9=288, 10=157, 11=43, 12=6} 6 a 10
		// 15 => {6=10, 7=59, 8=185, 9=330, 10=238, 11=99, 12=21, 13=3}
		// 16 => {7=19, 8=113, 9=236, 10=312, 11=166, 12=44, 13=8}
		// 17 => {7=1, 8=40, 9=182, 10=300, 11=281, 12=114, 13=17, 14=3}
		// 18 => {8=3, 9=78, 10=262, 11=334, 12=210, 13=47, 14=6}
		// 19 => {9=15, 10=134, 11=331, 12=308, 13=135, 14=19}
		// 20 => {10=41, 11=217, 12=380, 13=247, 14=56} 11 a 15

		// Estatisticas est = new Estatisticas();
		// List<Jogo> todosJogosPrimeiro = est.lerTodosOsJogos();
		// List<Jogo> todosJogosSegundo = est.lerTodosOsJogos();
		// TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();
		//
		// for (int primeiro = 0; primeiro < todosJogosPrimeiro.size();
		// primeiro++) {
		//
		// for (int segundo = 0; segundo < todosJogosSegundo.size(); segundo++)
		// {
		//
		// if (primeiro != segundo) {
		// Set<Integer> intersection = new //
		// HashSet<Integer>(todosJogosPrimeiro.get(primeiro).getJogo());
		// intersection.retainAll(todosJogosSegundo.get(segundo).getJogo());
		//
		// int chave = intersection.size();
		// if (trmap.containsKey(chave)) {
		// int valor = trmap.get(chave).intValue() + 1;
		// trmap.put(chave, valor);
		//
		// } else {
		// trmap.put(chave, 1);
		// }
		//
		// }
		//
		// }
		//
		// }
		// System.out.println(trmap);
	}// 9-40;
	
	public static List<Jogo> combincaoDe20(){
		Jogo numeros = new Jogo(Arrays.asList(1,2,3,4,5,6,7,9,10,11,13,14,15,16,17,18,20,21,22,23,24));
		List<Jogo> listOpcoesJogos =  combinacoes(numeros);
		return listOpcoesJogos;
	}

	
	private static List<Jogo> combinacoes(Jogo numeros) {
		Combinacao combinacoes = new Combinacao(numeros.getJogo(), 15);
		return combinacoes.geraListaCombinacoes();
	}

	public static List<Jogo> combinaSorteadosNaoSorteados(Jogo ultimoJogo) throws FileNotFoundException, IOException {
		
	    Jogo naoSorteado = ultimoJogo.numerosNaoSorteados(ultimoJogo);
	    //{1=3, 2=2, 3=2, 4=2, 5=3}
	    int casa= 0;
	    Jogo fixo = new Jogo();
	    Jogo jUm = new Jogo();
	    Jogo jDois = new Jogo();
	    Jogo jTres = new Jogo();
	    Jogo jQuatro = new Jogo();
	    for (Integer numero : ultimoJogo.getJogo()) {
	    	if (casa < 3){
	    		fixo.getJogo().add(numero);
	    	}
	    	
	    	if (casa > 2 && casa < 6 ){
	    		jUm.getJogo().add(numero);
	    	}
	    	
	    	if (casa > 5 && casa < 9 ){
	    		jDois.getJogo().add(numero);
	    	}
	    	
	    	if (casa > 8 && casa < 12 ){
	    		jTres.getJogo().add(numero);
	    	}
	    	
	    	if (casa > 11 && casa < 15 ){
	    		jQuatro.getJogo().add(numero);
	    	}
	    	casa++;
	    	
		}
	    
	    casa = 0;
	    for (Integer numero : naoSorteado.getJogo()) {
	    	if (casa < 2 ){
	    		fixo.getJogo().add(numero);
	    	}
	    	
	    	if (casa > 1 && casa < 4 ){
	    		jUm.getJogo().add(numero);
	    	}
	    	
	    	if (casa > 3 && casa < 6 ){
	    		jDois.getJogo().add(numero);
	    	}
	    	
	    	if (casa > 5 && casa < 8 ){
	    		jTres.getJogo().add(numero);
	    	}
	    	
	    	if (casa > 7 && casa < 10 ){
	    		jQuatro.getJogo().add(numero);
	    	}
	    	casa ++;
		}
	    
	    
	    Jogo jogo1 = new Jogo();
	    jogo1.getJogo().addAll(fixo.getJogo());
	    jogo1.getJogo().addAll(jUm.getJogo());
	    jogo1.getJogo().addAll(jDois.getJogo());
	    
	    Jogo jogo2 = new Jogo();
	    jogo2.getJogo().addAll(fixo.getJogo());
	    jogo2.getJogo().addAll(jUm.getJogo());
	    jogo2.getJogo().addAll(jTres.getJogo());
	    
	    Jogo jogo3 = new Jogo();
	    jogo3.getJogo().addAll(fixo.getJogo());
	    jogo3.getJogo().addAll(jUm.getJogo());
	    jogo3.getJogo().addAll(jQuatro.getJogo());
	    
	    Jogo jogo4 = new Jogo();
	    jogo4.getJogo().addAll(fixo.getJogo());
	    jogo4.getJogo().addAll(jDois.getJogo());
	    jogo4.getJogo().addAll(jTres.getJogo());
	    
	    Jogo jogo5 = new Jogo();
	    jogo5.getJogo().addAll(fixo.getJogo());
	    jogo5.getJogo().addAll(jDois.getJogo());
	    jogo5.getJogo().addAll(jQuatro.getJogo());
	     
	    Jogo jogo6 = new Jogo();
	    jogo6.getJogo().addAll(fixo.getJogo());
	    jogo6.getJogo().addAll(jTres.getJogo());
	    jogo6.getJogo().addAll(jQuatro.getJogo());
	    
	    Collections.sort(jogo1.getJogo());
	    Collections.sort(jogo2.getJogo());
	    Collections.sort(jogo3.getJogo());
	    Collections.sort(jogo4.getJogo());
	    Collections.sort(jogo5.getJogo());
	    Collections.sort(jogo6.getJogo());
	    
	    List<Jogo> retorno = new ArrayList<Jogo>();
	    retorno.add(jogo1);
	    retorno.add(jogo2);
	    retorno.add(jogo3);
	    retorno.add(jogo4);
	    retorno.add(jogo5);
	    retorno.add(jogo6);
	    
	    return retorno;
	}
		// 1873240
	
	
	public static void soma() throws FileNotFoundException, IOException {

		Estatisticas est = new Estatisticas();

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(est.lerTodosOsJogos());
		Integer soma = 0;

		for (Jogo jogo : listaAuxilar) {
			for (Integer numero : jogo.getJogo()) {
				soma = soma + numero;
			}
			System.out.println(soma);
			soma = 0;
		}

	}

	public static void primoNove() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();
		List<Jogo> primo = new ArrayList<Jogo>();
		primo.add(new Jogo(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23))); // {2=5,
																			// 3=67,
																			// 4=265,
																			// 5=483,
																			// 6=417,
																			// 7=239,
																			// 8=61,
																			// 9=4}

		TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();

		for (Jogo jogo : todosJogos) {
			Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
			intersection.retainAll(primo.get(0).getJogo());

			int chave = intersection.size();
			if (trmap.containsKey(chave)) {
				int valor = trmap.get(chave).intValue() + 1;
				trmap.put(chave, valor);

			} else {
				trmap.put(chave, 1);
			}
		}

		System.out.println(trmap.toString());

	}
	
	public static void dentro() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();
		List<Jogo> dentro = new ArrayList<Jogo>();
		//dentro.add(new Jogo(Arrays.asList(7, 8, 9, 12, 13, 14, 17, 18, 19))); 
		dentro.add(new Jogo(Arrays.asList(19, 20, 24, 25)));

		TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();

		for (Jogo jogo : todosJogos) {
			Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
			intersection.retainAll(dentro.get(0).getJogo());

			int chave = intersection.size();
			if (trmap.containsKey(chave)) {
				int valor = trmap.get(chave).intValue() + 1;
				trmap.put(chave, valor);

			} else {
				trmap.put(chave, 1);
			}
		}

		System.out.println(trmap.toString());

	}
	
	public static void pares() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();
		List<Jogo> dentro = new ArrayList<Jogo>();
		dentro.add(new Jogo(Arrays.asList(2,4,6,8,10,12,14,16,18,20,22,24))); 

		TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();

		for (Jogo jogo : todosJogos) {
			Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
			intersection.retainAll(dentro.get(0).getJogo());

			int chave = intersection.size();
			if (trmap.containsKey(chave)) {
				int valor = trmap.get(chave).intValue() + 1;
				trmap.put(chave, valor);

			} else {
				trmap.put(chave, 1);
			}
		}

		System.out.println(trmap.toString());

	}

	public void resultados() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		List<Jogo> listaTodosJogos = est.lerTodosOsJogos();

		Jogo strUltimoSorteio = listaTodosJogos.get(listaTodosJogos.size() - 1);
		Jogo strSorteioAnterior = listaTodosJogos.get(listaTodosJogos.size() - 2);

		List<Jogo> listaJogosFiltrados = jogos(strSorteioAnterior);

		TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();

//		for (Jogo jogo : todosJogos) {
//			Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
//			intersection.retainAll(primo.get(0).getJogo());
//
//			int chave = intersection.size();
//			if (trmap.containsKey(chave)) {
//				int valor = trmap.get(chave).intValue() + 1;
//				trmap.put(chave, valor);
//
//			} else {
//				trmap.put(chave, 1);
//			}
//		}

		System.out.println(trmap.toString());

	}

	private List<Jogo> jogos(Jogo strSorteioAnterior) {
		// TODO Auto-generated method stub
		return null;
	}

	public static long fatorial(long num) {
		if (num <= 1)
			return 1;
		else
			return num * fatorial(num - 1);
	}
}

class Student {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	private int age;
	private List<String> skills;

	// getters setters

	public static void testesEstatisticas() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		// List<Jogo> todosJogos = est.listaJogosNumerosRepetidosJogoAnterior();
		List<Jogo> todosJogos = est.lerTodosOsJogos();
		List<Jogo> primo = new ArrayList<Jogo>();
		// primo.add(new Jogo(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23)));
		// // {2=5, 3=67, 4=265, 5=483, 6=417, 7=239, 8=61, 9=4}
		// primo.add(new Jogo(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20,
		// 22, 24))); // {2=5, 3=67, 4=265, 5=483, 6=417, 7=239, 8=61, 9=4}
		primo.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 10, 11, 15, 16, 20, 21, 22, 23, 24, 25)));
		TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();

		for (Jogo jogo : todosJogos) {

			// Jogo strNumerosNaoSorteados = jogo.numerosNaoSorteados(jogo);

			// Set<Integer> intersection = new
			// HashSet<Integer>(strNumerosNaoSorteados.getJogo());
			Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
			intersection.retainAll(primo.get(0).getJogo());

			int chave = intersection.size();
			if (trmap.containsKey(chave)) {
				int valor = trmap.get(chave).intValue() + 1;
				trmap.put(chave, valor);

			} else {
				trmap.put(chave, 1);
			}
		}

		System.out.println(trmap.toString());
	}

	public static void tercaParte() {

		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos;

		List<Integer> tercaParte = Arrays.asList(6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

		try {
			todosJogos = est.lerTodosOsJogos();

			for (Integer parte : tercaParte) {

				TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();

				for (Jogo jogo : todosJogos) {

					for (int numero = 0; numero >= jogo.getJogo().size(); numero++) {

						if (parte == jogo.getJogo().get(numero).intValue()) {
							int chave = numero + 1;
							if (trmap.containsKey(chave)) {
								int valor = trmap.get(chave).intValue() + 1;
								trmap.put(chave, valor);

							} else {
								trmap.put(numero, 1);
							}

						}

					}

				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static <K, V> Map<Integer, Integer> listAsMap(Collection<Integer> sourceList,
			ListToMapConverter<Integer, Integer> converter) {
		TreeMap<Integer, Integer> newMap = new TreeMap<Integer, Integer>();
		for (Integer item : sourceList) {
			newMap.put(converter.getKey(item), item);
		}
		return newMap;
	}

	public static interface ListToMapConverter<K, V> {
		public K getKey(V item);
	}

}