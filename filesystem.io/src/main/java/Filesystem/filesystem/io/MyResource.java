
package Filesystem.filesystem.io;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
public class MyResource {

	final static Charset ENCODING = StandardCharsets.UTF_8;

	final static String FILE_PATH_FOLDER = "E:\\temp\\";

	@GET
	@Produces("text/plain")
	@Consumes("text/plain")
	public String readFile(@QueryParam("fileName") String filename) throws FileNotFoundException {
		java.nio.file.Path path = Paths.get(FILE_PATH_FOLDER + filename);

		try {
			return Files.readAllLines(path).toString();
		} catch (IOException e) {
			throw new FileNotFoundException("File with path " + path + " not found");
		}

	}

	@POST
	@Produces("text/plain")
	@Consumes("text/plain")
	public String createFile(@QueryParam("fileName") String filename, @QueryParam("fileText") String fileText)
			throws FileNotFoundException {
		java.nio.file.Path path = Paths.get(FILE_PATH_FOLDER + filename);
		try {
			Files.write(path, fileText.getBytes(), StandardOpenOption.CREATE);
			return readFile(filename);
		} catch (IOException e) {
			throw new FileNotFoundException("File with path " + path + " not found");
		}
	}

	@PUT
	@Produces("text/plain")
	@Consumes("text/plain")
	public String updateFile(@QueryParam("fileName") String filename, @QueryParam("fileText") String fileText)
			throws FileNotFoundException {
		java.nio.file.Path path = Paths.get(FILE_PATH_FOLDER + filename);
		try {
			Files.write(path, fileText.getBytes(), StandardOpenOption.APPEND);
			return readFile(filename);
		} catch (IOException e) {
			throw new FileNotFoundException("File with path " + path + " not found");
		}
	}

	@DELETE
	@Produces("text/plain")
	@Consumes("text/plain")
	public boolean deleteFile(@QueryParam("fileName") String filename) throws FileNotFoundException {
		java.nio.file.Path path = Paths.get(FILE_PATH_FOLDER + filename);
		try {
			return Files.deleteIfExists(path);
		} catch (IOException e) {
			throw new FileNotFoundException("File with path " + path + " not found");
		}
	}
}
