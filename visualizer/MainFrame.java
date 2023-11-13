package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class MainFrame extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    protected static final Color BACKGROUND_COLOR = Color.black;
    private JPanel graphPanel;
    private JLabel modeLabel;
    private static Mode mode = Mode.ADD_A_VERTEX;
    protected static List<Vertex> edgeVertices = new ArrayList<>();
    protected static List<List<String>> availableEdges = new ArrayList<>();

    enum Mode {
        ADD_A_VERTEX, ADD_AN_EDGE, NONE
    }

    public MainFrame() {
        super("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
        setLayout(null);
        setResizable(false);

        setModeJLabel();
        setJMenu();
        setJPanel();

        setVisible(true);

    }

    private void setJPanel() {
        //this.graphPanel = (JPanel) this.getContentPane();
        this.graphPanel = new JPanel();
        this.add(graphPanel);
        graphPanel.setName("Graph");
        graphPanel.setBackground(MainFrame.BACKGROUND_COLOR);
        graphPanel.setLayout(null);
        graphPanel.setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
        graphPanel.setLocation(0, 0);
        graphPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (mode == Mode.ADD_A_VERTEX) {
                    boolean validPosition = validPlacementForVertex(e.getX(), e.getY());
                    if (validPosition) {
                        while (true) {
                            String input = JOptionPane.showInputDialog(graphPanel, "Enter the Vertex ID (Should be 1 char):",
                                    "Vertex", JOptionPane.QUESTION_MESSAGE);
                            if (input == null) {
                                break;
                            } else {
                                input = input.trim();
                                if (input.length() == 1 && validVertexID(input)) {
                                    int xValue = e.getX() - Vertex.WIDTH / 2;
                                    int yValue = e.getY() - Vertex.HEIGHT / 2;
                                    createVertex(graphPanel, xValue, yValue, input);
                                    return;
                                }
                            }
                        }
                    }
                } else if (mode == Mode.ADD_AN_EDGE) {
                    // Check if a vertex was clicked
                    Vertex vertex = clickedOnVertex(e.getX(), e.getY());

                    if (vertex != null) {
                        edgeVertices.add(vertex);
                        if (edgeVertices.size() == 2) {
                            // Don't allow edge drawing if an edge between the two selected vertices already exists
                            for (List<String> verticesOfAnEdge: MainFrame.availableEdges) {
                                String id1 = edgeVertices.get(0).getId();
                                String id2 = edgeVertices.get(1).getId();
                                if (verticesOfAnEdge.contains(id1) && verticesOfAnEdge.contains(id2)) {
                                    edgeVertices.clear();
                                    return;
                                }
                            }
                            // New couple of vertices were selected
                            Vertex vertex1 = edgeVertices.get(0);
                            Vertex vertex2 = edgeVertices.get(1);
                            edgeVertices.clear();

                            List<String> newIdCouple = new ArrayList<>();
                            newIdCouple.add(vertex1.getId());
                            newIdCouple.add(vertex2.getId());
                            MainFrame.availableEdges.add(newIdCouple);

                            drawEdge(graphPanel, vertex1, vertex2);
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

            private static void drawEdge(JPanel graphPanel, Vertex vertex1, Vertex vertex2) {
                while (true) {
                    String input = JOptionPane.showInputDialog(graphPanel, "Enter Weight:",
                            "Input", JOptionPane.QUESTION_MESSAGE);
                    if (input == null) {
                        break;
                    } else {
                        if (input.matches("(-?[1-9]|0)")) {
                            Graphics g = graphPanel.getGraphics();
                            g.setColor(Vertex.VERTEX_COLOR);
                            g.setFont(new Font(null, Font.BOLD, 11));
                            g.drawLine(vertex1.getXLocation() + Vertex.WIDTH / 2,
                                    vertex1.getYLocation() + Vertex.HEIGHT / 2,
                                    vertex2.getXLocation() + Vertex.WIDTH / 2,
                                    vertex2.getYLocation() + Vertex.HEIGHT / 2);
                            int xMid = (vertex1.getXLocation() + Vertex.WIDTH / 2 + vertex2.getXLocation() + Vertex.WIDTH / 2) / 2;
                            int yMid = (vertex1.getYLocation() + Vertex.HEIGHT / 2 + vertex2.getYLocation() + Vertex.HEIGHT / 2) / 2;
                            g.drawString(input, xMid + 10, yMid + 10);
                            vertex1.repaint();
                            vertex2.repaint();
                            return;
                        }
                    }
                }

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
        addAVertex.setName("Add a Vertex");
        JMenuItem addAnEdge = new JMenuItem("Add an Edge");
        addAnEdge.setName("Add an Edge");
        JMenuItem none = new JMenuItem("None");
        none.setName("None");

        modeMenu.add(addAVertex);
        modeMenu.add(addAnEdge);
        modeMenu.add(none);

        // Add event listeners to the three menu items
        addAVertex.addActionListener(e -> {
            // Change text for label
            changeTextForModeLabel("Current Mode -> Add a Vertex");
            // Change the mode
            mode = Mode.ADD_A_VERTEX;
            // Remove response to previous vertex clicks
            edgeVertices.clear();
        });
        addAnEdge.addActionListener(e -> {
            // Change text for label
            changeTextForModeLabel("Current Mode -> Add an Edge");
            // Change the mode
            mode = Mode.ADD_AN_EDGE;
        });
        none.addActionListener(e -> {
            // Change text for label
            changeTextForModeLabel("Current Mode -> None");
            // Change the mode
            mode = Mode.NONE;
            // Remove response to previous vertex clicks
            edgeVertices.clear();
        });
    }

    private void changeTextForModeLabel(String newText) {
        this.modeLabel.setText(newText);
        modeLabel.setSize(modeLabel.getPreferredSize());
        this.modeLabel.setLocation(MainFrame.WIDTH - modeLabel.getWidth() - 20, 0);
    }

    private void setModeJLabel() {

        modeLabel = new JLabel();
        this.add(modeLabel);
        modeLabel.setName("Mode");
        modeLabel.setText("Current Mode -> Add a Vertex");
        modeLabel.setOpaque(true);
        modeLabel.setForeground(Vertex.VERTEX_COLOR);
        modeLabel.setBackground(MainFrame.BACKGROUND_COLOR);
        modeLabel.setSize(modeLabel.getPreferredSize());
        modeLabel.setLocation(MainFrame.WIDTH - modeLabel.getWidth() - 20, 0);

    }

    public static Vertex clickedOnVertex(int x, int y) {

        for (var entry: Vertex.vertices.entrySet()) {
            String id = entry.getKey();
            Vertex vertex = entry.getValue();
            int xLocation = vertex.getXLocation();
            int yLocation = vertex.getYLocation();

            if (x >= xLocation && x <= xLocation + Vertex.WIDTH
                    && y >= yLocation && y <= yLocation + Vertex.HEIGHT) {
                return vertex;
            }
        }

        return null;
    }

    public static boolean validPlacementForVertex(int x, int y) {

        for (Vertex vertex: Vertex.vertices.values()) {
            int xLocation = vertex.getXLocation();
            int yLocation = vertex.getYLocation();

            if (x >= xLocation - Vertex.WIDTH / 2 && x <= xLocation + Vertex.WIDTH + Vertex.WIDTH / 2
                    && y >= yLocation - Vertex.WIDTH / 2 && y <= yLocation + Vertex.HEIGHT + Vertex.HEIGHT / 2) {
                return false;
            }
        }

        return true;
    }

    public static boolean validVertexID(String userInput) {

        for (String id: Vertex.vertices.keySet()) {
            if (userInput.equals(id)) return false;
        }

        return true;
    }
}