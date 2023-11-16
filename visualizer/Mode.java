package visualizer;

public enum Mode {

    NONE("None"),
    ADD_A_VERTEX("Add a Vertex"),
    ADD_AN_EDGE("Add an Edge"),
    REMOVE_A_VERTEX("Remove a Vertex"),
    REMOVE_AN_EDGE("Remove an Edge");
    private final String description;

    Mode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
