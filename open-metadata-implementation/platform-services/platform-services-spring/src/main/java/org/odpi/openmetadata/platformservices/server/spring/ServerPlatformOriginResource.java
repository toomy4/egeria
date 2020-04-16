/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.platformservices.server.spring;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.odpi.openmetadata.platformservices.server.OMAGServerPlatformOriginServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * ServerPlatformOriginResource provides the Spring wrapper for the origin service that helps the client
 * discover the type of the server platform.
 */
@RestController
@RequestMapping("/open-metadata/platform-services/users/{userId}")

@Tag(name="Platform Services", description="The platform services provides the APIs for querying the Open Metadata and Governance (OMAG) Server Platform and discovering information about the OMAG Servers that it is hosting.", externalDocs=@ExternalDocumentation(description="Platform Services",url="https://egeria.odpi.org/open-metadata-implementation/platform-services"))

public class ServerPlatformOriginResource
{
    OMAGServerPlatformOriginServices originAPI = new OMAGServerPlatformOriginServices();

    /**
     * Return the origin of this server implementation.
     *
     * @param userId name of the user making the request
     * @return String description
     */
    @Deprecated
    @GetMapping(path = "/server-platform-origin")
    public String getOldServerOrigin(@PathVariable String   userId)
    {
        return originAPI.getServerPlatformOrigin(userId);
    }


    /**
     * Return the origin of this server implementation.
     *
     * @param userId name of the user making the request
     * @return String description
     */
    @GetMapping(path = "/server-platform/origin")
    public String getServerOrigin(@PathVariable String   userId)
    {
        return originAPI.getServerPlatformOrigin(userId);
    }

}
