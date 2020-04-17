/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.commonservices.ocf.metadatamanagement.converters;

import org.odpi.openmetadata.commonservices.ocf.metadatamanagement.mappers.AssetMapper;
import org.odpi.openmetadata.commonservices.ocf.metadatamanagement.mappers.ReferenceableMapper;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.Asset;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.Meaning;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.OwnerType;
import org.odpi.openmetadata.metadatasecurity.properties.AssetAuditHeader;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.*;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryHelper;

import java.util.List;
import java.util.Map;

/**
 * AssetConverter transfers the relevant properties from an Open Metadata Repository Services (OMRS)
 * EntityDetail object into a Asset bean.
 */
public class AssetConverter extends ReferenceableConverter
{
    /**
     * Constructor captures the initial content with connectionToAssetRelationship
     *
     * @param assetEntity properties to convert
     * @param connectionToAssetRelationship properties to convert
     * @param repositoryHelper helper object to parse entity/relationship objects
     * @param serviceName name of this component
     */
    public AssetConverter(EntityDetail         assetEntity,
                          Relationship         connectionToAssetRelationship,
                          OMRSRepositoryHelper repositoryHelper,
                          String               serviceName)
    {
        this(assetEntity, connectionToAssetRelationship,null, repositoryHelper, serviceName);
    }
    public AssetConverter(EntityDetail         assetEntity,
                          Relationship         connectionToAssetRelationship,
                          List<Meaning>        meanings,
                          OMRSRepositoryHelper repositoryHelper,
                          String               serviceName)
    {
        super(assetEntity, connectionToAssetRelationship, meanings, repositoryHelper, serviceName);
    }


    /**
     * This method is overridable by the subclasses.
     *
     * @return empty bean
     */
    protected Asset  getNewBean()
    {
       return new Asset();
    }


    /**
     * Request the bean is extracted from the repository objects
     *
     * @return output bean
     */
    public Asset getAssetBean()
    {
        Asset  bean = null;

        if (entity != null)
        {
            bean = getNewBean();

            updateBean(bean);
        }

        return bean;
    }


    /**
     * Return the new audit header for the asset.  These are values stored in the repository and
     * made available to open metadata security connectors and asset owners.
     *
     * @return audit header
     */
    public AssetAuditHeader getAssetAuditHeader()
    {
        if (entity != null)
        {
            AssetAuditHeader auditHeader = new AssetAuditHeader();

            auditHeader.setCreatedBy(entity.getCreatedBy());
            auditHeader.setCreateTime(entity.getCreateTime());
            auditHeader.setMaintainedBy(entity.getMaintainedBy());
            auditHeader.setUpdatedBy(entity.getUpdatedBy());
            auditHeader.setUpdateTime(entity.getUpdateTime());
            auditHeader.setVersion(entity.getVersion());

            return auditHeader;
        }

        return null;
    }


