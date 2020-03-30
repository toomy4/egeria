/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.repositoryservices.enterprise.repositoryconnector.accumulators;

import org.odpi.openmetadata.repositoryservices.ffdc.OMRSErrorCode;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.*;

/**
 * ExceptionAccumulatorBase collections exception responses from multiple queries.
 */
public class ExceptionAccumulatorBase
{
    ClassificationErrorException    classificationErrorException    = null;
    EntityNotDeletedException       entityNotDeletedException       = null;
    EntityNotKnownException         entityNotKnownException         = null;
    EntityProxyOnlyException        entityProxyOnlyException        = null;
    FunctionNotSupportedException   functionNotSupportedException   = null;
    InvalidParameterException       invalidParameterException       = null;
    InvalidTypeDefException         invalidTypeDefException         = null;
    PagingErrorException            pagingErrorException            = null;
    PropertyErrorException          propertyErrorException          = null;
    RelationshipNotDeletedException relationshipNotDeletedException = null;
    RelationshipNotKnownException   relationshipNotKnownException   = null;
    RepositoryErrorException        repositoryErrorException        = null;
    StatusNotSupportedException     statusNotSupportedException     = null;
    Throwable                       anotherException                = null;
    TypeDefConflictException        typeDefConflictException        = null;
    TypeDefNotKnownException        typeDefNotKnownException        = null;
    TypeDefNotSupportedException    typeDefNotSupportedException    = null;
    TypeErrorException              typeErrorException              = null;
    UserNotAuthorizedException      userNotAuthorizedException      = null;


    /**
     * Constructor restricted to use by this package
     */
    ExceptionAccumulatorBase()
    {
    }


    /**
     * Throw a ClassificationErrorException if it was returned by one of the calls to a cohort connector.
     *
     * @throws ClassificationErrorException the classification is not known
     */
    public synchronized void throwCapturedClassificationErrorException() throws ClassificationErrorException
    {
        if (classificationErrorException != null)
        {
            throw classificationErrorException;
        }
    }


    /**
     * Throw a EntityNotDeletedException if it was returned by one of the calls to a cohort connector.
     *
     * @throws EntityNotDeletedException the entity is not in deleted status
     */
    public synchronized void throwCapturedEntityNotDeletedException() throws EntityNotDeletedException
    {
        if (entityNotDeletedException != null)
        {
            throw entityNotDeletedException;
        }
    }


    /**
     * Throw a EntityNotKnownException if it was returned by one of the calls to a cohort connector.
     *
     * @throws EntityNotKnownException the entity is not known in any of the federated repositories
     */
    public synchronized void throwCapturedEntityNotKnownException() throws EntityNotKnownException
    {
        if (entityNotKnownException != null)
        {
            throw entityNotKnownException;
        }
    }


    /**
     * Throw a EntityProxyOnlyException if it was returned by one of the calls to a cohort connector.
     *
     * @throws EntityProxyOnlyException only entity proxies have been found in the available federated repositories
     */
    public synchronized void throwCapturedEntityProxyOnlyException() throws EntityProxyOnlyException
    {
        if (entityProxyOnlyException != null)
        {
            throw entityProxyOnlyException;
        }

        // todo add audit log call as this exception may indicate one of the repositories in the
        // todo may be in trouble.
    }


    /**
     * Throw a FunctionNotSupportedException if it was returned by all of the calls to the cohort connectors.
     *
     * @throws FunctionNotSupportedException the requested function is not supported in any of the federated repositories
     */
    public synchronized void throwCapturedFunctionNotSupportedException() throws FunctionNotSupportedException
    {
        if (functionNotSupportedException != null)
        {
            throw functionNotSupportedException;
        }
    }


    /**
     * Throw a InvalidParameterException if it was returned by one of the calls to a cohort connector.
     *
     * @throws InvalidParameterException one of the parameters is invalid
     */
    public synchronized void throwCapturedInvalidParameterException() throws InvalidParameterException
    {
        if (invalidParameterException != null)
        {
            throw invalidParameterException;
        }
    }


    /**
     * Throw a TypeDefNotKnownException if it was returned by one of the calls to a cohort connector.
     *
     * @throws InvalidTypeDefException the type definition is not of the correct format
     */
    public synchronized void throwCapturedInvalidTypeDefException() throws InvalidTypeDefException
    {
        if (invalidTypeDefException != null)
        {
            throw invalidTypeDefException;
        }
    }


    /**
     * Throw a PropertyErrorException if it was returned by one of the calls to a cohort connector.
     *
     * @throws PropertyErrorException the properties are not valid for the call
     */
    public synchronized void throwCapturedPropertyErrorException() throws PropertyErrorException
    {
        if (propertyErrorException != null)
        {
            throw propertyErrorException;
        }
    }


