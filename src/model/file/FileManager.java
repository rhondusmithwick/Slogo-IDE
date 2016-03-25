//This entire file is part of my masterpiece.
//Jonathan Ma
/*
 * This code acts as a file manager class for the Slogo application. It's used primarily to read from and write to specified
 * file locations. This is so that the user can store any user-defined variables by writing them to a file and then retrieving
 * them later by reading from that saved file. I think overall the code is pretty well designed. The methods that should be 
 * private are kept private and there are no magic numbers in the code. Originally, I had split up the read and write into
 * two separate classes, but I felt as though that code was a bit redundant and could be placed into one class. I also tried
 * to keep the variables in the class encapsulated, but as flexible as possible. 
 */
package model.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.usercontrol.Variable;
import observables.MapObservable;

public class FileManager {
	
	private static final String SEPARATOR = "=";
	private static final String NEWLINE = "\n";
	private FileWriter writer;
	private PrintWriter printWriter;
    private FileReader reader;
    private BufferedReader bufferedReader;
	private MapObservable<String, Variable> importedVariables;
	private String readPath;
	private String writePath;
	
	public FileManager(String path, boolean bool) throws IOException {
		setPath(path, bool);
	}
	
	private void setPath(String path, boolean bool) throws IOException {
		if (bool) {
			setReadPath(path);
		} else {
			setWritePath(path);
		}
	}
	
	public void setReadPath(String path) throws FileNotFoundException {
		this.readPath = path;
		this.reader = new FileReader(readPath);
		this.bufferedReader = new BufferedReader(reader);
	}
	
	public void setWritePath(String path) throws IOException {
		this.writePath = path;
		this.writer = new FileWriter(writePath);
		this.printWriter = new PrintWriter(writer);
	}
	
	public void importVariables(MapObservable<String, Variable> variables) {
		this.importedVariables = variables;
	}
	
    private String variablesToString() {
        StringBuilder result = new StringBuilder();
        for (String name : importedVariables.getMap().keySet()) {
            Variable var = importedVariables.get(name);
            String str = name + SEPARATOR + var.getValue();
            result.append(str + NEWLINE);
        }

        return result.toString();
    }
    
    public void writeToFile() throws IOException {
    	String text = variablesToString();
        printWriter.printf("%s" + NEWLINE, text);
        printWriter.close();
    }
    
    public MapObservable<String, Variable> read() throws IOException {
    	MapObservable<String, Variable> parsedVariables = new MapObservable<>("parsedVariables");
        String text = this.getFileString();
        String[] list = text.split(NEWLINE);
        for (int i = 0; i < list.length - 1; i++) {
            String[] temp = list[i].split(SEPARATOR);
            String variableName = temp[0];
            double value = Double.parseDouble(temp[1]);
            Variable var = new Variable();
            var.setValue(value);
            parsedVariables.put(variableName, var);
        }
        return parsedVariables;
    }

    private String getFileString() throws IOException {
        StringBuilder sb = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = bufferedReader.readLine();
        }
        return sb.toString();
    }

}
