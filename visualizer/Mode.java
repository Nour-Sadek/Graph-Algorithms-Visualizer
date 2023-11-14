package visualizer;

public enum Mode {

    NONE("None"), ADD_A_VERTEX("Add a Vertex"), ADD_AN_EDGE("Add an Edge");
    private final String description;

    Mode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
