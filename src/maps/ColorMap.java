package maps;

import java.lang.reflect.Field;

import view.Defaults;
import javafx.scene.paint.Color;
import observables.MapObservable;

public class ColorMap extends IndexMap {
    private static final int B = 2;
    private static final int G = 1;
    private static final int R = 0;
    private MapObservable<Integer,String> colors;
    private int count;




    public ColorMap() throws Exception{
        super();
    }






    @Override
    public MapObservable<Integer, String> getIndexMap(){
        return colors;

    }
    @Override
    public void setAtIndex(int index, String toSet) throws Exception{
        String[] rgb = toSet.split(" ");
        String value = "rgb(" + rgb[R] + "," + rgb[G]+ ","+rgb[B]+")";
        colors.put(index, value);
        colors.notifyObservers();

    }



    @SuppressWarnings("rawtypes")
    protected void defaultElements () throws Exception{
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


    @Override
    protected void newMap () {
        colors = new MapObservable<>("colors");

    }


    @Override
    public String get(int key) {
        return colors.get(key);

    }


    @Override
    protected String getDirectory () {
       return Defaults.COLORLIST_LOC.getDefault();
    }








}
