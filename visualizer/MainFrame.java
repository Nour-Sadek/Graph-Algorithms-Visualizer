package visualizer;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    protected static final Color BACKGROUND_COLOR = Color.black;
    public MainFrame() {
        super("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setJPanel();
        this.pack();

    }

    private void setJPanel() {
        JPanel graphPanel = new JPanel();
        graphPanel.setName("Graph");
        graphPanel.setBackground(MainFrame.BACKGROUND_COLOR);
        graphPanel.setPreferredSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
        graphPanel.setLayout(null);
        this.add(graphPanel);

        // Set the four vertices in this panel
        setVertices(graphPanel);
    }

    private void setVertices(JPanel graphPanel) {
        JPanel vertex0 = new Vertex(0, 0);
        Vertex vertex1 = new Vertex(MainFrame.WIDTH - Vertex.WIDTH, 0);
        Vertex vertex2 = new Vertex(0, MainFrame.HEIGHT - Vertex.HEIGHT);
        Vertex vertex3 = new Vertex(MainFrame.WIDTH - Vertex.WIDTH, MainFrame.HEIGHT - Vertex.HEIGHT);

        graphPanel.add(vertex0);
        graphPanel.add(vertex1);
        graphPanel.add(vertex2);
        graphPanel.add(vertex3);
    }

}