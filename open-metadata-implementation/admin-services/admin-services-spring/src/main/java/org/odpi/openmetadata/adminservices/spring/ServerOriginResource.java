/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.adminservices.spring;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.odpi.openmetadata.adminservices.OMAGServerOriginServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * ServerPlatformOriginResource provides the Spring wrapper for the origin service that helps the client
 * discover the type of the server.
 */
@RestController
@RequestMapping("/open-metadata/admin-services/users/{userId}")

@Tag(name="Administration Services", description="The administration services support the configuration of the open metadata and governance services within the OMAG Server Platform This configuration determines which of the open metadata and governance services are active.", externalDocs=@ExternalDocumentation(description="Administration Services",url="https://egeria.odpi.org/open-metadata-implementation/admin-services/"))

public class ServerOriginResource
{
    OMAGServerOriginServices originAPI = new OMAGServerOriginServices();

    /**
     * Return the origin of this server implementation.
     *
     * @param userId name of the user making the request
     * @param serverName name of the server that the request is for
     * @return String description
     */
    @GetMapping(path = "/servers/{serverName}/server-origin")
    @Deprecated
    public String getServerOrigin(@PathVariable String   userId,
                                  @PathVariable String   serverName)
    {
        return originAPI.getServerOrigin(userId);
    }

    /**
     * Return the origin of this server implementation.
     *
     * @param userId name of the user making the request
     * @return String description
     */
    @GetMapping(path = "/server-origin")
    @Deprecated
    public String getServerOrigin(@PathVariable String   userId)
    {
        return originAPI.getServerOrigin(userId);
    }
}
