import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Logger {

	private static String path = null;

	
	public static void log(String key, String value) throws IOException {
		File file = new File(path);

	    if (!file.exists()) {
	    	file.createNewFile();
	    }

	    FileWriter fw = new FileWriter(file.getAbsoluteFile());
	    BufferedWriter bw = new BufferedWriter(fw);
	    bw.write(key+":"+value);
	    bw.close();
	    
	}
	
	public static void setPath(String filepath) {
	    path = filepath;
	}
	
}
