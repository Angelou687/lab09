package graph;
import estructura.*;

public class VertexObj<V, E> {
    protected V info;
    protected int position;
    protected ListLinked<EdgeObj<V, E>> listAdj;

    public VertexObj(V info, int position) {
        this.info = info;
        this.position = position;
        this.listAdj = new ListLinked<>();
    }

    public V getInfo() {
        return info;
    }

    public int getPosition() {
        return position;
    }

    public ListLinked<EdgeObj<V, E>> getListAdj() {
        return listAdj;
    }
}
