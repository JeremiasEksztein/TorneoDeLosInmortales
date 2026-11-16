package eventos;
import combatientes.CreadorCombatientes;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



class EliminacionTest {
    private static int contadorId=1;
    CreadorCombatientes creadorCombatiente = new CreadorCombatientes();
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
    	
        Eliminacion e = new Eliminacion(4, "Kael");

        assertEquals(4, e.getIdCombatiente());
        assertEquals("Kael", e.getNombre());
    }

    @Test
    void laDescripcionMencionaAlCombatienteCaido() {
        Eliminacion e = new Eliminacion(4, "Kael");

        assertTrue(e.getDescripcion().contains("Kael"));
    }
}