package eventos;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;

class ResultadoCombateYTablaTest {

    @Test
    void registraAAmbasParejasEnLaTabla() {
        TablaDePosiciones tabla = new TablaDePosiciones();

        new ResultadoCombate(1, 1, 2, 70, 0, 1).aplicarA(tabla);

        List<Posicion> ordenada = tabla.ordenada();
        assertEquals(2, ordenada.size());
        assertEquals(1, ordenada.get(0).getIdPareja()); // mayor vida total
        assertEquals(2, ordenada.get(1).getIdPareja());
    }

    @Test
    void laPosicionDeUnaParejaAvanzaConSuUltimaRonda() {
        TablaDePosiciones tabla = new TablaDePosiciones();

        new ResultadoCombate(1, 1, 2, 70, 0, 1).aplicarA(tabla);
        new ResultadoCombate(2, 1, 3, 20, 0, 1).aplicarA(tabla);

        List<Posicion> ordenada = tabla.ordenada();
        assertEquals(1, ordenada.get(0).getIdPareja()); // llegó a la ronda 2
        assertEquals(2, ordenada.get(0).getUltimaRonda());
        assertEquals(20, ordenada.get(0).getVidaTotalFinal());
    }
}