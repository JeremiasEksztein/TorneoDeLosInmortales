package efectos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import combatientes.CultivadorDeLaEspada;
import combatientes.MonjeDelCuerpoCelestial;
import tecnicas.NieblaVenenosa;

class EfectoTemporalTest {

	@Test
	void venenoDaniaDuranteTresTurnosYLuegoExpira() {
		CultivadorDeLaEspada atacante = new CultivadorDeLaEspada(1, "Atacante");
		MonjeDelCuerpoCelestial objetivo = new MonjeDelCuerpoCelestial(2, "Objetivo");
		NieblaVenenosa niebla = new NieblaVenenosa();
		atacante.aprenderTecnica(niebla);

		niebla.ejecutar(atacante, objetivo);
		int vidaTrasImpacto = objetivo.getVida();
		assertEquals(1, objetivo.getEfectosActivos().size());

		// Turno 1 del afectado: pierde 8 de vida, quedan 2 turnos
		objetivo.procesarEfectosTemporales();
		assertEquals(vidaTrasImpacto - 8, objetivo.getVida());
		assertEquals(1, objetivo.getEfectosActivos().size());

		// Turno 2 del afectado: pierde 8 de vida, queda 1 turno
		objetivo.procesarEfectosTemporales();
		assertEquals(vidaTrasImpacto - 16, objetivo.getVida());

		// Turno 3 del afectado: pierde 8 de vida y expira (se remueve)
		objetivo.procesarEfectosTemporales();
		assertEquals(vidaTrasImpacto - 24, objetivo.getVida());
		assertTrue(objetivo.getEfectosActivos().isEmpty());

		// Turno 4: ya no pierde más vida
		objetivo.procesarEfectosTemporales();
		assertEquals(vidaTrasImpacto - 24, objetivo.getVida());
	}
}