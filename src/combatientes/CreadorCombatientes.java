package combatientes;

import excepciones.TipoDeCombatienteDesconocidoException;

public class CreadorCombatientes {
	private int proximoId = 1;
		
	public Combatiente crear(String tipoCombatiente, String nombre, Escuela escuela) {
		Combatiente nuevoCombatiente = null;

		switch(tipoCombatiente) {
		case "Cultivador de la Espada":
			nuevoCombatiente = new CultivadorDeLaEspada(proximoId, nombre);
			break;
		case "Monje del Cuerpo Celestial":
			nuevoCombatiente = new MonjeDelCuerpoCelestial(proximoId, nombre);
			break;
		case "Hechicero del Vacio":
			nuevoCombatiente = new HechiceroDelVacio(proximoId, nombre);
			break;
		
		default: 
			throw new TipoDeCombatienteDesconocidoException("Combatiente ingresado desconocido: " + tipoCombatiente);
		}
		proximoId++;
		escuela.preparar(nuevoCombatiente);
		return nuevoCombatiente;
	}
	
}
