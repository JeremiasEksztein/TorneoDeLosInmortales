package eventos;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;
/**
 * Pruebas unitarias de {Posicion}.
 *
 * <p>Verifican el criterio de orden de la tabla de posiciones definido en el
 * enunciado: 
 * 1. las parejas se ordenan por la última ronda jugada,
 * 2. en caso de empate por la vida total con la que terminaron su último combate
 * 3. finalmente, por identificador.</p>
 *
 * <p>
 * Decisión del grupo: el enunciado no indica el sentido del orden, por lo
 * que 
 * 4. Se establece que ubican a la pareja más arriba, una 
 * - mayor ronda
 * - una mayor vida total 
 * - un menor identificador 
 *  desempata a su favor. </p>
 */
class PosicionTest {
	
	   /**
     * 
     * 1.1. Comprueba que una lista desordenada de posiciones queda ordenada por
     * última ronda jugada (descendente), vida total final (descendente) e
     * identificador de pareja (ascendente).
     *
     */
    @Test
    void seOrdenaPorRondaVidaEIdentificador() {
        Posicion campeona = new Posicion(3, 3, 50);
        Posicion finalistaConMasVida = new Posicion(1, 2, 80);
        Posicion finalistaConMenosVida = new Posicion(2, 2, 40);
        Posicion empatadaConMenorId = new Posicion(4, 1, 30);
        Posicion empatadaConMayorId = new Posicion(5, 1, 30);

        List<Posicion> lista = new ArrayList<>(List.of(
            empatadaConMayorId, finalistaConMenosVida, campeona,
            empatadaConMenorId, finalistaConMasVida));
        Collections.sort(lista);

        assertEquals(List.of(campeona, finalistaConMasVida,
            finalistaConMenosVida, empatadaConMenorId, empatadaConMayorId), lista);
    }
}