package com.nyp.shopping.opensource.emailsender;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JaxbUtils {

	private static Logger logger = LoggerFactory.getLogger(JaxbUtils.class);

	@SuppressWarnings({ "rawtypes" })
	public static Object loadClassFromJaxb(String xmlTemplateName, Class clazz) throws JAXBException {

		InputStream in = JaxbUtils.class.getClassLoader().getResourceAsStream(xmlTemplateName);

		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return jaxbUnmarshaller.unmarshal(in);
	}

	public static EmailVO getEmailVO(String emailTemplate) {

		EmailVO emailVO = null;

		// Get a input stream towards mail template file
		try {
			emailVO = (EmailVO) JaxbUtils.loadClassFromJaxb(emailTemplate, EmailVO.class);

		} catch (JAXBException ex) {
			logger.error("unable to load email template [{}] ", emailTemplate, ex);
		}
		return emailVO;
	}
}
