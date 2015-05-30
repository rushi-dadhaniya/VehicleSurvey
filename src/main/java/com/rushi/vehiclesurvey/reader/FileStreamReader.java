package com.rushi.vehiclesurvey.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStreamReader {

	public List<String> read(String path) {

		List<String> lines = new ArrayList<String>();
		
		if(path != null) {
			
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
				String line;
				while((line = bufferedReader.readLine()) != null) {
					lines.add(line);
				}
				bufferedReader.close();
				return lines;
				
			}
			catch(IOException exception) {
				exception.printStackTrace();
			}
		}
		return null;
	}

}
