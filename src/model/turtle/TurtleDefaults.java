package model.turtle;

/**
 * Created by rhondusmithwick on 3/10/16.
 *
 * @author Rhondu Smithwick
 */
public enum TurtleDefaults {
    TURTLE_IMAGE("resources/images/blackarrow.png"),
    VISIBLE("true"),
    HEADING("180"),
    PEN_DOWN("true"),
    PEN_SIZE("1"),
    PEN_COLOR("black"),
    IMG_DIM("30");

    private String content;

    TurtleDefaults(String content) {
        this.content = content;
    }


    public String getString() {
        return content;
    }

    public Double getDouble() {
        return Double.valueOf(content);
    }

    public Boolean getBoolean() {
        return Boolean.valueOf(content);
    }
}
