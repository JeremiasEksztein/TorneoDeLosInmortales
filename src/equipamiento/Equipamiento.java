package equipamiento;

import java.util.Objects;

public abstract class Equipamiento {

    private final String nombre;
    private final TipoEquipamiento tipo;

    protected Equipamiento(String nombre, TipoEquipamiento tipo) {
    	if (nombre == null || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre del equipamiento no puede ser nulo ni vacío.");
		}
		if (tipo == null) {
			throw new IllegalArgumentException("El tipo de equipamiento no puede ser nulo.");
		}
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
    
    @Override
	public int hashCode() {
		return Objects.hash(this.nombre);
	}
   
}
