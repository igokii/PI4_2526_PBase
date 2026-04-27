package ejemplo2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.GraphPath;

public record Solucion2B(Double peso, Set<String> conjuntos) 
           implements Comparable<Solucion2B>{
	
	public static Solucion2B of(GraphPath<Vertex2, Edge2> path) {
		List<Integer> la = path.getEdgeList().stream().map(e->e.action()).toList();
		return Solucion2B.of(la);
	}

	public static Solucion2B of(List<Integer> ls) {
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
		return new Solucion2B(ps,cs);
	}

	public static Solucion2B of(Set<Integer> indices) {
		Set<String> cs = new HashSet<>();
		Double ps = 0.;
		Set<Integer> s = new HashSet<>();
		for (Integer i : indices) {
			cs.add(Datos2.nombre(i));
			ps += Datos2.peso(i);
			s.addAll(Datos2.conjunto(i));
		}
		return new Solucion2B(ps,cs);		
	}

	@Override
	public int compareTo(Solucion2B other) {
		return this.peso().compareTo(other.peso());
	}
}
