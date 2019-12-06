package org.kaushik.minda.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.kaushik.minda.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoudException>{
	
	@Override
	public Response toResponse(DataNotFoudException ex) {
		ErrorMessage errormessage=new ErrorMessage(ex.getMessage(),404,"http://slack.com/mindagroup");
		return Response.status(Status.NOT_FOUND)
				.entity(errormessage)
				.build();
	}
	
	

}
