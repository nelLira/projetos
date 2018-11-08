package br.com.sorteio.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import br.com.sorteio.numeros.Jogo;

public class Estatisticas {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// lerTodosOsJogos();
	}

	public List<Jogo> lerTodosOsJogos() throws FileNotFoundException, IOException {

		// capturar todos os resultados pelo arquivo txt

		// testes
		String path = ClassLoader.getSystemResource("").getPath() + "/lotofacil.txt";

		// produção
		// String path = "lotofacil.txt";

		File file = new File(path);
		byte[] bytes = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);

		fis.read(bytes);
		fis.close();

		String[] valueStr = new String(bytes).trim().split("\\s+");

		List<Jogo> jogos = new ArrayList<Jogo>();

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

	public List<Jogo> Repetidos(List<Jogo> jogos, int QtdRep) throws FileNotFoundException, IOException {

		List<Jogo> numResult = new ArrayList<Jogo>();
		Jogo numAux = null;
		for (Jogo n : jogos) {
			if (numAux == null) {
				numAux = n;
			} else {
				Set<Integer> intersection = new HashSet<Integer>(numAux.getJogo());
				intersection.retainAll(n.getJogo());
				if (intersection.size() == QtdRep) {
					numResult.add(n);
				}
				numAux = n;
			}
		}

		return numResult;
	}

	public void numerosPares(List<Jogo> jogos) throws FileNotFoundException, IOException {

		TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();

		int cont = 0;
		for (Jogo n : jogos) {
			for (Integer i : n.getJogo()) {
				if (i % 2 == 0)
					cont++;

			}
			if (trmap.containsKey(cont)) {
				int valor = trmap.get(cont).intValue();
				valor++;
				trmap.put(cont, valor);

			} else {
				trmap.put(cont, 1);
			}

			cont = 0;

		}

		System.out.println(trmap.toString());
	}

	public void estatResultados(List<Jogo> jogosFiltrados, Jogo resultado) throws FileNotFoundException, IOException {

		TreeMap<Integer, Integer> trmap = new TreeMap<Integer, Integer>();

		int cont = 0;

		for (Jogo n : jogosFiltrados) {
			Set<Integer> intersection = new HashSet<Integer>(resultado.getJogo());
			intersection.retainAll(n.getJogo());
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

	public List<Jogo> listaNumerosRepetidosJogoAnterior() throws FileNotFoundException, IOException {

		List<Jogo> jogos = lerTodosOsJogos();

		List<Jogo> numResult = new ArrayList<Jogo>();
		Jogo numAux = null;
		for (Jogo n : jogos) {
			if (numAux == null) {
				numAux = n;
			} else {
				Set<Integer> intersection = new HashSet<Integer>(numAux.getJogo());
				intersection.retainAll(n.getJogo());

				if (intersection.size() == 9) {
					Iterator<Integer> iterator = intersection.iterator();
					List<Integer> lista = new ArrayList<Integer>();
					if (iterator.hasNext()) {
						while (iterator.hasNext()) {
							lista.add(iterator.next());
						}
					}
					numResult.add(new Jogo(lista));
					lista = new ArrayList<Integer>();
				}
				numAux = n;
			}
		}
		return numResult;
	}

	public List<Jogo> listaJogosNumerosRepetidosJogoAnterior() throws FileNotFoundException, IOException {

		List<Jogo> jogos = lerTodosOsJogos();

		List<Jogo> numResult = new ArrayList<Jogo>();
		Jogo numAux = null;
		for (Jogo n : jogos) {
			if (numAux == null) {
				numAux = n;
			} else {
				Set<Integer> intersection = new HashSet<Integer>(numAux.getJogo());
				intersection.retainAll(n.getJogo());

				if (intersection.size() == 9) {
					List<Integer> lista = new ArrayList<Integer>();
					for (Integer numero : intersection) {
						lista.add(numero);
					}
					// numResult.add(new Jogo(n.getJogo()));
					numResult.add(new Jogo(lista));

				}
				numAux = n;
			}
		}
		return numResult;
	}

	public List<Jogo> filtraNumerosParesDentroDaEstatistica(List<Jogo> jogos, int maiorQue, int menorQue)
			throws FileNotFoundException, IOException {
		System.out.println("Filtrando os números pares...");
		int cont = 0;
		List<Jogo> numResult = new ArrayList<Jogo>();
		for (Jogo n : jogos) {
			for (Integer lista : n.getJogo()) {
				if (lista % 2 == 0) {
					cont++;
				}
			}
			if (cont > maiorQue && cont < menorQue) {
				numResult.add(n);
			}
			cont = 0;
		}
		if (numResult.size() == 0) {
			System.out.println("filtraNumerosParesDentroDaEstatistica [sem lista de números]");
		}
		;
		return numResult;
	}

	public int quantidadeNumerosRepetidos(Jogo strUltimoSorteio, Jogo strSorteioAnterior) {
		Set<Integer> intersection = new HashSet<Integer>(strSorteioAnterior.getJogo());
		intersection.retainAll(strUltimoSorteio.getJogo());
		return intersection.size();

	}

}
