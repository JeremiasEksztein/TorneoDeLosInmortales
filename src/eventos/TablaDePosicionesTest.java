package eventos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;



class TablaDePosicionesTest {

	/* Probamos primero que ordenada devuelva las posiciones
	 * ordenadas por los criterios correspondientes.
	 */
    @Test
    void ordenadaDevuelveLasParejasPorRondaVidaEIdentificador() {
        TablaDePosiciones tabla = new TablaDePosiciones();
        tabla.registrar(1, 1, 30);
        tabla.registrar(2, 2, 40);
        tabla.registrar(3, 2, 80);
        tabla.registrar(4, 3, 50);

        List<Posicion> ordenada = tabla.ordenada();

        assertEquals(4, ordenada.get(0).getIdPareja());
        assertEquals(3, ordenada.get(1).getIdPareja());
        assertEquals(2, ordenada.get(2).getIdPareja());
        assertEquals(1, ordenada.get(3).getIdPareja());
    }
    /*Este test agrega empates en la misma ronda. 
     * Hay dos parejas empatadas en vida total (para ver que desempata el ID) y otras dos con distinta vida 
     * (para ver que desempata la vida). 
     * También hay una pareja de una ronda anterior con mucha vida, 
     * que tiene que quedar última aunque tenga más vida que todas.*/
    @Test
    void ordenadaDesempataPorVidaTotalYLuegoPorIdentificador() {
        TablaDePosiciones tabla = new TablaDePosiciones();
        // Cuatro parejas empatadas en la ronda 2
        tabla.registrar(5, 2, 40);
        tabla.registrar(2, 2, 80);
        tabla.registrar(3, 2, 40);
        tabla.registrar(7, 2, 80);
        // Una pareja que llegó más lejos, con muy poca vida
        tabla.registrar(9, 3, 10);
        // Una pareja eliminada antes, con mucha vida
        tabla.registrar(1, 1, 100);

        List<Integer> ids = tabla.ordenada().stream()
            .map(Posicion::getIdPareja)
            .toList();

        // 9: mayor ronda. Luego, en la ronda 2: 2 y 7 (vida 80, desempata el id),
        // y 3 y 5 (vida 40, desempata el id). Última: la 1, que quedó en la ronda 1.
        assertEquals(List.of(9, 2, 7, 3, 5, 1), ids);
    }
    /* Ahora probamos colocar la misma pareja. De tal forma que el comportamiento pase a ser
     * reemplazar correctamente la fila donde ya estaba esa pareja.
     */
    @Test
    void registrarReemplazaLaFilaDeLaMismaPareja() {
        TablaDePosiciones tabla = new TablaDePosiciones();
        tabla.registrar(1, 1, 60);
        tabla.registrar(1, 2, 25);

        List<Posicion> ordenada = tabla.ordenada();

        assertEquals(1, ordenada.size());
        assertEquals(2, ordenada.get(0).getUltimaRonda());
        assertEquals(25, ordenada.get(0).getVidaTotalFinal());
    }
}