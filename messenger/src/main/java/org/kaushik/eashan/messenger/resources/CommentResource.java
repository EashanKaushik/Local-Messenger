package org.kaushik.minda.messenger.resources;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.kaushik.minda.messenger.model.Comment;
import org.kaushik.minda.messenger.service.CommentService;

@Path("/")
@Consumes(value= {MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
@Produces(value= {MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public class CommentResource {
	
	private CommentService commentservice=new CommentService();
	
	@GET	
	public List<Comment> getAllComments(@PathParam("messageId") long messageId){
		return commentservice.getAllComments(messageId);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId,@PathParam("commentId") long commentId,Comment comment) {
		comment.setId(commentId);
		return commentservice.updateComment(messageId,comment);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") long messageId,Comment comment){
		return commentservice.addComment(messageId,comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messageId") long messageId,@PathParam("commentId") long commentId) {
		commentservice.removeComment(messageId,commentId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId,@PathParam("commentId") long commentId){
		return commentservice.getComment(messageId,commentId);
	}
	
}
