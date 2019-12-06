package org.kaushik.minda.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.kaushik.minda.messenger.model.Message;
import org.kaushik.minda.messenger.resources.beans.MessageFilterBean;
import org.kaushik.minda.messenger.service.MessageService;

@Path("/messages")
@Consumes(value= {MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
@Produces(value= {MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public class MessageResource {
	
	
	MessageService messageservice=new MessageService();
	
	@GET	
	public List<Message> getMessage(@BeanParam MessageFilterBean filterbeam){
		if(filterbeam.getYear()>0) {
			return messageservice.getAllMessagesForYear(filterbeam.getYear());
		}
		if(filterbeam.getStart() >=0 && filterbeam.getSize()>=0) {
			return messageservice.getAllMessagesPaginated(filterbeam.getStart(), filterbeam.getSize());
		}
		return messageservice.getAllMessages();
	}
	
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId,Message message) {
		message.setId(messageId);
		return messageservice.updateMessage(message);
	}
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriinfo) throws URISyntaxException{
		
		Message newMessage=messageservice.addMessage(message);
		String newId=String.valueOf(newMessage.getId());
		URI uri=uriinfo.getAbsolutePathBuilder().path(newId).build();
		
		return Response.created(uri).entity(newMessage).build();
		}
	 
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId) {
		messageservice.removeMessage(messageId);
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId,@Context UriInfo uriinfo){
	Message message = messageservice.getMessage(messageId);
	message.addLink(getUriForSelf(uriinfo, message), "Self");
	message.addLink(getUriForProfile(uriinfo, message), "Profile");
	message.addLink(getUriForComments(uriinfo, message), "Comments");
	
	return message;
	}


	private String getUriForComments(UriInfo uriinfo, Message message) {

		URI uri = uriinfo.getBaseUriBuilder().path(MessageResource.class).path(MessageResource.class, "getCommentResource").path(CommentResource.class).resolveTemplate("messageId", message.getId()).build();
		return uri.toString();	}


	private String getUriForProfile(UriInfo uriinfo, Message message) {

		URI uri = uriinfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build();
		return uri.toString();
	}


	private String getUriForSelf(UriInfo uriinfo, Message message) {
		String uri=uriinfo.getBaseUriBuilder().path(MessageResource.class).path(Long.toString(message.getId())).build().toString();
		return uri;
	}
		
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
	
}


























