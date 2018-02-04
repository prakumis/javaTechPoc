/**
 * 
 */
package com.nyp.shopping.web.controller.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nyp.shopping.business.service.ProductService;
import com.nyp.shopping.common.constants.WebConstants;
import com.nyp.shopping.common.vo.ProductVO;
import com.nyp.shopping.common.vo.UserVO;
import com.nyp.shopping.web.exception.ApplicationValidationException;
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
@RequestMapping(path = "/cat/{catId}/products", produces = { WebConstants.MEDIA_TYPE_XML_VERSION_0_1,
		WebConstants.MEDIA_TYPE_JSON_VERSION_0_1 })
@Api(value = "Product Controller", description = "REST APIs related to Product Entity!!!!", consumes = "JSON", produces = "JSON")
public class ProductController extends BaseController {

	@Inject
	private ProductService productService;

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		// do nothing
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<ProductVO> findAllProducts(@PathVariable Long catId) {

		if (catId == 0) {
			List<String> validationErrors = new ArrayList<>();
			validationErrors.add("Invalid catId: " + catId);
			throw new ApplicationValidationException("ValidationException caught", validationErrors);
		} else if (catId == -1) {
			return productService.findAllProducts();
		} else {
			return productService.findAllProducts(catId);
		}
	}

	/**
	 * This is version [application/vnd.shop.app-v0.1+xml,json] of the url
	 * /products, which returns all products
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public List<ProductVO> findProductById(@PathVariable Long catId, @PathVariable Long id) {

		if (catId == 0) {
			List<String> validationErrors = new ArrayList<>();
			validationErrors.add("Invalid catId");
			throw new ApplicationValidationException("ValidationException caught", validationErrors);
		}
		return productService.findProductById(catId, id);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/status/{status}")
	public List<ProductVO> findProductByStatus(@PathVariable Long catId, @PathVariable Boolean status) {

		if (catId == 0) {
			List<String> validationErrors = new ArrayList<>();
			validationErrors.add("Invalid catId");
			throw new ApplicationValidationException("ValidationException caught", validationErrors);
		}
		return productService.findProductByStatus(catId, status);
	}

	// @ResponseStatus( HttpStatus.CREATED )
	@RequestMapping(method = RequestMethod.POST, consumes = { WebConstants.MEDIA_TYPE_XML_VERSION_0_1,
			WebConstants.MEDIA_TYPE_JSON_VERSION_0_1 })
	public ResponseBean<Object> createProduct(@PathVariable Long catId, @Valid @RequestBody ProductVO productVO,
			HttpServletRequest request, @ApiIgnore UserVO userProfile) {

		productVO.setCatId(catId);
		productVO.setLoggedInUserId(userProfile.getLoggedInUserId());
		Long id = productService.createProduct(productVO);
		return new ResponseBean<>(HttpStatus.CREATED.value(), String.format("Product %s created successfully", id));
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{id}", consumes = { WebConstants.MEDIA_TYPE_XML_VERSION_0_1,
			WebConstants.MEDIA_TYPE_JSON_VERSION_0_1 })
	public ProductVO updateProduct(@PathVariable Long id, @PathVariable Long catId, @RequestBody ProductVO productVO,
			HttpServletRequest request, @ApiIgnore UserVO userProfile) {

		productVO.setId(id);
		productVO.setCatId(catId);
		productVO.setLoggedInUserId(userProfile.getLoggedInUserId());
		return productService.updateProduct(productVO);
	}

	@RequestMapping(method = RequestMethod.PATCH, path = "/{id}/status/{status}", consumes = {
			WebConstants.MEDIA_TYPE_XML_VERSION_0_1, WebConstants.MEDIA_TYPE_JSON_VERSION_0_1 })
	public ResponseBean<Object> updateStatus(@PathVariable Long id, @PathVariable Long catId,
			HttpServletRequest request, @ApiIgnore UserVO userProfile, @PathVariable Boolean status) {

		ProductVO productVO = new ProductVO();
		productVO.setId(id);
		productVO.setCatId(catId);
		productVO.setValid(status);
		productVO.setLoggedInUserId(userProfile.getLoggedInUserId());
		productService.updateStatus(productVO);
		return new ResponseBean<>(HttpStatus.NO_CONTENT.value(),
				String.format("Product %s status updated successfully", id));
	}

	@RequestMapping(method = RequestMethod.PATCH, path = "/{id}/category/{newCatId}", consumes = {
			WebConstants.MEDIA_TYPE_XML_VERSION_0_1, WebConstants.MEDIA_TYPE_JSON_VERSION_0_1 })
	public ResponseBean<Object> updateCategory(@PathVariable Long id, @PathVariable Long catId, @PathVariable Long newCatId,
			HttpServletRequest request, @ApiIgnore UserVO userProfile) {

		ProductVO productVO = new ProductVO();
		productVO.setId(id);
		productVO.setCatId(catId);
		productVO.setLoggedInUserId(userProfile.getLoggedInUserId());
		productService.updateCategory(productVO, newCatId);
		return new ResponseBean<>(HttpStatus.NO_CONTENT.value(),
				String.format("Product %s status updated successfully", id));
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public ResponseBean<Object> deleteProduct(@PathVariable Long id, @PathVariable Long catId,
			@ApiIgnore UserVO userProfile) {
		ProductVO productVO = new ProductVO();
		productVO.setId(id);
		productVO.setCatId(catId);
		productVO.setLoggedInUserId(userProfile.getLoggedInUserId());
		productService.deleteProduct(productVO);
		return new ResponseBean<>(HttpStatus.NO_CONTENT.value(), String.format("Product %s deleted successfully", id));
	}
}
