package ejemplo2.manual;

import java.util.List;

import ejemplo2.Datos2;

public class TestPD {

	public static void main(String[] args) {
		for(Integer i: List.of(1,2)) {
			Datos2.iniDatos("ficheros/DatosEntrada"+i+".txt");
			System.out.println(PD2.search());
		}

	}

}
