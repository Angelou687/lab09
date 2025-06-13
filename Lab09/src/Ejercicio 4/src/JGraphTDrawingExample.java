import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import javax.swing.*;

/**
 * Ejemplo de dibujo de grafo dirigido ponderado usando JGraphT y JGraphX con Java Swing.
 */
public class JGraphTDrawingExample extends JFrame {

    private static final long serialVersionUID = 1L;

    private Graph<String, DefaultWeightedEdge> graph;
    private JGraphXAdapter<String, DefaultWeightedEdge> jgxAdapter;

    public JGraphTDrawingExample() {
        super("Dibujo de Grafo con JGraphT y JGraphX");

        // Crear grafo dirigido ponderado
        graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        // Agregar vértices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        // Agregar aristas con peso
        addEdgeWithWeight("A", "B", 5.0);
        addEdgeWithWeight("B", "C", 3.0);
        addEdgeWithWeight("A", "C", 10.0);
        addEdgeWithWeight("C", "D", 1.0);

        // Crear adaptador JGraphX para mostrar el grafo
        jgxAdapter = new JGraphXAdapter<>(graph);

        // Personalizar estilos de celda para mostrar pesos y flechas
        configureStyles();

        // Layout circular para ubicar nodos automáticamente
        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());

        // Componente gráfico para visualizar el grafo
        mxGraphComponent graphComponent = new mxGraphComponent(jgxAdapter);

        // Personalizar apariencia del componente (eliminar edición, mover, selección)
        graphComponent.setConnectable(false);
        graphComponent.getGraph().setAllowDanglingEdges(false);
        graphComponent.setDragEnabled(false);
        graphComponent.getGraph().setCellsEditable(false);
        graphComponent.getGraph().setCellsMovable(false);
        graphComponent.getGraph().setCellsResizable(false);

        // Añadir el componente al JFrame
        getContentPane().add(graphComponent);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null); // Centrar ventana
    }

    private void addEdgeWithWeight(String source, String target, double weight) {
        DefaultWeightedEdge e = graph.addEdge(source, target);
        if (e != null) {
            graph.setEdgeWeight(e, weight);
        }
    }

    private void configureStyles() {
        var stylesheet = jgxAdapter.getStylesheet();

        var vertexStyle = stylesheet.getDefaultVertexStyle();
        vertexStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        vertexStyle.put(mxConstants.STYLE_FILLCOLOR, "#98d0f7");
        vertexStyle.put(mxConstants.STYLE_STROKECOLOR, "#003399");
        vertexStyle.put(mxConstants.STYLE_FONTCOLOR, "#000000");
        vertexStyle.put(mxConstants.STYLE_FONTSIZE, 14);
        vertexStyle.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
        vertexStyle.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_CENTER);
        vertexStyle.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_CENTER);
        vertexStyle.put(mxConstants.STYLE_LABEL_POSITION, mxConstants.ALIGN_CENTER);

        var edgeStyle = stylesheet.getDefaultEdgeStyle();
        edgeStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        edgeStyle.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
        edgeStyle.put(mxConstants.STYLE_STROKECOLOR, "#404040");
        edgeStyle.put(mxConstants.STYLE_FONTCOLOR, "#FF0000");
        edgeStyle.put(mxConstants.STYLE_FONTSIZE, 12);
        edgeStyle.put(mxConstants.STYLE_LABEL_BACKGROUNDCOLOR, "#FFFFFF");
        edgeStyle.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ELBOW);
        edgeStyle.put(mxConstants.STYLE_ROUNDED, true);

        // Asignar estilos personalizados
        stylesheet.setDefaultVertexStyle(vertexStyle);
        stylesheet.setDefaultEdgeStyle(edgeStyle);

        // Mostrar pesos en las etiquetas de las aristas
        for (DefaultWeightedEdge edge : graph.edgeSet()) {
            mxICell cell = jgxAdapter.getEdgeToCellMap().get(edge);
            if (cell != null) {
                cell.setValue(String.format("%.1f", graph.getEdgeWeight(edge)));
            }
        }
    }

    public static void main(String[] args) {
        // Ejecutar GUI en la hebra de eventos para Swing
        SwingUtilities.invokeLater(() -> {
            JGraphTDrawingExample frame = new JGraphTDrawingExample();
            frame.setVisible(true);
        });
    }
}
