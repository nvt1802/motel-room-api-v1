package tainv13.app.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UploadCommon {
	public static String getPath() {
		try (InputStream input = UploadCommon.class.getClassLoader().getResourceAsStream("upload.properties")) {
			Properties prop = new Properties();
			prop.load(input);
			return prop.getProperty("path");
		} catch (IOException ex) {
			return ex.getMessage();
		}
	}

	public static String getMaxFileSize() {
		try (InputStream input = UploadCommon.class.getClassLoader().getResourceAsStream("upload.properties")) {
			Properties prop = new Properties();
			prop.load(input);
			return prop.getProperty("max-file-size");
		} catch (IOException ex) {
			return ex.getMessage();
		}
	}

	public static String getMaxRequestSize() {
		try (InputStream input = UploadCommon.class.getClassLoader().getResourceAsStream("upload.properties")) {
			Properties prop = new Properties();
			prop.load(input);
			return prop.getProperty("max-request-size");
		} catch (IOException ex) {
			return ex.getMessage();
		}
	}
}
