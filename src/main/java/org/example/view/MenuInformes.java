package org.example.view;

import java.util.Scanner;

public class MenuInformes {

    private final Scanner input;

    public MenuInformes() {
        this.input = new Scanner(System.in);
    }


    public int seleccionarOpcion() {
        System.out.println("""
                                
                ------- MENÚ INFORMES -------
                1. Informes de clientes.
                2. Informes de proveedores.
                3. Informes de transportistas.
                4. Informes de pedidos.
                0. Salir.
                """);
        return input.nextInt();
    }

}