/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.frameworks.connectors.properties.beans;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;


/**
 * ValidValuesAssignmentDefinition describes the link to a valid value for a consumer of a valid value via a ValidValuesAssignment
 * relationship.
 * This relationship is used to make use of a valid value (typically valid value set) as a set of valid values for the referenceable (consumer).
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ValidValuesAssignmentDefinition extends ValidValuesAssignment
{
    private static final long serialVersionUID = 1L;

    private ValidValue validValue = null;


    /**
     * Default constructor
     */
    public ValidValuesAssignmentDefinition()
    {
        super();
    }


    /**
     * Copy clone constructor
     *
     * @param template object to copy
     */
    public ValidValuesAssignmentDefinition(ValidValuesAssignmentDefinition template)
    {
        super(template);

        if (template != null)
        {
            validValue          = template.getValidValue();
        }
    }


    /**
     * Returns the properties of the valid value used in the assignment.
     *
     * @return properties of the valid value
     */
    public ValidValue getValidValue()
    {
        return validValue;
    }


    /**
     * Set up the properties of the valid value used in the assignment.
     *
     * @param validValue properties of the asset
     */
    public void setValidValue(ValidValue validValue)
    {
        this.validValue = validValue;
    }


    /**
     * JSON-style toString
     *
     * @return return string containing the property names and values
     */
    @Override
    public String toString()
    {
        return "ValidValuesAssignmentDefinition{" +
                "validValue=" + validValue +
                ", strictRequirement=" + isStrictRequirement() +
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
        ValidValuesAssignmentDefinition that = (ValidValuesAssignmentDefinition) objectToCompare;
        return Objects.equals(validValue, that.validValue);
    }


    /**
     * Return hash code for this object
     *
     * @return int hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), validValue);
    }
}
