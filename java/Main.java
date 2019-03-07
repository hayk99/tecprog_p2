public class Main
{
	//TODO: Define la cabecera de este metodo aprovechando la herencia para que sea equivalente
	//a declarar los dos metodos siguientes: 
	//public static void anyadir_elementos(AgrupacionEstatica<Integer> ag)
	//public static void anyadir_elementos(AgrupacionDinamica<Integer> ag)
	{
		ag.anyadir(42);
		ag.anyadir(15);
		ag.borrarUltimo();
		for (int i = 0; i < 100; i+=5) ag.anyadir(i);
	}

	//TODO: Define la cabecera de este metodo aprovechando la herencia para que sea equivalente
	//a declarar los dos metodos siguientes: 
	//public static void mostrar_elementos(AgrupacionEstatica<Integer> ag)
	//public static void mostrar_elementos(AgrupacionDinamica<Integer> ag)
	{
		for (Integer i : ag) 
			System.out.print(i+" ");
		System.out.println();
	}

	public static void main(String[] args)
	{
		AgrupacionEstatica<Integer> ag_e = new AgrupacionEstatica<Integer>();
		AgrupacionDinamica<Integer> ag_d = new AgrupacionDinamica<Integer>();

		anyadir_elementos(ag_e);
		anyadir_elementos(ag_d);

		mostrar_elementos(ag_e);
		mostrar_elementos(ag_d);
	}
}
