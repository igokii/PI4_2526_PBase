package ejemplo2.manual;

import java.util.List;
import java.util.stream.IntStream;

import ejemplo2.Datos2;
import ejemplo2.Solucion2A;
import us.lsi.common.List2;

public class Estado2 {
	
	public Problem2 actual;
	public Double acumulado;
	public List<Integer> acciones;
	public List<Problem2> anteriores;
	
	public Estado2(Problem2 p, double ac, List<Integer> la, List<Problem2> lp) {
		actual = p;
		acumulado = ac;
		acciones = la;
		anteriores = lp;
	}

	public static Estado2 initial() {
		return new Estado2(Problem2.initial(), 0., List2.empty(), List2.empty());
	}
	
	public void forward(Integer a) {
		acumulado += actual.edgeWeight(a);
		anteriores.add(actual);
		acciones.add(a);
		actual = actual.neighbor(a);
	}
	
	public void back() {
		actual = anteriores.removeLast();
		acumulado -=actual.edgeWeight(acciones.removeLast());
	}

	public Solucion2A getSolucion() {
		return Solucion2A.of(acciones);
	}

	public Double cota(Integer a) {
		return actual.edgeWeight(a) + heuristica(actual.neighbor(a));
	}

	private Double heuristica(Problem2 p) {
		return p.goalHasSolution()? 0.: 
		IntStream.range(p.index(), Datos2.NUM_SC)
		.mapToDouble(i -> Datos2.peso(i)).min().orElse(0.);
	}
 	
}
