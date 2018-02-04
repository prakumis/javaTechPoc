/**
 * 
 */
package com.nyp.shopping.web.controller.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.management.ServiceNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nyp.shopping.business.service.ProductCategoryService;
import com.nyp.shopping.common.constants.WebConstants;
import com.nyp.shopping.common.vo.ProductCategoryVO;
import com.nyp.shopping.common.vo.UserVO;
import com.nyp.shopping.web.exception.ApplicationValidationException;
import com.nyp.shopping.web.exception.KeywordNotFoundException;
import com.nyp.shopping.web.model.ResponseBean;

import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * CRUD Operations:
 * 
 * 
 * @author pmis30
 *
 */
@RestController
// @RequestMapping("/cat")
@RequestMapping(path = "/cat", produces = { WebConstants.MEDIA_TYPE_XML_VERSION_0_1,
		WebConstants.MEDIA_TYPE_JSON_VERSION_0_1 })
// @RequestMapping(path = "/cat", consumes = { "application/json",
// "application/xml" }, produces = { "application/json", "application/xml" },
// headers = { "X-API-Version=v1" })
@Api(value = "ProductCategory Controller", description = "REST APIs related to ProductCategory Entity!!!!", consumes = "JSON", produces = "JSON")
public class ProductCategoryController extends BaseController {

	@Inject
	private ProductCategoryService productCatalogService;

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		// do nothing
	}

	/**
	 * This is version [application/vnd.shop.app-v0.1+xml,json] of the url /cat,
	 * which returns all cat
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<ProductCategoryVO> findAllCategories() {
		return productCatalogService.findAllCategories();
	}

	// @ResponseStatus( HttpStatus.CREATED )
	@RequestMapping(method = RequestMethod.POST, consumes = { WebConstants.MEDIA_TYPE_XML_VERSION_0_1,
			WebConstants.MEDIA_TYPE_JSON_VERSION_0_1 })
	public ResponseBean<Object> createCategory(@Valid @RequestBody ProductCategoryVO category,
			@ApiIgnore UserVO userProfile) {

		category.setLoggedInUserId(userProfile.getLoggedInUserId());
		Long id = productCatalogService.createCategory(category);
		return new ResponseBean<>(HttpStatus.CREATED.value(), String.format("Category %s created successfully", id));
	}

	@RequestMapping(method = RequestMethod.GET, path = "/parent/{parentId}")
	public List<ProductCategoryVO> findCategoriesByParentId(@PathVariable Long parentId) {
		return productCatalogService.findCategoriesByParentId(parentId);
	}

	/**
	 * This is version [application/vnd.shop.app-v0.2+xml,json] of the url /cat,
	 * which returns parent cat.
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/status/{status}")
	public List<ProductCategoryVO> findCategoriesByStatus(@PathVariable Boolean status) {
		return productCatalogService.findCategoriesByStatus(status);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public List<ProductCategoryVO> findCategoryById(@PathVariable Long id) throws ServiceNotFoundException {

		if (id == 0) {
			throw new KeywordNotFoundException("The ID is: " + id);
		}
		if (id == -1) {
			List<String> validationErrors = new ArrayList<>();
			validationErrors.add("First Name is required");
			validationErrors.add("Last Name is required");
			throw new ApplicationValidationException("ValidationException caught", validationErrors);
		}
		if (id == -2) {
			throw new ServiceNotFoundException("Custome error caused in doing something");
		}
		return productCatalogService.getCategoryById(id);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{id}", consumes = { WebConstants.MEDIA_TYPE_XML_VERSION_0_1,
			WebConstants.MEDIA_TYPE_JSON_VERSION_0_1 })
	public ProductCategoryVO updateCategory(@PathVariable Long id, @RequestBody ProductCategoryVO category,
			@ApiIgnore UserVO userProfile) {
		category.setId(id);
		category.setLoggedInUserId(userProfile.getLoggedInUserId());
		return productCatalogService.updateCategory(category);
	}

	@RequestMapping(method = RequestMethod.PATCH, path = "/{id}/status/{status}", consumes = {
			WebConstants.MEDIA_TYPE_XML_VERSION_0_1, WebConstants.MEDIA_TYPE_JSON_VERSION_0_1 })
	public ProductCategoryVO updateStatus(@PathVariable Long id, @PathVariable Boolean status,
			@ApiIgnore UserVO userProfile) {
		ProductCategoryVO categoryVO = new ProductCategoryVO();
		categoryVO.setId(id);
		categoryVO.setValid(status);
		categoryVO.setLoggedInUserId(userProfile.getLoggedInUserId());
		return productCatalogService.updateStatus(categoryVO);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public ResponseBean<Object> deleteCategory(@PathVariable Long id, HttpServletResponse response) {
		ProductCategoryVO productCategoryVO = new ProductCategoryVO();
		productCategoryVO.setId(id);
		productCatalogService.deleteCategory(productCategoryVO);
		return new ResponseBean<>(HttpStatus.NO_CONTENT.value(), String.format("Category %s deleted successfully", id));
	}
}
