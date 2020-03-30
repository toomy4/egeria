/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.datascience.rest;

/**
 * MockAPIResponse enables the overridden methods of DataScienceOMASAPIResponse to be tested.
 */
public class MockAPIResponse extends DataScienceOMASAPIResponse
{
    private static final long    serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public MockAPIResponse()
    {
    }


    /**
     * Copy/clone constructor
     *
     * @param template object to clone
     */
    public MockAPIResponse(MockAPIResponse template)
    {
        super(template);
    }
}
