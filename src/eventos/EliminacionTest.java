package eventos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EliminacionTest {

    @Test
    void guardaElIdYElNombreDelCombatienteCaido() {
        Eliminacion e = new Eliminacion(new Combatiente());

        assertEquals(4, e.getIdCombatiente());
        assertEquals("Kael", e.getNombre());
    }

    @Test
    void laDescripcionMencionaAlCombatienteCaido() {
        Eliminacion e = new Eliminacion(4, "Kael");

        assertTrue(e.getDescripcion().contains("Kael"));
    }
}