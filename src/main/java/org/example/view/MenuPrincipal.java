package org.example.view;

import java.util.Scanner;

public class MenuPrincipal {
    Scanner input = new Scanner(System.in);
    int option = 99;
    public int seleccionarModulo() {
        System.out.println("""
                Seleccione el módulo que desea:
                1. Gestión de clientes.
                2. Gestión de proveedores.
                4. Gestion de transportistas.
                5. Gestión de productos.

                0. Atras""");
        option = input.nextInt();
        return option;
    }

    public void invalido(){
        System.out.println("opcion invalida");
    }

    public void regresar(){
        System.out.println("Regresando al menu...");
    }

    public int atras(){
        return option;
    }
}
