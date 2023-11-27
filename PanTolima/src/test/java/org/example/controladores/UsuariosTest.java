package org.example.controladores;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UsuariosTest {
    @Mock
    BDUsuarios bd_usrs;

    //cargos: administrador, supervisor.
    @Test
    void registrarCargo() {
        Usuario admin = new Usuario(1, "Diana Patricia Gonzalez", 3123027704, "dipago12@gmail.com", "Choco115");
        Usuario supervisor = new Usuario(2, "Luz Aidy Gonzalez", 3146504422, "aidygova@hotmail.com", "150629amn");
        List<Usuario> usuariosEntrada = List.of(admin, supervisor);
        List<Usuario> usuariosEsperados = List.of(admin, supervisor);

        ControladorUsuarios cusrs = new ControladorUsuarios(bd_usrs);
        Mockito.when(bd_usrs.guadarUsuario(usuariosEntrada)).thenReturn(usuariosEntrada);

        List<Usuario> usuariosFinal = cusrs.guardarUsuario(usuariosEntrada);

        Assertions.assertEquals(usuariosEsperados, usuariosEntrada);
    }
    @Test
    void inicioSesionCargo(){
        Usuario supervisor1 = new Usuario(2, "Luz Aidy Gonzalez", 3146504422, "aidygova@hotmail.com", "150629amn");
        Usuario supervisor2 = new Usuario(3, "Nicolas Serna Gonzalez", 3218810044, "nicoserna@gmail.com", "nicolas29");
        Usuario supervisor3 = new Usuario(4, "Carlos Correa", 3104870316, "caliche_choco115@gmail.com", "Choco115");
        List<Usuario> usuariosEntrada = List.of(supervisor1, supervisor2, supervisor3);

        Usuario usuarioIngresar = new Usuario(2, "nicoserna@gmail.com", "nicolas29");

        ControladorUsuarios cusrs = new ControladorUsuarios(bd_usrs);
        Mockito.when(bd_usrs.guadarUsuario(usuariosEntrada)).thenReturn(usuariosEntrada);
        Mockito.when(bd_usrs.inicioSesion(usuarioIngresar)).thenReturn(usuarioIngresar);

        boolean iniciaSesion= cusrs.guardarUsuario(usuariosEntrada, usuarioIngresar);
        Assertions.assertTrue(iniciaSesion);
    }

    @Test
    void inicioSesionCargo(){
        Usuario supervisor1 = new Usuario(2, "Luz Aidy Gonzalez", 3146504422, "aidygova@hotmail.com", "150629amn");
        Usuario supervisor2 = new Usuario(3, "Nicolas Serna Gonzalez", 3218810044, "nicoserna@gmail.com", "nicolas29");
        Usuario supervisor3 = new Usuario(4, "Carlos Correa", 3104870316, "caliche_choco115@gmail.com", "Choco115");
        List<Usuario> usuariosEntrada = List.of(supervisor1, supervisor2, supervisor3);

        Usuario usuarioIngresar = new Usuario(2, "nicoserna29@gmail.com", "armandoParedes123");

        ControladorUsuarios cusrs = new ControladorUsuarios(bd_usrs);
        Mockito.when(bd_usrs.guadarUsuario(usuariosEntrada)).thenReturn(usuariosEntrada);
        Mockito.when(bd_usrs.inicioSesion(usuarioIngresar)).thenReturn(usuarioIngresar);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {cmp.inicioSession(usuariosEntrada, usuarioIngresar);});
    }

    @Test
    void llenarHueco(){
        Usuario supervisor1 = new Usuario(2, "Luz Aidy Gonzalez", 3146504422, "aidygova@hotmail.com", "150629amn");
        Usuario supervisor2 = new Usuario(3, "Nicolas Serna Gonzalez", 3218810044, "nicoserna@gmail.com", "nicolas29");
        Usuario supervisor3 = new Usuario(4, "Carlos Correa", 3104870316, "caliche_choco115@gmail.com", "Choco115");
        List<Usuario> usuariosEntrada = List.of(supervisor1, supervisor2, supervisor3);

        Usuario usuarioEliminar = new Usuario(3);
        List<Usuario> usuariosEsperados = List.of(supervisor1, supervisor3);

        ControladorUsuarios cusrs = new ControladorUsuarios(bd_usrs);
        Mockito.when(bd_usrs.guadarUsuario(usuariosEntrada)).thenReturn(usuariosEntrada);

        Mockito.when(bd_usrs.eliminarUsuario(usuarioEliminar)).thenReturn(usuariosEntrada);

        List<Usuario> usuariosResultado = cusrs.eliminarUsuario(usuariosEntrada, usuarioEliminar);
        Assertions.assertEquals(usuariosEsperados, usuariosResultado);

        Usuario supervisor2 = new Usuario(3, "Johana Gonzalez", 31277550316, "ninijogova@hotmail.com", "jase2023");
        usuariosEsperados.add(supervisor2);

        Mockito.when(bd_usrs.guardarUsuario(supervisor2)).thenReturn(supervisor2);

        usuariosResultado = cusrs.guardarUsuario(supervisor2);

        Assertions.assertEquals(usuariosEsperados, usuariosResultado);
    }
}
