package br.com.loteria.lotofacil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import br.com.loteria.combinacoes.Combinacoes;
import br.com.loteria.jogo.Jogo;
import br.com.loteria.util.Utils;

public class Estatisticas {

	private List<Estatisticas> listaEstatisticas;

	private Jogo jogo;
	private String numeroSorteio;
	private String repetidos;
	private String pares;
	private String primos;
	private String fibonacci;
	private String quadrado;
	private String multiplosDeTres;
	private String dezMais;
	private String linhas;
	private String colunas;

	private Jogo numerosCruz;
	private Jogo numerosX;
	private Jogo numerosQuadrado;
	private Jogo numerosMultiplosDeTres;
	private Jogo numerosSequenciaDeFibonacci;
	private Jogo numerosPrimos;
	private Jogo numerosDentro;
	private Jogo numerosPares;
	private Jogo numerosGrupo20a25;
	private Jogo numerosGrupo8a11;
	private Jogo numerosCanto1;
	private Jogo numerosCanto2;
	private Jogo numerosCanto3;
	private Jogo numerosCanto4;
	private Jogo numerosLinha1;
	private Jogo numerosLinha2;
	private Jogo numerosLinha3;
	private Jogo numerosLinha4;
	private Jogo numerosLinha5;
	private Jogo numerosColuna1;
	private Jogo numerosColuna2;
	private Jogo numerosColuna3;
	private Jogo numerosColuna4;
	private Jogo numerosColuna5;
	private Jogo numerosImportantes;

	public List<Jogo> lerTodosOsJogos() throws FileNotFoundException, IOException {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String path = s + "\\target\\sorteios.txt";

		File file = new File(path.replace("\\target\\target", "\\target"));
		byte[] bytes = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);

		fis.read(bytes);
		fis.close();

		List<Jogo> jogos = new ArrayList<Jogo>();

