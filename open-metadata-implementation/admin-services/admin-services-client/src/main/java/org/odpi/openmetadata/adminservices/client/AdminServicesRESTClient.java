/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.adminservices.client;

import org.odpi.openmetadata.adapters.connectors.restclients.RESTClientConnector;
import org.odpi.openmetadata.adapters.connectors.restclients.RESTClientFactory;
import org.odpi.openmetadata.adminservices.ffdc.OMAGAdminErrorCode;
import org.odpi.openmetadata.adminservices.ffdc.exception.OMAGConfigurationErrorException;
import org.odpi.openmetadata.adminservices.ffdc.exception.OMAGInvalidParameterException;
import org.odpi.openmetadata.adminservices.ffdc.exception.OMAGNotAuthorizedException;
import org.odpi.openmetadata.adminservices.rest.*;
import org.odpi.openmetadata.commonservices.ffdc.rest.*;


/**
 * AssetOwnerRESTClient is responsible for issuing calls to the Admin Services REST APIs.
 */
class AdminServicesRESTClient
{
    private String                          serverPlatformURLRoot;  /* Initialized in constructor */
    private AdminClientRESTExceptionHandler exceptionHandler = new AdminClientRESTExceptionHandler();

    private RESTClientConnector clientConnector;        /* Initialized in constructor */


