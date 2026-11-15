package combatientes;

public class MonjeDelCuerpoCelestial extends Combatiente {
    private static final int VIDA_INICIAL = 150;
    private static final int ENERGIA_INICIAL = 300;

    public MonjeDelCuerpoCelestial(int id, String nombre) {
        super(id, nombre, VIDA_INICIAL, ENERGIA_INICIAL);
    }
    
    @Override
    public int calcularDanioRecibido(int cantidad) {
        return cantidad;
    }

}
