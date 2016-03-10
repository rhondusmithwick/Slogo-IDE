package main;

import maps.ColorMap;
import maps.ImageMap;
import maps.IndexMap;
import observables.ObjectObservable;

/**
 * Created by rhondusmithwick on 3/9/16.
 *
 * @author Rhondu Smithwick
 */
public class GlobalProperties {
    private final IndexMap colorMap = new ColorMap();
    private final IndexMap imageMap = new ImageMap();
    private final ObjectObservable<String> input = new ObjectObservable<>();
    private final ObjectObservable<String> language = new ObjectObservable<>();
    private final ObjectObservable<String> backgroundColor = new ObjectObservable<>();

    public ObjectObservable<String> getInput() {
        return input;
    }

    public ObjectObservable<String> getLanguage() {
        return language;
    }

    public ObjectObservable<String> getBackgroundColor() {
        return backgroundColor;
    }

    public IndexMap getColorMap() {
        return colorMap;
    }

    public IndexMap getImageMap() {
        return imageMap;
    }
}
