package com.zavod.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utilities to support and simplify examples.
 */
public class AuthenticationUtilities {

	private static String connectionUri = "xmldb:exist://%1$s:%2$s/exist-korisnici/xmlrpc";

	/**
	 * Connection parameters.
	 */
	static public class ExistConnectionProperties {

		public String host;
		public int port = -1;
		public String user;
		public String password;
		public String driver;
		public String uri;

		public ExistConnectionProperties(Properties props) {
			super();

			user = props.getProperty("conn.user").trim();
			password = props.getProperty("conn.password").trim();

			host = props.getProperty("conn.host").trim();
			port = Integer.parseInt(props.getProperty("conn.port"));

			uri = String.format(connectionUri, host, port);

			driver = props.getProperty("conn.driver").trim();
		}
	}

	/**
	 * Read the configuration properties for the example.
	 *
	 * @return the configuration object
	 */
	public static ExistConnectionProperties loadExistProperties() {
		String propsName = "exist.properties";

		InputStream propsStream = null;
		try {
			propsStream = openStream(propsName);
			if (propsStream == null)
				throw new IOException("Could not read properties " + propsName);
			Properties props = new Properties();
			props.load(propsStream);

			return new ExistConnectionProperties(props);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	static public class FusekiConnectionProperties {

		public String endpoint;
		public String dataset;

		public String queryEndpoint;
		public String updateEndpoint;
		public String dataEndpoint;


		public FusekiConnectionProperties(Properties props) {
			super();
			dataset = props.getProperty("conn.dataset").trim();
			endpoint = props.getProperty("conn.endpoint").trim();

			queryEndpoint = String.join("/", endpoint, dataset, props.getProperty("conn.query").trim());
			updateEndpoint = String.join("/", endpoint, dataset, props.getProperty("conn.update").trim());
			dataEndpoint = String.join("/", endpoint, dataset, props.getProperty("conn.data").trim());

			System.out.println("[INFO] Parsing connection properties:");
			System.out.println("[INFO] Query endpoint: " + queryEndpoint);
			System.out.println("[INFO] Update endpoint: " + updateEndpoint);
			System.out.println("[INFO] Graph store endpoint: " + dataEndpoint);
		}
	}

	/**
	 * Read the configuration properties for the example.
	 *
	 * @return the configuration object
	 */
	public static FusekiConnectionProperties loadFusekiProperties() throws IOException {
		String propsName = "connection.properties";

		InputStream propsStream = openStream(propsName);
		if (propsStream == null)
			throw new IOException("Could not read properties " + propsName);

		Properties props = new Properties();
		props.load(propsStream);

		return new FusekiConnectionProperties(props);
	}

	/**
	 * Read a resource for an example.
	 *
	 * @param fileName
	 *            the name of the resource
	 * @return an input stream for the resource
	 * @throws IOException
	 */
	public static InputStream openStream(String fileName) throws IOException {
		return AuthenticationUtilities.class.getClassLoader().getResourceAsStream(fileName);
	}

}
