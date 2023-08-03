package org.example.view;

import org.example.controller.*;
import org.example.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuPedido {

    private final MenuPrincipal menuPrincipal;
    private final MenuSucursal menuSucursal;
    private final MenuTransportista menuTransportista;
    private final ClienteController clienteController;
    private final ProveedorController proveedorController;
    private final PedidoController pedidoController;
    private final SucursalController sucursalController;
    private final TransportistaController transportistaController;
    private final Scanner input;
    private int numeroPedido;

    public MenuPedido() {
        this.menuPrincipal = new MenuPrincipal();
        this.clienteController = new ClienteController();
        this.proveedorController = new ProveedorController();
        this.pedidoController = new PedidoController();
        this.sucursalController = new SucursalController();
        this.menuSucursal = new MenuSucursal();
        this.menuTransportista = new MenuTransportista();
        this.transportistaController = new TransportistaController();
        this.input = new Scanner(System.in);
        this.numeroPedido = 0;
    }

    public int seleccionarOpcion() {
        System.out.println("""
                                
                ----- MENÚ PEDIDOS -----
                Seleccione la opción:
                1. Gestionar linea pedido.
                2. Generar un pedido
                3. Cancelar un pedido.
                4. Buscar pedido por ID.
                5. Obtener lista de todos los pedidos.
                6. Cambiar estado de un pedido.
                7. Informar la ubicación de un pedido.
                8. Ver estados de un pedido.
                9. Calificar un transportista.
                0. Salir.
                """);
        return input.nextInt();
    }

    public void generarPedido() {
        Pedido nuevoPedido = new Pedido("0", null, null, new ArrayList<>(), new ArrayList<>(), null, null);
        System.out.println("Ingrese el cuit del cliente:");
        String cuit = input.next();
        while (clienteController.findOne(cuit) == null && !(cuit.equals("0"))) {
            cuit = menuPrincipal.verificarExistencia("cuit");
        }
        if (cuit.equals("0")) {
            return;
        }
        nuevoPedido.setCliente(clienteController.findOne(cuit));
        this.armarCarrito(nuevoPedido);
    }

    private void armarCarrito(Pedido nuevoPedido) {
        int option = 1;
        while (option != 0) {
            for (LineaPedido lineaPedido : pedidoController.findAllLP()) {
                System.out.println("ID: " + lineaPedido.getCodigo() +
                        ", " + lineaPedido.getCantidad() + " " +
                        lineaPedido.getProducto().getNombre() + ".");
            }
            System.out.println("Ingrese el id de la linea de pedido a añadir:");
            String id = input.next();
            while (pedidoController.findOneLP(id) == null && !(id.equals("0"))) {
                id = menuPrincipal.verificarExistencia("id");
            }
            if (id.equals("0")) {
                return;
            }
            pedidoController.agregarLineaPedido(nuevoPedido, id);
            System.out.println("Artículo " + pedidoController.findOneLP(id).getProducto().getNombre() + " agregado correctamente al pedido.");
            System.out.println("¿Desea añadir una línea de pedido nueva? (s/n)");
            String resp = input.next();
            if (resp.contains("n")) {
                option = 0;
            }
        }
        this.seleccionarSucursales(nuevoPedido);
    }

    private void seleccionarSucursales(Pedido nuevoPedido) {
        System.out.println("Seleccione las sucursales de origen y destino para el pedido.");
        menuSucursal.buscarTodasLasSucursales();
        System.out.print("Ingrese el ID de la sucursal de origen: ");
        String idOrigen = input.next();
        while (sucursalController.findOne(idOrigen) == null && !(idOrigen.equals("0"))) {
            idOrigen = menuPrincipal.verificarExistencia("id");
        }
        if (idOrigen.equals("0")) {
            return;
        }
        nuevoPedido.setEncargado(pedidoController.setEmpleado(idOrigen));
        pedidoController.setSucursalOrigen(nuevoPedido, idOrigen);
        System.out.print("Ingrese el ID de la sucursal de destino: ");
        String idDestino = input.next();
        while (sucursalController.findOne(idDestino) == null && !(idDestino.equals("0"))) {
            idDestino = menuPrincipal.verificarExistencia("id");
        }
        if (idDestino.equals("0")) {
            return;
        }
        pedidoController.setSucursalDestino(nuevoPedido, idDestino);
        this.seleccionarTransportista(nuevoPedido, idOrigen, idDestino);
    }

    private void seleccionarTransportista(Pedido nuevoPedido, String idOrigen, String idDestino) {
        System.out.println("Seleccione el transporte que desea para su envío:");
        menuTransportista.buscarTransportistasPorTipo();
        System.out.println("Ingrese el ID del transportista elegido:");
        String idTransportista = input.next();
        while (transportistaController.findOne(idTransportista) == null && !(idTransportista.equals("0"))) {
            idTransportista = menuPrincipal.verificarExistencia("cuit");
        }
        if (idTransportista.equals("0")) {
            return;
        }
        numeroPedido++;
        nuevoPedido.setPedidoId(String.valueOf(numeroPedido));
        pedidoController.create(nuevoPedido);
        pedidoController.createRemito(nuevoPedido, sucursalController.findOne(idOrigen), pedidoController.setEmpleado(idOrigen), sucursalController.findOne(idDestino), pedidoController.setEmpleado(idDestino), transportistaController.findOne(idTransportista));
        System.out.println("Pedido creado con éxito con el número: " + numeroPedido + ".");
    }

    public void mostrarPedidoPorId() {
        System.out.println("----------------------");
        System.out.println("Ingrese el numero del pedido que desea buscar: ");
        String id = input.next();
        while (pedidoController.findOne(id) == null && !(id.equals("0"))) {
            id = menuPrincipal.verificarExistencia("id");
        }
        if (id.equals("0")) {
            return;
        }
        Pedido pedidoBuscado = pedidoController.findOne(id);
        int ultimo = pedidoBuscado.getSeguimientoPedido().size() - 1;
        String ultimoEstado = pedidoBuscado.getSeguimientoPedido().get(ultimo).getEstado().getNombre();
        System.out.println("El pedido asociado al ID ingresado es del " +
                "Cliente: " + pedidoBuscado.getCliente().getNombre() +
                ", Sucursal Origen: " + pedidoBuscado.getSucursalOrigen().getSucId() +
                " (" + pedidoBuscado.getSucursalOrigen().getContinente() + ")" +
                ", Sucursal Destino: " + pedidoBuscado.getSucursalDestino().getSucId() +
                " (" + pedidoBuscado.getSucursalDestino().getContinente() + ")" +
                ", estado: " + ultimoEstado + ";");
    }

    public void mostrarTodosLosPedidos() {
        System.out.println("-----------------------------");
        System.out.println("Los pedidos registrados son: ");
        for (Pedido pe : pedidoController.findAll()) {
            int ultimo = pe.getSeguimientoPedido().size() - 1;
            String ultimoEstado = pe.getSeguimientoPedido().get(ultimo).getEstado().getNombre();
            System.out.println("Pedido N°" + pe.getPedidoId() +
                    " de Cliente: " + pe.getCliente().getApellido() + " " + pe.getCliente().getCuit() +
                    ", Sucursal Origen: " + pe.getSucursalOrigen().getSucId() +
                    " (" + pe.getSucursalOrigen().getContinente() + ")" +
                    ", Sucursal Destino: " + pe.getSucursalDestino().getSucId() +
                    " (" + pe.getSucursalDestino().getContinente() + ")" +
                    ", estado: " + ultimoEstado + ";");
        }

    }

    public void cancelarPedido() {
        System.out.println("--------------------------");
        System.out.println("Ingrese el número del pedido que desea borrar");
        String id = input.next();
        while (pedidoController.findOne(id) == null && !(id.equals("0"))) {
            id = menuPrincipal.verificarExistencia("id");
        }
        if (id.equals("0")) {
            return;
        }
        Pedido pedidoEliminado = pedidoController.findOne(id);
        if (this.verificarDevuelto(pedidoEliminado)) {
            return;
        }
        int ultimoEstado = pedidoEliminado.getSeguimientoPedido().size() - 1;
        Sucursal ultimaSucursal = pedidoEliminado.getSeguimientoPedido().get(ultimoEstado).getEstado().getSucursal();
        if (ultimaSucursal.getSucId().equals(pedidoEliminado.getSucursalDestino().getSucId())) {
            Sucursal sucursalOrigen = pedidoEliminado.getSucursalOrigen();
            Transportista transportista = pedidoController.verRemitoDePedido(id).getTransportista();
            pedidoController.createRemito(pedidoEliminado, ultimaSucursal, pedidoController.setEmpleado(ultimaSucursal.getSucId()), sucursalOrigen, pedidoController.setEmpleado(sucursalOrigen.getSucId()), transportista);
        }
        pedidoController.delete(id);
        System.out.println("El pedido nro°" + pedidoEliminado.getPedidoId() +
                " del cliente: " + pedidoEliminado.getCliente().getNombre() +
                " ha sido cancelado con éxito");
    }

    public void siguienteEstado() {
        input.nextLine();
        System.out.print("Ingrese el número del pedido: ");
        String id = input.nextLine();
        while (pedidoController.findOne(id) == null && !(id.equals("0"))) {
            id = menuPrincipal.verificarExistencia("id");
        }
        if (id.equals("0")) {
            return;
        }
        if (this.verificarDevuelto(pedidoController.findOne(id)) || this.verificarEntregado(pedidoController.findOne(id))){
            return;
        }
        System.out.println("Desea cambiarlo de estado?");
        String resp = input.nextLine();
        if (resp.contains("s")) {
            pedidoController.siguienteEstado(pedidoController.findOne(id));
            if (this.verificarEntregado(pedidoController.findOne(id))) {
                pedidoController.entregar(pedidoController.findOne(id));
            }
        }
    }

    public void verEstados() {
        input.nextLine();
        System.out.print("Ingrese el número del pedido: ");
        String id = input.nextLine();
        while (pedidoController.findOne(id) == null && !(id.equals("0"))) {
            id = menuPrincipal.verificarExistencia("id");
        }
        if (id.equals("0")) {
            return;
        }
        int estados = pedidoController.findOne(id).getSeguimientoPedido().size();
        for (int i = 0; i < estados; i++) {
            SeguimientoPedido seguimiento = pedidoController.findOne(id).getSeguimientoPedido().get(i);
            System.out.print((i + 1) + "° estado: " +
                    seguimiento.getEstado().getNombre() + " " +
                    seguimiento.getFechaYhora().getDayOfMonth() + "/" +
                    seguimiento.getFechaYhora().getMonthValue() + " - " +
                    seguimiento.getFechaYhora().getHour() + ":" +
                    seguimiento.getFechaYhora().getMinute());
            if (i < 5) {
                System.out.println(", En sucursal Origen.");
            } else if (seguimiento.getEstado().getNombre().equals("En transito")) {
                System.out.println(" (latitud: " + seguimiento.getLatitud() +
                        ", longitud: " + seguimiento.getLongitud() + ").");
            } else {
                System.out.println(", En sucursal de Destino.");
            }
        }
    }

    public void informeClientes() {
        System.out.println("La aplicación posee " + clienteController.findAll().size() + " clientes registrados.");
        if (clienteController.findAll().isEmpty()) {
            this.finInforme();
            return;
        }
        System.out.println("¿Desea obtener un informe detallado de uno de ellos? (s/n)");
        String respuesta = input.next();
        if (respuesta.contains("s")) {
            System.out.print("Ingrese el cuit del Cliente: ");
            String cuit = input.next();
            while (clienteController.findOne(cuit) == null && !(cuit.equals("0"))) {
                cuit = menuPrincipal.verificarExistencia("cuit");
            }
            if (cuit.equals("0")) {
                return;
            }
            Cliente cliente = clienteController.findOne(cuit);
            String estado = (cliente.getHabilitado() ? "Habilitado." : "Inhabilitado.");
            System.out.println("Cliente: " + cliente.getApellido() + " " + cliente.getNombre() +
                    ", teléfono: " + cliente.getTelefono() + ", estado: " + estado);
            this.obtenerPromediosDeCliente(cliente.getCuit());
        }
    }

    private void obtenerPromediosDeCliente(String cuit) {
        List<Pedido> pedidosDeCliente = pedidoController.buscarPedidosPorCliente(clienteController.findOne(cuit));
        System.out.print("Este cliente ha realizado " + pedidosDeCliente.size() + " pedidos,");
        if (pedidosDeCliente.isEmpty()) {
            this.finInforme();
            return;
        }
        List<Producto> productosDeCliente = new ArrayList<>();
        for (Pedido pedido : pedidosDeCliente) {
            for (int i = 0; i < pedido.getDetalle().size(); i++) {
                productosDeCliente.add(pedido.getDetalle().get(i).getProducto());
            }
        }
        List<Categoria> categorias = proveedorController.verCategorias();
        List<Double> promedios = new ArrayList<>();
        for (Categoria categoria : categorias) {
            double cantidad = 0;
            for (Producto producto : productosDeCliente) {
                if (producto.getCategoria().getDescripcion().equals(categoria.getDescripcion())) {
                    cantidad++;
                }
            }
            promedios.add(cantidad);
        }
        System.out.println(" con las siguientes preferencias: ");
        for (int i = 0; i < promedios.size(); i++) {
            double promedio = (promedios.get(i) * 100) / productosDeCliente.size();
            System.out.println(categorias.get(i).getDescripcion() + ": " + promedio + "%.");
        }
    }

    public void informeProveedores() {
        System.out.println("La aplicación posee " + proveedorController.findAll().size() + " proveedores registrados.");
        if (proveedorController.findAll().isEmpty()) {
            this.finInforme();
            return;
        }
        System.out.println("¿Desea obtener un informe detallado de uno de ellos? (s/n)");
        String respuesta = input.next();
        if (respuesta.contains("s")) {
            System.out.print("Ingrese el cuit del proveedor: ");
            String cuit = input.nextLine();
            while (proveedorController.findOneProv(cuit) == null && !(cuit.equals("0"))) {
                cuit = menuPrincipal.verificarExistencia("cuit");
            }
            if (cuit.equals("0")) {
                return;
            }
            Proveedor proveedor = proveedorController.findOneProv(cuit);
            String estado = (proveedor.getHabilitado() ? "Habilitado." : "Inhabilitado.");
            System.out.println("Cliente: " + proveedor.getNombre() +
                    ", teléfono: " + proveedor.getTelefono() + ", estado: " + estado);
            this.obtenerPromediosDeProveedores(cuit);
        }
    }

    private void obtenerPromediosDeProveedores(String cuit) {
        List<LineaPedido> pedidosDeProveedor = pedidoController.buscarPorProveedor(cuit);
        System.out.print("Este proveedor ha recibido " + pedidosDeProveedor.size() + " pedidos");
        if (pedidosDeProveedor.isEmpty()) {
            this.finInforme();
            return;
        }
        int promedio = 0;
        for (LineaPedido lineaPedido : pedidosDeProveedor) {
            if (lineaPedido.getCalificaProveedor() != 0) {
                promedio = promedio + lineaPedido.getCalificaProveedor();
            }
        }
        if (promedio == 0) {
            System.out.println(" sin recibir calificaciones.");
        } else {
            System.out.println(" con el siguiente promedio de calificación: " + (promedio / pedidosDeProveedor.size()) + ".");
        }
    }

    public void informarUbicacion() {
        input.nextLine();
        System.out.print("Ingrese el número del pedido: ");
        String id = input.nextLine();
        while (pedidoController.findOne(id) == null && !(id.equals("0"))) {
            id = menuPrincipal.verificarExistencia("id");
        }
        if (id.equals("0")) {
            return;
        }
        Pedido pedido = pedidoController.findOne(id);
        if (verificarDevuelto(pedido) || verificarEntregado(pedido) || verificarTransito(pedido)) {
            return;
        }
        System.out.print("Ingrese su latitud: ");
        Double latitud = input.nextDouble();
        System.out.print("Ingrese su longitud: ");
        Double longitud = input.nextDouble();
        pedidoController.nuevoTransito(pedidoController.findOne(id), latitud, longitud);
    }

    public void calificarTransportista() {
        System.out.print("Ingrese el id del pedido: ");
        String id = input.next();
        while (pedidoController.findOne(id) == null && !(id.equals("0"))) {
            id = menuPrincipal.verificarExistencia("id");
        }
        if (id.equals("0")) {
            return;
        }
        if (!verificarEntregado(pedidoController.findOne(id))){
            System.out.println("Este pedido aún no ha sido entregado.");
            return;
        }
        System.out.println("Ingrese una calificación, del 1 al 5 para este transportista: ");
        int star = input.nextInt();
        while (star < 0 || 5 < star) {
            System.out.print("Ingrese una calificación válida (0 para cancelar): ");
            star = input.nextInt();
        }
        if (star == 0) {
            return;
        }
        pedidoController.calificarTransportista(id, star);
    }

    public void informeTransportistas() {
        System.out.println("La aplicación posee " + transportistaController.findAll().size() + " transportistas registrados.");
        if (transportistaController.findAll().isEmpty()) {
            this.finInforme();
            return;
        }
        System.out.println("¿Desea obtener un informe detallado de uno de ellos? (s/n)");
        String respuesta = input.next();
        if (respuesta.contains("s")) {
            System.out.print("Ingrese el cuit del transportista: ");
            String cuit = input.nextLine();
            while (transportistaController.findOne(cuit) == null && !(cuit.equals("0"))) {
                cuit = menuPrincipal.verificarExistencia("cuit");
            }
            if (cuit.equals("0")) {
                return;
            }
            Transportista transportista = transportistaController.findOne(cuit);
            String estado = (transportista.getHabilitado() ? "Habilitado." : "Inhabilitado.");
            System.out.println("Transportista: " + transportista.getNombre() +
                    ", teléfono: " + transportista.getTelefono() + ", estado: " + estado);
            this.obtenerPromediosDeTransportistas(cuit);
        }
    }

    private void obtenerPromediosDeTransportistas(String cuit) {
        List<Remito> pedidosDeTransportistas = pedidoController.verRemitosPorTransportista(cuit);
        System.out.print("Este transportista ha entregado " + pedidosDeTransportistas.size() + " pedidos,");
        if (pedidosDeTransportistas.isEmpty()) {
            this.finInforme();
            return;
        }
        int promedio = 0;
        for (Remito remito : pedidosDeTransportistas) {
            if (remito.getCalificaTransportista() != 0) {
                promedio = promedio + remito.getCalificaTransportista();
            }
        }
        if (promedio == 0) {
            System.out.println(" sin recibir calificaciones.");
        } else {
            System.out.println(" con el siguiente promedio de calificación: " + (promedio / pedidosDeTransportistas.size()));
        }
        this.finInforme();
    }

    public void informePedidos() {
        int sectorDevuelto = pedidoController.verSectores().size() - 1;
        String estadoDevuelto = pedidoController.verSectores().get(sectorDevuelto).getNombre();
        int devueltos = 0;
        int sectorEntregado = pedidoController.verSectores().size() - 2;
        String estadoEntregado = pedidoController.verSectores().get(sectorEntregado).getNombre();
        int entregados = 0;
        for (Pedido pedido : pedidoController.findAll()) {
            int ultimoEstado = pedido.getSeguimientoPedido().size() - 1;
            if (pedido.getSeguimientoPedido().get(ultimoEstado).getEstado().getNombre().equals(estadoDevuelto)) {
                devueltos++;
            } else if (pedido.getSeguimientoPedido().get(ultimoEstado).getEstado().getNombre().equals(estadoEntregado)) {
                entregados++;
            }
        }
        System.out.print("Se gestionaron " + pedidoController.findAll().size() +
                " pedidos a través de la aplicación de los cuales \n" +
                devueltos + " han sido devueltos.\n" +
                entregados + " han sido correctamente entregados.\n" +
                (pedidoController.findAll().size() - devueltos - entregados) + " continúan en proceso.\n");
        if (pedidoController.findAll().isEmpty()) {
            System.out.println("No existe ningun pedido, volvera al Menu de Informes ");
            return;
        }

        System.out.println("¿Desea obtener un informe detallado de uno de ellos? (s/n)");
        String respuesta = input.next();
        if (respuesta.contains("s")) {
            System.out.print("Ingrese el id del pedido: ");
            String id = input.next();
            while (pedidoController.findOne(id) == null && !(id.equals("0"))) {
                id = menuPrincipal.verificarExistencia("id");
            }
            if (id.equals("0")) {
                return;
            }
            this.obtenerDetallesDePedido(id);
        }
    }

    private void obtenerDetallesDePedido(String id) {
        Pedido pedido = pedidoController.findOne(id);
        Remito remitoDePedido = pedidoController.verRemitoDePedido(id);
        System.out.println("El pedido asociado al ID ingresado es del " +
                "Cliente: " + pedido.getCliente().getNombre() +
                ", Sucursal Origen: " + pedido.getSucursalOrigen().getSucId() +
                " (" + pedido.getSucursalOrigen().getContinente() + ")" +
                ", Sucursal Destino: " + pedido.getSucursalDestino().getSucId() +
                " (" + pedido.getSucursalDestino().getContinente() + ").");
        System.out.println("Fue preparado por el empleado " + pedido.getEncargado().getNombre() +
                " y despachado el " + remitoDePedido.getEmision() +
                " a cargo del transportista " + remitoDePedido.getTransportista().getNombre() +
                " y los empleados " + remitoDePedido.getEmpleadoEmisor().getNombre() + "(emisor), " +
                remitoDePedido.getEmpleadoReceptor().getNombre() + "(receptor).");
        System.out.println("Este pedido contenía:");
        List<LineaPedido> lineaPedidosDePedido = pedido.getDetalle();
        for (LineaPedido lineaPedido : lineaPedidosDePedido) {
            System.out.println(lineaPedido.getCantidad() + " " + lineaPedido.getProducto().getNombre());
        }
        this.finInforme();
    }

    private void finInforme() {
        System.out.println("Fin del informe.");
    }

    private Boolean verificarDevuelto(Pedido pedido) {
        Sector devuelto = pedidoController.verSectores().get(8);
        int ultimoEstado = pedido.getSeguimientoPedido().size() - 1;
        Sector ultimoSector = pedido.getSeguimientoPedido().get(ultimoEstado).getEstado();
        if (ultimoSector.getNombre().equals(devuelto.getNombre())) {
            System.out.println("Este pedido está cancelado.");
            return true;
        }
        return false;
    }

    private Boolean verificarEntregado(Pedido pedido) {
        Sector entregado = pedidoController.verSectores().get(7);
        int ultimoEstado = pedido.getSeguimientoPedido().size() - 1;
        Sector ultimoSector = pedido.getSeguimientoPedido().get(ultimoEstado).getEstado();
        if (ultimoSector.getNombre().equals(entregado.getNombre())) {
            System.out.println("Este pedido ya ha sido entregado.");
            return true;
        }
        return false;
    }

    private Boolean verificarTransito(Pedido pedido) {
        Sector transito = pedidoController.verSectores().get(5);
        int ultimoEstado = pedido.getSeguimientoPedido().size() - 1;
        Sector ultimoSector = pedido.getSeguimientoPedido().get(ultimoEstado).getEstado();
        if (!ultimoSector.getNombre().equals(transito.getNombre())) {
            System.out.println("Este pedido no se encuentra en tránsito.");
            return true;
        }
        return false;
    }

}
