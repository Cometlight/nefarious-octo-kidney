package at.fhv.itb5c.commons.property;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

import at.fhv.itb5c.logging.ILogger;

public class PropertyManager implements ILogger {
	private static PropertyManager _instance;
	private Properties _propertiesDefault;
	private Properties _propertiesUser;
	private final String _fileName = "config.properties";
	private final String _defaultFile = "config.properties";

	private PropertyManager() {
		// TODO where is the null error at startup from?
		_propertiesDefault = new Properties();
		_propertiesUser = new Properties();
		try {
			loadDefaultProperties();
			loadProperties();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	public static PropertyManager getInstance() {
		if (_instance == null) {
			_instance = new PropertyManager();
		}
		return _instance;
	}

	public String getProperty(String key) {
		String defaultProp = _propertiesDefault.getProperty(key);
		String userProp = null;

		if (_propertiesUser != null) {
			userProp = _propertiesUser.getProperty(key);
		}

		if (defaultProp == null && userProp == null) {
			log.error("property " + key + "not defined");
		}

		return (userProp == null) ? defaultProp : userProp;
	}

	private void loadProperties() throws IOException {
		log.debug("loading properties file " + _fileName);
		File props = new File(_fileName);
		if (props.exists()) {
			InputStream inputStream = Files.newInputStream(props.toPath(), StandardOpenOption.READ);

			if (inputStream != null) {
				_propertiesUser.load(inputStream);
			} else {
				log.info("couldn't load user properties file");
			}
		} else {
			log.info("no user properties file found (config.properties)");
		}
	}

	private void loadDefaultProperties() throws IOException {
		log.debug("loading default properties file " + _defaultFile);
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(_defaultFile);

		if (inputStream != null) {
			_propertiesDefault.load(inputStream);
		} else {
			log.error("no default properties file found");
			throw new IOException();
		}
	}
}