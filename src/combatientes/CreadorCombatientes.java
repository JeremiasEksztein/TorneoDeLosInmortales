package combatientes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;

import excepciones.TipoDeCombatienteDesconocidoException;

public class CreadorCombatientes {
	private int proximoId = 1;
	private final Map<String, BiFunction<Integer, String, Combatiente>> tipos = new LinkedHashMap<>();

		
	public CreadorCombatientes() {
		registrar("Cultivador de la Espada", CultivadorDeLaEspada::new);
		registrar("Monje del Cuerpo Celestial", MonjeDelCuerpoCelestial::new);
		registrar("Hechicero del Vacío", HechiceroDelVacio::new);
	}

	public void registrar(String tipo, BiFunction<Integer, String, Combatiente> constructor) {
		tipos.put(tipo, constructor);
	}

	public Combatiente crear(String tipo, String nombre, Escuela escuela) {
		BiFunction<Integer, String, Combatiente> constructor = tipos.get(tipo);
		if (constructor == null) {
			throw new TipoDeCombatienteDesconocidoException("Combatiente ingresado desconocido: " + tipo);
		}
		Combatiente nuevo = constructor.apply(proximoId, nombre);
		proximoId++;
		escuela.preparar(nuevo);
		return nuevo;
	}
}
