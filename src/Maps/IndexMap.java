package Maps;

import Observables.MapObservable;

public abstract class IndexMap {
    protected IndexMap() throws Exception{
        addElements("default");
    }
    
    public abstract void addElements(String type) throws Exception;

    public abstract MapObservable<Integer, String> getIndexMap();
    public abstract void setAtIndex(int index, String value) throws Exception;

    
}
