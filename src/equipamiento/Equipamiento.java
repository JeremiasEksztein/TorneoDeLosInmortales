package equipamiento;

public abstract class Equipamiento {

    private String nombre;
    private TipoEquipamiento tipo;
    private int daño;
    private int dañoRecibido;
    private int vidaMaxima;
    private int energiaMaxima;

    protected Equipamiento(String nombre, TipoEquipamiento tipo, int daño,
                           int dañoRecibido, int vidaMaxima,
                           int energiaMaxima) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.daño = daño;
        this.dañoRecibido = dañoRecibido;
        this.vidaMaxima = vidaMaxima;
        
        this.energiaMaxima = energiaMaxima;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDaño() {
        return daño;
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
    
    @Override
    public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || this.getClass() != o.getClass()) {
			return false;
		}
		Equipamiento e = (Equipamiento) o;
		return this.nombre.equals(e.nombre);
    }
    public  int getBonusVida(){
        return 0;
    }
    public  int getBonoDefensa()
    {
        return 0;
    }
    public  int getBonusEnergia()
    {
        return 0;
    }
    public int getBonoDaño() {
        return 0;
    }
}
