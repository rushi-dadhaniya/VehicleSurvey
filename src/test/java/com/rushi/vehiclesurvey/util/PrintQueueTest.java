package com.rushi.vehiclesurvey.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.rushi.vehilcesurvey.util.PrintQueue;

public class PrintQueueTest {

	@Test
	public void getPrintQueueNonEmptyQueue() {
		
		List<String> expectedPrintQueue = new ArrayList<String>();
		expectedPrintQueue.add("Hello");
		expectedPrintQueue.add("How are you?");
		expectedPrintQueue.add("I am fine");
		
		List<String> printQueue = PrintQueue.getPrintQueue();
		printQueue.add("Hello");
		printQueue.add("How are you?");
		printQueue.add("I am fine");
		
		Assert.assertEquals(expectedPrintQueue, PrintQueue.getPrintQueue());
	}
	
	@Test
	public void getPrintQueueEmptyQueue() {
		Assert.assertEquals(new ArrayList<String>(), PrintQueue.getPrintQueue());
	}

}
