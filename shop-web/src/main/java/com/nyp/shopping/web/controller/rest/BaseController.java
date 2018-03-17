/**
 * 
 */
package com.nyp.shopping.web.controller.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;

import com.nyp.shopping.common.constants.WebConstants;

/**
 * @author pmis30
 *
 */
//@RequestMapping("restapi")
public class BaseController {

	/** Logger that is available to subclasses */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected static final String[] SUPPORTED_MEDIA_TYPES = { WebConstants.MEDIA_TYPE_XML_VERSION_0_1,
			WebConstants.MEDIA_TYPE_JSON_VERSION_0_1 };

	protected List<String> convertToStringErros(List<ObjectError> allErrors) {

		List<String> errorsList = new ArrayList<>(allErrors.size());
		ListIterator<ObjectError> iterator = allErrors.listIterator();
		while (iterator.hasNext()) {
			ObjectError objectError = iterator.next();
			errorsList.add(objectError.getDefaultMessage());
		}
		return errorsList;
	}

}
