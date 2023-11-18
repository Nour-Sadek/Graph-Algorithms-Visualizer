package visualizer;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 600;
    protected static final Color BACKGROUND_COLOR = Color.black;
    private JLabel modeLabel;
    private static final JLabel algorithmDisplayLabel;
    private Graph graphPanel;
    protected static Mode mode = Mode.ADD_A_VERTEX;
    protected static Algorithm algorithm = null;

    static {
        algorithmDisplayLabel = new JLabel();
        algorithmDisplayLabel.setName("Display");
        algorithmDisplayLabel.setText("Please choose a starting vertex");
        algorithmDisplayLabel.setForeground(MainFrame.BACKGROUND_COLOR);
        algorithmDisplayLabel.setBackground(Color.white);
        algorithmDisplayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        algorithmDisplayLabel.setVerticalAlignment(SwingConstants.CENTER);
        algorithmDisplayLabel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        algorithmDisplayLabel.setVisible(false);
    }


    public MainFrame() {
        super("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
        setLayout(new BorderLayout());
        setResizable(false);

        setModeJLabel();
        this.add(algorithmDisplayLabel, BorderLayout.SOUTH);
        setJMenu();

        add(this.graphPanel = new Graph(), BorderLayout.CENTER);

        setVisible(true);

    }

    private void setJMenu() {
        // Creating the menuBar
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        // Creating the menu "File"
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Creating the menu "Mode"
        JMenu modeMenu = new JMenu("Mode");
        menuBar.add(modeMenu);

        // Creating the menu "Algorithms"
        JMenu algorithmsMenu = new JMenu("Algorithms");
        menuBar.add(algorithmsMenu);

        // Creating the five menu items for "Mode"
        JMenuItem addAVertex = new JMenuItem("Add a Vertex");
        addAVertex.setName("Add a Vertex");
        JMenuItem addAnEdge = new JMenuItem("Add an Edge");
        addAnEdge.setName("Add an Edge");
        JMenuItem none = new JMenuItem("None");
        none.setName("None");
        JMenuItem removeAVertex = new JMenuItem("Remove a Vertex");
        removeAVertex.setName("Remove a Vertex");
        JMenuItem removeAnEdge = new JMenuItem("Remove an Edge");
        removeAnEdge.setName("Remove an Edge");

        modeMenu.add(addAVertex);
        modeMenu.add(addAnEdge);
        modeMenu.add(removeAVertex);
        modeMenu.add(removeAnEdge);
        modeMenu.add(none);

        // Creating the two menu items for "File"
        JMenuItem newReset = new JMenuItem("New");
        newReset.setName("New");
        JMenuItem exit = new JMenuItem("Exit");
        exit.setName("Exit");

        fileMenu.add(newReset);
        fileMenu.add(exit);

        // Creating the four menu items for "Algorithms"
        JMenuItem DFS = new JMenuItem("Depth-First Search");
        DFS.setName("Depth-First Search");
        JMenuItem BFS = new JMenuItem("Breadth-First Search");
        BFS.setName("Breadth-First Search");
        JMenuItem Dijkstras = new JMenuItem("Dijkstra's Algorithm");
        Dijkstras.setName("Dijkstra's Algorithm");
        JMenuItem Prims = new JMenuItem("Prim's Algorithm");
        Prims.setName("Prim's Algorithm");

        algorithmsMenu.add(DFS);
        algorithmsMenu.add(BFS);
        algorithmsMenu.add(Dijkstras);
        algorithmsMenu.add(Prims);

        // Add event listeners to the five menu items of "Mode"
        addAVertex.addActionListener(e -> {
            // Change the mode
            mode = Mode.ADD_A_VERTEX;
            // Change text for label
            changeTextForModeLabel("Current Mode -> " + mode.getDescription());
            // Remove response to previous vertex clicks
            Graph.edgeVertices.clear();
            // Switch algorithmDisplayLabel visibility to false and text
            algorithmDisplayLabel.setVisible(false);
            algorithmDisplayLabel.setText("Please choose a starting vertex");
        });
        addAnEdge.addActionListener(e -> {
            // Change the mode
            mode = Mode.ADD_AN_EDGE;
            // Change text for label
            changeTextForModeLabel("Current Mode -> " + mode.getDescription());
            // Switch algorithmDisplayLabel visibility to false and text
            algorithmDisplayLabel.setVisible(false);
            algorithmDisplayLabel.setText("Please choose a starting vertex");
        });
        none.addActionListener(e -> {
            // Change the mode
            mode = Mode.NONE;
            // Change text for label
            changeTextForModeLabel("Current Mode -> " + mode.getDescription());
            // Remove response to previous vertex clicks
            Graph.edgeVertices.clear();
            // Switch algorithmDisplayLabel visibility to false and text
            algorithmDisplayLabel.setVisible(false);
            algorithmDisplayLabel.setText("Please choose a starting vertex");
        });
        removeAVertex.addActionListener(e -> {
            // Change the mode
            mode = Mode.REMOVE_A_VERTEX;
            // Change text for label
            changeTextForModeLabel("Current Mode -> " + mode.getDescription());
            // Remove response to previous vertex clicks
            Graph.edgeVertices.clear();
            // Switch algorithmDisplayLabel visibility to false and text
            algorithmDisplayLabel.setVisible(false);
            algorithmDisplayLabel.setText("Please choose a starting vertex");
        });
        removeAnEdge.addActionListener(e -> {
            // Change the mode
            mode = Mode.REMOVE_AN_EDGE;
            // Change text for label
            changeTextForModeLabel("Current Mode -> " + mode.getDescription());
            // Remove response to previous vertex clicks
            Graph.edgeVertices.clear();
            // Switch algorithmDisplayLabel visibility to false and text
            algorithmDisplayLabel.setVisible(false);
            algorithmDisplayLabel.setText("Please choose a starting vertex");
        });

        // Add event listeners to the two menu items of "File"
        newReset.addActionListener(e -> {
            this.remove(this.graphPanel);
            this.repaint();
            this.revalidate();
            this.add(this.graphPanel = new Graph());

            // Clear all the static containers
            Edge.edges.clear();
            Vertex.vertices.clear();
            Graph.edgeVertices.clear();
            Graph.availableEdges.clear();

            // Change the mode
            mode = Mode.ADD_A_VERTEX;
            // Change text for label
            changeTextForModeLabel("Current Mode -> " + mode.getDescription());
            // Switch algorithmDisplayLabel visibility to false and text
            algorithmDisplayLabel.setVisible(false);
            algorithmDisplayLabel.setText("Please choose a starting vertex");

        });
        exit.addActionListener(e -> {
            this.dispose();
        });

        // Add event listeners to the four menu items of "Algorithms"
        DFS.addActionListener(e -> {
            none.doClick();
            // Switch algorithmDisplayLabel visibility to true
            algorithmDisplayLabel.setVisible(true);
            // Change the algorithm
            algorithm = Algorithm.DFS;
        });
        BFS.addActionListener(e -> {
            none.doClick();
            // Switch algorithmDisplayLabel visibility to true
            algorithmDisplayLabel.setVisible(true);
            // Change the algorithm
            algorithm = Algorithm.BFS;
        });
        Dijkstras.addActionListener(e -> {
            none.doClick();
            // Switch algorithmDisplayLabel visibility to true
            algorithmDisplayLabel.setVisible(true);
            // Change the algorithm
            algorithm = Algorithm.DIJKSTRAS;
        });
        Prims.addActionListener(e -> {
            none.doClick();
            // Switch algorithmDisplayLabel visibility to true
            algorithmDisplayLabel.setVisible(true);
            // Change the algorithm
            algorithm = Algorithm.PRIMS;
        });
    }

    private void changeTextForModeLabel(String newText) {
        this.modeLabel.setText(newText);
        modeLabel.setSize(modeLabel.getPreferredSize());
        this.modeLabel.setLocation(MainFrame.WIDTH - modeLabel.getWidth() - 20, 0);
    }

    private void setModeJLabel() {
        modeLabel = new JLabel();
        this.add(modeLabel, BorderLayout.NORTH);
        modeLabel.setName("Mode");
        modeLabel.setText("Current Mode -> Add a Vertex");
        modeLabel.setOpaque(true);
        modeLabel.setForeground(Vertex.VERTEX_COLOR);
        modeLabel.setBackground(MainFrame.BACKGROUND_COLOR);
        modeLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        modeLabel.setHorizontalAlignment(SwingConstants.RIGHT);

    }

    public static JLabel getAlgorithmDisplayLabel() {
        return algorithmDisplayLabel;
    }

}
