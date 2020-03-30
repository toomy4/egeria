/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.commonservices.odf.metadatamanagement.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.odpi.openmetadata.frameworks.discovery.properties.DiscoveryEngineProperties;

import java.util.Arrays;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;


/**
 * DiscoveryEnginePropertiesResponse is the response structure used on the Discovery Engine OMAS REST API calls that returns a
 * DiscoveryEngineProperties object as a response.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class DiscoveryEnginePropertiesResponse extends ODFOMASAPIResponse
{
    private static final long    serialVersionUID = 1L;

    private DiscoveryEngineProperties discoveryEngineProperties = null;

    /**
     * Default constructor
     */
    public DiscoveryEnginePropertiesResponse()
    {
        super();
    }


    /**
     * Copy/clone constructor
     *
     * @param template object to copy
     */
    public DiscoveryEnginePropertiesResponse(DiscoveryEnginePropertiesResponse template)
    {
        super(template);

        if (template != null)
        {
            this.discoveryEngineProperties = template.getDiscoveryEngineProperties();
        }
    }


    /**
     * Return the properties object.
     *
     * @return properties object
     */
    public DiscoveryEngineProperties getDiscoveryEngineProperties()
    {
        if (discoveryEngineProperties == null)
        {
            return null;
        }
        else
        {
            return discoveryEngineProperties;
        }
    }


    /**
     * Set up the properties object.
     *
     * @param discoveryEngineProperties  properties object
     */
    public void setDiscoveryEngineProperties(DiscoveryEngineProperties discoveryEngineProperties)
    {
        this.discoveryEngineProperties = discoveryEngineProperties;
    }


    /**
     * JSON-style toString
     *
     * @return return string containing the property names and values
     */
    @Override
    public String toString()
    {
        return "DiscoveryEnginePropertiesResponse{" +
                "discoveryEngineProperties=" + discoveryEngineProperties +
                ", exceptionClassName='" + getExceptionClassName() + '\'' +
                ", exceptionCausedBy='" + getExceptionCausedBy() + '\'' +
                ", actionDescription='" + getActionDescription() + '\'' +
                ", relatedHTTPCode=" + getRelatedHTTPCode() +
                ", exceptionErrorMessage='" + getExceptionErrorMessage() + '\'' +
                ", exceptionErrorMessageId='" + getExceptionErrorMessageId() + '\'' +
                ", exceptionErrorMessageParameters=" + Arrays.toString(getExceptionErrorMessageParameters()) +
                ", exceptionSystemAction='" + getExceptionSystemAction() + '\'' +
                ", exceptionUserAction='" + getExceptionUserAction() + '\'' +
                ", exceptionProperties=" + getExceptionProperties() +
                '}';
    }


    /**
     * Return comparison result based on the content of the properties.
     *
     * @param objectToCompare test object
     * @return result of comparison
     */
    @Override
    public boolean equals(Object objectToCompare)
    {
        if (this == objectToCompare)
        {
            return true;
        }
        if (objectToCompare == null || getClass() != objectToCompare.getClass())
        {
            return false;
        }
        if (!super.equals(objectToCompare))
        {
            return false;
        }
        DiscoveryEnginePropertiesResponse that = (DiscoveryEnginePropertiesResponse) objectToCompare;
        return Objects.equals(getDiscoveryEngineProperties(), that.getDiscoveryEngineProperties());
    }

    
    /**
     * Return hash code for this object
     *
     * @return int hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), getDiscoveryEngineProperties());
    }
}
