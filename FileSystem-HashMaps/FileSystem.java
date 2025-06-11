/*
 * @author Justin DeGraaf
 * email: jdegraaf@ucsd.edu
 * 
 * Description: This class creates two different file systems which implementing HashMaps I made. 
 * 				The nameMap stores files in a HashMap that has the name of the file as the key,
 * 				while dateMap stores files in a HashMap that has the files date as the key.
 * 				Each have FileData as the HashMap value. Also in this file are methods to perform
 * 				tasks on each Map like adding and removing from it.
 */

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSystem {

    MyHashMap<String, ArrayList<FileData>> nameMap;
    MyHashMap<String, ArrayList<FileData>> dateMap;

    // Default constructor setting instance variables
    public FileSystem() {
    	nameMap = new MyHashMap<String, ArrayList<FileData>>();
    	dateMap = new MyHashMap<String, ArrayList<FileData>>();
    }

    // Constructor that creates a new file system with a given input file
    // @param inputFile is the file with information to be added to each hashMap
    public FileSystem(String inputFile) {
    	this.nameMap = new MyHashMap<String, ArrayList<FileData>>();
    	this.dateMap = new MyHashMap<String, ArrayList<FileData>>();
        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                add(data[0], data[1], data[2]);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * This method adds a FileData object with the given information to each hashMap
     *@param fileName is the name of a FileData object we want to add
     *@param directory is the directory of a FileData object we want to add
     *@param modifiedDate is the date of a FileData object we want to add
     *@return true if it adds succesfully
	 */
    public boolean add(String fileName, String directory, String modifiedDate) {
    	
    	if(!dateMap.isEmpty()) {
    		for(String data : dateMap.keys()) {
    			for(FileData keys: dateMap.get(data)) {
    				if((keys.name.equals(fileName)) && (keys.name.equals(directory))) {
    					return false;
    				}
    			}
    		}
    	}

    	if(nameMap.get(fileName) != null) {
    		for(FileData entry2 : nameMap.get(fileName)) {
    			if ((entry2.name.equals(fileName)) && (entry2.dir.equals(directory))) {
    				return false;
    			}
    		}
    	}
    	
    	ArrayList<FileData> dateInputFiles = dateMap.get(modifiedDate);
    	if(dateInputFiles == null) {
    		dateInputFiles = new ArrayList<>();
    	}
    	FileData inputFile = new FileData(fileName, directory,modifiedDate);
    	boolean dateStatus = false;
    	if(addToArray(dateInputFiles, inputFile)) {
    		dateMap.set(modifiedDate, dateInputFiles);
    		dateStatus = dateMap.get(modifiedDate).equals(dateInputFiles);
    	}
    	ArrayList<FileData> nameInputFiles = nameMap.get(fileName);
    	if(nameInputFiles == null) {
    		nameInputFiles = new ArrayList<>();
    	}
    	FileData inputFile2 = new FileData(fileName, directory, modifiedDate);
    	boolean nameStatus = false;
    	if(addToArray(nameInputFiles, inputFile2)) {
    		nameMap.set(fileName, nameInputFiles);
    		nameStatus = nameMap.get(fileName).equals(nameInputFiles);
    	}
    	return (dateStatus && nameStatus);
    }

    /**
     * This method finds a FileData within the hashMaps by going through nameMap
     *@param name is the name of a FileData object we want to find
     *@param directory is the directory of a FileData object we want to find
     *@return the found FileData, if not found, null is returned
	 */
    public FileData findFile(String name, String directory) {
    	ArrayList<FileData> returnFiles = nameMap.get(name);
    	if(returnFiles == null) {
    		return null;
    	}
    	for(FileData file: returnFiles) {
    		if(file.dir.equals(directory)) {
    			return file;
    		}
    	}
    	return null;
    }

    /**
     * This method gets unique file names
     * @return an ArrayList with the unique file names, if there are none it returns an empty list
	 */
    public ArrayList<String> findAllFilesName() {
    	ArrayList<String> unique = new ArrayList<>(nameMap.keys());
    	return unique;
    }

    /**
     * This method gets all files with the given name
     * @param name is the name of the files we want to display
     * @return an ArrayList with the values of the desired name, if there are none it returns
     * an empty list
	 */
    public ArrayList<FileData> findFilesByName(String name) {
    	if(nameMap.get(name) == null){
    		ArrayList<FileData> names1 = new ArrayList<FileData>();
    		return names1;
    	}
    	ArrayList<FileData> names = new ArrayList<>(nameMap.get(name));
    	return names;
    }

    /**
     * This method returns a list of FileData with the given date
     * @param modifiedDate is the date we want to find within the hashMaps
     * @return an ArrayList of FileData contining the modifiedDate, returns and empty array if
     * the desired date isn't found 
	 */
    public ArrayList<FileData> findFilesByDate(String modifiedDate) {
    	if(dateMap.get(modifiedDate) == null){
    		ArrayList<FileData> dates1 = new ArrayList<FileData>();
    		return dates1;
    	}
    	ArrayList<FileData> dates = new ArrayList<>(dateMap.get(modifiedDate));
    	return dates;
    }

    /**
     * This method returns a list of FileData with the given date that has at least another
     * appearance in different directoy
     * @param modifiedDate is the date we want to find within the hashMaps
     * @return an ArrayList of FileData contining the modifiedDate with multiple appearances, 
     * returns and empty array if the desired date isn't found multiple times
	 */
    public ArrayList<FileData> findFilesInMultDir(String modifiedDate) {
    	ArrayList<FileData> dateFiles = dateMap.get(modifiedDate);
    	if(dateFiles == null) {
    		return new ArrayList<FileData>();
    	}
    	LinkedHashSet<FileData> returnFiles = new LinkedHashSet<>();
    	for(FileData file : dateFiles) {
    		ArrayList<FileData> filesToCheck = nameMap.get(file.name);
    		for(FileData checkFile : filesToCheck) {
    			if(checkFile.name == file.name && checkFile.dir != file.dir) {
    				returnFiles.add(file);
    				returnFiles.add(checkFile);
    			}
    		}
    	}
    	return new ArrayList<>(returnFiles);
    }
    /**
     * This is a helper method that checks if the FileData being added into the array is unique
     * @param array is the ArrayList we want to add to and use to track unique FileData's
     * @param fileToAdd is the FileData we want to see if it's unique or not
     * @return true if it is unique, false if not
	 */
    private boolean addToArray (ArrayList<FileData> array, FileData fileToAdd) {
    	try {
    		for(FileData file : array) {
    			if(fileToAdd.equalsFile(file)) {
    				return false;
    			}
    		}
    	}catch(Exception e) {
    		return false;
    	}
    	return array.add(fileToAdd);
    }
    
    /**
     * This method removes a file from the tracking ArrayList
     * @param array is the ArrayList we want to remove from
     * @param fileToRemove is the file in the array which we want to remove
     * @return true if the file was removed/present, false if not
	 */
    private boolean removeArrayList (ArrayList<FileData> array, FileData fileToRemove) {
    	if(array.isEmpty()) {
    		return false;
    	}
    	for(FileData file : array) {
    		if(file.equalsFile(fileToRemove)) {
    			array.remove(file);
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * This method removes all files in the hashMaps with the given name
     * @param name is the name of files we want to remove
     * @return true if the file(s) with the name was removed/present, false if not
	 */
    public boolean removeByName(String name) {
    	ArrayList<FileData> nameFiles = nameMap.get(name);
    	if(nameFiles == null || name == null) {
    		return nameMap.remove(name);
    	}
    	try {
    		for(FileData file : nameFiles) {
    			removeArrayList(dateMap.get(file.lastModifiedDate), file);
    			if(dateMap.get(file.lastModifiedDate).isEmpty()) {
    				dateMap.remove(file.lastModifiedDate);
    			}
    		}
    	}catch(Exception e) {}
    	return nameMap.remove(name);
    }

    /**
     * This method removes a file with the given name and directory
     * @param name is the name of the file we want to remove
     * @param directory is the directory of the file we want ot remove
     * @return true if the file was removed/present, false if not
	 */
    public boolean removeFile(String name, String directory) {
    	FileData removedFile = null;
    	ArrayList<FileData> removeFiles = nameMap.get(name);
    	if(removeFiles == null || name == null || directory == null) {
    		return false;
    	}
    	try {
    		for(FileData file : removeFiles) {
    			if(file.name.equals(name) && file.dir.equals(directory)) {
    				removedFile = file;
    				break;
    			}
    		}
    	}catch(Exception e) {
    		return false;
    	}
    	if(removedFile == null) {	
    		
    	}else if(removeArrayList(removeFiles, removedFile)) {
    		if(removeFiles.isEmpty()) {
    			nameMap.remove(name);
    		}
    		removeFiles = dateMap.get(removedFile.lastModifiedDate);
    		removeArrayList(removeFiles, removedFile);
    		if(removeFiles.isEmpty()) {
    			dateMap.remove(removedFile.lastModifiedDate);
    		}
    		return true;
    	}
    	return false;
    	
    }

}
