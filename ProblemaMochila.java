import java.util.Arrays; 
import java.util.Comparator;
import java.util.Random; 

public class ProblemaMochila{ 

	public ProblemaMochila(int tamanho){
		int[] peso = new int[tamanho];
		int[] valor = new int[tamanho];
		int capacidade = 0;
		Random random = new Random();

		for(int i = 0; i < tamanho; i ++){
			peso[i] = random.nextInt(1000);
			valor[i] = random.nextInt(1000);
			capacidade += valor[i];
			capacidade = capacidade / 2;
		}

		double valorMaximo = getValorMaximo(peso, valor, capacidade); 
		System.out.println("Valor maximo = " + valorMaximo); 
	}

	private static double getValorMaximo(int[] peso, int[] valor, int capacidade){ 
		ItemValor[] iValor = new ItemValor[peso.length]; 

		for(int i = 0; i < peso.length; i++){ 
			iValor[i] = new ItemValor(peso[i], valor[i], i); 
		} 

		Arrays.sort(iValor, new Comparator<ItemValor>(){ 

			@Override
			public int compare(ItemValor o1, ItemValor o2){ 
				return o2.custo.compareTo(o1.custo) ; 
			} 
		}
				); 

		double totalValor = 0d; 

		for(ItemValor i: iValor){ 
			int peso1 = (int) i.ps; 
			int valor1 = (int) i.vl; 

			if (capacidade - peso1 >= 0){ 
				capacidade = capacidade-peso1; 
				totalValor += valor1; 
			} 
			else{ 
				double fraction = ((double)capacidade/(double)peso1); 
				totalValor += (valor1*fraction); 
				capacidade = (int)(capacidade - (peso1*fraction)); 
				break; 
			} 
		} 

		int tempoInicio = 0;

		System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));

		return totalValor; 
	} 

	static class ItemValor{ 
		Double custo; 
		double ps, vl, id; 

		public ItemValor(int ps, int vl, int id){ 
			this.ps = ps; 
			this.vl = vl; 
			this.id = id; 
			custo = new Double(vl/ps ); 
		} 
	} 
}
