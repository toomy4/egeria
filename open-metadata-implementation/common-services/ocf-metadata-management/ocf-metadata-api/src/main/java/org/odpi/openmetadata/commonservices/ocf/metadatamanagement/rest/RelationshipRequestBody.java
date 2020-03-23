/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.commonservices.ocf.metadatamanagement.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * RelationshipRequestBody provides a structure for passing information about an asset's origin as
 * a request body over a REST API.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class RelationshipRequestBody extends OCFOMASAPIRequestBody
{
    private static final long    serialVersionUID = 1L;

    private String              targetAssetGUID = null;
    private String              relationshipTypeGUID   = null;


    /**
     * Default constructor
     */
    public RelationshipRequestBody()
    {
    }


    /**
     * Copy/clone constructor
     *
     * @param template object to copy
     */
    public RelationshipRequestBody(RelationshipRequestBody template)
    {
        super(template);

        if (template != null)
        {
            this.targetAssetGUID = template.getTargetAssetGUID();
            this.relationshipTypeGUID = template.getRelationshipTypeGUID();
        }
    }

    public String getTargetAssetGUID() {
        return targetAssetGUID;
    }

    public void setTargetAssetGUID(String targetAssetGUID) {
        this.targetAssetGUID = targetAssetGUID;
    }

    public String getRelationshipTypeGUID() {
        return relationshipTypeGUID;
    }

    public void setRelationshipTypeGUID(String relationshipTypeGUID) {
        this.relationshipTypeGUID = relationshipTypeGUID;
    }

    /**
     * JSON-style toString.
     *
     * @return list of properties and their values.
     */
    @Override
    public String toString()
    {
        return "RelationshipRequestBody{" +
                "targetAssetGUID='" + targetAssetGUID + '\'' +
                ", relationshipTypeGUID='" + relationshipTypeGUID + '\'' +
                '}';
    }


    /**
     * Equals method that returns true if containing properties are the same.
     *
     * @param objectToCompare object to compare
     * @return boolean result of comparison
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
        RelationshipRequestBody that = (RelationshipRequestBody) objectToCompare;
        return Objects.equals(getTargetAssetGUID(), that.getTargetAssetGUID()) &&
                Objects.equals(getRelationshipTypeGUID(), that.getRelationshipTypeGUID());
    }



    /**
     * Return hash code for this object
     *
     * @return int hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(getTargetAssetGUID(), getRelationshipTypeGUID());
    }
}
