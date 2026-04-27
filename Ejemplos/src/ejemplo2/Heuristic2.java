package ejemplo2;


import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Heuristic2 {

	public static Double heuristic(Vertex2 v1, Predicate<Vertex2> goal, Vertex2 v2) {
		return heuristic1(v1, Datos2.NUM_SC);
	}
	

	public static Double heuristic0(Vertex2 vertice, Integer lastIndex) {
		return 0.;
	}

	public static Double heuristic1(Vertex2 vertice, Integer lastIndex) {
		if (vertice.remaining().isEmpty())  return 0.;
		else return IntStream.range(vertice.index(),lastIndex)
				.mapToDouble(i->Datos2.peso(i))
				.min()
				.getAsDouble();
	}

}


