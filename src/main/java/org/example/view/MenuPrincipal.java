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
                3. Gestion de Sucursales.
                0. Atras""");
        option = input.nextInt();
        return option;
    }

    public String invalido(){
        return "opcion invalida";
    }

    public String regresar(){
        return "Regresando al menu...";
    }

    public int atras(){
        return option;
    }
}
