package risk;
import java.util.Scanner;
import java.math.*;
public class risk {
	/**
	 * DEFINICIÓ DE CONSTANTS
	 */
	/**
	 * Véctor (array) amb els noms dels continents. La posició del continent
	 * dins del vector l'identifica en les diferents matrius o arrays on es
	 * relaciona. Seria la seva clau primària.
	 */
	public static final String[] continents = { "Amèrica Nord", "Amèrica Sud", "Àfrica", "Europa", "Àsia", "Oceania" };
	/**
	 * Vector (array) amb els noms dels territoris. Es relacionen amb el seu
	 * continent ja que el nombre de fila correspon a la posició del array
	 * continents.
	 */
	public static final String[] territoris = { "Alaska", "Territorio del nor-oeste", "Groenlandia", "Alberta",
			"Ontario", "Labrador", "Territorio del oeste", "Territorio del este", "America central", "Venezuela",
			"Perú", "Argentina", "Brasil", "África del norte", "Egipto", "Africa Oriental", "Congo", "África del sur",
			"Magadascar", "Europa Occidental", "Gran Bretaña", "Islandia", "Escandinavia", "Europa del norte",
			"Europa del sur", "Ucrania", "Ural", "Afganistan", "Oriente Medio", "Siberia", "Yakutia", "Chita",
			"Kamchatka", "Mongolia", "Japon", "China", "Siam", "India", "Indonesia", "Nueva Guinea",
			"Australia Occidental", "Australia Oriental" };
	/**
	 * Matriu (array de dues dimensions) que ens permet identificar els païssos
	 * veïns i així poder moure exèrcits entre ells o atacar. Segons moment de
	 * la partida.
	 */
	// public static final String[][] fronteres={{"Alaska,Kamchatka"}};
	public static final String[][] veins = { { "Alaska,Kamchatka" } };
	/**
	 * Véctor (array) amb els objectius del joc. La posició de l'objectiu dins
	 * del vector l'identifica en les diferents matrius o arrays on es
	 * relaciona. Seria la seva clau primària.
	 */
	public static final String[] objectius = { "Amèrica sur i Àfrica", "Amèrica del nord i Oceania", "Àfrica i Àsia" };
	/**
	 * Véctor (array) amb la quantitat d'exèrcits inicials. En la posició 0
	 * correspon a 3 jugadors i la posició 3 a 6 jugadors.
	 */
	public static final int[] exercicitsInicials = { 35, 30, 25, 20 };
	/**
	 * Véctor (array) amb la quantitat d'exèrcits que guanyes per continent
	 * conquistat. En la posició 0 correspon a Amèrica del Nord i la 5 a
	 * Oceania.
	 */
	public static final int[] continentsComplets = { 5, 2, 3, 5, 7, 2 };
	/**
	 * Nombre que divideix els païssos conquerits per saber les unitats de
	 * reforç.
	 */
	public static final int divisioTerritori = 3;
	/**
	 * Nombre màxims de jugadors que poden jugar al DamRISK.
	 */
	public static final int maxJugadors = 6;
	/**
	 * Nombre mínim de jugadors que poden jugar al DamRISK.
	 */
	public static final int minJugadors = 3;
	public static void main(String[] args) {
		// variables
		int cantidadJugadores;
		int ejercitoInicial;
		Scanner lector = new Scanner(System.in);
		/**
		 * Matriu que representa el tauler de joc. Cada posició té un array on
		 * es guarda la informació següent {Id jugador, Número exercits} Cada
		 * posició és un territori.
		 */
		int[][] tauler = new int[42][2];
		/**
		 * Inicialitzem el tauler sense jugadors, valor -1. Doncs el jugador 0
		 * existeix
		 */
		for (int territori = 0; territori < tauler.length; territori++) {
			tauler[territori][0] = -1;
		}
		/**
		 * Vector per guardar els noms dels jugadors. La posició dins del vector
		 * és l'identificador de jugador.
		 */
		String[] nomsJugadors = null;
		// Codi per demanar el nombre de jugadors
		// Dimensionar els vectors nomsJugadors i infoJugadors
		cantidadJugadores = saberNombreJugadors();
		nomsJugadors = new String[cantidadJugadores];
		/**
		 * Matriu on guardem la informació del joc per a cada jugador. On
		 * guardem la informació del jugador. Per a cada jugador guardem
		 * nomsJugadors[0] li correspon la infoJugadors {objectiu, cavalleria,
		 * artilleria, cano, comodi }
		 */
		int[][] infoJugadors = new int[cantidadJugadores][5];
		/**
		 * Enter que indicarà el nombre de jugador que li toca tirar.
		 */
		int torn = 0;
		/**
		 * Java.util.Scanner ens permet llegir de consola les dades dels usuaris
		 */
		// Calcular nombre d'exèrcits inicials
		ejercitoInicial = saberNombreTropes(cantidadJugadores);
		//  Demanar les dades als jugadors i preparar-los per poder iniciar
		// el joc.
		for (int jugador = 0; jugador < nomsJugadors.length; jugador++) {
			System.out.println("Jugador: " + jugador + ".");
			// Demanar el nom i guardar-ho en el vector
			nomsJugadors[jugador] = asignarNombre();
			System.out.println(nomsJugadors[jugador]);
			// Assignar objetiu
			// infoJugadors[jugador][0]=asignarMision(cantidadJugadores);
			// System.out.println(infoJugadors[jugador][0]);
		}
		System.out.println("Empieza el jugador " + torn);
		// Repartir territorios
		repartirTerritorios(tauler, cantidadJugadores);
		
		// Mostrar territorios
		for (int i = 0; i < (tauler.length) - 1; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(tauler[i][j] + " ");
				if (j == (1)) {
					System.out.println(" ");
				}
			}
		}
		
