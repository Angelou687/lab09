package Prueba;
import graph.*;
import estructura.*;
import grafic.*;

public class PruebaUso {
    public static void main(String[] args) {
        // Crear grafo de almacenes (no dirigido y ponderado)
        GraphLink<String> grafo = new GraphLink<>(false);

        // Insertar almacenes
        grafo.insertVertex("Almacen Central");
        grafo.insertVertex("Almacen Norte");
        grafo.insertVertex("Almacen Sur");

        // Insertar rutas con peso (distancia o tiempo de traslado)
        grafo.insertEdgeWeight("Almacen Central", "Almacen Norte", 10);
        grafo.insertEdgeWeight("Almacen Norte", "Almacen Sur", 20);
        grafo.insertEdgeWeight("Almacen Central", "Almacen Sur", 25);

        System.out.println("Grafo de almacenes creado.");

        // Mapa de stock por almacén y producto (clave: Almacen_Producto)
        HashMap<String, Integer> stock = new HashMap<>();
        stock.put("Almacen Central_Martillo", 50);
        stock.put("Almacen Norte_Martillo", 20);
        stock.put("Almacen Sur_Martillo", 0);

        System.out.println("Stock inicial:");
        System.out.println("Almacen Central: " + stock.get("Almacen Central_Martillo") + " martillos");
        System.out.println("Almacen Sur: " + stock.get("Almacen Sur_Martillo") + " martillos");

        // Datos de transferencia
        String origen = "Almacen Central";
        String destino = "Almacen Sur";
        int cantidad = 10;

        String claveOrigen = origen + "_Martillo";
        String claveDestino = destino + "_Martillo";

        // Obtener ruta óptima como Stack
        Stack<String> ruta = grafo.Dijkstra(origen, destino);
        System.out.print("Ruta óptima: ");
        while (!ruta.isEmpty()) {
            String nodo = ruta.pop();
            System.out.print(nodo);
            if (!ruta.isEmpty()) System.out.print(" -> ");
        }
        System.out.println();

        // Verificar y transferir stock
        if (stock.get(claveOrigen) != null && stock.get(claveOrigen) >= cantidad) {
            stock.put(claveOrigen, stock.get(claveOrigen) - cantidad);
            int actualDestino = (stock.get(claveDestino) != null) ? stock.get(claveDestino) : 0;
            stock.put(claveDestino, actualDestino + cantidad);
            System.out.println("Transferencia exitosa: " + cantidad + " martillos de " + origen + " a " + destino);
        } else {
            System.out.println("No hay suficiente stock en " + origen + " para transferir.");
        }

        // Mostrar stock final
        System.out.println("Stock final:");
        System.out.println("Almacen Central: " + stock.get(claveOrigen) + " martillos");
        System.out.println("Almacen Sur: " + stock.get(claveDestino) + " martillos");
        GraphViewer.mostrar(grafo);
    }
}
