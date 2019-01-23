/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.adapters.repositoryservices.igc.clientlibrary.model.generated.v11701sp1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.odpi.openmetadata.adapters.repositoryservices.igc.clientlibrary.model.common.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * POJO for the 'logical_data_model' asset type in IGC, displayed as 'Logical Data Model' in the IGC UI.
 * <br><br>
 * (this code has been generated based on out-of-the-box IGC metadata types;
 *  if modifications are needed, eg. to handle custom attributes,
 *  extending from this class in your own custom class is the best approach.)
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class LogicalDataModel extends Reference {

    public static String getIgcTypeId() { return "logical_data_model"; }
    public static String getIgcTypeDisplayName() { return "Logical Data Model"; }

    /**
     * The 'name' property, displayed as 'Name' in the IGC UI.
     */
    protected String name;

    /**
     * The 'short_description' property, displayed as 'Short Description' in the IGC UI.
     */
    protected String short_description;

    /**
     * The 'long_description' property, displayed as 'Long Description' in the IGC UI.
     */
    protected String long_description;

    /**
     * The 'logical_data_model' property, displayed as 'Logical Data Model' in the IGC UI.
     * <br><br>
     * Will be a single {@link Reference} to a {@link LogicalDataModel} object.
     */
    protected Reference logical_data_model;

    /**
     * The 'labels' property, displayed as 'Labels' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link Label} objects.
     */
    protected ReferenceList labels;

    /**
     * The 'stewards' property, displayed as 'Stewards' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link AsclSteward} objects.
     */
    protected ReferenceList stewards;

    /**
     * The 'assigned_to_terms' property, displayed as 'Assigned to Terms' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link Term} objects.
     */
    protected ReferenceList assigned_to_terms;

    /**
     * The 'implements_rules' property, displayed as 'Implements Rules' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link InformationGovernanceRule} objects.
     */
    protected ReferenceList implements_rules;

    /**
     * The 'governed_by_rules' property, displayed as 'Governed by Rules' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link InformationGovernanceRule} objects.
     */
    protected ReferenceList governed_by_rules;

    /**
     * The 'implemented_by_physical_data_models' property, displayed as 'Implemented by Physical Data Models' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link PhysicalDataModel} objects.
     */
    protected ReferenceList implemented_by_physical_data_models;

    /**
     * The 'implemented_by_database_schemas' property, displayed as 'Implemented by Database Schemas' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link DatabaseSchema} objects.
     */
    protected ReferenceList implemented_by_database_schemas;

    /**
     * The 'contains_logical_data_models' property, displayed as 'Contains Logical Data Models' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link LogicalDataModel} objects.
     */
    protected ReferenceList contains_logical_data_models;

    /**
     * The 'subject_areas' property, displayed as 'Subject Areas' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link SubjectArea} objects.
     */
    protected ReferenceList subject_areas;

    /**
     * The 'logical_entities' property, displayed as 'Logical Entities' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link LogicalEntity} objects.
     */
    protected ReferenceList logical_entities;

    /**
     * The 'logical_domains' property, displayed as 'Logical Domains' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link LogicalDomain} objects.
     */
    protected ReferenceList logical_domains;

    /**
     * The 'alias_(business_name)' property, displayed as 'Alias (Business Name)' in the IGC UI.
     */
    @JsonProperty("alias_(business_name)") protected String alias__business_name_;

    /**
     * The 'author' property, displayed as 'Author' in the IGC UI.
     */
    protected String author;

    /**
     * The 'methodology' property, displayed as 'Methodology' in the IGC UI.
     */
    protected String methodology;

    /**
     * The 'version' property, displayed as 'Version' in the IGC UI.
     */
    protected String version;

    /**
     * The 'design_tool' property, displayed as 'Design Tool' in the IGC UI.
     */
    protected String design_tool;

    /**
     * The 'native_id' property, displayed as 'Native ID' in the IGC UI.
     */
    protected String native_id;

    /**
     * The 'package' property, displayed as 'Package' in the IGC UI.
     */
    @JsonProperty("package") protected String __package;

    /**
     * The 'namespace' property, displayed as 'Namespace' in the IGC UI.
     */
    protected String namespace;

    /**
     * The 'imported_from' property, displayed as 'Imported From' in the IGC UI.
     */
    protected String imported_from;

    /**
     * The 'in_collections' property, displayed as 'In Collections' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link Collection} objects.
     */
    protected ReferenceList in_collections;

    /**
     * The 'created_by' property, displayed as 'Created By' in the IGC UI.
     */
    protected String created_by;

    /**
     * The 'created_on' property, displayed as 'Created On' in the IGC UI.
     */
    protected Date created_on;

    /**
     * The 'modified_by' property, displayed as 'Modified By' in the IGC UI.
     */
    protected String modified_by;

    /**
     * The 'modified_on' property, displayed as 'Modified On' in the IGC UI.
     */
    protected Date modified_on;


    /** @see #name */ @JsonProperty("name")  public String getTheName() { return this.name; }
    /** @see #name */ @JsonProperty("name")  public void setTheName(String name) { this.name = name; }

    /** @see #short_description */ @JsonProperty("short_description")  public String getShortDescription() { return this.short_description; }
    /** @see #short_description */ @JsonProperty("short_description")  public void setShortDescription(String short_description) { this.short_description = short_description; }

    /** @see #long_description */ @JsonProperty("long_description")  public String getLongDescription() { return this.long_description; }
    /** @see #long_description */ @JsonProperty("long_description")  public void setLongDescription(String long_description) { this.long_description = long_description; }

    /** @see #logical_data_model */ @JsonProperty("logical_data_model")  public Reference getLogicalDataModel() { return this.logical_data_model; }
    /** @see #logical_data_model */ @JsonProperty("logical_data_model")  public void setLogicalDataModel(Reference logical_data_model) { this.logical_data_model = logical_data_model; }

    /** @see #labels */ @JsonProperty("labels")  public ReferenceList getLabels() { return this.labels; }
    /** @see #labels */ @JsonProperty("labels")  public void setLabels(ReferenceList labels) { this.labels = labels; }

    /** @see #stewards */ @JsonProperty("stewards")  public ReferenceList getStewards() { return this.stewards; }
    /** @see #stewards */ @JsonProperty("stewards")  public void setStewards(ReferenceList stewards) { this.stewards = stewards; }

    /** @see #assigned_to_terms */ @JsonProperty("assigned_to_terms")  public ReferenceList getAssignedToTerms() { return this.assigned_to_terms; }
    /** @see #assigned_to_terms */ @JsonProperty("assigned_to_terms")  public void setAssignedToTerms(ReferenceList assigned_to_terms) { this.assigned_to_terms = assigned_to_terms; }

    /** @see #implements_rules */ @JsonProperty("implements_rules")  public ReferenceList getImplementsRules() { return this.implements_rules; }
    /** @see #implements_rules */ @JsonProperty("implements_rules")  public void setImplementsRules(ReferenceList implements_rules) { this.implements_rules = implements_rules; }

    /** @see #governed_by_rules */ @JsonProperty("governed_by_rules")  public ReferenceList getGovernedByRules() { return this.governed_by_rules; }
    /** @see #governed_by_rules */ @JsonProperty("governed_by_rules")  public void setGovernedByRules(ReferenceList governed_by_rules) { this.governed_by_rules = governed_by_rules; }

    /** @see #implemented_by_physical_data_models */ @JsonProperty("implemented_by_physical_data_models")  public ReferenceList getImplementedByPhysicalDataModels() { return this.implemented_by_physical_data_models; }
    /** @see #implemented_by_physical_data_models */ @JsonProperty("implemented_by_physical_data_models")  public void setImplementedByPhysicalDataModels(ReferenceList implemented_by_physical_data_models) { this.implemented_by_physical_data_models = implemented_by_physical_data_models; }

    /** @see #implemented_by_database_schemas */ @JsonProperty("implemented_by_database_schemas")  public ReferenceList getImplementedByDatabaseSchemas() { return this.implemented_by_database_schemas; }
    /** @see #implemented_by_database_schemas */ @JsonProperty("implemented_by_database_schemas")  public void setImplementedByDatabaseSchemas(ReferenceList implemented_by_database_schemas) { this.implemented_by_database_schemas = implemented_by_database_schemas; }

    /** @see #contains_logical_data_models */ @JsonProperty("contains_logical_data_models")  public ReferenceList getContainsLogicalDataModels() { return this.contains_logical_data_models; }
    /** @see #contains_logical_data_models */ @JsonProperty("contains_logical_data_models")  public void setContainsLogicalDataModels(ReferenceList contains_logical_data_models) { this.contains_logical_data_models = contains_logical_data_models; }

    /** @see #subject_areas */ @JsonProperty("subject_areas")  public ReferenceList getSubjectAreas() { return this.subject_areas; }
    /** @see #subject_areas */ @JsonProperty("subject_areas")  public void setSubjectAreas(ReferenceList subject_areas) { this.subject_areas = subject_areas; }

    /** @see #logical_entities */ @JsonProperty("logical_entities")  public ReferenceList getLogicalEntities() { return this.logical_entities; }
    /** @see #logical_entities */ @JsonProperty("logical_entities")  public void setLogicalEntities(ReferenceList logical_entities) { this.logical_entities = logical_entities; }

    /** @see #logical_domains */ @JsonProperty("logical_domains")  public ReferenceList getLogicalDomains() { return this.logical_domains; }
    /** @see #logical_domains */ @JsonProperty("logical_domains")  public void setLogicalDomains(ReferenceList logical_domains) { this.logical_domains = logical_domains; }

    /** @see #alias__business_name_ */ @JsonProperty("alias_(business_name)")  public String getAliasBusinessName() { return this.alias__business_name_; }
    /** @see #alias__business_name_ */ @JsonProperty("alias_(business_name)")  public void setAliasBusinessName(String alias__business_name_) { this.alias__business_name_ = alias__business_name_; }

    /** @see #author */ @JsonProperty("author")  public String getAuthor() { return this.author; }
    /** @see #author */ @JsonProperty("author")  public void setAuthor(String author) { this.author = author; }

    /** @see #methodology */ @JsonProperty("methodology")  public String getMethodology() { return this.methodology; }
    /** @see #methodology */ @JsonProperty("methodology")  public void setMethodology(String methodology) { this.methodology = methodology; }

    /** @see #version */ @JsonProperty("version")  public String getVersion() { return this.version; }
    /** @see #version */ @JsonProperty("version")  public void setVersion(String version) { this.version = version; }

    /** @see #design_tool */ @JsonProperty("design_tool")  public String getDesignTool() { return this.design_tool; }
    /** @see #design_tool */ @JsonProperty("design_tool")  public void setDesignTool(String design_tool) { this.design_tool = design_tool; }

    /** @see #native_id */ @JsonProperty("native_id")  public String getNativeId() { return this.native_id; }
    /** @see #native_id */ @JsonProperty("native_id")  public void setNativeId(String native_id) { this.native_id = native_id; }

    /** @see #__package */ @JsonProperty("package")  public String getPackage() { return this.__package; }
    /** @see #__package */ @JsonProperty("package")  public void setPackage(String __package) { this.__package = __package; }

    /** @see #namespace */ @JsonProperty("namespace")  public String getNamespace() { return this.namespace; }
    /** @see #namespace */ @JsonProperty("namespace")  public void setNamespace(String namespace) { this.namespace = namespace; }

    /** @see #imported_from */ @JsonProperty("imported_from")  public String getImportedFrom() { return this.imported_from; }
    /** @see #imported_from */ @JsonProperty("imported_from")  public void setImportedFrom(String imported_from) { this.imported_from = imported_from; }

    /** @see #in_collections */ @JsonProperty("in_collections")  public ReferenceList getInCollections() { return this.in_collections; }
    /** @see #in_collections */ @JsonProperty("in_collections")  public void setInCollections(ReferenceList in_collections) { this.in_collections = in_collections; }

    /** @see #created_by */ @JsonProperty("created_by")  public String getCreatedBy() { return this.created_by; }
    /** @see #created_by */ @JsonProperty("created_by")  public void setCreatedBy(String created_by) { this.created_by = created_by; }

    /** @see #created_on */ @JsonProperty("created_on")  public Date getCreatedOn() { return this.created_on; }
    /** @see #created_on */ @JsonProperty("created_on")  public void setCreatedOn(Date created_on) { this.created_on = created_on; }

    /** @see #modified_by */ @JsonProperty("modified_by")  public String getModifiedBy() { return this.modified_by; }
    /** @see #modified_by */ @JsonProperty("modified_by")  public void setModifiedBy(String modified_by) { this.modified_by = modified_by; }

    /** @see #modified_on */ @JsonProperty("modified_on")  public Date getModifiedOn() { return this.modified_on; }
    /** @see #modified_on */ @JsonProperty("modified_on")  public void setModifiedOn(Date modified_on) { this.modified_on = modified_on; }

    public static Boolean canBeCreated() { return false; }
    public static Boolean includesModificationDetails() { return true; }
    private static final List<String> NON_RELATIONAL_PROPERTIES = Arrays.asList(
        "name",
        "short_description",
        "long_description",
        "alias_(business_name)",
        "author",
        "methodology",
        "version",
        "design_tool",
        "native_id",
        "package",
        "namespace",
        "imported_from",
        "created_by",
        "created_on",
        "modified_by",
        "modified_on"
    );
    private static final List<String> STRING_PROPERTIES = Arrays.asList(
        "name",
        "short_description",
        "long_description",
        "alias_(business_name)",
        "author",
        "methodology",
        "version",
        "design_tool",
        "native_id",
        "package",
        "namespace",
        "imported_from",
        "created_by",
        "modified_by"
    );
    private static final List<String> PAGED_RELATIONAL_PROPERTIES = Arrays.asList(
        "labels",
        "stewards",
        "assigned_to_terms",
        "implements_rules",
        "governed_by_rules",
        "implemented_by_physical_data_models",
        "implemented_by_database_schemas",
        "contains_logical_data_models",
        "subject_areas",
        "logical_entities",
        "logical_domains",
        "in_collections"
    );
    private static final List<String> ALL_PROPERTIES = Arrays.asList(
        "name",
        "short_description",
        "long_description",
        "logical_data_model",
        "labels",
        "stewards",
        "assigned_to_terms",
        "implements_rules",
        "governed_by_rules",
        "implemented_by_physical_data_models",
        "implemented_by_database_schemas",
        "contains_logical_data_models",
        "subject_areas",
        "logical_entities",
        "logical_domains",
        "alias_(business_name)",
        "author",
        "methodology",
        "version",
        "design_tool",
        "native_id",
        "package",
        "namespace",
        "imported_from",
        "in_collections",
        "created_by",
        "created_on",
        "modified_by",
        "modified_on"
    );
    public static List<String> getNonRelationshipProperties() { return NON_RELATIONAL_PROPERTIES; }
    public static List<String> getStringProperties() { return STRING_PROPERTIES; }
    public static List<String> getPagedRelationshipProperties() { return PAGED_RELATIONAL_PROPERTIES; }
    public static List<String> getAllProperties() { return ALL_PROPERTIES; }
    public static Boolean isLogicalDataModel(Object obj) { return (obj.getClass() == LogicalDataModel.class); }

}