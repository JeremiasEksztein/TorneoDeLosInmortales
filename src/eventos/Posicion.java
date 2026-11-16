package eventos;

import java.util.Objects;

public class Posicion implements Comparable<Posicion> {

    private final int idPareja;
    private final int ultimaRonda;
    private final int vidaTotalFinal;

    public Posicion(int idPareja, int ultimaRonda, int vidaTotalFinal) {
        this.idPareja = idPareja;
        this.ultimaRonda = ultimaRonda;
        this.vidaTotalFinal = vidaTotalFinal;
    }

    public int getIdPareja() {
        return idPareja;
    }

    public int getUltimaRonda() {
        return ultimaRonda;
    }

    public int getVidaTotalFinal() {
        return vidaTotalFinal;
    }

    // Orden de la tabla: mayor ronda primero, luego mayor vida total, luego menor id.
    @Override
    public int compareTo(Posicion otra) {
        if (ultimaRonda != otra.ultimaRonda) {
            return Integer.compare(otra.ultimaRonda, ultimaRonda);
        }
        if (vidaTotalFinal != otra.vidaTotalFinal) {
            return Integer.compare(otra.vidaTotalFinal, vidaTotalFinal);
        }
        return Integer.compare(idPareja, otra.idPareja);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Posicion posicion = (Posicion) o;
        return idPareja == posicion.idPareja
                && ultimaRonda == posicion.ultimaRonda
                && vidaTotalFinal == posicion.vidaTotalFinal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPareja, ultimaRonda, vidaTotalFinal);
    }
}