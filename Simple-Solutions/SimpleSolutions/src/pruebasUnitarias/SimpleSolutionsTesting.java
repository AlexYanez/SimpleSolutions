package pruebasUnitarias;

import static org.junit.Assert.*;

import org.junit.Test;

import controlador.CPrueba;
import controlador.Cproyecto;
import modelo.Proyecto;
import modelo.Prueba;
import modelo.Version;
import vista.Vprincipal;
import vista.Vproyecto;
import vista.Vprueba;

public class SimpleSolutionsTesting {

	Vproyecto vp = new Vproyecto();
	Vprueba vqa = new Vprueba();
	Vprincipal vprincipal = new Vprincipal();
	Proyecto p = new Proyecto();
	Prueba pru = new Prueba();
	Version ver = new Version();
	CPrueba cpru = new CPrueba(pru, ver, p);
	Cproyecto cp = new Cproyecto(p);

	@Test
	public void testIndiceRapidez() {
		Double resultado = pru.calcularIndrapidez(20, 0.2);
		Double esperado = 0.01;
		assertEquals(esperado, resultado);
	}
	
	@Test
	public void testCalcularEfiPrueba() {
		Double resultado = pru.calcularEfiPrueba(0.11, 5.0);
		Double esperado = 984.0;
		assertEquals(esperado, resultado);
	}
	
	@Test
	public void testCalcEfVersion(){
		 Double resultado = ver.calcularEfVersion(995.0+1000.0+990.0, 3);
		 Double esperado = 995.0;
		 assertEquals(esperado, resultado);
	 }
	 
	
}
