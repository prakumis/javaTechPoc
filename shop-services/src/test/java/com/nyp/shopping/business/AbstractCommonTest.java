package com.nyp.shopping.business;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class AbstractCommonTest extends AbstractTransactionalJUnit4SpringContextTests {

	private static Logger logger = LoggerFactory.getLogger(AbstractCommonTest.class);
	private static int staticCounter;

    private static final String STATEMENT_SEPARATOR = ";;";
    private static final String INIT_DB_FILE_NAME = "shopService_HSQL2.sql";
	private static EmbeddedDatabase database;
	private int instanceCounter;

	@Autowired
	protected ApplicationContext context;
    private static EmbeddedDatabase db;

	static {
		try {
			System.setProperty("test.mysql.username", "");
			System.setProperty("test.mysql.password", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public static void initBeforeClass() {
		System.out.println("***************** @BeforeClass.initBeforeClass() called: "+ staticCounter++);
//		initH2Db();
//		initHsqlDb();
//		initMysqlDb();
	}

    public static void initH2Db() {
		System.out.println("***************** @BeforeClass.setUp() called: "+ staticCounter++);
        //db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
    	db = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.H2)
//    		.addScript("config/database/h2/shop_create_table.sql")
    		.addScript("config/database/h2/shop_insert_master_data.sql")
    		.build();
    }

	protected static void initMysqlDb() {

//		executeSqlScript("classpath:shopService.cleanDb.MYSQL.sql", false);
	}

	protected static void executeScript(final String scriptLocation, final String profile) {
		
	}


	public static void initHsqlDb() {
		try {
			database = (new EmbeddedDatabaseBuilder())
					.setSeparator(STATEMENT_SEPARATOR)
					.addScript("classpath:/config/database/hsql/"+INIT_DB_FILE_NAME)
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		System.out.println("database created: " + database);
	}

	@AfterClass
	public static void shutDown() {
		System.out.println("***************** @AfterClass.shutDown() called: "+ staticCounter++);
		if (null != database)
			database.shutdown();
	}

	@Before
	public void prepareDb() {
		System.out.println("***************** @Before.prepareDb() called: "+ instanceCounter++);
		// executeScriptIfProfile("classpath:/config/database/hsql/shopService_HSQL.sql", "testHsql");
		// executeScriptIfProfile("classpath:shopService.cleanDb.MYSQL.sql", "testMysql");
	}

	@After
	public void afterTest() {
		System.out.println("***************** @After.afterTest() called: "+ instanceCounter++);
	}

	protected void executeScriptIfProfile(final String scriptLocation, final String profile) {
		List<String> activeProfiles = Arrays.asList(context.getEnvironment().getActiveProfiles());
		logger.info("active profiles: {}", activeProfiles);

		if (activeProfiles.contains(profile)) {
			try {
				executeSqlScript(scriptLocation, false);
			} catch (Exception e) {
				logger.warn(String.format("Unable to execute script [%s]", scriptLocation), e);
			}
		}

	}

}
