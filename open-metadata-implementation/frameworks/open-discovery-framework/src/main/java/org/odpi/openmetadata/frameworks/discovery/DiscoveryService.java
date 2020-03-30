/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.frameworks.discovery;

import org.odpi.openmetadata.frameworks.connectors.Connector;
import org.odpi.openmetadata.frameworks.connectors.ConnectorBase;
import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectorCheckedException;
import org.odpi.openmetadata.frameworks.discovery.ffdc.DiscoveryServiceException;
import org.odpi.openmetadata.frameworks.discovery.ffdc.ODFErrorCode;

import java.util.ArrayList;
import java.util.List;


/**
 * DiscoveryService describes a specific type of connector that is responsible for analyzing the content
 * of a specific asset.  Information about the asset to analyze is passed in the discovery context.
 * The returned discovery context also contains the results.
 *
 * Some discovery services manage the invocation of other discovery services.  These discovery services are called
 * discovery pipelines.
 */
public abstract class DiscoveryService extends ConnectorBase
{
    protected String           discoveryServiceName = "<Unknown>";
    protected DiscoveryContext discoveryContext = null;


    /**
     * Set up details of the asset to analyze and the results of any previous analysis.
     *
     * @param discoveryContext information about the asset to analyze and the results of analysis of
     *                         other discovery service request.  Partial results from other discovery
     *                         services run as part of the same discovery service request may also be
     *                         stored in the newAnnotations list.
     */
    public synchronized void setDiscoveryContext(DiscoveryContext discoveryContext)
    {
        this.discoveryContext = discoveryContext;
    }


    /**
     * Set up the discovery service name.  This is used in error messages.
     *
     * @param discoveryServiceName name of the discovery service
     */
    public void setDiscoveryServiceName(String discoveryServiceName)
    {
        this.discoveryServiceName = discoveryServiceName;
    }


    /**
     * Return the discovery context for this discovery service.  This is typically called after the disconnect()
     * method is called.  If called before disconnect(), it may only contain partial results.
     *
     * @return discovery context containing the results discovered (so far) by the discovery service.
     */
    public synchronized DiscoveryContext getDiscoveryContext()
    {
        return discoveryContext;
    }


    /**
     * Retrieve and validate the list of embedded connectors and cast them to discovery service connector.
     * This is used by DiscoveryPipelines and DiscoveryScanningServices.
     *
     * @param embeddedConnectors list of supplied connector instances supplied by the Connector Broker.
     *
     * @return list of discovery service connectors
     *
     * @throws DiscoveryServiceException one of the embedded connectors is not a discovery service
     */
    protected List<DiscoveryService> getEmbeddedDiscoveryServices(List<Connector>  embeddedConnectors) throws DiscoveryServiceException
    {
        final String           methodName        = "getEmbeddedDiscoveryServices";
        List<DiscoveryService> discoveryServices = null;

        if (embeddedConnectors != null)
        {
            discoveryServices = new ArrayList<>();

            for (Connector embeddedConnector : embeddedConnectors)
            {
                if (embeddedConnector != null)
                {
                    if (embeddedConnector instanceof DiscoveryService)
                    {
                        discoveryServices.add((DiscoveryService)embeddedConnector);
                    }
                    else
                    {
                        throw new DiscoveryServiceException(ODFErrorCode.INVALID_EMBEDDED_DISCOVERY_SERVICE.getMessageDefinition(discoveryServiceName),
                                                            this.getClass().getName(),
                                                            methodName);
                    }
                }
            }

            if (discoveryServices.isEmpty())
            {
                discoveryServices = null;
            }
        }

        return discoveryServices;
    }


    /**
     * Indicates that the discovery service is completely configured and can begin processing.
     *
     * @throws DiscoveryServiceException there is a problem within the discovery service.
     */
    public void start() throws ConnectorCheckedException
    {
        super.start();

        final String methodName = "start";

        if (discoveryContext == null)
        {
            throw new DiscoveryServiceException(ODFErrorCode.NULL_DISCOVERY_CONTEXT.getMessageDefinition(discoveryServiceName),
                                                this.getClass().getName(),
                                                methodName);
        }
    }


    /**
     * Provide a common exception for unexpected errors.
     *
     * @param methodName calling method
     * @param error caught exception
     * @throws ConnectorCheckedException wrapped exception
     */
    protected void handleUnexpectedException(String      methodName,
                                             Throwable   error) throws ConnectorCheckedException
    {
        throw new DiscoveryServiceException(ODFErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(discoveryServiceName,
                                                                                                   error.getClass().getName(),
                                                                                                   methodName,
                                                                                                   error.getMessage()),
                                            this.getClass().getName(),
                                            methodName);
    }


    /**
     * Free up any resources held since the connector is no longer needed.
     *
     * @throws ConnectorCheckedException there is a problem within the discovery service.
     */
    public  void disconnect() throws ConnectorCheckedException
    {
        final String methodName = "disconnect";

        super.disconnect();
    }
}
