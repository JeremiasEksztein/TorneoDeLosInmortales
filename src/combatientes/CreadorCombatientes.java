package combatientes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;

import excepciones.TipoDeCombatienteDesconocidoException;

public class CreadorCombatientes {
    
    private int proximoId = 1;

    public Combatiente crear(String tipo, String nombre, Escuela escuela) {
        Combatiente nuevo;

        // Evaluamos el string del tipo y llamamos al constructor correspondiente
        switch (tipo) {
            case "Cultivador de la Espada":
                nuevo = new CultivadorDeLaEspada(this.proximoId, nombre);
                break;
                
            case "Monje del Cuerpo Celestial":
                nuevo = new MonjeDelCuerpoCelestial(this.proximoId, nombre);
                break;
                
            case "Hechicero del Vacío":
                nuevo = new HechiceroDelVacio(this.proximoId, nombre);
                break;
                
            default:
                // Si escriben mal el nombre o mandan un tipo que no existe, falla con una excepción
                throw new TipoDeCombatienteDesconocidoException("Combatiente ingresado desconocido: " + tipo);
        }

        // Aumentamos el contador para el próximo personaje
        this.proximoId++;
        // Le pasamos el personaje a la escuela para que le dé sus técnicas y equipamiento
        escuela.preparar(nuevo);
        
        return nuevo;
    }
}
