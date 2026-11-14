package eventos;

import java.util.ArrayList;
import java.util.List;

public class CronistaDeLaSecta implements Interesado {

    private final List<Acontecimiento> historial = new ArrayList<>();
    private final TablaDePosiciones tabla = new TablaDePosiciones();

    @Override
    public void recibir(Acontecimiento a) {
        historial.add(a);
        a.aplicarA(tabla);
    }

    // Copia no modificable: nadie altera el historial desde afuera.
    public List<Acontecimiento> getHistorial() {
        return List.copyOf(historial);
    }

    public List<Posicion> tablaDePosiciones() {
        return tabla.ordenada();
    }
}