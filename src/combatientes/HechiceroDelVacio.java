package combatientes;

import equipamiento.Equipamiento;

public class HechiceroDelVacio extends Combatiente {
    private static final int VIDA_INICIAL = 150;
    private static final int ENERGIA_INICIAL = 300;

    public HechiceroDelVacio(int id, String nombre) {
        super(id, nombre, VIDA_INICIAL, ENERGIA_INICIAL);
    }

    @Override
    public int calcularDanioProvocado(int cantidad) {
        return cantidad;
    }

    @Override
    public int calcularDanioRecibido(int cantidad) {
        return cantidad;
    }

    @Override
    public int calcularCostoEnergia(int cantidad) {
        return cantidad;
    }
}
