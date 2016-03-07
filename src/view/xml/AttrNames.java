package view.xml;

import java.util.Arrays;
import java.util.List;

public enum AttrNames {
	
	WORKSPACE(Arrays.asList("BGColor", "PenColor", "PLang", "CFile", "IFile", "NumTurts")),
	INDEX_MAP(Arrays.asList("index", "name"));
	
	private List<String> names;
	AttrNames(List<String> names){
		this.names=names;
	}
	
	public List<String> getNames(){
		return names;
	}
}
