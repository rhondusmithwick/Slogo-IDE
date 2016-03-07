package maps;

import observables.MapObservable;
import view.Defaults;
import view.xml.MapFromXML;

public abstract class IndexMap {
    protected IndexMap() throws Exception{
        addElements(Defaults.DEFAULT.getDefault());
    }
    
    public void addElements(String type) throws Exception{
        newMap();
        if(type.equals(Defaults.DEFAULT.getDefault())){
            defaultElements();
        }else{
            getElementsFromXML(type);
        }
    }
    
    protected abstract void newMap ();

    protected void getElementsFromXML(String type) throws Exception{
        MapFromXML xMap = new MapFromXML(this.getIndexMap());
        String direct = getDirectory();
        xMap.getElements(direct+type);
    }

    protected abstract String getDirectory ();

    
    protected abstract void defaultElements () throws Exception;

    public abstract MapObservable<Integer, String> getIndexMap();
    public abstract void setAtIndex(int index, String value) throws Exception;
    
    public abstract String get(int key);

}
