package br.com.sorteio.lotofacil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import br.com.sorteio.combinacoes.Combinacao;
import br.com.sorteio.numeros.Jogo;
import br.com.sorteio.util.Estatisticas;

public class FiltroJogos extends Filtro {

	private List<Jogo> primeiraLista;
	private List<Jogo> segundaLista;
	private static List<Jogo> listaJogosPorDemanda;
	final int ultimoJogo = 1;
	final int pNultimoJogo = 2;
	final int noveJogosAtras = 9;
	List<Jogo> listaCheia;

	public static void iniciaListaJogosPorDemanda() {
		FiltroJogos.listaJogosPorDemanda = new ArrayList<Jogo>();
	}

	public List<Jogo> listaJogosPorDemanda() {
		return FiltroJogos.listaJogosPorDemanda;
	}

	private static List<Jogo> listaTodosJogos;

	public static List<Jogo> getListaTodosJogos() {
		return listaTodosJogos;
	}

	public static void setListaTodosJogos(List<Jogo> listaTodosJogos) {
		FiltroJogos.listaTodosJogos = listaTodosJogos;
	}

	public static void setJogolistaJogosPorDemanda(Jogo jogoPorDemanda) {
		FiltroJogos.listaJogosPorDemanda.add(jogoPorDemanda);
	}

	public static void setAllJogolistaJogosPorDemanda(List<Jogo> jogosPorDemanda) {
		FiltroJogos.listaJogosPorDemanda.addAll(jogosPorDemanda);
	}

	@Override
	public void filtrar() throws FileNotFoundException, IOException {
		listaCheia = new ArrayList<Jogo>();
		listaCheia.addAll(getListaDeJogos());
		// eQTDRepetidos(9,9);
		// a verificar
		// tercaParte();
		// intersecao();
		// atrasos();
		// dicaDeOuro();
		////

		// a verificando
		cruz(3, 8);// {2=5, 3=75, 4=243, 5=493, 6=451, 7=218, 8=53, 9=3}
		x(3, 8); // {2=8, 3=65, 4=269, 5=465, 6=473, 7=213, 8=47, 9=1}
		quadrado(7, 12); // {6=2, 7=52,8=199, 9=450, 10=483, 11=261, 12=88,13=6}
		multiplosDeTres(3, 7); // {1=1, 2=33, 3=193, 4=430, 5=541, 6=317, 7=111,8=14}
		sequenciaDeFibonacci(2, 6); // {1=8, 2=85, 3=330, 4=567,5=453,6=182,7=15}
		primo(3, 8); // {2=5, 3=76, 4=278, 5=520, 6=451, 7=257, 8=62, 9=4}
		dentro(3, 7); // {2=6, 3=89, 4=281, 5=522, 6=484, 7=212, 8=57, 9=2}
		pares(5, 10); // {3=3, 4=21, 5=117, 6=339, 7=516, 8=429, 9=182, 10=41, 11=5}
		grupo20a25(2, 5); // 2=187;3=524;4=592;5=277 ****
		grupo8a11(); // 180=4;612=3;571=2;261=1
		cantos();
		linha();
		coluna();
		numerosImportantes(3, 8); // {1=2,2=9,3=80,4=276,5=492,6=498,7=230,8=49,9=3}
		soma();
		posicoes();
		verificaNumerosPrioritarios();
		numerosAnteriores();
		gruposQuintos();
		PrimeiroESegundoGrupos();
		sequencia();
		////

		
		// imparesSeguindos();
		// paresSeguindos();

		revisarJogosEliminados();

		// retirar();

	}

	private void revisarJogosEliminados() {

		int limite = 17;
		int quantidadeDeFiltros = 21;
		int cont = 0;

		if (listaJogosPorDemanda.size() < 10){
			limite--; quantidadeDeFiltros--;
			limite--; quantidadeDeFiltros--;
		}
		if (listaJogosPorDemanda.size() < 9){
			limite--; quantidadeDeFiltros--;
		}
		if (listaJogosPorDemanda.size() < 8){
			limite--; quantidadeDeFiltros--;
		}

		for (Jogo jogo : listaCheia) {
			if (jogo.buscaElimina() > limite && jogo.buscaElimina() < quantidadeDeFiltros) {
				cont++;
				getListaDeJogos().add(jogo);
			}

		}
		System.out.println("Tamaho da lista depois de revisor os jogos eliminados..." + getListaDeJogos().size()
				+ " somados: " + cont);
	}

	public void filtraBoolean(Jogo jogo) throws FileNotFoundException, IOException {

		int cont = 0;

		if (eCruz(2, 9, jogo)) {
			cont++;// {2=5, 3=75, 4=243, 5=493, 6=451,
			// System.out.println("eCruz"); // 7=218, 8=53, 9=3}

		}
		if (eX(2, 9, jogo)) {
			cont++; // {2=8, 3=65, 4=269, 5=465,
					// 6=473, 7=213, 8=47, 9=1}
					// 7=218, 8=53, 9=3}
			// System.out.println("ex");
		}
		if (eQuadrado(7, 12, jogo)) {
			cont++; // {6=2, 7=52,8=199,
					// 9=450, 10=483,
					// 11=261,
					// 12=88,13=6}
			// System.out.println("equadrado");

		}
		if (eMultiplosDeTres(1, 8, jogo)) {
			cont++; // {1=1, 2=33,
					// 3=193, 4=430,
					// 5=541, 6=317,
					// 7=111,8=14}
			// System.out.println("eMultiplosDeTres");
		}
		if (eSequenciaDeFibonacci(1, 7, jogo)) {
			cont++; // {1=8,
					// 2=85,
					// 3=330,
					// 4=567,5=453,
					// 6=182,7=15}
			// System.out.println("eSequenciaDeFibonacci");
		}
		if (eCantos(jogo)) {
			cont++;
			// System.out.println("eCantos");
		}
		if (eSoma(jogo)) {
			cont++;
			// System.out.println("eSoma");
		}
		if (ePosicoes(jogo)) {
			cont++;
			// System.out.println("ePosicoes");
		}

		if (ePrimeiroEUltimo(jogo)) {
			cont++;
			// System.out.println("ePrimeiroEUltimo");
		}
		if (eVerificaNumerosPrioritarios(jogo)) {
			cont++;
			// System.out.println("eVerificaNumerosPrioritarios");
		}
		if (eNumerosAnteriores(jogo)) {
			cont++;
			// System.out.println("eNumerosAnteriores");
		}
		if (eNumerosImportantes(1, 9, jogo)) {
			cont++; // {1=2,2=9,3=80,4=276,5=492,6=498,7=230,8=49,9=3}
			// System.out.println("eNumerosImportantes");
		}
		if (ePrimo(3, 8, jogo)) {
			cont++; // {2=5, 3=76, 4=278,
					// 5=520, 6=451, 7=257,
					// 8=62, 9=4}
			// System.out.println("ePrimo");
		}
		if (eDentro(3, 8, jogo)) {
			cont++;// {2=6, 3=89, 4=281,
					// 5=522, 6=484, 7=212,
					// 8=57, 9=2}
			// System.out.println("eDentro");
		}
		if (ePares(5, 9, jogo)) {
			cont++; // {3=3, 4=21, 5=117,
					// 6=339, 7=516, 8=429,
					// 9=182, 10=41, 11=5}
			// System.out.println("ePares");
		}
		if (eGrupo20a25(2, 5, jogo)) {
			cont++; // 2=187;3=524;4=592;5=277
					// ****
			// System.out.println("eGrupo20a25");
		}
		if (eGrupo8a11(jogo)) {
			cont++;// 180=4;612=3;571=2;261=1
			// System.out.println("eGrupo8a11");
		}
		if (eLinha(jogo)) {
			cont++;
			// System.out.println("eLinha");
		}
		if (eColuna(jogo)) {
			cont++;
			// System.out.println("eColuna");
		}
		if (eSequencia(jogo)) {
			cont++;
			// System.out.println("eSequencia");

		}

		// if (eQTDRepetidos(9, 9, jogo)) {
		// cont++;

		// }

		// if(eGruposQuintos(jogo)){
		// cont++;
		// }

		System.out.println(cont);

	}

	public void filtraListaBoolean() throws FileNotFoundException, IOException {

		int limite = 17;
		boolean eMenorQueDez = listaJogosPorDemanda.size() < 10;

		if (eMenorQueDez) {
			limite = limite - 2;
		}

		List<Jogo> listaAuxiliar = new ArrayList<Jogo>();
		List<Jogo> numResult = new ArrayList<Jogo>();
		listaAuxiliar.addAll(getListaDeJogos());
		numResult.addAll(listaAuxiliar);
		for (Jogo jogo : listaAuxiliar) {
			int cont = 0;

			if (eCruz(2, 9, jogo)) {
				cont++;// {2=5, 3=75, 4=243, 5=493, 6=451,
				// System.out.println("eCruz"); // 7=218, 8=53, 9=3}

			}
			if (eX(2, 9, jogo)) {
				cont++; // {2=8, 3=65, 4=269, 5=465,
						// 6=473, 7=213, 8=47, 9=1}
						// 7=218, 8=53, 9=3}
				// System.out.println("eX");
			}
			if (eQuadrado(7, 12, jogo)) {
				cont++; // {6=2, 7=52,8=199,
						// 9=450, 10=483,
						// 11=261,
						// 12=88,13=6}
				// System.out.println("eQuadrado");
			}
			if (eMultiplosDeTres(1, 8, jogo)) {
				cont++; // {1=1, 2=33,
						// 3=193, 4=430,
						// 5=541, 6=317,
						// 7=111,8=14}
				// System.out.println("eMultiplosDeTres");
			}
			if (eSequenciaDeFibonacci(1, 7, jogo)) {
				cont++; // {1=8,
						// 2=85,
						// 3=330,
						// 4=567,5=453,
						// 6=182,7=15}
				// System.out.println("eSequenciaDeFibonacci");
			}
			if (eCantos(jogo)) {
				cont++;
				// System.out.println("eCantos");
			}
			if (eSoma(jogo)) {
				cont++;
				// System.out.println("eSoma");
			}
			if (ePosicoes(jogo)) {
				cont++;
				// System.out.println("ePosicoes");
			}
			if (ePrimeiroEUltimo(jogo)) {
				cont++;
				// System.out.println("ePrimeiroEUltimo");
			}

			if (!eMenorQueDez) {
				if (eVerificaNumerosPrioritarios(jogo)) {
					cont++;
					// System.out.println("eVerificaNumerosPrioritarios");
				}
			}
			if (!eMenorQueDez) {
				if (eNumerosAnteriores(jogo)) {
					cont++;
					// System.out.println("eNumerosAnteriores");
				}
			}
			if (eNumerosImportantes(1, 9, jogo)) {
				cont++; // {1=2,2=9,3=80,4=276,5=492,6=498,7=230,8=49,9=3}
				// System.out.println("eNumerosImportantes");
			}

			if (ePrimo(3, 8, jogo)) {
				cont++; // {2=5, 3=76, 4=278,
						// 5=520, 6=451, 7=257,
						// 8=62, 9=4}
				// System.out.println("ePrimo");
			}
			if (eDentro(3, 8, jogo)) {
				cont++;// {2=6, 3=89, 4=281,
						// 5=522, 6=484, 7=212,
						// 8=57, 9=2}
				// System.out.println("eDentro");
			}
			if (ePares(5, 9, jogo)) {
				cont++; // {3=3, 4=21, 5=117,
						// 6=339, 7=516, 8=429,
						// 9=182, 10=41, 11=5}
				// System.out.println("ePares");
			}
			if (eGrupo20a25(2, 5, jogo)) {
				cont++; // 2=187;3=524;4=592;5=277
						// ****
				// System.out.println("eGrupo20a25");
			}
			if (eGrupo8a11(jogo)) {
				cont++;// 180=4;612=3;571=2;261=1
				// System.out.println("eGrupo8a11");
			}
			if (eLinha(jogo)) {
				cont++;
				// System.out.println("eLinha");
			}
			if (eColuna(jogo)) {
				cont++;
				// System.out.println("eColuna");
			}
			if (eSequencia(jogo)) {
				cont++;
				// System.out.println("eSequencia");
			}
			if (cont > limite && !eRepetido(jogo)) {
				numResult.add(jogo);
			}

		}

	}

	@Override
	public void combinacoes() {
		List<Jogo> combinacoes = new ArrayList<Jogo>();
		for (Jogo n1 : primeiraLista) {
			for (Jogo n2 : segundaLista) {
				combinacoes.add(new Jogo(union(n1.getJogo(), n2.getJogo())));
			}
		}
		setListaDeJogos(combinacoes);
	}

	public FiltroJogos(List<Jogo> primeiraLista, List<Jogo> segundaLista) {
		this.primeiraLista = primeiraLista;
		this.segundaLista = segundaLista;
		combinacoes();

	}

