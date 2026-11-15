package eventos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import combatientes.CreadorCombatiente;


class EliminacionTest {
    private static final int contadorId=1;
    CreadorCombatiente creadorCombatiente = new CreadorCombatiente();
    @BeforeEach
    void setUp() {

        int id=contadorId;
        String nombre="Kael";
        int vidaMaxima=100;
        int energiaMaxima=50;  
        contadorId++;
    }
    @Test
    void guardaElIdYElNombreDelCombatienteCaido() {
        Eliminacion e = new Eliminacion();

        assertEquals(4, e.getIdCombatiente());
        assertEquals("Kael", e.getNombre());
    }

    @Test
    void laDescripcionMencionaAlCombatienteCaido() {
        Eliminacion e = new Eliminacion(4, "Kael");

        assertTrue(e.getDescripcion().contains("Kael"));
    }
}