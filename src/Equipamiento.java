public abstract class Equipamiento {

    private String nombre;
    private TipoEquipamiento tipo;
    private int daño;
    private int dañoRecibido;
    private int vidaMaxima;
    private int energia;
    private int energiaMaxima;

    protected Equipamiento(String nombre, TipoEquipamiento tipo, int daño,
                           int dañoRecibido, int vidaMaxima, int energia,
                           int energiaMaxima) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.daño = daño;
        this.dañoRecibido = dañoRecibido;
        this.vidaMaxima = vidaMaxima;
        this.energia = energia;
        this.energiaMaxima = energiaMaxima;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDaño() {
        return daño;
    }

    public int getEnergia() {
        return energia;
    }

    public int getDañoRecibido() {
        return dañoRecibido;
    }

    public int getVidaMax() {
        return vidaMaxima;
    }

    public int getEnergiaMax() {
        return energiaMaxima;
    }

    public TipoEquipamiento getTipo() {
        return tipo;
    }
}

