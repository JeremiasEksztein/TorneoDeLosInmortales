package unlam;

import java.util.List;

import combate.Pareja;
import combatientes.Combatiente;
import combatientes.CreadorCombatientes;
import combatientes.Escuela;
import equipamiento.AnilloEspiritual;
import equipamiento.ArmaduraDorada;
import equipamiento.EspadaCelestial;
import equipamiento.SelloDelFenix;
import eventos.CronistaDeLaSecta;
import eventos.HeraldoImperial;
import tecnicas.CorteDeLosMilAmaneceres;
import tecnicas.DominioDeLaNada;
import tecnicas.MeditacionDelInmortal;
import tecnicas.MuroDelCuerpoDorado;
import tecnicas.RugidoDeLaBestiaPrimordial;
import torneo.Torneo;

public class TorneoDeLosInmortales {

	public static void main(String[] args) {
		// 1. Configuración de Escuelas
		Escuela escuelaDeLaEspada = new Escuela(
				"Escuela de la Espada Celestial",
				List.of(new CorteDeLosMilAmaneceres(), new RugidoDeLaBestiaPrimordial()),
				List.of(new EspadaCelestial()));

		Escuela palacioDelVacio = new Escuela(
				"Palacio del Vacío",
				List.of(new DominioDeLaNada(), new MeditacionDelInmortal()),
				List.of(new AnilloEspiritual()));

		Escuela monasterioCuerpoDorado = new Escuela(
				"Monasterio del Cuerpo Dorado",
				List.of(new MuroDelCuerpoDorado(), new MeditacionDelInmortal()),
				List.of(new ArmaduraDorada(), new SelloDelFenix()));

		// 2. Creación centralizada de combatientes
		CreadorCombatientes creador = new CreadorCombatientes();

		Combatiente aurelio = creador.crear("Cultivador de la Espada", "Aurelio", escuelaDeLaEspada);
		Combatiente nebula = creador.crear("Hechicero del Vacío", "Nébula", palacioDelVacio);

		Combatiente kael = creador.crear("Monje del Cuerpo Celestial", "Kael", monasterioCuerpoDorado);
		Combatiente darian = creador.crear("Cultivador de la Espada", "Darian", escuelaDeLaEspada);

		Combatiente lyra = creador.crear("Hechicero del Vacío", "Lyra", palacioDelVacio);
		Combatiente thorne = creador.crear("Monje del Cuerpo Celestial", "Thorne", monasterioCuerpoDorado);

		Combatiente vaelen = creador.crear("Cultivador de la Espada", "Vaelen", monasterioCuerpoDorado);
		Combatiente seraphina = creador.crear("Hechicero del Vacío", "Seraphina", escuelaDeLaEspada);

		// Equipamiento adicional previo al torneo (hasta 3 objetos de distinta categoría)
		aurelio.equipar(new SelloDelFenix());
		lyra.equipar(new ArmaduraDorada());

		// 3. Configuración del Torneo e Interesados
		Torneo torneo = new Torneo();
		HeraldoImperial heraldo = new HeraldoImperial();
		CronistaDeLaSecta cronista = new CronistaDeLaSecta();

		torneo.suscribir(heraldo);
		torneo.suscribir(cronista);

		// 4. Formación e inscripción de 4 parejas
		Pareja pareja1 = new Pareja(1, aurelio, nebula);
		Pareja pareja2 = new Pareja(2, kael, darian);
		Pareja pareja3 = new Pareja(3, lyra, thorne);
		Pareja pareja4 = new Pareja(4, vaelen, seraphina);

		torneo.inscribir(pareja1);
		torneo.inscribir(pareja2);
		torneo.inscribir(pareja3);
		torneo.inscribir(pareja4);

		// 5. Ejecución del torneo y presentación de la tabla final
		torneo.iniciar();
		heraldo.presentarTabla(cronista.tablaDePosiciones());
	}
}