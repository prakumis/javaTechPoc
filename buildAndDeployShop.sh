#!/bin/bash
set -x
# Usually User need to change the following Variables only
APP_SERVER_HOME="D:/Application/apache-tomcat-9.0.0.M9_shop"
WEB_SERVER_HOME="C:/Apache24/htdocs"
APP_SERVER_START_MODE="debug"
PROJECT_VERSION="0.0.1-SNAPSHOT"
SRC_WEB_APP_ROOT_LOC="D:/Work/Repository/JavaTechPoc/javaTechPoc"

# Comment the follwoing variables if not required.
#MAVEN_SKIP_TEST="-DskipTests=true"
#SONAR_BUILD="sonar:sonar"

# Usually User don't need to change the following
#WEB_APP_NAME="shop-web-${PROJECT_VERSION}"
WEB_APP_NAME="shop-web"			# from the shop-web/pom.xml => <finalName>shop-web</finalName>
WEB_APP_WAR_FILE_NAME="$WEB_APP_NAME.war"
SRC_WEB_APP_POM_LOC="${SRC_WEB_APP_ROOT_LOC}"
SRC_WEB_APP_TARGET_PATH="$SRC_WEB_APP_ROOT_LOC/shop-web/target"
SRC_WEB_APP_CONFIG_PATH="${SRC_WEB_APP_ROOT_LOC}/deployment/shop/configuration"
#WEB_APP_DEPLOY_ENV="dev"

STATIC_CODE_GIT_LOC="$SRC_WEB_APP_ROOT_LOC/shop-web-client/"
SHOP_CONFIG_PATH="${SHOP_MAIN_POM_LOCATION}/shop-web/src/main/resources"

APP_SERVER_NAME="Tomcat Application Server v 9.0"
APP_SERVER_DEPLOYEMENT_PATH="$APP_SERVER_HOME/webapps"
APP_SERVER_LOG_PATH="$APP_SERVER_HOME/logs"
APP_SERVER_BIN_PATH="$APP_SERVER_HOME/bin"
APP_SERVER_WEB_CONFIG_PATH="$APP_SERVER_HOME/shop/configuration"
APP_SERVER_WEB_LOG_PATH="${APP_SERVER_HOME}/shop/logs"
APP_SERVER_START_CMD="./${APP_SERVER_START_MODE}.sh"

DATE_FORMAT='%Y%m%d-%H%M'
CURRENT_DATE="$(date +$DATE_FORMAT)"
echo $CURRENT_DATE

stopAppServer() {

	echo "$APP_SERVER_NAME [$APP_SERVER_HOME] Shutdown Started"
	cd $APP_SERVER_BIN_PATH
	./shutdown.sh
	echo "$APP_SERVER_NAME [$APP_SERVER_HOME] Shutdown Finished"
}


mavenBuild() {

	echo "Maven Build Started"
	cd $SRC_WEB_APP_POM_LOC
	mvn clean install $MAVEN_SKIP_TEST $SONAR_BUILD
	echo "Maven Build Finished"
}
executeDeployment() {

	cleanAppServerLogs;
	cleanWebAppLogs;
	cleanWebAppWar;
	cleanResource;
	copyResources;
	deployWar;
}
cleanAppServerLogs() {

	echo "$APP_SERVER_NAME [$APP_SERVER_HOME] Log Cleaning Started"
	rm -fr $APP_SERVER_LOG_PATH/*
	echo "$APP_SERVER_NAME [$APP_SERVER_HOME] Log Cleaning Finished"
}
cleanWebAppLogs() {

	echo "$APP_SERVER_NAME [$APP_SERVER_HOME] Log Cleaning Started"
	rm -fr $APP_SERVER_WEB_LOG_PATH/*
	echo "$APP_SERVER_NAME [$APP_SERVER_HOME] Log Cleaning Finished"
}

cleanWebAppWar() {

	echo "$WEB_APP_NAME Cleaning Started"
	cd $APP_SERVER_DEPLOYEMENT_PATH
	rm $WEB_APP_WAR_FILE_NAME
	rm -fr $WEB_APP_NAME/
	echo "$WEB_APP_NAME Cleaning Finished"
}

cleanResource() {

	echo "$APP_SERVER_WEB_CONFIG_PATH Cleaning Started"
	rm -rf $APP_SERVER_WEB_CONFIG_PATH/*
	echo "$APP_SERVER_WEB_CONFIG_PATH Cleaning Finished"
}

copyResources() {

	echo "Copying Resources from $SRC_WEB_APP_CONFIG_PATH to $APP_SERVER_WEB_CONFIG_PATH Started"
	mkdir -p $APP_SERVER_WEB_CONFIG_PATH
	cd $SRC_WEB_APP_CONFIG_PATH
	cp *.* $APP_SERVER_WEB_CONFIG_PATH/
	echo "Copying Resources from $SRC_WEB_APP_CONFIG_PATH to $APP_SERVER_WEB_CONFIG_PATH Finished"
}
deployWar() {

	echo "Deploying $WEB_APP_WAR_FILE_NAME Started"
	cd $APP_SERVER_DEPLOYEMENT_PATH
	cp $SRC_WEB_APP_TARGET_PATH/$WEB_APP_WAR_FILE_NAME .
	echo "Deploying $WEB_APP_WAR_FILE_NAME Finished"
}

startAppServer() {

	echo "$APP_SERVER_NAME [$APP_SERVER_HOME] Started"
	cd $APP_SERVER_BIN_PATH
	#sh ${APP_SERVER_START_CMD}
	#sh ./startup.sh $APP_SERVER_START_MODE start
	sh ./catalina.sh jpda start 
	#./run.sh
	echo "$APP_SERVER_NAME [$APP_SERVER_HOME] Finished"
}

deployStatic() {

	echo "Moving latest files from etme_integrate to ${WEB_SERVER_HOME}"
	cd $WEB_SERVER_HOME
	cd ../
	echo "Taking backup of exiting $WEB_SERVER_HOME into ${WEB_SERVER_HOME}_${CURRENT_DATE}"
	mkdir ${WEB_SERVER_HOME}_${CURRENT_DATE}
	mv -f ${WEB_SERVER_HOME}/* ${WEB_SERVER_HOME}_${CURRENT_DATE}
	echo "Copying static code from GIT [$STATIC_CODE_GIT_LOC] to ${WEB_SERVER_HOME}/"
	cp -r ${STATIC_CODE_GIT_LOC}/* ${WEB_SERVER_HOME}/
	echo "Static GUI contents moved to ${WEB_SERVER_HOME}"
}

startApache() {

	echo "Re-Starting Apache"
	cd $WEB_SERVER_HOME
	cd ../bin
	httpd.exe -k restart
	echo "Apache restarted"
}

# start calling your functions
#deployStatic;
#stopAppServer;
mavenBuild;
#executeDeployment;
#startAppServer;