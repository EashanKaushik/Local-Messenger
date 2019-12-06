package org.kaushik.minda.messenger.service;

import java.util.ArrayList;
import java.util.Map;

import org.kaushik.minda.messenger.database.DatabaseClass;
import org.kaushik.minda.messenger.model.Profile;

public class ProfileService {
	private Map<String,Profile> profiles=DatabaseClass.getProfile();

	public ProfileService(){
		profiles.put("Kaushik",new Profile(1L,"kaushik","kauhik","kalop"));
	}
	
	public ArrayList<Profile> getAllProfiles(){
		return  new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(),profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(),profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}
	

}
