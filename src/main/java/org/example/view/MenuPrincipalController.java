package org.example.view;

public class MenuPrincipalController {
    private final MenuPrincipal menuPrincipal;
    private final MenuCliente menuCliente;
    private final MenuSucursal menuSucursal;
    private final MenuTransportista menuTransportista;
    private final MenuProveedor menuProveedor;
    private final MenuProducto menuProducto;
    private final MenuLineaPedido menuLineaPedido;

    public MenuPrincipalController() {
        this.menuPrincipal = new MenuPrincipal();
        this.menuCliente = new MenuCliente();
        this.menuSucursal = new MenuSucursal();
        this.menuTransportista = new MenuTransportista();
        this.menuProveedor = new MenuProveedor();
        this.menuProducto = new MenuProducto();
        this.menuLineaPedido = new MenuLineaPedido();
        this.seleccionarOpciones();
    }

    private void seleccionarOpciones() {
        while (menuPrincipal.getOpcion() != 0) {
            switch (menuPrincipal.seleccionarModulo()) {
                case 1 -> gestionarClientes();
                case 2 -> gestionarProveedor();
                case 3 -> gestionarSucursales();
                case 4 -> gestionarTransportista();
                case 5 -> gestionarProductos();
                case 6 -> gestionarLineaPedido();
                default -> System.out.println("Opcion invalida.");
            }
        }
    }

    private void gestionarClientes() {
        while (menuCliente.atras() != 0) {
            switch (menuCliente.seleccionarOpcion()) {
                case 1 -> menuCliente.crearNuevoCliente();
                case 2 -> menuCliente.modificarCliente();
                case 3 -> menuCliente.eliminarCliente();
                case 4 -> menuCliente.buscarClientePorCuit();
                case 5 -> menuCliente.buscarTodosLosClientes();
                case 0 -> menuPrincipal.regresar();
                default -> menuPrincipal.invalido();
            }
        }
    }

    private void gestionarProveedor() {
        while (menuProveedor.atras() != 0) {
            switch (menuProveedor.seleccionarOpcion()) {
                case 1 -> menuProveedor.crearProveedor();
                case 2 -> menuProveedor.modificarProveedor();
                case 3 -> menuProveedor.eliminarProveedor();
                case 4 -> menuProveedor.buscarProveedorPorCuit();
                case 5 -> menuProveedor.buscarProveedores();
                case 0 -> menuPrincipal.regresar();
                default -> menuPrincipal.invalido();
            }
        }
    }

    private void gestionarSucursales() {
        while (menuSucursal.atras() != 0) {
            switch (menuSucursal.seleccionarOpcion()) {
                case 1 -> menuSucursal.agregarUnaSucursal();
                case 2 -> menuSucursal.modificarSucursal();
                case 3 -> menuSucursal.deshablitarSucursal();
                case 4 -> menuSucursal.buscarSucursalPorCodigo();
                case 5 -> menuSucursal.buscarTodasLasSucursales();
                case 0 -> menuPrincipal.regresar();
                default -> menuPrincipal.invalido();
            }
        }
    }

    private void gestionarTransportista() {
        while (menuTransportista.atras() != 0) {
            switch (menuTransportista.seleccionarOpcion()) {
                case 1 -> menuTransportista.agregarTransportista();
                case 2 -> menuTransportista.modificarTransportista();
                case 3 -> menuTransportista.eliminarTransportista();
                case 4 -> menuTransportista.buscarPorCuit();
                case 5 -> menuTransportista.buscarTransportistas();
                case 6 -> menuTransportista.buscarTransportistasPorTipo();
                case 0 -> menuPrincipal.regresar();
                default -> menuPrincipal.invalido();
            }
        }
    }

    private void gestionarProductos() {
        while (menuProducto.getOption() != 0) {
            switch (menuProducto.seleccionarOpcion()) {
                case 1 -> menuProducto.crearProducto();
                case 2 -> menuProducto.modificarProducto();
                case 3 -> menuProducto.eliminarProducto();
                case 4 -> menuProducto.buscarProductoPorId();
                case 5 -> menuProducto.buscarTodosLosProductos();
                case 0 -> menuPrincipal.regresar();
                default -> menuPrincipal.invalido();
            }
        }
    }

    private void gestionarLineaPedido() {
        while (menuLineaPedido.atras() != 0) {
            switch (menuLineaPedido.seleccionarOpcion()) {
                case 1 -> menuLineaPedido.seleccionarProductos();
                case 2 -> menuLineaPedido.modificarLineaPedido();
                case 3 -> menuLineaPedido.eliminarLineaPedido();
                case 4 -> menuLineaPedido.buscarLineasPedido();
                //case 5 -> menuLineaPedido.generarPedido();
                case 0 -> menuPrincipal.regresar();
                default -> menuPrincipal.invalido();
            }
        }
    }
}

