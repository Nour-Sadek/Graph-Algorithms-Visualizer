package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame implements MouseListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    protected static final Color BACKGROUND_COLOR = Color.black;
    private JPanel graphPanel;
    public MainFrame() {
        super("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addMouseListener(this);
        setSize(MainFrame.WIDTH, MainFrame.HEIGHT);

        setJPanel();

    }

    private void setJPanel() {
        this.graphPanel = new JPanel();
        graphPanel.setName("Graph");
        graphPanel.setBackground(MainFrame.BACKGROUND_COLOR);
        //graphPanel.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        graphPanel.setLayout(null);
        this.add(graphPanel);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Cancel gives null
        // OK gives input entered by user (empty string if user doesn't give input)
        while (true) {
            String input = JOptionPane.showInputDialog(this, "Enter the Vertex ID (Should be 1 char):",
                    "Vertex", JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                break;
            } else {
                input = input.trim();
                if (input.length() == 1) {
                    int xValue = e.getX();
                    int yValue = e.getY();
                    this.createVertex(xValue, yValue, input);
                    return;
                }
            }
        }
    }

    private void createVertex(int xValue, int yValue, String id) {
        JPanel vertex = new Vertex(xValue, yValue, id);
        this.graphPanel.add(vertex);
        vertex.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}