package view.utilities;

import java.util.ResourceBundle;
import view.Defaults;

public class GetCommand {
    
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
