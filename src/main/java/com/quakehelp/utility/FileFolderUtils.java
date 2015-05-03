package com.quakehelp.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileFolderUtils {

	

	
	public static void createFolder(String folder) {

		File file = new File(folder);
		if (file.exists()) {
			return;
		}
		boolean check = file.mkdirs();
		if (check) {
		} else {
		}

	}

	/**
	 * This method returns list of only files in a directory Doesn't return
	 * directories
	 */
	public static List<File> getAllFiles(String inputDir) {

		List<File> onlyFiles = new ArrayList<File>();
		File curDirOrFiles = new File(inputDir);
		File[] listOfItems = curDirOrFiles.listFiles();

		if (listOfItems != null && listOfItems.length > 0) {
			for (File curFileOrDir : listOfItems) {
				if (curFileOrDir.isFile()) {
					onlyFiles.add(curFileOrDir);
				}
			}
		}

		return onlyFiles;

	}

	public static List<File> getAllDirectories(String inputDir) {

		List<File> onlyDirectories = new ArrayList<File>();
		File clientFolder = new File(inputDir);
		File[] listOfFolders = clientFolder.listFiles();

		for (File file : listOfFolders) {
			if (file.isDirectory()) {
				onlyDirectories.add(file);
			}
		}

		return onlyDirectories;

	}

	/**
	 * This method writes the list of String into file specified
	 */
	public static void writeToFile(List<String> lines, String fileName) {

		try {
			FileWriter fstream = new FileWriter(fileName);
			BufferedWriter out = new BufferedWriter(fstream);
			for (String line : lines) {
				out.write(line);
			}
			out.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

}
