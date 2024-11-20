package processing;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataRead {
	
	public ArrayList<KOL> runDataRead() {
		ArrayList<KOL> listKOL = new ArrayList<>();
    	
    	URL url = DataRead.class.getProtectionDomain().getCodeSource().getLocation();
    	String parentPath = url.getPath();     	
    	File parentFile = new File(parentPath);
    	String dataPath = parentFile.getPath() + "\\data\\KOLdata.txt";
    	
    	ArrayList<String> listStr = new ArrayList<>();
    	
    	try {
	    	File dataFile = new File(dataPath); 		    	
	    	Scanner scanner = new Scanner(dataFile);
	    	
	    	while (scanner.hasNextLine()) {
	    		String str = scanner.nextLine();
	    		if (str.length() != 0) {
	    			listStr.add(str);
	    		}
	    	}
	    	
	    	scanner.close();
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}    	
    	
    	int index = 0;
    	
    	while (index < listStr.size()) {
    		ArrayList<String> listFollowers = new ArrayList<>();
    		ArrayList<Tweet> listTweets = new ArrayList<>();
    		
    		String nameKOL = listStr.get(index++);
    		
    		int numberFollowers = Integer.parseInt(listStr.get(index++));
    		
    		while ((numberFollowers--) > 0) {
    			listFollowers.add(listStr.get(index++));
    		}
    		
    		int numberTweets = Integer.parseInt(listStr.get(index++));
    		
    		while ((numberTweets--) > 0 ){
    			String tweetLink = "", tweetOwner = "";
    			String temp = listStr.get(index++);
    			int type = 0;
    			for (int i = 0; i < temp.length(); ++i) {
    				char c = temp.charAt(i);
    				if (c == ' ') {
    					type = 1;
    				} else if (type == 1) {
    					tweetLink += c;
    				} else {
    					tweetOwner += c;
    				}
    			}
    			listTweets.add(new Tweet(tweetLink, tweetOwner));
    		}
    		listKOL.add(new KOL(nameKOL, listFollowers, listTweets));
//    		if (listKOL.size() == 10) break;
    	}
    	
//    	for (int i = 0 ; i < listKOL.size(); ++i) {
//    		System.out.println(listKOL.get(i).getNameKOL());
//    		System.out.println("-> Number Followers: " + listKOL.get(i).getListFollowers().size());
//    		System.out.println("-> Number Tweets: " + listKOL.get(i).getListTweets().size());
//    	}
    	
    	return listKOL;
	}
	
}
