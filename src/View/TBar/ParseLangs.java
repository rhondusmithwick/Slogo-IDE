package View.TBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import View.Defaults;

public class ParseLangs {
    private static ParseLangs instance;
    private ArrayList<String> langs;

    private ParseLangs(){
        getAllFromDirectory();
    }

    private void getAllFromDirectory () {
        
        langs = new ArrayList<>();
        File directory = new File(Defaults.PARSELANG_LOC.getDefault());
        File[] fList = directory.listFiles();
        for (File file : fList) {
            String name = file.getName();
            langs.add(name.substring(0, name.lastIndexOf('.')));
        }


    }
    
    public List<String> getLangs(){
        return langs;
    }

    public static synchronized ParseLangs getInstance() 
    {
        if (instance == null)
            instance = new ParseLangs();
        return instance;
    }







}


