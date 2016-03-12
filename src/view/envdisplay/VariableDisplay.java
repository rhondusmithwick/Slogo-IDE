package view.envdisplay;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import main.GlobalProperties;
import observables.ObjectObservable;
import view.Defaults;

/**
 * Class responsible for displaying user defined variables and starting user
 * updating process. is subclass of defined objects display abstract class.
 *
 * @author calisnelson and Stephen Kwok
 */

public class VariableDisplay extends DefinedObjectsDisplay {

    /**
     * creates new variable display instance
     *
     * @param pLang      string observable for storing and setting parsing language
     * @param intCommand string observable to pass commands to command entry instance
     * @param variables  simplestring property storing user defined variables
     * @param error      simplestring property to display error
     */
    public VariableDisplay(GlobalProperties globalProperties, ObjectObservable<String> internalCommand,
                           SimpleStringProperty variables, SimpleStringProperty error) {
        super(variables, globalProperties.getLanguage(), internalCommand, error, Defaults.ENV_SPLITTER.getDefault(),
                "varTitle");
        createCurrEnvDisp();
    }

    /**
     * creates a new updater object that updates a label's text based on user
     * input
     *
     * @param Label label whose text is to be updated
     */
    @Override
    protected void updateDefinedObject(Label label) {
        EnvActor updater = getUpdater(Defaults.VAR_UP_LOC.getDefault(), label);
        updater.show();
    }

    protected void parseString(String text) {
        if (text.equals("[]")) {
            return;
        }
        if (text.startsWith("[")) {
            text = text.substring(1);
        }
        if (text.endsWith("]")) {
            text = text.substring(0, text.length() - 1);
        }
        String[] split = text.split("=");
        if (split[0].equals(Defaults.REP_VAR.getDefault())) {
            return;
        }
        setLabel(split[0] + " " + split[1]);
    }

}