package org.example.view;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner input;
    private int option;

    public MenuPrincipal() {
        this.input = new Scanner(System.in);
        this.option = 99;
    }

    public int seleccionarModulo() {
        System.out.println("""
                
                ------ MENÚ PRINCIPAL ------
                Seleccione el módulo que desea:
                1. Gestión de clientes.
                2. Gestión de proveedores.
                3. Gestión de Sucursales.
                4. Gestión de transportistas.
                5. Gestión de pedidos.
                6. Generar informe.
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
