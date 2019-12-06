package org.kaushik.minda.messenger.resources;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDomainResource {

	@GET
	@Path("annotations")
	public String getParamUsingAnnotations(@MatrixParam("param") String matrixparam,@HeaderParam("authSessionID") String header, @CookieParam("name") String cookie){
	return "Matric param:"+matrixparam+"Header Param:"+ header + "cookie param:"+cookie;
	}
	
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriinfo,@Context HttpHeaders headers) {
		
		String path= uriinfo.getAbsolutePath().toString();
		String cookies=headers.getCookies().toString();
		return "Path:"+ path+" Cookies:"+cookies;
	}
}