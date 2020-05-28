/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.commonservices.multitenant;

import org.odpi.openmetadata.commonservices.ffdc.exceptions.PropertyServerException;
import org.odpi.openmetadata.commonservices.multitenant.ffdc.exceptions.NewInstanceException;
import org.odpi.openmetadata.commonservices.ocf.metadatamanagement.handlers.*;
import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryConnector;

import java.util.List;

/**
 * OCFOMASServiceInstance provides an instance base class for Open Metadata Access Services (OMASs)
 * that are built around the Open Connector Framework (OCF).
 */
public class OCFOMASServiceInstance extends OMASServiceInstance
{
    protected AssetHandler                    assetHandler;
    protected CertificationHandler            certificationHandler;
    protected CommentHandler                  commentHandler;
    protected ConnectionHandler               connectionHandler;
    protected ConnectorTypeHandler            connectorTypeHandler;
    protected EndpointHandler                 endpointHandler;
    protected ExternalIdentifierHandler       externalIdentifierHandler;
    protected ExternalReferenceHandler        externalReferenceHandler;
    protected FileSystemHandler               fileSystemHandler;
    protected GlossaryTermHandler             glossaryTermHandler;
    protected InformalTagHandler              informalTagHandler;
    protected LicenseHandler                  licenseHandler;
    protected LikeHandler                     likeHandler;
    protected LocationHandler                 locationHandler;
    protected MeaningHandler                  meaningHandler;
    protected NoteHandler                     noteHandler;
    protected NoteLogHandler                  noteLogHandler;
    protected RatingHandler                   ratingHandler;
    protected ReferenceableHandler            referenceableHandler;
    protected RelatedMediaHandler             relatedMediaHandler;
    protected SchemaTypeHandler               schemaTypeHandler;
    protected SoftwareServerCapabilityHandler softwareServerCapabilityHandler;
    protected ValidValuesHandler              validValuesHandler;
    protected RelationalDataHandler           relationalDataHandler;

    /**
     * Set up the local repository connector that will service the REST Calls.
     *
     * @param serviceName name of this service
     * @param repositoryConnector link to the repository responsible for servicing the REST calls.
     * @param auditLog logging destination
     * @throws NewInstanceException a problem occurred during initialization
     */
    @Deprecated
    public OCFOMASServiceInstance(String                  serviceName,
                                  OMRSRepositoryConnector repositoryConnector,
                                  AuditLog                auditLog) throws NewInstanceException
    {
        this(serviceName, repositoryConnector, null, null, null, auditLog, null, 500);
    }


    /**
     * Set up the local repository connector that will service the REST Calls.
     *
     * @param serviceName name of this service
     * @param repositoryConnector link to the repository responsible for servicing the REST calls.
     * @param supportedZones list of zones that DiscoveryEngine is allowed to serve Assets from.
     * @param defaultZones list of zones that DiscoveryEngine should set in all new Assets.
     * @param auditLog logging destination
     * @throws NewInstanceException a problem occurred during initialization
     */
    @Deprecated
    public OCFOMASServiceInstance(String                  serviceName,
                                  OMRSRepositoryConnector repositoryConnector,
                                  List<String>            supportedZones,
                                  List<String>            defaultZones,
                                  AuditLog                auditLog) throws NewInstanceException
    {
        this(serviceName, repositoryConnector, supportedZones, defaultZones, null, auditLog, null, 500);
    }


    /**
     * Set up the local repository connector that will service the REST Calls.
     *
     * @param serviceName name of this service
     * @param repositoryConnector link to the repository responsible for servicing the REST calls.
     * @param auditLog logging destination
     * @param localServerUserId userId used for server initiated actions
     * @param maxPageSize max number of results to return on single request.
     * @throws NewInstanceException a problem occurred during initialization
     */
    public OCFOMASServiceInstance(String                  serviceName,
                                  OMRSRepositoryConnector repositoryConnector,
                                  AuditLog                auditLog,
                                  String                  localServerUserId,
                                  int                     maxPageSize) throws NewInstanceException
    {
        this(serviceName,
             repositoryConnector,
             null,
             null,
             null,
             auditLog,
             localServerUserId,
             maxPageSize);
    }


