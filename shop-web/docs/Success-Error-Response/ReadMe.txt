Success/Error response format has been updated to make it consistent.
ResponseBean extends ErrorBean extends ValidationBean
ResponseBean has
    a) responseTime
    b) statusCode - same as httpStatusCode of response (client can use either) 200/201
    c) successMessage - in case of successful request
    d) data - the actual response data
ErrorBean has
    a) errorCode - application defined errorCodes like db_error, internal_error, smtp_error
    b) errorMessage - the detailed error message to identify cause of failure
ValidationBean has
    a) errorList - list of all validation errors

RestResponseEntityExceptionHandler
    - catch all the exceptions
    - wraps into ErrorBean and return, would be processed by RestResponseBodyAdvice

RestResponseBodyAdvice
    - process all the response 
    - Allows customizing the response after the execution of 
        an @ResponseBody or a ResponseEntity controller method 
        but before the body is written with an HttpMessageConverter
    - Wraps the responseBody into ResponseBean and sets responseTime/statusCode 
        and other success/error details
