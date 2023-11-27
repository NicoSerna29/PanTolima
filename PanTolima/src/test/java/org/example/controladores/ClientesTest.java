package org.example.controladores;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ClientesTest {
    @Mock
    BDClientes bd_clts;
    @Test
    void agregarClientes() {
        Cliente MariaEntrada =
                new Cliente(111201934, "María Fernandez Cruz", "Meseta Alta M1C3", 3218045672, "mariaFdzCz@gmail.com");
        Cliente MariaEsperada =
                new Cliente(111201934, "María Fernandez Cruz", "Meseta Alta M1C3", 3218045672, "mariaFdzCz@gmail.com");
        ControladorClientes clts = new ControladorClientes(bd_clts);
        Mockito.when(bd_clts.guardarClientes(MariaEntrada)).thenReturn(MariaEntrada);

        Cliente MariaResultado = clts.guardarClientes(MariaEntrada);

        Assertions.assertEquals(MariaEsperada, MariaResultado);
    }

    @Test
    void agregaMalCliente(){
        Cliente MariaEntrada =
                new Cliente(0, "María Fernandez Cruz", "Meseta Alta M1C3", 3218045672, "mariaFdzCz@gmail.com");
        Cliente MariaEsperada =
                new Cliente(111201934, "María Fernandez Cruz", "Meseta Alta M1C3", 3218045672, "mariaFdzCz@gmail.com");
        ControladorClientes clts = new ControladorClientes(bd_clts);
        Mockito.when(bd_clts.guardarClientes(MariaEntrada)).thenReturn(MariaEntrada);

        Cliente MariaResultado = clts.guardarClientes(MariaEntrada);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {clts.guardarCliente(MariaEntrada);});
    }

    @Test
    void enviarCorreoCliente() {
        Cliente Maria =
                new Cliente(111201934, "María Fernandez Cruz", "Meseta Alta M1C3", 3218045672, "mariaFdzCz@gmail.com");
        Cliente Pedro =
                new Cliente(1005749294, "Pedro Rodrigez vallejo", "Divino Niño", 3105637821, "PedroRgezVll@gmail.com");
        Cliente Mateo =
                new Cliente(1056785241, "Mateo Uribe Santos", "Meseta Alta M6C1", 3119987842, "MateoUrbSts10@gmail.com");
        Lista<Cliente> Clientes = List.of(Maria, Pedro, Mateo);

        Cliente ClienteGanador =
                new Cliente(1056785241, "mateoSTS20@gmail.com");

        ControladorClientes clts =
                new ControladorClientes(bd_clts);
        Mockito.when(bd_clts.guardarCliente(Clientes)).thenReturn(Clientes);
        Mockito.when(bd_clts.enviarCorreo(ClienteGanador)).thenReturn(ClienteGanador);

        boolean envio = clts.enviarCorreo(ClienteGanador);

        Assertions.assertTrue(envio);

    }

    @Test
    void modificarInfoCliente(){
        Cliente Maria =
                new Cliente(111201934, "María Fernandez Cruz", "Meseta Alta M1C3", 3218045672, "mariaFdzCz@gmail.com");
        Cliente clienteInfo1 =
                new Cliente(111201934, "Santa Genoveva Sector las Brisas");
        Cliente clienteInfo2 =
                new Cliente(111201934, 3006592201);

        Cliente clienteEsperado =
                new Cliente(111201934, "María Fernandez Cruz", "Santa Genoveva Sector las Brisas", 3006592201, "mariaFdzCz.gmail.com");

        ControladorClientes clts =
                new ControladorClientes(bd_clts);

        Mockito.when(bd_clts.modificarInfoCliente(Maria, clienteInfo1)).thenReturn(Maria);
        Mockito.when(bd_clts.modificarInfoCliente(Maria, clienteInfo2)).thenReturn(Maria);

        Cliente clienteTerminado = clts.modificarInfoCliente(Maria, clienteInfo1);
        clienteTerminado = clts.modificarInfoCliente(clienteTerminado, clienteInfo2);

        Assertions.assertEquals(clienteEsperado, clienteTerminado);
    }
}
