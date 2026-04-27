package ejemplo2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.GraphPath;

public record Solucion2A(Double peso, Set<String> conjuntos, Boolean cubre) 
           implements Comparable<Solucion2A>{
	
	public static Solucion2A of(GraphPath<Vertex2, Edge2> path) {
		List<Integer> la = path.getEdgeList().stream().map(e->e.action()).toList();
		return Solucion2A.of(la);
	}

	public static Solucion2A of(List<Integer> ls) {
		Set<String> cs = new HashSet<>();
		Double ps = 0.;
		Set<Integer> s = new HashSet<>();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) == 1) {
				cs.add(Datos2.nombre(i));
				ps += Datos2.peso(i);
				s.addAll(Datos2.conjunto(i));
			}
		}
		Boolean c = s.equals(Datos2.universo());
		return new Solucion2A(ps,cs,c);
	}

	@Override
	public int compareTo(Solucion2A other) {
		return this.peso().compareTo(other.peso());
	}
}
