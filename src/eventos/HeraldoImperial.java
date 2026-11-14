package eventos;

import java.io.PrintStream;
import java.util.List;
/* Heraldo Imperial usa un "segundo constructor" para facilitar la impresión por consola
 * El famoso printStream de más abajo.
 */
public class HeraldoImperial implements Interesado {

    private final PrintStream salida;

    public HeraldoImperial() {
        this(System.out);
    }

    public HeraldoImperial(PrintStream salida) {
        this.salida = salida;
    }

    @Override
    public void recibir(Acontecimiento a) {
        salida.println(a.getDescripcion());
    }

    public void presentarTabla(List<Posicion> tabla) {
        salida.println("=== Tabla de posiciones ===");
        int lugar = 1;
        for (Posicion p : tabla) {
            salida.println(lugar + ". Pareja " + p.getIdPareja()
                + " | última ronda: " + p.getUltimaRonda()
                + " | vida total: " + p.getVidaTotalFinal());
            lugar++;
        }
    }
}