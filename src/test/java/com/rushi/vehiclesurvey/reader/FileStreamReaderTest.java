package com.rushi.vehiclesurvey.reader;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileStreamReaderTest {

	private FileStreamReader fileStreamReader;
	private List<String> expectedLines;
	
	@Before
	public void setUp() {
		fileStreamReader = new  FileStreamReader();
		expectedLines = new ArrayList<String>();
	}
	
	@Test
	public void readWithData() {
		expectedLines.add("A123456");
		expectedLines.add("A126543");
		expectedLines.add("B777777");
		expectedLines.add("B888888");
		expectedLines.add("B111111");
		Assert.assertEquals(expectedLines, fileStreamReader.read("vehicles.text"));
	}
	
	@Test
	public void readWithEmptyFile() {
		Assert.assertEquals(new ArrayList<String>(), fileStreamReader.read("emptyfile.text"));
	}
	
	@Test
	public void readWithNullPath() {
		Assert.assertNull(fileStreamReader.read(null));
	}

}
