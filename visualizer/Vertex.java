package visualizer;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Vertex extends JPanel {

    protected static final int WIDTH = 50;
    protected static final int HEIGHT = 50;
    protected static final Color VERTEX_COLOR = Color.white;
    //protected static final Map<String, Vertex> vertices = new HashMap<>();
    private String id;
    private JLabel label;

    /*
    public Vertex(int x, int y) {

        this.id = String.valueOf(vertices.size());
        vertices.put(this.id, this);
        this.label = new JLabel("VertexLabel " + this.id);
        this.setLabel();

        this.setName("Vertex " + this.id);
        this.setBackground(MainFrame.BACKGROUND_COLOR);
        this.setLayout(null);
        this.setBounds(x, y, Vertex.WIDTH, Vertex.HEIGHT);

    }

     */

    public Vertex(int x, int y, String id) {
        this.id = id;
        //vertices.put(this.id, this);
        this.label = new JLabel();
        this.label.setName("VertexLabel " + this.id);
        this.setLabel();

        this.setName("Vertex " + this.id);
        this.setBackground(MainFrame.BACKGROUND_COLOR);
        this.setLayout(null);
        this.setBounds(x, y, Vertex.WIDTH, Vertex.HEIGHT);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Vertex.VERTEX_COLOR);
        g.fillOval(0, 0, Vertex.WIDTH, Vertex.HEIGHT);
    }

    private void setLabel() {
        this.label.setText(String.valueOf(this.id));
        this.label.setVerticalAlignment(JLabel.CENTER);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.label.setSize(Vertex.WIDTH, Vertex.HEIGHT);
        this.add(this.label);
    }


}

