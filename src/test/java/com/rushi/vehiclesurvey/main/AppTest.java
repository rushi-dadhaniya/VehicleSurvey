package com.rushi.vehiclesurvey.main;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.rushi.vehiclesurvey.vo.Messages;
import com.rushi.vehilcesurvey.util.PrintQueue;

public class AppTest {

	private List<String> expectedPrintQueue;
	
	@Before
	public void setUp() {
		expectedPrintQueue = new ArrayList<String>();
	}

	@Test
	public void mainWithNoCommandLines() {
		App.main(new String[0]);
		expectedPrintQueue.add(Messages.FILE_PATH_NOT_PROVIDED.getMessage());
		List<String> printQueue = PrintQueue.getPrintQueue();
		Assert.assertEquals(expectedPrintQueue, printQueue);
	}

}
