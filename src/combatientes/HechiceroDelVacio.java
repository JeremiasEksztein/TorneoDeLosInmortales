package combatientes;

import equipamiento.Equipamiento;

public class HechiceroDelVacio extends Combatiente {
    private static final int VIDA_INICIAL = 150;
    private static final int ENERGIA_INICIAL = 300;
    private static final double REDUCCION_COSTO = 0.1;
    
    public HechiceroDelVacio(int id, String nombre) {
        super(id, nombre, VIDA_INICIAL, ENERGIA_INICIAL);
    }

    @Override
    public int calcularCostoEnergia(int cantidad) {
        return cantidad - (int)(cantidad * REDUCCION_COSTO);
    }

}
