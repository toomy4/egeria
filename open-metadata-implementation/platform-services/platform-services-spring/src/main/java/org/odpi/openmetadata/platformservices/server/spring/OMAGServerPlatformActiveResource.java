/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.platformservices.server.spring;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.odpi.openmetadata.commonservices.ffdc.rest.BooleanResponse;
import org.odpi.openmetadata.commonservices.ffdc.rest.RegisteredOMAGServicesResponse;
import org.odpi.openmetadata.platformservices.rest.ServerListResponse;
import org.odpi.openmetadata.platformservices.rest.ServerServicesListResponse;
import org.odpi.openmetadata.platformservices.rest.ServerStatusResponse;
import org.odpi.openmetadata.platformservices.server.OMAGServerPlatformActiveServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OMAGServerPlatformActiveResource allow an external caller to determine which servers are active on the
 * platform and the services that are active within them.
 */

@Tag(name="Platform Services", description="The platform services provides the APIs for querying the Open Metadata and Governance (OMAG) Server Platform and discovering information about the OMAG Servers that it is hosting.", externalDocs=@ExternalDocumentation(description="Platform Services",url="https://egeria.odpi.org/open-metadata-implementation/platform-services"))

@RestController
@RequestMapping("/open-metadata/platform-services/users/{userId}/server-platform")

public class OMAGServerPlatformActiveResource
{
    OMAGServerPlatformActiveServices  platformAPI = new OMAGServerPlatformActiveServices();


    /**
     * Return the list of access services that are registered (supported) in this OMAG Server Platform
     * and can be configured for a metadata server.
     *
     * @param userId calling user
     * @return list of service descriptions
     */
    @GetMapping(path = "/registered-services/access-services")
    @Operation( summary = "Get access services",
                description="Retrieve a list of access services registered on this platform",
                responses = {
                    @ApiResponse(responseCode = "200",description="list of service descriptions",
                            content = @Content(
                                    mediaType ="application/json",
                                    schema = @Schema(implementation=RegisteredOMAGServicesResponse.class)
                           )

                    )
                }
    )

    public RegisteredOMAGServicesResponse getRegisteredAccessServices(@Parameter(description="calling user") @PathVariable String userId)
    {
        return platformAPI.getRegisteredAccessServices(userId);
    }

    /**
     * Return the list of view services that are registered (supported) in this OMAG Server Platform
     * and can be configured for a metadata server.
     *
     * @param userId calling user
     * @return list of service descriptions
     */
    @GetMapping(path = "/registered-services/view-services")

    @Operation( summary = "Get view services",
            description="Retrieve a list of view services registered on this platform",
            responses = {
                    @ApiResponse(responseCode = "200",description="list of service descriptions",
                            content = @Content(
                                    mediaType ="application/json",
                                    schema = @Schema(implementation=RegisteredOMAGServicesResponse.class)
                            )

                    )
            })
    public RegisteredOMAGServicesResponse getRegisteredViewServices(@Parameter(description="calling user") @PathVariable String userId)
    {
        return platformAPI.getRegisteredViewServices(userId);
    }


    /**
     * Return the list of governance services that are registered (supported) in this OMAG Server Platform
     * and can be configured as part of a governance server.
     *
     * @param userId calling user
     * @return list of service descriptions
     */
    @GetMapping(path = "/registered-services/governance-services")
    @Operation( summary = "Get governance services",
            description="Retrieve a list of governance services registered on this platform",
            responses = {
                    @ApiResponse(responseCode = "200",description="list of service descriptions",
                            content = @Content(
                                    mediaType ="application/json",
                                    schema = @Schema(implementation=RegisteredOMAGServicesResponse.class)
                            )

                    )
            })
    public RegisteredOMAGServicesResponse getRegisteredGovernanceServices(@Parameter(description="calling user") @PathVariable String userId)
    {
        return platformAPI.getRegisteredGovernanceServices(userId);
    }


    /**
     * Return the list of common services that are registered (supported) in this OMAG Server Platform
     * and can be configured as part of any server.
     *
     * @param userId calling user
     * @return list of service descriptions
     */
    @GetMapping(path = "/registered-services/common-services")
    @Operation( summary = "Get common services",
            description="Retrieve a list of common services registered on this platform",
            responses = {
                    @ApiResponse(responseCode = "200",description="list of service descriptions",
                            content = @Content(
                                    mediaType ="application/json",
                                    schema = @Schema(implementation=RegisteredOMAGServicesResponse.class)
                            )

                    )
            })

    public RegisteredOMAGServicesResponse getRegisteredCommonServices(@Parameter(description="calling user") @PathVariable String userId)
    {
        return platformAPI.getRegisteredCommonServices(userId);
    }


