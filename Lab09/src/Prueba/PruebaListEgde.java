package Prueba;
import graph.*;


public class PruebaListEgde{
    public static void main(String[] args) {
        GraphListEdge<String, Object> graph = new GraphListEdge<>();

        // Insertar vértices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        // Insertar aristas
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "E");
        graph.insertEdge("D", "E");

        // Intentar insertar arista con vértice inexistente
        graph.insertEdge("A", "F");  // Debería mostrar mensaje de error

        // Realizar BFS desde A
        System.out.println("Recorrido BFS desde A:");
        graph.bfs("A");

        // Verificar búsqueda de vértices y aristas
        System.out.println("Existe vértice 'C'? " + graph.searchVertex("C"));  // true
        System.out.println("Existe arista entre 'A' y 'C'? " + graph.searchEdge("A", "C"));  // true
        System.out.println("Existe arista entre 'B' y 'E'? " + graph.searchEdge("B", "E"));  // false
    }
}