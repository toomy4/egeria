/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.accessservices.assetowner.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.CommentType;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * CommentRequestBody provides a structure for passing a comment as a request body over a REST API.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CommentRequestBody extends ReferenceableRequestBody
{
    private static final long    serialVersionUID = 1L;

    private boolean     isPublic    = false;
    private CommentType commentType = null;
    private String      commentText = null;


    /**
     * Default constructor
     */
    public CommentRequestBody()
    {
    }


    /**
     * Copy/clone constructor
     *
     * @param template object to copy
     */
    public CommentRequestBody(CommentRequestBody template)
    {
        super(template);

        if (template != null)
        {
            this.commentType = template.getCommentType();
            this.commentText = template.getCommentText();
            this.isPublic = template.isPublic();
        }
    }



    /**
     * Return whether the feedback is private or not
     *
     * @return boolean
     */
    public boolean isPublic()
    {
        return isPublic;
    }


    /**
     * Set up the privacy flag.
     *
     * @param aPrivate boolean
     */
    public void setPublic(boolean aPrivate)
    {
        isPublic = aPrivate;
    }



    /**
     * Return the type of comment.
     * 
     * @return string
     */
    public CommentType getCommentType()
    {
        return commentType;
    }


    /**
     * Set up the type of comment.
     * 
     * @param commentType string
     */
    public void setCommentType(CommentType commentType)
    {
        this.commentType = commentType;
    }


    /**
     * Return the comment content.
     * 
     * @return string description
     */
    public String getCommentText()
    {
        return commentText;
    }


    /**
     * Set up the comment content.
     * 
     * @param commentText text.
     */
    public void setCommentText(String commentText)
    {
        this.commentText = commentText;
    }


    /**
     * JSON-style toString.
     *
     * @return list of properties and their values.
     */
    @Override
    public String toString()
    {
        return "CommentRequestBody{" +
                "isPublic=" + isPublic +
                ", commentType=" + commentType +
                ", commentText='" + commentText + '\'' +
                ", typeName='" + getTypeName() + '\'' +
                ", classifications=" + getClassifications() +
                ", qualifiedName='" + getQualifiedName() + '\'' +
                ", additionalProperties=" + getAdditionalProperties() +
                ", meanings=" + getMeanings() +
                ", extendedProperties=" + getExtendedProperties() +
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
        CommentRequestBody that = (CommentRequestBody) objectToCompare;
        return isPublic() == that.isPublic() &&
                getCommentType() == that.getCommentType() &&
                Objects.equals(getCommentText(), that.getCommentText());
    }


    /**
     * Return hash code for this object
     *
     * @return int hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(getCommentType(), getCommentText(), isPublic());
    }
}
