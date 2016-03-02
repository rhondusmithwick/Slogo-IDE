package View.TBar;

import java.lang.reflect.Field;


import Observables.MapObservable;
import View.Defaults;
import javafx.scene.paint.Color;

public class ColorMap {
    private static ColorMap instance;
    private MapObservable<Integer,String> colors;
    private int count;

    
    
    private ColorMap() throws Exception{
        addColors();
    }


    @SuppressWarnings("rawtypes")
    private void addColors() throws Exception {

            colors = new MapObservable<>("colorMap");
            count =0;
            Class colorClass = Class.forName(Defaults.FX_PAINT_CLASS.getDefault());
            Field[] fields = colorClass.getFields();
            for (Field field : fields) {
                Object o = field.get(null);
                if (o instanceof Color) {
                    colors.put(count, field.getName());
                    count++;
                }
            }


    }
    
    
    public static synchronized ColorMap getInstance() throws Exception
    {
            if (instance == null)
                    instance = new ColorMap();
            return instance;
    }
    
    public MapObservable<Integer, String> getIndexMap(){
        return colors;
    }
    
    public void setColorAtIndex(int r, int g , int b, int index){
        String value = "rgb(" + Integer.toString(r) + "," + Integer.toString(b)+ ","+Integer.toString(b)+")";
        colors.put(index, value);
        colors.notifyObservers();
        
    }
    
    
    
}
