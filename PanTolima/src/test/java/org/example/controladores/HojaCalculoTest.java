package org.example.controladores;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class HojaCalculoTest {
    @Mock
    BDHojaCalculo bd_hc;
    //Conexcion base de datos
    @Test
    void guardarProducto(){
        Producto ProductoEntrada=
                new Producto(1, "Abuela", "Pan", 1, 3500.);
        //Producto que entra al sistema
        Producto ProductoEsperado=
                new Producto(1, "Abuela", "Pan", 1, 3500.);
        //Producto que se espera que "salga"(guarde)
        ControladorHojaCalculo chc= new ControladorHojaCalculo(bd_hc);
        //logica de la base de datos (crud)
        Mockito.when(bd_hc.guardarProducto(ProductoEntrada)).thenReturn(ProductoEntrada);
        //Comportamiento de la bd
        Producto ProductoResultado = chc.guardarProducto(ProductoEntrada);
        //Se aplica la logica de la base de datos con el controlador creado
        Assertions.assertEquals(ProductoEsperado, ProductoResultado);
        //Se verifica
    }
    @Test
    void guardarProductos() {
        Producto ProductoEntrada1 = new Producto(1, "Abuela", "Pan", 1, 3500.);
        Producto ProductoEntrada2 = new Producto(2, "Achira", "Pan", 8, 400.);
        Producto ProductoEntrada3 = new Producto(3, "Hawaiano", "Pan", 5, 3000.);
        Producto ProductoEntrada4 = new Producto(4, "Maria Luisa", "Pastel", 2, 25000.);
        List<Producto> Productos = List.of(ProductoEntrada1, ProductoEntrada2, ProductoEntrada3, ProductoEntrada4);

        ControladorHojaCalculo chc= new ControladorHojaCalculo(bd_hc);
        Mockito.when(bd_hc.guardarProductos(Productos).thenReturn(Productos);

        List<Producto> ProductoResultado = chc.guardarProductos(ProductoEntrada);

        Assertions.assertEquals(ProductoEsperado, ProductoResultado);
    }

    @Test
    void EliminarProducto(){
        Producto ProductoEntrada1=new Producto(9, "Abuela", "Pan", 1, 3500.);
        Producto ProductoEntrada2=new Producto(8, "Achira", "Pan", 8, 400.);
        Producto ProductoEntrada3=new Producto(16, "Hawaiano", "Pan", 5, 3000.);
        Producto ProductoEntrada4=new Producto(32, "Maria Luisa", "Pastel", 2, 25000.);
        List<Producto> ProductosEntrantes= List.of(ProductoEntrada1, ProductoEntrada2, ProductoEntrada3, ProductoEntrada4);
        List<Producto> ProductosEsperados= List.of(ProductoEntrada1, ProductoEntrada2, ProductoEntrada4);
        Producto Producto_Eliminar= new Producto(16, 5);

        ControladorHojaCalculo chc= new ControladorHojaCalculo(bd_hc);
        Mockito.when(bd_hc.guardarProducto(ProductosEntrantes).thenReturn(ProductosEntrantes);
        Mockito.when(bd_hc.elimnarProducto(ProductosEntrantes, Producto_Eliminar)).thenReturn(ProductosEntrantes);

        Producto ProductosResultado = chc.eliminarProducto(ProductosEntrantes, Producto_Eliminar);

        Assertions.assertEquals(ProductosEsperados, ProductosResultado);
    }

    @Test
    void EliminarMalProductos(){
        Producto ProductoEntrada1=new Producto(9, "Abuela", "Pan", 1, 3500.);
        Producto ProductoEntrada2=new Producto(8, "Achira", "Pan", 8, 400.);
        Producto ProductoEntrada3=new Producto(16, "Hawaiano", "Pan", 5, 3000.);
        Producto ProductoEntrada4=new Producto(32, "Maria Luisa", "Pastel", 2, 25000.);
        List<Producto> ProductosEntrantes= List.of(ProductoEntrada1, ProductoEntrada2, ProductoEntrada3, ProductoEntrada4);

        Producto Producto_Eliminar= new Producto(16, 13);

        ControladorHojaCalculo chc= new ControladorHojaCalculo(bd_hc);
        Mockito.when(bd_hc.guardarProducto(ProductosEntrantes)).thenReturn(ProductosEntrantes);
        Mockito.when(bd_hc.eliminarProducto(Producto_Eliminar)).thenReturn(ProductosEntrantes);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {chc.eliminarProducto(Producto_Eliminar);});
    }

    @Test
    void modificarProducto(){
        Producto MariaLuisa=new Producto(32, "Maria Luisa", "Pastel", 2, 25000.);
        Producto modificacion1 = new Producto(32, "Pasteleria");
        Producto modificacion2 = new Producto(32, 30000.);

        Producto productoEsperado = new Producto(32, "Maria Luisa", "Pasteleria", 2, 30000.);

        ControladorHojaCalculo chc = new ControladorHojaCalculo(bd_hc);
        Mockito.when(bd_hc.guardarProducto(MariaLuisa)).thenReturn(MariaLuisa);
        Mockito.when(bd_hc.modificarProducto(modificacion1)).thenReturn(MariaLuisa);
        Mockito.when(bd_hc.modificarProducto(modificacion2)).thenReturn(MariaLuisa);

        Producto ProductoTerminado = chc.modificarProducto(MariaLuisa, modificacion1);
        ProductoTerminado = chc.modificarProducto(ProductoTerminado, modificacion2);

        Assertions.assertEquals(productoEsperado, ProductoTerminado);
    }

    @Test
    void calcularGananciasBrutas(){
        //Venta Pan =new Venta(id, cantidad);
        Venta MariaLuisa =new Venta(32, 34);
        Venta Pescador =new Venta(17, 42);
        Venta JugoHit =new Venta(25, 64);
        Venta BolsaLeche =new Venta(41, 17);
        Venta Volcanes =new Venta(53, 25);
        //Venta Ganancias= new Venta(AñoMes, ventaTotal);
        Venta Ganancias_Esperadas= new Venta(20232, 909000.);

        List<Venta> ProductosVendidos = List.of(MariaLuisa, Pescador, JugoHit, BolsaLeche, Volcanes);

        ControladorHojaCalculo chc= new ControladorHojaCalculo(bd_hc);
        Mockito.when(bd_hc.getGananciasBrutasMes(ProductosVendidos).thenReturn(ProductosVendidos);

        Venta GananciasNetas = chc.getGananciasBrutasMes(ProductosVendidos);
        Assertions.assertEquals(Ganancias_Esperadas, GananciasNetas);
    }
    @Test
    void calcularGananciasNetas(){
        //Venta Pan =new Venta(id, cantidad);
        Venta PanAbuela =new Venta(9, 67);
        Venta PanPizza =new Venta(14, 38);
        Venta PanYema =new Venta(7, 105);
        Venta CocaCola350 =new Venta(23, 79);
        Venta BolsaAgua =new Venta(20, 132);

        //Venta Ganancias= new Venta(AñoMes, ventaTotal);
        Venta gananciasEsperadas= new Venta(20232, 909000.);
        List<Venta> ProductosVendidos = List.of(PanAbuela, PanPizza, PanYema, CocaCola350, BolsaAgua);

        Producto PanAbuelaDesecho =new Producto(9, 10);
        Producto PanYemaDesecho =new Producto(7, 20);
        List<Venta> ProductosDesechos = List.of(PanAbuelaDesecho, PanYemaDesecho);

        ControladorHojaCalculo chc= new ControladorHojaCalculo(bd_hc);
        Mockito.when(bd_hc.getGananciasNetasMes(ProductosVendidos, ProductosDesechos).thenReturn(ProductosVendidos);
        Venta GananciasNetas = chc.getGananciasNetasMes(ProductosVendidos, ProductosDesechos);

        //REVISAR PROFE
        Assertions.assertEquals(Ganancias_Esperadas, GananciasNetas);
    }
}