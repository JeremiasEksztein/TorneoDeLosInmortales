package equipamiento;

public abstract class Equipamiento {

    private String nombre;
    private TipoEquipamiento tipo;
    private int daño;
    private int dañoRecibido;
    private int vidaMaxima;
    private int energiaMaxima;

    protected Equipamiento(String nombre, TipoEquipamiento tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
   
    }

    public String getNombre() {
        return nombre;
    }


    public TipoEquipamiento getTipo() {
        return tipo;
    }
    
    
    public int calcularDanioBonificado(int cantidad) {return cantidad;}
    public int calcularEnergiaBonificada(int cantidad){return cantidad;}
    public int calcularVidaMaximaBonificada(int cantidad){return cantidad;}
    public int calcularDanioRecibidoBonificado(int cantidad){return cantidad;}
    
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
    
   
   
}
