package br.com.sorteio.util;

public class Split {
	
	
	public static void main (String[] args){
		String string = "jogos.add(new Jogo(Arrays.asList(1, 3, 4, 5, 7, 9, 10, 14, 15, 16, 19, 20, 21, 23, 24))) # Repetidos # 8 # Pares # 9 # Primos # 5 # Fibonacci # 4 # Quadrado # 11 # Multiplos de Três # 5 # Dez primeiros est. # 4 # Cinco do meio esta. #  2 # Dez últimos est. # 9";
		String[] parts = string.split("#");
		
		System.out.println(parts[0]);
		
	}
	 
	
	

}
