package grafic;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.util.HashMap;
import java.util.Random;

import graph.*;
import estructura.*;

public class GraphPanel<E> extends JPanel {
    private static final long serialVersionUID = 1L;
    private GraphLink<E> graph;
    private HashMap<Vertex<E>, Point> positions;

    public GraphPanel(GraphLink<E> graph) {
        this.graph = graph;
        this.positions = new HashMap<>();
        asignarCoordenadas();
    }

    private void asignarCoordenadas() {
        int width = 600;
        int height = 400;
        int padding = 50;
        Random rand = new Random();

        Node<Vertex<E>> aux = graph.listVertex.getHead();
        while (aux != null) {
            int x = padding + rand.nextInt(width - 2 * padding);
            int y = padding + rand.nextInt(height - 2 * padding);
            positions.put(aux.data, new Point(x, y));
            aux = aux.next;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar aristas
        g2.setColor(Color.BLACK);
        Node<Vertex<E>> aux = graph.listVertex.getHead();
        while (aux != null) {
            Point p1 = positions.get(aux.data);
            Node<Edge<E>> adj = aux.data.listAdj.getHead();
            while (adj != null) {
                Vertex<E> destino = adj.data.getDestination();
                Point p2 = positions.get(destino);

                if (graph.dirigido || aux.data.getData().hashCode() <= destino.getData().hashCode()) {

                    // Verificar si hay arista inversa
                    boolean aristaInversa = false;
                    Node<Edge<E>> adjInv = destino.listAdj.getHead();
                    while (adjInv != null) {
                        if (adjInv.data.getDestination().equals(aux.data)) {
                            aristaInversa = true;
                            break;
                        }
                        adjInv = adjInv.next;
                    }

                    int peso = adj.data.getWeight();

                    if (graph.dirigido && aristaInversa) {
                        drawCurvedArrow(g2, p1, p2, peso, true);
                    } else {
                        drawArrow(g2, p1, p2, peso);
                    }
                }
                adj = adj.next;
            }
            aux = aux.next;
        }

        // Dibujar nodos
        for (Vertex<E> v : positions.keySet()) {
            Point p = positions.get(v);
            g2.setColor(Color.CYAN);
            g2.fillOval(p.x - 15, p.y - 15, 30, 30);
            g2.setColor(Color.BLACK);
            g2.drawOval(p.x - 15, p.y - 15, 30, 30);
            g2.drawString(v.getData().toString(), p.x - 5, p.y + 5);
        }
    }

    private void drawArrow(Graphics2D g2, Point p1, Point p2, int peso) {
        g2.drawLine(p1.x, p1.y, p2.x, p2.y);
        drawArrowHead(g2, p1, p2);

        int mx = (p1.x + p2.x) / 2;
        int my = (p1.y + p2.y) / 2;
        g2.setColor(Color.RED);
        g2.drawString(String.valueOf(peso), mx, my);
        g2.setColor(Color.BLACK);
    }

    private void drawCurvedArrow(Graphics2D g2, Point p1, Point p2, int peso, boolean desplazarArriba) {
        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;
        int mx = (p1.x + p2.x) / 2;
        int my = (p1.y + p2.y) / 2;

        double dist = Math.sqrt(dx * dx + dy * dy);
        double nx = -dy / dist;
        double ny = dx / dist;

        int offset = 20;
        if (!desplazarArriba) offset = -offset;

        int cx = (int) (mx + nx * offset);
        int cy = (int) (my + ny * offset);

        QuadCurve2D q = new QuadCurve2D.Float();
        q.setCurve(p1.x, p1.y, cx, cy, p2.x, p2.y);
        g2.draw(q);

        double t = 0.8;
        double xt = Math.pow(1 - t, 2) * p1.x + 2 * (1 - t) * t * cx + Math.pow(t, 2) * p2.x;
        double yt = Math.pow(1 - t, 2) * p1.y + 2 * (1 - t) * t * cy + Math.pow(t, 2) * p2.y;

        double dxdt = 2 * (1 - t) * (cx - p1.x) + 2 * t * (p2.x - cx);
        double dydt = 2 * (1 - t) * (cy - p1.y) + 2 * t * (p2.y - cy);

        drawArrowHead(g2, xt, yt, dxdt, dydt);

        g2.setColor(Color.RED);
        g2.drawString(String.valueOf(peso), cx, cy);
        g2.setColor(Color.BLACK);
    }

    private void drawArrowHead(Graphics2D g2, Point p1, Point p2) {
        double phi = Math.toRadians(20);
        int barb = 10;

        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;
        double theta = Math.atan2(dy, dx);

        double x, y;

        for (int j = 0; j < 2; j++) {
            double rho = theta + (j == 0 ? phi : -phi);
            x = p2.x - barb * Math.cos(rho);
            y = p2.y - barb * Math.sin(rho);
            g2.drawLine(p2.x, p2.y, (int) x, (int) y);
        }
    }

    private void drawArrowHead(Graphics2D g2, double x, double y, double dx, double dy) {
        double phi = Math.toRadians(20);
        int barb = 10;

        double theta = Math.atan2(dy, dx);
        double x1, y1;

        for (int j = 0; j < 2; j++) {
            double rho = theta + (j == 0 ? phi : -phi);
            x1 = x - barb * Math.cos(rho);
            y1 = y - barb * Math.sin(rho);
            g2.drawLine((int) x, (int) y, (int) x1, (int) y1);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(700, 500);
    }
}
