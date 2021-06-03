package app.helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	Properties properties;

	public PropertyReader(String PropertyFilePath) {
		try {
			FileInputStream fileInputStream = new FileInputStream(PropertyFilePath);

			properties = new Properties();
			properties.load(fileInputStream);	

			fileInputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String get(String PropertyName) {
		return properties.getProperty(PropertyName);
	}
}
