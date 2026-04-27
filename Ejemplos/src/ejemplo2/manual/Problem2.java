package ejemplo2.manual;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import ejemplo2.Datos2;
import us.lsi.common.IntegerSet;
import us.lsi.common.Set2;

public record Problem2(Integer index, IntegerSet remaining) {

	public static Problem2 initial() {
		return new Problem2(0, IntegerSet.of(Datos2.universo()));
	}

	public Boolean goal() {
		return this.index() == Datos2.NUM_SC;
	}

	public Boolean goalHasSolution() {
		return this.remaining().isEmpty();
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

	
	public Problem2 neighbor(Integer a) {
		IntegerSet rest = a == 0 ? 
				IntegerSet.copy(remaining) : 
				remaining.difference(Datos2.conjunto(index));
		
		return new Problem2(index + 1, rest);
	}

	@Override
	public String toString() {
		return String.format("%d; %d", index, remaining.size());
	}

	public boolean isBaseCase() {
		return goal() || goalHasSolution();
	}

	public Double baseCaseWeight() {
		return goalHasSolution()? 0.: null;
	}
	
	public Double edgeWeight(Integer action) {
		return Datos2.peso(index)*action;
	}
}
