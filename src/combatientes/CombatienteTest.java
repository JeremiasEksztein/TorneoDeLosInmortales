package combatientes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import combate.Pareja;
import equipamiento.AnilloEspiritual;
import equipamiento.ArmaduraDorada;
import equipamiento.EspadaCelestial;
import equipamiento.SelloDelFenix;
import eventos.AccionRealizada;
import eventos.Acontecimiento;
import excepciones.EquipamientoInvalidoException;
import tecnicas.CorteDeLosMilAmaneceres;
import tecnicas.DominioDeLaNada;
import tecnicas.MeditacionDelInmortal;
import tecnicas.RugidoDeLaBestiaPrimordial;
import tecnicas.Tecnica;

/**
 * Pruebas unitarias y de reglas de dominio para Combatientes, Técnicas,
 * Equipamientos y Escuelas (Secciones 1, 2, 3, 4 y 6 de la especificación).
 */
class CombatienteTest {

    @Test
    void mismaTecnicaProvocaDistintoDanioSegunTipoDeCombatiente() {
        // Cultivador potencia daño provocado (+30%), Monje y Hechicero no tienen bono ofensivo propio
        CultivadorDeLaEspada cultivador = new CultivadorDeLaEspada(1, "Li Bai");
        MonjeDelCuerpoCelestial monje = new MonjeDelCuerpoCelestial(2, "Buda de Hierro");

        // Blanco común sin defensas para medir daño provocado
        MonjeDelCuerpoCelestial blanco1 = new MonjeDelCuerpoCelestial(3, "Blanco 1");
        MonjeDelCuerpoCelestial blanco2 = new MonjeDelCuerpoCelestial(4, "Blanco 2");

        Tecnica tecnicaCultivador = new RugidoDeLaBestiaPrimordial(); // base 40
        Tecnica tecnicaMonje = new RugidoDeLaBestiaPrimordial();

        cultivador.aprenderTecnica(tecnicaCultivador);
        monje.aprenderTecnica(tecnicaMonje);

        int vidaAntes1 = blanco1.getVida();
        tecnicaCultivador.ejecutar(cultivador, blanco1);
        int danioCultivador = vidaAntes1 - blanco1.getVida();

        int vidaAntes2 = blanco2.getVida();
        tecnicaMonje.ejecutar(monje, blanco2);
        int danioMonje = vidaAntes2 - blanco2.getVida();

        // El cultivador debe haber provocado mayor daño por su bono pasivo
        assertTrue(danioCultivador > danioMonje, "El Cultivador de la Espada debe hacer más daño que el Monje");
    }

    @Test
    void monjeDelCuerpoCelestialReduceDanioRecibido() {
        CultivadorDeLaEspada cultivador = new CultivadorDeLaEspada(1, "Espadachin");
        MonjeDelCuerpoCelestial monje = new MonjeDelCuerpoCelestial(2, "Monje");

        // Golpe base directo de 40 puntos
        cultivador.recibirDanio(40);
        monje.recibirDanio(40);

        // El monje debe conservar más vida porque reduce el daño recibido (25%)
        assertTrue(monje.getVida() > cultivador.getVida(), "El Monje debe reducir el daño que recibe");
    }

    @Test
    void hechiceroDelVacioReduceCostoDeEnergia() {
        HechiceroDelVacio hechicero = new HechiceroDelVacio(1, "Mago");
        CultivadorDeLaEspada otro = new CultivadorDeLaEspada(2, "Otro");

        Tecnica tecnicaHechicero = new CorteDeLosMilAmaneceres(); // costo 15
        Tecnica tecnicaOtro = new CorteDeLosMilAmaneceres();

        hechicero.aprenderTecnica(tecnicaHechicero);
        otro.aprenderTecnica(tecnicaOtro);

        MonjeDelCuerpoCelestial dummy1 = new MonjeDelCuerpoCelestial(3, "Dummy 1");
        MonjeDelCuerpoCelestial dummy2 = new MonjeDelCuerpoCelestial(4, "Dummy 2");

        int energiaAntesH = hechicero.getEnergiaActual();
        tecnicaHechicero.ejecutar(hechicero, dummy1);
        int gastoHechicero = energiaAntesH - hechicero.getEnergiaActual();

        int energiaAntesO = otro.getEnergiaActual();
        tecnicaOtro.ejecutar(otro, dummy2);
        int gastoOtro = energiaAntesO - otro.getEnergiaActual();

        assertTrue(gastoHechicero < gastoOtro, "El hechicero debe gastar menos energía al ejecutar técnicas");
    }

    @Test
    void escuelaPreparaCombatienteCorrectamente() {
        Tecnica t1 = new CorteDeLosMilAmaneceres();
        Tecnica t2 = new RugidoDeLaBestiaPrimordial();
        EspadaCelestial espada = new EspadaCelestial();
        SelloDelFenix sello = new SelloDelFenix();

        Escuela escuelaEspada = new Escuela("Escuela de la Espada Celestial", List.of(t1, t2), List.of(espada, sello));
        CultivadorDeLaEspada c = new CultivadorDeLaEspada(1, "Guerrero");

        escuelaEspada.preparar(c);

        assertEquals(2, c.getEquipamiento().size());
        assertEquals(5, c.usosRestantes(t1));
        assertEquals(5, c.usosRestantes(t2));
        // El sello suma 30 de vida máxima
        assertEquals(180, c.getVidaMaxima());
        assertEquals(180, c.getVida());
    }

