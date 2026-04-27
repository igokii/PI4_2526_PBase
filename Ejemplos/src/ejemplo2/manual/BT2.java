package ejemplo2.manual;

import ejemplo2.Solucion2A;

public class BT2 {

	private static Double mejorValor;
	private static Estado2 estado;
	private static Solucion2A solucion;
	
	public static Solucion2A search() {
		solucion = null;
		mejorValor = Double.MAX_VALUE; // Estamos minimizando
		estado = Estado2.initial();
		bt_search();
		return solucion;
	}

	private static void bt_search() {
		if (estado.actual.goal() || estado.actual.goalHasSolution()) {
			Double valorObtenido = estado.acumulado;
			if (valorObtenido < mejorValor) {  // Estamos minimizando
				mejorValor = valorObtenido;
				solucion = estado.getSolucion();
			}
		} else {
			for (Integer a: estado.actual.actions()) {
				if (estado.cota(a) < mejorValor) {  // Estamos minimizando
					estado.forward(a);
					bt_search();
					estado.back();
				}
			}
		}
	}

}
