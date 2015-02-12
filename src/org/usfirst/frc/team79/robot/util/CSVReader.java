package org.usfirst.frc.team79.robot.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class CSVReader {

	HashMap<String, Object> items;

	public CSVReader(String filename) throws Exception {
		// Init data structure
		items = new HashMap<String, Object>();

		// Load CSV file contents to hashmap
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String buffer = "";
		
		// Iterate over entire file
		while((buffer = br.readLine()) != null){
			// Remove unwanted newline separators
			buffer = buffer.replace(System.getProperty("line.separator"), "");

			// Parse for key and value
			String key = "";
			Object value = null;
			
			String[] temp = null;
			
			// Key (account for spaces)
			if(buffer.contains(",") && !buffer.contains(", "))
            {
                temp = buffer.split(",");
                key = temp[0];
            }
            else if(buffer.contains(", "))
            {
                temp = buffer.split(", ");
                key = temp[0];
            }
			
			// Value (account for type)
			// Determine the correct type
            if(temp[1].toLowerCase().equals("true") || temp[1].toLowerCase().equals("false"))
            {
                // Boolean
                value = Boolean.parseBoolean(temp[1].toLowerCase());
            }
            else if(temp[1].startsWith("\"") && temp[1].endsWith("\""))
            {
                // String
                value = temp[1].substring(1, temp[1].length() - 2); // Take off beginning and end quotes
            }
            else if(temp[1].equals(""))
            {
                // Empty String
                value = temp[1];
            }
            else
            {
                // Double
                value = Double.valueOf(temp[1]);
            }
            
            // Add corresponding pair to the keymap
            items.put(key, value);
		}
	}
	
	public Object getValue(String key){
		return items.get(key);
	}
}