    /**
     * Set up the local repository connector that will service the REST Calls.
     *
     * @param serviceName name of this service
     * @param repositoryConnector link to the repository responsible for servicing the REST calls.
     * @param supportedZones list of zones that DiscoveryEngine is allowed to serve Assets from.
     * @param defaultZones list of zones that DiscoveryEngine should set in all new Assets.
     * @param auditLog logging destination
     * @param localServerUserId userId used for server initiated actions
     * @param maxPageSize max number of results to return on single request.
     * @throws NewInstanceException a problem occurred during initialization
     */
    public OCFOMASServiceInstance(String                  serviceName,
                                  OMRSRepositoryConnector repositoryConnector,
                                  List<String>            supportedZones,
                                  List<String>            defaultZones,
                                  AuditLog                auditLog,
                                  String                  localServerUserId,
                                  int                     maxPageSize) throws NewInstanceException
    {
        this(serviceName, repositoryConnector, supportedZones, defaultZones, null, auditLog, localServerUserId, maxPageSize);
    }


    /**
     * Set up the local repository connector that will service the REST Calls.
     *
     * @param serviceName name of this service
     * @param repositoryConnector link to the repository responsible for servicing the REST calls.
     * @param supportedZones list of zones that the access service is allowed to serve Assets from.
     * @param defaultZones list of zones that the access service should set in all new Assets.
     * @param publishZones list of zones that the access service sets up in published Asset instances.
     * @param auditLog logging destination
     * @param localServerUserId userId used for server initiated actions
     * @param maxPageSize max number of results to return on single request.
     * @throws NewInstanceException a problem occurred during initialization
     */
    public OCFOMASServiceInstance(String                  serviceName,
                                  OMRSRepositoryConnector repositoryConnector,
                                  List<String>            supportedZones,
                                  List<String>            defaultZones,
                                  List<String>            publishZones,
                                  AuditLog                auditLog,
                                  String                  localServerUserId,
                                  int                     maxPageSize) throws NewInstanceException
    {
        super(serviceName, repositoryConnector, supportedZones, defaultZones, publishZones, auditLog, localServerUserId, maxPageSize);

        LastAttachmentHandler lastAttachmentHandler = new LastAttachmentHandler(serviceName,
                                                                                serverName,
                                                                                localServerUserId,
                                                                                repositoryHandler,
                                                                                repositoryHelper);

        this.certificationHandler      = new CertificationHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);
        this.commentHandler            = new CommentHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);
        this.connectionHandler         = new ConnectionHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);
        this.connectorTypeHandler      = new ConnectorTypeHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper);
        this.endpointHandler           = new EndpointHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper);
        this.externalIdentifierHandler = new ExternalIdentifierHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);
        this.externalReferenceHandler  = new ExternalReferenceHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);
        this.glossaryTermHandler       = new GlossaryTermHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);
        this.informalTagHandler        = new InformalTagHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);
        this.licenseHandler            = new LicenseHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);
        this.likeHandler               = new LikeHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);
        this.locationHandler           = new LocationHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);
        this.meaningHandler            = new MeaningHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);
        this.noteHandler               = new NoteHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);
        this.noteLogHandler            = new NoteLogHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);
        this.ratingHandler             = new RatingHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);
        this.referenceableHandler      = new ReferenceableHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper);
        this.relatedMediaHandler       = new RelatedMediaHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);
        this.schemaTypeHandler         = new SchemaTypeHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, meaningHandler, lastAttachmentHandler);
        this.softwareServerCapabilityHandler = new SoftwareServerCapabilityHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper);
        this.validValuesHandler        = new ValidValuesHandler(serviceName, serverName, invalidParameterHandler, repositoryHandler, repositoryHelper, lastAttachmentHandler);

        this.assetHandler              = new AssetHandler(serviceName,
                                                          serverName,
                                                          localServerUserId,
                                                          invalidParameterHandler,
                                                          repositoryHandler,
                                                          repositoryHelper,
                                                          certificationHandler,
                                                          commentHandler,
                                                          connectionHandler,
                                                          endpointHandler,
                                                          externalIdentifierHandler,
                                                          externalReferenceHandler,
                                                          informalTagHandler,
                                                          licenseHandler,
                                                          likeHandler,
                                                          locationHandler,
                                                          noteLogHandler,
                                                          ratingHandler,
                                                          relatedMediaHandler,
                                                          meaningHandler,
                                                          schemaTypeHandler,
                                                          validValuesHandler,
                                                          supportedZones,
                                                          defaultZones);

        validValuesHandler.setAssetHandler(assetHandler);

        /*
         * The following handlers add specialized methods on top of the basic OCF handlers
         */
        relationalDataHandler = new RelationalDataHandler(serviceName,
                                                          serverName,
                                                          invalidParameterHandler,
                                                          repositoryHandler,
                                                          repositoryHelper,
                                                          defaultZones,
                                                          supportedZones,
                                                          publishZones,
                                                          assetHandler,
                                                          connectionHandler,
                                                          schemaTypeHandler,
                                                          softwareServerCapabilityHandler,
                                                          glossaryTermHandler);

        fileSystemHandler = new FileSystemHandler(serviceName,
                                                  serverName,
                                                  invalidParameterHandler,
                                                  repositoryHandler,
                                                  repositoryHelper,
                                                  supportedZones,
                                                  assetHandler,
                                                  schemaTypeHandler,
                                                  softwareServerCapabilityHandler);

        if (securityVerifier != null)
        {
            assetHandler.setSecurityVerifier(securityVerifier);
            connectionHandler.setSecurityVerifier(securityVerifier);
        }
    }


    /**
     * Return the handler for managing asset objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    AssetHandler getAssetHandler() throws PropertyServerException
    {
        final String methodName = "getAssetHandler";

        validateActiveRepository(methodName);

        return assetHandler;
    }


    /**
     * Return the handler for managing certification objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    CertificationHandler getCertificationHandler() throws PropertyServerException
    {
        final String methodName = "getCertificationHandler";

        validateActiveRepository(methodName);

        return certificationHandler;
    }


    /**
     * Return the handler for managing comment objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    CommentHandler getCommentHandler() throws PropertyServerException
    {
        final String methodName = "getCommentHandler";

        validateActiveRepository(methodName);

        return commentHandler;
    }


    /**
     * Return the handler for managing connection objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    ConnectionHandler getConnectionHandler() throws PropertyServerException
    {
        final String methodName = "getConnectionHandler";

        validateActiveRepository(methodName);

        return connectionHandler;
    }


    /**
     * Return the handler for managing connector type objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    ConnectorTypeHandler getConnectorTypeHandler() throws PropertyServerException
    {
        final String methodName = "getConnectorTypeHandler";

        validateActiveRepository(methodName);

        return connectorTypeHandler;
    }


    /**
     * Return the handler for managing endpoint objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    EndpointHandler getEndpointHandler() throws PropertyServerException
    {
        final String methodName = "getEndpointHandler";

        validateActiveRepository(methodName);

        return endpointHandler;
    }


    /**
     * Return the handler for managing external identifier objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    ExternalIdentifierHandler getExternalIdentifierHandler() throws PropertyServerException
    {
        final String methodName = "getExternalIdentifierHandler";

        validateActiveRepository(methodName);

        return externalIdentifierHandler;
    }


    /**
     * Return the handler for managing external reference objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    ExternalReferenceHandler getExternalReferenceHandler() throws PropertyServerException
    {
        final String methodName = "getExternalReferenceHandler";

        validateActiveRepository(methodName);

        return externalReferenceHandler;
    }


    /**
     * Return the handler for managing filesystems, folders and files.
     *
     * @return file system handler
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    FileSystemHandler getFileSystemHandler() throws PropertyServerException
    {
        final String methodName = "getFileSystemHandler";

        validateActiveRepository(methodName);

        return fileSystemHandler;
    }


    /**
     * Return the handler for managing glossary objects.
     *
     * @return glossary handler
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    GlossaryTermHandler getGlossaryTermHandler() throws PropertyServerException
    {
        final String methodName = "getGlossaryTermHandler";

        validateActiveRepository(methodName);

        return glossaryTermHandler;
    }


    /**
     * Return the handler for managing informal tag objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    InformalTagHandler getInformalTagHandler() throws PropertyServerException
    {
        final String methodName = "getInformalTagHandler";

        validateActiveRepository(methodName);

        return informalTagHandler;
    }


    /**
     * Return the handler for managing license objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    LicenseHandler getLicenseHandler() throws PropertyServerException
    {
        final String methodName = "getLicenseHandler";

        validateActiveRepository(methodName);

        return licenseHandler;
    }


    /**
     * Return the handler for managing like objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    LikeHandler getLikeHandler() throws PropertyServerException
    {
        final String methodName = "getLikeHandler";

        validateActiveRepository(methodName);

        return likeHandler;
    }


    /**
     * Return the handler for managing location objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    LocationHandler getLocationHandler() throws PropertyServerException
    {
        final String methodName = "getLocationHandler";

        validateActiveRepository(methodName);

        return locationHandler;
    }


    /**
     * Return the handler for managing meaning objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    MeaningHandler getMeaningHandler() throws PropertyServerException
    {
        final String methodName = "getMeaningHandler";

        validateActiveRepository(methodName);

        return meaningHandler;
    }


    /**
     * Return the handler for managing note objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    NoteHandler getNoteHandler() throws PropertyServerException
    {
        final String methodName = "getNoteHandler";

        validateActiveRepository(methodName);

        return noteHandler;
    }


    /**
     * Return the handler for managing note log objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    NoteLogHandler getNoteLogHandler() throws PropertyServerException
    {
        final String methodName = "getNoteLogHandler";

        validateActiveRepository(methodName);

        return noteLogHandler;
    }


    /**
     * Return the handler for managing rating objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    RatingHandler getRatingHandler() throws PropertyServerException
    {
        final String methodName = "getRatingHandler";

        validateActiveRepository(methodName);

        return ratingHandler;
    }


    /**
     * Return the handler for managing generic referenceable objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    ReferenceableHandler getReferenceableHandler() throws PropertyServerException
    {
        final String methodName = "getReferenceableHandler";

        validateActiveRepository(methodName);

        return referenceableHandler;
    }


    /**
     * Return the handler for managing related media objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    RelatedMediaHandler getRelatedMediaHandler() throws PropertyServerException
    {
        final String methodName = "getRelatedMediaHandler";

        validateActiveRepository(methodName);

        return relatedMediaHandler;
    }


    /**
     * Return the handler for managing relational data assets, connections and schema.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    RelationalDataHandler getRelationalDataHandler() throws PropertyServerException
    {
        final String methodName = "getRelationalDataHandler";

        validateActiveRepository(methodName);

        return relationalDataHandler;
    }


    /**
     * Return the handler for managing schema objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    SchemaTypeHandler getSchemaTypeHandler() throws PropertyServerException
    {
        final String methodName = "getSchemaTypeHandler";

        validateActiveRepository(methodName);

        return schemaTypeHandler;
    }


    /**
     * Return the handler for managing software server capability objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    SoftwareServerCapabilityHandler getSoftwareServerCapabilityHandler() throws PropertyServerException
    {
        final String methodName = "getSoftwareServerCapabilityHandler";

        validateActiveRepository(methodName);

        return softwareServerCapabilityHandler;
    }


    /**
     * Return the handler for managing valid value objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    ValidValuesHandler getValidValuesHandler() throws PropertyServerException
    {
        final String methodName = "getValidValuesHandler";

        validateActiveRepository(methodName);

        return validValuesHandler;
    }
}
