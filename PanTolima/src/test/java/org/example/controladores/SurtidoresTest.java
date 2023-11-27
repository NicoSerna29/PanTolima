package org.example.controladores;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class SurtidoresTest {

    @Mock
    BDSurtidores bd_stds;

    @Test
    void agregarSurtidor(){
        Surtidor surtidorEntrada =
                new Surtidor(1, "Ovidio Cardenas", 3106578482, "OvidioCardenas@hotmail.com", "Transporta mercancia importada de otras regiones", "", "Llamar con 2 dias de anticipacion");
        Surtidor surtidorEsperado =
                new Surtidor(1, "Ovidio Cardenas", 3106578482, "OvidioCardenas@hotmail.com", "Transporta mercancia importada de otras regiones", "", "Llamar con 2 dias de anticipacion");

        ControladorSurtidores stdrs = new ControladorMateriaPrima(bd_stds);
        Mockito.when(bd_stds.guardarSurtidor(SurtidorEntrada)).thenReturn(SurtidorEntrada);

        Surtidor surtidorResultado = stdrs.guardarMateriaPrima(bd_stds);

        Assertions.assertEquals(surtidorEsperado, surtidorResultado);
    }
    @Test
    void surtidoresPopulares(){
        Surtidor surtidorEntrada1 =
                new Surtidor(5, "Ovidio Cardenas", 3106578482, "OvidioCardenas@hotmail.com", "Transporta mercancia importada de otras regiones", "", "Llamar con 2 dias de anticipacion");
        Surtidor surtidorEntrada2 =
                new Surtidor(7, "Avivar", 6059231, "Avivar_2023@gmail.com", "Surtidora de Huevos", "Otros Productos de Campo...", "Llamar entre las 6AM y 2PM");
        Surtidor surtidorEntrada3 =
                new Surtidor(2, "Colanta", 6019323, "ColantaCol@gmail.com", "Surtidora principalmente Leche de vaca", "Surtidora de demás productos lacteos y algunos carnicos...", "Llamar solo los fines de semana");
        Surtidor surtidorEntrada4 =
                new Surtidor(9, "Harinas del Valle", 6004576, "HarinasValle23@gmail.com", "Transportadora de Harina", "Surtidora de harina y otros productos derivados del trigo...", "Llamar despues de las 3PM hasta las 12PM");

        Surtidor surtidorPopular1 = new Surtidor(5);
        Surtidor surtidorPopular2 = new Surtidor(9);

        List<Surtidor> listaPopular = List.of(surtidorPopular1, surtidorPopular2);
        List<Surtidor> listaEsperada = List.of(surtidorPopular1, surtidorPopular2);

        List<Surtidor> SurtidoresEntrantes = List.of(surtidorEntrada1, surtidorEntrada2, surtidorEntrada3, surtidorEntrada4);
        Mockito.when(bd_stds.guardarSurtidor(SurtidoresEntrantes)).thenReturn(SurtidoresEntrantes);
        Mockito.when(bd_stds.agregarPopular(listaPopular)).thenReturn(listaPopular);
        ControladorSurtidores stdrs = ControladorSurtidores(bd_stds);

        List<Surtidor> listaResultado = stdrs.agregarPopular(SurtidoresEntrantes, listaPopular);

        Assertions.assertEquals(listaEsperada, listaResultado);
    }

    @Test
    void ModificarInfoSurtidor() {
        Surtidor surtidorEntrada1 = new Surtidor(5, "Ovidio Cardenas", 3106578482, "OvidioCardenas@hotmail.com", "Transporta mercancia importada de otras regiones", "", "Llamar con 2 dias de anticipacion");
        Surtidor surtidorCambio1 = new Surtidor(5, 3106558082);
        Surtidor surtidorCambio2 = new Surtidor(5,
                "Ovidio Cardenas" +
                        "Principales Rutas: " +
                        "Pereira-Chocó, Chocó-Pereira, Chocó-Medellin, Medellin-Pereira", "Pereira-Armenia" +
                "Productos Principales que transporta: " +
                "Queso Costeño, Huevos, Frutas, Objetos gral, Utencilios, etc.");
        Surtidor surtidorEsperado =
                new Surtidor(5, "Ovidio Cardenas", 3106558082, "OvidioCardenas@hotmail.com", "Transporta mercancia importada de otras regiones", "Ovidio Cardenas" +
                        "Principales Rutas: " +
                        "Pereira-Chocó, Chocó-Pereira, Chocó-Medellin, Medellin-Pereira", "Pereira-Armenia" +
                        "Productos Principales que transporta: " +
                        "Queso Costeño, Huevos, Frutas, Objetos gral, Utencilios, etc.", "Llamar con 2 dias de anticipacion");

        Mockito.when(bd_stds.modificarInfoSurtidor(surtidorEntrada1, surtidorCambio1)).thenReturn(surtidorEntrada1);
        Mockito.when(bd_stds.modificarInfoSurtidor(surtidorEntrada1, surtidorCambio2)).thenReturn(surtidorEntrada1);
        ControladorSurtidores stdrs = ControladorSurtidores(bd_stds);

        Surtidor surtidorresultado = stdrs.modificarInfoSurtidor(surtidorEntrada1, surtidorCambio1);
        surtidorresultado = stdrs.modificarInfoSurtidor(surtidorresultado, surtidorCambio2);

        Assertions.assertEquals(surtidorEsperado, surtidorresultado);
    }
}
