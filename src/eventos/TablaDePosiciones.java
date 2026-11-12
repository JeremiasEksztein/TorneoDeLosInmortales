package eventos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TablaDePosiciones {

    private final Map<Integer, Posicion> posiciones = new HashMap<>();

    // Registra el resultado de un combate. Si la pareja ya tenía una fila,
    // se reemplaza por la nueva, porque cuenta el último combate jugado.
    public void registrar(int idPareja, int ronda, int vidaTotal) {
        posiciones.put(idPareja, new Posicion(idPareja, ronda, vidaTotal));
    }

    // Devuelve una copia ordenada, para que nadie modifique la tabla desde afuera.
    public List<Posicion> ordenada() {
        List<Posicion> resultado = new ArrayList<>(posiciones.values());
        Collections.sort(resultado);
        return resultado;
    }
}
