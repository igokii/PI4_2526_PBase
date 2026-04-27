package ejemplo2;

import us.lsi.common.IntegerSet;
import us.lsi.graphs.virtual.VirtualVertex;

public interface Vertex2 extends 
	VirtualVertex<Vertex2, Edge2, Integer> {
	
	Integer index();
	IntegerSet remaining();
	String toGraph();

	public static Vertex2 initial() {
		return Vertex2I.of(0, IntegerSet.of(Datos2.universo()));
	}
}