		String[] valueStr = new String(bytes).trim().split("\\s+");
		List<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < valueStr.length; i++) {
			lista.add(Integer.parseInt(valueStr[i]));
			if (lista.size() == 15) {
				jogos.add(new Jogo(lista));
				lista = new ArrayList<Integer>();
			}
		}

		return jogos;

	}

	public Estatisticas() {

	}

	public void iniciarListas() {
		this.listaEstatisticas = new ArrayList<>();
		this.numerosCruz = new Jogo(Arrays.asList(3, 8, 11, 12, 13, 14, 15, 18, 23));
		this.numerosX = new Jogo(Arrays.asList(1, 5, 7, 9, 13, 17, 19, 21, 25));
		this.numerosQuadrado = new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 10, 11, 15, 16, 20, 21, 22, 23, 24, 25));
		this.numerosMultiplosDeTres = new Jogo(Arrays.asList(3, 6, 9, 12, 15, 18, 21, 24));
		this.numerosSequenciaDeFibonacci = new Jogo(Arrays.asList(1, 2, 3, 5, 8, 13, 21));
		this.numerosPrimos = new Jogo(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23));
		this.numerosDentro = new Jogo(Arrays.asList(7, 8, 9, 12, 13, 14, 17, 18, 19));
		this.numerosPares = new Jogo(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24));
		this.numerosGrupo20a25 = new Jogo(Arrays.asList(20, 21, 22, 23, 24, 25));
		this.numerosGrupo8a11 = new Jogo(Arrays.asList(8, 9, 10, 11));

		this.numerosCanto1 = new Jogo(Arrays.asList(1, 2, 6, 7));
		this.numerosCanto2 = new Jogo(Arrays.asList(4, 5, 9, 10));
		this.numerosCanto3 = new Jogo(Arrays.asList(16, 17, 21, 22));
		this.numerosCanto4 = new Jogo(Arrays.asList(19, 20, 24, 25));

		this.numerosLinha1 = new Jogo(Arrays.asList(1, 2, 3, 4, 5));
		this.numerosLinha2 = new Jogo(Arrays.asList(6, 7, 8, 9, 10));
		this.numerosLinha3 = new Jogo(Arrays.asList(11, 12, 13, 14, 15));
		this.numerosLinha4 = new Jogo(Arrays.asList(16, 17, 18, 19, 20));
		this.numerosLinha5 = new Jogo(Arrays.asList(21, 22, 23, 24, 25));

		this.numerosColuna1 = new Jogo(Arrays.asList(1, 6, 11, 16, 21));
		this.numerosColuna2 = new Jogo(Arrays.asList(2, 7, 12, 17, 22));
		this.numerosColuna3 = new Jogo(Arrays.asList(3, 8, 13, 18, 23));
		this.numerosColuna4 = new Jogo(Arrays.asList(4, 9, 14, 19, 24));
		this.numerosColuna5 = new Jogo(Arrays.asList(5, 10, 15, 20, 25));

		this.numerosImportantes = new Jogo(Arrays.asList(5, 6, 7, 12, 13, 14, 19, 20, 21));
	}

	public void limparListas() {
		this.numerosCruz = null;
		this.numerosX = null;
		this.numerosQuadrado = null;
		this.numerosMultiplosDeTres = null;
		this.numerosSequenciaDeFibonacci = null;
		this.numerosPrimos = null;
		this.numerosDentro = null;
		this.numerosPares = null;
		this.numerosGrupo20a25 = null;
		this.numerosGrupo8a11 = null;

		this.numerosCanto1 = null;
		this.numerosCanto2 = null;
		this.numerosCanto3 = null;
		this.numerosCanto4 = null;

		this.numerosLinha1 = null;
		this.numerosLinha2 = null;
		this.numerosLinha3 = null;
		this.numerosLinha4 = null;
		this.numerosLinha5 = null;

		this.numerosColuna1 = null;
		this.numerosColuna2 = null;
		this.numerosColuna3 = null;
		this.numerosColuna4 = null;
		this.numerosColuna5 = null;

		this.numerosImportantes = null;
	}

	public List<Estatisticas> getListaEstatisticas() {
		return listaEstatisticas;
	}

	public void setListaEstatisticas(List<Estatisticas> listaEstatisticas) {
		this.listaEstatisticas = listaEstatisticas;
	}

	public String getNumeroSorteio() {
		return numeroSorteio;
	}

	public void setNumeroSorteio(String numeroSorteio) {
		this.numeroSorteio = numeroSorteio;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public String getRepetidos() {
		return repetidos;
	}

	public void setRepetidos(String repetidos) {
		this.repetidos = repetidos;
	}

	public String getPares() {
		return pares;
	}

	public void setPares(String pares) {
		this.pares = pares;
	}

	public String getPrimos() {
		return primos;
	}

	public void setPrimos(String primos) {
		this.primos = primos;
	}

	public String getFibonacci() {
		return fibonacci;
	}

	public void setFibonacci(String fibonacci) {
		this.fibonacci = fibonacci;
	}

	public String getQuadrado() {
		return quadrado;
	}

	public void setQuadrado(String quadrado) {
		this.quadrado = quadrado;
	}

	public String getMultiplosDeTres() {
		return multiplosDeTres;
	}

	public void setMultiplosDeTres(String multiplosDeTres) {
		this.multiplosDeTres = multiplosDeTres;
	}

//	public String getDezMais() {
//		return dezMais;
//	}

	public void setDezMais(String dezMais) {
		this.dezMais = dezMais;
	}

	public String getLinhas() {
		return linhas;
	}

	public void setLinhas(String linhas) {
		this.linhas = linhas;
	}

	public String getColunas() {
		return colunas;
	}

	public void setColunas(String colunas) {
		this.colunas = colunas;
	}

	public Jogo buscarNumerosCruz() {
		return this.numerosCruz;
	}

	public Jogo buscarNumerosX() {
		return this.numerosX;
	}

	public Jogo buscarNumerosQuadrado() {
		return this.numerosQuadrado;
	}

	public Jogo buscarNumerosMultiplosDeTres() {
		return this.numerosMultiplosDeTres;
	}

	public Jogo buscarNumerosSequenciaDeFibonacci() {
		return this.numerosSequenciaDeFibonacci;
	}

	public Jogo buscarNumerosPrimos() {
		return this.numerosPrimos;
	}

	public Jogo buscarNumerosDentro() {
		return this.numerosDentro;
	}

	public Jogo buscarNumerosPares() {
		return this.numerosPares;
	}

	public Jogo buscarNumerosGrupo20a25() {
		return this.numerosGrupo20a25;
	}

	public Jogo buscarNumerosGrupo8a11() {
		return this.numerosGrupo8a11;
	}

	public Jogo buscarNumerosCanto1() {
		return this.numerosCanto1;
	}

	public Jogo buscarNumerosCanto2() {
		return this.numerosCanto2;
	}

	public Jogo buscarNumerosCanto3() {
		return this.numerosCanto3;
	}

	public Jogo buscarNumerosCanto4() {
		return this.numerosCanto4;
	}

	public Jogo buscarNumerosLinha1() {
		return this.numerosLinha1;
	}

	public Jogo buscarNumerosLinha2() {
		return this.numerosLinha2;
	}

	public Jogo buscarNumerosLinha3() {
		return this.numerosLinha3;
	}

	public Jogo buscarNumerosLinha4() {
		return this.numerosLinha4;
	}

	public Jogo buscarNumerosLinha5() {
		return this.numerosLinha5;
	}

	public Jogo buscarNumerosColuna1() {
		return this.numerosColuna1;
	}

	public Jogo buscarNumerosColuna2() {
		return this.numerosColuna2;
	}

	public Jogo buscarNumerosColuna3() {
		return this.numerosColuna3;
	}

	public Jogo buscarNumerosColuna4() {
		return this.numerosColuna4;
	}

	public Jogo buscarNumerosColuna5() {
		return this.numerosColuna5;
	}

	public Jogo buscarNumerosImportantes() {
		return this.numerosImportantes;
	}

//	public Jogo buscarDezMais(List<Jogo> todosSorteios, int i) throws FileNotFoundException, IOException {
//
//		Map<Integer, Integer> jogosEst = estatisticasJogosListaDinamica(todosSorteios, 15,i);
//
//		Jogo dezMais = new Jogo();
//		int j = 0;
//		for (Integer integer : jogosEst.keySet()) {
//
//			if (j == 10) {
//				break;
//			}
//
//			dezMais.getJogo().add(integer);
//			j++;
//
//		}
//
//		return dezMais;
//	}

	public List<Integer> numerosPrioritarios(List<Jogo> listaTodosSorteios) {

		Map<Integer, Integer> trmap = dezUltimosSorteios(listaTodosSorteios);

		return MaisEMenosSorteados(trmap, true);

	}

	protected Map<Integer, Integer> dezUltimosSorteios(List<Jogo> listaJogosPorDemanda) {

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
		Map<Integer, Integer> sortedMap = Utils.sortByValue(trmap);
		return sortedMap;
	}

	public List<Integer> MaisEMenosSorteados(Map<Integer, Integer> trmap, boolean bol) {

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

	public Map<Integer, Integer> estatisticasJogos(List<Jogo> todosSorteios) throws FileNotFoundException, IOException {

		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = todosSorteios;
		Map<Integer, Integer> mapaNumeros = new TreeMap<Integer, Integer>();
		Integer qtdJogosAnalisados = 10;

		for (int i = todosJogos.size() - qtdJogosAnalisados; i < todosJogos.size(); i++) {
			for (Integer numero : todosJogos.get(i).getJogo()) {
				if (mapaNumeros.containsKey(numero)) {
					mapaNumeros.put(numero, mapaNumeros.get(numero).intValue() + 1);
				} else {
					mapaNumeros.put(numero, 1);
				}
			}

		}

		Map<Integer, Integer> mapaNumerosOrdenados = Utils.sortByValue(mapaNumeros);

		for (Integer chave : mapaNumerosOrdenados.keySet()) {
			mapaNumerosOrdenados.put(chave, (mapaNumerosOrdenados.get(chave).intValue() * 100) / qtdJogosAnalisados);
		}

		return mapaNumerosOrdenados;

	}

	public Map<Integer, Integer> estatisticasJogos(int QtdJogos) throws FileNotFoundException, IOException {

		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();

		Map<Integer, Integer> mapaNumeros = new TreeMap<Integer, Integer>();
		Integer qtdJogosAnalisados = 19;

		for (int i = QtdJogos - qtdJogosAnalisados; i < QtdJogos; i++) {
			for (Integer numero : todosJogos.get(i).getJogo()) {
				if (mapaNumeros.containsKey(numero)) {
					mapaNumeros.put(numero, mapaNumeros.get(numero).intValue() + 1);
				} else {
					mapaNumeros.put(numero, 1);
				}
			}

		}

		Map<Integer, Integer> mapaNumerosOrdenados = Utils.sortByValue(mapaNumeros);

		for (Integer chave : mapaNumerosOrdenados.keySet()) {
			//mapaNumerosOrdenados.put(chave, (mapaNumerosOrdenados.get(chave).intValue() * 100) / qtdJogosAnalisados);
			mapaNumerosOrdenados.put(chave, mapaNumerosOrdenados.get(chave).intValue());
		}
		return mapaNumerosOrdenados;

	}
	
	public Map<Integer, Integer> estatisticasJogosPorDemanda(int QtdJogos, List<Jogo> todosJogos) throws FileNotFoundException, IOException {

		//Estatisticas est = new Estatisticas();
		//List<Jogo> todosJogos = est.lerTodosOsJogos();

		Map<Integer, Integer> mapaNumeros = new TreeMap<Integer, Integer>();
		Integer qtdJogosAnalisados = 19;

		for (int i = QtdJogos - qtdJogosAnalisados; i < QtdJogos; i++) {
			for (Integer numero : todosJogos.get(i).getJogo()) {
				
				if (mapaNumeros.containsKey(numero)) {
					mapaNumeros.put(numero, mapaNumeros.get(numero).intValue() + 1);
				} else {
					mapaNumeros.put(numero, 1);
				}
			}

		}

		Map<Integer, Integer> mapaNumerosOrdenados = Utils.sortByValue(mapaNumeros);

		for (Integer chave : mapaNumerosOrdenados.keySet()) {
			//mapaNumerosOrdenados.put(chave, (mapaNumerosOrdenados.get(chave).intValue() * 100) / qtdJogosAnalisados);
			mapaNumerosOrdenados.put(chave, mapaNumerosOrdenados.get(chave).intValue());
		}
		return mapaNumerosOrdenados;

	}

	public Map<Integer, Integer> estatisticasJogosQtdSeguidos(int QtdJogos) throws FileNotFoundException, IOException {

		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();

		Map<Integer, Integer> mapaNumeros = new TreeMap<Integer, Integer>();
		Integer qtdJogosAnalisados = 11;
		List<Integer> numeros = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25));

		for (Integer numero : numeros) {
			if (numero == 14) {
				System.out.println("");
			}
			int contaNumeroPresentes = 0;
			int contaNumeroAusentes = 0;

			for (int i = todosJogos.size() - 1; i > QtdJogos - qtdJogosAnalisados; i--) {
				if (todosJogos.get(i).getJogo().contains(numero)) {
					contaNumeroPresentes++;
				} else {
				//	mapaNumeros.put(numero, contaNumeroPresentes);
					break;
				}

			}
			//if (qtdJogosAnalisados == contaNumeroPresentes+1) {
			//	mapaNumeros.put(numero, contaNumeroPresentes);
		//	}

			if (contaNumeroPresentes == 0) {
				for (int i = todosJogos.size() - 1; i > QtdJogos - qtdJogosAnalisados; i--) {
					if (!todosJogos.get(i).getJogo().contains(numero)) {
						contaNumeroAusentes++;
					} else {
					//	mapaNumeros.put(numero, contaNumeroAusentes * -1);
						break;
					}
				}
				mapaNumeros.put(numero, contaNumeroAusentes * -1);
			} else {
				mapaNumeros.put(numero, contaNumeroPresentes);
			}

		}

		Map<Integer, Integer> mapaNumerosOrdenados = Utils.sortByValue(mapaNumeros);

		for (Integer chave : mapaNumerosOrdenados.keySet()) {
			mapaNumerosOrdenados.put(chave, mapaNumerosOrdenados.get(chave).intValue());
		}
		return mapaNumerosOrdenados;

	}

	public Map<Integer, Integer> estatisticasJogosListaDinamica(List<Jogo> todosJogos, int QtdJogos, int posicao)
			throws FileNotFoundException, IOException {

		Estatisticas est = new Estatisticas();

		Map<Integer, Integer> mapaNumeros = new TreeMap<Integer, Integer>();
		
		for (int i = posicao - (QtdJogos); i < posicao; i++) {
			
			for (Integer numero : todosJogos.get(i).getJogo()) {
				if (mapaNumeros.containsKey(numero)) {
					mapaNumeros.put(numero, mapaNumeros.get(numero).intValue() + 1);
				} else {
					mapaNumeros.put(numero, 1);
				}
			}
			
		}

		Map<Integer, Integer> mapaNumerosOrdenados = Utils.sortByValue(mapaNumeros);

		for (Integer chave : mapaNumerosOrdenados.keySet()) {
			mapaNumerosOrdenados.put(chave, (mapaNumerosOrdenados.get(chave).intValue() * 100) / QtdJogos);
		}
		return mapaNumerosOrdenados;

	}

	public Jogo dezJogosMaisFrequentes(Map<Integer, Integer> mapaNumeros) throws FileNotFoundException, IOException {

		Jogo dezPrimeiros = new Jogo();

		int dezPrimeiras = 10;
		int cont = 0;

		for (Integer chave : mapaNumeros.keySet()) {
			cont++;
			dezPrimeiros.getJogo().add(chave);

			if (cont == dezPrimeiras) {
				break;
			}
		}

		return dezPrimeiros;

	}

	public Jogo dezJogosMenosFrequentes(Map<Integer, Integer> mapaNumeros) throws FileNotFoundException, IOException {

		// 2=0.23; 3=1.99; 4=8.87; 5=24.32; 6=30.37; 7=23.14; 8=9.51; 9=1.52

		Jogo dezUltimos = new Jogo();

		int cont = 0;

		for (Integer chave : mapaNumeros.keySet()) {
			cont++;
			if (cont > 15) {
				dezUltimos.getJogo().add(chave);
			}
		}

		return dezUltimos;

	}

	public Jogo cincoJogosDoMeio(Map<Integer, Integer> mapaNumeros) throws FileNotFoundException, IOException {

		Jogo cincoDoMeio = new Jogo();

		int cont = 0;

		for (Integer chave : mapaNumeros.keySet()) {
			cont++;
			if (cont > 10 && cont < 16) {
				cincoDoMeio.getJogo().add(chave);
			}
		}

		return cincoDoMeio;

	}

	public void estatSorteio() throws FileNotFoundException, IOException {

		Filtro filtro = new Filtro();
		filtro.iniciaListas();
		Estatisticas estisticas = new Estatisticas();
		estisticas.limparListas();
		filtro.setaListaTodosSorteios(estisticas.lerTodosOsJogos());

		Jogo strUltimoSorteio = filtro.buscaListaTodosSorteios().get(filtro.buscaListaTodosSorteios().size() - 1);
		filtro.buscaListaTodosSorteios().remove(filtro.buscaListaTodosSorteios().size() - 1);

		Jogo premiado = new Jogo();

		Combinacoes combinacoes = new Combinacoes();
		filtro.setListaJogosCombinadosCompleto(combinacoes.todosCombinacoesLotoFacil());

		List<Jogo> listaJogosFiltrados = filtro.bucaListaJogosFiltrados();

		TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();

		for (Jogo jogo : listaJogosFiltrados) {
			Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
			intersection.retainAll(strUltimoSorteio.getJogo());

			int chave = intersection.size();
			if (trmap.containsKey(chave)) {
				int valor = trmap.get(chave).intValue() + 1;
				trmap.put(chave, valor);

			} else {
				trmap.put(chave, 1);
			}
			if (chave == 15) {
				premiado = jogo;
			}
		}

		System.err.println(premiado.getJogo());

		System.out.println(trmap.toString());

		System.out.println("Repetidos: " + estisticas.quantidadeNumerosRepetidos(strUltimoSorteio,
				filtro.buscaListaTodosSorteios().get(filtro.buscaListaTodosSorteios().size() - 1)));

		filtro.limpaListas();
		estisticas.limparListas();

		System.out.println("FIM");

	}

	public String estatSorteio(Jogo ultimoJogo, int numeroUltimoSorteio) throws FileNotFoundException, IOException {

		Filtro filtro = new Filtro();
		filtro.iniciaListas();
		filtro.setJogoAtual(numeroUltimoSorteio);
		Estatisticas estisticas = new Estatisticas();
		estisticas.limparListas();
		filtro.setaListaTodosSorteios(estisticas.lerTodosOsJogos());
		String retorno = "";
		while (filtro.buscaListaTodosSorteios().size() > numeroUltimoSorteio) {

			filtro.buscaListaTodosSorteios().remove(filtro.buscaListaTodosSorteios().size() - 1);

		}

		System.out.println(filtro.buscaListaTodosSorteios().size());

		Combinacoes combinacoes = new Combinacoes();
		filtro.setListaJogosCombinadosCompleto(combinacoes.todosCombinacoesLotoFacil());

		List<Jogo> listaJogosFiltrados = filtro.bucaListaJogosFiltrados();

		TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();

		for (Jogo jogo : listaJogosFiltrados) {
			Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
			intersection.retainAll(ultimoJogo.getJogo());

			int chave = intersection.size();
			if (chave == 15) {
				retorno = "Sim";
				break;
			}
		}

		filtro.limpaListas();
		estisticas.limparListas();
		return retorno;

	}

	public int quantidadeNumerosRepetidos(Jogo strUltimoSorteio, Jogo strSorteioAnterior) {
		Set<Integer> intersection = new HashSet<Integer>(strSorteioAnterior.getJogo());
		intersection.retainAll(strUltimoSorteio.getJogo());
		return intersection.size();

	}

	public void estatisticasUltimosSorteio(int qtdJogos) throws FileNotFoundException, IOException {
		System.out.println("gerando estatÃ­sticas dos Ãºltimos jogos...");
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();
		List<Jogo> todosJogos = estatisticas.lerTodosOsJogos();
		Jogo pares = estatisticas.buscarNumerosPares();
		Jogo primos = estatisticas.buscarNumerosPrimos();
		Jogo fibonacci = estatisticas.buscarNumerosSequenciaDeFibonacci();
		Jogo quadrado = estatisticas.buscarNumerosQuadrado();
		Jogo multiplosDeTres = estatisticas.buscarNumerosMultiplosDeTres();

		for (int i = todosJogos.size() - (qtdJogos); i < todosJogos.size(); i++) {
			Set<Integer> intersectionRepetidos = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionRepetidos.retainAll(todosJogos.get(i - 1).getJogo());

			Set<Integer> intersectionPares = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionPares.retainAll(pares.getJogo());

			Set<Integer> intersectionPrimos = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionPrimos.retainAll(primos.getJogo());

			Set<Integer> intersectionFibonacci = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionFibonacci.retainAll(fibonacci.getJogo());

			Set<Integer> intersectionQuadrado = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionQuadrado.retainAll(quadrado.getJogo());

			Set<Integer> intersectionMultiplosDeTres = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionMultiplosDeTres.retainAll(multiplosDeTres.getJogo());

			Map<Integer, Integer> mapaEstatisticasJogos = estatisticas.estatisticasJogos(i);

			Jogo dezPrimeiros = estatisticas.dezJogosMaisFrequentes(mapaEstatisticasJogos);
			Jogo cincoDoMeio = estatisticas.cincoJogosDoMeio(mapaEstatisticasJogos);
			Jogo dezUltimos = estatisticas.dezJogosMenosFrequentes(mapaEstatisticasJogos);

			Set<Integer> intersectionDezPrimeiros = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionDezPrimeiros.retainAll(dezPrimeiros.getJogo());

			Set<Integer> intersectionCincoDoMeio = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionCincoDoMeio.retainAll(cincoDoMeio.getJogo());

			Set<Integer> intersectionDezUltimos = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionDezUltimos.retainAll(dezUltimos.getJogo());

			System.out.println(
					// "Jogo: " + todosJogos.get(i).getJogo() +
					" Repetidos: " + intersectionRepetidos.size() + " Pares -> " + intersectionPares.size()
							+ " Primos -> " + intersectionPrimos.size() + " Fibonacci -> "
							+ intersectionFibonacci.size() + " Quadrado -> " + intersectionQuadrado.size()
							+ " Multiplos de TrÃªs -> " + intersectionMultiplosDeTres.size() + " Dez primeiros est. -> "
							+ intersectionDezPrimeiros.size() + " Cinco do meio esta. ->  "
							+ intersectionCincoDoMeio.size() + " Dez Ãºltimos est. -> "
							+ intersectionDezUltimos.size());

		}
		System.out.println(estatisticas.estatisticasJogos(todosJogos.size()));
	}

	public void estatisticasUltimosSorteioCSV(int qtdJogos, boolean gerarAquivo) throws IOException {

		System.out.println("gerando estatÃ­sticas dos Ãºltimos jogos...");
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();
		List<Jogo> todosJogos = estatisticas.lerTodosOsJogos();
		Jogo pares = estatisticas.buscarNumerosPares();
		Jogo primos = estatisticas.buscarNumerosPrimos();
		Jogo fibonacci = estatisticas.buscarNumerosSequenciaDeFibonacci();
		Jogo quadrado = estatisticas.buscarNumerosQuadrado();
		Jogo multiplosDeTres = estatisticas.buscarNumerosMultiplosDeTres();
		Jogo numerosImportantes = estatisticas.buscarNumerosImportantes();
		Jogo linha1 = new Jogo(estatisticas.buscarNumerosLinha1().getJogo());
		Jogo linha2 = new Jogo(estatisticas.buscarNumerosLinha2().getJogo());
		Jogo linha3 = new Jogo(estatisticas.buscarNumerosLinha3().getJogo());
		Jogo linha4 = new Jogo(estatisticas.buscarNumerosLinha4().getJogo());
		Jogo linha5 = new Jogo(estatisticas.buscarNumerosLinha5().getJogo());
		Jogo coluna1 = new Jogo(estatisticas.buscarNumerosColuna1().getJogo());
		Jogo coluna2 = new Jogo(estatisticas.buscarNumerosColuna2().getJogo());
		Jogo coluna3 = new Jogo(estatisticas.buscarNumerosColuna3().getJogo());
		Jogo coluna4 = new Jogo(estatisticas.buscarNumerosColuna4().getJogo());
		Jogo coluna5 = new Jogo(estatisticas.buscarNumerosColuna5().getJogo());

		List<String> resultCSV = new ArrayList<String>();
		for (int i = todosJogos.size() - (qtdJogos); i < todosJogos.size(); i++) {
		
			Set<Integer> intersectionRepetidos = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionRepetidos.retainAll(todosJogos.get(i - 1).getJogo());

			Set<Integer> intersectionPares = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionPares.retainAll(pares.getJogo());

			Set<Integer> intersectionPrimos = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionPrimos.retainAll(primos.getJogo());

			Set<Integer> intersectionFibonacci = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionFibonacci.retainAll(fibonacci.getJogo());

			Set<Integer> intersectionQuadrado = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionQuadrado.retainAll(quadrado.getJogo());

			Set<Integer> intersectionMultiplosDeTres = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionMultiplosDeTres.retainAll(multiplosDeTres.getJogo());

			Set<Integer> intersectionNumerosImportantes = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionNumerosImportantes.retainAll(numerosImportantes.getJogo());

			List<Integer> lista = new ArrayList<>();
			lista.add(intersectionPares.size());
			lista.add(intersectionPrimos.size());
			lista.add(intersectionFibonacci.size());
			lista.add(intersectionQuadrado.size());
			lista.add(intersectionMultiplosDeTres.size());
			lista.add(intersectionNumerosImportantes.size());
			int maxNumeroFiltroRepetido = 0;

			for (int j = 1; j < 13; j++) {
				maxNumeroFiltroRepetido = (maxNumeroFiltroRepetido > Collections.frequency(lista, j))
						? maxNumeroFiltroRepetido
						: Collections.frequency(lista, j);
			}
			
			///linhas 
			Set<Integer> primeiraLinha = new HashSet<Integer>(todosJogos.get(i).getJogo());
			primeiraLinha.retainAll(estatisticas.buscarNumerosLinha1().getJogo());

			Set<Integer> segundaLinha = new HashSet<Integer>(todosJogos.get(i).getJogo());
			segundaLinha.retainAll(estatisticas.buscarNumerosLinha2().getJogo());

			Set<Integer> terceiraLinha = new HashSet<Integer>(todosJogos.get(i).getJogo());
			terceiraLinha.retainAll(estatisticas.buscarNumerosLinha3().getJogo());

			Set<Integer> quartaLinha = new HashSet<Integer>(todosJogos.get(i).getJogo());
			quartaLinha.retainAll(estatisticas.buscarNumerosLinha4().getJogo());

			Set<Integer> quintaLinha = new HashSet<Integer>(todosJogos.get(i).getJogo());
			quintaLinha.retainAll(estatisticas.buscarNumerosLinha5().getJogo());
			
			//colunas
			Set<Integer> primeiraColuna = new HashSet<Integer>(todosJogos.get(i).getJogo());
			primeiraColuna.retainAll(estatisticas.buscarNumerosColuna1().getJogo());

			Set<Integer> segundaColuna = new HashSet<Integer>(todosJogos.get(i).getJogo());
			segundaColuna.retainAll(estatisticas.buscarNumerosColuna2().getJogo());

			Set<Integer> terceiraColuna = new HashSet<Integer>(todosJogos.get(i).getJogo());
			terceiraColuna.retainAll(estatisticas.buscarNumerosColuna3().getJogo());

			Set<Integer> quartaColuna = new HashSet<Integer>(todosJogos.get(i).getJogo());
			quartaColuna.retainAll(estatisticas.buscarNumerosColuna4().getJogo());

			Set<Integer> quintaColuna = new HashSet<Integer>(todosJogos.get(i).getJogo());
			quintaColuna.retainAll(estatisticas.buscarNumerosColuna5().getJogo());
			
			// linhas em sequência
			List<Integer> linhas = new ArrayList<>();
			
			Set<Integer> l1 = new HashSet<Integer>(linha1.getJogo());
			l1.retainAll(todosJogos.get(i).getJogo());
			
			linhas.add(l1.size());
			
			Set<Integer> l2 = new HashSet<Integer>(linha2.getJogo());
			l2.retainAll(todosJogos.get(i).getJogo());
			
			linhas.add(l2.size());
			
			Set<Integer> l3 = new HashSet<Integer>(linha3.getJogo());
			l3.retainAll(todosJogos.get(i).getJogo());
			
			linhas.add(l3.size());
			
			Set<Integer> l4 = new HashSet<Integer>(linha4.getJogo());
			l4.retainAll(todosJogos.get(i).getJogo());
			
			linhas.add(l4.size());
			
			Set<Integer> l5 = new HashSet<Integer>(linha5.getJogo());
			l5.retainAll(todosJogos.get(i).getJogo());
			
			linhas.add(l5.size());
			
			Collections.sort (linhas,Collections.reverseOrder());
			
			// colunas em sequência
			List<Integer> colunas = new ArrayList<>();
			
			Set<Integer> c1 = new HashSet<Integer>(coluna1.getJogo());
			c1.retainAll(todosJogos.get(i).getJogo());
			
			colunas.add(c1.size());
			
			Set<Integer> c2 = new HashSet<Integer>(coluna2.getJogo());
			c2.retainAll(todosJogos.get(i).getJogo());
			
			colunas.add(c2.size());
			
			Set<Integer> c3 = new HashSet<Integer>(coluna3.getJogo());
			c3.retainAll(todosJogos.get(i).getJogo());
			
			colunas.add(c3.size());
			
			Set<Integer> c4 = new HashSet<Integer>(coluna4.getJogo());
			c4.retainAll(todosJogos.get(i).getJogo());
			
			colunas.add(c4.size());
			
			Set<Integer> c5 = new HashSet<Integer>(coluna5.getJogo());
			c5.retainAll(todosJogos.get(i).getJogo());
			
			colunas.add(c5.size());
			
			Collections.sort (colunas,Collections.reverseOrder());
			
		
			if (gerarAquivo) {
				resultCSV.add((i + 1) + "#Repetidos#" + intersectionRepetidos.size() + "#Jogo#"
						+ todosJogos.get(i).getJogo() + "#Saiu?#" + estatSorteio(todosJogos.get(i), i) + "#Pares#"
						+ intersectionPares.size() + "#Primos#" + intersectionPrimos.size() + "#Fibonacci#"
						+ intersectionFibonacci.size() + "#Quadrado#" + intersectionQuadrado.size()
						+ "#Multiplos de Tres#" + intersectionMultiplosDeTres.size() 
						//+ "#Dez Mais#" + intersectionDezMais.size() 
						+ "#Números Importantes#" + intersectionNumerosImportantes.size()
						+ "#Filtros Repetidos#" + maxNumeroFiltroRepetido + "#Dois em Dois#"
						+ sequenciaDoisEmDois(todosJogos.get(i).getJogo()) + "#Um em Um#"
						+ sequenciaUmEmUm(todosJogos.get(i).getJogo()) + "#Linhas#" + primeiraLinha.size() + " - "
						+ segundaLinha.size() + " - " + terceiraLinha.size() + " - " + quartaLinha.size() + " - "
						+ quintaLinha.size() + "#Colunas#" + primeiraColuna.size() + " - " + segundaColuna.size()
						+ " - " + terceiraColuna.size() + " - " + quartaColuna.size() + " - " + quintaColuna.size()
						+ "#Seq Linhas#" + linhas.toString().replace("[", "").replace("]","").replace(", ","")
						+ "#Seq Colunas#" + colunas.toString().replace("[", "").replace("]","").replace(", ",""));
			} else {

				System.out.println(
						// "Jogo: " + todosJogos.get(i).getJogo() +
						" Repetidos: " + intersectionRepetidos.size() + "; Pares -> " + intersectionPares.size()
								+ "; Primos -> " + intersectionPrimos.size() + "; Fibonacci -> "
								+ intersectionFibonacci.size() + "; Quadrado -> " + intersectionQuadrado.size()
								+ "; Multiplos de TrÃªs -> " + intersectionNumerosImportantes.size() + "; Dez Mais -> "
							//	+ intersectionDezMais.size() + "; Linhas -> " + primeiraLinha.size() + ""
								+ segundaLinha.size() + "" + terceiraLinha.size() + "" + quartaLinha.size() + ""
								+ quintaLinha.size() + "; Colunas -> " + primeiraColuna.size() + ""
								+ segundaColuna.size() + "" + terceiraColuna.size() + "" + quartaColuna.size() + ""
								+ quintaColuna.size());

			}

		}
		resultCSV.add(estatisticas.estatisticasJogos(todosJogos.size()).toString());
		resultCSV.add(estatisticas.estatisticasJogosQtdSeguidos(todosJogos.size()).toString());

		if (gerarAquivo) {
			System.out.println("gerando arquivo...");
			Utils.gerarCSV(resultCSV, "estatÃ­sticas");
		} else {
			for (String string : resultCSV) {
				System.out.println(string);
			}
		}

	}

	public List<Estatisticas> listaEstatisticasUltimosSorteio(int qtdJogos, boolean gerarAquivo)
			throws FileNotFoundException, IOException {

		System.out.println("gerando estatíisticas dos últimos jogos...");
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();
		List<Jogo> todosJogos = estatisticas.lerTodosOsJogos();
		Jogo pares = estatisticas.buscarNumerosPares();
		Jogo primos = estatisticas.buscarNumerosPrimos();
		Jogo fibonacci = estatisticas.buscarNumerosSequenciaDeFibonacci();
		Jogo quadrado = estatisticas.buscarNumerosQuadrado();
		Jogo multiplosDeTres = estatisticas.buscarNumerosMultiplosDeTres();

		List<String> resultCSV = new ArrayList<String>();
		for (int i = todosJogos.size() - (qtdJogos); i < todosJogos.size(); i++) {
			Set<Integer> intersectionRepetidos = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionRepetidos.retainAll(todosJogos.get(i - 1).getJogo());

			Set<Integer> intersectionPares = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionPares.retainAll(pares.getJogo());

			Set<Integer> intersectionPrimos = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionPrimos.retainAll(primos.getJogo());

			Set<Integer> intersectionFibonacci = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionFibonacci.retainAll(fibonacci.getJogo());

			Set<Integer> intersectionQuadrado = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionQuadrado.retainAll(quadrado.getJogo());

			Set<Integer> intersectionMultiplosDeTres = new HashSet<Integer>(todosJogos.get(i).getJogo());
			intersectionMultiplosDeTres.retainAll(multiplosDeTres.getJogo());

			Map<Integer, Integer> mapaEstatisticasJogos = estatisticas.estatisticasJogos(i);

//			Set<Integer> intersectionDezMais = new HashSet<Integer>(todosJogos.get(i).getJogo());
//			intersectionDezMais.retainAll(buscarDezMais(todosJogos,qtdJogos ).getJogo());

			Set<Integer> primeiraLinha = new HashSet<Integer>(todosJogos.get(i).getJogo());
			primeiraLinha.retainAll(estatisticas.buscarNumerosLinha1().getJogo());

			Set<Integer> segundaLinha = new HashSet<Integer>(todosJogos.get(i).getJogo());
			segundaLinha.retainAll(estatisticas.buscarNumerosLinha2().getJogo());

			Set<Integer> terceiraLinha = new HashSet<Integer>(todosJogos.get(i).getJogo());
			terceiraLinha.retainAll(estatisticas.buscarNumerosLinha3().getJogo());

			Set<Integer> quartaLinha = new HashSet<Integer>(todosJogos.get(i).getJogo());
			quartaLinha.retainAll(estatisticas.buscarNumerosLinha4().getJogo());

			Set<Integer> quintaLinha = new HashSet<Integer>(todosJogos.get(i).getJogo());
			quintaLinha.retainAll(estatisticas.buscarNumerosLinha5().getJogo());

			Set<Integer> primeiraColuna = new HashSet<Integer>(todosJogos.get(i).getJogo());
			primeiraColuna.retainAll(estatisticas.buscarNumerosColuna1().getJogo());

			Set<Integer> segundaColuna = new HashSet<Integer>(todosJogos.get(i).getJogo());
			segundaColuna.retainAll(estatisticas.buscarNumerosColuna2().getJogo());

			Set<Integer> terceiraColuna = new HashSet<Integer>(todosJogos.get(i).getJogo());
			terceiraColuna.retainAll(estatisticas.buscarNumerosColuna3().getJogo());

			Set<Integer> quartaColuna = new HashSet<Integer>(todosJogos.get(i).getJogo());
			quartaColuna.retainAll(estatisticas.buscarNumerosColuna4().getJogo());

			Set<Integer> quintaColuna = new HashSet<Integer>(todosJogos.get(i).getJogo());
			quintaColuna.retainAll(estatisticas.buscarNumerosColuna5().getJogo());

			Estatisticas estatistica = new Estatisticas();

			estatistica.setNumeroSorteio(String.valueOf(i + 1));
			estatistica.setJogo(todosJogos.get(i));
			estatistica.setRepetidos(String.valueOf(intersectionRepetidos.size()));
			estatistica.setPares(String.valueOf(intersectionPares.size()));
			estatistica.setPrimos(String.valueOf(intersectionPrimos.size()));
			estatistica.setFibonacci(String.valueOf(intersectionFibonacci.size()));
			estatistica.setQuadrado(String.valueOf(intersectionQuadrado.size()));
			estatistica.setMultiplosDeTres(String.valueOf(intersectionMultiplosDeTres.size()));
		//	estatistica.setDezMais(String.valueOf(intersectionDezMais.size()));
			estatistica.setLinhas(String.valueOf(primeiraLinha.size() + "-" + segundaLinha.size() + "-"
					+ terceiraLinha.size() + "-" + quartaLinha.size() + "-" + quintaLinha.size()));
			estatistica.setColunas(String.valueOf(primeiraColuna.size() + "-" + segundaColuna.size() + "-"
					+ terceiraColuna.size() + "-" + quartaColuna.size() + "-" + quintaColuna.size())

			);
			listaEstatisticas.add(estatistica);
		}
		return listaEstatisticas;
	}

	public int sequenciaDoisEmDois(List<Integer> jogo) {

		int cont = 0;
		int numero = 0;
		int maiorSequencia = 0;
		for (Integer n : jogo) {

			if (numero != 0) {
				if (numero + 2 == n) {
					cont++;
					if (maiorSequencia < cont) {
						maiorSequencia = cont;
					}
				} else {
					cont = 0;
				}

			}
			numero = n;

		}
		return maiorSequencia + 1;

	}
	
	public String sequenciaUmEmUm(List<Integer> jogo) {

		int cont = 0;
		int numero = 0;
		int maiorSequencia = 0;
		List<Integer> sequencias = new ArrayList<>();
		for (Integer n : jogo) {

			if (numero != 0) {
				if (numero + 1 == n) {
					cont++;
//					if (maiorSequencia < cont) {
//						maiorSequencia = cont;
//					}
				} else {
					sequencias.add(cont + 1);
					cont = 0;
				}

			}
			numero = n;
		}
		sequencias.add(cont + 1);
		return sequencias.toString();

	}

//	public int sequenciaUmEmUm(List<Integer> jogo) {
//
//		int cont = 0;
//		int numero = 0;
//		int maiorSequencia = 0;
//		for (Integer n : jogo) {
//
//			if (numero != 0) {
//				if (numero + 1 == n) {
//					cont++;
//					if (maiorSequencia < cont) {
//						maiorSequencia = cont;
//					}
//				} else {
//					cont = 0;
//				}
//
//			}
//			numero = n;
//
//		}
//		return maiorSequencia + 1;
//
//	}

}
