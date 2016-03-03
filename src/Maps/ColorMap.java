package Maps;

import java.lang.reflect.Field;


import Observables.MapObservable;
import View.Defaults;
import javafx.scene.paint.Color;

public class ColorMap extends IndexMap {
    private static ColorMap instance;
    private MapObservable<Integer,String> colors;
    private int count;


    
    
    private ColorMap() throws Exception{
        super();
    }
    
    
    public static synchronized ColorMap getInstance() throws Exception
    {
            if (instance == null)
                    instance = new ColorMap();
            return instance;
    }
    
    
    
    
    @Override
    public MapObservable<Integer, String> getIndexMap(){
        return colors;
        
    }
    @Override
    public void setAtIndex(int index, String toSet) throws Exception{
        String[] rgb = toSet.split(" ");
        String value = "rgb(" + rgb[0] + "," + rgb[1]+ ","+rgb[2]+")";
        colors.put(index, value);
        colors.notifyObservers();
        
    }

    
    @Override
    public void addElements (String type) throws Exception {
        if(type.equals("default")){
            stripColors();
        }
        
    }

    @SuppressWarnings("rawtypes")
    private void stripColors () throws Exception{
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
    
    
    
}
