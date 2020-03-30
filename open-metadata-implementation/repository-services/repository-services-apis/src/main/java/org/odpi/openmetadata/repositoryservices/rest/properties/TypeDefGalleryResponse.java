/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.repositoryservices.rest.properties;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.AttributeTypeDef;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.TypeDef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * TypeDefGalleryResponse provides the response structure for an OMRS REST API call that returns a TypeDefGallery.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TypeDefGalleryResponse extends OMRSAPIResponse
{
    private static final long    serialVersionUID = 1L;

    private List<AttributeTypeDef> attributeTypeDefs = null;
    private List<TypeDef>          typeDefs          = null;


    /**
     * Default constructor
     */
    public TypeDefGalleryResponse()
    {
        super();
    }


    /**
     * Copy/clone constructor
     *
     * @param template object to copy
     */
    public TypeDefGalleryResponse(TypeDefGalleryResponse template)
    {
        super(template);

        if (template != null)
        {
            attributeTypeDefs = template.getAttributeTypeDefs();
            typeDefs = template.getTypeDefs();
        }
    }


    /**
     * Return the list of attribute type definitions from this gallery.
     *
     * @return list of AttributeTypeDefs
     */
    public List<AttributeTypeDef> getAttributeTypeDefs()
    {
        if (attributeTypeDefs == null)
        {
            return null;
        }
        else if (attributeTypeDefs.isEmpty())
        {
            return null;
        }
        else
        {
            List<AttributeTypeDef>  clonedTypeDefs = new ArrayList<>();

            for (AttributeTypeDef  attributeTypeDef : attributeTypeDefs)
            {
                clonedTypeDefs.add(attributeTypeDef.cloneFromSubclass());
            }

            return clonedTypeDefs;
        }
    }


    /**
     * Set up the list of attribute type definitions from this gallery.
     *
     * @param attributeTypeDefs list of AttributeTypeDefs
     */
    public void setAttributeTypeDefs(List<AttributeTypeDef> attributeTypeDefs)
    {
        this.attributeTypeDefs = attributeTypeDefs;
    }


    /**
     * Return the list of type definitions from this gallery
     *
     * @return list of TypeDefs
     */
    public List<TypeDef> getTypeDefs()
    {
        if (typeDefs == null)
        {
            return null;
        }
        else if (typeDefs.isEmpty())
        {
            return null;
        }
        else
        {
            List<TypeDef>  clonedTypeDefs = new ArrayList<>();

            for (TypeDef  typeDef : typeDefs)
            {
                clonedTypeDefs.add(typeDef.cloneFromSubclass());
            }

            return clonedTypeDefs;
        }
    }


    /**
     * Set up the list of type definitions for this gallery.
     *
     * @param typeDefs list of type definitions
     */
    public void setTypeDefs(List<TypeDef> typeDefs)
    {
        this.typeDefs = typeDefs;
    }


    /**
     * Standard toString method.
     *
     * @return print out of variables in a JSON-style
     */
    @Override
    public String toString()
    {
        return "TypeDefGalleryResponse{" +
                "attributeTypeDefs=" + attributeTypeDefs +
                ", typeDefs=" + typeDefs +
                ", relatedHTTPCode=" + relatedHTTPCode +
                ", actionDescription='" + actionDescription + '\'' +
                ", exceptionClassName='" + exceptionClassName + '\'' +
                ", exceptionCausedBy='" + exceptionCausedBy + '\'' +
                ", exceptionErrorMessage='" + exceptionErrorMessage + '\'' +
                ", exceptionErrorMessageId='" + exceptionErrorMessageId + '\'' +
                ", exceptionErrorMessageParameters=" + Arrays.toString(exceptionErrorMessageParameters) +
                ", exceptionSystemAction='" + exceptionSystemAction + '\'' +
                ", exceptionUserAction='" + exceptionUserAction + '\'' +
                ", exceptionProperties=" + exceptionProperties +
                '}';
    }


    /**
     * Compare the values of the supplied object with those stored in the current object.
     *
     * @param objectToCompare supplied object
     * @return boolean result of comparison
     */
    @Override
    public boolean equals(Object objectToCompare)
    {
        if (this == objectToCompare)
        {
            return true;
        }
        if (!(objectToCompare instanceof TypeDefGalleryResponse))
        {
            return false;
        }
        if (!super.equals(objectToCompare))
        {
            return false;
        }
        TypeDefGalleryResponse
                that = (TypeDefGalleryResponse) objectToCompare;
        return Objects.equals(getAttributeTypeDefs(), that.getAttributeTypeDefs()) &&
                Objects.equals(getTypeDefs(), that.getTypeDefs());
    }


    /**
     * Create a hash code for this element type.
     *
     * @return int hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), getAttributeTypeDefs(), getTypeDefs());
    }
}
