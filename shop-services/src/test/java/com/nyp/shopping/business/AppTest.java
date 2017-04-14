package com.nyp.shopping.business;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		Assert.assertTrue(true);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp2() {
		Assert.assertEquals(false, true);
	}

	/**
	 * Rigourous Test :-)
	 */
	@Test
	public void testApp3() {
		System.out.println("Services-testApp3()");
		Assert.assertEquals(false, false);
	}
}
