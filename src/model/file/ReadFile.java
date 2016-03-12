package model.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.usercontrol.Variable;
import observables.MapObservable;

public class ReadFile {
	
	private final String path;
	
	public ReadFile(String path) {
		this.path = path;
	}
	
	public MapObservable<String, Variable> getVariables() throws IOException {
		MapObservable<String, Variable> variables = new MapObservable<>("variables");
		String text = readFromFile();
		String[] list = text.split("\n");
		for (int i = 0; i < list.length-1; i++) {
			String[] temp = list[i].split("=");
			String variableName = temp[0];
			double value = Double.parseDouble(temp[1]);
			Variable var = new Variable();
			var.setValue(value);
			variables.put(variableName, var);
		}
		
		return variables;
	}
	
	@SuppressWarnings("resource")
	private String readFromFile() throws IOException {
		FileReader read = new FileReader(path);
		BufferedReader br = new BufferedReader(read);
		
		StringBuilder sb = new StringBuilder();
		
		String line = br.readLine();
		
		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
		}
		return sb.toString();
	}
}
