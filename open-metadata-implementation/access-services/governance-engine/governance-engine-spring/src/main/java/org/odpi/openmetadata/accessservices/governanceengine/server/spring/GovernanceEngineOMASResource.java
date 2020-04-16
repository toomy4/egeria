/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.governanceengine.server.spring;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.odpi.openmetadata.accessservices.governanceengine.api.model.GovernedAssetListResponse;
import org.odpi.openmetadata.accessservices.governanceengine.api.model.GovernedAssetResponse;
import org.odpi.openmetadata.accessservices.governanceengine.api.model.SoftwareServerCapabilityRequestBody;
import org.odpi.openmetadata.accessservices.governanceengine.api.model.SoftwareServerCapabilityResponse;
import org.odpi.openmetadata.accessservices.governanceengine.server.services.GovernanceEngineRESTServices;
import org.odpi.openmetadata.commonservices.ffdc.rest.StringResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/servers/{serverName}/open-metadata/access-services/governance-engine/users/{userId}")

@Tag(name="Governance Engine OMAS", description="The Governance Engine Open Metadata Access Service (OMAS) provides access to metadata for policy enforcement frameworks such as Apache Ranger.", externalDocs=@ExternalDocumentation(description="Governance Engine Open Metadata Access Service (OMAS)",url="https://egeria.odpi.org/open-metadata-implementation/access-services/governance-engine/"))

public class GovernanceEngineOMASResource {

    private GovernanceEngineRESTServices restAPI = new GovernanceEngineRESTServices();

    /**
     * Returns the list of governed asset
     * <p>
     * These include the tag associations but not the definitions of those tags
     *
     * @param userId      - String - userId of user making request.
     * @param entityTypes - the type of the entities that are returned
     * @return GovernedAssetComponentList or
     * InvalidParameterException - one of the parameters is null or invalid.
     * UnrecognizedConnectionNameException - there is no connection defined for this name.
     * AmbiguousConnectionNameException - there is more than one connection defined for this name.
     * PropertyServerException - there is a problem retrieving information offset the property (metadata) handlers.
     * UserNotAuthorizedException - the requesting user is not authorized to issue this request.
     */
    @GetMapping(path = "/assets", produces = MediaType.APPLICATION_JSON_VALUE)
    public GovernedAssetListResponse getGovernedAssets(@PathVariable String serverName,
                                                       @PathVariable String userId,
                                                       @RequestParam(value = "entityTypes", required = false) List<String> entityTypes,
                                                       @RequestParam(value = "offset", required = false) Integer offset,
                                                       @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return restAPI.getGovernedAssets(serverName, userId, entityTypes, offset, pageSize);
    }

    /**
     * Returns a single governed asset
     * <p>
     * These include the tag associations but not the definitions of those tags
     *
     * @param userId    - String - userId of user making request.
     * @param assetGuid - Guid of the asset component to retrieve
     * @return GovernedAsset or
     * InvalidParameterException - one of the parameters is null or invalid.
     * UnrecognizedConnectionNameException - there is no connection defined for this name.
     * AmbiguousConnectionNameException - there is more than one connection defined for this name.
     * PropertyServerException - there is a problem retrieving information from the property (metadata) handlers.
     * UserNotAuthorizedException - the requesting user is not authorized to issue this request.
     */
    @GetMapping(path = "/assets/{assetGuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GovernedAssetResponse getGovernedAsset(@PathVariable String serverName,
                                                  @PathVariable String userId,
                                                  @PathVariable String assetGuid) {
        return restAPI.getGovernedAsset(serverName, userId, assetGuid);
    }

    /**
     * Create a Software Server Capability entity
     *
     * @param serverName  - String - the name of server instance to call
     * @param userId      - String - userId of user making request.
     * @param requestBody - SoftwareServerCapabilityRequestBody
     * @return the Software Server entity created
     */
    @PostMapping(path = "/software-server-capabilities")
    public StringResponse createSoftwareServerCapability(@PathVariable("serverName") String serverName,
                                                         @PathVariable("userId") String userId,
                                                         @RequestBody SoftwareServerCapabilityRequestBody requestBody) {
        return restAPI.createSoftwareServer(serverName, userId, requestBody);
    }


    /**
     * Returns the Software Server Capability entity by global identifier
     *
     * @param serverName name of server instance to call
     * @param userId     the name of the calling user
     * @param guid       guid of the software server
     * @return unique identifier of the created process
     */
    @GetMapping(path = "/software-server-capabilities/{guid}")
    public SoftwareServerCapabilityResponse getSoftwareServerCapabilityByGUID(@PathVariable String serverName,
                                                                              @PathVariable String userId,
                                                                              @PathVariable String guid) {
        return restAPI.getSoftwareServerByGUID(serverName, userId, guid);
    }

}
