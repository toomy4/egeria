/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.adminservices.spring;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.odpi.openmetadata.adminservices.OMAGServerDataPlatformService;
import org.odpi.openmetadata.adminservices.configuration.properties.DataPlatformServicesConfig;
import org.odpi.openmetadata.commonservices.ffdc.rest.VoidResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/open-metadata/admin-services/users/{userId}/servers/{serverName}")

@Tag(name="Administration Services", description="The administration services support the configuration of the open metadata and governance services within the OMAG Server Platform This configuration determines which of the open metadata and governance services are active.", externalDocs=@ExternalDocumentation(description="Administration Services",url="https://egeria.odpi.org/open-metadata-implementation/admin-services/"))

public class DataPlatformServiceResource {


    private OMAGServerDataPlatformService adminAPI = new OMAGServerDataPlatformService();

    @PostMapping(path = "/data-platform-service/configuration")
    public VoidResponse setAccessServicesConfig(@PathVariable String userId,
                                                @PathVariable String serverName,
                                                @RequestBody DataPlatformServicesConfig dataPlatformServicesConfig)
    {
        return adminAPI.setDataPlatformServiceConfig(userId, serverName, dataPlatformServicesConfig);
    }

    @PostMapping(path = "/data-platform-service")
    public VoidResponse enableDataPlatformService(@PathVariable String userId,
                                                  @PathVariable String serverName)
    {
        return adminAPI.enableDataPlatformService(userId, serverName);
    }
}
