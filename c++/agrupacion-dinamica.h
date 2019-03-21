#ifndef AGRUPACION_H
#define AGRUPACION_H
#include <iostream>
using namespace std;
// Interfaz del TAD agrupación genérico. Pre-declaraciones:9

template<typename T>
class agrupacion_dinamica{
	
private: 
	//Mantenemos aqui los mismos atributos privados, ignorando aquellos que tengan que ver
	//con el iterador
	class nodo{
		public:
			nodo* ant;
			T data;
	};
	nodo* tail;
	int total;

public:
//	Ahora la funcion iniciar de la estructura es el constructor. 
	agrupacion_dinamica() {
		tail = nullptr;
		total = 0;
	}

	~agrupacion_dinamica(){
		nodo* aux = tail;
		while(aux != nullptr){
			nodo* aux2 = aux -> ant;
			delete aux;
			aux = aux2;
		}
	}

//	TODO: La funcion anyadir ahora es el metodo anyadir. Rellénalo. Para acceder a atributos
//	y métodos de la propia clase, deberás hacerlo a través del puntero this->
	void anyadir(const T& p) {
		if (total == 0) {
			tail = new nodo;
			tail -> ant = nullptr;
		}
		else {
			nodo* newtail = new nodo;
			newtail -> ant = tail;
			tail = newtail;
		}
		tail -> data = p;
		total++;
	}

//	TODO: La funcion borrarUltimo se transforma tambien en un metodo. Rellénalo.
	bool borrarUltimo() {
		bool sePuede = total > 0;
		if (sePuede) {
			nodo* borrar = tail;
			tail = tail -> ant;
			total--;
			delete borrar;
		}
		return sePuede;
	}

	//Declarando la clase const_iterator (iterador constante) como friend y como
	//clase local, conseguimos que pueda haber varios iteradores, cada uno de ellos
	//llevando su propio "puntero" a que parte de la estructura estamos
	friend class const_iterator;
       
	class const_iterator {
	private:
		//Al separar el iterador en una clase aparte, trasladamos los miembros privados que estaban
		//en el struct a esta clase. Además, mantenemos una referencia a la agrupacion.
		const agrupacion_dinamica<T>& c; //puntero a alto nivel, es como si copias por valor, pero es solo un ptr
		// este const de arriba impide modificar los elementos de la agrupación
		nodo* iter;
	public:
		//Este constructor sirve como inicializador del iterador, tanto al principio como al final.
		const_iterator(const agrupacion_dinamica& c_, nodo* iter_): c(c_), iter(iter_){
			//iter = *(c.tail); // puedes porqué c es una clase friend
		}
		
	//	En la definición por defecto de los iteradores, se separa el avance del iterador
	//	a la obtención del elemento apuntado por el iterador (que en la otra implementación ocurren
	//	en la misma función.
	
		//Este método redefine el operador de pre-incremento (++x).
		//Representa el avance del iterador.
		const_iterator& operator++() { 	
			iter = (iter ->ant);
			return *this;
		}

		//Este método redefine el operador de "apuntado" (*x)
		//Representa la obtención de lo apuntado por el iterador.
		const T& operator*()   const {
			return (iter -> data);
		} 


	//	En la definición por defecto de los iteradores, no existe la comprobación de si existe
	//	siguiente elemento. Por defecto las estructuras de datas devuelven iteradores al principio y al final,
	//	y para recorrer la estructura se compara el iterador que avanza con el iterador que apunta al final
	//	de la estructura.
	//
		bool operator!=(const const_iterator& that) const { 
			return ( (that.iter) != (this->iter) || ( &c != &(that.c)) );
		}		

	};

	//Observa como nuestra agrupacion permite generar dos iteradores: uno al principio y otro al final.
	//El iterador que apunta al final de la agrupación se mantiene en este caso únicamente para hacer
	//la comprobación de que "existe siguiente".
	//Date cuenta que los valores que le pasamos como índice del iterador son para que se recorra la
	//estructura desde el último elemento (this->total - 1) hasta el primero (0).
	//begin y end llaman al constructor. Indican al bucle de recorrido dónde empezar y dónde acabar
	const_iterator begin() const { return const_iterator(*this , tail); }
	const_iterator end()   const { return const_iterator(*this, nullptr); }
};

#endif //fin de agrupacion.h
