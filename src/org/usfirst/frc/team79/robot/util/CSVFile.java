package org.usfirst.frc.team79.robot.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CSVFile {

	static final String s_lineSeparator = System.getProperty("line.separator");

	TreeMap<String, Object> items;
	String filename;

	public CSVFile(String filename) {
		// Init data structure
		items = new TreeMap<String, Object>();
		this.filename = filename;

		// Load items into hashmap
		load();
	}

	public void load() {
		try {
			// Load CSV file contents to hashmap
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String buffer = "";

			// Iterate over entire file
			while ((buffer = br.readLine()) != null) {
				// Remove unwanted newline separators
				buffer = buffer.replace(s_lineSeparator, "");

				// Parse for key and value
				String key = "";
				Object value = null;

				String[] temp = null;

				// Key (account for spaces)
				if (buffer.contains(",") && !buffer.contains(", ")) {
					temp = buffer.split(",");
					key = temp[0];
				} else if (buffer.contains(", ")) {
					temp = buffer.split(", ");
					key = temp[0];
				}

				// Value (account for type)
				// Determine the correct type
				if (temp[1].toLowerCase().equals("true")
						|| temp[1].toLowerCase().equals("false")) {
					// Boolean
					value = Boolean.parseBoolean(temp[1].toLowerCase());
				} else if (temp[1].startsWith("\"") && temp[1].endsWith("\"")) {
					// String
					value = temp[1].substring(1, temp[1].length() - 2); // Take
																		// off
																		// beginning
																		// and
																		// end
																		// quotes
				} else if (temp[1].equals("")) {
					// Empty String
					value = temp[1];
				} else {
					// Double
					value = Double.valueOf(temp[1]);
				}

				// Add corresponding pair to the keymap
				items.put(key, value);
			}

			fr.close();
		} catch (FileNotFoundException fe) {
			save();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {
		Thread saveThread;
		saveThread = new Thread() {
			@Override
			public void run() {

				try {
					// Create new file and add default values
					FileWriter fw = new FileWriter(filename);

					// Iterate through all keys and write current values to file
					Iterator<Entry<String, Object>> i = items.entrySet()
							.iterator();
					while (i.hasNext()) {
						Map.Entry<String, Object> entry = (Map.Entry<String, Object>) i
								.next();

						// Write values depending on the data type
						if (entry.getValue().getClass() == Boolean.class) {
							fw.write(entry.getKey()
									+ ", "
									+ Boolean.toString((Boolean) entry
											.getValue()) + s_lineSeparator);
						} else if (entry.getValue().getClass() == String.class) {
							fw.write(entry.getKey() + ", " + "\""
									+ (String) entry.getValue() + "\""
									+ s_lineSeparator);
						} else if (entry.getValue().getClass() == Double.class) {
							fw.write(entry.getKey()
									+ ", "
									+ Double.toString((Double) entry.getValue())
									+ s_lineSeparator);
						}
					}

					fw.flush();
					fw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		};
		saveThread.start();
	}

	public Object getValue(String key) {
		return items.get(key);
	}

	public void putValue(String key, Object value) {
		items.put(key, value);
	}

}
