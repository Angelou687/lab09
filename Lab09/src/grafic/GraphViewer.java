package grafic;
import graph.*;
import javax.swing.*;

public class GraphViewer {
    public static <E> void mostrar(GraphLink<E> grafo) {
        JFrame frame = new JFrame("Visualización del Grafo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new GraphPanel<>(grafo));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
