/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.commonservices.multitenant;

import org.odpi.openmetadata.frameworks.auditlog.AuditLog;

/**
 * GovernanceServerServiceInstance caches references to OMRS objects for a specific server.
 * It is also responsible for registering itself in the instance map.
 */
public abstract class GovernanceServerServiceInstance extends AuditableServerServiceInstance
{
    private String        accessServiceRootURL;
    private String        accessServiceServerName;
    private String        accessServiceInTopicName  = null;
    private String        accessServiceOutTopicName = null;

    /**
     * Constructor where REST Services used.
     *
     * @param serverName name of this server
     * @param serviceName name of this service
     * @param auditLog link to the repository responsible for servicing the REST calls.
     * @param maxPageSize maximum number of results that can be returned in a single request
     * @param localServerUserId userId to use for server initiated requests
     * @param accessServiceRootURL URL root for server platform where the access service is running.
     * @param accessServiceServerName name of the server where the access service is running.
     */
    public GovernanceServerServiceInstance(String        serverName,
                                           String        serviceName,
                                           AuditLog      auditLog,
                                           String        localServerUserId,
                                           int           maxPageSize,
                                           String        accessServiceRootURL,
                                           String        accessServiceServerName)
    {
        super(serverName, serviceName, auditLog, localServerUserId, maxPageSize);

        this.accessServiceRootURL = accessServiceRootURL;
        this.accessServiceServerName = accessServiceServerName;
    }


    /**
     * Constructor where all services used.
     *
     * @param serverName name of this server
     * @param serviceName name of this service
     * @param auditLog link to the repository responsible for servicing the REST calls.
     * @param localServerUserId userId to use for server initiated requests
     * @param maxPageSize maximum number of results that can be returned in a single request
     * @param accessServiceRootURL URL root for server platform where the access service is running.
     * @param accessServiceServerName name of the server where the access service is running.
     * @param accessServiceInTopicName topic name to send events to the access service.
     * @param accessServiceOutTopicName topic name to receive events from the access service.
     */
    public GovernanceServerServiceInstance(String        serverName,
                                           String        serviceName,
                                           AuditLog      auditLog,
                                           String        localServerUserId,
                                           int           maxPageSize,
                                           String        accessServiceRootURL,
                                           String        accessServiceServerName,
                                           String        accessServiceInTopicName,
                                           String        accessServiceOutTopicName)
    {
        super(serverName, serviceName, auditLog, localServerUserId, maxPageSize);

        this.accessServiceRootURL = accessServiceRootURL;
        this.accessServiceServerName = accessServiceServerName;
        this.accessServiceInTopicName = accessServiceInTopicName;
        this.accessServiceOutTopicName = accessServiceOutTopicName;
    }
}
