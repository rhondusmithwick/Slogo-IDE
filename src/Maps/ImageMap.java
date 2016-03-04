package Maps;

import java.io.File;
import Observables.MapObservable;
import view.Defaults;

public class ImageMap extends IndexMap{

    private MapObservable<Integer, String> images;
    private static ImageMap instance;
    private int count;

    private ImageMap() throws Exception{
        super();
    }




    public static synchronized ImageMap getInstance() throws Exception 
    {
        if (instance == null){
            instance = new ImageMap();
        }
        return instance;
    }

    @Override
    public MapObservable<Integer, String> getIndexMap () {
        return images;
    }

    @Override
    public void setAtIndex (int index, String value) {
        images.put(index, value);
        images.notifyObservers();

    }



    @Override
    protected void defaultElements () {
        count = 0;
        File directory = new File(Defaults.IMAGE_LOC.getDefault());
        File[] fList = directory.listFiles();
        for (File file : fList) {
            String name = file.getName();
            images.put(count, Defaults.IMAGE_LOC.getDefault()+name);
            count++;
        }
        
    }




    @Override
    protected void newMap () {
        images = new MapObservable<>("images");
        
    }




	@Override
	public String get(int key) {
		return images.get(key);
	}




    @Override
    protected String getDirectory () {
        return Defaults.IMAGELIST_LOC.getDefault();
    }


}
