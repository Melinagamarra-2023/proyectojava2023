package org.example.view;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner input; //Nose si corresponde private final
    private int option;

    public MenuPrincipal() {
        this.option = 99;
        this.input = new Scanner(System.in);
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

    public int getOption(){
        return option;
    }
}
