package model.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.usercontrol.Variable;
import observables.MapObservable;

public class SaveFile {
	
	private final String path;
	
	public SaveFile(String path) {
		this.path = path;
	}
	
	public String variablesToText(MapObservable<String, Variable> variables) {
		StringBuilder result = new StringBuilder();
		
		for (String name : variables.getMap().keySet()) {
			Variable var = variables.get(name);
			double value = var.getValue();
			String str = name + "=" + value;
			result.append(str + "\n");
		}
		
		return result.toString();
	}
	
	public void writeToFile(String text) throws IOException {
		FileWriter write = new FileWriter(path);
		PrintWriter print = new PrintWriter(write);
		
		print.printf("%s" + "\n", text);
		print.close();
	}
}
