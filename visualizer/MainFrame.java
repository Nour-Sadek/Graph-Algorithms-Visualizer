package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    protected static final Color BACKGROUND_COLOR = Color.black;
    private JPanel graphPanel;
    public MainFrame() {
        super("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(MainFrame.WIDTH, MainFrame.HEIGHT);

        setJPanel();
    }

    private void setJPanel() {
        this.graphPanel = new JPanel();
        graphPanel.setName("Graph");
        graphPanel.setBackground(MainFrame.BACKGROUND_COLOR);
        graphPanel.setLayout(null);
        this.add(graphPanel);
        graphPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                while (true) {
                    String input = JOptionPane.showInputDialog(graphPanel, "Enter the Vertex ID (Should be 1 char):",
                            "Vertex", JOptionPane.QUESTION_MESSAGE);
                    if (input == null) {
                        break;
                    } else {
                        input = input.trim();
                        if (input.length() == 1) {
                            int xValue = e.getX();
                            int yValue = e.getY();
                            createVertex(graphPanel, xValue, yValue, input);
                            return;
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            private static void createVertex(JPanel graphPanel, int xValue, int yValue, String id) {
                JPanel vertex = new Vertex(xValue, yValue, id);
                graphPanel.add(vertex);
                vertex.repaint();
            }
        });

    }
}