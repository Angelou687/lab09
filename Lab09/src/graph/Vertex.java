package graph;

import estructura.*;

public class Vertex<E> implements Comparable<Vertex<E>> {
    private E data;
    public ListLinked<Edge<E>> listAdj;
    public int dist; // distancia usada para comparación

    public Vertex(E data) {
        this.data = data;
        this.listAdj = new ListLinked<>();
        this.dist = Integer.MAX_VALUE;
    }

    public E getData() {
        return data;
    }

    @Override
    public int compareTo(Vertex<E> other) {
        return Integer.compare(this.dist, other.dist);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vertex) {
            Vertex<?> v = (Vertex<?>) obj;
            return data.equals(v.data);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
    
    @Override
    public String toString() {
        return data + " -> " + listAdj.toString();
    }
    
}
