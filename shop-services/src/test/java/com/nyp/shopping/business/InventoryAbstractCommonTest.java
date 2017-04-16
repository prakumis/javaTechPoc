package com.nyp.shopping.business;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class InventoryAbstractCommonTest extends AbstractTransactionalJUnit4SpringContextTests {

	private static Logger logger = LoggerFactory.getLogger( InventoryAbstractCommonTest.class );

	protected static final boolean ROLLBACK = false;

	@Autowired
	protected ApplicationContext context;

	static {
		try {
//			System.setProperty( "test.mysql.username", EnvironmentLoader.loadEnvironmentProperty( "test.mysql.username" ) );
//			System.setProperty( "test.mysql.password", EnvironmentLoader.loadEnvironmentProperty( "test.mysql.password" ) );
		} catch ( Exception e ) {
			e.printStackTrace();
		}

	}

	protected void executeScriptIfProfile( final String scriptLocation, final String profile ) {
		List<String> activeProfiles = Arrays.asList( context.getEnvironment().getActiveProfiles() );
		logger.info( "active profiles: {}", activeProfiles );

		if ( activeProfiles.contains( profile ) ) {
			try {
				executeSqlScript( scriptLocation, false );
			} catch ( Exception e ) {
				logger.warn( String.format( "Unable to execute script [%s]", scriptLocation ), e );
			}
		}

	}


}
