package org.example.view;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner input;

    public MenuPrincipal() {
        this.input = new Scanner(System.in);
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
        return input.nextInt();
    }

    public String verificarExistencia(String tipo) {
        System.out.println("El " + tipo + " ingresado no existe, intente nuevamente. (0 para cancelar)");
        System.out.print("ID: ");
        String respuesta = input.next();
        if (respuesta.equals("0")) {
            System.out.println("Operación cancelada.");
        }
        return respuesta;
    }

    public String verificarAusencia(String tipo) {
        System.out.println("El " + tipo + " ingresado ya existe, intente nuevamente. (0 para cancelar)");
        System.out.print("ID: ");
        String respuesta = input.next();
        if (respuesta.equals("0")) {
            System.out.println("Operación cancelada.");
        }
        return respuesta;
    }

    public void invalido(){
        System.out.println("opcion invalida");
    }

    public void regresar(){
        System.out.println("Regresando al menu...");
    }

}
