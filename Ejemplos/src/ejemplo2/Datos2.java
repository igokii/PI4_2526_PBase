package ejemplo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
//import java.util.TreeSet;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.common.String2;

public class Datos2 {

	public static Integer NUM_SC, NUM_E;
	
	private static Set<Integer> universo;
	private static List<DatoDeConjunto> datosDeConjuntos;

	public static void iniDatos(String fichero) {		
		Datos2.datosDeConjuntos = new ArrayList<>();
		Datos2.universo = new HashSet<>();
		for(String linea: Files2.linesFromFile(fichero)) {
			DatoDeConjunto s = DatoDeConjunto.parse(linea);
			Datos2.datosDeConjuntos.add(s);
			Datos2.universo.addAll(s.conjunto());
		}
		Datos2.NUM_SC = datosDeConjuntos.size();
		Datos2.NUM_E = universo.size();
		Collections.sort(Datos2.datosDeConjuntos,Comparator.comparing(d->d.peso()));
	}
	
	public static List<DatoDeConjunto> datosDeConjuntos() {
		return datosDeConjuntos;
	}
	
	public static  Set<Integer> universo() {
		return universo;
	}
	
	public static Set<Integer> conjunto(int index){
		return datosDeConjuntos.get(index).conjunto();
	}
	
	public static Double peso(int index){
		return datosDeConjuntos.get(index).peso();
	}
	
	public static String nombre(int index){
		return index == Datos2.NUM_SC? "_":datosDeConjuntos.get(index).nombre();
	}
	
	public static void toConsole() {
		String2.toConsole(String.format("Universo U = %s\nConjuntos S =  \n\t%s",
				Datos2.universo(),
				Datos2.datosDeConjuntos().stream().map(d->d.toString()).collect(Collectors.joining("\n\t"))));		
	}
	
	// Test de la lectura del fichero
	public static void main(String[] args) {
		// Set up
		Locale.setDefault(Locale.of("en", "US"));

		for (Integer id_fichero = 1; id_fichero < 3; id_fichero++) {

			Datos2.iniDatos("ficheros/p4/subconjuntos" + id_fichero + ".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			Datos2.toConsole();
			
		}
	}
}

