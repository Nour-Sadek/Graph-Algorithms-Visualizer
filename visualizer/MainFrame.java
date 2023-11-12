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
    private JLabel modeLabel;
    public MainFrame() {
        super("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
        setLayout(null);
        setLocationRelativeTo(null);

        setJMenu();
        setJPanel();
        setModeJLabel();

        setVisible(true);

    }

    private void setJPanel() {
        this.graphPanel = (JPanel) this.getContentPane();
        graphPanel.setName("Graph");
        graphPanel.setBackground(MainFrame.BACKGROUND_COLOR);
        graphPanel.setLayout(null);
        graphPanel.setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
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
                            int xValue = e.getX() - Vertex.WIDTH / 2;
                            int yValue = e.getY() - Vertex.HEIGHT / 2;
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

    private void setJMenu() {
        // Creating the menuBar
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        // Creating the menu "Mode"
        JMenu modeMenu = new JMenu("Mode");
        menuBar.add(modeMenu);

        // Creating the three menu items for "Mode"
        JMenuItem addAVertex = new JMenuItem("Add a Vertex");
        JMenuItem addAnEdge = new JMenuItem("Add an Edge");
        JMenuItem none = new JMenuItem("None");

        modeMenu.add(addAVertex);
        modeMenu.add(addAnEdge);
        modeMenu.add(none);

        // Add event listeners to the three menu items
        addAVertex.addActionListener(e -> {
            // Change text for label
            changeTextForModeLabel("Current Mode -> Add a Vertex");
        });  // TODO
        addAnEdge.addActionListener(e -> {
            // Change text for label
            changeTextForModeLabel("Current Mode -> Add an Edge");
        });  // TODO
        none.addActionListener(e -> {
            // Change text for label
            changeTextForModeLabel("Current Mode -> None");
        });  // TODO
    }

    private void changeTextForModeLabel(String newText) {
        this.modeLabel.setText(newText);
        modeLabel.setSize(modeLabel.getPreferredSize());
        this.modeLabel.setLocation(MainFrame.WIDTH - modeLabel.getWidth() - 20, 0);
    }

    private void setModeJLabel() {

        modeLabel = new JLabel();
        this.graphPanel.add(modeLabel);
        modeLabel.setName("Mode");
        modeLabel.setText("Current Mode -> Add a Vertex");
        modeLabel.setOpaque(true);
        modeLabel.setForeground(Vertex.VERTEX_COLOR);
        modeLabel.setBackground(MainFrame.BACKGROUND_COLOR);
        modeLabel.setSize(modeLabel.getPreferredSize());
        modeLabel.setLocation(MainFrame.WIDTH - modeLabel.getWidth() - 20, 0);

    }
}