package com.nyp.shopping.business.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({

		/*
		 * This [@Suite.SuiteClasses()] annotation/class is not required as long as the
		 * [@RunWith(SpringJUnit4ClassRunner.class)] is annotated on individual test
		 * classes. If this annotation on this class is used, then the mentioned test
		 * class will be executed once more.
		 * 
		 * The test suite classes are not executed by maven test plugin by default.
		 * These are only useful to run a group/suite of classes from eclipse by
		 * selecting the suite class -> right click -> run as junit.
		 * 
		 */
})
/**
 * Junit test classes related to ProductService are placed here. These all test
 * classes can be executed in single click from eclipse. TODO How to execute
 * this test suite from maven build by passing the test suite name parameter.
 * 
 * @author Java Developer
 *
 */
public class JunitProductServiceTestSuite {

}
