
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import Filesystem.filesystem.io.FileNotFoundException;
import Filesystem.filesystem.io.MyResource;

import org.junit.Assert;
import org.junit.Before;

@RunWith(MockitoJUnitRunner.class)
public class FileSystemMockitoTest {

	@InjectMocks
	MyResource myResource = new MyResource();

	@Mock
	MyResource myResourceMockService;

	@Test
	public void testMyResourceMethod() throws FileNotFoundException {
		testCreateFile();
		testReadFile();
		testUpdateFile();
		testDeleteFile();

	}

	public void testCreateFile() throws FileNotFoundException {
		when(myResourceMockService.createFile("welcome.txt", "created new File")).thenReturn("[created new File]");
		Assert.assertEquals(myResource.createFile("welcome.txt", "created new File"), "[created new File]");
		//verify(myResourceMockService).createFile("welcome.txt", "created new File");
	}

	public void testReadFile() throws FileNotFoundException {
		when(myResourceMockService.readFile("welcome.txt")).thenReturn("[created new File]");
		Assert.assertEquals(myResource.readFile("welcome.txt"), "[created new File]");
		//verify(myResourceMockService, times(1)).readFile("welcome.txt");

	}

	public void testUpdateFile() throws FileNotFoundException {
		when(myResourceMockService.updateFile("welcome.txt", " updated")).thenReturn("[created new File updated]");
		Assert.assertEquals(myResource.updateFile("welcome.txt", " updated"), "[created new File updated]");
	//	test on et branch verify(myResourceMockService, times(1)).updateFile("welcome.txt", "[created new File updated]");

	}

	@Test(expected = FileNotFoundException.class)
	public void testReadFileNotFound() throws FileNotFoundException {
		doThrow(new FileNotFoundException("File with path E:\\test\\wecomeNot not found")).when(myResourceMockService)
				.readFile("welcomeNot.txt");
		myResource.readFile("welcomeNot.txt");
	}

	public void testDeleteFile() throws FileNotFoundException {
		when(myResourceMockService.deleteFile("welcome.text")).thenReturn(true);
		Assert.assertEquals(myResource.deleteFile("welcome.txt"), true);
	//	verify(myResourceMockService, times(1)).deleteFile("welcome.txt");
	}

}
