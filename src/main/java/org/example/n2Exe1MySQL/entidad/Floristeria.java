package org.example.n2Exe1MySQL.entidad;


import org.example.n2Exe1MySQL.excepcion.CantidadExcedida;
import org.example.n2Exe1MySQL.persistencia.InterfaceBaseDeDatos;
import org.example.n2Exe1MySQL.persistencia.MySQLDB;

import java.util.HashMap;

public class Floristeria {
	private static Floristeria instancia = null;
	private String nombre;
	private InterfaceBaseDeDatos baseDeDatos;

	private Floristeria() {
		this.baseDeDatos = MySQLDB.instanciar();
	}

	public static Floristeria getInstancia() {

		if (instancia == null) {

			return new Floristeria();
		}
		return instancia;
	}

	// Getters y Setters.

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public InterfaceBaseDeDatos getBaseDeDatos() {
		return baseDeDatos;
	}

	public void setBaseDeDatos(InterfaceBaseDeDatos baseDeDatos) {
		this.baseDeDatos = baseDeDatos;
	}

	// Métodos propios.

	public void agregarProducto(Producto producto) {
		baseDeDatos.agregarProducto(producto);
	}

	public void agregarCantidadProducto(int id, int nuevaCantidad){
		baseDeDatos.setCantidadProducto(id, nuevaCantidad);
	}
	public void agregarTicket(Ticket ticket) {
		baseDeDatos.agregarTicket(ticket);
	}

	public Producto consultarProducto(int productoId) {
		return baseDeDatos.leerProducto(productoId);
	}
	
	public int consultarSiguienteProductoID() {
		return baseDeDatos.getNextProductoId();
	}
	public int consultarSiguienteTicketID() {
		return baseDeDatos.getNextTicketId();
	}

	public String eliminarProducto(int productoID, int cantidad) throws CantidadExcedida {
		String response;
		if (existeProducto(productoID, 0)){
		Producto productoEliminado = baseDeDatos.eliminarProducto(productoID, cantidad);
			response = productoEliminado + " ha sido eliminado.";
		} else {
			response = "El producto no se ha encontrado.";
		}
		return response;
	}
	public HashMap<Integer, Producto> consultarListaProductosPorTipo(String tipo){
		return baseDeDatos.listarProductosFiltrando(tipo);
	}
	public HashMap<Integer, Ticket> consultarListaTickets() {
		return baseDeDatos.getTickets();
	}
	public float consultarValorTotalInventario() {
		return baseDeDatos.getValorTotalStock();
	}
	public float consultarValorTotalVentas() {
		return baseDeDatos.getValorTotalTickets();
	}
	public boolean existeProducto(int productoID, int cantidadMinima) {
		Boolean returnValue = false;
		Producto producto = baseDeDatos.leerProducto(productoID);
		if (producto != null) {
			returnValue = producto.getProductoCantidad() > cantidadMinima;
		}
		return returnValue;
	}

}