	public FiltroJogos() {
		iniciaLista();
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

	public void sequenciaold() {
		System.out.println("Filtrando posições sequências... ");
		List<Jogo> listaAuxiliar = new ArrayList<Jogo>();
		List<Jogo> numResult = new ArrayList<Jogo>();
		listaAuxiliar.addAll(getListaDeJogos());
		numResult.addAll(listaAuxiliar);
		int numeroAnterior = 0;
		int contSeq = 1;
		for (Jogo jogo : listaAuxiliar) {

			for (Integer numero : jogo.getJogo()) {

				if (numeroAnterior > 0) {
					if (numero == numeroAnterior + 1) {

					} else {

						if (contSeq > 6) {
							// System.out.println(jogo.getJogo());
							numResult.remove(jogo);
							break;
						}
						contSeq = 1;
					}
					contSeq++;
				}
				numeroAnterior = numero;
			}
			if (contSeq > 6) {
				// System.out.println(jogo.getJogo());
				numResult.remove(jogo);
			}
			contSeq = 1;
		}

		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamamho da lista depois de filtrar as sequências... " + getListaDeJogos().size());

	}

	public void sequencia() {
		System.out.println("Filtrando posições sequências... ");
		List<Jogo> listaAuxiliar = new ArrayList<Jogo>();
		List<Jogo> numResult = new ArrayList<Jogo>();
		listaAuxiliar.addAll(getListaDeJogos());
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		int cont = 0;

		for (Jogo jogo : listaAuxiliar) {
			// listaCheia.add(jogo);

			if

			((jogo.getJogo().get(0) == 1 && jogo.getJogo().get(6) == 7)
					|| (jogo.getJogo().get(0) == 2 && jogo.getJogo().get(6) == 8)
					|| (jogo.getJogo().get(0) == 3 && jogo.getJogo().get(6) == 9)
					|| (jogo.getJogo().get(0) == 4 && jogo.getJogo().get(6) == 10)
					|| (jogo.getJogo().get(0) == 5 && jogo.getJogo().get(6) == 11)
					|| (jogo.getJogo().get(0) == 6 && jogo.getJogo().get(6) == 12)
					|| (jogo.getJogo().get(0) == 7 && jogo.getJogo().get(6) == 13)
					|| (jogo.getJogo().get(0) == 8 && jogo.getJogo().get(6) == 14)
					|| (jogo.getJogo().get(0) == 9 && jogo.getJogo().get(6) == 15)
					|| (jogo.getJogo().get(0) == 10 && jogo.getJogo().get(6) == 16)
					|| (jogo.getJogo().get(0) == 11 && jogo.getJogo().get(6) == 17)
					|| (jogo.getJogo().get(0) == 12 && jogo.getJogo().get(6) == 18)
					|| (jogo.getJogo().get(0) == 13 && jogo.getJogo().get(6) == 19)
					|| (jogo.getJogo().get(0) == 14 && jogo.getJogo().get(6) == 120)
					|| (jogo.getJogo().get(0) == 15 && jogo.getJogo().get(6) == 21)
					|| (jogo.getJogo().get(0) == 16 && jogo.getJogo().get(6) == 22)
					|| (jogo.getJogo().get(0) == 17 && jogo.getJogo().get(6) == 23)
					|| (jogo.getJogo().get(0) == 18 && jogo.getJogo().get(6) == 24)
					|| (jogo.getJogo().get(0) == 19 && jogo.getJogo().get(6) == 25)) {
				cont++;
			}

			if

			((jogo.getJogo().get(1) == 2 && jogo.getJogo().get(7) == 8)
					|| (jogo.getJogo().get(1) == 3 && jogo.getJogo().get(7) == 9)
					|| (jogo.getJogo().get(1) == 4 && jogo.getJogo().get(7) == 10)
					|| (jogo.getJogo().get(1) == 5 && jogo.getJogo().get(7) == 11)
					|| (jogo.getJogo().get(1) == 6 && jogo.getJogo().get(7) == 12)
					|| (jogo.getJogo().get(1) == 7 && jogo.getJogo().get(7) == 13)
					|| (jogo.getJogo().get(1) == 8 && jogo.getJogo().get(7) == 14)
					|| (jogo.getJogo().get(1) == 9 && jogo.getJogo().get(7) == 15)
					|| (jogo.getJogo().get(1) == 10 && jogo.getJogo().get(7) == 16)
					|| (jogo.getJogo().get(1) == 11 && jogo.getJogo().get(7) == 17)
					|| (jogo.getJogo().get(1) == 12 && jogo.getJogo().get(7) == 18)
					|| (jogo.getJogo().get(1) == 13 && jogo.getJogo().get(7) == 19)
					|| (jogo.getJogo().get(1) == 14 && jogo.getJogo().get(7) == 20)
					|| (jogo.getJogo().get(1) == 15 && jogo.getJogo().get(7) == 21)
					|| (jogo.getJogo().get(1) == 16 && jogo.getJogo().get(7) == 22)
					|| (jogo.getJogo().get(1) == 17 && jogo.getJogo().get(7) == 23)
					|| (jogo.getJogo().get(1) == 18 && jogo.getJogo().get(7) == 24)
					|| (jogo.getJogo().get(1) == 19 && jogo.getJogo().get(7) == 25))

			{
				cont++;
			}

			if

			((jogo.getJogo().get(2) == 3 && jogo.getJogo().get(8) == 9)
					|| (jogo.getJogo().get(2) == 4 && jogo.getJogo().get(8) == 10)
					|| (jogo.getJogo().get(2) == 5 && jogo.getJogo().get(8) == 11)
					|| (jogo.getJogo().get(2) == 6 && jogo.getJogo().get(8) == 12)
					|| (jogo.getJogo().get(2) == 7 && jogo.getJogo().get(8) == 13)
					|| (jogo.getJogo().get(2) == 8 && jogo.getJogo().get(8) == 14)
					|| (jogo.getJogo().get(2) == 9 && jogo.getJogo().get(8) == 15)
					|| (jogo.getJogo().get(2) == 10 && jogo.getJogo().get(8) == 16)
					|| (jogo.getJogo().get(2) == 11 && jogo.getJogo().get(8) == 17)
					|| (jogo.getJogo().get(2) == 12 && jogo.getJogo().get(8) == 18)
					|| (jogo.getJogo().get(2) == 13 && jogo.getJogo().get(8) == 19)
					|| (jogo.getJogo().get(2) == 14 && jogo.getJogo().get(8) == 20)
					|| (jogo.getJogo().get(2) == 15 && jogo.getJogo().get(8) == 21)
					|| (jogo.getJogo().get(2) == 16 && jogo.getJogo().get(8) == 22)
					|| (jogo.getJogo().get(2) == 17 && jogo.getJogo().get(8) == 23)
					|| (jogo.getJogo().get(2) == 18 && jogo.getJogo().get(8) == 24)
					|| (jogo.getJogo().get(2) == 19 && jogo.getJogo().get(8) == 25))

			{
				cont++;
			}

			if

			((jogo.getJogo().get(3) == 4 && jogo.getJogo().get(9) == 10)
					|| (jogo.getJogo().get(3) == 5 && jogo.getJogo().get(9) == 11)
					|| (jogo.getJogo().get(3) == 6 && jogo.getJogo().get(9) == 12)
					|| (jogo.getJogo().get(3) == 7 && jogo.getJogo().get(9) == 13)
					|| (jogo.getJogo().get(3) == 8 && jogo.getJogo().get(9) == 14)
					|| (jogo.getJogo().get(3) == 9 && jogo.getJogo().get(9) == 15)
					|| (jogo.getJogo().get(3) == 10 && jogo.getJogo().get(9) == 16)
					|| (jogo.getJogo().get(3) == 11 && jogo.getJogo().get(9) == 17)
					|| (jogo.getJogo().get(3) == 12 && jogo.getJogo().get(9) == 18)
					|| (jogo.getJogo().get(3) == 13 && jogo.getJogo().get(9) == 19)
					|| (jogo.getJogo().get(3) == 14 && jogo.getJogo().get(9) == 20)
					|| (jogo.getJogo().get(3) == 15 && jogo.getJogo().get(9) == 21)
					|| (jogo.getJogo().get(3) == 16 && jogo.getJogo().get(9) == 22)
					|| (jogo.getJogo().get(3) == 17 && jogo.getJogo().get(9) == 23)
					|| (jogo.getJogo().get(3) == 18 && jogo.getJogo().get(9) == 24)
					|| (jogo.getJogo().get(3) == 19 && jogo.getJogo().get(9) == 25))

			{
				cont++;
			}

			if

			((jogo.getJogo().get(4) == 5 && jogo.getJogo().get(10) == 11)
					|| (jogo.getJogo().get(4) == 6 && jogo.getJogo().get(10) == 12)
					|| (jogo.getJogo().get(4) == 7 && jogo.getJogo().get(10) == 13)
					|| (jogo.getJogo().get(4) == 8 && jogo.getJogo().get(10) == 14)
					|| (jogo.getJogo().get(4) == 9 && jogo.getJogo().get(10) == 15)
					|| (jogo.getJogo().get(4) == 10 && jogo.getJogo().get(10) == 16)
					|| (jogo.getJogo().get(4) == 11 && jogo.getJogo().get(10) == 17)
					|| (jogo.getJogo().get(4) == 12 && jogo.getJogo().get(10) == 18)
					|| (jogo.getJogo().get(4) == 13 && jogo.getJogo().get(10) == 19)
					|| (jogo.getJogo().get(4) == 14 && jogo.getJogo().get(10) == 20)
					|| (jogo.getJogo().get(4) == 15 && jogo.getJogo().get(10) == 21)
					|| (jogo.getJogo().get(4) == 16 && jogo.getJogo().get(10) == 22)
					|| (jogo.getJogo().get(4) == 17 && jogo.getJogo().get(10) == 23)
					|| (jogo.getJogo().get(4) == 18 && jogo.getJogo().get(10) == 24)
					|| (jogo.getJogo().get(4) == 19 && jogo.getJogo().get(10) == 25))

			{
				cont++;
			}

			if

			((jogo.getJogo().get(5) == 6 && jogo.getJogo().get(11) == 12)
					|| (jogo.getJogo().get(5) == 7 && jogo.getJogo().get(11) == 13)
					|| (jogo.getJogo().get(5) == 8 && jogo.getJogo().get(11) == 14)
					|| (jogo.getJogo().get(5) == 9 && jogo.getJogo().get(11) == 15)
					|| (jogo.getJogo().get(5) == 10 && jogo.getJogo().get(11) == 16)
					|| (jogo.getJogo().get(5) == 11 && jogo.getJogo().get(11) == 17)
					|| (jogo.getJogo().get(5) == 12 && jogo.getJogo().get(11) == 18)
					|| (jogo.getJogo().get(5) == 13 && jogo.getJogo().get(11) == 19)
					|| (jogo.getJogo().get(5) == 14 && jogo.getJogo().get(11) == 20)
					|| (jogo.getJogo().get(5) == 15 && jogo.getJogo().get(11) == 21)
					|| (jogo.getJogo().get(5) == 16 && jogo.getJogo().get(11) == 22)
					|| (jogo.getJogo().get(5) == 17 && jogo.getJogo().get(11) == 23)
					|| (jogo.getJogo().get(5) == 18 && jogo.getJogo().get(11) == 24)
					|| (jogo.getJogo().get(5) == 19 && jogo.getJogo().get(11) == 25))

			{
				cont++;
			}

			if

			((jogo.getJogo().get(6) == 7 && jogo.getJogo().get(12) == 13)
					|| (jogo.getJogo().get(6) == 8 && jogo.getJogo().get(12) == 14)
					|| (jogo.getJogo().get(6) == 9 && jogo.getJogo().get(12) == 15)
					|| (jogo.getJogo().get(6) == 10 && jogo.getJogo().get(12) == 16)
					|| (jogo.getJogo().get(6) == 11 && jogo.getJogo().get(12) == 17)
					|| (jogo.getJogo().get(6) == 12 && jogo.getJogo().get(12) == 18)
					|| (jogo.getJogo().get(6) == 13 && jogo.getJogo().get(12) == 19)
					|| (jogo.getJogo().get(6) == 14 && jogo.getJogo().get(12) == 20)
					|| (jogo.getJogo().get(6) == 15 && jogo.getJogo().get(12) == 21)
					|| (jogo.getJogo().get(6) == 16 && jogo.getJogo().get(12) == 22)
					|| (jogo.getJogo().get(6) == 17 && jogo.getJogo().get(12) == 23)
					|| (jogo.getJogo().get(6) == 18 && jogo.getJogo().get(12) == 24)
					|| (jogo.getJogo().get(6) == 19 && jogo.getJogo().get(12) == 25))

			{
				cont++;
			}

			if

			((jogo.getJogo().get(7) == 8 && jogo.getJogo().get(13) == 14)
					|| (jogo.getJogo().get(7) == 9 && jogo.getJogo().get(13) == 15)
					|| (jogo.getJogo().get(7) == 10 && jogo.getJogo().get(13) == 16)
					|| (jogo.getJogo().get(7) == 11 && jogo.getJogo().get(13) == 17)
					|| (jogo.getJogo().get(7) == 12 && jogo.getJogo().get(13) == 18)
					|| (jogo.getJogo().get(7) == 13 && jogo.getJogo().get(13) == 19)
					|| (jogo.getJogo().get(7) == 14 && jogo.getJogo().get(13) == 20)
					|| (jogo.getJogo().get(7) == 15 && jogo.getJogo().get(13) == 21)
					|| (jogo.getJogo().get(7) == 16 && jogo.getJogo().get(13) == 22)
					|| (jogo.getJogo().get(7) == 17 && jogo.getJogo().get(13) == 23)
					|| (jogo.getJogo().get(7) == 18 && jogo.getJogo().get(13) == 24)
					|| (jogo.getJogo().get(7) == 19 && jogo.getJogo().get(13) == 25))

			{
				cont++;
			}

			if

			((jogo.getJogo().get(8) == 9 && jogo.getJogo().get(14) == 15)
					|| (jogo.getJogo().get(8) == 10 && jogo.getJogo().get(14) == 16)
					|| (jogo.getJogo().get(8) == 11 && jogo.getJogo().get(14) == 17)
					|| (jogo.getJogo().get(8) == 12 && jogo.getJogo().get(14) == 18)
					|| (jogo.getJogo().get(8) == 13 && jogo.getJogo().get(14) == 19)
					|| (jogo.getJogo().get(8) == 14 && jogo.getJogo().get(14) == 20)
					|| (jogo.getJogo().get(8) == 15 && jogo.getJogo().get(14) == 21)
					|| (jogo.getJogo().get(8) == 16 && jogo.getJogo().get(14) == 22)
					|| (jogo.getJogo().get(8) == 17 && jogo.getJogo().get(14) == 23)
					|| (jogo.getJogo().get(8) == 18 && jogo.getJogo().get(14) == 24)
					|| (jogo.getJogo().get(8) == 19 && jogo.getJogo().get(14) == 25))

			{
				cont++;
			}

			if (cont < 1) {
				jogo.somaElimina();
				numResult.add(jogo);
			}
			cont = 0;
		}

		// numResult.removeAll(listaRemover);
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamamho da lista depois de filtrar as sequências... " + getListaDeJogos().size());

	}

	public void eQTDRepetidos(int maiorQue, int menorQue) {

		System.out.println("Filtrando repetidos do último jogo.. ");
		Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 1);

		List<Jogo> listaAuxiliar = new ArrayList<Jogo>();
		List<Jogo> numResult = new ArrayList<Jogo>();
		listaAuxiliar.addAll(getListaDeJogos());

		for (Jogo n : listaAuxiliar) {
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(n.getJogo());
			int index = intersection.size();
			if (index >= maiorQue && index <= menorQue) {
				numResult.add(n);
			}
		}

		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out
				.println("Tamamho da lista depois de filtrar repetidos do úlitmo jogo... " + getListaDeJogos().size());

	}

	public FiltroJogos(List<Jogo> jogos) {
		setListaDeJogos(jogos);
	}

	public void posicoes() {
		/*
		 * posição 1 [1,2,3] posição 2 [2 a 5] posição 3 [3 a 7] posição 4 [4 a
		 * 9] posição 5 [5 a 11] posição 6 [6 a 13] posição 7 [8 a 15] posição 8
		 * [9 a 17] posição 9 [11 a 18] posição 10 [13 a 19] posição 11 [15 a
		 * 21] posição 12 [17 a 22] posição 13 [19 a 23] posição 14 [21 a 24]
		 * posição 15 [23 a 25]
		 */

		System.out.println("Filtrando posições dos números.. ");

		List<Jogo> listaAuxiliar = new ArrayList<Jogo>();
		List<Jogo> numResult = new ArrayList<Jogo>();
		listaAuxiliar.addAll(getListaDeJogos());
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxiliar) {
			// listaCheia.add(jogo);

			if ((jogo.getJogo().get(0) < 4) && (jogo.getJogo().get(1) > 1 && jogo.getJogo().get(1) < 6)
					&& (jogo.getJogo().get(2) > 2 && jogo.getJogo().get(2) < 8)
					&& (jogo.getJogo().get(3) > 3 && jogo.getJogo().get(3) < 10)
					&& (jogo.getJogo().get(4) > 4 && jogo.getJogo().get(4) < 12)
					&& (jogo.getJogo().get(5) > 5 && jogo.getJogo().get(5) < 14)
					&& (jogo.getJogo().get(6) > 7 && jogo.getJogo().get(6) < 16)
					&& (jogo.getJogo().get(7) > 8 && jogo.getJogo().get(7) < 18)
					&& (jogo.getJogo().get(8) > 10 && jogo.getJogo().get(8) < 19)
					&& (jogo.getJogo().get(9) > 12 && jogo.getJogo().get(9) < 20)
					&& (jogo.getJogo().get(10) > 14 && jogo.getJogo().get(10) < 22)
					&& (jogo.getJogo().get(11) > 16 && jogo.getJogo().get(11) < 23)
					&& (jogo.getJogo().get(12) > 18 && jogo.getJogo().get(12) < 24)
					&& (jogo.getJogo().get(13) > 20 && jogo.getJogo().get(13) < 25)
					&& (jogo.getJogo().get(14) > 22 && jogo.getJogo().get(14) < 26))

			{

				jogo.somaElimina();
				numResult.add(jogo);

			}

		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamamho da lista depois de filtrar as posições dos números.. " + getListaDeJogos().size());
	}

	public void maximoRepetido() throws FileNotFoundException, IOException {

		System.out.println("filtrando máximo repetidos...");

		List<Jogo> listaAuxiliar = new ArrayList<Jogo>();
		List<Jogo> numResult = new ArrayList<Jogo>();
		listaAuxiliar.addAll(getListaDeJogos());

		List<Jogo> todosJogos = listaJogosPorDemanda;
		int cont = 0;
		int contAdd = 0;

		for (Jogo opcoesJogos : listaAuxiliar) {

			contAdd = 0;

			for (Jogo tj : todosJogos) {
				cont = 0;
				if (opcoesJogos.getJogo().contains(tj.getJogo().get(0))) {
					cont++;
				}
				if (opcoesJogos.getJogo().contains(tj.getJogo().get(1))) {
					cont++;
				}
				if (opcoesJogos.getJogo().contains(tj.getJogo().get(2))) {
					cont++;
				}
				if (opcoesJogos.getJogo().contains(tj.getJogo().get(3))) {
					cont++;
				}
				if (opcoesJogos.getJogo().contains(tj.getJogo().get(4))) {
					cont++;
				}
				if (opcoesJogos.getJogo().contains(tj.getJogo().get(5))) {
					cont++;
				}
				if (opcoesJogos.getJogo().contains(tj.getJogo().get(6))) {
					cont++;
				}
				if (opcoesJogos.getJogo().contains(tj.getJogo().get(7))) {
					cont++;
				}
				if (opcoesJogos.getJogo().contains(tj.getJogo().get(8))) {
					cont++;
				}
				if (opcoesJogos.getJogo().contains(tj.getJogo().get(9))) {
					cont++;
				}
				if (opcoesJogos.getJogo().contains(tj.getJogo().get(10))) {
					cont++;
				}
				if (opcoesJogos.getJogo().contains(tj.getJogo().get(11))) {
					cont++;
				}
				if (opcoesJogos.getJogo().contains(tj.getJogo().get(12))) {
					cont++;
				}
				if (opcoesJogos.getJogo().contains(tj.getJogo().get(13))) {
					cont++;
				}
				if (opcoesJogos.getJogo().contains(tj.getJogo().get(14))) {
					cont++;
				}

				if (cont > 14) {
					contAdd++;
					break;
				}

			}
			if (contAdd < 1) {
				numResult.add(opcoesJogos);
			}

		}

		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar o máximo repetido..." + getListaDeJogos().size());
	}

	// public void linha() {
	//
	// System.out.println("filtrando as linhas...");
	//
	// List<Jogo> linha = new ArrayList<Jogo>();
	// linha.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5)));
	// linha.add(new Jogo(Arrays.asList(6, 7, 8, 9, 10)));
	// linha.add(new Jogo(Arrays.asList(11, 12, 13, 14, 15)));
	// linha.add(new Jogo(Arrays.asList(16, 17, 18, 19, 20)));
	// linha.add(new Jogo(Arrays.asList(21, 22, 23, 24, 25)));
	//
	// HashMap<String, Integer> setLinha = new HashMap<String, Integer>();
	//
	// List<Jogo> listaAuxilar = new ArrayList<Jogo>();
	// listaAuxilar.addAll(getListaDeJogos());
	// List<Jogo> numResult = new ArrayList<Jogo>();
	// int cont;
	// for (Jogo jogo : listaAuxilar) {
	//
	// cont = 0;
	// for (Jogo l : linha) {
	// Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
	// intersection.retainAll(l.getJogo());
	//
	// if (intersection.size() == 0) {
	// cont++;
	// break;
	// }
	// }
	// if (cont == 0) {
	// numResult.add(jogo);
	// }
	//
	// }
	// getListaDeJogos().clear();
	// getListaDeJogos().addAll(numResult);
	//
	// System.out.println("Tamanho da lista depois de filtrar as linhas..." +
	// getListaDeJogos().size());
	//
	// }
	public void linha() {

		System.out.println("filtrando as linhas...");

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);
			int cont = 0;
			if (jogo.getJogo().contains(1) || jogo.getJogo().contains(2) || jogo.getJogo().contains(3)
					|| jogo.getJogo().contains(4) || jogo.getJogo().contains(5)) {
				cont++;
			}

			if (jogo.getJogo().contains(6) || jogo.getJogo().contains(7) || jogo.getJogo().contains(8)
					|| jogo.getJogo().contains(9) || jogo.getJogo().contains(10)) {
				cont++;
			}

			if (jogo.getJogo().contains(11) || jogo.getJogo().contains(12) || jogo.getJogo().contains(13)
					|| jogo.getJogo().contains(14) || jogo.getJogo().contains(15)) {
				cont++;
			}

			if (jogo.getJogo().contains(16) || jogo.getJogo().contains(17) || jogo.getJogo().contains(18)
					|| jogo.getJogo().contains(19) || jogo.getJogo().contains(20)) {
				cont++;
			}

			if (jogo.getJogo().contains(21) || jogo.getJogo().contains(22) || jogo.getJogo().contains(23)
					|| jogo.getJogo().contains(24) || jogo.getJogo().contains(25)) {
				cont++;
			}

			if (cont > 4)