		//  Assignar tropes
		System.out.println("hola");
		asignarEjercitos(tauler, cantidadJugadores, exercicitsInicials);
		System.out.println("2");
		
		// Decidir qui comença a jugar i dir-ho per pantalla
		torn = random(cantidadJugadores);
		/**
		 * Pinta el tauler com ha quedat
		 */
		for (int territori = 0; territori < tauler.length; territori++) {
			System.out.println(
					territoris[territori] + "-" + nomsJugadors[tauler[territori][0]] + "-" + tauler[territori][1]);
		}
	}
	private static void asignarEjercitos(int tauler[][],int cantidadJugadores, int exercicitsInicials[]) {
		int territori = 0;
		int jugador=0;
		int eleccion = 0;
		int quedan=exercicitsInicials[cantidadJugadores-3];
		
		do{
			quedan=exercicitsInicials[cantidadJugadores-3];
			territori=0;
			
			do{
				if(tauler[territori][0]==jugador){
					tauler[territori][1] = 1;
					quedan--;
				}
				
				territori++;
			}while(territori<tauler.length);
			territori=0;
			
			do{
				
			if(tauler[territori][0]==jugador){
				do{
					Scanner lector = new Scanner(System.in);
					System.out.println("Cuantos tropas quieres asignar de "+0+" a "+quedan+"?");
					System.out.println("territori"+territori);
					if (lector.hasNextInt()) {
						eleccion = lector.nextInt();
						if(eleccion>0 && eleccion<=quedan ){
							tauler[territori][1] = eleccion;
							quedan = quedan - eleccion;
						}
					} else {
						System.out.println("No válido");
					}
				}while((quedan>0 && eleccion<0) || (eleccion>quedan && quedan>0));
				
			}
				territori++;
				
			}while(territori<=tauler.length && quedan!=0);
			jugador++;
			
		}while(jugador<cantidadJugadores);
		
		System.out.println("fin");
	}
	private static void repartirTerritorios(int[][] tauler, int cantidadJugadores) {
		int contador = 0;
		int jugador = 0;
		int eleccion = 0;
		int contador1=0;
		int contador2=0;
		int contador3=0;
		int contador4=0;
		int contador5=0;
		
		
		do {
			jugador = 0;
			do {
				eleccion = random(42)-1;
				if (tauler[eleccion][0] == -1) {
					tauler[eleccion][0] = jugador;
					
					if(jugador==0){
						contador1++;
					}
					if(jugador==1){
						contador2++;
					}
					if(jugador==2){
						contador3++;
					}
					if(jugador==3){
						contador4++;
					}
					if(jugador==4){
						contador5++;
					}
					contador++;
					jugador++;
					
					System.out.println("Elección: " + eleccion + " Jugador: " + jugador);
				}
				
			} while (jugador < cantidadJugadores && contador < 42);
			System.out.println("j1 "+contador1);
			System.out.println("j2 "+contador2);
			System.out.println("j3 "+contador3);
			System.out.println("j4 "+contador4);
			System.out.println("j5 "+contador5);
		} while (contador < 42);
	}
	private static int random(int cantidadJugadores) {
		return (int) (Math.random() * (cantidadJugadores) + 1);
	}
	private static String asignarNombre() {
		Scanner lector = new Scanner(System.in);
		lector = new Scanner(System.in);
		System.out.println("Nombre?");
		return lector.next();
	}
	private static int saberNombreTropes(int cantidadJugadores) {
		return exercicitsInicials[cantidadJugadores - 3];
	}
	private static int saberNombreJugadors() {
		int numeroJugadores = 0;
		do {
			Scanner lector = new Scanner(System.in);
			System.out.println("Cuantos jugadores habrá?");
			if (lector.hasNextInt()) {
				numeroJugadores = lector.nextInt();
			} else {
				System.out.println("No válido");
			}
		} while (numeroJugadores < minJugadors || numeroJugadores > maxJugadors);
		return numeroJugadores;
	}
}