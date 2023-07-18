package org.example.view;


public class MenuPrincipalController {

    private final MenuPrincipal menuPrincipal;
    private final MenuCliente menuCliente;
    private final MenuSucursal menuSucursal;
    private final MenuTransportista menuTransportista;
    private final MenuProveedor menuProveedor;
    private final MenuProducto menuProducto;
    private final MenuLineaPedido menuLineaPedido;
    private final MenuPedido menuPedido;
    private int option;

    public MenuPrincipalController() {
        this.menuPrincipal = new MenuPrincipal();
        this.menuCliente = new MenuCliente();
        this.menuSucursal = new MenuSucursal();
        this.menuTransportista = new MenuTransportista();
        this.menuProveedor = new MenuProveedor();
        this.menuProducto = new MenuProducto();
        this.menuLineaPedido = new MenuLineaPedido();
        this.menuPedido = new MenuPedido();
        this.option = 99;
        this.seleccionarOpcion();
    }

    private void seleccionarOpcion() {
        while (option != 0) {
            switch (menuPrincipal.seleccionarModulo()) {
                case 1 -> gestionarClientes();
                case 2 -> gestionarProveedor();
                case 3 -> gestionarSucursales();
                case 4 -> gestionarTransportista();
                case 5 -> gestionarPedidos();
                case 0 -> menuPrincipal.regresar();
                default -> menuPrincipal.invalido();
            }
            option = menuPrincipal.getOption();
        }
    }

    private void gestionarClientes() {
        while (option != 0) {
            switch (menuCliente.seleccionarOpcion()) {
                case 1 -> menuCliente.crearNuevoCliente();
                case 2 -> menuCliente.modificarCliente();
                case 3 -> menuCliente.eliminarCliente();
                case 4 -> menuCliente.buscarClientePorCuit();
                case 5 -> menuCliente.buscarTodosLosClientes();
                case 0 -> menuPrincipal.regresar();
                default -> menuPrincipal.invalido();
            }
            option = menuCliente.getOption();
        }
    }

    private void gestionarProveedor() {
        while (option != 0) {
            switch (menuProveedor.seleccionarOpcion()) {
                case 1 -> menuProveedor.crearProveedor();
                case 2 -> menuProveedor.modificarProveedor();
                case 3 -> gestionarProductos();
                case 4 -> menuProveedor.eliminarProveerdor();
                case 5 -> menuProveedor.buscarProveedorPorCuit();
                case 6 -> menuProveedor.buscarProveedores();
                case 0 -> menuPrincipal.regresar();
                default -> menuPrincipal.invalido();
            }
            option = menuProveedor.getOption();
        }
    }

    private void gestionarProductos() {
        while (option != 0) {
            switch (menuProducto.seleccionarOpcion()) {
                case 1 -> menuProducto.crearProducto();
                case 2 -> menuProducto.modificarProducto();
                case 3 -> menuProducto.eliminarProducto();
                case 4 -> menuProducto.buscarProductoPorId();
                case 5 -> menuProducto.buscarPorCategoria();
                case 6 -> menuProducto.buscarPorNombre();
                case 7 -> menuProducto.buscarTodosLosProductos();
                case 0 -> menuPrincipal.regresar();
                default -> menuPrincipal.invalido();
            }
            option = menuProducto.getOption();
        }
    }

    private void gestionarSucursales() {
        while (option != 0) {
            switch (menuSucursal.seleccionarOpcion()) {
                case 1 -> menuSucursal.agregarUnaSucursal();
                case 2 -> menuSucursal.modificarSucursal();
                case 3 -> menuSucursal.deshablitarSucursal();
                case 4 -> menuSucursal.buscarSucursalPorCodigo();
                case 5 -> menuSucursal.buscarTodasLasSucursales();
                case 0 -> menuPrincipal.regresar();
                default -> menuPrincipal.invalido();
            }
            option = menuSucursal.getOption();
        }
    }

    private void gestionarTransportista() {
        while (option != 0) {
            switch (menuTransportista.seleccionarOpcion()) {
                case 1 -> menuTransportista.agregarTransportista();
                case 2 -> menuTransportista.modificarTransportista();
                case 3 -> menuTransportista.eliminarTransportista();
                case 4 -> menuTransportista.buscarPorCuit();
                case 5 -> menuTransportista.buscarTransportistas();
                case 6 -> menuTransportista.buscarTransportistasPorTipo();
                case 7 -> menuTransportista.informarUbicacion();
                case 0 -> menuPrincipal.regresar();
                default -> menuPrincipal.invalido();
            }
            option = menuTransportista.getOption();
        }
    }

    private void gestionarPedidos() {
        while (option != 0) {
            switch (menuPedido.seleccionarOpcion()) {
                case 1 -> gestionarLineaPedido();
                case 2 -> menuPedido.generarPedido();
                case 3 -> menuPedido.cancelarPedido();
                case 4 -> menuPedido.mostrarPedidoPorId();
                case 5 -> menuPedido.mostrarTodosLosPedidos();
                case 6 -> menuPedido.siguienteEstado();
                case 7 -> menuPedido.verEstados();
                case 0 -> menuPrincipal.regresar();
                default -> menuPrincipal.invalido();
            }
            option = menuPedido.getOption();
        }
    }

    private void gestionarLineaPedido() {
        while (option != 0) {
            switch (menuLineaPedido.seleccionarOpcion()) {
                case 1 -> menuLineaPedido.seleccionarProductos();
                case 2 -> menuLineaPedido.modificarLineaPedido();
                case 3 -> menuLineaPedido.eliminarLineaPedido();
                case 4 -> menuLineaPedido.buscarLineasPedido();
                case 6 -> menuLineaPedido.calificarProveedor();
                case 7 -> menuPedido.mostrarTodosLosPedidos();
                case 0 -> menuPrincipal.regresar();
                default -> menuPrincipal.invalido();
            }
            option = menuLineaPedido.getOption();
        }
    }

}


