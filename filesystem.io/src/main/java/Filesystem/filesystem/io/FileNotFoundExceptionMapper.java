package Filesystem.filesystem.io;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class FileNotFoundExceptionMapper implements ExceptionMapper<FileNotFoundException> {

	@Override
	public Response toResponse(FileNotFoundException ex) {
		ErrorMessage errMessage = new ErrorMessage(ex.getMessage(), 404, "File not found at the given location");
		return Response.status(Status.NOT_FOUND).entity(errMessage).build();
	}

}
