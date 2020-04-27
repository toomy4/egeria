/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.repositoryservices.enterprise.repositoryconnector.executors;

import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.OMRSMetadataCollection;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.SequencingOrder;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.EntityDetail;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.InstanceStatus;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.search.SearchClassifications;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.search.SearchProperties;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryValidator;
import org.odpi.openmetadata.repositoryservices.enterprise.repositoryconnector.EnterpriseOMRSRepositoryConnector;
import org.odpi.openmetadata.repositoryservices.enterprise.repositoryconnector.accumulators.EntityAccumulator;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.*;

import java.util.Date;
import java.util.List;

/**
 * FindEntitiesExecutor is the executor for the findEntities request.
 * This request can be issued in parallel - the call to each request potentially running in a different thread.
 */
public class FindEntitiesExecutor extends PageableRepositoryExecutorBase
{

    private SearchProperties      matchProperties;
    private SearchClassifications matchClassifications;
    private List<String>          instanceSubtypeGUIDs;

    private EntityAccumulator  accumulator;

    /**
     * Create the executor.  The parameters provide the parameters for issuing the requests and
     * combining the results.
     *
     * @param userId unique identifier for requesting user.
     * @param entityTypeGUID String unique identifier for the entity type of interest (null means any entity type).
     * @param entitySubtypeGUIDs optional list of the unique identifiers (guids) for subtypes of the entityTypeGUID to
     *                           include in the search results. Null means all subtypes.
     * @param matchProperties Optional list of entity properties to match (contains wildcards).
     * @param fromEntityElement the starting element number of the entities to return.
     *                                This is used when retrieving elements
     *                                beyond the first page of results. Zero means start from the first element.
     * @param limitResultsByStatus By default, entities in all statuses are returned.  However, it is possible
     *                             to specify a list of statuses (eg ACTIVE) to restrict the results to.  Null means all
     *                             status values.
     * @param matchClassifications Optional list of entity classifications to match.
     * @param asOfTime Requests a historical query of the entity.  Null means return the present values.
     * @param sequencingProperty String name of the entity property that is to be used to sequence the results.
     *                           Null means do not sequence on a property name (see SequencingOrder).
     * @param sequencingOrder Enum defining how the results should be ordered.
     * @param pageSize the maximum number of result entities that can be returned on this request.  Zero means
     *                 unrestricted return results size.
     * @param localMetadataCollectionId unique identifier for the local repository - may be null if no local repository
     * @param auditLog logging destination
     * @param repositoryValidator validator for resulting relationships
     * @param methodName calling method
     */
    public FindEntitiesExecutor(String                    userId,
                                String                    entityTypeGUID,
                                List<String>              entitySubtypeGUIDs,
                                SearchProperties          matchProperties,
                                int                       fromEntityElement,
                                List<InstanceStatus>      limitResultsByStatus,
                                SearchClassifications     matchClassifications,
                                Date                      asOfTime,
                                String                    sequencingProperty,
                                SequencingOrder           sequencingOrder,
                                int                       pageSize,
                                String                    localMetadataCollectionId,
                                AuditLog                  auditLog,
                                OMRSRepositoryValidator   repositoryValidator,
                                String                    methodName)
    {
        this(userId,
             entityTypeGUID,
             entitySubtypeGUIDs,
             matchProperties,
             fromEntityElement,
             limitResultsByStatus,
             matchClassifications,
             asOfTime,
             sequencingProperty,
             sequencingOrder,
             pageSize,
             new EntityAccumulator(localMetadataCollectionId, auditLog, repositoryValidator),
             methodName);
    }


    /**
     * Create the executor.  The parameters provide the parameters for issuing the requests and
     * combining the results.
     *
     * @param userId unique identifier for requesting user.
     * @param entityTypeGUID String unique identifier for the entity type of interest (null means any entity type).
     * @param entitySubtypeGUIDs optional list of the unique identifiers (guids) for subtypes of the entityTypeGUID to
     *                           include in the search results. Null means all subtypes.
     * @param matchProperties Optional list of entity properties to match (contains wildcards).
     * @param fromEntityElement the starting element number of the entities to return.
     *                                This is used when retrieving elements
     *                                beyond the first page of results. Zero means start from the first element.
     * @param limitResultsByStatus By default, entities in all statuses are returned.  However, it is possible
     *                             to specify a list of statuses (eg ACTIVE) to restrict the results to.  Null means all
     *                             status values.
     * @param matchClassifications Optional list of entity classifications to match.
     * @param asOfTime Requests a historical query of the entity.  Null means return the present values.
     * @param sequencingProperty String name of the entity property that is to be used to sequence the results.
     *                           Null means do not sequence on a property name (see SequencingOrder).
     * @param sequencingOrder Enum defining how the results should be ordered.
     * @param pageSize the maximum number of result entities that can be returned on this request.  Zero means
     *                 unrestricted return results size.
     * @param accumulator location for results and returned exceptions
     * @param methodName calling method
     */
    private FindEntitiesExecutor(String                    userId,
                                 String                    entityTypeGUID,
                                 List<String>              entitySubtypeGUIDs,
                                 SearchProperties          matchProperties,
                                 int                       fromEntityElement,
                                 List<InstanceStatus>      limitResultsByStatus,
                                 SearchClassifications     matchClassifications,
                                 Date                      asOfTime,
                                 String                    sequencingProperty,
                                 SequencingOrder           sequencingOrder,
                                 int                       pageSize,
                                 EntityAccumulator         accumulator,
                                 String                    methodName)
    {
        super(userId,
              entityTypeGUID,
              fromEntityElement,
              limitResultsByStatus,
              sequencingProperty,
              sequencingOrder,
              pageSize,
              asOfTime,
              accumulator,
              methodName);

        this.matchProperties = matchProperties;
        this.matchClassifications = matchClassifications;
        this.instanceSubtypeGUIDs = entitySubtypeGUIDs;
        this.accumulator = accumulator;

    }