    /**
     * Request the bean is extracted from the repository objects
     *
     * @param bean  output bean
     */
    protected void updateBean(Asset bean)
    {
        final String  methodName = "getBean";

        if (entity != null)
        {
            super.updateBean(bean);

            /*
             * The properties are removed from the instance properties and stowed in the bean.
             * Any remaining properties are stored in extendedProperties.
             */
            InstanceProperties instanceProperties = entity.getProperties();

            if (instanceProperties != null)
            {
                bean.setQualifiedName(repositoryHelper.removeStringProperty(serviceName,
                                                                            ReferenceableMapper.QUALIFIED_NAME_PROPERTY_NAME,
                                                                            instanceProperties,
                                                                            methodName));
                bean.setDisplayName(repositoryHelper.removeStringProperty(serviceName,
                                                                          AssetMapper.DISPLAY_NAME_PROPERTY_NAME,
                                                                          instanceProperties,
                                                                          methodName));
                bean.setDescription(repositoryHelper.removeStringProperty(serviceName,
                                                                          AssetMapper.DESCRIPTION_PROPERTY_NAME,
                                                                          instanceProperties,
                                                                          methodName));
                /* Note this value should be in the classification */
                bean.setOwner(repositoryHelper.removeStringProperty(serviceName,
                                                                    AssetMapper.OWNER_PROPERTY_NAME,
                                                                    instanceProperties,
                                                                    methodName));
                /* Note this value should be in the classification */
                bean.setOwnerType(this.removeOwnerTypeFromProperties(instanceProperties));
                /* Note this value should be in the classification */
                bean.setZoneMembership(repositoryHelper.removeStringArrayProperty(serviceName,
                                                                                  AssetMapper.ZONE_MEMBERSHIP_PROPERTY_NAME,
                                                                                  instanceProperties,
                                                                                  methodName));
                bean.setLatestChange(repositoryHelper.removeStringProperty(serviceName,
                                                                           AssetMapper.LATEST_CHANGE_PROPERTY_NAME,
                                                                           instanceProperties,
                                                                           methodName));
                bean.setAdditionalProperties(repositoryHelper.removeStringMapFromProperty(serviceName,
                                                                                          ReferenceableMapper.ADDITIONAL_PROPERTIES_PROPERTY_NAME,
                                                                                          instanceProperties,
                                                                                          methodName));
                bean.setExtendedProperties(repositoryHelper.getInstancePropertiesAsMap(instanceProperties));
            }

            if (relationship != null)
            {
                instanceProperties = relationship.getProperties();

                if (instanceProperties != null)
                {
                    bean.setShortDescription(repositoryHelper.getStringProperty(serviceName,
                                                                                AssetMapper.SHORT_DESCRIPTION_PROPERTY_NAME,
                                                                                instanceProperties,
                                                                                methodName));
                }
            }

            /*
             * The values in the classifications override the values in the main properties of the Asset's entity.
             * Having these properties in the main entity is deprecated.
             */
            instanceProperties = super.getClassificationProperties(AssetMapper.ASSET_ZONES_CLASSIFICATION_NAME);

            if (instanceProperties != null)
            {
                bean.setZoneMembership(repositoryHelper.getStringArrayProperty(serviceName,
                                                                               AssetMapper.ZONE_MEMBERSHIP_PROPERTY_NAME,
                                                                               instanceProperties,
                                                                               methodName));
            }

            instanceProperties = super.getClassificationProperties(AssetMapper.ASSET_OWNERSHIP_CLASSIFICATION_NAME);

            if (instanceProperties != null)
            {
                bean.setOwner(repositoryHelper.getStringProperty(serviceName,
                                                                 AssetMapper.OWNER_PROPERTY_NAME,
                                                                 instanceProperties,
                                                                 methodName));
                bean.setOwnerType(this.getOwnerTypeFromProperties(instanceProperties));
            }
        }
    }


    /**
     * Retrieve the ContactMethodType enum property from the instance properties of an entity
     *
     * @param properties  entity properties
     * @return ContactMethodType  enum value
     */
    private OwnerType getOwnerTypeFromProperties(InstanceProperties   properties)
    {
        OwnerType ownerType = OwnerType.OTHER;

        if (properties != null)
        {
            Map<String, InstancePropertyValue> instancePropertiesMap = properties.getInstanceProperties();

            if (instancePropertiesMap != null)
            {
                InstancePropertyValue instancePropertyValue = instancePropertiesMap.get(AssetMapper.OWNER_TYPE_PROPERTY_NAME);

                if (instancePropertyValue instanceof EnumPropertyValue)
                {
                    EnumPropertyValue enumPropertyValue = (EnumPropertyValue) instancePropertyValue;

                    switch (enumPropertyValue.getOrdinal())
                    {
                        case 0:
                            ownerType = OwnerType.USER_ID;
                            break;

                        case 1:
                            ownerType = OwnerType.PROFILE_ID;
                            break;

                        case 99:
                            ownerType = OwnerType.OTHER;
                            break;
                    }
                }
            }
        }

        return ownerType;
    }


    /**
     * Retrieve the ContactMethodType enum property from the instance properties of an entity
     *
     * @param properties  entity properties
     * @return ContactMethodType  enum value
     */
    private OwnerType removeOwnerTypeFromProperties(InstanceProperties   properties)
    {
        OwnerType ownerType = this.getOwnerTypeFromProperties(properties);

        if (properties != null)
        {
            Map<String, InstancePropertyValue> instancePropertiesMap = properties.getInstanceProperties();

            if (instancePropertiesMap != null)
            {
                instancePropertiesMap.remove(AssetMapper.OWNER_TYPE_PROPERTY_NAME);
            }

            properties.setInstanceProperties(instancePropertiesMap);
        }

        return ownerType;
    }
}
