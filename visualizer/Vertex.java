package visualizer;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Vertex extends JPanel {

    protected static final int SIZE = 50;
    protected static final Color VERTEX_COLOR = Color.white;
    protected static final Map<String, Vertex> vertices = new HashMap<>();
    private String id;
    private JLabel label;
    private int xLocation;
    private int yLocation;
    private List<Edge> edges = new ArrayList<>();

    public Vertex(int x, int y, String id) {
        this.id = id;
        this.xLocation = x;
        this.yLocation = y;
        vertices.put(this.id, this);
        this.label = new JLabel();
        this.label.setName("VertexLabel " + this.id);
        this.setLabel();

        this.setName("Vertex " + this.id);
        this.setBackground(MainFrame.BACKGROUND_COLOR);
        this.setOpaque(false);
        this.setLayout(null);
        this.setBounds(x, y, Vertex.SIZE, Vertex.SIZE);
    }

    public int getXLocation() {
        return this.xLocation;
    }

    public int getYLocation() {
        return this.yLocation;
    }

    public String getId() {
        return this.id;
    }
    public List<Edge> getEdges() { return this.edges; }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Vertex.VERTEX_COLOR);
        g.fillOval(0, 0, Vertex.SIZE, Vertex.SIZE);
    }

    private void setLabel() {
        this.label.setText(this.id);
        this.label.setVerticalAlignment(JLabel.CENTER);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.label.setSize(Vertex.SIZE, Vertex.SIZE);
        this.add(this.label);
    }


}

