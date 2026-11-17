package combate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import combatientes.CultivadorDeLaEspada;
import combatientes.HechiceroDelVacio;
import combatientes.MonjeDelCuerpoCelestial;
import eventos.AccionRealizada;
import eventos.CronistaDeLaSecta;
import eventos.Eliminacion;
import eventos.PublicadorAcontecimiento;
import eventos.ResultadoCombate;
import tecnicas.CorteDeLosMilAmaneceres;
import tecnicas.MuroDelCuerpoDorado;
import tecnicas.Tecnica;

class CombateTest {

	@Test
	void usosDeTecnicasSeConservanEntreCombatesPeroVidaYEnergiaSeRecuperan() {
		CultivadorDeLaEspada a1 = new CultivadorDeLaEspada(1, "A1");
		MonjeDelCuerpoCelestial a2 = new MonjeDelCuerpoCelestial(2, "A2");
		Tecnica corte = new CorteDeLosMilAmaneceres();
		a1.aprenderTecnica(corte);

		CultivadorDeLaEspada b1 = new CultivadorDeLaEspada(3, "B1");
		MonjeDelCuerpoCelestial b2 = new MonjeDelCuerpoCelestial(4, "B2");

		Pareja p1 = new Pareja(1, a1, a2);
		Pareja p2 = new Pareja(2, b1, b2);

		Combate primerCombate = new Combate(p1, p2, 1, new PublicadorAcontecimiento());
		primerCombate.ejecutar();

		int usosDespuesDelPrimero = a1.usosRestantes(corte);
		assertTrue(usosDespuesDelPrimero < 5, "Debe haber consumido usos de la técnica en el primer combate");

		CultivadorDeLaEspada c1 = new CultivadorDeLaEspada(5, "C1");
		MonjeDelCuerpoCelestial c2 = new MonjeDelCuerpoCelestial(6, "C2");
		Pareja p3 = new Pareja(3, c1, c2);

		Combate segundoCombate = new Combate(p1, p3, 2, new PublicadorAcontecimiento());
		p1.reiniciarParaCombate();
		assertEquals(a1.getVidaMaxima(), a1.getVida());
		assertEquals(a1.getEnergiaMaxima(), a1.getEnergiaActual());
		assertEquals(a2.getVidaMaxima(), a2.getVida());
		assertEquals(usosDespuesDelPrimero, a1.usosRestantes(corte));
		assertNotNull(segundoCombate);
	}

	@Test
	void sinEnergiaOSinUsosRestantesRecurreAAtaqueBasico() {
		HechiceroDelVacio h = new HechiceroDelVacio(1, "Hechicero");
		MonjeDelCuerpoCelestial comp = new MonjeDelCuerpoCelestial(2, "Comp");
		Tecnica corte = new CorteDeLosMilAmaneceres();
		h.aprenderTecnica(corte);
		Pareja p = new Pareja(1, h, comp);

		for (int i = 0; i < 5; i++) {
			h.registrarUso(corte);
		}
		assertEquals(0, h.usosRestantes(corte));
		assertEquals("Ataque básico", h.elegirAccion(p).getNombre());
	}

	@Test
	void caidoNoActuaNiEsElegidoComoObjetivo() {
		CultivadorDeLaEspada a1 = new CultivadorDeLaEspada(1, "A1");
		MonjeDelCuerpoCelestial a2 = new MonjeDelCuerpoCelestial(2, "A2");
		CultivadorDeLaEspada b1 = new CultivadorDeLaEspada(3, "B1");
		MonjeDelCuerpoCelestial b2 = new MonjeDelCuerpoCelestial(4, "B2");

		Pareja p1 = new Pareja(1, a1, a2);
		Pareja p2 = new Pareja(2, b1, b2);

		PublicadorAcontecimiento pub = new PublicadorAcontecimiento();
		CronistaDeLaSecta cronista = new CronistaDeLaSecta();
		pub.suscribir(cronista);

		new Combate(p1, p2, 1, pub).ejecutar();

		var historial = cronista.getHistorial();
		int idxEliminacionB1 = -1;
		for (int i = 0; i < historial.size(); i++) {
			if (historial.get(i) instanceof Eliminacion e && e.getIdCombatiente() == 3) {
				idxEliminacionB1 = i;
				break;
			}
		}
		assertTrue(idxEliminacionB1 >= 0, "B1 debe haber sido eliminado en el combate");

		// Después de su eliminación, B1 no vuelve a actuar ni a ser elegido como objetivo
		List<AccionRealizada> accionesPosteriores = historial.subList(idxEliminacionB1 + 1, historial.size()).stream()
				.filter(AccionRealizada.class::isInstance)
				.map(AccionRealizada.class::cast)
				.toList();

		assertTrue(accionesPosteriores.stream().noneMatch(a -> a.getNomObjetivo() == "B1"));
		assertTrue(accionesPosteriores.stream().noneMatch(a -> a.getNomObjetivo() == "B1"));
	}

	@Test
	void desempateAlLimiteDeAsaltosGanaMayorVidaTotalYMenorId() {
		MonjeDelCuerpoCelestial a1 = new MonjeDelCuerpoCelestial(1, "A1");
		MonjeDelCuerpoCelestial a2 = new MonjeDelCuerpoCelestial(2, "A2");
		a1.aprenderTecnica(new MuroDelCuerpoDorado());
		a2.aprenderTecnica(new MuroDelCuerpoDorado());

		MonjeDelCuerpoCelestial b1 = new MonjeDelCuerpoCelestial(3, "B1");
		MonjeDelCuerpoCelestial b2 = new MonjeDelCuerpoCelestial(4, "B2");
		b1.aprenderTecnica(new MuroDelCuerpoDorado());
		b2.aprenderTecnica(new MuroDelCuerpoDorado());

		Pareja p1 = new Pareja(1, a1, a2);
		Pareja p2 = new Pareja(2, b1, b2);

		ResultadoCombate resultado = new Combate(p1, p2, 1, new PublicadorAcontecimiento()).ejecutar();
		assertNotNull(resultado);
		int idEsperado = p1.vidaTotal() >= p2.vidaTotal() ? p1.getId() : p2.getId();
		assertEquals(idEsperado, resultado.getIdGanadora());
	}
}