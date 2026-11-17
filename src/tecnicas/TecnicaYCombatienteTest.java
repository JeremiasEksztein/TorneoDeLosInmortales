package tecnicas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import combate.Pareja;
import combatientes.CultivadorDeLaEspada;
import combatientes.MonjeDelCuerpoCelestial;

class TecnicaYCombatienteTest {
	
	@Test
	void noEjecutaTecnicaCuandoSeAgotanSusUsos() {
		CultivadorDeLaEspada ejecutor = new CultivadorDeLaEspada(1, "Ejecutor");
		MonjeDelCuerpoCelestial companiero = new MonjeDelCuerpoCelestial(2, "Compañero");
		Pareja pareja = new Pareja(1, ejecutor, companiero);

		Tecnica corte = new CorteDeLosMilAmaneceres(); // 5 usos iniciales
		ejecutor.aprenderTecnica(corte);

		// Consumimos los 5 usos disponibles
		for (int i = 0; i < 5; i++) {
			ejecutor.registrarUso(corte);
		}

		assertEquals(0, ejecutor.usosRestantes(corte));
		assertFalse(ejecutor.puedeEjecutar(corte));
		assertEquals("Ataque básico", ejecutor.elegirAccion(pareja).getNombre());
	}

	@Test
	void noEjecutaTecnicaCuandoFaltaEnergiaAunqueQuedenUsos() {
		CultivadorDeLaEspada ejecutor = new CultivadorDeLaEspada(1, "Ejecutor");
		MonjeDelCuerpoCelestial companiero = new MonjeDelCuerpoCelestial(2, "Compañero");
		Pareja pareja = new Pareja(1, ejecutor, companiero);

		Tecnica rugido = new RugidoDeLaBestiaPrimordial(); // costo 20
		ejecutor.aprenderTecnica(rugido);

		// Reducimos su energía de 300 a 5 (menor al costo de 20)
		ejecutor.quitarEnergia(295);

		assertEquals(5, ejecutor.getEnergiaActual());
		assertEquals(5, ejecutor.usosRestantes(rugido));
		assertFalse(ejecutor.puedeEjecutar(rugido));
		assertEquals("Ataque básico", ejecutor.elegirAccion(pareja).getNombre());
	}
}