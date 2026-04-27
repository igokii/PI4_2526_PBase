package ejemplo2;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import us.lsi.common.IntegerSet;
import us.lsi.common.Set2;

public record Vertex2I(Integer index, IntegerSet remaining) implements Vertex2 {

	public static Vertex2 initial() {
		return of(0, IntegerSet.of(Datos2.universo()));
	}

	public static Vertex2 of(Integer i, IntegerSet rest) {
		return new Vertex2I(i, rest);
	}

	public Boolean goal() {
		return this.index() == Datos2.NUM_SC;
	}

	public Boolean goalHasSolution() {
		return this.remaining().isEmpty();
	}

	public String toGraph() {
		return String.format("%s,%s)", Datos2.nombre(this.index), this.remaining().isEmpty() ? "Y" : "N");
	}

	public List<Integer> actions() {
		List<Integer> r;
		if (index == Datos2.NUM_SC)
			r = List.of();
		else if (this.index == Datos2.NUM_SC - 1) {
			if (Datos2.conjunto(index).containsAll(this.remaining))
				r = List.of(1);
			else
				r = List.of();
		} else {
			Set<Integer> rest = Set2.difference(remaining, Datos2.conjunto(index));
			if (rest.equals(remaining))
				r = List.of(0);
			else
				r = List.of(1, 0);
		}
		return r;
	}

	@Override
	public Vertex2 neighbor(Integer a) {
		IntegerSet rest = a == 0 ? 
				IntegerSet.copy(remaining) : 
				remaining.difference(Datos2.conjunto(index));
		
		return of(index + 1, rest);
	}

	@Override
	public Edge2 edge(Integer a) {
		return Edge2.of(this, neighbor(a), a);
	}

	public Integer greedyAction() {
		return this.actions().stream().max(Comparator.naturalOrder()).get();
	}

	public Edge2 greedyEdge() {
		return this.edge(this.greedyAction());
	}

	@Override
	public String toString() {
		return String.format("%d; %d", index, remaining.size());
	}
}
