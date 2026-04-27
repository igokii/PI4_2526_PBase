package ejemplo2.manual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import ejemplo2.Solucion2A;
import us.lsi.common.List2;
import us.lsi.common.Map2;


public class PD2 {

	public static record SP(Integer a, Double weight) implements Comparable<SP> {
		public static SP of(Integer a, Double weight) {
			return new SP(a, weight);
		}
		@Override
		public int compareTo(SP sp) {
			return this.weight.compareTo(sp.weight);
		}
	}

	public static Map<Problem2, SP> memory;

	public static Solucion2A search() {
		memory =  Map2.empty();
		pd_search(Problem2.initial());
		return getSolucion();
	}

	private static SP pd_search(Problem2 prob) {
		SP res = null;
		if (memory.containsKey(prob)) {
			res = memory.get(prob);
		} else if (prob.isBaseCase()) {
			Double w = prob.baseCaseWeight();
			if (w!=null) res = SP.of(null,w);
			else res = null;
			memory.put(prob, res);
		} else {
			List<SP> sps = new ArrayList<>();
			for (Integer action : prob.actions()) {
				Problem2 next = prob.neighbor(action);
				SP spNext = pd_search(next);
				if (spNext != null) {
					SP sp = SP.of(action, spNext.weight() + prob.edgeWeight(action));
					sps.add(sp);
				}
			}
			res = sps.stream().min(Comparator.naturalOrder()).orElse(null);
			memory.put(prob, res);
		}
		return res;
	}

	public static Solucion2A getSolucion() {
		List<Integer> acciones = List2.empty();
		Problem2 prob = Problem2.initial();
		SP spm = memory.get(prob);
		while (spm != null && spm.a != null) {
			Problem2 old = prob;
			acciones.add(spm.a);
			prob = old.neighbor(spm.a);
			spm = memory.get(prob);
		}
		return Solucion2A.of(acciones);
	}

}
