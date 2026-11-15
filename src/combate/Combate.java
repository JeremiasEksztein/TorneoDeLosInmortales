package combate;
import java.util.List;

import combatientes.Combatiente;
import eventos.Acontecimiento;
import eventos.Eliminacion;
import eventos.InicioDeCombate;
import eventos.Interesado;
import eventos.PublicadorAcontecimiento;
import eventos.ResultadoCombate;
import tecnicas.Accion;

public class Combate {
	private Pareja parejaA;
	private Pareja parejaB;
	
	private int numeroRonda;
	private PublicadorAcontecimiento publicador;
	private int LIMITE_ASALTOS;
	private int ENERGIA_POR_ASALTO;

	public Combate(Pareja parejaA, Pareja parejaB, int numeroRonda,PublicadorAcontecimiento publicador,
                   int limiteAsaltos, int energiaPorAsalto) {

		if (parejaA == null || parejaB == null || publicador == null) {
			throw new IllegalArgumentException("Las parejas y el publicador son obligatorios.");
		}
		if (limiteAsaltos <= 0 || energiaPorAsalto < 0) {
			throw new IllegalArgumentException("Los parámetros del combate no son válidos.");
		}
		this.parejaA = parejaA;
		this.parejaB = parejaB;
		this.numeroRonda = numeroRonda;
		this.publicador = publicador;
		this.LIMITE_ASALTOS = limiteAsaltos;
		this.ENERGIA_POR_ASALTO = energiaPorAsalto;
	}

	public Combate(Pareja parejaA, Pareja parejaB, int numeroRonda,
			PublicadorAcontecimiento publicador) {
		this(parejaA, parejaB, numeroRonda, publicador, 100, 10); // valores por defecto
	}

	public Combate(Pareja parejaA, Pareja parejaB) {
		this(parejaA, parejaB, 1, new PublicadorAcontecimiento());
	}
	
	public ResultadoCombate ejecutar() {
	    // 1. Preparar combatientes (restaurar vida/energía al máximo y limpiar estados del combate anterior)
	    parejaA.reiniciarParaCombate();
	    parejaB.reiniciarParaCombate();

	    // Determinar orden de inicio por menor ID
	    Pareja primera = Integer.compare(parejaA.getId(),parejaB.getId()) < 0 ? parejaA : parejaB;
	    Pareja segunda = (primera == parejaA) ? parejaB : parejaA;

	    // Notificar inicio de combate
	    publicador.notificar(new InicioDeCombate(numeroRonda, primera.getId(), segunda.getId()));

	    int asaltoActual = 0;

	 // 2. Bucle de asaltos
        while (primera.estaEnPie() && segunda.estaEnPie() && asaltoActual < LIMITE_ASALTOS) {
            asaltoActual++;
            jugarAsalto(primera, segunda);
        }

        // 3. Resolución del combate
        Pareja ganadora = determinarGanadora(primera, segunda);
        ResultadoCombate resultado = new ResultadoCombate(
            numeroRonda,
            primera.getId(),
            segunda.getId(),
            primera.vidaTotal(),
            segunda.vidaTotal(),
            ganadora.getId()
        );

        // Notificar el resultado final del combate
        publicador.notificar(resultado);

        return resultado;
	}
	private void jugarAsalto(Pareja primera, Pareja segunda) {
        // Al comenzar cada asalto, cada combatiente en pie recupera energía fija sin superar su máximo
        primera.recuperarEnergiaAsalto(ENERGIA_POR_ASALTO);
        segunda.recuperarEnergiaAsalto(ENERGIA_POR_ASALTO);

        // Turno completo de la primera pareja (menor ID)
        jugarTurno(primera, segunda);

        // Si en cualquier momento una pareja queda sin integrantes en pie, el combate termina
        if (segunda.estaEnPie()) {
            jugarTurno(segunda, primera);
        }
    }

    private void jugarTurno(Pareja atacante, Pareja defensora) {
        // Integrantes en pie ordenados por menor ID
        List<Combatiente> activos = atacante.integrantesEnOrden();

        for (Combatiente combatiente : activos) {
            if (!combatiente.estaEnPie() || defensora.estaDerrotada()) {
                break;
            }

            // Si implementaste el bonus de efectos temporales (veneno/regeneración)
            combatiente.procesarEfectosTemporales();
            if (!combatiente.estaEnPie()) {
                publicador.notificar(new Eliminacion(combatiente));
                continue;
            }

            List<Combatiente> rivalesEnPie = defensora.getCombatientesEnPieOrdenados();

            // El combatiente decide y ejecuta su acción (técnica o ataque básico)
            Acontecimiento eventoAccion = combatiente.ejecutarTurno(atacante,rivalesEnPie);
            publicador.notificar(eventoAccion);

            // Verificar si algún rival quedó fuera de combate (vida == 0) tras la acción
            for (Combatiente rival : rivalesEnPie) {
                if (!rival.estaEnPie()) {
                    publicador.notificar(new Eliminacion(rival));
                }
            }
        }
    }

    private Pareja determinarGanadora(Pareja primera, Pareja segunda) {
        if (primera.estaDerrotada()) {
            return segunda;
        }
        if (segunda.estaDerrotada()) {
            return primera;
        }

        // Límite de asaltos alcanzado: gana la pareja con más vida total
        int vidaPrimera = primera.vidaTotal();
        int vidaSegunda = segunda.vidaTotal();

        if (vidaPrimera != vidaSegunda) {
            return vidaPrimera > vidaSegunda ? primera : segunda;
        }

        // En empate de vida total, gana la de menor identificador ('primera' ya es la de menor ID)
        return primera;
    }
	private void elegirObjetivo(Pareja pareja) {
		
	}
	
	private boolean esEmpate() {
		return parejaA != null && parejaB != null
				&& parejaA.vidaTotal() == parejaB.vidaTotal();
	}
}
