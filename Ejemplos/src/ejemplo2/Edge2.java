package ejemplo2;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Edge2(Vertex2 source, Vertex2 target, 
		Integer action, Double weight) 
			implements SimpleEdgeAction<Vertex2,Integer> {

	public static Edge2 of(Vertex2I c1, Vertex2 c2, Integer action) {
		Double w = (double) Datos2.peso(c1.index())*action;
		
		return new Edge2(c1, c2, action, w);
	}
}