    /**
     * Return a clone of this executor with the same command parameters and accumulator instance.
     * This is used when setting up the parallel execution of the work.  Each clone executes
     * the calls to a single open metadata repository.
     *
     * @return clone of this executor
     */
    public CloneableRepositoryExecutor getClone()
    {
        return new FindEntitiesExecutor(userId,
                                        instanceTypeGUID,
                                        instanceSubtypeGUIDs,
                                        matchProperties,
                                        startingElement,
                                        limitResultsByStatus,
                                        matchClassifications,
                                        asOfTime,
                                        sequencingProperty,
                                        sequencingOrder,
                                        pageSize,
                                        accumulator,
                                        methodName);
    }


    /**
     * Perform the required action for the supplied repository.
     *
     * @param metadataCollectionId unique identifier for the metadata collection for the repository
     * @param metadataCollection metadata collection object for the repository
     * @return boolean true means that the required results have been achieved
     */
    public boolean issueRequestToRepository(String                 metadataCollectionId,
                                            OMRSMetadataCollection metadataCollection)
    {
        try
        {
            /*
             * Issue the request
             */
            List<EntityDetail> results = metadataCollection.findEntities(userId,
                                                                         instanceTypeGUID,
                                                                         instanceSubtypeGUIDs,
                                                                         matchProperties,
                                                                         startingElement,
                                                                         limitResultsByStatus,
                                                                         matchClassifications,
                                                                         asOfTime,
                                                                         sequencingProperty,
                                                                         sequencingOrder,
                                                                         pageSize);

            accumulator.addEntities(results, metadataCollectionId);
        }
        catch (InvalidParameterException error)
        {
            accumulator.captureException(metadataCollectionId, error);
        }
        catch (FunctionNotSupportedException error)
        {
            accumulator.captureException(metadataCollectionId, error);
        }
        catch (TypeErrorException error)
        {
            accumulator.captureException(metadataCollectionId, error);
        }
        catch (PropertyErrorException error)
        {
            accumulator.captureException(metadataCollectionId, error);
        }
        catch (PagingErrorException error)
        {
            accumulator.captureException(metadataCollectionId, error);
        }
        catch (RepositoryErrorException error)
        {
            accumulator.captureException(metadataCollectionId, error);
        }
        catch (UserNotAuthorizedException error)
        {
            accumulator.captureException(metadataCollectionId, error);
        }
        catch (Throwable error)
        {
            accumulator.captureGenericException(metadataCollectionId, error);
        }

        return true;
    }


    /**
     * Return the results or exception.
     *
     * @param repositoryConnector enterprise connector
     * @return a list of entities matching the supplied criteria; null means no matching entities in the metadata
     * collection.
     * @throws InvalidParameterException a parameter is invalid or null.
     * @throws TypeErrorException the type guid passed on the request is not known by the
     *                              metadata collection.
     * @throws RepositoryErrorException there is a problem communicating with the metadata repository where
     *                                    the metadata collection is stored.
     * @throws PropertyErrorException the properties specified are not valid for any of the requested types of
     *                                  entity.
     * @throws PagingErrorException the paging/sequencing parameters are set up incorrectly.
     * @throws FunctionNotSupportedException the repository does not support the asOfTime parameter.
     * @throws UserNotAuthorizedException the userId is not permitted to perform this operation.
     */
    public List<EntityDetail> getResults(EnterpriseOMRSRepositoryConnector repositoryConnector) throws InvalidParameterException,
                                                                                                       RepositoryErrorException,
                                                                                                       TypeErrorException,
                                                                                                       PropertyErrorException,
                                                                                                       PagingErrorException,
                                                                                                       FunctionNotSupportedException,
                                                                                                       UserNotAuthorizedException
    {
        if (accumulator.resultsReturned())
        {
            return accumulator.getResults(repositoryConnector);
        }

        handleCommonPagingRequestExceptions();

        return null;
    }
}
