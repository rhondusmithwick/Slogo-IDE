package view.xml;

import java.util.Arrays;
import java.util.List;

/**
 * enum representing the attribute names for the created and read xml files
 *
 * @author calinelson
 */

public enum AttrNames {

    WORKSPACE(Arrays.asList("BGColor", "PenColor", "PLang", "CFile", "IFile", "NumTurts")),
    INDEX_MAP(Arrays.asList("index", "name"));

    private List<String> names;

    /**
     * creates new attribute name enum from string of names
     *
     * @param names List<String> of atrribute names
     */
    AttrNames(List<String> names) {
        this.names = names;
    }

    /**
     * returns List<String> of names for enum
     *
     * @return List<String> of atrribute names
     */
    public List<String> getNames() {
        return names;
    }
}
