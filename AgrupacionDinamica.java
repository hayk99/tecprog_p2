//Similar a los includes de C++, traemos aquellas clases que nos seran utiles
import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

//En Java, no existe el concepto de pre-declaracion ni de funcion. Java es un 
//lenguaje puramente orientado a objetos, por lo que todo son clases.
//
//El uso de programación paramétrica no requiere ninguna palabra clave. Directamente ponemos
//el tipo parámetro entre corchetes.
//Para hacer uso de iteradores, implementamos el interfaz Iterable<T>. Esto es un mecanismo
//básico de la herencia en Java que es inevitable en este caso y sobre el que hablaremos
//más adelante en clase de teoría.
public class Agrupacion<T> implements Iterable<T> {
	//Esta es la única forma que tiene Java de declarar valores inmutables.
	private static final int MAX = 40; 

	//Habrás notado que en Java no hay bloques "private" y "public", sino que es una palabra clave
	//que se pone en cada elemento (atributo o método).
	private T[] datos;
	private int total;

	//El constructor de Java llama a los constructores de los parámetros dentro del bloque de código.
	@SuppressWarnings("unchecked")
	public Agrupacion() {
		datos = (T[])(new Object[MAX]);
		total = 0;
	}

	public boolean anyadir(T t) {
		//TODO: Rellena el código de este método para que anyada un elemento a la agrupacion
		//y devuelva si ha sido posible (o no) meterlo.
		boolean sePuede = total < MAX;
		if (sePuede) {
			datos[total] = t;
			total++;
		}
		return sePuede;
	}

	public boolean borrarUltimo() {
		//TODO: Rellena el código de este método para que borre el último elemento de la agrupación
		//y devuelva si ha sido posible (o no) borrarlo.
		boolean sePuede = this.total > -1;
		if (sePuede) this.total--;
		return sePuede;
	}

	//Esta clase representa a un iterador sobre la agrupación. De nuevo, por el comportamiento estándar de los
	//iteradores en Java, deberemos utilizar la herencia.
	private class IteradorAgrupacion<T> implements Iterator<T> {
		//Aquí declaramos los atributos
		Agrupacion<T> ag;
		int i;

		//Este es el constructor del iterador.
		private IteradorAgrupacion(Agrupacion<T> ag) {
			this.ag = ag;
			i = ag.total - 1;
		}

		//Todos los iteradores deben de implementar un método que devuelva
		//si hay siguiente elemento (o no).
		public boolean hasNext() {
			//TODO: Devuelve si hay siguiente elemento o no.
			return (i != -1);
		}

		//Todos los iteradores deben de implementar un método que devuelva el elemento
		//en la posción actual y avance el iterador. Por construcción, este método debe
		//lanzar una excepción si no existe el siguiente elemento.
		//Recuerda que se recorren desde el último al primero.
		public T next() throws NoSuchElementException {
			//Aquí lanzamos la excepción
			if (!hasNext()) throw new NoSuchElementException();
			else {
				//TODO: Devuelve el elemento apuntado por el iterador, y avanza el iterador.
				i--;
				return ag.datos[i+1];

			}
		}

		//Los iteradores en Java tienen que tener este método, también. Para no implementarlo (no lo vamos
		//a usar y nuestra definición de agrupación no lo incluye) simplemente lanzamos una excepción.
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
	}

	//Este método de la estructura de datos simplemente devuelve un nuevo iterador con el que recorrerse la estructura de datos.
	public Iterator<T> iterator() {
		return new IteradorAgrupacion<T>(this);
	}
}
