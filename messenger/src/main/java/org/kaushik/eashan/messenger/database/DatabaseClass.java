package org.kaushik.minda.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.kaushik.minda.messenger.model.Message;
import org.kaushik.minda.messenger.model.Profile;

public class DatabaseClass {
	
	private static Map<Long,Message> messages= new HashMap<>();
	private static Map<String,Profile> profiles= new HashMap<>();
	
	public static Map<Long,Message> getMessages(){
		return messages;
	}
	
	public static Map<String,Profile> getProfile(){
		return profiles;	
	}



}
