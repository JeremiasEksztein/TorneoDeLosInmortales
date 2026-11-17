package eventos;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeraldoImperialTest {

    private ByteArrayOutputStream buffer;
    private HeraldoImperial heraldo;

    @BeforeEach
    void preparar() {
        buffer = new ByteArrayOutputStream();
        heraldo = new HeraldoImperial(
            new PrintStream(buffer, true, StandardCharsets.UTF_8));
    }

    @Test
    void imprimeLaDescripcionDelAcontecimiento() {
        Acontecimiento a = new InicioDeCombate(1, 1, 2);

        heraldo.recibir(a);

        assertTrue(buffer.toString(StandardCharsets.UTF_8)
            .contains(a.getDescripcion()));
    }

    @Test
    void presentaLaTablaEnElOrdenRecibido() {
        heraldo.presentarTabla(List.of(
            new Posicion(4, 3, 50), new Posicion(1, 2, 80)));

        String salida = buffer.toString(StandardCharsets.UTF_8);
        assertTrue(salida.indexOf("Pareja 4") < salida.indexOf("Pareja 1"));
    }
}