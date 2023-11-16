package visualizer;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Edge extends JComponent implements Comparable<Edge> {
    private Vertex vertex1;
    private Vertex vertex2;
    private int weight;
    private JLabel label;
    private boolean topEqualsLeft;
    protected static List<Edge> edges = new ArrayList<>();

    public Edge(Vertex vertex1, Vertex vertex2, int weight) {
        this.setName("Edge <" + vertex1.getId() + " -> " + vertex2.getId() + ">");
        this.setBackground(MainFrame.BACKGROUND_COLOR);
        this.setLayout(null);
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;

        this.topEqualsLeft = setEdgeBounds();
        setLabel();

        edges.add(this);
    }

    /*
    public Edge(Vertex vertex1, Vertex vertex2) {
        this.setName("Edge <" + vertex1.getId() + " -> " + vertex2.getId() + ">");
        this.setBackground(MainFrame.BACKGROUND_COLOR);
        this.setLayout(null);
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.topEqualsLeft = setEdgeBounds();

        edges.add(this);
    }

     */

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Vertex.VERTEX_COLOR);
        g.drawLine(0, topEqualsLeft ? 0 : this.getHeight(),
                this.getWidth(), topEqualsLeft ? this.getHeight() : 0);
    }

    private void setLabel() {
        this.label = new JLabel();
        this.label.setName("EdgeLabel <" + vertex1.getId() + " -> " + vertex2.getId() + ">");
        this.label.setText(String.valueOf(this.weight));
        this.label.setLocation(this.getX() + this.getWidth() / 2, this.getY() + this.getHeight() / 2);
        this.label.setSize(this.label.getPreferredSize());
        this.label.setForeground(Vertex.VERTEX_COLOR);
    }

    private boolean setEdgeBounds() {
        // top and left
        Vertex top;  // To determine the y-coordinate
        Vertex left;  // To determine the x-coordinate
        top = this.vertex1.getYLocation() < this.vertex2.getYLocation() ? vertex1 : vertex2;
        left = this.vertex1.getXLocation() < this.vertex2.getXLocation() ? vertex1 : vertex2;
        int extra = Vertex.SIZE / 2;

        int width = Math.abs(this.vertex1.getXLocation() - this.vertex2.getXLocation());
        int height = Math.abs(this.vertex1.getYLocation() - this.vertex2.getYLocation());

        this.setBounds(left.getXLocation() + extra - 2,
                top.getYLocation() + extra - 2,
                width + 4,
                height + 4);

        return top == left;
    }

    public JLabel getLabel() {
        return this.label;
    }

    public int getWeight() {
        return this.weight;
    }

    public Vertex getVertex1() {
        return this.vertex1;
    }

    public Vertex getVertex2() {
        return this.vertex2;
    }

    public boolean getTopEqualsLeft() {
        return this.topEqualsLeft;
    }

    public boolean equals(Edge other) {
        return (this.vertex1.equals(other.vertex1) || this.vertex1.equals(other.vertex2))
                && (this.vertex2.equals(other.vertex1) || this.vertex2.equals(other.vertex2));
    }

    @Override
    public int compareTo(Edge otherEdge) {
        return Integer.valueOf(this.weight).compareTo(otherEdge.getWeight());
    }

}