    /**
     * Constructor for no authentication.
     *
     * @param serverName name of the OMAG Server to call
     * @param serverPlatformURLRoot URL root of the server platform where the OMAG Server is running.
     * @throws OMAGInvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    AdminServicesRESTClient(String serverName,
                            String serverPlatformURLRoot) throws OMAGInvalidParameterException
    {
        final String  methodName = "RESTClient(no authentication)";

        this.serverPlatformURLRoot = serverPlatformURLRoot;

        RESTClientFactory factory = new RESTClientFactory(serverName, serverPlatformURLRoot);

        try
        {
            this.clientConnector = factory.getClientConnector();
        }
        catch (Throwable     error)
        {
            throw new OMAGInvalidParameterException(OMAGAdminErrorCode.NULL_LOCAL_SERVER_NAME.getMessageDefinition(serverName, error.getMessage()),
                                                    this.getClass().getName(),
                                                    methodName,
                                                    error);
        }
    }


    /**
     * Constructor for simple userId and password authentication.
     *
     * @param serverName name of the OMAG Server to call
     * @param serverPlatformURLRoot URL root of the server platform where the OMAG Server is running.
     * @param userId user id for the HTTP request
     * @param password password for the HTTP request
     * @throws OMAGInvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    AdminServicesRESTClient(String serverName,
                            String serverPlatformURLRoot,
                            String userId,
                            String password) throws OMAGInvalidParameterException
    {
        final String  methodName = "RESTClient(userId and password)";

        this.serverPlatformURLRoot = serverPlatformURLRoot;

        RESTClientFactory  factory = new RESTClientFactory(serverName,
                                                           serverPlatformURLRoot,
                                                           userId,
                                                           password);

        try
        {
            this.clientConnector = factory.getClientConnector();
        }
        catch (Throwable     error)
        {
            throw new OMAGInvalidParameterException(OMAGAdminErrorCode.NULL_LOCAL_SERVER_NAME.getMessageDefinition(serverName, error.getMessage()),
                                                    this.getClass().getName(),
                                                    methodName,
                                                    error);
        }
    }


    /**
     * Issue a POST REST call that returns a VoidResponse object.  This is typically a create
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate  template of the URL for the REST API call with place-holders for the parameters.
     * @param requestBody request body for the request.
     * @param params  a list of parameters that are slotted into the url template.
     *
     * @return VoidResponse
     * @throws OMAGInvalidParameterException one of the parameters is invalid.
     * @throws OMAGNotAuthorizedException the user is not authorized to make this request.
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    VoidResponse callVoidPostRESTCall(String    methodName,
                                      String    urlTemplate,
                                      Object    requestBody,
                                      Object... params) throws OMAGInvalidParameterException,
                                                               OMAGNotAuthorizedException,
                                                               OMAGConfigurationErrorException
    {
        VoidResponse restResult =  this.callPostRESTCall(methodName,
                                                         VoidResponse.class,
                                                         urlTemplate,
                                                         requestBody,
                                                         params);

        exceptionHandler.detectAndThrowAdminExceptions(restResult);

        return restResult;
    }


    /**
     * Issue a POST REST call that returns a VoidResponse object.  This is typically a create
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate  template of the URL for the REST API call with place-holders for the parameters.
     * @param params  a list of parameters that are slotted into the url template.
     *
     * @return VoidResponse
     * @throws OMAGInvalidParameterException one of the parameters is invalid.
     * @throws OMAGNotAuthorizedException the user is not authorized to make this request.
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    VoidResponse callVoidDeleteRESTCall(String    methodName,
                                        String    urlTemplate,
                                        Object... params) throws OMAGInvalidParameterException,
                                                                 OMAGNotAuthorizedException,
                                                                 OMAGConfigurationErrorException
    {
        VoidResponse restResult =  this.callPostRESTCall(methodName,
                                                         VoidResponse.class,
                                                         urlTemplate,
                                                         null,
                                                         params);

        exceptionHandler.detectAndThrowAdminExceptions(restResult);

        return restResult;
    }


    /**
     * Issue a GET REST call that returns a GUIDResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return GUIDResponse
     * @throws OMAGInvalidParameterException one of the parameters is invalid.
     * @throws OMAGNotAuthorizedException the user is not authorized to make this request.
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    StringResponse callStringGetRESTCall(String    methodName,
                                         String    urlTemplate,
                                         Object... params) throws OMAGInvalidParameterException,
                                                                  OMAGNotAuthorizedException,
                                                                  OMAGConfigurationErrorException
    {
        StringResponse restResult = this.callGetRESTCall(methodName, StringResponse.class, urlTemplate, params);

        exceptionHandler.detectAndThrowAdminExceptions(restResult);

        return restResult;
    }


    /**
     * Issue a GET REST call that returns a GUIDResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return GUIDResponse
     * @throws OMAGInvalidParameterException one of the parameters is invalid.
     * @throws OMAGNotAuthorizedException the user is not authorized to make this request.
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    GUIDResponse callGUIDGetRESTCall(String    methodName,
                                     String    urlTemplate,
                                     Object... params) throws OMAGInvalidParameterException,
                                                              OMAGNotAuthorizedException,
                                                              OMAGConfigurationErrorException
    {
        GUIDResponse restResult = this.callGetRESTCall(methodName, GUIDResponse.class, urlTemplate, params);

        exceptionHandler.detectAndThrowAdminExceptions(restResult);

        return restResult;
    }


    /**
     * Issue a GET REST call that returns a StringMapResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return StringMapResponse
     * @throws OMAGInvalidParameterException one of the parameters is invalid.
     * @throws OMAGNotAuthorizedException the user is not authorized to make this request.
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    StringMapResponse callStringMapGetRESTCall(String    methodName,
                                               String    urlTemplate,
                                               Object... params) throws OMAGInvalidParameterException,
                                                                        OMAGNotAuthorizedException,
                                                                        OMAGConfigurationErrorException
    {
        StringMapResponse restResult = this.callGetRESTCall(methodName, StringMapResponse.class, urlTemplate, params);

        exceptionHandler.detectAndThrowAdminExceptions(restResult);

        return restResult;
    }


    /**
     * Issue a GET REST call that returns a OMAGServerConfigResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return OMAGServerConfigResponse
     * @throws OMAGInvalidParameterException one of the parameters is invalid.
     * @throws OMAGNotAuthorizedException the user is not authorized to make this request.
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    OMAGServerConfigResponse callOMAGServerConfigGetRESTCall(String    methodName,
                                                             String    urlTemplate,
                                                             Object... params) throws OMAGInvalidParameterException,
                                                                                      OMAGNotAuthorizedException,
                                                                                      OMAGConfigurationErrorException
    {
        OMAGServerConfigResponse restResult = this.callGetRESTCall(methodName, OMAGServerConfigResponse.class, urlTemplate, params);

        exceptionHandler.detectAndThrowAdminExceptions(restResult);

        return restResult;
    }


    /**
     * Issue a GET REST call that returns a ConnectionResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return ConnectionResponse
     * @throws OMAGInvalidParameterException one of the parameters is invalid.
     * @throws OMAGNotAuthorizedException the user is not authorized to make this request.
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    ConnectionResponse callConnectionGetRESTCall(String    methodName,
                                                 String    urlTemplate,
                                                 Object... params) throws OMAGInvalidParameterException,
                                                                          OMAGNotAuthorizedException,
                                                                          OMAGConfigurationErrorException
    {
        ConnectionResponse restResult = this.callGetRESTCall(methodName, ConnectionResponse.class, urlTemplate, params);

        exceptionHandler.detectAndThrowAdminExceptions(restResult);

        return restResult;
    }


    /**
     * Issue a GET REST call that returns a ServerTypeClassificationResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return ServerTypeClassificationResponse
     * @throws OMAGInvalidParameterException one of the parameters is invalid.
     * @throws OMAGNotAuthorizedException the user is not authorized to make this request.
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    ServerTypeClassificationResponse callServerClassificationGetRESTCall(String    methodName,
                                                                         String    urlTemplate,
                                                                         Object... params) throws OMAGInvalidParameterException,
                                                                                                  OMAGNotAuthorizedException,
                                                                                                  OMAGConfigurationErrorException
    {
        ServerTypeClassificationResponse restResult = this.callGetRESTCall(methodName, ServerTypeClassificationResponse.class, urlTemplate, params);

        exceptionHandler.detectAndThrowAdminExceptions(restResult);

        return restResult;
    }


    /**
     * Issue a GET REST call that returns a DiscoveryEngineServicesConfigResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return DiscoveryEngineServicesConfigResponse
     * @throws OMAGInvalidParameterException one of the parameters is invalid.
     * @throws OMAGNotAuthorizedException the user is not authorized to make this request.
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    DiscoveryEngineServicesConfigResponse callDiscoveryServerConfigGetRESTCall(String    methodName,
                                                                               String    urlTemplate,
                                                                               Object... params) throws OMAGInvalidParameterException,
                                                                                                        OMAGNotAuthorizedException,
                                                                                                        OMAGConfigurationErrorException
    {
        DiscoveryEngineServicesConfigResponse restResult = this.callGetRESTCall(methodName, DiscoveryEngineServicesConfigResponse.class, urlTemplate, params);

        exceptionHandler.detectAndThrowAdminExceptions(restResult);

        return restResult;
    }


    /**
     * Issue a GET REST call that returns a StewardshipEngineServicesConfigResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return StewardshipEngineServicesConfigResponse
     * @throws OMAGInvalidParameterException one of the parameters is invalid.
     * @throws OMAGNotAuthorizedException the user is not authorized to make this request.
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    StewardshipEngineServicesConfigResponse callStewardshipServerConfigGetRESTCall(String    methodName,
                                                                                   String    urlTemplate,
                                                                                   Object... params) throws OMAGInvalidParameterException,
                                                                                                            OMAGNotAuthorizedException,
                                                                                                            OMAGConfigurationErrorException
    {
        StewardshipEngineServicesConfigResponse restResult = this.callGetRESTCall(methodName, StewardshipEngineServicesConfigResponse.class, urlTemplate, params);

        exceptionHandler.detectAndThrowAdminExceptions(restResult);

        return restResult;
    }


    /**
     * Issue a GET REST call that returns a RegisteredOMAGServicesResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return RegisteredOMAGServicesResponse
     * @throws OMAGInvalidParameterException one of the parameters is invalid.
     * @throws OMAGNotAuthorizedException the user is not authorized to make this request.
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    RegisteredOMAGServicesResponse callRegisteredOMAGServicesGetRESTCall(String    methodName,
                                                                         String    urlTemplate,
                                                                         Object... params) throws OMAGInvalidParameterException,
                                                                                                  OMAGNotAuthorizedException,
                                                                                                  OMAGConfigurationErrorException
    {
        RegisteredOMAGServicesResponse restResult = this.callGetRESTCall(methodName, RegisteredOMAGServicesResponse.class, urlTemplate, params);

        exceptionHandler.detectAndThrowAdminExceptions(restResult);

        return restResult;
    }





    /**
     * Issue a GET REST call that returns a response object.
     *
     * @param <T> return type
     * @param methodName  name of the method being called.
     * @param returnClass class of the response object.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     *
     * @return response object
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    protected <T> T callGetRESTCallNoParams(String    methodName,
                                            Class<T>  returnClass,
                                            String    urlTemplate) throws OMAGConfigurationErrorException
    {
        try
        {
            return clientConnector.callGetRESTCall(methodName, returnClass, urlTemplate);
        }
        catch (Throwable error)
        {
            exceptionHandler.logRESTCallException(serverPlatformURLRoot, methodName, error);
        }

        return null;
    }


    /**
     * Issue a GET REST call that returns a response object.
     *
     * @param <T> return type
     * @param methodName  name of the method being called.
     * @param returnClass class of the response object.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return response object
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    protected  <T> T callGetRESTCall(String    methodName,
                                     Class<T>  returnClass,
                                     String    urlTemplate,
                                     Object... params) throws OMAGConfigurationErrorException
    {
        try
        {
            return clientConnector.callGetRESTCall(methodName, returnClass, urlTemplate, params);
        }
        catch (Throwable error)
        {
            exceptionHandler.logRESTCallException(serverPlatformURLRoot, methodName, error);
        }

        return null;
    }


    /**
     * Issue a POST REST call that returns a response object.  This is typically a create, update, or find with
     * complex parameters.
     *
     * @param <T> return type
     * @param methodName  name of the method being called.
     * @param returnClass class of the response object.
     * @param urlTemplate  template of the URL for the REST API call with place-holders for the parameters.
     * @param requestBody request body for the request.
     *
     * @return response object
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    protected <T> T callPostRESTCallNoParams(String    methodName,
                                             Class<T>  returnClass,
                                             String    urlTemplate,
                                             Object    requestBody) throws OMAGConfigurationErrorException
    {
        try
        {
            return clientConnector.callPostRESTCallNoParams(methodName, returnClass, urlTemplate, requestBody);
        }
        catch (Throwable error)
        {
            exceptionHandler.logRESTCallException(serverPlatformURLRoot, methodName, error);
        }

        return null;
    }


    /**
     * Issue a POST REST call that returns a response object.  This is typically a create, update, or find with
     * complex parameters.
     *
     * @param <T> return type
     * @param methodName  name of the method being called.
     * @param returnClass class of the response object.
     * @param urlTemplate  template of the URL for the REST API call with place-holders for the parameters.
     * @param requestBody request body for the request.
     * @param params  a list of parameters that are slotted into the url template.
     *
     * @return response object
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    protected  <T> T callPostRESTCall(String    methodName,
                                      Class<T>  returnClass,
                                      String    urlTemplate,
                                      Object    requestBody,
                                      Object... params) throws OMAGConfigurationErrorException
    {
        try
        {
            return clientConnector.callPostRESTCall(methodName, returnClass, urlTemplate, requestBody, params);
        }
        catch (Throwable error)
        {
            exceptionHandler.logRESTCallException(serverPlatformURLRoot, methodName, error);
        }

        return null;
    }


    /**
     * Issue a DELETE REST call that returns a response object.
     *
     * @param <T> return type
     * @param methodName  name of the method being called.
     * @param returnClass class of the response object.
     * @param urlTemplate  template of the URL for the REST API call with place-holders for the parameters.
     * @param requestBody request body for the request.
     *
     * @return response object
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    protected <T> T callDeleteRESTCallNoParams(String    methodName,
                                               Class<T>  returnClass,
                                               String    urlTemplate,
                                               Object    requestBody) throws OMAGConfigurationErrorException
    {
        try
        {
            return clientConnector.callDeleteRESTCallNoParams(methodName, returnClass, urlTemplate, requestBody);
        }
        catch (Throwable error)
        {
            exceptionHandler.logRESTCallException(serverPlatformURLRoot, methodName, error);
        }

        return null;
    }


    /**
     * Issue a DELETE REST call that returns a response object.
     *
     * @param <T> return type
     * @param methodName  name of the method being called.
     * @param returnClass class of the response object.
     * @param urlTemplate  template of the URL for the REST API call with place-holders for the parameters.
     * @param requestBody request body for the request.
     * @param params  a list of parameters that are slotted into the url template.
     *
     * @return response object
     * @throws OMAGConfigurationErrorException something went wrong with the REST call stack.
     */
    protected  <T> T callDeleteRESTCall(String    methodName,
                                        Class<T>  returnClass,
                                        String    urlTemplate,
                                        Object    requestBody,
                                        Object... params) throws OMAGConfigurationErrorException
    {
        try
        {
            return clientConnector.callDeleteRESTCall(methodName, returnClass, urlTemplate, requestBody, params);
        }
        catch (Throwable error)
        {
            exceptionHandler.logRESTCallException(serverPlatformURLRoot, methodName, error);
        }

        return null;
    }



}
