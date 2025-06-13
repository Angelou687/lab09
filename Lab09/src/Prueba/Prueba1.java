package Prueba;

import graph.*;
import grafic.*;
import estructura.*;
import java.util.Scanner;

public class Prueba1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("¿Quieres grafo dirigido? (s/n): ");
        boolean dirigido = sc.nextLine().trim().equalsIgnoreCase("s");

        System.out.println("¿Quieres grafo ponderado? (s/n): ");
        boolean ponderado = sc.nextLine().trim().equalsIgnoreCase("s");

        GraphLink<String> grafo = new GraphLink<>(dirigido);

        while (true) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Insertar vértice");
            System.out.println("2. Insertar arista");
            System.out.println("3. Mostrar grafo");
            System.out.println("4. Eliminar vértice");
            System.out.println("5. Eliminar arista");
            System.out.println("6. Recorrido DFS");
            System.out.println("7. Verificar conexidad");
            System.out.println("8. Camino BFS");
            System.out.println("9. Camino más corto (Dijkstra)");
            System.out.println("10. Ruta mínima (shortPath)");
            System.out.println("11. Grado de un nodo");
            System.out.println("12. Verificar si es camino");
            System.out.println("13. Verificar si es ciclo");
            System.out.println("14. Verificar si es completo");
            System.out.println("15. Verificar si es rueda");
            System.out.println("16. Representación formal");
            System.out.println("17. Lista de adyacencia");
            System.out.println("18. Matriz de adyacencia (string)");
            System.out.println("0. Salir");

            System.out.print("Elige opción: ");
            int opcion;
            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Entrada inválida.");
                continue;
            }

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre vértice: ");
                    String v = sc.nextLine();
                    grafo.insertVertex(v);
                    System.out.println("Vértice insertado.");
                }
                case 2 -> {
                    System.out.print("Origen: ");
                    String v1 = sc.nextLine();
                    System.out.print("Destino: ");
                    String v2 = sc.nextLine();
                    if (ponderado) {
                        System.out.print("Peso (entero): ");
                        int peso = Integer.parseInt(sc.nextLine());
                        grafo.insertEdgeWeight(v1, v2, peso);
                    } else {
                        grafo.insertEdge(v1, v2);
                    }
                    System.out.println("Arista insertada.");
                }
                case 3 -> {
                	System.out.println(grafo);
                	GraphViewer.mostrar(grafo);
                }
                
                case 4 -> {
                    System.out.print("Vértice a eliminar: ");
                    String v = sc.nextLine();
                    grafo.removeVertex(v);
                    System.out.println("Vértice eliminado.");
                }
                case 5 -> {
                    System.out.print("Origen arista a eliminar: ");
                    String v1 = sc.nextLine();
                    System.out.print("Destino arista a eliminar: ");
                    String v2 = sc.nextLine();
                    grafo.removeEdge(v1, v2);
                    System.out.println("Arista eliminada.");
                }
                case 6 -> {
                    System.out.print("Vértice de inicio DFS: ");
                    String v = sc.nextLine();
                    grafo.dfs(v);
                }
                case 7 -> {
                    boolean conexo = grafo.isConexo();
                    System.out.println("¿El grafo es conexo? " + (conexo ? "Sí" : "No"));
                }
                case 8 -> {
                    System.out.print("Origen camino BFS: ");
                    String v1 = sc.nextLine();
                    System.out.print("Destino camino BFS: ");
                    String v2 = sc.nextLine();
                    ArrayList<String> camino = grafo.bfsPath(v1, v2);
                    if (camino == null) System.out.println("No hay camino.");
                    else {
                        System.out.print("Camino BFS: ");
                        for (int i = 0; i < camino.size(); i++) {
                            System.out.print(camino.get(i));
                            if (i < camino.size() - 1) System.out.print(" -> ");
                        }
                        System.out.println();
                    }
                }
                case 9 -> {
                    System.out.print("Origen: ");
                    String origen = sc.nextLine();
                    System.out.print("Destino: ");
                    String destino = sc.nextLine();
                    Stack<String> dijkstra = grafo.Dijkstra(origen, destino);
                    if (dijkstra == null || dijkstra.isEmpty()) {
                        System.out.println("No hay camino con Dijkstra.");
                    } else {
                        System.out.print("Camino Dijkstra: ");
                        while (!dijkstra.isEmpty()) {
                            System.out.print(dijkstra.pop());
                            if (!dijkstra.isEmpty()) System.out.print(" -> ");
                        }
                        System.out.println();
                    }
                }
                case 10 -> {
                    System.out.print("Origen: ");
                    String origen = sc.nextLine();
                    System.out.print("Destino: ");
                    String destino = sc.nextLine();
                    ArrayList<Vertex<String>> ruta = grafo.shortPath(origen, destino);
                    if (ruta == null) {
                        System.out.println("No hay ruta encontrada.");
                    } else {
                        System.out.print("Ruta mínima: ");
                        for (int i = 0; i < ruta.size(); i++) {
                            System.out.print(ruta.get(i).getData());
                            if (i < ruta.size() - 1) System.out.print(" -> ");
                        }
                        System.out.println();
                    }
                }
                case 11 -> {
                    System.out.print("Nodo para calcular grado: ");
                    String nodo = sc.nextLine();
                    int grado = grafo.gradoNodo(nodo);
                    if (grado == -1) System.out.println("Nodo no encontrado.");
                    else System.out.println("Grado de " + nodo + ": " + grado);
                }
                case 12 -> {
                    System.out.println("¿El grafo es camino? " + (grafo.esCamino() ? "Sí" : "No"));
                }
                case 13 -> {
                    System.out.println("¿El grafo es ciclo? " + (grafo.esCiclo() ? "Sí" : "No"));
                }
                case 14 -> {
                    System.out.println("¿El grafo es completo? " + (grafo.esCompleto() ? "Sí" : "No"));
                }
                case 15 -> {
                    System.out.println("¿El grafo es rueda? " + (grafo.esRueda() ? "Sí" : "No"));
                }
                case 16 -> {
                    System.out.println("Representación formal:\n" + grafo.representacionFormal());
                }
                case 17 -> {
                    System.out.println("Lista de adyacencia:\n" + grafo.listaAdyacencia());
                }
                case 18 -> {
                    System.out.println("Matriz de adyacencia:\n" + grafo.matrizAdyacenciaString());
                }
                case 0 -> {
                    System.out.println("Saliendo...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
	