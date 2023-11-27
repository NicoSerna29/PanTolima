package org.example.controladores;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MateriaPrimaTest {
    @Mock
    BDMateriaPrima bd_mp;
    @Test
    void agregarMateriaPrima(){
        MateriaPrima huevos = new MateriaPrima(3, "Paca de Huevos", 60, "PACA DE HUEVO DE 30U", "Avivar");
        MateriaPrima leche = new MateriaPrima(2, "Bolsa de leche", 80, "BOLSA DE LECHE DE 900ML", "Colanta");
        MateriaPrima agua = new MateriaPrima(1, "Bolsa con Agua", 120, "BOLSA CON AGUA DE 7L", "Hidratante");
        MateriaPrima harina = new MateriaPrima(4, "Harina", 50, "BULTO DE HARINA DE 50K", "Harinas del Valle");
        List<MateriaPrima> MateriasPrimasEntrantes = List.of(huevos, leche, agua, harina);
        List<MateriaPrima> MateriasPrimasEsperadas = List.of(huevos, leche, agua, harina);

        ControladorMateriaPrima cmp = new controladorMateriaPrima(bd_mp);
        Mockito.when(bd_mp.guardarMateriaPrima(MateriasPrimasEntrantes)).thenReturn(MateriasPrimasEntrantes);

        List<MateriaPrima> MateriasPrimasResultado = cmp.guardarMateriaPrima(MateriasPrimasEntrantes);

        Assertions.assertEquals(MateriasPrimasEsperadas, MateriasPrimasResultado);
    }

    @Test
    void modificarMateriaPrima(){
        MateriaPrima huevos = new MateriaPrima(3, "Paca de Huevos", 60, "PACA DE HUEVO DE 30U", "Avivar");
        MateriaPrima leche = new MateriaPrima(2, "Bolsa de leche", 80, "BOLSA DE LECHE DE 900ML", "Colanta");
        MateriaPrima agua = new MateriaPrima(1, "Bolsa con Agua", 120, "BOLSA CON AGUA DE 7L", "Hidratante");
        MateriaPrima harina = new MateriaPrima(4, "Harina", 50, "BULTO DE HARINA DE 50K", "Harinas del Valle");
        List<MateriaPrima> MateriasPrimasEntrantes = List.of(huevos, leche, agua, harina);

        MateriaPrima MTmodificador = new MateriaPrima(4, "Harinas Farallones");
        MateriaPrima MTEsperada = new MateriaPrima(4, "Harina", 50, "BULTO DE HARINA DE 50K", "Harinas Farallones");
        List<MateriaPrima> MateriasPrimasEsperadas = List.of(huevos, leche, agua, MTEsperada);

        ControladorMateriaPrima cmp = new controladorMateriaPrima(bd_mp);
        Mockito.when(bd_mp.guardarMateriaPrima(MateriasPrimasEntrantes)).thenReturn(MateriasPrimasEntrantes);
        Mockito.when(bd_mp.modificarMateriaPrima(MTmodificador)).thenReturn(MateriasPrimasEntrantes);

        List<MateriaPrima> MateriasPrimasTerminadas= cmp.modificarMateriaPrima(MateriasPrimasEntrantes, MTmodificador);

        Assertions.assertEquals(MateriasPrimasEsperadas, MateriasPrimasTerminadas);
    }


    @Test
    void eliminarMateriaPrima(){
        MateriaPrima huevos = new MateriaPrima(3, "Paca de Huevos", 3, "PACA DE HUEVO DE 30U", "Avivar");
        MateriaPrima leche = new MateriaPrima(2, "Bolsa de leche", 12, "BOLSA DE LECHE DE 900ML", "Colanta");
        MateriaPrima agua = new MateriaPrima(6, "Bolsa con Agua", 10, "BOLSA CON AGUA DE 7L", "Hidratante");
        MateriaPrima harina = new MateriaPrima(4, "Harina", 25, "BULTO DE HARINA DE 50K", "Harinas del Valle");
        List<MateriaPrima> MateriasPrimasEntrantes = List.of(huevos, leche, agua, harina);
        MateriaPrima MTElimnar = new MateriaPrima(6, 15);

        ControladorMateriaPrima cmp = new controladorMateriaPrima(bd_mp);
        Mockito.when(bd_mp.guardarMateriaPrima(MateriasPrimasEntrantes)).thenReturn(MateriasPrimasEntrantes);
        Mockito.when(bd_mp.eliminarMateriaPrima(MTElimnar)).thenReturn(MateriasPrimasEntrantes);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {cmp.eliminarCliente(MateriasPrimasEntrantes, MTEliminar);});
    }

}