			{
				jogo.somaElimina();
				numResult.add(jogo);

			}

		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);

		System.out.println("Tamanho da lista depois de filtrar as linhas..." + getListaDeJogos().size());

	}

	// public void cantos() {
	//
	// System.out.println("filtrando os cantos...");
	//
	// List<Jogo> canto = new ArrayList<Jogo>();
	// canto.add(new Jogo(Arrays.asList(1, 2, 6, 7))); // 164
	// canto.add(new Jogo(Arrays.asList(4, 5, 9, 10))); // 167
	// canto.add(new Jogo(Arrays.asList(16, 17, 21, 22))); // 166
	// canto.add(new Jogo(Arrays.asList(19, 20, 24, 25))); // 163
	//
	// HashMap<String, Integer> setLinha = new HashMap<String, Integer>();
	//
	// List<Jogo> listaAuxilar = new ArrayList<Jogo>();
	// listaAuxilar.addAll(getListaDeJogos());
	// List<Jogo> numResult = new ArrayList<Jogo>();
	// int cont;
	// for (Jogo jogo : listaAuxilar) {
	//
	// cont = 0;
	// for (Jogo l : canto) {
	// Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
	// intersection.retainAll(l.getJogo());
	//
	// if (intersection.size() == 0) {
	// cont++;
	// // break;
	// }
	// }
	// if (cont == 0) {
	// numResult.add(jogo);
	// }
	//
	// }
	// getListaDeJogos().clear();
	// getListaDeJogos().addAll(numResult);
	// System.out.println("Tamanho da lista depois de filtrar os cantos..." +
	// getListaDeJogos().size());
	//
	// }
	public void cantos() {

		System.out.println("filtrando os cantos...");

		List<Jogo> canto = new ArrayList<Jogo>();

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);
			int cont = 0;
			if (jogo.getJogo().contains(1) || jogo.getJogo().contains(2) || jogo.getJogo().contains(6)
					|| jogo.getJogo().contains(7)) {
				cont++;
			}

			if (jogo.getJogo().contains(4) || jogo.getJogo().contains(5) || jogo.getJogo().contains(9)
					|| jogo.getJogo().contains(10)) {
				cont++;
			}

			if (jogo.getJogo().contains(16) || jogo.getJogo().contains(17) || jogo.getJogo().contains(21)
					|| jogo.getJogo().contains(22)) {
				cont++;
			}

			if (jogo.getJogo().contains(19) || jogo.getJogo().contains(20) || jogo.getJogo().contains(24)
					|| jogo.getJogo().contains(25)) {
				cont++;
			}

			if (cont > 3) {
				jogo.somaElimina();
				numResult.add(jogo);

			}

		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar os cantos..." + getListaDeJogos().size());

	}
	// public void coluna() {
	//
	// System.out.println("filtrando as colunas...");
	//
	// List<Jogo> coluna = new ArrayList<Jogo>();
	// coluna.add(new Jogo(Arrays.asList(1, 6, 11, 16, 21)));
	// coluna.add(new Jogo(Arrays.asList(2, 7, 12, 17, 22)));
	// coluna.add(new Jogo(Arrays.asList(3, 8, 13, 18, 23)));
	// coluna.add(new Jogo(Arrays.asList(4, 9, 14, 19, 24)));
	// coluna.add(new Jogo(Arrays.asList(5, 10, 15, 20, 25)));
	//
	// HashMap<String, Integer> setLinha = new HashMap<String, Integer>();
	//
	// List<Jogo> listaAuxilar = new ArrayList<Jogo>();
	// listaAuxilar.addAll(getListaDeJogos());
	// List<Jogo> numResult = new ArrayList<Jogo>();
	// int cont;
	// for (Jogo jogo : listaAuxilar) {
	//
	// cont = 0;
	// for (Jogo l : coluna) {
	// Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
	// intersection.retainAll(l.getJogo());
	//
	// if (intersection.size() == 0) {
	// cont++;
	// break;
	// }
	// }
	// if (cont == 0) {
	// numResult.add(jogo);
	// }
	//
	// }
	// getListaDeJogos().clear();
	// getListaDeJogos().addAll(numResult);
	// System.out.println("Tamanho da lista depois de filtrar as colunas..." +
	// getListaDeJogos().size());
	//
	// }

	public void coluna() {

		System.out.println("filtrando as colunas...");

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);
			int cont = 0;
			if (jogo.getJogo().contains(1) || jogo.getJogo().contains(6) || jogo.getJogo().contains(11)
					|| jogo.getJogo().contains(16) || jogo.getJogo().contains(21)) {
				cont++;
			}

			if (jogo.getJogo().contains(2) || jogo.getJogo().contains(7) || jogo.getJogo().contains(12)
					|| jogo.getJogo().contains(17) || jogo.getJogo().contains(22)) {
				cont++;
			}

			if (jogo.getJogo().contains(3) || jogo.getJogo().contains(8) || jogo.getJogo().contains(13)
					|| jogo.getJogo().contains(18) || jogo.getJogo().contains(23)) {
				cont++;
			}

			if (jogo.getJogo().contains(4) || jogo.getJogo().contains(9) || jogo.getJogo().contains(14)
					|| jogo.getJogo().contains(19) || jogo.getJogo().contains(24)) {
				cont++;
			}

			if (jogo.getJogo().contains(5) || jogo.getJogo().contains(10) || jogo.getJogo().contains(15)
					|| jogo.getJogo().contains(20) || jogo.getJogo().contains(25)) {
				cont++;
			}

			if (cont > 4)

			{
				jogo.somaElimina();
				numResult.add(jogo);

			}

		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar as colunas..." + getListaDeJogos().size());

	}

	public void soma() {

		System.out.println("Filtrando somas...");

		List<Jogo> numResult = new ArrayList<Jogo>();
		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		Integer soma = 0;

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);
			for (Integer numero : jogo.getJogo()) {
				soma = soma + numero;
			}
			if (soma > 135 && soma < 248) {
				jogo.somaElimina();
				numResult.add(jogo);
			}
			soma = 0;
		}

		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar as somas..." + getListaDeJogos().size());
	}

	public void grupos() {

		System.out.println("filtrando as colunas...");

		List<Jogo> grupos = new ArrayList<Jogo>();

		/*
		 * Grupo 1 : 02,03,04,05,07,11,13,14 (Usar de 4 a 6 dezenas , 5 seriam o
		 * ideal) Grupo 2 : 15,18,19,20,21,22,25 (Usar de 3 a 5 dezenas , 4
		 * seriam o ideal) Grupo 3 : 01,06,08,09,10 (Usar de 2 a 4 dezenas , 3
		 * seriam o ideal) Grupo 4 : 12,16,17,23,24
		 */
		grupos.add(new Jogo(Arrays.asList(2, 3, 4, 5, 7, 11, 13, 14)));
		grupos.add(new Jogo(Arrays.asList(15, 18, 19, 20, 21, 22, 25)));
		grupos.add(new Jogo(Arrays.asList(1, 6, 8, 9, 10)));
		grupos.add(new Jogo(Arrays.asList(12, 16, 17, 23, 24)));

		HashMap<String, Integer> setLinha = new HashMap<String, Integer>();

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		int cont;
		for (Jogo jogo : listaAuxilar) {

			String strLinha = null;
			cont = 0;
			for (Jogo l : grupos) {
				Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
				intersection.retainAll(l.getJogo());

				if (intersection.size() == 0) {
					cont++;
					break;
				}
			}
			if (cont == 0) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar as colunas..." + getListaDeJogos().size());

	}

	public void atrasos() {

		System.out.println("filtrando atrasos...");
		List<Jogo> todosJogosRevertidos = new ArrayList<Jogo>();

		// ListIterator li =
		// listaTodosJogos.listIterator(listaTodosJogos.size());

		// utilizar apenas para simulação
		ListIterator li = listaJogosPorDemanda.listIterator(listaJogosPorDemanda.size());

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();

		while (li.hasPrevious()) {
			todosJogosRevertidos.add((Jogo) li.previous());
		}

		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
				22, 23, 24, 25);

		TreeMap<Integer, Integer> listaEstatAtrasos = new TreeMap<Integer, Integer>();

		listaEstatAtrasos.put(1, 7);
		listaEstatAtrasos.put(2, 7);
		listaEstatAtrasos.put(3, 7);
		listaEstatAtrasos.put(4, 7);
		listaEstatAtrasos.put(5, 7);
		listaEstatAtrasos.put(6, 7);
		listaEstatAtrasos.put(7, 10);
		listaEstatAtrasos.put(8, 7);
		listaEstatAtrasos.put(9, 7);
		listaEstatAtrasos.put(10, 8);
		listaEstatAtrasos.put(11, 6);
		listaEstatAtrasos.put(12, 9);
		listaEstatAtrasos.put(13, 6);
		listaEstatAtrasos.put(14, 7);
		listaEstatAtrasos.put(15, 9);
		listaEstatAtrasos.put(16, 6);
		listaEstatAtrasos.put(17, 8);
		listaEstatAtrasos.put(18, 7);
		listaEstatAtrasos.put(19, 8);
		listaEstatAtrasos.put(20, 7);
		listaEstatAtrasos.put(21, 7);
		listaEstatAtrasos.put(22, 7);
		listaEstatAtrasos.put(23, 6);
		listaEstatAtrasos.put(24, 7);
		listaEstatAtrasos.put(25, 11);

		List<Integer> listaNumerosImportantes = new ArrayList<Integer>();
		int cont = 0;

		for (Integer n : numeros) {
			cont = 0;
			for (Jogo jogo : todosJogosRevertidos) {
				Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
				if (!intersection.contains(n)) {
					cont++;
				} else {

					if (cont >= listaEstatAtrasos.get(n).intValue()) {
						listaNumerosImportantes.add(n);
					}

					cont = 0;
					break;
				}
			}
		}

		Jogo listNumImportantes = new Jogo(listaNumerosImportantes);

		for (Jogo jogo : listaAuxilar) {
			Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
			intersection.retainAll(listNumImportantes.getJogo());

			if (intersection.size() == listaNumerosImportantes.size()) {
				numResult.add(jogo);
			}
		}

		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar atrasos ..." + getListaDeJogos().size());
	}

	public void cruz(int maiorQue, int menorQue) {

		System.out.println("filtrando a cruz...");

		Jogo cruz = new Jogo(Arrays.asList(3, 8, 11, 12, 13, 14, 15, 18, 23));// {2=5,
																				// 3=75,
																				// 4=243,
																				// 5=493,
																				// 6=451,
																				// 7=218,
																				// 8=53,
																				// 9=3}

		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 1);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(cruz.getJogo());
			qtdOcorrencias = intersection.size();
		}

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);

			int cont = 0;
			if (jogo.getJogo().contains(3))
				cont++;
			if (jogo.getJogo().contains(8))
				cont++;
			if (jogo.getJogo().contains(11))
				cont++;
			if (jogo.getJogo().contains(12))
				cont++;
			if (jogo.getJogo().contains(13))
				cont++;
			if (jogo.getJogo().contains(14))
				cont++;
			if (jogo.getJogo().contains(15))
				cont++;
			if (jogo.getJogo().contains(18))
				cont++;
			if (jogo.getJogo().contains(23))
				cont++;

			/*
			 * quando vez 7,8,9 dificilmente vem uma quantidade maior no próximo
			 */
			/* quando vez 2,3,7,8,9 dificilmente se repete */
			/*
			 * quando vez 2,3,4 dificilmente vem uma quantidade menor no próximo
			 */

			if ((qtdOcorrencias >= 8 && qtdOcorrencias <= 9) && cont > qtdOcorrencias) {
				continue;
			}

			if (((qtdOcorrencias >= 2 && qtdOcorrencias <= 2) || (qtdOcorrencias >= 8 && qtdOcorrencias <= 9))
					&& cont == qtdOcorrencias) {
				continue;
			}

			if ((qtdOcorrencias >= 2 && qtdOcorrencias <= 3) && cont < qtdOcorrencias) {
				continue;
			}
			if (cont >= maiorQue && cont <= menorQue) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar a cruz..." + getListaDeJogos().size());

	}

	public void x(int maiorQue, int menorQue) {

		System.out.println("filtrando o x...");

		// {2=8,// 3=65,// 4=269,// 5=465,// 6=473,// 7=213,// 8=47,// 9=1}

		Jogo x = new Jogo(Arrays.asList(1, 5, 7, 9, 13, 17, 19, 21, 25));
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 1);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(x.getJogo());
			qtdOcorrencias = intersection.size();
		}

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);

			int cont = 0;

			if (jogo.getJogo().contains(1))
				cont++;
			if (jogo.getJogo().contains(5))
				cont++;
			if (jogo.getJogo().contains(7))
				cont++;
			if (jogo.getJogo().contains(9))
				cont++;
			if (jogo.getJogo().contains(13))
				cont++;
			if (jogo.getJogo().contains(17))
				cont++;
			if (jogo.getJogo().contains(19))
				cont++;
			if (jogo.getJogo().contains(21))
				cont++;
			if (jogo.getJogo().contains(25))
				cont++;

			/* quando vem o 7,8,9 dificilmente vem maior */
			/* quando vem o 3,8,9 dificilmente igual */
			/* quando vem o 2,3,4 dificilmente vem menor */

			if ((qtdOcorrencias >= 8 && qtdOcorrencias <= 9) && cont > qtdOcorrencias) {
				continue;
			}

			if (((qtdOcorrencias == 3) || (qtdOcorrencias >= 9 && qtdOcorrencias <= 9)) && cont == qtdOcorrencias) {
				continue;
			}

			if ((qtdOcorrencias >= 2 && qtdOcorrencias <= 3) && cont < qtdOcorrencias) {
				continue;
			}

			if (cont >= maiorQue && cont <= menorQue) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar o x..." + getListaDeJogos().size());

	}

	public void quadrado(int maiorQue, int menorQue) {

		System.out.println("filtrando o quadrado...");

		Jogo quadrado = new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 10, 11, 15, 16, 20, 21, 22, 23, 24, 25));
		// cruz.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 10, 11, 15, 16, 20,
		// 21, 22, 23, 24, 25))); // {6=2, 7=52,8=199, 9=450, 10=483, 11=261,
		// 12=88, 13=6}

		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 1);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(quadrado.getJogo());
			qtdOcorrencias = intersection.size();
		}

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);

			int cont = 0;
			if (jogo.getJogo().contains(1))
				cont++;
			if (jogo.getJogo().contains(2))
				cont++;
			if (jogo.getJogo().contains(3))
				cont++;
			if (jogo.getJogo().contains(4))
				cont++;
			if (jogo.getJogo().contains(5))
				cont++;
			if (jogo.getJogo().contains(6))
				cont++;
			if (jogo.getJogo().contains(10))
				cont++;
			if (jogo.getJogo().contains(11))
				cont++;
			if (jogo.getJogo().contains(15))
				cont++;
			if (jogo.getJogo().contains(16))
				cont++;
			if (jogo.getJogo().contains(20))
				cont++;
			if (jogo.getJogo().contains(21))
				cont++;
			if (jogo.getJogo().contains(22))
				cont++;
			if (jogo.getJogo().contains(23))
				cont++;
			if (jogo.getJogo().contains(24))
				cont++;
			if (jogo.getJogo().contains(25))
				cont++;

			/* quando vem o 11,12,13 dificilmente vem maior */
			/* quando vem o 6,7,12,13 dificilmente igual */
			/* quando vem o 6,7,8 dificilmente vem menor */

			if ((qtdOcorrencias >= 12 && qtdOcorrencias <= 13) && cont > qtdOcorrencias) {
				continue;
			}

			if (((qtdOcorrencias >= 6 && qtdOcorrencias <= 6) || (qtdOcorrencias >= 13 && qtdOcorrencias <= 13))
					&& cont == qtdOcorrencias) {
				continue;
			}

			if ((qtdOcorrencias >= 6 && qtdOcorrencias <= 7) && cont < qtdOcorrencias) {
				continue;
			}

			if (cont >= maiorQue && cont <= menorQue) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar o quadrado..." + getListaDeJogos().size());

	}

	public void multiplosDeTres(int maiorQue, int menorQue) {

		System.out.println("filtrando números múltiplos de 3...");

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		Jogo multiplosDeTres = new Jogo(Arrays.asList(3, 6, 9, 12, 15, 18, 21, 24));
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 1);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(multiplosDeTres.getJogo());
			qtdOcorrencias = intersection.size();
		}

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);

			int cont = 0;

			if (jogo.getJogo().contains(3))
				cont++;
			if (jogo.getJogo().contains(6))
				cont++;
			if (jogo.getJogo().contains(9))
				cont++;
			if (jogo.getJogo().contains(12))
				cont++;
			if (jogo.getJogo().contains(15))
				cont++;
			if (jogo.getJogo().contains(18))
				cont++;
			if (jogo.getJogo().contains(21))
				cont++;
			if (jogo.getJogo().contains(24))
				cont++;

			/* quando vem o 6,7,8 dificilmente vem maior */
			/* quando vem o 1,2,3,7,8 dificilmente igual */
			/* quando vem o 1,2,3 dificilmente vem menor */

			if ((qtdOcorrencias >= 7 && qtdOcorrencias <= 8) && cont > qtdOcorrencias) {
				continue;
			}

			if (((qtdOcorrencias >= 1 && qtdOcorrencias <= 2) || (qtdOcorrencias >= 8 && qtdOcorrencias <= 8))
					&& cont == qtdOcorrencias) {
				continue;
			}

			if ((qtdOcorrencias >= 1 && qtdOcorrencias <= 2) && cont < qtdOcorrencias) {
				continue;
			}

			if (cont >= maiorQue && cont <= menorQue) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

		}

		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out
				.println("Tamanho da lista depois de filtrar os números múltiplos de 3..." + getListaDeJogos().size());

	}

	public void sequenciaDeFibonacci(int maiorQue, int menorQue) {

		/* quando vem o 5,6,7 dificilmente vem maior */
		/* quando vem o 1,2,6,7 dificilmente igual */
		/* quando vem o 1,2,3 dificilmente vem menor */

		System.out.println("filtrando números da sequência de Fibonacci...");

		Jogo sequenciaDeFibonacci = new Jogo(Arrays.asList(1, 2, 3, 5, 8, 13, 21));
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 1);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(sequenciaDeFibonacci.getJogo());
			qtdOcorrencias = intersection.size();
		}

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);

			int cont = 0;

			if (jogo.getJogo().contains(1))
				cont++;
			if (jogo.getJogo().contains(2))
				cont++;
			if (jogo.getJogo().contains(3))
				cont++;
			if (jogo.getJogo().contains(5))
				cont++;
			if (jogo.getJogo().contains(8))
				cont++;
			if (jogo.getJogo().contains(13))
				cont++;
			if (jogo.getJogo().contains(21))
				cont++;

			/* quando vem o 5,6,7 dificilmente vem maior */
			/* quando vem o 1,2,6,7 dificilmente igual */
			/* quando vem o 1,2,3 dificilmente vem menor */

			if ((qtdOcorrencias >= 6 && qtdOcorrencias <= 7) && cont > qtdOcorrencias) {
				continue;
			}

			if (((qtdOcorrencias >= 1 && qtdOcorrencias <= 1) || (qtdOcorrencias >= 7 && qtdOcorrencias <= 7))
					&& cont == qtdOcorrencias) {
				continue;
			}

			if ((qtdOcorrencias >= 1 && qtdOcorrencias <= 2) && cont < qtdOcorrencias) {
				continue;
			}

			if (cont >= maiorQue && cont <= menorQue) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

		}

		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar os números  da sequência de Fibonacci..."
				+ getListaDeJogos().size());

	}

	public void linhasEColunas() {

		System.out.println("filtrando as linhas e colunas...");

		List<Jogo> linha = new ArrayList<Jogo>();
		linha.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5)));
		linha.add(new Jogo(Arrays.asList(6, 7, 8, 9, 10)));
		linha.add(new Jogo(Arrays.asList(11, 12, 13, 14, 15)));
		linha.add(new Jogo(Arrays.asList(16, 17, 18, 19, 20)));
		linha.add(new Jogo(Arrays.asList(21, 22, 23, 24, 25)));

		List<Jogo> coluna = new ArrayList<Jogo>();
		coluna.add(new Jogo(Arrays.asList(1, 6, 11, 16, 21)));
		coluna.add(new Jogo(Arrays.asList(2, 7, 12, 17, 22)));
		coluna.add(new Jogo(Arrays.asList(3, 8, 13, 18, 23)));
		coluna.add(new Jogo(Arrays.asList(4, 9, 14, 19, 24)));
		coluna.add(new Jogo(Arrays.asList(5, 10, 15, 20, 25)));

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		int cont;
		int teste = 0;
		for (Jogo jogo : listaAuxilar) {
			teste++;
			if (teste == 2659) {
				System.out.println();
			}
			cont = 0;
			for (Jogo l : linha) {
				Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
				intersection.retainAll(l.getJogo());

				if (intersection.size() == 0) {
					cont++;
				}
			}

			if (cont > 1) {
				break;
			}

			for (Jogo c : coluna) {
				Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
				intersection.retainAll(c.getJogo());

				if (intersection.size() == 0) {
					cont++;
				}
			}

			if (cont < 2) {
				numResult.add(jogo);
			}
		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar as linhas e colunas..." + getListaDeJogos().size());

	}

	public void tercaParte() {

		System.out.println("filtrando a tercaParte...");

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();

		// 6 = 1 a 5
		// 12, 13, 14 = 6 a 10
		// 20 = 11 a 15
		for (Jogo jogo : listaAuxilar) {

			int cont = 0;

			for (int i = 0; i < jogo.getJogo().size(); i++) {
				if (jogo.getJogo().get(i) == 6 && i > 4) {
					cont++;
					break;
				}

				if ((jogo.getJogo().get(i) > 11 && jogo.getJogo().get(i) < 15) && (i < 5 || i > 9)) {
					cont++;
					break;
				}

				if (jogo.getJogo().get(i) == 20 && (i < 10 || i > 14)) {
					cont++;
					break;
				}
			}
			if (cont == 0) {
				numResult.add(jogo);
			}
		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar a tercaParte..." + getListaDeJogos().size());

	}

	public void intersecao() throws FileNotFoundException, IOException {
		System.out.println("filtrando a intersecao...");

		// Estatisticas est = new Estatisticas();
		// List<Jogo> todosJogos = est.lerTodosOsJogos();
		List<Jogo> todosJogos = listaJogosPorDemanda;
		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();

		for (Jogo primeiroJogo : listaAuxilar) {
			TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();
			for (Jogo jogo : todosJogos) {
				Set<Integer> intersection = new HashSet<Integer>(primeiroJogo.getJogo());
				intersection.retainAll(jogo.getJogo());
				int chave = intersection.size();
				if (trmap.containsKey(chave)) {
					int valor = trmap.get(chave).intValue() + 1;
					trmap.put(chave, valor);

				} else {
					trmap.put(chave, 1);
				}
			}
			if (trmap.containsKey(11)) {
				if (trmap.get(11).intValue() >= 104) {
					numResult.add(primeiroJogo);
				}
			}
		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar a intersecao..." + getListaDeJogos().size());
	}

	public List<Integer> numerosPrioritarios() {

		Map<Integer, Integer> trmap = dezUltimosSorteios(listaJogosPorDemanda);

		return MaisEMenosSorteados(trmap, true);

	}

	public void verificaNumerosPrioritarios() {

		if (listaJogosPorDemanda.size() < 10) {
			return;
		}
		System.out.println("filtrando números prioritarios...");

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		List<Integer> numerosPrioritarios = new ArrayList<Integer>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		numerosPrioritarios = numerosPrioritarios();

		int cont = 0;

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);
			cont = 0;

			for (Integer integer : numerosPrioritarios) {
				if (jogo.getJogo().contains(integer)) {
					cont++;
				}

				if (numerosPrioritarios.size() > 3) {
					if (cont > 1) {

						jogo.somaElimina();
						numResult.add(jogo);
						break;
					}
				} else {
					if (cont > 0) {
						jogo.somaElimina();
						numResult.add(jogo);
						break;
					}
				}

			}

		}

		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar os números prioritários..." + getListaDeJogos().size());

	}

	public void numerosAnteriores() {

		if (listaJogosPorDemanda.size() > 8) {

			System.out.println("filtrando números anteriores...");

			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 1);
			Jogo strPenUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 2);
			Jogo strNoveSorteiosAtras = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 9);

			Jogo strNumerosNaoSorteados = strUltimoSorteio.numerosNaoSorteados(strUltimoSorteio);

			Set<Integer> differenceUltimoPenUltimo = new HashSet<Integer>(strPenUltimoSorteio.getJogo());
			differenceUltimoPenUltimo.removeAll(strUltimoSorteio.getJogo());

			Set<Integer> intersectionNoveJogosAtras = new HashSet<Integer>(strNoveSorteiosAtras.getJogo());
			intersectionNoveJogosAtras.retainAll(differenceUltimoPenUltimo);

			List<Jogo> listaAuxilar = new ArrayList<Jogo>();
			listaAuxilar.addAll(getListaDeJogos());
			List<Jogo> numResult = new ArrayList<Jogo>();
			// List<Jogo> listaCheia = new ArrayList<Jogo>();

			int intContDif = 0;
			int intContInt = 0;
			for (Jogo jogo : listaAuxilar) {
				// listaCheia.add(jogo);

				for (Integer numero : differenceUltimoPenUltimo) {
					if (jogo.getJogo().contains(numero)) {
						intContDif++;
					}

				}

				for (Integer numero : intersectionNoveJogosAtras) {
					if (jogo.getJogo().contains(numero)) {
						intContInt++;
					}

				}

				if (intContDif > 1 && intContInt > 0) {
					jogo.somaElimina();
					numResult.add(jogo);
					// System.out.println(jogo.getJogo());
				}

				intContDif = 0;
				intContInt = 0;
			}

			getListaDeJogos().clear();
			getListaDeJogos().addAll(numResult);
			System.out
					.println("Tamanho da lista depois de filtrar os números anteriores..." + getListaDeJogos().size());
		}

	}

	public void numerosImportantes(int maiorQue, int menorQue) {

		System.out.println("filtrando números importantes...");

		Jogo numerosImportantes = new Jogo(Arrays.asList(5, 6, 7, 12, 14, 19, 20, 21));
	/*	int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 1);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(numerosImportantes.getJogo());
			qtdOcorrencias = intersection.size();
		}*/

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);

			int cont = 0;

			if (jogo.getJogo().contains(5))
				cont++;
			if (jogo.getJogo().contains(6))
				cont++;
			if (jogo.getJogo().contains(7))
				cont++;
			if (jogo.getJogo().contains(12))
				cont++;
			if (jogo.getJogo().contains(13))
				cont++;
			if (jogo.getJogo().contains(14))
				cont++;
			if (jogo.getJogo().contains(19))
				cont++;
			if (jogo.getJogo().contains(20))
				cont++;
			if (jogo.getJogo().contains(21))
				cont++;

			/* quando vem o 7,8,9 dificilmente vem maior */
			/* quando vem o 1,2,3,7,8,9 dificilmente igual */
			/* quando vem o 1,2,3,4 dificilmente vem menor */