    @Test
    void noSePuedeEquiparMasDeTresObjetosNiDosDeLaMismaCategoria() {
        CultivadorDeLaEspada c = new CultivadorDeLaEspada(1, "Guerrero");
        c.equipar(new EspadaCelestial());
        c.equipar(new ArmaduraDorada());
        c.equipar(new AnilloEspiritual());

        // Intento de 4to equipamiento
        assertThrows(EquipamientoInvalidoException.class, () -> c.equipar(new SelloDelFenix()));

        // Intento de categoría duplicada en un combatiente nuevo
        CultivadorDeLaEspada c2 = new CultivadorDeLaEspada(2, "Guerrero 2");
        c2.equipar(new EspadaCelestial());
        assertThrows(EquipamientoInvalidoException.class, () -> c2.equipar(new EspadaCelestial()));
    }

    @Test
    void quitarEquipamientoRecortaVidaActualSiSuperaNuevoTope() {
        CultivadorDeLaEspada c = new CultivadorDeLaEspada(1, "Guerrero");
        SelloDelFenix sello = new SelloDelFenix(); // +30 vida max
        c.equipar(sello);
        assertEquals(180, c.getVidaMaxima());
        c.recuperarVida(30);
        assertEquals(180, c.getVida());

        c.quitar(sello);
        assertEquals(150, c.getVidaMaxima());
        assertEquals(150, c.getVida(), "La vida actual debe recortarse a la nueva vida máxima");
    }

    @Test
    void objetivoPorDefectoEsElRivalEnPieConMenosVidaActualYMenorId() {
        CultivadorDeLaEspada atacante = new CultivadorDeLaEspada(1, "Atacante");

        MonjeDelCuerpoCelestial r1 = new MonjeDelCuerpoCelestial(10, "Rival 10");
        MonjeDelCuerpoCelestial r2 = new MonjeDelCuerpoCelestial(5, "Rival 5");
        MonjeDelCuerpoCelestial r3 = new MonjeDelCuerpoCelestial(8, "Rival 8");

        r1.recibirDanio(50); // vida 100
        r2.recibirDanio(80); // vida 70 (menos vida)
        r3.recibirDanio(80); // vida 70 (empate en vida con r2, pero r2 tiene id 5 < 8)

        Combatiente elegido = atacante.elegirObjetivoRival(List.of(r1, r2, r3));
        assertEquals(r2, elegido, "Debe elegir el de menor vida, y ante empate el de menor ID");
    }

    @Test
    void accionPorDefectoPriorizaCuracionSiFaltaVidaLuegoOfensivaConMasUsos() {
        CultivadorDeLaEspada c = new CultivadorDeLaEspada(1, "Cultivador");
        Tecnica curacion = new MeditacionDelInmortal();
        Tecnica of1 = new CorteDeLosMilAmaneceres();
        Tecnica of2 = new RugidoDeLaBestiaPrimordial();

        c.aprenderTecnica(curacion);
        c.aprenderTecnica(of1);
        c.aprenderTecnica(of2);

        // Sin daño recibido: debe elegir la ofensiva con más usos
        c.registrarUso(of1); // of1 tiene 4 usos, of2 tiene 5 usos
        Tecnica elegidaSinDanio = c.seleccionarTecnicaPorDefecto(null);
        assertEquals(of2, elegidaSinDanio, "Con vida completa debe priorizar la técnica ofensiva con más usos");

        // Si le falta vida: debe priorizar la curación
        c.recibirDanio(20);
        Tecnica elegidaHerido = c.seleccionarTecnicaPorDefecto(null);
        assertEquals(curacion, elegidaHerido, "Herido debe priorizar la técnica de recuperación");
    }

    @Test
    void reglaDelCompanieroBloqueaTecnicaUsadaHastaQueSeAgotenUsosOCaiga() {
        CultivadorDeLaEspada a = new CultivadorDeLaEspada(1, "A");
        MonjeDelCuerpoCelestial b = new MonjeDelCuerpoCelestial(2, "B");
        Pareja pareja = new Pareja(a, b);

        Tecnica tecnicaComun = new RugidoDeLaBestiaPrimordial();
        a.aprenderTecnica(tecnicaComun);
        b.aprenderTecnica(tecnicaComun);

        // Inicialmente B puede usarla
        assertTrue(pareja.puedeUsar(b, tecnicaComun));

        // A la usa una vez
        a.registrarUso(tecnicaComun);

        // B queda bloqueado mientras A conserve usos y esté en pie
        assertFalse(pareja.puedeUsar(b, tecnicaComun), "B no debería poder usarla mientras A tenga usos y esté vivo");

        // Si A gasta todos sus usos restantes
        while (a.usosRestantes(tecnicaComun) > 0) {
            a.registrarUso(tecnicaComun);
        }
        assertTrue(pareja.puedeUsar(b, tecnicaComun), "B puede usarla si a A no le quedan usos");
    }
}
