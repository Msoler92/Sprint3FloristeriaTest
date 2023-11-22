package org.example.persistence;

import java.util.HashMap;

public class BaseDeDatos {
    HashMap<Integer, Producto> stock;
    HashMap<Integer, Ticket> tickets;

    public HashMap<Integer, Producto> getStock() {
        return stock;
    }
    public HashMap<Integer, Ticket> getTickets() {
        return tickets;
    }
    public void agregarProducto(Producto producto) {
        stock.compute(producto.getProductoId(), (id, existingProducto) -> {
            if (existingProducto != null) {
                producto.setCantidad(producto.getCantidad() + existingProducto.getCantidad());
                return producto;
            } else {
                return producto;
            }
        });
    }
    public void agregarTicket(Ticket ticket) {
        tickets.put(ticket.getId(), ticket);
    }
    public Producto leerProducto(int id) {
        return stock.get(id);
    }
    public Ticket leerTicket(int id) {
        return tickets.get(id);
    }
    public void eliminarProducto(int id) {
        stock.remove(id);
    }
    public void eliminarTicket(int id) {
        tickets.remove(id);
    }

    //TODO Double?
    public float getValorTotalStock() {
        return stock.values().stream().mapToInt(producto -> producto.getPrecio() * producto.getCantidad()).sum();
    }
    public double getValorTotalTickets() {
        return tickets.values().stream().mapToDouble(Ticket::getTicketTotal).sum();

    }

    /*public boolean agregarProducto(Producto producto) {
        boolean isNew = true;
        Optional<Map.Entry<Integer, Producto>> productoEntry = stock.entrySet().stream().filter(entry -> producto.equals(entry.getValue())).findAny();
        if (productoEntry.isPresent()) {
            isNew = false;
            producto.setCantidad(producto.getCantidad() + stock.get(productoEntry.get().getKey()).getCantidad());
        }
        else {
            stock.put(producto.getId(), producto);
        }
        return isNew;
    }*/
}