//
//			if ((qtdOcorrencias >= 8 && qtdOcorrencias <= 9) && cont > qtdOcorrencias) {
//				continue;
//			}
//
//			if (((qtdOcorrencias >= 1 && qtdOcorrencias <= 2) || (qtdOcorrencias >= 8 && qtdOcorrencias <= 9))
//					&& cont == qtdOcorrencias) {
//				continue;
//			}
//
//			if ((qtdOcorrencias >= 1 && qtdOcorrencias <= 3) && cont < qtdOcorrencias) {
//				continue;
//			}

			if (cont >= maiorQue && cont <= menorQue) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

		}

		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar os números  importantes..." + getListaDeJogos().size());

	}

	public void primo(int maiorQue, int menorQue) {

		System.out.println("filtrando números primos...");

		Jogo primo = new Jogo(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23)); //
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 1);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(primo.getJogo());
			qtdOcorrencias = intersection.size();
		}

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);

			int cont = 0;

			if (jogo.getJogo().contains(2))
				cont++;
			if (jogo.getJogo().contains(3))
				cont++;
			if (jogo.getJogo().contains(5))
				cont++;
			if (jogo.getJogo().contains(7))
				cont++;
			if (jogo.getJogo().contains(11))
				cont++;
			if (jogo.getJogo().contains(13))
				cont++;
			if (jogo.getJogo().contains(17))
				cont++;
			if (jogo.getJogo().contains(19))
				cont++;
			if (jogo.getJogo().contains(23))
				cont++;

			/* quando vem o 7,8,9 dificilmente vem maior */
			/* quando vem o 2,3,8,9 dificilmente igual */
			/* quando vem o 1,2,3,4 dificilmente vem menor */

			if ((qtdOcorrencias >= 8 && qtdOcorrencias <= 9) && cont > qtdOcorrencias) {
				continue;
			}

			if (((qtdOcorrencias >= 2 && qtdOcorrencias <= 2) || (qtdOcorrencias >= 9 && qtdOcorrencias <= 9))
					&& cont == qtdOcorrencias) {
				continue;
			}

			if ((qtdOcorrencias >= 1 && qtdOcorrencias <= 3) && cont < qtdOcorrencias) {
				continue;
			}

			if (cont >= maiorQue && cont <= menorQue) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

		}

		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar os números primos..." + getListaDeJogos().size());

	}

	public void dentro(int maiorQue, int menorQue) {

		System.out.println("filtrando números dentro...");

		Jogo dentro = new Jogo(Arrays.asList(7, 8, 9, 12, 13, 14, 17, 18, 19));
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 1);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(dentro.getJogo());
			qtdOcorrencias = intersection.size();
		}

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);

			int cont = 0;

			if (jogo.getJogo().contains(7))
				cont++;
			if (jogo.getJogo().contains(8))
				cont++;
			if (jogo.getJogo().contains(9))
				cont++;
			if (jogo.getJogo().contains(12))
				cont++;
			if (jogo.getJogo().contains(13))
				cont++;
			if (jogo.getJogo().contains(14))
				cont++;
			if (jogo.getJogo().contains(17))
				cont++;
			if (jogo.getJogo().contains(18))
				cont++;
			if (jogo.getJogo().contains(19))
				cont++;

			/* quando vem o 7,8,9 dificilmente vem maior */
			/* quando vem o 2,3,7,8,9 dificilmente igual */
			/* quando vem o 2,3,4 dificilmente vem menor */

			if ((qtdOcorrencias >= 8 && qtdOcorrencias <= 9) && cont > qtdOcorrencias) {
				continue;
			}

			if (((qtdOcorrencias >= 2 && qtdOcorrencias <= 2) || (qtdOcorrencias >= 8 && qtdOcorrencias <= 9))
					&& cont == qtdOcorrencias) {
				continue;
			}

			if ((qtdOcorrencias >= 2 && qtdOcorrencias <= 3) && cont < qtdOcorrencias) {
				continue;
			}

			if (cont >= maiorQue && cont <= menorQue) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

		}

		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar os números dentro..." + getListaDeJogos().size());

	}

	public void pares(int maiorQue, int menorQue) throws FileNotFoundException, IOException {

		System.out.println("Filtrando os números pares...");

		Jogo pares = new Jogo(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24));
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 1);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(pares.getJogo());
			qtdOcorrencias = intersection.size();
		}

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);

			int cont = 0;

			if (jogo.getJogo().contains(2))
				cont++;
			if (jogo.getJogo().contains(4))
				cont++;
			if (jogo.getJogo().contains(6))
				cont++;
			if (jogo.getJogo().contains(8))
				cont++;
			if (jogo.getJogo().contains(10))
				cont++;
			if (jogo.getJogo().contains(12))
				cont++;
			if (jogo.getJogo().contains(14))
				cont++;
			if (jogo.getJogo().contains(16))
				cont++;
			if (jogo.getJogo().contains(18))
				cont++;
			if (jogo.getJogo().contains(20))
				cont++;
			if (jogo.getJogo().contains(22))
				cont++;
			if (jogo.getJogo().contains(24))
				cont++;

			/* quando vem o ... dificilmente vem maior */
			/* quando vem o 3,4,5,9,10,11 dificilmente igual */
			/* quando vem o 3,4,5,6 dificilmente vem menor */

			if (((qtdOcorrencias >= 3 && qtdOcorrencias <= 4) || (qtdOcorrencias >= 10 && qtdOcorrencias <= 11))
					&& cont == qtdOcorrencias) {
				continue;
			}

			if ((qtdOcorrencias >= 3 && qtdOcorrencias <= 5) && cont < qtdOcorrencias) {
				continue;
			}

			if (cont >= maiorQue && cont <= menorQue) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar os números pares... " + getListaDeJogos().size());
	}

	public void dicaDeOuro() {

		System.out.println("filtrando dica de ouro...");
		Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 1);
		Jogo strNumerosNaoSorteados = strUltimoSorteio.numerosNaoSorteados(strUltimoSorteio);

		Jogo jogoSA = new Jogo(Arrays.asList(strUltimoSorteio.getJogo().get(0), strUltimoSorteio.getJogo().get(1),
				strUltimoSorteio.getJogo().get(2)));
		Jogo jogoSB = new Jogo(Arrays.asList(strUltimoSorteio.getJogo().get(3), strUltimoSorteio.getJogo().get(4),
				strUltimoSorteio.getJogo().get(5)));
		Jogo jogoSC = new Jogo(Arrays.asList(strUltimoSorteio.getJogo().get(6), strUltimoSorteio.getJogo().get(7),
				strUltimoSorteio.getJogo().get(8)));
		Jogo jogoSD = new Jogo(Arrays.asList(strUltimoSorteio.getJogo().get(9), strUltimoSorteio.getJogo().get(10),
				strUltimoSorteio.getJogo().get(11)));
		Jogo jogoSE = new Jogo(Arrays.asList(strUltimoSorteio.getJogo().get(12), strUltimoSorteio.getJogo().get(13),
				strUltimoSorteio.getJogo().get(14)));

		Jogo jogoNA = new Jogo(
				Arrays.asList(strNumerosNaoSorteados.getJogo().get(0), strNumerosNaoSorteados.getJogo().get(1)));
		Jogo jogoNB = new Jogo(
				Arrays.asList(strNumerosNaoSorteados.getJogo().get(2), strNumerosNaoSorteados.getJogo().get(3)));
		Jogo jogoNC = new Jogo(
				Arrays.asList(strNumerosNaoSorteados.getJogo().get(4), strNumerosNaoSorteados.getJogo().get(5)));
		Jogo jogoND = new Jogo(
				Arrays.asList(strNumerosNaoSorteados.getJogo().get(6), strNumerosNaoSorteados.getJogo().get(7)));
		Jogo jogoNE = new Jogo(
				Arrays.asList(strNumerosNaoSorteados.getJogo().get(8), strNumerosNaoSorteados.getJogo().get(9)));

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		int contS = 0;
		int contN = 0;
		for (Jogo jogo : listaAuxilar) {

			// GRUPO A
			contS = 0;
			contN = 0;
			if (jogo.getJogo().contains(jogoSA.getJogo().get(0))) {
				contS++;
			}
			if (jogo.getJogo().contains(jogoSA.getJogo().get(1))) {
				contS++;
			}
			if (jogo.getJogo().contains(jogoSA.getJogo().get(2))) {
				contS++;
			}

			if (jogo.getJogo().contains(jogoNA.getJogo().get(0))) {
				contN++;
			}
			if (jogo.getJogo().contains(jogoNA.getJogo().get(1))) {
				contN++;
			}

			if (contN > 1 && contS > 1) {
				numResult.add(jogo);
				continue;
			}

			// GRUPO B
			contS = 0;
			contN = 0;
			if (jogo.getJogo().contains(jogoSB.getJogo().get(0))) {
				contS++;
			}
			if (jogo.getJogo().contains(jogoSB.getJogo().get(1))) {
				contS++;
			}
			if (jogo.getJogo().contains(jogoSB.getJogo().get(2))) {
				contS++;
			}

			if (jogo.getJogo().contains(jogoNB.getJogo().get(0))) {
				contN++;
			}
			if (jogo.getJogo().contains(jogoNB.getJogo().get(1))) {
				contN++;
			}

			if (contN > 1 && contS > 1) {
				numResult.add(jogo);
				continue;
			}

			// GRUPO C
			contS = 0;
			contN = 0;
			if (jogo.getJogo().contains(jogoSC.getJogo().get(0))) {
				contS++;
			}
			if (jogo.getJogo().contains(jogoSC.getJogo().get(1))) {
				contS++;
			}
			if (jogo.getJogo().contains(jogoSC.getJogo().get(2))) {
				contS++;
			}

			if (jogo.getJogo().contains(jogoNC.getJogo().get(0))) {
				contN++;
			}
			if (jogo.getJogo().contains(jogoNC.getJogo().get(1))) {
				contN++;
			}

			if (contN > 1 && contS > 1) {
				numResult.add(jogo);
				continue;
			}

			// GRUPO D
			contS = 0;
			contN = 0;
			if (jogo.getJogo().contains(jogoSD.getJogo().get(0))) {
				contS++;
			}
			if (jogo.getJogo().contains(jogoSD.getJogo().get(1))) {
				contS++;
			}
			if (jogo.getJogo().contains(jogoSD.getJogo().get(2))) {
				contS++;
			}

			if (jogo.getJogo().contains(jogoND.getJogo().get(0))) {
				contN++;
			}
			if (jogo.getJogo().contains(jogoND.getJogo().get(1))) {
				contN++;
			}

			if (contN > 1 && contS > 1) {
				numResult.add(jogo);
				continue;
			}

			// GRUPO E
			contS = 0;
			contN = 0;
			if (jogo.getJogo().contains(jogoSE.getJogo().get(0))) {
				contS++;
			}
			if (jogo.getJogo().contains(jogoSE.getJogo().get(1))) {
				contS++;
			}
			if (jogo.getJogo().contains(jogoSE.getJogo().get(2))) {
				contS++;
			}

			if (jogo.getJogo().contains(jogoNE.getJogo().get(0))) {
				contN++;
			}
			if (jogo.getJogo().contains(jogoNE.getJogo().get(1))) {
				contN++;
			}

			if (contN > 1 && contS > 1) {
				numResult.add(jogo);
				continue;
			}

		}

		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar dica de outro..." + getListaDeJogos().size());

	}

	public List<Jogo> getListaJogosPorDemanda() {
		return listaJogosPorDemanda;
	}

	public void grupo8a11() {

		System.out.println("filtrando o grupo 8 a 11...");

		List<Jogo> linha = new ArrayList<Jogo>();
		linha.add(new Jogo(Arrays.asList(8, 9, 10, 11))); // 180=4;612=3;571=2;261=1

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);

			if (jogo.getJogo().contains(8) || jogo.getJogo().contains(9) || jogo.getJogo().contains(10)
					|| jogo.getJogo().contains(11)) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar o grupo 8 a 11..." + getListaDeJogos().size());
	}

	public void grupo20a25(int maiorQue, int menorQue) {

		System.out.println("filtrando o grupo 20 a 25...");

		Jogo grupo20a25 = new Jogo(Arrays.asList(20, 21, 22, 23, 24, 25));
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 1);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(grupo20a25.getJogo());
			qtdOcorrencias = intersection.size();
		}

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);

			int cont = 0;

			if (jogo.getJogo().contains(20)) {
				cont++;
			}

			if (jogo.getJogo().contains(21)) {
				cont++;
			}

			if (jogo.getJogo().contains(22)) {
				cont++;
			}

			if (jogo.getJogo().contains(23)) {
				cont++;
			}

			if (jogo.getJogo().contains(24)) {
				cont++;
			}

			if (jogo.getJogo().contains(25)) {
				cont++;
			}

			/* quando vem o 2 dificilmente igual */

			if (qtdOcorrencias == 2 && cont == qtdOcorrencias) {
				continue;
			}

			if (cont >= maiorQue && cont <= menorQue) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar o grupo 20 a 25..." + getListaDeJogos().size());
	}

	public void retirar() {

		System.out.println("filtrando números a sair...");

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		for (Jogo jogo : listaAuxilar) {

			if (!jogo.getJogo().contains(4)) {
				numResult.add(jogo);
			}

		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar números a sair..." + getListaDeJogos().size());
	}

	public void primeiroEUltimo() {

		/* quando sai o 3 na primeira casa, geralmente o próximo é o 1 ou o 2 */
		/*
		 * quando sai o 23 na última casa, geralmente o próximo é o 24 ou o 25
		 */

		System.out.println("filtrando primeiro e último...");

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);

			if (jogo.getJogo().get(0) == 1 && jogo.getJogo().get(14) == 25) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

			if (jogo.getJogo().get(0) == 1 && jogo.getJogo().get(14) == 24) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

			if (jogo.getJogo().get(0) == 1 && jogo.getJogo().get(14) == 23) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

			if (jogo.getJogo().get(0) == 2 && jogo.getJogo().get(14) == 25) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

			if (jogo.getJogo().get(0) == 2 && jogo.getJogo().get(14) == 24) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

			if (jogo.getJogo().get(0) == 2 && jogo.getJogo().get(14) == 23) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

			if (jogo.getJogo().get(0) == 3 && jogo.getJogo().get(14) == 25) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

			if (jogo.getJogo().get(0) == 3 && jogo.getJogo().get(14) == 24) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

			if (jogo.getJogo().get(0) == 3 && jogo.getJogo().get(14) == 23) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

			if (jogo.getJogo().get(0) == 4 && jogo.getJogo().get(14) == 25) {
				jogo.somaElimina();
				numResult.add(jogo);
			}

		}
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar primeiro e último..." + getListaDeJogos().size());
	}

	public void gruposQuintos() throws FileNotFoundException, IOException {

		if (listaJogosPorDemanda.size() < 8) {
			return;
		}
		System.out.println("filtrando grupos quintos...");
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();

		int qTDJogosAnalisados = 8;

		Integer primeiroQuinto = 0;
		Integer segundoQuinto = 0;
		Integer terceiroQuinto = 0;
		Integer quartoQuinto = 0;
		Integer quintoQuinto = 0;

		TreeMap<Integer, Integer> mapaQuintos = new TreeMap<Integer, Integer>();

		for (int posicao = listaJogosPorDemanda.size() - 1; posicao > listaJogosPorDemanda.size()
				- (qTDJogosAnalisados + 1); posicao--) {
			// System.out.println(todosJogos.get(posicao).getJogo());
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

		mapaQuintos.put(1, primeiroQuinto / qTDJogosAnalisados);
		mapaQuintos.put(2, segundoQuinto / qTDJogosAnalisados);
		mapaQuintos.put(3, terceiroQuinto / qTDJogosAnalisados);
		mapaQuintos.put(4, quartoQuinto / qTDJogosAnalisados);
		mapaQuintos.put(5, quintoQuinto / qTDJogosAnalisados);

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);

			int cont = 0;
			int contOcorreciaGrupos = 0;
			if (jogo.getJogo().contains(1)) {
				cont++;
			}
			if (jogo.getJogo().contains(2)) {
				cont++;
			}
			if (jogo.getJogo().contains(3)) {
				cont++;
			}
			if (jogo.getJogo().contains(4)) {
				cont++;
			}
			if (jogo.getJogo().contains(5)) {
				cont++;
			}
			if (cont >= mapaQuintos.get(1).intValue()) {
				contOcorreciaGrupos++;
			}

			cont = 0;
			if (jogo.getJogo().contains(6)) {
				cont++;
			}
			if (jogo.getJogo().contains(7)) {
				cont++;
			}
			if (jogo.getJogo().contains(8)) {
				cont++;
			}
			if (jogo.getJogo().contains(9)) {
				cont++;
			}
			if (jogo.getJogo().contains(10)) {
				cont++;
			}
			if (cont >= mapaQuintos.get(2).intValue()) {
				contOcorreciaGrupos++;
			}

			cont = 0;
			if (jogo.getJogo().contains(11)) {
				cont++;
			}
			if (jogo.getJogo().contains(12)) {
				cont++;
			}
			if (jogo.getJogo().contains(13)) {
				cont++;
			}
			if (jogo.getJogo().contains(14)) {
				cont++;
			}
			if (jogo.getJogo().contains(15)) {
				cont++;
			}
			if (cont >= mapaQuintos.get(3).intValue()) {
				contOcorreciaGrupos++;
			}

			cont = 0;
			if (jogo.getJogo().contains(16)) {
				cont++;
			}
			if (jogo.getJogo().contains(17)) {
				cont++;
			}
			if (jogo.getJogo().contains(18)) {
				cont++;
			}
			if (jogo.getJogo().contains(19)) {
				cont++;
			}
			if (jogo.getJogo().contains(20)) {
				cont++;
			}
			if (cont >= mapaQuintos.get(4).intValue()) {
				contOcorreciaGrupos++;
			}

			cont = 0;
			if (jogo.getJogo().contains(21)) {
				cont++;
			}
			if (jogo.getJogo().contains(22)) {
				cont++;
			}
			if (jogo.getJogo().contains(23)) {
				cont++;
			}
			if (jogo.getJogo().contains(24)) {
				cont++;
			}
			if (jogo.getJogo().contains(25)) {
				cont++;
			}
			if (cont >= mapaQuintos.get(5).intValue()) {
				contOcorreciaGrupos++;
			}

			if (contOcorreciaGrupos > 3) {
				jogo.somaElimina();
				numResult.add(jogo);
			}
			contOcorreciaGrupos = 0;
		}

		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar grupos quintos..." + getListaDeJogos().size());

	}

	public void imparesSeguindos() {

		System.out.println("Filtrando os números impares seguidos...");
		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		int ocorrencias = 0;
		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);
			ocorrencias = 0;
			for (Integer numero : jogo.getJogo()) {
				if (numero % 2 != 0) {
					ocorrencias++;
				} else {
					ocorrencias = 0;
				}

				if (ocorrencias > 5) {
					break;
				}
			}
			if (ocorrencias < 6) {
				jogo.somaElimina();
				numResult.add(jogo);
			}
		}

		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar impares seguidos..." + getListaDeJogos().size());
	}

	public void paresSeguindos() {

		System.out.println("Filtrando os números pares seguidos...");
		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		int ocorrencias = 0;
		for (Jogo jogo : listaAuxilar) {
			// listaCheia.add(jogo);
			ocorrencias = 0;
			for (Integer numero : jogo.getJogo()) {
				if (numero % 2 == 0) {
					ocorrencias++;
				} else {
					ocorrencias = 0;
				}

				if (ocorrencias > 5) {
					break;
				}
			}
			if (ocorrencias < 6) {
				jogo.somaElimina();
				numResult.add(jogo);
			}
		}

		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar pares seguidos..." + getListaDeJogos().size());
	}

	/////////////////////////////////////////////////////
	////////////////// BOOLEANOS///////////////////////
	/////////////////////////////////////////////
	public static boolean eRepetidos(int maiorQue, int menorQue, Jogo jogo) {

		// para testar tem que somar um
		Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 1);
		Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
		intersection.retainAll(jogo.getJogo());
		int index = intersection.size();

		if (index >= maiorQue && index <= menorQue) {
			return true;
		}
		return false;
	}

	public boolean eCruz(int maiorQue, int menorQue, Jogo jogo) {
		Jogo cruz = new Jogo(Arrays.asList(3, 8, 11, 12, 13, 14, 15, 18, 23));// {2=5,
																				// 3=75,
																				// 4=243,
																				// 5=493,
																				// 6=451,
																				// 7=218,
																				// 8=53,
																				// 9=3}
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - ultimoJogo);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(cruz.getJogo());
			qtdOcorrencias = intersection.size();
		}

		int cont = 0;
		if (jogo.getJogo().contains(3))
			cont++;
		if (jogo.getJogo().contains(8))
			cont++;
		if (jogo.getJogo().contains(11))
			cont++;
		if (jogo.getJogo().contains(12))
			cont++;
		if (jogo.getJogo().contains(13))
			cont++;
		if (jogo.getJogo().contains(14))
			cont++;
		if (jogo.getJogo().contains(15))
			cont++;
		if (jogo.getJogo().contains(18))
			cont++;
		if (jogo.getJogo().contains(23))
			cont++;

		/*
		 * quando vez 7,8,9 dificilmente vem uma quantidade maior no próximo
		 */
		/* quando vez 2,3,7,8,9 dificilmente se repete */
		/*
		 * quando vez 2,3,4 dificilmente vem uma quantidade menor no próximo
		 */

		if ((qtdOcorrencias >= 8 && qtdOcorrencias <= 9) && cont > qtdOcorrencias) {
			return false;
		}

		if (((qtdOcorrencias >= 2 && qtdOcorrencias <= 2) || (qtdOcorrencias >= 8 && qtdOcorrencias <= 9))
				&& cont == qtdOcorrencias) {
			return false;
		}

		if ((qtdOcorrencias >= 2 && qtdOcorrencias <= 3) && cont < qtdOcorrencias) {
			return false;
		}
		if (cont >= maiorQue && cont <= menorQue) {
			// numResult.add(jogo);
			return true;
		}
		return false;

	}

	public boolean eX(int maiorQue, int menorQue, Jogo jogo) {
		// {2=8,// 3=65,// 4=269,// 5=465,// 6=473,// 7=213,// 8=47,// 9=1}

		Jogo x = new Jogo(Arrays.asList(1, 5, 7, 9, 13, 17, 19, 21, 25));
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - ultimoJogo);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(x.getJogo());
			qtdOcorrencias = intersection.size();
		}

		int cont = 0;

		if (jogo.getJogo().contains(1))
			cont++;
		if (jogo.getJogo().contains(5))
			cont++;
		if (jogo.getJogo().contains(7))
			cont++;
		if (jogo.getJogo().contains(9))
			cont++;
		if (jogo.getJogo().contains(13))
			cont++;
		if (jogo.getJogo().contains(17))
			cont++;
		if (jogo.getJogo().contains(19))
			cont++;
		if (jogo.getJogo().contains(21))
			cont++;
		if (jogo.getJogo().contains(25))
			cont++;

		/* quando vem o 7,8,9 dificilmente vem maior */
		/* quando vem o 3,8,9 dificilmente igual */
		/* quando vem o 2,3,4 dificilmente vem menor */

		if ((qtdOcorrencias >= 8 && qtdOcorrencias <= 9) && cont > qtdOcorrencias) {
			return false;
		}

		if (((qtdOcorrencias == 3) || (qtdOcorrencias >= 9 && qtdOcorrencias <= 9)) && cont == qtdOcorrencias) {
			return false;
		}

		if ((qtdOcorrencias >= 2 && qtdOcorrencias <= 3) && cont < qtdOcorrencias) {
			return false;
		}

		if (cont >= maiorQue && cont <= menorQue) {
			// numResult.add(jogo);
			return true;
		}
		return false;

	}

	public boolean eQuadrado(int maiorQue, int menorQue, Jogo jogo) {
		Jogo quadrado = new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 10, 11, 15, 16, 20, 21, 22, 23, 24, 25));
		// cruz.add(new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 10, 11, 15, 16, 20,
		// 21, 22, 23, 24, 25))); // {6=2, 7=52,8=199, 9=450, 10=483, 11=261,
		// 12=88, 13=6}

		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - ultimoJogo);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(quadrado.getJogo());
			qtdOcorrencias = intersection.size();
		}

		int cont = 0;
		if (jogo.getJogo().contains(1))
			cont++;
		if (jogo.getJogo().contains(2))
			cont++;
		if (jogo.getJogo().contains(3))
			cont++;
		if (jogo.getJogo().contains(4))
			cont++;
		if (jogo.getJogo().contains(5))
			cont++;
		if (jogo.getJogo().contains(6))
			cont++;
		if (jogo.getJogo().contains(10))
			cont++;
		if (jogo.getJogo().contains(11))
			cont++;
		if (jogo.getJogo().contains(15))
			cont++;
		if (jogo.getJogo().contains(16))
			cont++;
		if (jogo.getJogo().contains(20))
			cont++;
		if (jogo.getJogo().contains(21))
			cont++;
		if (jogo.getJogo().contains(22))
			cont++;
		if (jogo.getJogo().contains(23))
			cont++;
		if (jogo.getJogo().contains(24))
			cont++;
		if (jogo.getJogo().contains(25))
			cont++;

		/* quando vem o 11,12,13 dificilmente vem maior */
		/* quando vem o 6,7,12,13 dificilmente igual */
		/* quando vem o 6,7,8 dificilmente vem menor */

		if ((qtdOcorrencias >= 12 && qtdOcorrencias <= 13) && cont > qtdOcorrencias) {
			return false;
		}

		if (((qtdOcorrencias >= 6 && qtdOcorrencias <= 6) || (qtdOcorrencias >= 13 && qtdOcorrencias <= 13))
				&& cont == qtdOcorrencias) {
			return false;
		}

		if ((qtdOcorrencias >= 6 && qtdOcorrencias <= 7) && cont < qtdOcorrencias) {
			return false;
		}

		if (cont >= maiorQue && cont <= menorQue) {
			return true;
		}
		return false;
	}

	public boolean eMultiplosDeTres(int maiorQue, int menorQue, Jogo jogo) {
		Jogo multiplosDeTres = new Jogo(Arrays.asList(3, 6, 9, 12, 15, 18, 21, 24));
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - ultimoJogo);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(multiplosDeTres.getJogo());
			qtdOcorrencias = intersection.size();
		}

		int cont = 0;

		if (jogo.getJogo().contains(3))
			cont++;
		if (jogo.getJogo().contains(6))
			cont++;
		if (jogo.getJogo().contains(9))
			cont++;
		if (jogo.getJogo().contains(12))
			cont++;
		if (jogo.getJogo().contains(15))
			cont++;
		if (jogo.getJogo().contains(18))
			cont++;
		if (jogo.getJogo().contains(21))
			cont++;
		if (jogo.getJogo().contains(24))
			cont++;

		/* quando vem o 6,7,8 dificilmente vem maior */
		/* quando vem o 1,2,3,7,8 dificilmente igual */
		/* quando vem o 1,2,3 dificilmente vem menor */

		if ((qtdOcorrencias >= 7 && qtdOcorrencias <= 8) && cont > qtdOcorrencias) {
			return false;
		}

		if (((qtdOcorrencias >= 1 && qtdOcorrencias <= 2) || (qtdOcorrencias >= 8 && qtdOcorrencias <= 8))
				&& cont == qtdOcorrencias) {
			return false;
		}

		if ((qtdOcorrencias >= 1 && qtdOcorrencias <= 2) && cont < qtdOcorrencias) {
			return false;
		}

		if (cont >= maiorQue && cont <= menorQue) {
			return true;
		}
		return false;

	}

	public boolean eSequenciaDeFibonacci(int maiorQue, int menorQue, Jogo jogo) {

		/* quando vem o 5,6,7 dificilmente vem maior */
		/* quando vem o 1,2,6,7 dificilmente igual */
		/* quando vem o 1,2,3 dificilmente vem menor */

		Jogo sequenciaDeFibonacci = new Jogo(Arrays.asList(1, 2, 3, 5, 8, 13, 21));
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - ultimoJogo);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(sequenciaDeFibonacci.getJogo());
			qtdOcorrencias = intersection.size();
		}

		int cont = 0;

		if (jogo.getJogo().contains(1))
			cont++;
		if (jogo.getJogo().contains(2))
			cont++;
		if (jogo.getJogo().contains(3))
			cont++;
		if (jogo.getJogo().contains(5))
			cont++;
		if (jogo.getJogo().contains(8))
			cont++;
		if (jogo.getJogo().contains(13))
			cont++;
		if (jogo.getJogo().contains(21))
			cont++;

		/* quando vem o 5,6,7 dificilmente vem maior */
		/* quando vem o 1,2,6,7 dificilmente igual */
		/* quando vem o 1,2,3 dificilmente vem menor */

		if ((qtdOcorrencias >= 6 && qtdOcorrencias <= 7) && cont > qtdOcorrencias) {
			return false;
		}

		if (((qtdOcorrencias >= 1 && qtdOcorrencias <= 1) || (qtdOcorrencias >= 7 && qtdOcorrencias <= 7))
				&& cont == qtdOcorrencias) {
			return false;
		}

		if ((qtdOcorrencias >= 1 && qtdOcorrencias <= 2) && cont < qtdOcorrencias) {
			return false;
		}

		if (cont >= maiorQue && cont <= menorQue) {
			return true;
		}
		return false;

	}

	public boolean eCantos(Jogo jogo) {

		List<Jogo> canto = new ArrayList<Jogo>();

		int cont = 0;
		if (jogo.getJogo().contains(1) || jogo.getJogo().contains(2) || jogo.getJogo().contains(6)
				|| jogo.getJogo().contains(7)) {
			cont++;
		}

		if (jogo.getJogo().contains(4) || jogo.getJogo().contains(5) || jogo.getJogo().contains(9)
				|| jogo.getJogo().contains(10)) {
			cont++;
		}

		if (jogo.getJogo().contains(16) || jogo.getJogo().contains(17) || jogo.getJogo().contains(21)
				|| jogo.getJogo().contains(22)) {
			cont++;
		}

		if (jogo.getJogo().contains(19) || jogo.getJogo().contains(20) || jogo.getJogo().contains(24)
				|| jogo.getJogo().contains(25)) {
			cont++;
		}

		if (cont > 3) {
			return true;

		}
		return false;

	}

	public boolean eSoma(Jogo jogo) {

		Integer soma = 0;

		for (Integer numero : jogo.getJogo()) {
			soma = soma + numero;
		}
		if (soma > 135 && soma < 248) {
			return true;
		}
		soma = 0;
		return false;

	}

	public boolean ePosicoes(Jogo jogo) {
		/*
		 * posição 1 [1,2,3] posição 2 [2 a 5] posição 3 [3 a 7] posição 4 [4 a
		 * 9] posição 5 [5 a 11] posição 6 [6 a 13] posição 7 [8 a 15] posição 8
		 * [9 a 17] posição 9 [11 a 18] posição 10 [13 a 19] posição 11 [15 a
		 * 21] posição 12 [17 a 22] posição 13 [19 a 23] posição 14 [21 a 24]
		 * posição 15 [23 a 25]
		 */

		if ((jogo.getJogo().get(0) < 4) && (jogo.getJogo().get(1) > 1 && jogo.getJogo().get(1) < 6)
				&& (jogo.getJogo().get(2) > 2 && jogo.getJogo().get(2) < 8)
				&& (jogo.getJogo().get(3) > 3 && jogo.getJogo().get(3) < 10)
				&& (jogo.getJogo().get(4) > 4 && jogo.getJogo().get(4) < 12)
				&& (jogo.getJogo().get(5) > 5 && jogo.getJogo().get(5) < 14)
				&& (jogo.getJogo().get(6) > 7 && jogo.getJogo().get(6) < 16)
				&& (jogo.getJogo().get(7) > 8 && jogo.getJogo().get(7) < 18)
				&& (jogo.getJogo().get(8) > 10 && jogo.getJogo().get(8) < 19)
				&& (jogo.getJogo().get(9) > 12 && jogo.getJogo().get(9) < 20)
				&& (jogo.getJogo().get(10) > 14 && jogo.getJogo().get(10) < 22)
				&& (jogo.getJogo().get(11) > 16 && jogo.getJogo().get(11) < 23)
				&& (jogo.getJogo().get(12) > 18 && jogo.getJogo().get(12) < 24)
				&& (jogo.getJogo().get(13) > 20 && jogo.getJogo().get(13) < 25)
				&& (jogo.getJogo().get(14) > 22 && jogo.getJogo().get(14) < 26))

		{

			return true;
		}
		return false;
	}

	public boolean ePrimeiroEUltimo(Jogo jogo) {

		/* quando sai o 3 na primeira casa, geralmente o próximo é o 1 ou o 2 */
		/*
		 * quando sai o 23 na última casa, geralmente o próximo é o 24 ou o 25
		 */

		if (jogo.getJogo().get(0) == 1 && jogo.getJogo().get(14) == 25) {
			return true;
		}

		if (jogo.getJogo().get(0) == 1 && jogo.getJogo().get(14) == 24) {
			return true;
		}

		if (jogo.getJogo().get(0) == 1 && jogo.getJogo().get(14) == 23) {
			return true;
		}

		if (jogo.getJogo().get(0) == 2 && jogo.getJogo().get(14) == 25) {
			return true;
		}

		if (jogo.getJogo().get(0) == 2 && jogo.getJogo().get(14) == 24) {
			return true;
		}

		if (jogo.getJogo().get(0) == 2 && jogo.getJogo().get(14) == 23) {
			return true;
		}

		if (jogo.getJogo().get(0) == 3 && jogo.getJogo().get(14) == 25) {
			return true;
		}

		if (jogo.getJogo().get(0) == 3 && jogo.getJogo().get(14) == 24) {
			return true;
		}

		if (jogo.getJogo().get(0) == 3 && jogo.getJogo().get(14) == 23) {
			return true;
		}

		if (jogo.getJogo().get(0) == 4 && jogo.getJogo().get(14) == 25) {
			return true;
		}
		return false;

	}

	public boolean eVerificaNumerosPrioritarios(Jogo jogo) {

		List<Integer> numerosPrioritarios = new ArrayList<Integer>();
		numerosPrioritarios = numerosPrioritarios();

		int cont = 0;

		cont = 0;

		for (Integer integer : numerosPrioritarios) {
			if (jogo.getJogo().contains(integer)) {
				cont++;
			}

			if (numerosPrioritarios.size() > 3) {
				if (cont > 1) {
					return true;
				}
			} else {
				if (cont > 0) {
					return true;
				}
			}

		}
		return false;

	}

	public boolean eNumerosAnteriores(Jogo jogo) {

		/// para a simulação tem que somar um valor a mais em cada caso abaixo
		Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - ultimoJogo);
		Jogo strPenUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - pNultimoJogo);
		Jogo strNoveSorteiosAtras = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - noveJogosAtras);

		// Jogo strUltimoSorteio =
		// listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 1);
		// Jogo strPenUltimoSorteio =
		// listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 2);
		// Jogo strNoveSorteiosAtras =
		// listaJogosPorDemanda.get(listaJogosPorDemanda.size() - 9);

		Jogo strNumerosNaoSorteados = strUltimoSorteio.numerosNaoSorteados(strUltimoSorteio);

		Set<Integer> differenceUltimoPenUltimo = new HashSet<Integer>(strPenUltimoSorteio.getJogo());
		differenceUltimoPenUltimo.removeAll(strUltimoSorteio.getJogo());

		Set<Integer> intersectionNoveJogosAtras = new HashSet<Integer>(strNoveSorteiosAtras.getJogo());
		intersectionNoveJogosAtras.retainAll(differenceUltimoPenUltimo);

		int intContDif = 0;
		int intContInt = 0;

		for (Integer numero : differenceUltimoPenUltimo) {
			if (jogo.getJogo().contains(numero)) {
				intContDif++;
			}

		}

		for (Integer numero : intersectionNoveJogosAtras) {
			if (jogo.getJogo().contains(numero)) {
				intContInt++;
			}

		}

		if (intContDif > 1 && intContInt > 0) {
			return true;
		}

		intContDif = 0;
		intContInt = 0;

		return false;

	}

	public boolean eNumerosImportantes(int maiorQue, int menorQue, Jogo jogo) {

		Jogo numerosImportantes = new Jogo(Arrays.asList(5, 6, 7, 12, 14, 19, 20, 21));
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - ultimoJogo);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(numerosImportantes.getJogo());
			qtdOcorrencias = intersection.size();
		}

		int cont = 0;

		if (jogo.getJogo().contains(5))
			cont++;
		if (jogo.getJogo().contains(6))
			cont++;
		if (jogo.getJogo().contains(7))
			cont++;
		if (jogo.getJogo().contains(12))
			cont++;
		if (jogo.getJogo().contains(13))
			cont++;
		if (jogo.getJogo().contains(14))
			cont++;
		if (jogo.getJogo().contains(19))
			cont++;
		if (jogo.getJogo().contains(20))
			cont++;
		if (jogo.getJogo().contains(21))
			cont++;

		/* quando vem o 7,8,9 dificilmente vem maior */
		/* quando vem o 1,2,3,7,8,9 dificilmente igual */
		/* quando vem o 1,2,3,4 dificilmente vem menor */

		if ((qtdOcorrencias >= 8 && qtdOcorrencias <= 9) && cont > qtdOcorrencias) {
			return false;
		}

		if (((qtdOcorrencias >= 1 && qtdOcorrencias <= 2) || (qtdOcorrencias >= 8 && qtdOcorrencias <= 9))
				&& cont == qtdOcorrencias) {
			return false;
		}

		if ((qtdOcorrencias >= 1 && qtdOcorrencias <= 3) && cont < qtdOcorrencias) {
			return false;
		}

		if (cont >= maiorQue && cont <= menorQue) {
			return true;
		}
		return false;

	}

	public boolean ePrimo(int maiorQue, int menorQue, Jogo jogo) {

		Jogo primo = new Jogo(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23)); //
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - ultimoJogo);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(primo.getJogo());
			qtdOcorrencias = intersection.size();
		}

		int cont = 0;

		if (jogo.getJogo().contains(2))
			cont++;
		if (jogo.getJogo().contains(3))
			cont++;
		if (jogo.getJogo().contains(5))
			cont++;
		if (jogo.getJogo().contains(7))
			cont++;
		if (jogo.getJogo().contains(11))
			cont++;
		if (jogo.getJogo().contains(13))
			cont++;
		if (jogo.getJogo().contains(17))
			cont++;
		if (jogo.getJogo().contains(19))
			cont++;
		if (jogo.getJogo().contains(23))
			cont++;

		/* quando vem o 7,8,9 dificilmente vem maior */
		/* quando vem o 2,3,8,9 dificilmente igual */
		/* quando vem o 1,2,3,4 dificilmente vem menor */

		if ((qtdOcorrencias >= 8 && qtdOcorrencias <= 9) && cont > qtdOcorrencias) {
			return false;
		}

		if (((qtdOcorrencias >= 2 && qtdOcorrencias <= 2) || (qtdOcorrencias >= 9 && qtdOcorrencias <= 9))
				&& cont == qtdOcorrencias) {
			return false;
		}

		if ((qtdOcorrencias >= 1 && qtdOcorrencias <= 3) && cont < qtdOcorrencias) {
			return false;
		}

		if (cont >= maiorQue && cont <= menorQue) {
			return true;
		}
		return false;

	}

	public boolean eDentro(int maiorQue, int menorQue, Jogo jogo) {

		Jogo dentro = new Jogo(Arrays.asList(7, 8, 9, 12, 13, 14, 17, 18, 19));
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - ultimoJogo);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(dentro.getJogo());
			qtdOcorrencias = intersection.size();
		}

		int cont = 0;

		if (jogo.getJogo().contains(7))
			cont++;
		if (jogo.getJogo().contains(8))
			cont++;
		if (jogo.getJogo().contains(9))
			cont++;
		if (jogo.getJogo().contains(12))
			cont++;
		if (jogo.getJogo().contains(13))
			cont++;
		if (jogo.getJogo().contains(14))
			cont++;
		if (jogo.getJogo().contains(17))
			cont++;
		if (jogo.getJogo().contains(18))
			cont++;
		if (jogo.getJogo().contains(19))
			cont++;

		/* quando vem o 7,8,9 dificilmente vem maior */
		/* quando vem o 2,3,7,8,9 dificilmente igual */
		/* quando vem o 2,3,4 dificilmente vem menor */

		if ((qtdOcorrencias >= 8 && qtdOcorrencias <= 9) && cont > qtdOcorrencias) {
			return false;
		}

		if (((qtdOcorrencias >= 2 && qtdOcorrencias <= 2) || (qtdOcorrencias >= 8 && qtdOcorrencias <= 9))
				&& cont == qtdOcorrencias) {
			return false;
		}

		if ((qtdOcorrencias >= 2 && qtdOcorrencias <= 3) && cont < qtdOcorrencias) {
			return false;
		}

		if (cont >= maiorQue && cont <= menorQue) {
			return true;
		}

		return false;

	}

	public boolean ePares(int maiorQue, int menorQue, Jogo jogo) throws FileNotFoundException, IOException {

		Jogo pares = new Jogo(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24));
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - ultimoJogo);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(pares.getJogo());
			qtdOcorrencias = intersection.size();
		}

		int cont = 0;

		if (jogo.getJogo().contains(2))
			cont++;
		if (jogo.getJogo().contains(4))
			cont++;
		if (jogo.getJogo().contains(6))
			cont++;
		if (jogo.getJogo().contains(8))
			cont++;
		if (jogo.getJogo().contains(10))
			cont++;
		if (jogo.getJogo().contains(12))
			cont++;
		if (jogo.getJogo().contains(14))
			cont++;
		if (jogo.getJogo().contains(16))
			cont++;
		if (jogo.getJogo().contains(18))
			cont++;
		if (jogo.getJogo().contains(20))
			cont++;
		if (jogo.getJogo().contains(22))
			cont++;
		if (jogo.getJogo().contains(24))
			cont++;

		/* quando vem o ... dificilmente vem maior */
		/* quando vem o 3,4,5,9,10,11 dificilmente igual */
		/* quando vem o 3,4,5,6 dificilmente vem menor */

		if (((qtdOcorrencias >= 3 && qtdOcorrencias <= 4) || (qtdOcorrencias >= 10 && qtdOcorrencias <= 11))
				&& cont == qtdOcorrencias) {
			return false;
		}

		if ((qtdOcorrencias >= 3 && qtdOcorrencias <= 5) && cont < qtdOcorrencias) {
			return false;
		}

		if (cont >= maiorQue && cont <= menorQue) {
			return true;
		}
		return false;

	}

	public boolean eGrupo20a25(int maiorQue, int menorQue, Jogo jogo) {

		Jogo grupo20a25 = new Jogo(Arrays.asList(20, 21, 22, 23, 24, 25));
		int qtdOcorrencias = 0;
		if (listaJogosPorDemanda.size() > 0) {
			Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - ultimoJogo);
			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(grupo20a25.getJogo());
			qtdOcorrencias = intersection.size();
		}

		int cont = 0;

		if (jogo.getJogo().contains(20)) {
			cont++;
		}

		if (jogo.getJogo().contains(21)) {
			cont++;
		}

		if (jogo.getJogo().contains(22)) {
			cont++;
		}

		if (jogo.getJogo().contains(23)) {
			cont++;
		}

		if (jogo.getJogo().contains(24)) {
			cont++;
		}

		if (jogo.getJogo().contains(25)) {
			cont++;
		}

		/* quando vem o 2 dificilmente igual */

		if (qtdOcorrencias == 2 && cont == qtdOcorrencias) {
			return false;
		}

		if (cont >= maiorQue && cont <= menorQue) {
			return true;
		}
		return false;
	}

	public boolean eGrupo8a11(Jogo jogo) {

		List<Jogo> linha = new ArrayList<Jogo>();
		linha.add(new Jogo(Arrays.asList(8, 9, 10, 11))); // 180=4;612=3;571=2;261=1

		if (jogo.getJogo().contains(8) || jogo.getJogo().contains(9) || jogo.getJogo().contains(10)
				|| jogo.getJogo().contains(11)) {
			return true;
		}
		return false;
	}

	public boolean eLinha(Jogo jogo) {

		int cont = 0;
		if (jogo.getJogo().contains(1) || jogo.getJogo().contains(2) || jogo.getJogo().contains(3)
				|| jogo.getJogo().contains(4) || jogo.getJogo().contains(5)) {
			cont++;
		}

		if (jogo.getJogo().contains(6) || jogo.getJogo().contains(7) || jogo.getJogo().contains(8)
				|| jogo.getJogo().contains(9) || jogo.getJogo().contains(10)) {
			cont++;
		}

		if (jogo.getJogo().contains(11) || jogo.getJogo().contains(12) || jogo.getJogo().contains(13)
				|| jogo.getJogo().contains(14) || jogo.getJogo().contains(15)) {
			cont++;
		}

		if (jogo.getJogo().contains(16) || jogo.getJogo().contains(17) || jogo.getJogo().contains(18)
				|| jogo.getJogo().contains(19) || jogo.getJogo().contains(20)) {
			cont++;
		}

		if (jogo.getJogo().contains(21) || jogo.getJogo().contains(22) || jogo.getJogo().contains(23)
				|| jogo.getJogo().contains(24) || jogo.getJogo().contains(25)) {
			cont++;
		}

		if (cont > 4)

		{
			return true;

		}

		return false;

	}

	public boolean eColuna(Jogo jogo) {

		int cont = 0;
		if (jogo.getJogo().contains(1) || jogo.getJogo().contains(6) || jogo.getJogo().contains(11)
				|| jogo.getJogo().contains(16) || jogo.getJogo().contains(21)) {
			cont++;
		}

		if (jogo.getJogo().contains(2) || jogo.getJogo().contains(7) || jogo.getJogo().contains(12)
				|| jogo.getJogo().contains(17) || jogo.getJogo().contains(22)) {
			cont++;
		}

		if (jogo.getJogo().contains(3) || jogo.getJogo().contains(8) || jogo.getJogo().contains(13)
				|| jogo.getJogo().contains(18) || jogo.getJogo().contains(23)) {
			cont++;
		}

		if (jogo.getJogo().contains(4) || jogo.getJogo().contains(9) || jogo.getJogo().contains(14)
				|| jogo.getJogo().contains(19) || jogo.getJogo().contains(24)) {
			cont++;
		}

		if (jogo.getJogo().contains(5) || jogo.getJogo().contains(10) || jogo.getJogo().contains(15)
				|| jogo.getJogo().contains(20) || jogo.getJogo().contains(25)) {
			cont++;
		}

		if (cont > 4)

		{
			return true;

		}
		return false;

	}

	public boolean eSequencia(Jogo jogo) {

		int cont = 0;

		if

		((jogo.getJogo().get(0) == 1 && jogo.getJogo().get(6) == 7)
				|| (jogo.getJogo().get(0) == 2 && jogo.getJogo().get(6) == 8)
				|| (jogo.getJogo().get(0) == 3 && jogo.getJogo().get(6) == 9)
				|| (jogo.getJogo().get(0) == 4 && jogo.getJogo().get(6) == 10)
				|| (jogo.getJogo().get(0) == 5 && jogo.getJogo().get(6) == 11)
				|| (jogo.getJogo().get(0) == 6 && jogo.getJogo().get(6) == 12)
				|| (jogo.getJogo().get(0) == 7 && jogo.getJogo().get(6) == 13)
				|| (jogo.getJogo().get(0) == 8 && jogo.getJogo().get(6) == 14)
				|| (jogo.getJogo().get(0) == 9 && jogo.getJogo().get(6) == 15)
				|| (jogo.getJogo().get(0) == 10 && jogo.getJogo().get(6) == 16)
				|| (jogo.getJogo().get(0) == 11 && jogo.getJogo().get(6) == 17)
				|| (jogo.getJogo().get(0) == 12 && jogo.getJogo().get(6) == 18)
				|| (jogo.getJogo().get(0) == 13 && jogo.getJogo().get(6) == 19)
				|| (jogo.getJogo().get(0) == 14 && jogo.getJogo().get(6) == 120)
				|| (jogo.getJogo().get(0) == 15 && jogo.getJogo().get(6) == 21)
				|| (jogo.getJogo().get(0) == 16 && jogo.getJogo().get(6) == 22)
				|| (jogo.getJogo().get(0) == 17 && jogo.getJogo().get(6) == 23)
				|| (jogo.getJogo().get(0) == 18 && jogo.getJogo().get(6) == 24)
				|| (jogo.getJogo().get(0) == 19 && jogo.getJogo().get(6) == 25)) {
			cont++;
		}

		if

		((jogo.getJogo().get(1) == 2 && jogo.getJogo().get(7) == 8)
				|| (jogo.getJogo().get(1) == 3 && jogo.getJogo().get(7) == 9)
				|| (jogo.getJogo().get(1) == 4 && jogo.getJogo().get(7) == 10)
				|| (jogo.getJogo().get(1) == 5 && jogo.getJogo().get(7) == 11)
				|| (jogo.getJogo().get(1) == 6 && jogo.getJogo().get(7) == 12)
				|| (jogo.getJogo().get(1) == 7 && jogo.getJogo().get(7) == 13)
				|| (jogo.getJogo().get(1) == 8 && jogo.getJogo().get(7) == 14)
				|| (jogo.getJogo().get(1) == 9 && jogo.getJogo().get(7) == 15)
				|| (jogo.getJogo().get(1) == 10 && jogo.getJogo().get(7) == 16)
				|| (jogo.getJogo().get(1) == 11 && jogo.getJogo().get(7) == 17)
				|| (jogo.getJogo().get(1) == 12 && jogo.getJogo().get(7) == 18)
				|| (jogo.getJogo().get(1) == 13 && jogo.getJogo().get(7) == 19)
				|| (jogo.getJogo().get(1) == 14 && jogo.getJogo().get(7) == 20)
				|| (jogo.getJogo().get(1) == 15 && jogo.getJogo().get(7) == 21)
				|| (jogo.getJogo().get(1) == 16 && jogo.getJogo().get(7) == 22)
				|| (jogo.getJogo().get(1) == 17 && jogo.getJogo().get(7) == 23)
				|| (jogo.getJogo().get(1) == 18 && jogo.getJogo().get(7) == 24)
				|| (jogo.getJogo().get(1) == 19 && jogo.getJogo().get(7) == 25))

		{
			cont++;
		}

		if

		((jogo.getJogo().get(2) == 3 && jogo.getJogo().get(8) == 9)
				|| (jogo.getJogo().get(2) == 4 && jogo.getJogo().get(8) == 10)
				|| (jogo.getJogo().get(2) == 5 && jogo.getJogo().get(8) == 11)
				|| (jogo.getJogo().get(2) == 6 && jogo.getJogo().get(8) == 12)
				|| (jogo.getJogo().get(2) == 7 && jogo.getJogo().get(8) == 13)
				|| (jogo.getJogo().get(2) == 8 && jogo.getJogo().get(8) == 14)
				|| (jogo.getJogo().get(2) == 9 && jogo.getJogo().get(8) == 15)
				|| (jogo.getJogo().get(2) == 10 && jogo.getJogo().get(8) == 16)
				|| (jogo.getJogo().get(2) == 11 && jogo.getJogo().get(8) == 17)
				|| (jogo.getJogo().get(2) == 12 && jogo.getJogo().get(8) == 18)
				|| (jogo.getJogo().get(2) == 13 && jogo.getJogo().get(8) == 19)
				|| (jogo.getJogo().get(2) == 14 && jogo.getJogo().get(8) == 20)
				|| (jogo.getJogo().get(2) == 15 && jogo.getJogo().get(8) == 21)
				|| (jogo.getJogo().get(2) == 16 && jogo.getJogo().get(8) == 22)
				|| (jogo.getJogo().get(2) == 17 && jogo.getJogo().get(8) == 23)
				|| (jogo.getJogo().get(2) == 18 && jogo.getJogo().get(8) == 24)
				|| (jogo.getJogo().get(2) == 19 && jogo.getJogo().get(8) == 25))

		{
			cont++;
		}

		if

		((jogo.getJogo().get(3) == 4 && jogo.getJogo().get(9) == 10)
				|| (jogo.getJogo().get(3) == 5 && jogo.getJogo().get(9) == 11)
				|| (jogo.getJogo().get(3) == 6 && jogo.getJogo().get(9) == 12)
				|| (jogo.getJogo().get(3) == 7 && jogo.getJogo().get(9) == 13)
				|| (jogo.getJogo().get(3) == 8 && jogo.getJogo().get(9) == 14)
				|| (jogo.getJogo().get(3) == 9 && jogo.getJogo().get(9) == 15)
				|| (jogo.getJogo().get(3) == 10 && jogo.getJogo().get(9) == 16)
				|| (jogo.getJogo().get(3) == 11 && jogo.getJogo().get(9) == 17)
				|| (jogo.getJogo().get(3) == 12 && jogo.getJogo().get(9) == 18)
				|| (jogo.getJogo().get(3) == 13 && jogo.getJogo().get(9) == 19)
				|| (jogo.getJogo().get(3) == 14 && jogo.getJogo().get(9) == 20)
				|| (jogo.getJogo().get(3) == 15 && jogo.getJogo().get(9) == 21)
				|| (jogo.getJogo().get(3) == 16 && jogo.getJogo().get(9) == 22)
				|| (jogo.getJogo().get(3) == 17 && jogo.getJogo().get(9) == 23)
				|| (jogo.getJogo().get(3) == 18 && jogo.getJogo().get(9) == 24)
				|| (jogo.getJogo().get(3) == 19 && jogo.getJogo().get(9) == 25))

		{
			cont++;
		}

		if

		((jogo.getJogo().get(4) == 5 && jogo.getJogo().get(10) == 11)
				|| (jogo.getJogo().get(4) == 6 && jogo.getJogo().get(10) == 12)
				|| (jogo.getJogo().get(4) == 7 && jogo.getJogo().get(10) == 13)
				|| (jogo.getJogo().get(4) == 8 && jogo.getJogo().get(10) == 14)
				|| (jogo.getJogo().get(4) == 9 && jogo.getJogo().get(10) == 15)
				|| (jogo.getJogo().get(4) == 10 && jogo.getJogo().get(10) == 16)
				|| (jogo.getJogo().get(4) == 11 && jogo.getJogo().get(10) == 17)
				|| (jogo.getJogo().get(4) == 12 && jogo.getJogo().get(10) == 18)
				|| (jogo.getJogo().get(4) == 13 && jogo.getJogo().get(10) == 19)
				|| (jogo.getJogo().get(4) == 14 && jogo.getJogo().get(10) == 20)
				|| (jogo.getJogo().get(4) == 15 && jogo.getJogo().get(10) == 21)
				|| (jogo.getJogo().get(4) == 16 && jogo.getJogo().get(10) == 22)
				|| (jogo.getJogo().get(4) == 17 && jogo.getJogo().get(10) == 23)
				|| (jogo.getJogo().get(4) == 18 && jogo.getJogo().get(10) == 24)
				|| (jogo.getJogo().get(4) == 19 && jogo.getJogo().get(10) == 25))

		{
			cont++;
		}

		if

		((jogo.getJogo().get(5) == 6 && jogo.getJogo().get(11) == 12)
				|| (jogo.getJogo().get(5) == 7 && jogo.getJogo().get(11) == 13)
				|| (jogo.getJogo().get(5) == 8 && jogo.getJogo().get(11) == 14)
				|| (jogo.getJogo().get(5) == 9 && jogo.getJogo().get(11) == 15)
				|| (jogo.getJogo().get(5) == 10 && jogo.getJogo().get(11) == 16)
				|| (jogo.getJogo().get(5) == 11 && jogo.getJogo().get(11) == 17)
				|| (jogo.getJogo().get(5) == 12 && jogo.getJogo().get(11) == 18)
				|| (jogo.getJogo().get(5) == 13 && jogo.getJogo().get(11) == 19)
				|| (jogo.getJogo().get(5) == 14 && jogo.getJogo().get(11) == 20)
				|| (jogo.getJogo().get(5) == 15 && jogo.getJogo().get(11) == 21)
				|| (jogo.getJogo().get(5) == 16 && jogo.getJogo().get(11) == 22)
				|| (jogo.getJogo().get(5) == 17 && jogo.getJogo().get(11) == 23)
				|| (jogo.getJogo().get(5) == 18 && jogo.getJogo().get(11) == 24)
				|| (jogo.getJogo().get(5) == 19 && jogo.getJogo().get(11) == 25))

		{
			cont++;
		}

		if

		((jogo.getJogo().get(6) == 7 && jogo.getJogo().get(12) == 13)
				|| (jogo.getJogo().get(6) == 8 && jogo.getJogo().get(12) == 14)
				|| (jogo.getJogo().get(6) == 9 && jogo.getJogo().get(12) == 15)
				|| (jogo.getJogo().get(6) == 10 && jogo.getJogo().get(12) == 16)
				|| (jogo.getJogo().get(6) == 11 && jogo.getJogo().get(12) == 17)
				|| (jogo.getJogo().get(6) == 12 && jogo.getJogo().get(12) == 18)
				|| (jogo.getJogo().get(6) == 13 && jogo.getJogo().get(12) == 19)
				|| (jogo.getJogo().get(6) == 14 && jogo.getJogo().get(12) == 20)
				|| (jogo.getJogo().get(6) == 15 && jogo.getJogo().get(12) == 21)
				|| (jogo.getJogo().get(6) == 16 && jogo.getJogo().get(12) == 22)
				|| (jogo.getJogo().get(6) == 17 && jogo.getJogo().get(12) == 23)
				|| (jogo.getJogo().get(6) == 18 && jogo.getJogo().get(12) == 24)
				|| (jogo.getJogo().get(6) == 19 && jogo.getJogo().get(12) == 25))

		{
			cont++;
		}

		if

		((jogo.getJogo().get(7) == 8 && jogo.getJogo().get(13) == 14)
				|| (jogo.getJogo().get(7) == 9 && jogo.getJogo().get(13) == 15)
				|| (jogo.getJogo().get(7) == 10 && jogo.getJogo().get(13) == 16)
				|| (jogo.getJogo().get(7) == 11 && jogo.getJogo().get(13) == 17)
				|| (jogo.getJogo().get(7) == 12 && jogo.getJogo().get(13) == 18)
				|| (jogo.getJogo().get(7) == 13 && jogo.getJogo().get(13) == 19)
				|| (jogo.getJogo().get(7) == 14 && jogo.getJogo().get(13) == 20)
				|| (jogo.getJogo().get(7) == 15 && jogo.getJogo().get(13) == 21)
				|| (jogo.getJogo().get(7) == 16 && jogo.getJogo().get(13) == 22)
				|| (jogo.getJogo().get(7) == 17 && jogo.getJogo().get(13) == 23)
				|| (jogo.getJogo().get(7) == 18 && jogo.getJogo().get(13) == 24)
				|| (jogo.getJogo().get(7) == 19 && jogo.getJogo().get(13) == 25))

		{
			cont++;
		}

		if

		((jogo.getJogo().get(8) == 9 && jogo.getJogo().get(14) == 15)
				|| (jogo.getJogo().get(8) == 10 && jogo.getJogo().get(14) == 16)
				|| (jogo.getJogo().get(8) == 11 && jogo.getJogo().get(14) == 17)
				|| (jogo.getJogo().get(8) == 12 && jogo.getJogo().get(14) == 18)
				|| (jogo.getJogo().get(8) == 13 && jogo.getJogo().get(14) == 19)
				|| (jogo.getJogo().get(8) == 14 && jogo.getJogo().get(14) == 20)
				|| (jogo.getJogo().get(8) == 15 && jogo.getJogo().get(14) == 21)
				|| (jogo.getJogo().get(8) == 16 && jogo.getJogo().get(14) == 22)
				|| (jogo.getJogo().get(8) == 17 && jogo.getJogo().get(14) == 23)
				|| (jogo.getJogo().get(8) == 18 && jogo.getJogo().get(14) == 24)
				|| (jogo.getJogo().get(8) == 19 && jogo.getJogo().get(14) == 25))

		{
			cont++;
		}

		if (cont < 1) {
			return true;
		}
		cont = 0;
		return false;

	}

	public boolean eQTDRepetidos(int maiorQue, int menorQue, Jogo jogo) {

		// para testar tem que somar um
		Jogo strUltimoSorteio = listaJogosPorDemanda.get(listaJogosPorDemanda.size() - ultimoJogo);

		Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
		intersection.retainAll(jogo.getJogo());
		int index = intersection.size();
		if (index >= maiorQue && index <= menorQue) {
			return true;
		}
		return false;

	}

	public boolean eRepetido(Jogo jogo) throws FileNotFoundException, IOException {

		for (Jogo tj : listaTodosJogos) {
			Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
			intersection.retainAll(tj.getJogo());

			if (intersection.size() > 14) {
				return true;
			}
		}
		return false;
	}

	public boolean eGruposQuintos(Jogo jogo) throws FileNotFoundException, IOException {

		int qTDJogosAnalisados = 8;

		Integer primeiroQuinto = 0;
		Integer segundoQuinto = 0;
		Integer terceiroQuinto = 0;
		Integer quartoQuinto = 0;
		Integer quintoQuinto = 0;

		TreeMap<Integer, Integer> mapaQuintos = new TreeMap<Integer, Integer>();

		for (int posicao = listaJogosPorDemanda.size() - 1; posicao > listaJogosPorDemanda.size()
				- (qTDJogosAnalisados + 1); posicao--) {
			// System.out.println(todosJogos.get(posicao).getJogo());
			for (Integer numero : listaJogosPorDemanda.get(posicao).getJogo()) {

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

		mapaQuintos.put(1, primeiroQuinto / qTDJogosAnalisados);
		mapaQuintos.put(2, segundoQuinto / qTDJogosAnalisados);
		mapaQuintos.put(3, terceiroQuinto / qTDJogosAnalisados);
		mapaQuintos.put(4, quartoQuinto / qTDJogosAnalisados);
		mapaQuintos.put(5, quintoQuinto / qTDJogosAnalisados);

		int cont = 0;
		int contOcorreciaGrupos = 0;
		if (jogo.getJogo().contains(1)) {
			cont++;
		}
		if (jogo.getJogo().contains(2)) {
			cont++;
		}
		if (jogo.getJogo().contains(3)) {
			cont++;
		}
		if (jogo.getJogo().contains(4)) {
			cont++;
		}
		if (jogo.getJogo().contains(5)) {
			cont++;
		}
		if (cont >= mapaQuintos.get(1).intValue()) {
			contOcorreciaGrupos++;
		}

		cont = 0;
		if (jogo.getJogo().contains(6)) {
			cont++;
		}
		if (jogo.getJogo().contains(7)) {
			cont++;
		}
		if (jogo.getJogo().contains(8)) {
			cont++;
		}
		if (jogo.getJogo().contains(9)) {
			cont++;
		}
		if (jogo.getJogo().contains(10)) {
			cont++;
		}
		if (cont >= mapaQuintos.get(2).intValue()) {
			contOcorreciaGrupos++;
		}

		cont = 0;
		if (jogo.getJogo().contains(11)) {
			cont++;
		}
		if (jogo.getJogo().contains(12)) {
			cont++;
		}
		if (jogo.getJogo().contains(13)) {
			cont++;
		}
		if (jogo.getJogo().contains(14)) {
			cont++;
		}
		if (jogo.getJogo().contains(15)) {
			cont++;
		}
		if (cont >= mapaQuintos.get(3).intValue()) {
			contOcorreciaGrupos++;
		}

		cont = 0;
		if (jogo.getJogo().contains(16)) {
			cont++;
		}
		if (jogo.getJogo().contains(17)) {
			cont++;
		}
		if (jogo.getJogo().contains(18)) {
			cont++;
		}
		if (jogo.getJogo().contains(19)) {
			cont++;
		}
		if (jogo.getJogo().contains(20)) {
			cont++;
		}
		if (cont >= mapaQuintos.get(4).intValue()) {
			contOcorreciaGrupos++;
		}

		cont = 0;
		if (jogo.getJogo().contains(21)) {
			cont++;
		}
		if (jogo.getJogo().contains(22)) {
			cont++;
		}
		if (jogo.getJogo().contains(23)) {
			cont++;
		}
		if (jogo.getJogo().contains(24)) {
			cont++;
		}
		if (jogo.getJogo().contains(25)) {
			cont++;
		}
		if (cont >= mapaQuintos.get(5).intValue()) {
			contOcorreciaGrupos++;
		}

		if (contOcorreciaGrupos > 3) {
			return true;
		}
		contOcorreciaGrupos = 0;
		return false;

	}

	/////////////////////////////////////////////////////
	////////////////// FIM BOOLEANOS///////////////////////
	/////////////////////////////////////////////

	public void PrimeiroESegundoGrupos() throws FileNotFoundException, IOException {
		
		if (listaJogosPorDemanda.size() < 10) {
			return;
		}

		System.out.println("filtrando o primeiro e o segundo grupo...");

		List<Jogo> listaAuxilar = new ArrayList<Jogo>();
		listaAuxilar.addAll(getListaDeJogos());
		List<Jogo> numResult = new ArrayList<Jogo>();
		// List<Jogo> listaCheia = new ArrayList<Jogo>();

		List<Integer> numeros = new ArrayList<Integer>(estatisticasJogos().keySet());
		Jogo primeiroGrupo = new Jogo();
		Jogo segundoGrupo = new Jogo();
		int contNumeros = 1;
		for (Integer integer : numeros) {

			if (contNumeros < 13) {
				primeiroGrupo.getJogo().add(integer);
			} else {
				segundoGrupo.getJogo().add(integer);
			}

			contNumeros++;
		}
		
		for (Jogo jogo : listaAuxilar) {
			Set<Integer> intersectionPrimeiroGrupo = new HashSet<Integer>(jogo.getJogo());
			intersectionPrimeiroGrupo.retainAll(primeiroGrupo.getJogo());
			int indexPrimeiroGrupo  = intersectionPrimeiroGrupo.size();
			Set<Integer> intersectionSegundoGrupo = new HashSet<Integer>(jogo.getJogo());
			intersectionSegundoGrupo.retainAll(primeiroGrupo.getJogo());
			int indexSegundoGrupo  = intersectionSegundoGrupo.size();
			
			if ((indexPrimeiroGrupo > 6 && indexPrimeiroGrupo < 12) && (indexSegundoGrupo > 3 && indexSegundoGrupo < 9)){
				jogo.somaElimina();
				numResult.add(jogo);
			}
		}
		
		getListaDeJogos().clear();
		getListaDeJogos().addAll(numResult);
		System.out.println("Tamanho da lista depois de filtrar o primeiro e o segundo grupo..." + getListaDeJogos().size());

	}

	public static Map<Integer, Integer> estatisticasJogos() throws FileNotFoundException, IOException {

		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = listaJogosPorDemanda;
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

		Map<Integer, Integer> mapaNumerosOrdenados = sortByValue(mapaNumeros);

		for (Integer chave : mapaNumerosOrdenados.keySet()) {
			mapaNumerosOrdenados.put(chave, (mapaNumerosOrdenados.get(chave).intValue() * 100) / qtdJogosAnalisados);
		}

		return mapaNumerosOrdenados;

	}

	public static Jogo dezJogosMaisFrequentes(Map<Integer, Integer> mapaNumeros)
			throws FileNotFoundException, IOException {

		// 2=0.17 ; 3=1.88; 4=8.46; 5=23.61; 6=30.72; 7=23.38; 8=10.10; 9=1.64

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

		// for (Integer chave : mapaNumerosOrdenados.keySet()) {
		// System.out.println(chave + "-> " +
		// (mapaNumerosOrdenados.get(chave).intValue() * 100)/qtdJogosAnalisados
		// + "%");
		// }

	}

	public static Jogo dezJogosMenosFrequentes(Map<Integer, Integer> mapaNumeros)
			throws FileNotFoundException, IOException {

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

	public static Jogo cincoJogosDoMeio(Map<Integer, Integer> mapaNumeros) throws FileNotFoundException, IOException {

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

	public static List<Jogo> combinaDezUltimosPrimeiosMeio() throws FileNotFoundException, IOException {

		Map<Integer, Integer> mapaNumeros = estatisticasJogos();

		Jogo dezPrimeiros = dezJogosMaisFrequentes(mapaNumeros);
		Jogo dezUltimos = dezJogosMenosFrequentes(mapaNumeros);
		Jogo cincoDoMeio = cincoJogosDoMeio(mapaNumeros);

		Combinacao comb = new Combinacao(dezPrimeiros.getJogo(), 4);
		List<Jogo> combinacoesQuatroDezPrimeiros = new ArrayList<Jogo>();
		combinacoesQuatroDezPrimeiros.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezPrimeiros.getJogo(), 5);
		List<Jogo> combinacoesCincoDezPrimeiros = new ArrayList<Jogo>();
		combinacoesCincoDezPrimeiros.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezPrimeiros.getJogo(), 6);
		List<Jogo> combinacoesSeisDezPrimeiros = new ArrayList<Jogo>();
		combinacoesSeisDezPrimeiros.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezPrimeiros.getJogo(), 7);
		List<Jogo> combinacoesSeteDezPrimeiros = new ArrayList<Jogo>();
		combinacoesSeteDezPrimeiros.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezPrimeiros.getJogo(), 8);
		List<Jogo> combinacoesOitoDezPrimeiros = new ArrayList<Jogo>();
		combinacoesOitoDezPrimeiros.addAll(comb.geraListaCombinacoes());

		comb = new Combinacao(dezUltimos.getJogo(), 4);
		List<Jogo> combinacoesQuatroDezUltimos = new ArrayList<Jogo>();
		combinacoesQuatroDezUltimos.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezUltimos.getJogo(), 5);
		List<Jogo> combinacoesCincoDezUltimos = new ArrayList<Jogo>();
		combinacoesCincoDezUltimos.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezUltimos.getJogo(), 6);
		List<Jogo> combinacoesSeisDezUltimos = new ArrayList<Jogo>();
		combinacoesSeisDezUltimos.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezUltimos.getJogo(), 7);
		List<Jogo> combinacoesSeteDezUltimos = new ArrayList<Jogo>();
		combinacoesSeteDezUltimos.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(dezUltimos.getJogo(), 8);
		List<Jogo> combinacoesOitoDezUltimos = new ArrayList<Jogo>();
		combinacoesOitoDezUltimos.addAll(comb.geraListaCombinacoes());

		comb = new Combinacao(cincoDoMeio.getJogo(), 1);
		List<Jogo> combinacoesUmCincoMeio = new ArrayList<Jogo>();
		combinacoesUmCincoMeio.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(cincoDoMeio.getJogo(), 2);
		List<Jogo> combinacoesDoisCincoMeio = new ArrayList<Jogo>();
		combinacoesDoisCincoMeio.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(cincoDoMeio.getJogo(), 3);
		List<Jogo> combinacoesTresCincoMeio = new ArrayList<Jogo>();
		combinacoesTresCincoMeio.addAll(comb.geraListaCombinacoes());
		comb = new Combinacao(cincoDoMeio.getJogo(), 4);
		List<Jogo> combinacoesQuatroCincoMeio = new ArrayList<Jogo>();
		combinacoesQuatroCincoMeio.addAll(comb.geraListaCombinacoes());

		List<Jogo> listaCominacoes = new ArrayList<Jogo>();
		FiltroJogos filtro = new FiltroJogos();
		// 4 + 6 + 5
		filtro = new FiltroJogos(combinacoesQuatroDezPrimeiros, combinacoesSeisDezUltimos);
		for (Jogo jogo : filtro.getListaDeJogos()) {
			jogo.getJogo().addAll(cincoDoMeio.getJogo());
			listaCominacoes.add(jogo);
		}

		// 4 + 7 + 4
		filtro = new FiltroJogos(combinacoesQuatroDezPrimeiros, combinacoesSeteDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesQuatroCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 4 + 8 + 3
		filtro = new FiltroJogos(combinacoesQuatroDezPrimeiros, combinacoesOitoDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesTresCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 5 * 5 + 5
		filtro = new FiltroJogos(combinacoesCincoDezPrimeiros, combinacoesCincoDezUltimos);
		for (Jogo jogo : filtro.getListaDeJogos()) {
			jogo.getJogo().addAll(cincoDoMeio.getJogo());
			listaCominacoes.add(jogo);
		}
		// 5 + 6 + 4
		filtro = new FiltroJogos(combinacoesCincoDezPrimeiros, combinacoesSeisDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesQuatroCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 5 + 7 + 3
		filtro = new FiltroJogos(combinacoesCincoDezPrimeiros, combinacoesSeteDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesTresCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 5 + 8 + 2
		filtro = new FiltroJogos(combinacoesCincoDezPrimeiros, combinacoesOitoDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesDoisCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 6 + 4 + 5
		filtro = new FiltroJogos(combinacoesSeisDezPrimeiros, combinacoesQuatroDezUltimos);
		for (Jogo jogo : filtro.getListaDeJogos()) {
			jogo.getJogo().addAll(cincoDoMeio.getJogo());
			listaCominacoes.add(jogo);
		}
		// 6 + 5 + 4
		filtro = new FiltroJogos(combinacoesSeisDezPrimeiros, combinacoesCincoDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesQuatroCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 6 + 6 + 3
		filtro = new FiltroJogos(combinacoesSeisDezPrimeiros, combinacoesSeisDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesTresCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 6 + 7 + 2
		filtro = new FiltroJogos(combinacoesSeisDezPrimeiros, combinacoesSeteDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesDoisCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 6 + 8 + 1
		filtro = new FiltroJogos(combinacoesSeisDezPrimeiros, combinacoesOitoDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesUmCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 7 + 4 + 4
		filtro = new FiltroJogos(combinacoesSeteDezPrimeiros, combinacoesQuatroDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesQuatroCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 7 + 5 + 3
		filtro = new FiltroJogos(combinacoesSeteDezPrimeiros, combinacoesCincoDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesTresCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 7 + 6 + 2
		filtro = new FiltroJogos(combinacoesSeteDezPrimeiros, combinacoesSeisDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesDoisCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 7 + 7 + 1
		filtro = new FiltroJogos(combinacoesSeteDezPrimeiros, combinacoesSeteDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesUmCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 8 + 4 + 3
		filtro = new FiltroJogos(combinacoesOitoDezPrimeiros, combinacoesQuatroDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesTresCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 8 + 5 + 2
		filtro = new FiltroJogos(combinacoesOitoDezPrimeiros, combinacoesCincoDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesDoisCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());
		// 8 + 6 + 1
		filtro = new FiltroJogos(combinacoesOitoDezPrimeiros, combinacoesSeisDezUltimos);
		filtro = new FiltroJogos(filtro.getListaDeJogos(), combinacoesUmCincoMeio);
		listaCominacoes.addAll(filtro.getListaDeJogos());

		List<Jogo> listaRetorno = new ArrayList<Jogo>();

		for (Jogo jogo : listaCominacoes) {
			if (eRepetidos(9, 9, jogo)) {
				listaRetorno.add(jogo);
			}
		}

		return listaRetorno;
	}

	public static List<Jogo> analiseEstatisticasJogo() throws FileNotFoundException, IOException {

		List<Jogo> todosJogos = listaJogosPorDemanda;
		Map<Integer, Integer> mapaEstatisticasJogos = estatisticasJogos();
		Map<Integer, Integer> mapaEstatisticasLinhas = estatisticasLinhas();
		Map<Integer, Integer> mapaEstatisticasColunas = estatisticasColunas();

		Jogo dezPrimeiros = dezJogosMaisFrequentes(mapaEstatisticasJogos);
		Jogo cincoDoMeio = cincoJogosDoMeio(mapaEstatisticasJogos);
		Jogo dezUltimos = dezJogosMenosFrequentes(mapaEstatisticasJogos);

		// Estatisticas est = new Estatisticas();
		// List<Jogo> todosJogos = est.lerTodosOsJogos();
		Jogo ultimoJogo = todosJogos.get(todosJogos.size() - 1);

		Set<Integer> intersectionDezPrimeiros = new HashSet<Integer>(ultimoJogo.getJogo());
		intersectionDezPrimeiros.retainAll(dezPrimeiros.getJogo());
		Set<Integer> intersectionCincoDoMeio = new HashSet<Integer>(ultimoJogo.getJogo());
		intersectionCincoDoMeio.retainAll(cincoDoMeio.getJogo());
		Set<Integer> intersectionDezUltimos = new HashSet<Integer>(ultimoJogo.getJogo());
		intersectionDezUltimos.retainAll(dezUltimos.getJogo());

		// System.err.println(">>>>>> LINHAS <<<<<<");
		// System.out.println("Linhas: " + estatisticasLinhas());
		// System.err.println(">>>>>> COLUNAS <<<<<<");
		// System.out.println("Colunas: " + estatisticasColunas());
		// System.err.println(">>>>>> RANKING DOS DEZ ÚLTIMOS JOGOS <<<<<<");
		// System.out.println("Dez primeiros: " + dezPrimeiros.getJogo());
		// System.out.println("Cinco do meio: " + cincoDoMeio.getJogo());
		// System.out.println("Dez últimos: " + dezUltimos.getJogo());
		// System.err.println(">>>>>> COMPARAÇÃO DO RANKING COM O ÚLITMO JOGO
		// <<<<<<");
		// System.out.println("Dez primeiros: " +
		// intersectionDezPrimeiros.size());
		// System.out.println("Cinco do meio: " +
		// intersectionCincoDoMeio.size());
		// System.out.println("Dez últimos: " + intersectionDezUltimos.size());

		Jogo primeiroGrupo = new Jogo();
		Jogo segundoGrupo = new Jogo();
		Jogo terceiroGrupo = new Jogo();

		int primeiraLinha = mapaEstatisticasLinhas.get(1);
		int segundaLinha = mapaEstatisticasLinhas.get(2);
		int terceiraLinha = mapaEstatisticasLinhas.get(3);
		int quartaLinha = mapaEstatisticasLinhas.get(4);
		int quintaLinha = mapaEstatisticasLinhas.get(5);

		int primeiraColuna = mapaEstatisticasColunas.get(1);
		int segundaColuna = mapaEstatisticasColunas.get(2);
		int terceiraColuna = mapaEstatisticasColunas.get(3);
		int quartaColuna = mapaEstatisticasColunas.get(4);
		int quintaColuna = mapaEstatisticasColunas.get(5);

		int ultimoGrupo = 3;
		boolean existe = false;
		int j = 0;
		int k = 0;
		int m = 1;
		List<Integer> linhasColunas = new ArrayList<Integer>();
		linhasColunas.add(primeiraLinha);
		linhasColunas.add(primeiraColuna);
		linhasColunas.add(segundaLinha);
		linhasColunas.add(segundaColuna);
		linhasColunas.add(terceiraLinha);
		linhasColunas.add(terceiraColuna);
		linhasColunas.add(quartaLinha);
		linhasColunas.add(quartaColuna);
		linhasColunas.add(quintaLinha);
		linhasColunas.add(quintaColuna);

		Jogo numerosPrimeiraLinha = new Jogo(Arrays.asList(1, 2, 3, 4, 5));
		Jogo numerosSegundaLinha = new Jogo(Arrays.asList(6, 7, 8, 9, 10));
		Jogo numerosTerceiraLinha = new Jogo(Arrays.asList(11, 12, 13, 14, 15));
		Jogo numerosQuartaLinha = new Jogo(Arrays.asList(16, 17, 18, 19, 20));
		Jogo numerosQuintaLinha = new Jogo(Arrays.asList(21, 22, 23, 24, 25));

		Jogo numerosPrimeiraColuna = new Jogo(Arrays.asList(1, 6, 11, 16, 21));
		Jogo numerosSegundaColuna = new Jogo(Arrays.asList(2, 7, 12, 17, 22));
		Jogo numerosTerceiraColuna = new Jogo(Arrays.asList(3, 8, 13, 18, 23));
		Jogo numerosQuartaColuna = new Jogo(Arrays.asList(4, 9, 14, 19, 24));
		Jogo numerosQuintaColuna = new Jogo(Arrays.asList(5, 10, 15, 20, 25));

		List<Integer> numeros = new ArrayList<Integer>(mapaEstatisticasJogos.keySet());
		while (k < linhasColunas.size()) {
			int tamanhoLaco = linhasColunas.get(k);

			for (Integer l = 1; l <= tamanhoLaco; l++) {

				int qtdNumeros = 0;
				Jogo linhaOuColuna = new Jogo();

				if (k == 0) {
					linhaOuColuna.getJogo().addAll(numerosPrimeiraLinha.getJogo());
				}
				if (k == 1) {
					linhaOuColuna.getJogo().addAll(numerosPrimeiraColuna.getJogo());
				}
				if (k == 2) {
					linhaOuColuna.getJogo().addAll(numerosSegundaLinha.getJogo());
				}
				if (k == 3) {
					linhaOuColuna.getJogo().addAll(numerosSegundaColuna.getJogo());
				}
				if (k == 4) {
					linhaOuColuna.getJogo().addAll(numerosTerceiraLinha.getJogo());
				}
				if (k == 5) {
					linhaOuColuna.getJogo().addAll(numerosTerceiraColuna.getJogo());
				}
				if (k == 6) {
					linhaOuColuna.getJogo().addAll(numerosQuartaLinha.getJogo());
				}
				if (k == 7) {
					linhaOuColuna.getJogo().addAll(numerosQuartaColuna.getJogo());
				}
				if (k == 8) {
					linhaOuColuna.getJogo().addAll(numerosQuintaLinha.getJogo());
				}
				if (k == 9) {
					linhaOuColuna.getJogo().addAll(numerosQuintaColuna.getJogo());
				}

				for (Integer integer : primeiroGrupo.getJogo()) {
					if (linhaOuColuna.getJogo().contains(integer)) {
						qtdNumeros++;
					}
				}

				for (Integer integer : segundoGrupo.getJogo()) {
					if (linhaOuColuna.getJogo().contains(integer)) {
						qtdNumeros++;
					}
				}

				for (Integer integer : terceiroGrupo.getJogo()) {
					if (linhaOuColuna.getJogo().contains(integer)) {
						qtdNumeros++;
					}
				}

				if (qtdNumeros == tamanhoLaco) {
					break;
				}

				if (ultimoGrupo == 3) {
					j = 0;
				} else if (ultimoGrupo == 1) {
					j = 10;
				} else {
					j = 15;
				}
				for (int i = j; i < numeros.size(); i++) {

					existe = linhaOuColuna.getJogo().contains(numeros.get(i));

					if (existe && i < 10 && ultimoGrupo == 3 && !primeiroGrupo.getJogo().contains(numeros.get(i))) {
						ultimoGrupo = 1;
						primeiroGrupo.getJogo().add(numeros.get(i));
						break;
					} else {
						ultimoGrupo = 1;
					}
					if (existe && i > 9 && i < 15 && ultimoGrupo == 1
							&& !segundoGrupo.getJogo().contains(numeros.get(i))) {
						ultimoGrupo = 2;
						segundoGrupo.getJogo().add(numeros.get(i));
						break;

					} else {
						ultimoGrupo = 2;
					}

					if (existe && i > 14 && ultimoGrupo == 2 && !terceiroGrupo.getJogo().contains(numeros.get(i))) {
						ultimoGrupo = 3;
						terceiroGrupo.getJogo().add(numeros.get(i));
						break;

					} else {
						ultimoGrupo = 3;
					}

				}
			}
			k++;
		}

		// }

		Jogo numerosPrimeiroGrupo = new Jogo();
		Jogo numerosSegundoGrupo = new Jogo();
		Jogo numerosTerceiroGrupo = new Jogo();
		Jogo jogoRetorno = new Jogo();
		int contNumeros = 1;
		for (Integer integer : numeros) {

			if (contNumeros < 13)
				jogoRetorno.getJogo().add(integer);
			// if (contNumeros > 9 && contNumeros < 15)
			if (contNumeros > 16)
				jogoRetorno.getJogo().add(integer);
			// if (contNumeros > 14 && contNumeros < 25)
			// numerosTerceiroGrupo.getJogo().add(integer);
			if (jogoRetorno.getJogo().size() > 18)
				break;
			contNumeros++;
		}

		// while (primeiroGrupo.getJogo().size() + segundoGrupo.getJogo().size()
		// + terceiroGrupo.getJogo().size() < 19) {
		// if (primeiroGrupo.getJogo().size() < 10) {
		// for (Integer integer : numeros) {
		// if (!primeiroGrupo.getJogo().contains(integer)) {
		// primeiroGrupo.getJogo().add(integer);
		// break;
		// }
		// }
		// continue;
		// }
		//
		// if (segundoGrupo.getJogo().size() < 3) {
		// for (Integer integer : numeros) {
		// if (!segundoGrupo.getJogo().contains(integer)) {
		// segundoGrupo.getJogo().add(integer);
		// break;
		// }
		// }
		// continue;
		//
		// }
		//
		// if (terceiroGrupo.getJogo().size() < 6) {
		// for (Integer integer : numeros) {
		// if (!terceiroGrupo.getJogo().contains(integer)) {
		// terceiroGrupo.getJogo().add(integer);
		// break;
		// }
		// }
		// continue;
		//
		// }
		// }

		// while (jogoRetorno.getJogo().size() < 19){
		// for (Integer integer : numeros) {
		// if (!jogoRetorno.getJogo().contains(integer)){
		// jogoRetorno.getJogo().add(integer);
		// break;
		// }
		// }
		//
		// }

		// jogoRetorno.getJogo().addAll(primeiroGrupo.getJogo());
		// jogoRetorno.getJogo().addAll(segundoGrupo.getJogo());
		// jogoRetorno.getJogo().addAll(terceiroGrupo.getJogo());

		Collections.sort(jogoRetorno.getJogo());
		Combinacao combinacoes = new Combinacao(jogoRetorno.getJogo(), 15);
		List<Jogo> jogos = new ArrayList<Jogo>();
		jogos.addAll(combinacoes.geraListaCombinacoes());
		for (Jogo jogo : jogos) {
			Collections.sort(jogo.getJogo());
		}
		return jogos;

	}

	public static TreeMap<Integer, Integer> estatisticasLinhas() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();

		int qTDJogosAnalisados = 9;

		Integer primeiroQuinto = 0;
		Integer segundoQuinto = 0;
		Integer terceiroQuinto = 0;
		Integer quartoQuinto = 0;
		Integer quintoQuinto = 0;

		TreeMap<Integer, Integer> mapaQuintos = new TreeMap<Integer, Integer>();

		for (int posicao = todosJogos.size() - 1; posicao > todosJogos.size() - (qTDJogosAnalisados + 1); posicao--) {
			// System.out.println(todosJogos.get(posicao).getJogo());
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

		mapaQuintos.put(1, primeiroQuinto / qTDJogosAnalisados);
		mapaQuintos.put(2, segundoQuinto / qTDJogosAnalisados);
		mapaQuintos.put(3, terceiroQuinto / qTDJogosAnalisados);
		mapaQuintos.put(4, quartoQuinto / qTDJogosAnalisados);
		mapaQuintos.put(5, quintoQuinto / qTDJogosAnalisados);

		return mapaQuintos;
	}

	public static TreeMap<Integer, Integer> estatisticasColunas() throws FileNotFoundException, IOException {
		Estatisticas est = new Estatisticas();
		List<Jogo> todosJogos = est.lerTodosOsJogos();

		int qTDJogosAnalisados = 9;

		Integer primeiroQuinto = 0;
		Integer segundoQuinto = 0;
		Integer terceiroQuinto = 0;
		Integer quartoQuinto = 0;
		Integer quintoQuinto = 0;

		TreeMap<Integer, Integer> mapaQuintos = new TreeMap<Integer, Integer>();

		for (int posicao = todosJogos.size() - 1; posicao > todosJogos.size() - (qTDJogosAnalisados + 1); posicao--) {
			// System.out.println(todosJogos.get(posicao).getJogo());
			for (Integer numero : todosJogos.get(posicao).getJogo()) {

				if (numero == 1 || numero == 6 || numero == 11 || numero == 16 || numero == 21) {
					primeiroQuinto++;
				}

				if (numero == 2 || numero == 7 || numero == 12 || numero == 17 || numero == 22) {
					segundoQuinto++;
				}

				if (numero == 3 || numero == 8 || numero == 13 || numero == 18 || numero == 23) {
					terceiroQuinto++;
				}

				if (numero == 4 || numero == 9 || numero == 14 || numero == 19 || numero == 24) {
					quartoQuinto++;
				}

				if (numero == 5 || numero == 10 || numero == 15 || numero == 20 || numero == 25) {
					quintoQuinto++;
				}

			}
		}

		mapaQuintos.put(1, primeiroQuinto / qTDJogosAnalisados);
		mapaQuintos.put(2, segundoQuinto / qTDJogosAnalisados);
		mapaQuintos.put(3, terceiroQuinto / qTDJogosAnalisados);
		mapaQuintos.put(4, quartoQuinto / qTDJogosAnalisados);
		mapaQuintos.put(5, quintoQuinto / qTDJogosAnalisados);

		return mapaQuintos;
	}
}
