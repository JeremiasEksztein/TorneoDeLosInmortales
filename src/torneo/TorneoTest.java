package torneo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import combate.Pareja;
import combatientes.Combatiente;
import combatientes.CreadorCombatientes;
import combatientes.Escuela;
import equipamiento.AnilloEspiritual;
import equipamiento.ArmaduraDorada;
import equipamiento.EspadaCelestial;
import eventos.Acontecimiento;
import eventos.CronistaDeLaSecta;
import eventos.Posicion;
import excepciones.CantidadDeParejasInvalidaException;
import excepciones.InscripcionDuplicadaException;
import tecnicas.CorrupcionDelCaos;
import tecnicas.CorteDeLosMilAmaneceres;
import tecnicas.DominioDeLaNada;
import tecnicas.LlamaPurificadora;
import tecnicas.MeditacionDelInmortal;
import tecnicas.MuroDelCuerpoDorado;
import tecnicas.NieblaVenenosa;

class TorneoTest {

	private Torneo armarTorneoDePrueba(CronistaDeLaSecta cronista, int cantidadParejas) {
		Escuela espada = new Escuela("Espada",
				List.of(new CorteDeLosMilAmaneceres(), new LlamaPurificadora()),
				List.of(new EspadaCelestial()));
		Escuela vacio = new Escuela("Vacío",
				List.of(new DominioDeLaNada(), new CorrupcionDelCaos(), new NieblaVenenosa()),
				List.of(new AnilloEspiritual()));
		Escuela dorado = new Escuela("Dorado",
				List.of(new MuroDelCuerpoDorado(), new MeditacionDelInmortal()),
				List.of(new ArmaduraDorada()));

		Escuela[] escuelas = { espada, vacio, dorado };
		String[] tipos = { "Cultivador de la Espada", "Hechicero del Vacío", "Monje del Cuerpo Celestial" };

		CreadorCombatientes creador = new CreadorCombatientes();
		Torneo torneo = new Torneo();
		torneo.suscribir(cronista);

		for (int i = 1; i <= cantidadParejas; i++) {
			Combatiente a = creador.crear(tipos[(i * 2) % 3], "CompA" + i, escuelas[(i * 2) % 3]);
			Combatiente b = creador.crear(tipos[(i * 2 + 1) % 3], "CompB" + i, escuelas[(i * 2 + 1) % 3]);
			torneo.inscribir(new Pareja(i, a, b));
		}
		return torneo;
	}

	@Test
	void rechazaParejaOCombatienteDuplicado() {
		Escuela escuelaBase = new Escuela("Base", List.of(), List.of());
		CreadorCombatientes creador = new CreadorCombatientes();
		Combatiente c1 = creador.crear("Cultivador de la Espada", "C1", escuelaBase);
		Combatiente c2 = creador.crear("Hechicero del Vacío", "C2", escuelaBase);
		Combatiente c3 = creador.crear("Monje del Cuerpo Celestial", "C3", escuelaBase);

		Pareja p1 = new Pareja(1, c1, c2);
		Pareja p1Duplicada = new Pareja(1, c1, c2);
		Pareja pConCombatienteRepetido = new Pareja(2, c1, c3);

		Torneo torneo = new Torneo();
		torneo.inscribir(p1);

		assertThrows(InscripcionDuplicadaException.class, () -> torneo.inscribir(p1Duplicada));
		assertThrows(InscripcionDuplicadaException.class, () -> torneo.inscribir(pConCombatienteRepetido));
	}
	@Test
	void rechazaCantidadDeParejasDistintaDeCuatroUOcho() {
		CronistaDeLaSecta cronista = new CronistaDeLaSecta();
		Torneo torneoConTres = armarTorneoDePrueba(cronista, 3);

		assertThrows(CantidadDeParejasInvalidaException.class, torneoConTres::iniciar);
		assertThrows(IllegalStateException.class, torneoConTres::getCampeon);
	}

	@Test
	void torneoDeOchoParejasSeResuelveEnTresRondasYProclamaCampeon() {
		CronistaDeLaSecta cronista = new CronistaDeLaSecta();
		Torneo torneo = armarTorneoDePrueba(cronista, 8);

		torneo.iniciar();

		assertEquals(3, torneo.getRondas().size());
		assertNotNull(torneo.getCampeon());
		assertEquals(8, cronista.tablaDePosiciones().size());
		assertEquals(torneo.getCampeon().getId(), cronista.tablaDePosiciones().get(0).getIdPareja());
	}

	@Test
	void dosTorneosArmadosIgualProducenElMismoCampeonYMismoHistorial() {
		CronistaDeLaSecta cronista1 = new CronistaDeLaSecta();
		CronistaDeLaSecta cronista2 = new CronistaDeLaSecta();

		Torneo t1 = armarTorneoDePrueba(cronista1, 4);
		Torneo t2 = armarTorneoDePrueba(cronista2, 4);

		t1.iniciar();
		t2.iniciar();

		assertEquals(t1.getCampeon(), t2.getCampeon());

		List<Posicion> tabla1 = cronista1.tablaDePosiciones();
		List<Posicion> tabla2 = cronista2.tablaDePosiciones();
		assertEquals(4, tabla1.size());
		assertEquals(tabla1.get(0).getIdPareja(), tabla2.get(0).getIdPareja());

		List<String> eventos1 = cronista1.getHistorial().stream().map(Acontecimiento::getDescripcion).toList();
		List<String> eventos2 = cronista2.getHistorial().stream().map(Acontecimiento::getDescripcion).toList();
		assertEquals(eventos1, eventos2, "Ambos torneos deterministas deben producir exactamente el mismo historial");
	}
	
	@Test
	void torneoDeOchoParejasSeResuelveEnTresRondasYOrdenaLaTablaDePosiciones() {
		CronistaDeLaSecta cronista = new CronistaDeLaSecta();
		Torneo torneo = armarTorneoDePrueba(cronista, 8);

		torneo.iniciar();

		// 8 parejas 3 rondas (4 cruces en R1, 2 en R2, 1 final en R3)
		assertEquals(3, torneo.getRondas().size());
		assertEquals(4, torneo.getRondas().get(0).getCruces().size());
		assertEquals(2, torneo.getRondas().get(1).getCruces().size());
		assertEquals(1, torneo.getRondas().get(2).getCruces().size());

		Pareja campeona = torneo.getCampeon();
		assertNotNull(campeona);

		List<Posicion> tabla = cronista.tablaDePosiciones();
		assertEquals(8, tabla.size());
		assertEquals(campeona.getId(), tabla.get(0).getIdPareja(), "La campeona debe encabezar la tabla");
		assertEquals(3, tabla.get(0).getUltimaRonda(), "La campeona debe haber jugado hasta la ronda 3");
		assertEquals(3, tabla.get(1).getUltimaRonda(), "La subcampeona también llegó a la ronda 3");
	}
}