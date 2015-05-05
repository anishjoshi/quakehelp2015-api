package com.quakehelp.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistrictVillageMapper {
	
	@Autowired
	private FileFolderUtils fileFolderUtils;
	
	BufferedReader br = null;
	String villages = null;	
	List<File> districts = null; 
	static HashMap<String, String> villageDistrictMap = new HashMap<String, String>();
	static HashMap<String, String> districtMap = new HashMap<String, String>();
	Logger logger = LoggerFactory.getLogger(DistrictVillageMapper.class);
	
	public void mapDistrictVillage() {
		URL checkUrl = DistrictVillageMapper.class.getResource("/districts");
		File path = null;
		try {
			path = new File(checkUrl.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		districts = fileFolderUtils.getAllFiles(path.getAbsolutePath());
		
		for (File file : districts) {
			try {

				String sCurrentLine;

				br = new BufferedReader(new FileReader(file));

				while ((sCurrentLine = br.readLine()) != null) {
					villages = sCurrentLine;
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}

			String[] splitVillage = villages.split(", ");

			logger.info("Setting village district map.");
			for (String village : splitVillage) {
				villageDistrictMap.put(village.toLowerCase(), file.getName());
			}
			logger.info("Setting district map.");
			districtMap.put(file.getName().toLowerCase(), file.getName());
		}
	}
}
