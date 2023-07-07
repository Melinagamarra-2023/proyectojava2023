package org.example.view;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner input = new Scanner(System.in); //Nose si corresponde private final
    int option;
    public MenuPrincipal() {
        option = 99;
    }



    public int seleccionarModulo() {
        System.out.println("""
                
                ------ MENÚ PRINCIPAL ------
                Seleccione el módulo que desea:
                1. Gestión de clientes.
                2. Gestión de proveedores.
                3. Gestion de Sucursales.
                4. Gestion de transportistas.
                5. Gestión de productos.
                6. Gestión de lineas de pedido.
                0. Salir""");
        option = input.nextInt();
        return option;
    }

    public void invalido(){
        System.out.println("opcion invalida");
    }

    public void regresar(){
        System.out.println("Regresando al menu...");
    }

    public int getOpcion(){
        return option;
    }
}
