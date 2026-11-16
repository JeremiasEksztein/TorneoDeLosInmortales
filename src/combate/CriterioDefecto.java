package combate;
import combatientes.Combatiente;
import tecnicas.Accion;
import tecnicas.AtaqueBasico;
import tecnicas.Tecnica;
import tecnicas.TipoTecnica;


public class CriterioDefecto implements CriterioAccion {

    @Override
    public Accion elegirAccion(Combatiente ejecutor, Pareja pareja) {
    	if (ejecutor == null || pareja == null) {
			throw new IllegalArgumentException("El ejecutor y la pareja no pueden ser nulos.");
		}
    	
        // 1. Prioridad 1: Técnica de recuperación si le falta vida
        if (ejecutor.faltaVida()) {
            Tecnica recuperacion = buscarTecnica(ejecutor, pareja, TipoTecnica.RECUPERACION);
            if (recuperacion != null) return recuperacion;
        }

        // 2. Prioridad 2: Técnica defensiva si no tiene una activa
        if (!ejecutor.tieneDefensaActiva()) {
            Tecnica defensiva = buscarTecnica(ejecutor, pareja, TipoTecnica.DEFENSIVA);
            if (defensiva != null) return defensiva;
        }

        // 3. Prioridad 3: Técnica ofensiva con más usos restantes[cite: 4]
        Tecnica ofensiva = buscarOfensivaMasUsos(ejecutor, pareja);
        if (ofensiva != null) return ofensiva;

        // 4. Si ninguna es posible (por energía, usos o regla del compañero), se realiza un ataque básico[cite: 4]
        return new AtaqueBasico();
    }

    // --- Métodos privados de ayuda para mantener el código limpio ---

    private Tecnica buscarTecnica(Combatiente ejecutor, Pareja pareja, TipoTecnica tipoBuscado) {
        // Recorremos las técnicas que el combatiente tiene cargadas en su mapa
        for (Tecnica t : ejecutor.getTecnicas().keySet()) {
            if (t.getTipo() == tipoBuscado && esValidaParaUsar(t, ejecutor, pareja)) {
                return t; // Devuelve la primera que encuentra (resuelve el desempate por orden)
            }
        }
        return null;
    }

    private Tecnica buscarOfensivaMasUsos(Combatiente ejecutor, Pareja p) {
        Tecnica mejorOfensiva = null;
        int maxUsos = -1;

        for (Tecnica t : ejecutor.getTecnicas().keySet()) {
            if (t.getTipo() == TipoTecnica.OFENSIVA && esValidaParaUsar(t, ejecutor, p)) {
                int usosActuales = ejecutor.usosRestantes(t);
                
                // Si encontramos una con MÁS usos estrictamente, la guardamos.
                // Si tienen la misma cantidad de usos (empate), no entra al if, 
                // manteniendo seleccionada la primera que apareció en el orden de la escuela[cite: 4].
                if (usosActuales > maxUsos) {
                    mejorOfensiva = t;
                    maxUsos = usosActuales;
                }
            }
        }
        return mejorOfensiva;
    }

    private boolean esValidaParaUsar(Tecnica t, Combatiente ejecutor, Pareja pareja) {
    	return ejecutor.puedeEjecutar(t) && pareja.puedeUsar(ejecutor, t);
    }
}