    /**
     * Return the list of all services that are registered (supported) in this OMAG Server Platform.
     *
     * @param userId calling user
     * @return list of service descriptions
     */
    @GetMapping(path = "/registered-services")
    @Operation( summary = "Get all services",
            description="Retrieve a list of all services registered on this platform",
            responses = {
                    @ApiResponse(responseCode = "200",description="list of service descriptions",
                            content = @Content(
                                    mediaType ="application/json",
                                    schema = @Schema(implementation=RegisteredOMAGServicesResponse.class)
                            )

                    )
            })
    public RegisteredOMAGServicesResponse getAllRegisteredServices(@Parameter(description="calling user") @PathVariable String userId)
    {
        return platformAPI.getAllRegisteredServices(userId);
    }


    /**
     * Return a flag to indicate if this server has ever run on this OMAG Server Platform.
     *
     * @param userId calling user
     * @param serverName server of interest
     * @return flag
     */
    @GetMapping(path = "/servers/{serverName}/is-known")
    @Operation( summary = "Is server known",
            description="Return a boolean indication if this server has ever run on this platform",
            responses = {
                    @ApiResponse(responseCode = "200",description="boolean flag",
                            content = @Content(
                                    mediaType ="application/json",
                                    schema = @Schema(implementation=RegisteredOMAGServicesResponse.class)
                            )

                    )
            })
    public BooleanResponse isServerKnown(@Parameter(description="calling user") @PathVariable String    userId,
                                         @Parameter(description="server name") @PathVariable String    serverName)
    {
        return platformAPI.isServerKnown(userId, serverName);
    }


    /**
     * Return the list of OMAG Servers that have run or are running in this OMAG Server Platform.
     *
     * @param userId calling user
     * @return list of OMAG server names
     */
    @GetMapping(path = "/servers")
    @Operation( summary = "Get list of run servers",
            description="Return the list of servers that have run or are running on this platform",
            responses = {
                    @ApiResponse(responseCode = "200",description="list of servers",
                            content = @Content(
                                    mediaType ="application/json",
                                    schema = @Schema(implementation=ServerListResponse.class)
                            )

                    )
            })
    public ServerListResponse getKnownServerList(@Parameter(description="calling user") @PathVariable String userId)
    {
        return platformAPI.getKnownServerList(userId);
    }


    /**
     * Return the list of OMAG Servers that are active on this OMAG Server Platform.
     *
     * @param userId name of the user making the request
     * @return list of server names
     */
    @GetMapping(path = "/servers/active")
    @Operation( summary = "Get list of active servers",
            description="Return the list of servers that are active on this platform",
            responses = {
                    @ApiResponse(responseCode = "200",description="list of servers",
                            content = @Content(
                                    mediaType ="application/json",
                                    schema = @Schema(implementation= ServerListResponse.class)
                            )

                    )
            })
    public ServerListResponse getActiveServerList(@Parameter(description="calling user") @PathVariable String    userId)
    {
        return platformAPI.getActiveServerList(userId);
    }


    /**
     * Return information about when the server has been active.
     *
     * @param userId name of the user making the request
     * @param serverName name of the server of interest
     * @return details of the server status
     */
    @GetMapping(path = "/servers/{serverName}/status")

    @Operation( summary = "Get server status",
            description="Return information about when the server has been active",
            responses = {
                    @ApiResponse(responseCode = "200",description="details of server status",
                            content = @Content(
                                    mediaType ="application/json",
                            schema = @Schema(implementation=ServerStatusResponse.class)
                            )
                    )
            })
    public ServerStatusResponse getServerStatus(@Parameter(description="calling user") @PathVariable String    userId,
                                                @Parameter(description="server name") @PathVariable String    serverName)
    {
        return platformAPI.getServerStatus(userId, serverName);
    }


    /**
     * Return the list of services that are active on a specific OMAG Server that is active on this OMAG Server Platform.
     *
     * @param userId name of the user making the request
     * @param serverName name of the server of interest
     * @return server name and list od services running within
     */
    @GetMapping(path = "/servers/{serverName}/services")
    @Operation( summary = "Get active services for server",
            description="Return the list of services that are active on the server on this platform",
            responses = {
                    @ApiResponse(responseCode = "200",description="details of server status",
                            content = @Content(
                                    mediaType ="application/json",
                                    schema = @Schema(implementation=ServerServicesListResponse.class)
                            )

                    )
            })
    public ServerServicesListResponse getActiveServiceListForServer(@Parameter(description="calling user") @PathVariable String    userId,
                                                                    @Parameter(description="server name") @PathVariable String    serverName)
    {
        return platformAPI.getActiveServiceListForServer(userId, serverName);
    }
}
