package combatientes;

public class HechiceroDelVacio extends Combatiente {
    private static final int VIDA_INICIAL = 150;
    private static final int ENERGIA_INICIAL = 300;
    private static final double REDUCCION_COSTO = 0.25; // 25% menos costo al ejecutar técnicas
    
    public HechiceroDelVacio(int id, String nombre) {
        super(id, nombre, VIDA_INICIAL, ENERGIA_INICIAL);
    }

    @Override
    public int calcularCostoEnergia(int cantidad) {
        int reduccion = (int) Math.round(cantidad * REDUCCION_COSTO);
        return (cantidad - reduccion);
    }
}
