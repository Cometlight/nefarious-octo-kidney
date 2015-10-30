package at.fhv.itb5c.commons.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import at.fhv.itb5c.logging.ILogger;

public class PropertyManager implements ILogger {
	private PropertyManager _instance;
	private Properties _properties;
	private InputStream _inputStream;
	private final String _fileName = "config.properties";

	private PropertyManager() {
		_inputStream = getClass().getClassLoader().getResourceAsStream(_fileName);

		try {
			if (_inputStream != null) {
				_properties.load(_inputStream);
			} else {
				// TODO create file if doesnt exist				
			}
		} catch (IOException e) {

		}
	}

	public PropertyManager getInstance() {
		if (_instance == null) {
			_instance = new PropertyManager();
		}
		return _instance;
	}

	public String getProperty(String key) {
		return _properties.getProperty(key);
	}
}