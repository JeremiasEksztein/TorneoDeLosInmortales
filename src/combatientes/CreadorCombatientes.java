package combatientes;

import excepciones.TipoDeCombatienteDesconocidoException;

public class CreadorCombatientes {
	private int proximoId = 1;
		
	public Combatiente crear(String tipoCombatiente, String nombre, Escuela escuela) {
		Combatiente nuevoCombatiente = null;

		switch(tipoCombatiente) {
		case "CULTIVADOR_ESPADA":
			nuevoCombatiente = new CultivadorDeLaEspada(proximoId, nombre);
			break;
		case "MONJE_CUERPO_CELESTIAL":
			nuevoCombatiente = new MonjeDelCuerpoCelestial(proximoId, nombre);
			break;
		case "HECHICERO_VACIO":
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
