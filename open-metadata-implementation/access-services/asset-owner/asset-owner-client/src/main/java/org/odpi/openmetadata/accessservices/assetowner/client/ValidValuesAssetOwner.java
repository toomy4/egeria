/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.assetowner.client;

import org.odpi.openmetadata.accessservices.assetowner.api.AssetOnboardingValidValues;
import org.odpi.openmetadata.accessservices.assetowner.rest.ValidValuesRequestBody;
import org.odpi.openmetadata.commonservices.ffdc.rest.*;
import org.odpi.openmetadata.commonservices.ocf.metadatamanagement.rest.*;
import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.frameworks.connectors.ffdc.InvalidParameterException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.PropertyServerException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.UserNotAuthorizedException;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.ValidValue;

import java.util.List;
import java.util.Map;

/**
 * ValidValuesAssetOwner provides the API operations to create and maintain lists of valid
 * value definitions grouped into a valid value set.  Both valid value definitions and valid value sets have
 * the same attributes and so inherit from ValidValue where all of the attributes are defined.
 *
 * A set is just grouping of valid values.   Valid value definitions and set can be nested many times in other
 * valid value sets.
 */
public class ValidValuesAssetOwner extends AssetOwner implements AssetOnboardingValidValues
{
    /**
     * Create a new client with no authentication embedded in the HTTP request and an audit log.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformRootURL the network address of the server running the OMAS REST servers
     * @param auditLog logging destination
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public ValidValuesAssetOwner(String   serverName,
                                 String   serverPlatformRootURL,
                                 AuditLog auditLog) throws InvalidParameterException
    {
        super(serverName, serverPlatformRootURL, auditLog);
    }


    /**
     * Create a new client with no authentication embedded in the HTTP request.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformRootURL the network address of the server running the OMAS REST servers
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public ValidValuesAssetOwner(String serverName,
                                 String serverPlatformRootURL) throws InvalidParameterException
    {
        super(serverName, serverPlatformRootURL);
    }


    /**
     * Create a new client that passes userId and password in each HTTP request.  This is the
     * userId/password of the calling server.  The end user's userId is sent on each request.
     * There is also an audit log destination.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformRootURL the network address of the server running the OMAS REST servers
     * @param userId caller's userId embedded in all HTTP requests
     * @param password caller's userId embedded in all HTTP requests
     * @param auditLog logging destination
     *
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public ValidValuesAssetOwner(String   serverName,
                                 String   serverPlatformRootURL,
                                 String   userId,
                                 String   password,
                                 AuditLog auditLog) throws InvalidParameterException
    {
        super(serverName, serverPlatformRootURL, userId, password, auditLog);
    }


    /**
     * Create a new client that passes userId and password in each HTTP request.  This is the
     * userId/password of the calling server.  The end user's userId is sent on each request.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformRootURL the network address of the server running the OMAS REST servers
     * @param userId caller's userId embedded in all HTTP requests
     * @param password caller's userId embedded in all HTTP requests
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public ValidValuesAssetOwner(String serverName,
                                 String serverPlatformRootURL,
                                 String userId,
                                 String password) throws InvalidParameterException
    {
        super(serverName, serverPlatformRootURL, userId, password);
    }


    /*
     * ==============================================
     * AssetOnboardingValidValuesSet
     * ==============================================
     */

