package org.kaushik.minda.messenger.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kaushik.minda.messenger.model.Profile;
import org.kaushik.minda.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(value= {MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
@Produces(value= {MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public class ProfileResource {
	ProfileService profileservice=new ProfileService();
	
	@GET	
	public ArrayList<Profile> getProfile(){
		return profileservice.getAllProfiles();
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName,Profile profile) {
		profile.setProfileName(profileName);
		return profileservice.updateProfile(profile);
	}
	
	@POST
	public Profile addProfile(Profile profile){
		return profileservice.addProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName) {
		profileservice.removeProfile(profileName);
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName){
		return profileservice.getProfile(profileName);
	}
	

}
