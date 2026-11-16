package eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PublicadorAcontecimiento {
	private final List<Interesado> interesados;

    public PublicadorAcontecimiento() {
        this.interesados = new ArrayList<>();
    }

    public void suscribir(Interesado interesado) {
        Objects.requireNonNull(interesado, "El interesado no puede ser nulo.");
        if (!this.interesados.contains(interesado)) {
            this.interesados.add(interesado);
        }
    }

    public void desuscribir(Interesado interesado) {
        if (interesado != null) {
            this.interesados.remove(interesado);
        }
    }

    public void notificar(Acontecimiento acontecimiento) {
        
    	if(acontecimiento == null) {
    		throw new IllegalArgumentException("El acontecimiento no puede ser nulo");
    	}
        
        for (Interesado interesado : this.interesados) {
            interesado.recibir(acontecimiento);
        }
    }
}
