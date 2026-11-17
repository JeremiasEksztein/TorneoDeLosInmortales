package tecnicas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import combatientes.CultivadorDeLaEspada;
import combatientes.HechiceroDelVacio;
import combatientes.MonjeDelCuerpoCelestial;
import eventos.AccionRealizada;

class TecnicaTest {

	@Test
	void unaTecnicaDaniaDistintoSegunElTipoDelEjecutor() {
		CultivadorDeLaEspada cultivador = new CultivadorDeLaEspada(1, "Li Bai");
		MonjeDelCuerpoCelestial monje = new MonjeDelCuerpoCelestial(2, "Buda");
		HechiceroDelVacio hechicero = new HechiceroDelVacio(3, "Vacio");

		// Blancos idénticos sin equipamiento para aislar el efecto del ejecutor
		CultivadorDeLaEspada blanco1 = new CultivadorDeLaEspada(4, "Blanco 1");
		CultivadorDeLaEspada blanco2 = new CultivadorDeLaEspada(5, "Blanco 2");
		CultivadorDeLaEspada blanco3 = new CultivadorDeLaEspada(6, "Blanco 3");

		Tecnica corte1 = new CorteDeLosMilAmaneceres(); // daño base 25
		Tecnica corte2 = new CorteDeLosMilAmaneceres();
		Tecnica corte3 = new CorteDeLosMilAmaneceres();

		cultivador.aprenderTecnica(corte1);
		monje.aprenderTecnica(corte2);
		hechicero.aprenderTecnica(corte3);

		AccionRealizada resCultivador = corte1.ejecutar(cultivador, blanco1);
		AccionRealizada resMonje = corte2.ejecutar(monje, blanco2);
		AccionRealizada resHechicero = corte3.ejecutar(hechicero, blanco3);

		
		assertNotEquals(
			    resCultivador.getPuntos(),
			    resMonje.getPuntos()
			    );

		assertNotEquals(
			    resCultivador.getPuntos(),
			    resHechicero.getPuntos()
			    );
		//Validacion de implementacion
		// Cultivador de la Espada potencia +30% el daño (25 * 1.30 = 33)
		// Monje y Hechicero infligen el daño base sin bono ofensivo (25)
		assertEquals(33, resCultivador.getPuntos());
		assertEquals(25, resMonje.getPuntos());
		assertEquals(25, resHechicero.getPuntos());
		
	}

	@Test
	void ejecutarTecnicaDescuentaEnergiaYConsumeUnUso() {
		MonjeDelCuerpoCelestial ejecutor = new MonjeDelCuerpoCelestial(1, "Ejecutor");
		CultivadorDeLaEspada rival = new CultivadorDeLaEspada(2, "Rival");
		Tecnica rugido = new RugidoDeLaBestiaPrimordial(); // costo 20, daño base 40

		ejecutor.aprenderTecnica(rugido);
		int energiaInicial = ejecutor.getEnergiaActual();

		rugido.ejecutar(ejecutor, rival);

		assertEquals(energiaInicial - 20, ejecutor.getEnergiaActual());
		assertEquals(4, ejecutor.usosRestantes(rugido));
	}
}