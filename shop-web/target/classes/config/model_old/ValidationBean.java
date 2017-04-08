package com.nyp.shopping.web.exception.model_old;

import java.util.List;

/**
 * Created by francesco on 2/11/14.
 */
public class ValidationBean extends ErrorBean{

    private List <String> validationErrors;

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }
    
    
    public ValidationBean() {
    }

    public ValidationBean(String code, String message, List<String> validationErrors) {
        super(code, message);
        this.validationErrors = validationErrors;
    }
    
    

   

   
}
