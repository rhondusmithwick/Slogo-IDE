package view.utilities;

import java.util.ResourceBundle;
import view.Defaults;


/**
 * Class is responsible for creating command from given key and
 * a commands resource bundle given the current parsing language. 
 * Contains a utilities function so is never actually instantiated.
 * @author Cali
 *
 */

public class GetCommand {
    
    private GetCommand(){}
    
    /**
     * Makes a command that can be correctly passed to backend from a command key and
     * the current parsing language.
     * 
     * Is static so that it can be accessed as the actual class is never instantiated,
     * also so that function can be accessed without this object being passed.
     * @param key key to get correct command from current command resource bundle
     * @param pLang current parsing language to create command resource bundle from
     * @return created command given the parsing language and key
     */
    public static String makeCommand(String key, String pLang){
        ResourceBundle myCommands = ResourceBundle.getBundle(pLang);
        String posCommands = myCommands.getString(key);
        String command;
        int multCommands = posCommands.indexOf(Defaults.COMM_SPLITER.getDefault());
        if(multCommands >0){
            command = posCommands.substring(0, multCommands);
        }else{
            command = posCommands;
        }
        return command;
    }
}
