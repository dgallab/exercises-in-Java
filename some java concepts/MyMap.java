package com.maps;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


public class MyMap {
	
	//Consists of key, value pairs. 
	// Each key has to be unique and can be only mapped to one value
	// A key can be mapped to a duplicate value
	
	//Methods
	// Basic Operations:
	//	put, get, remove, containsKey, containsValue, size and empty
	//Bulk Operations:
	//	putAll, clear
	//Collection Views:
	// keySet, entrySet and values. 
	
	
	// All MAPS don't allow DUPLICATE keys
	public static void main(String[] args) {
		
		Map<Integer, String> myMap = new HashMap<>();
		/*
		 * Allows Duplicates values, not duplicate keys
		 * Allows a single null key, and it allows multiple null values
		 * Does not guarantee order 
		 */
		Map<List<String>, List<String>> myOtherMap = new TreeMap<>(); //This is just unfair
		/*
		 *Does not allow null keys, but does allow null values.
		 *Sorted according to the natural ordering of the keys. 
		 */
		Map<Integer, String> myOtherOtherMap = new LinkedHashMap<>();
		/*
		 * It maintains insertion order 
		 */
		
		myMap.put(7, "This is my first value");	
		myMap.put(42, "The answer to everything");
		myMap.put(21, "Today");
		
//		myOtherOtherMap.put("Apple", "Red spherical thing, sometimes green");
		
//		List<String> myKeyList = new ArrayList<>();
//		myKeyList.add("MyKey");
//		myKeyList.add("My Backup key");
//		
//		List<String> myValueList = new ArrayList<>();
//		myValueList.add("Here's my value list");
//		
//		myOtherMap.put(myKeyList, myValueList);
		
//		System.out.println(myMap);
//		System.out.println(myMap.get(7));
//		System.out.println(myMap.remove(7));
//		System.out.println(myMap);
		
//		System.out.println(myMap.keySet());
//		System.out.println(myMap.values());
//		
//		myMap.put(42, null);
//		myMap.put(null, "This is a null valyue");
//		System.out.println(myMap.get(1234) + ": returning the value of key 1234"); //Access key value pair, if key doesn't exist, return null 
//		System.out.println(myMap);
//		
//		System.out.println(myMap.size()); //returns size 
//		System.out.println(myMap.isEmpty()); //Checks if empty 
//		
//		myOtherOtherMap.put(42, "I'm not null"); //put in a key for 42, will be overwritten with add all
//		
//		myOtherOtherMap.putAll(myMap); //Similar to putAll, adds submap into map
//		System.out.println(myOtherOtherMap);
//		myOtherOtherMap.clear(); //Clears all the entries in a map
//		System.out.println(myOtherOtherMap); 
		
		Set<Integer> myKeySet = myMap.keySet(); //Create a set to allow it to iterate 
		
//		for(Integer i: myKeySet) { //Iterate through the set 
//			System.out.println(myMap.get(i)); //Get the values
//		}
//		
//		for(int i: myMap.keySet()) {
//			System.out.println(myMap.get(i));
//		}
//		
//		for( String myValues: myMap.values()) {
//			System.out.println(myValues);
//		}
		
		Set<Entry<Integer,String>> myPairs = myMap.entrySet();
		
		for(Entry<Integer,String> i: myPairs) {
			System.out.println(i);
		}
		
	}

}
