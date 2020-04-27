/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.frameworks.connectors.properties.beans;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Validate that the SchemaLink bean can be cloned, compared, serialized, deserialized and printed as a String.
 */
public class TestSchemaLink
{
    private Map<String, String> linkProperties       = new HashMap<>();


    /**
     * Default constructor
     */
    public TestSchemaLink()
    {
        linkProperties.put("Key", "Value");
    }


    /**
     * Set up an example object to test.
     *
     * @return filled in object
     */
    private SchemaLink getTestObject()
    {
        SchemaLink testObject = new SchemaLink();

        testObject.setLinkName("TestLinkName");
        testObject.setLinkType("TestLinkType");
        testObject.setLinkedSchemaTypeGUID("TestAttribute");
        testObject.setLinkProperties(linkProperties);

        return testObject;
    }


    /**
     * Validate that the object that comes out of the test has the same content as the original test object.
     *
     * @param resultObject object returned by the test
     */
    private void validateResultObject(SchemaLink resultObject)
    {
        assertTrue(resultObject.getLinkName().equals("TestLinkName"));
        assertTrue(resultObject.getLinkType().equals("TestLinkType"));
        assertTrue(resultObject.getLinkedSchemaTypeGUID().equals("TestAttribute"));
        assertTrue(resultObject.getLinkProperties().equals(linkProperties));
    }


    /**
     * Validate that the object is initialized properly
     */
    @Test public void testNullObject()
    {
        SchemaLink nullObject = new SchemaLink();

        assertTrue(nullObject.getLinkName() == null);
        assertTrue(nullObject.getLinkType() == null);
        assertTrue(nullObject.getLinkedSchemaTypeGUID() == null);
        assertTrue(nullObject.getLinkProperties() == null);

        nullObject = new SchemaLink(null);

        assertTrue(nullObject.getLinkName() == null);
        assertTrue(nullObject.getLinkType() == null);
        assertTrue(nullObject.getLinkedSchemaTypeGUID() == null);
        assertTrue(nullObject.getLinkProperties() == null);

        nullObject = new SchemaLink(null);

        nullObject.setLinkProperties(new HashMap<>());

        assertTrue(nullObject.getLinkProperties() == null);
    }


    /**
     * Validate that 2 different objects with the same content are evaluated as equal.
     * Also that different objects are considered not equal.
     */
    @Test public void testEquals()
    {
        assertFalse(getTestObject().equals(null));
        assertFalse(getTestObject().equals("DummyString"));
        assertTrue(getTestObject().equals(getTestObject()));

        SchemaLink sameObject = getTestObject();
        assertTrue(sameObject.equals(sameObject));
    }


    /**
     *  Validate that 2 different objects with the same content have the same hash code.
     */
    @Test public void testHashCode()
    {
        assertTrue(getTestObject().hashCode() == getTestObject().hashCode());
    }


    /**
     *  Validate that an object cloned from another object has the same content as the original
     */
    @Test public void testClone()
    {
        validateResultObject(new SchemaLink(getTestObject()));
    }


    /**
     * Validate that an object generated from a JSON String has the same content as the object used to
     * create the JSON String.
     */
    @Test public void testJSON()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        String       jsonString   = null;

        /*
         * This class
         */
        try
        {
            jsonString = objectMapper.writeValueAsString(getTestObject());
        }
        catch (Throwable  exc)
        {
            assertTrue(false, "Exception: " + exc.getMessage());
        }

        try
        {
            validateResultObject(objectMapper.readValue(jsonString, SchemaLink.class));
        }
        catch (Throwable  exc)
        {
            assertTrue(false, "Exception: " + exc.getMessage());
        }

        /*
         * Through superclass
         */
        PropertyBase propertyBase = getTestObject();

        try
        {
            jsonString = objectMapper.writeValueAsString(propertyBase);
        }
        catch (Throwable  exc)
        {
            assertTrue(false, "Exception: " + exc.getMessage());
        }

        try
        {
            validateResultObject((SchemaLink)objectMapper.readValue(jsonString, PropertyBase.class));
        }
        catch (Throwable  exc)
        {
            assertTrue(false, "Exception: " + exc.getMessage());
        }
    }


    /**
     * Test that toString is overridden.
     */
    @Test public void testToString()
    {
        assertTrue(getTestObject().toString().contains("SchemaLink"));
    }
}