    /**
     * Create a new valid value set.  This just creates the Set itself.  Members are added either as they are
     * created, or they can be attached to a set after they are created.
     *
     * @param userId calling user.
     * @param qualifiedName unique name.
     * @param displayName displayable descriptive name.
     * @param description further information.
     * @param usage how/when should this set be used.
     * @param scope what is the scope of this set's values.
     * @param additionalProperties additional properties for this set.
     * @param extendedProperties properties that need to be populated into a subtype.
     *
     * @return unique identifier for the new set
     *
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public String  createValidValueSet(String              userId,
                                       String              qualifiedName,
                                       String              displayName,
                                       String              description,
                                       String              usage,
                                       String              scope,
                                       Map<String, String> additionalProperties,
                                       Map<String, Object> extendedProperties) throws InvalidParameterException,
                                                                                      UserNotAuthorizedException,
                                                                                      PropertyServerException
    {
        final String   methodName = "createValidValueSet";
        final String   nameParameter = "qualifiedName";
        final String   urlTemplate = "/servers/{0}/open-metadata/access-services/asset-owner/users/{1}//valid-values/new-set";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateName(qualifiedName, nameParameter, methodName);

        ValidValuesRequestBody requestBody = new ValidValuesRequestBody();
        requestBody.setQualifiedName(qualifiedName);
        requestBody.setDisplayName(displayName);
        requestBody.setDescription(description);
        requestBody.setUsage(usage);
        requestBody.setScope(scope);
        requestBody.setAdditionalProperties(additionalProperties);
        requestBody.setExtendedProperties(extendedProperties);

        GUIDResponse restResult = restClient.callGUIDPostRESTCall(methodName,
                                                                  serverPlatformRootURL + urlTemplate,
                                                                  requestBody,
                                                                  serverName,
                                                                  userId);

        return restResult.getGUID();
    }


    /**
     * Create a new valid value definition.
     *
     * @param userId calling user.
     * @param setGUID unique identifier of the set to attach this to.
     * @param qualifiedName unique name.
     * @param displayName displayable descriptive name.
     * @param description further information.
     * @param usage how/when should this value be used.
     * @param preferredValue the value that should be used in an implementation if possible.
     * @param additionalProperties additional properties for this definition.
     * @param extendedProperties properties that need to be populated into a subtype.
     *
     * @return unique identifier for the new definition
     *
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public String  createValidValueDefinition(String              userId,
                                              String              setGUID,
                                              String              qualifiedName,
                                              String              displayName,
                                              String              description,
                                              String              usage,
                                              String              scope,
                                              String              preferredValue,
                                              Map<String, String> additionalProperties,
                                              Map<String, Object> extendedProperties) throws InvalidParameterException,
                                                                                             UserNotAuthorizedException,
                                                                                             PropertyServerException
    {
        final String   methodName = "createValidValueDefinition";
        final String   nameParameter = "qualifiedName";
        final String   urlTemplate = "/servers/{0}/open-metadata/access-services/asset-owner/users/{1}/valid-values/new-definition";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateName(qualifiedName, nameParameter, methodName);

        ValidValuesRequestBody requestBody = new ValidValuesRequestBody();
        requestBody.setQualifiedName(qualifiedName);
        requestBody.setDisplayName(displayName);
        requestBody.setDescription(description);
        requestBody.setUsage(usage);
        requestBody.setScope(scope);
        requestBody.setPreferredValue(preferredValue);
        requestBody.setAdditionalProperties(additionalProperties);
        requestBody.setExtendedProperties(extendedProperties);

        GUIDResponse restResult = restClient.callGUIDPostRESTCall(methodName,
                                                                  serverPlatformRootURL + urlTemplate,
                                                                  requestBody,
                                                                  serverName,
                                                                  userId,
                                                                  setGUID);

        return restResult.getGUID();
    }


    /**
     * Update the properties of the valid value.  All properties are updated.
     * If only changing some if the properties, retrieve the current values from the repository
     * and pass existing values back on this call if they are not to change.
     *
     * @param userId calling user.
     * @param validValueGUID unique identifier of the valid value.
     * @param qualifiedName unique name.
     * @param displayName displayable descriptive name.
     * @param description further information.
     * @param usage how/when should this value be used.
     * @param scope what is the scope of the values.
     * @param preferredValue the value that should be used in an implementation if possible.
     * @param isDeprecated is this value deprecated?
     * @param additionalProperties additional properties for this valid value.
     * @param extendedProperties properties that need to be populated into a subtype.
     *
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public void    updateValidValue(String              userId,
                                    String              validValueGUID,
                                    String              qualifiedName,
                                    String              displayName,
                                    String              description,
                                    String              usage,
                                    String              scope,
                                    String              preferredValue,
                                    boolean             isDeprecated,
                                    Map<String, String> additionalProperties,
                                    Map<String, Object> extendedProperties) throws InvalidParameterException,
                                                                                   UserNotAuthorizedException,
                                                                                   PropertyServerException
    {
        final String   methodName = "updateValidValue";
        final String   guidParameter = "validValueGUID";
        final String   nameParameter = "qualifiedName";
        final String   urlTemplate = "/servers/{0}/open-metadata/access-services/asset-owner/users/{1}/valid-values/{2}/update";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateGUID(validValueGUID, guidParameter, methodName);
        invalidParameterHandler.validateName(qualifiedName, nameParameter, methodName);

        ValidValuesRequestBody requestBody = new ValidValuesRequestBody();
        requestBody.setQualifiedName(qualifiedName);
        requestBody.setDisplayName(displayName);
        requestBody.setDescription(description);
        requestBody.setUsage(usage);
        requestBody.setScope(scope);
        requestBody.setPreferredValue(preferredValue);
        requestBody.setAdditionalProperties(additionalProperties);
        requestBody.setExtendedProperties(extendedProperties);

        restClient.callVoidPostRESTCall(methodName,
                                        serverPlatformRootURL + urlTemplate,
                                        requestBody,
                                        serverName,
                                        userId,
                                        validValueGUID);
    }


    /**
     * Remove the valid value form the repository.  All links to it are deleted too.
     *
     * @param userId calling user
     * @param validValueGUID unique identifier of the value to delete
     * @param qualifiedName unique name of the value to delete.  This is used to verify that
     *                      the correct valid value is being deleted.
     *
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public void    deleteValidValue(String   userId,
                                    String   validValueGUID,
                                    String   qualifiedName) throws InvalidParameterException,
                                                                   UserNotAuthorizedException,
                                                                   PropertyServerException
    {
        final String   methodName = "deleteValidValue";
        final String   guidParameter = "validValueGUID";
        final String   nameParameter = "qualifiedName";
        final String   urlTemplate = "/servers/{0}/open-metadata/access-services/asset-owner/users/{1}/valid-values/{2}/delete";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateGUID(validValueGUID, guidParameter, methodName);
        invalidParameterHandler.validateName(qualifiedName, nameParameter, methodName);

        restClient.callVoidPostRESTCall(methodName,
                                        serverPlatformRootURL + urlTemplate,
                                        qualifiedName,
                                        serverName,
                                        userId,
                                        validValueGUID);
    }


    /**
     * Create a link between a valid value set or definition and a set.  This means the valid
     * value is a member of the set.
     *
     * @param userId calling user.
     * @param setGUID unique identifier of the set.
     * @param validValueGUID unique identifier of the valid value to add to the set.
     *
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public void    attachValidValueToSet(String   userId,
                                         String   setGUID,
                                         String   validValueGUID) throws InvalidParameterException,
                                                                         UserNotAuthorizedException,
                                                                         PropertyServerException
    {
        final String   methodName = "attachValidValueToSet";
        final String   setGUIDParameter = "setGUID";
        final String   validValueGUIDParameter = "validValueGUID";
        final String   urlTemplate = "/servers/{0}/open-metadata/access-services/asset-owner/users/{1}/valid-values/sets/{2}/members/{3}";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateGUID(setGUID, setGUIDParameter, methodName);
        invalidParameterHandler.validateGUID(validValueGUID, validValueGUIDParameter, methodName);

        restClient.callVoidPostRESTCall(methodName,
                                        serverPlatformRootURL + urlTemplate,
                                        nullRequestBody,
                                        serverName,
                                        userId,
                                        setGUID,
                                        validValueGUID);
    }


    /**
     * Remove the link between a valid value and a set it is a member of.
     *
     * @param userId calling user
     * @param setGUID owning set
     * @param validValueGUID unique identifier of the member to be removed.
     *
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public void    detachValidValueFromSet(String   userId,
                                           String   setGUID,
                                           String   validValueGUID) throws InvalidParameterException,
                                                                           UserNotAuthorizedException,
                                                                           PropertyServerException
    {
        final String   methodName = "detachValidValueFromSet";
        final String   setGUIDParameter = "setGUID";
        final String   validValueGUIDParameter = "validValueGUID";
        final String   urlTemplate = "/servers/{0}/open-metadata/access-services/asset-owner/users/{1}/valid-values/sets/{2}/members/{3}/delete";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateGUID(setGUID, setGUIDParameter, methodName);
        invalidParameterHandler.validateGUID(validValueGUID, validValueGUIDParameter, methodName);

        restClient.callVoidPostRESTCall(methodName,
                                        serverPlatformRootURL + urlTemplate,
                                        nullRequestBody,
                                        serverName,
                                        userId,
                                        setGUID,
                                        validValueGUID);
    }


    /**
     * Retrieve a specific valid value from the repository.
     *
     * @param userId calling user
     * @param validValueGUID unique identifier of the valid value.
     *
     * @return Valid value bean
     *
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public ValidValue getValidValueByGUID(String   userId,
                                          String   validValueGUID) throws InvalidParameterException,
                                                                          UserNotAuthorizedException,
                                                                          PropertyServerException
    {
        final String   methodName = "getValidValueByGUID";
        final String   validValueGUIDParameter = "validValueGUID";
        final String   urlTemplate = "/servers/{0}/open-metadata/access-services/asset-owner/users/{1}/valid-values/{2}";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateGUID(validValueGUID, validValueGUIDParameter, methodName);

        ValidValueResponse restResult = restClient.callValidValueGetRESTCall(methodName,
                                                                             serverPlatformRootURL + urlTemplate,
                                                                             serverName,
                                                                             userId,
                                                                             validValueGUID);
        return restResult.getValidValue();
    }


    /**
     * Retrieve a specific valid value from the repository. Duplicates may be returned if
     * multiple valid values have been assigned the same qualified name.
     *
     * @param userId calling user
     * @param validValueName qualified name of the valid value.
     *
     * @return Valid value beans
     *
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public List<ValidValue>   getValidValueByName(String   userId,
                                                  String   validValueName) throws InvalidParameterException,
                                                                                  UserNotAuthorizedException,
                                                                                  PropertyServerException
    {
        final String   methodName = "getValidValueByName";
        final String   validValueNameParameter = "validValueName";
        final String   urlTemplate = "/servers/{0}/open-metadata/access-services/asset-owner/users/{1}/valid-values/by-name";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateName(validValueName, validValueNameParameter, methodName);

        ValidValuesResponse restResult = restClient.callValidValuesPostRESTCall(methodName,
                                                                               serverPlatformRootURL + urlTemplate,
                                                                                validValueName,
                                                                                serverName,
                                                                                userId);

        return restResult.getValidValues();
    }


    /**
     * Locate valid values that match the search string.  It considers the names, description, scope,
     * usage and preferred value.
     *
     * @param userId calling user
     * @param searchString string value to look for - may contain RegEx characters.
     * @param startFrom paging starting point
     * @param pageSize maximum number of return values.
     *
     * @return list of valid value beans
     *
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public List<ValidValue> findValidValues(String   userId,
                                            String   searchString,
                                            int      startFrom,
                                            int      pageSize) throws InvalidParameterException,
                                                                      UserNotAuthorizedException,
                                                                      PropertyServerException
    {
        final String   methodName = "getValidValueByName";
        final String   parameterName = "searchString";
        final String   urlTemplate = "/servers/{0}/open-metadata/access-services/asset-owner/users/{1}/valid-values/by-search-string";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateSearchString(searchString, parameterName, methodName);
        invalidParameterHandler.validatePaging(startFrom, pageSize, methodName);

        ValidValuesResponse restResult = restClient.callValidValuesPostRESTCall(methodName,
                                                                                serverPlatformRootURL + urlTemplate,
                                                                                searchString,
                                                                                serverName,
                                                                                userId);

        return restResult.getValidValues();
    }


    /**
     * Page through the members of a valid value set.
     *
     * @param userId calling user.
     * @param validValueSetGUID unique identifier of the valid value set.
     * @param startFrom paging starting point
     * @param pageSize maximum number of return values.
     *
     * @return list of valid value beans
     *
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public List<ValidValue> getValidValueSetMembers(String   userId,
                                                    String   validValueSetGUID,
                                                    int      startFrom,
                                                    int      pageSize) throws InvalidParameterException,
                                                                              UserNotAuthorizedException,
                                                                              PropertyServerException
    {
        final String   methodName = "getValidValueSetMembers";
        final String   validValueGUIDParameter = "validValueSetGUID";
        final String   urlTemplate = "/servers/{0}/open-metadata/access-services/asset-owner/users/{1}/valid-values/sets/{2}/members?startFrom={3}&pageSize={4}";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateGUID(validValueSetGUID, validValueGUIDParameter, methodName);
        invalidParameterHandler.validatePaging(startFrom, pageSize, methodName);

        ValidValuesResponse restResult = restClient.callValidValuesGetRESTCall(methodName,
                                                                               serverPlatformRootURL + urlTemplate,
                                                                               serverName,
                                                                               userId,
                                                                               validValueSetGUID,
                                                                               Integer.toString(startFrom),
                                                                               Integer.toString(pageSize));
        return restResult.getValidValues();
    }


    /**
     * Page through the list of valid value sets that a valid value definition/set belongs to.
     *
     * @param userId calling user.
     * @param validValueGUID unique identifier of valid value to query
     * @param startFrom paging starting point
     * @param pageSize maximum number of return values.
     *
     * @return list of valid value beans
     *
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public List<ValidValue> getSetsForValidValue(String   userId,
                                                 String   validValueGUID,
                                                 int      startFrom,
                                                 int      pageSize) throws InvalidParameterException,
                                                                           UserNotAuthorizedException,
                                                                           PropertyServerException
    {
        final String   methodName = "getSetsForValidValue";
        final String   validValueGUIDParameter = "validValueSetGUID";
        final String   urlTemplate = "/servers/{0}/open-metadata/access-services/asset-owner/users/{1}/valid-values/{2}/set-membership?startFrom={3}&pageSize={4}";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateGUID(validValueGUID, validValueGUIDParameter, methodName);
        invalidParameterHandler.validatePaging(startFrom, pageSize, methodName);

        ValidValuesResponse restResult = restClient.callValidValuesGetRESTCall(methodName,
                                                                               serverPlatformRootURL + urlTemplate,
                                                                               serverName,
                                                                               userId,
                                                                               validValueGUID,
                                                                               Integer.toString(startFrom),
                                                                               Integer.toString(pageSize));
        return restResult.getValidValues();
    }
}
