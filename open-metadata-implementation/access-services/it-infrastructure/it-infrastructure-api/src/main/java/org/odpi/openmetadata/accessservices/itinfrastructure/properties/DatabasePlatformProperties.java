/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.accessservices.itinfrastructure.properties;

import com.fasterxml.jackson.annotation.*;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * DatabasePlatformProperties is a class for representing a database platform.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class DatabasePlatformProperties extends SoftwareServerCapabilitiesProperties
{
    private static final long    serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public DatabasePlatformProperties()
    {
        super();
    }


    /**
     * Copy/clone constructor.
     *
     * @param template object to copy
     */
    public DatabasePlatformProperties(DatabasePlatformProperties template)
    {
        super(template);
    }


    /**
     * Standard toString method.
     *
     * @return print out of variables in a JSON-style
     */
    @Override
    public String toString()
    {
        return "DatabasePlatformProperties{" +
                "displayName='" + getDisplayName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", typeDescription='" + getTypeDescription() + '\'' +
                ", version='" + getVersion() + '\'' +
                ", patchLevel='" + getPatchLevel() + '\'' +
                ", source='" + getSource() + '\'' +
                ", qualifiedName='" + getQualifiedName() + '\'' +
                ", additionalProperties=" + getAdditionalProperties() +
                ", meanings=" + getMeanings() +
                ", classifications=" + getClassifications() +
                ", typeName='" + getTypeName() + '\'' +
                ", extendedProperties=" + getExtendedProperties() +
                '}';
    }
}
