package graph;

import estructura.*;

public class GraphListEdge<V, E> {
    ArrayList<VertexObj<V, E>> secVertex;
    ArrayList<EdgeObj<V, E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    // a) insertVertex(v): inserta el vértice 'v' si no existe
    public void insertVertex(V v) {
        if (!searchVertex(v)) {
            VertexObj<V, E> vertex = new VertexObj<>(v, secVertex.size());
            secVertex.add(vertex);
        }
    }

    // b) insertEdge(v, z): inserta la arista entre 'v' y 'z' si no existe
    public void insertEdge(V v, V z) {
        VertexObj<V, E> vertexV = getVertex(v);
        VertexObj<V, E> vertexZ = getVertex(z);

        if (vertexV == null || vertexZ == null) {
            System.out.println("Uno o ambos vértices no existen.");
            return;
        }

        if (!searchEdge(v, z)) {
            EdgeObj<V, E> edge = new EdgeObj<>(vertexV, vertexZ, null, secEdge.size());
            secEdge.add(edge);
        }
    }

    // c) searchVertex(v): retorna true si existe vértice 'v'
    public boolean searchVertex(V v) {
        return getVertex(v) != null;
    }

    // d) searchEdge(v, z): retorna true si existe arista entre 'v' y 'z'
    public boolean searchEdge(V v, V z) {
        for (int i = 0; i < secEdge.size(); i++) {
            EdgeObj<V, E> edge = secEdge.get(i);
            V v1 = edge.getInitVertex().getInfo();
            V v2 = edge.getEndVertex().getInfo();

            if ((v1.equals(v) && v2.equals(z)) || (v1.equals(z) && v2.equals(v))) {
                return true;
            }
        }
        return false;
    }

    // e) bfs(v): recorrido en anchura desde 'v', mostrando vértices visitados
    public void bfs(V v) {
        VertexObj<V, E> startVertex = getVertex(v);
        if (startVertex == null) {
            System.out.println("El vértice inicial no existe.");
            return;
        }

        Queue<VertexObj<V, E>> queue = new Queue<>();
        ArrayList<VertexObj<V, E>> visited = new ArrayList<>();

        queue.enqueue(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            VertexObj<V, E> current = queue.dequeue();
            System.out.print(current.getInfo() + " ");

            // Recorremos todas las aristas para encontrar vecinos de current
            for (int i = 0; i < secEdge.size(); i++) {
                EdgeObj<V, E> edge = secEdge.get(i);
                VertexObj<V, E> v1 = edge.getInitVertex();
                VertexObj<V, E> v2 = edge.getEndVertex();

                VertexObj<V, E> neighbor = null;

                if (v1.equals(current)) neighbor = v2;
                else if (v2.equals(current)) neighbor = v1;

                if (neighbor != null && !visited.contains(neighbor)) {
                    queue.enqueue(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    // Método privado para obtener vértice dado su info
    private VertexObj<V, E> getVertex(V v) {
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V, E> vertex = secVertex.get(i);
            if (vertex.getInfo().equals(v)) {
                return vertex;
            }
        }
        return null;
    }
}