    /**
     * Throw a PagingErrorException if it was returned by one of the calls to a cohort connector.
     *
     * @throws PagingErrorException the properties are not valid for the call
     */
    public synchronized void throwCapturedPagingErrorException() throws PagingErrorException
    {
        if (pagingErrorException != null)
        {
            throw pagingErrorException;
        }
    }


    /**
     * Throw a RelationshipNotDeletedException if it was returned by one of the calls to a cohort connector.
     *
     * @throws RelationshipNotDeletedException the relationship is not in deleted status
     */
    public synchronized void throwCapturedRelationshipNotDeletedException() throws RelationshipNotDeletedException
    {
        if (relationshipNotDeletedException != null)
        {
            throw relationshipNotDeletedException;
        }
    }


    /**
     * Throw a RelationshipNotKnownException if it was returned by one of the calls to a cohort connector.
     *
     * @throws RelationshipNotKnownException the relationship is not known in any of the federated repositories
     */
    public synchronized void throwCapturedRelationshipNotKnownException() throws RelationshipNotKnownException
    {
        if (relationshipNotKnownException != null)
        {
            throw relationshipNotKnownException;
        }
    }


    /**
     * Throw a RepositoryErrorException if it was returned by one of the calls to a cohort connector.
     *
     * @throws RepositoryErrorException there was an error in the repository
     */
    public synchronized void throwCapturedRepositoryErrorException() throws RepositoryErrorException
    {
        if (repositoryErrorException != null)
        {
            throw repositoryErrorException;
        }
    }


    /**
     * Throw a StatusNotSupportedException if it was returned by one of the calls to a cohort connector.
     *
     * @throws StatusNotSupportedException the status is not known in any of the federated repositories
     */
    public synchronized void throwCapturedStatusNotSupportedException() throws StatusNotSupportedException
    {
        if (statusNotSupportedException != null)
        {
            throw statusNotSupportedException;
        }
    }


    /**
     * Throw a RepositoryErrorException if an unexpected Throwable exception was returned by one of the calls
     * to a cohort connector.
     *
     * @param methodName calling method
     * @throws RepositoryErrorException there was an unexpected error in the repository
     */
    public synchronized void throwCapturedThrowableException(String      methodName) throws RepositoryErrorException
    {
        if (anotherException != null)
        {
            throw new RepositoryErrorException(OMRSErrorCode.UNEXPECTED_EXCEPTION_FROM_COHORT.getMessageDefinition(methodName,
                                                                                                                   anotherException.getMessage()),
                                               this.getClass().getName(),
                                               methodName);
        }
    }


    /**
     * Throw a TypeDefNotKnownException if it was returned by one of the calls to a cohort connector.
     *
     * @throws TypeDefConflictException the type definition conflicts across the cohort
     */
    public void throwCapturedTypeDefConflictException() throws TypeDefConflictException
    {
        if (typeDefConflictException != null)
        {
            throw typeDefConflictException;
        }
    }


    /**
     * Throw a TypeDefNotSupportedException if it was returned by one of the calls to a cohort connector.
     *
     * @throws TypeDefNotSupportedException the type definition is not supported any of the federated repositories
     */
    public void throwCapturedTypeDefNotSupportedException() throws TypeDefNotSupportedException
    {
        if (typeDefNotSupportedException != null)
        {
            throw typeDefNotSupportedException;
        }
    }


    /**
     * Throw a TypeDefNotKnownException if it was returned by one of the calls to a cohort connector.
     *
     * @throws TypeDefNotKnownException the type definition is not known in any of the federated repositories
     */
    public void throwCapturedTypeDefNotKnownException() throws TypeDefNotKnownException
    {
        if (typeDefNotKnownException != null)
        {
            throw typeDefNotKnownException;
        }
    }


    /**
     * Throw a TypeErrorException if it was returned by one of the calls to a cohort connector.
     *
     * @throws TypeErrorException the type definition of the instance is not known in any of the federated repositories
     */
    public void throwCapturedTypeErrorException() throws TypeErrorException
    {
        if (typeErrorException != null)
        {
            throw typeErrorException;
        }
    }


    /**
     * Throw a UserNotAuthorizedException if it was returned by one of the calls to a cohort connector.
     *
     * @throws UserNotAuthorizedException the userId is not authorized in the server
     */
    public void throwCapturedUserNotAuthorizedException() throws UserNotAuthorizedException
    {
        if (userNotAuthorizedException != null)
        {
            throw userNotAuthorizedException;
        }
    }
}
