package ejemplo2.manual;

import java.util.List;

import ejemplo2.Datos2;

public class TestBT2 {

	public static void main(String[] args) {
		System.out.println("TEST BT MANUAL");
		for(Integer i: List.of(1,2)) {
			Datos2.iniDatos("ficheros/DatosEntrada"+i+".txt");
			System.out.println(BT2.search());
		}

	}

}
