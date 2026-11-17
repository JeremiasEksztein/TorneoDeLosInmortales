package equipamiento;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EquipamientoTest {

    // Equipamientos de prueba: usan los valores por defecto de la base.
    private static class Neutro extends Equipamiento {
        Neutro(String nombre, TipoEquipamiento tipo) {
            super(nombre, tipo);
        }
    }

    private static class OtroNeutro extends Equipamiento {
        OtroNeutro(String nombre, TipoEquipamiento tipo) {
            super(nombre, tipo);
        }
    }

    @Test
    void guardaNombreYTipo() {
        Equipamiento e = new Neutro("Objeto", TipoEquipamiento.ANILLO);

        assertEquals("Objeto", e.getNombre());
        assertEquals(TipoEquipamiento.ANILLO, e.getTipo());
    }

    @Test
    void rechazaUnNombreNuloOVacio() {
        assertThrows(IllegalArgumentException.class,
            () -> new Neutro(null, TipoEquipamiento.ARMA));
        assertThrows(IllegalArgumentException.class,
            () -> new Neutro("   ", TipoEquipamiento.ARMA));
    }

    @Test
    void rechazaUnTipoNulo() {
        assertThrows(IllegalArgumentException.class,
            () -> new Neutro("Objeto", null));
    }

    @Test
    void porDefectoNoModificaNingunValor() {
        Equipamiento e = new Neutro("Objeto", TipoEquipamiento.SELLO);

        assertEquals(40, e.calcularDanioBonificado(40));
        assertEquals(40, e.calcularEnergiaBonificada(40));
        assertEquals(40, e.calcularVidaMaximaBonificada(40));
        assertEquals(40, e.calcularDanioRecibidoBonificado(40));
    }

    @Test
    void dosEquipamientosDeLaMismaClaseYNombreSonIguales() {
        Equipamiento a = new Neutro("Objeto", TipoEquipamiento.ARMA);
        Equipamiento b = new Neutro("Objeto", TipoEquipamiento.ARMA);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void equipamientosDeDistintoNombreOClaseNoSonIguales() {
        Equipamiento a = new Neutro("Objeto", TipoEquipamiento.ARMA);

        assertNotEquals(a, new Neutro("Otro", TipoEquipamiento.ARMA));
        assertNotEquals(a, new OtroNeutro("Objeto", TipoEquipamiento.ARMA));
        assertNotEquals(a, null);
    }
}