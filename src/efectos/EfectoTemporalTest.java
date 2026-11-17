package efectos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import combatientes.CultivadorDeLaEspada;
import combatientes.MonjeDelCuerpoCelestial;
import tecnicas.CorrupcionDelCaos;
import tecnicas.LlamaPurificadora;
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

		objetivo.procesarEfectosTemporales();
		assertEquals(vidaTrasImpacto - 8, objetivo.getVida());

		objetivo.procesarEfectosTemporales();
		assertEquals(vidaTrasImpacto - 16, objetivo.getVida());

		objetivo.procesarEfectosTemporales();
		assertEquals(vidaTrasImpacto - 24, objetivo.getVida());
		assertTrue(objetivo.getEfectosActivos().isEmpty());

		objetivo.procesarEfectosTemporales();
		assertEquals(vidaTrasImpacto - 24, objetivo.getVida());
	}

	@Test
	void quemaduraDaniaDuranteDosTurnosYLuegoExpira() {
		CultivadorDeLaEspada atacante = new CultivadorDeLaEspada(1, "Atacante");
		MonjeDelCuerpoCelestial objetivo = new MonjeDelCuerpoCelestial(2, "Objetivo");
		LlamaPurificadora llama = new LlamaPurificadora();
		atacante.aprenderTecnica(llama);

		llama.ejecutar(atacante, objetivo);
		int vidaTrasImpacto = objetivo.getVida();

		objetivo.procesarEfectosTemporales();
		assertEquals(vidaTrasImpacto - 12, objetivo.getVida());

		objetivo.procesarEfectosTemporales();
		assertEquals(vidaTrasImpacto - 24, objetivo.getVida());
		assertTrue(objetivo.getEfectosActivos().isEmpty());

		objetivo.procesarEfectosTemporales();
		assertEquals(vidaTrasImpacto - 24, objetivo.getVida());
	}

	@Test
	void corrupcionDelCaosAplicaDebilitamientoQueReduceDanioProvocadoYExpira() {
		CultivadorDeLaEspada hechicero = new CultivadorDeLaEspada(1, "Hechicero");
		CultivadorDeLaEspada afectado = new CultivadorDeLaEspada(2, "Afectado");
		CorrupcionDelCaos corrupcion = new CorrupcionDelCaos();
		hechicero.aprenderTecnica(corrupcion);

		int danioNormal = afectado.calcularDanioProvocado(20);
		corrupcion.ejecutar(hechicero, afectado);

		// Mientras Debilitamiento está activo (2 turnos), el daño provocado baja un 30%
		int danioDebilitado = afectado.calcularDanioProvocado(20);
		assertEquals((int) Math.round(danioNormal * 0.70), danioDebilitado);

		afectado.procesarEfectosTemporales(); // Turno 1
		assertEquals(danioDebilitado, afectado.calcularDanioProvocado(20));

		afectado.procesarEfectosTemporales(); // Turno 2 -> expira
		assertTrue(afectado.getEfectosActivos().isEmpty());
		assertEquals(danioNormal, afectado.calcularDanioProvocado(20));
	}
}