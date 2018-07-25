/**
 * Copyright (C) 2012 White Source Ltd.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.whitesource.agent.api.dispatch;

import org.whitesource.agent.api.model.AgentProjectInfo;

import java.util.Collection;

/**
 * Factory for constructing {@link ServiceRequest}.
 *
 * @author Edo.Shor
 */
public class RequestFactory {

    /* --- Members --- */

    private String agent;

    private String agentVersion;

    private String pluginVersion;

    /* --- Constructors --- */

    /**
     * Constructor
     *
     * @param agent         Agent type identifier.
     * @param agentVersion  Agent API version.
     * @param pluginVersion Plugin version.
     */
    public RequestFactory(String agent, String agentVersion, String pluginVersion) {
        this.agent = agent;
        this.agentVersion = agentVersion;
        this.pluginVersion = pluginVersion;
    }

    /* --- Public methods --- */

    /**
     * Create new Inventory Update request.
     *
     * @param orgToken WhiteSource organization token.
     * @param projects Projects status statement to update.
     * @param userKey  user key uniquely identifying the account at white source.
     * @return Newly created request to update organization inventory.
     */
    public UpdateInventoryRequest newUpdateInventoryRequest(String orgToken, Collection<AgentProjectInfo> projects, String userKey) {
        return newUpdateInventoryRequest(orgToken, null, null, null, projects, userKey);
    }

    /**
     * Create new Inventory Update request.
     *
     * @param orgToken       WhiteSource organization token.
     * @param projects       Projects status statement to update.
     * @param product        Name or WhiteSource service token of the product to update.
     * @param productVersion Version of the product to update.
     * @param userKey        user key uniquely identifying the account at white source.
     * @return Newly created request to update organization inventory.
     */
    public UpdateInventoryRequest newUpdateInventoryRequest(String orgToken,
                                                            String product,
                                                            String productVersion,
                                                            Collection<AgentProjectInfo> projects,
                                                            String userKey) {
        return newUpdateInventoryRequest(orgToken, null, product, productVersion, projects, userKey);
    }

    /**
     * Create new Inventory Update request.
     *
     * @param orgToken       WhiteSource organization token.
     * @param updateType     Request UpdateType
     * @param requesterEmail Email of the WhiteSource user that requests to update WhiteSource.
     * @param product        Name or WhiteSource service token of the product to update.
     * @param productVersion Version of the product to update.
     * @param projects       Projects status statement to update.
     * @param userKey        user key uniquely identifying the account at white source.
     * @param logData
     * @return Newly created request to update organization inventory.
     */
    public UpdateInventoryRequest newUpdateInventoryRequest(String orgToken,
                                                            UpdateType updateType,
                                                            String requesterEmail,
                                                            String product,
                                                            String productVersion,
                                                            Collection<AgentProjectInfo> projects,
                                                            String userKey,
                                                            String logData) {
        UpdateInventoryRequest updateInventoryRequest = new UpdateInventoryRequest(projects, updateType);
        return (UpdateInventoryRequest) prepareRequest(updateInventoryRequest, orgToken, requesterEmail, product, productVersion, userKey,
                false, false, null, null, logData);
    }

    /**
     * Create new Inventory Update request.
     *
     * @param orgToken       WhiteSource organization token.
     * @param requesterEmail Email of the WhiteSource user that requests to update WhiteSource.
     * @param projects       Projects status statement to update.
     * @param product        Name or WhiteSource service token of the product to update.
     * @param productVersion Version of the product to update.
     * @param userKey        user key uniquely identifying the account at white source.
     * @return Newly created request to update organization inventory.
     */
    public UpdateInventoryRequest newUpdateInventoryRequest(String orgToken,
                                                            String requesterEmail,
                                                            String product,
                                                            String productVersion,
                                                            Collection<AgentProjectInfo> projects,
                                                            String userKey) {
        return (UpdateInventoryRequest) prepareRequest(new UpdateInventoryRequest(projects), orgToken, requesterEmail, product, productVersion, userKey,
                false, false, null, null, null);
    }

    /**
     * Create new Inventory Update request.
     *
     * @param orgToken                WhiteSource organization token.
     * @param requesterEmail          Email of the WhiteSource user that requests to update WhiteSource.
     * @param product                 Name or WhiteSource service token of the product to update.
     * @param productVersion          Version of the product to update.
     * @param projects                Projects status statement to update.
     * @param userKey                 user key uniquely identifying the account at white source.
     * @param aggregateModules        to combine all pom modules into a single WhiteSource project with an aggregated dependency flat list (no hierarchy).
     * @param preserveModuleStructure combine all pom modules to be dependencies of single project, each module will be represented as a parent of its dependencies.
     * @param aggregateProjectName    aggregate project name identifier.
     * @param aggregateProjectToken   aggregate project token identifier.
     * @return Newly created request to update organization inventory.
     */
    public UpdateInventoryRequest newUpdateInventoryRequest(String orgToken,
                                                            String requesterEmail,
                                                            String product,
                                                            String productVersion,
                                                            Collection<AgentProjectInfo> projects,
                                                            String userKey,
                                                            Boolean aggregateModules,
                                                            Boolean preserveModuleStructure,
                                                            String aggregateProjectName,
                                                            String aggregateProjectToken) {
        return (UpdateInventoryRequest) prepareRequest(new UpdateInventoryRequest(projects), orgToken, requesterEmail, product, productVersion, userKey,
                aggregateModules, preserveModuleStructure, aggregateProjectName, aggregateProjectToken, null);
    }

    /**
     * Create new Check policies request.
     *
     * @param orgToken WhiteSource organization token.
     * @param projects Projects status statement to check.
     * @param userKey  user key uniquely identifying the account at white source.
     * @return Newly created request to check policies application.
     */

    public CheckPoliciesRequest newCheckPoliciesRequest(String orgToken, Collection<AgentProjectInfo> projects, String userKey) {
        return newCheckPoliciesRequest(orgToken, null, null, projects, userKey, null);
    }

    /**
     * Create new Check policies request.
     *
     * @param orgToken       WhiteSource organization token.
     * @param userKey        user key uniquely identifying the account at white source.
     * @param projects       Projects status statement to check.
     * @param product        Name or WhiteSource service token of the product whose policies to check.
     * @param productVersion Version of the product whose policies to check.
     * @param requesterEmail Email of the WhiteSource user that requests to update WhiteSource.
     * @return Newly created request to check policies application.
     * @deprecated Use {@link RequestFactory#newCheckPolicyComplianceRequest(String, String, String, Collection, boolean, String)}
     */

    public CheckPoliciesRequest newCheckPoliciesRequest(String orgToken,
                                                        String product,
                                                        String productVersion,
                                                        Collection<AgentProjectInfo> projects,
                                                        String userKey,
                                                        String requesterEmail) {
        return (CheckPoliciesRequest) prepareRequest(new CheckPoliciesRequest(projects), orgToken, requesterEmail, product, productVersion, userKey,
                false, false, null, null, null);
    }

    public CheckPoliciesRequest newCheckPoliciesRequest(String orgToken,
                                                        String product,
                                                        String productVersion,
                                                        Collection<AgentProjectInfo> projects,
                                                        String userKey) {
        return newCheckPoliciesRequest(orgToken, product, productVersion, projects, userKey, null);
    }

    /**
     * Create new Check policies request.
     *
     * @param orgToken                  WhiteSource organization token.
     * @param projects                  Projects status statement to check.
     * @param forceCheckAllDependencies boolean check that all/added dependencies sent to WhiteSource
     * @param userKey                   user key uniquely identifying the account at white source.
     * @return Newly created request to check policies application.
     */

    public CheckPolicyComplianceRequest newCheckPolicyComplianceRequest(String orgToken, Collection<AgentProjectInfo> projects, boolean forceCheckAllDependencies, String userKey) {
        return newCheckPolicyComplianceRequest(orgToken, null, null, projects, forceCheckAllDependencies, userKey);
    }

    /**
     * Create new Check policies request.
     *
     * @param orgToken                  WhiteSource organization token.
     * @param projects                  Projects status statement to check.
     * @param product                   Name or WhiteSource service token of the product whose policies to check.
     * @param productVersion            Version of the product whose policies to check.
     * @param forceCheckAllDependencies boolean check that all/added dependencies sent to WhiteSource
     * @param userKey                   user key uniquely identifying the account at white source.
     * @param requesterEmail            Email of the WhiteSource user that requests to update WhiteSource.
     * @return Newly created request to check policies application.
     */

    public CheckPolicyComplianceRequest newCheckPolicyComplianceRequest(String orgToken,
                                                                        String product,
                                                                        String productVersion,
                                                                        Collection<AgentProjectInfo> projects,
                                                                        boolean forceCheckAllDependencies,
                                                                        String userKey,
                                                                        String requesterEmail) {
        return (CheckPolicyComplianceRequest) prepareRequest(new CheckPolicyComplianceRequest(projects, forceCheckAllDependencies), orgToken, requesterEmail, product, productVersion, userKey,
                false, false, null, null, null);
    }

    /**
     * Create new Check policies request.
     *
     * @param orgToken                  WhiteSource organization token.
     * @param projects                  Projects status statement to check.
     * @param product                   Name or WhiteSource service token of the product whose policies to check.
     * @param productVersion            Version of the product whose policies to check.
     * @param forceCheckAllDependencies boolean check that all/added dependencies sent to WhiteSource
     * @param userKey                   user key uniquely identifying the account at white source.
     * @param requesterEmail            Email of the WhiteSource user that requests to update WhiteSource.
     * @param logData                   list of FSA's log data events
     * @return Newly created request to check policies application.
     */

    public CheckPolicyComplianceRequest newCheckPolicyComplianceRequest(String orgToken,
                                                                        String product,
                                                                        String productVersion,
                                                                        Collection<AgentProjectInfo> projects,
                                                                        boolean forceCheckAllDependencies,
                                                                        String userKey,
                                                                        String requesterEmail,
                                                                        String logData) {
        return (CheckPolicyComplianceRequest) prepareRequest(new CheckPolicyComplianceRequest(projects, forceCheckAllDependencies), orgToken, requesterEmail, product, productVersion, userKey,
                false, false, null, null, logData);
    }

    public CheckPolicyComplianceRequest newCheckPolicyComplianceRequest(String orgToken,
                                                                        String product,
                                                                        String productVersion,
                                                                        Collection<AgentProjectInfo> projects,
                                                                        boolean forceCheckAllDependencies,
                                                                        String userKey) {
        return newCheckPolicyComplianceRequest(orgToken, product, productVersion, projects, forceCheckAllDependencies, userKey, null);
    }

    /**
     * Create new Check policies request.
     *
     * @param orgToken                  WhiteSource organization token.
     * @param projects                  Projects status statement to check.
     * @param product                   Name or WhiteSource service token of the product whose policies to check.
     * @param productVersion            Version of the product whose policies to check.
     * @param forceCheckAllDependencies boolean check that all/added dependencies sent to WhiteSource
     * @param userKey                   user key uniquely identifying the account at white source.
     * @param requesterEmail            Email of the WhiteSource user that requests to update WhiteSource.
     * @param aggregateModules          to combine all pom modules into a single WhiteSource project with an aggregated dependency flat list (no hierarchy).
     * @param preserveModuleStructure   combine all pom modules to be dependencies of single project, each module will be represented as a parent of its dependencies.
     * @param aggregateProjectName      aggregate project name identifier.
     * @param aggregateProjectToken     aggregate project token identifier.
     * @return Newly created request to check policies application.
     */

    public CheckPolicyComplianceRequest newCheckPolicyComplianceRequest(String orgToken,
                                                                        String product,
                                                                        String productVersion,
                                                                        Collection<AgentProjectInfo> projects,
                                                                        boolean forceCheckAllDependencies,
                                                                        String userKey,
                                                                        String requesterEmail,
                                                                        boolean aggregateModules,
                                                                        boolean preserveModuleStructure,
                                                                        String aggregateProjectName,
                                                                        String aggregateProjectToken) {
        return (CheckPolicyComplianceRequest) prepareRequest(new CheckPolicyComplianceRequest(projects, forceCheckAllDependencies), orgToken, requesterEmail, product, productVersion, userKey,
                aggregateModules, preserveModuleStructure, aggregateProjectName, aggregateProjectToken, null);
    }

    /**
     * Create new Dependency Data request.
     *
     * @param orgToken WhiteSource organization token.
     * @param projects Projects status statement to check.
     * @param userKey  user key uniquely identifying the account at white source.
     * @return Newly created request to get Dependency Additional Data (Licenses, Description, homepageUrl and Vulnerabilities).
     */

    public GetDependencyDataRequest newDependencyDataRequest(String orgToken, Collection<AgentProjectInfo> projects, String userKey) {
        return newDependencyDataRequest(orgToken, null, null, projects, userKey);
    }

    /**
     * Create new Dependency Data request.
     *
     * @param orgToken       WhiteSource organization token.
     * @param projects       Projects status statement to check.
     * @param product        Name or WhiteSource service token of the product whose policies to check.
     * @param productVersion Version of the product whose policies to check.
     * @param userKey        user key uniquely identifying the account at white source.
     * @param requesterEmail Email of the WhiteSource user that requests to update WhiteSource.
     * @return Newly created request to get Dependency Additional Data (Licenses, Description, homepageUrl and Vulnerabilities).
     */

    public GetDependencyDataRequest newDependencyDataRequest(String orgToken,
                                                             String product,
                                                             String productVersion,
                                                             Collection<AgentProjectInfo> projects,
                                                             String userKey,
                                                             String requesterEmail) {
        return (GetDependencyDataRequest) prepareRequest(new GetDependencyDataRequest(projects), orgToken, requesterEmail, product, productVersion, userKey,
                false, false, null, null, null);
    }

    public GetDependencyDataRequest newDependencyDataRequest(String orgToken,
                                                             String product,
                                                             String productVersion,
                                                             Collection<AgentProjectInfo> projects,
                                                             String userKey) {
        return newDependencyDataRequest(orgToken, product, productVersion, projects, userKey, null);
    }

    /**
     * Create new Inventory Update request.
     *
     * @param orgToken       WhiteSource organization token.
     * @param projects       Projects status statement to update.
     * @param product        Name or WhiteSource service token of the product to update.
     * @param productVersion Version of the product to update.
     * @param userKey        user key uniquely identifying the account at white source.
     * @param requesterEmail Email of the WhiteSource user that requests to update WhiteSource.
     * @return Newly created request to update organization inventory.
     */

    public SummaryScanRequest newSummaryScanRequest(String orgToken,
                                                    String product,
                                                    String productVersion,
                                                    Collection<AgentProjectInfo> projects,
                                                    String userKey,
                                                    String requesterEmail) {
        return (SummaryScanRequest) prepareRequest(new SummaryScanRequest(projects), orgToken, requesterEmail, product, productVersion, userKey,
                false, false, null, null, null);
    }

    public SummaryScanRequest newSummaryScanRequest(String orgToken,
                                                    String product,
                                                    String productVersion,
                                                    Collection<AgentProjectInfo> projects,
                                                    String userKey) {
        return newSummaryScanRequest(orgToken, product, productVersion, projects, userKey, null);
    }

    /**
     * Create new Inventory Update request.
     *
     * @param orgToken       WhiteSource organization token.
     * @param projects       Projects status statement to update.
     * @param product        Name or WhiteSource service token of the product to update.
     * @param productVersion Version of the product to update.
     * @param userKey        user key uniquely identifying the account at white source.
     * @param requesterEmail Email of the WhiteSource user that requests to update WhiteSource.
     * @return Newly created request to update organization inventory.
     */

    public CheckVulnerabilitiesRequest newCheckVulnerabilitiesRequest(String orgToken,
                                                                      String product,
                                                                      String productVersion,
                                                                      Collection<AgentProjectInfo> projects,
                                                                      String userKey,
                                                                      String requesterEmail) {
        return (CheckVulnerabilitiesRequest) prepareRequest(new CheckVulnerabilitiesRequest(projects), orgToken, requesterEmail, product, productVersion, userKey,
                false, false, null, null, null);
    }

    public CheckVulnerabilitiesRequest newCheckVulnerabilitiesRequest(String orgToken,
                                                                      String product,
                                                                      String productVersion,
                                                                      Collection<AgentProjectInfo> projects,
                                                                      String userKey) {
        return newCheckVulnerabilitiesRequest(orgToken, product, productVersion, projects, userKey, null);
    }

    /**
     * Create new Inventory Update request.
     *
     * @param orgToken       WhiteSource organization token.
     * @param product        Name or WhiteSource service token of the product to update.
     * @param productVersion Version of the product to update.
     * @param userKey        user key uniquely identifying the account at white source.
     * @param requesterEmail Email of the WhiteSource user that requests to update WhiteSource.
     * @return request to get organization plugin configuration.
     */

    public ConfigurationRequest newConfigurationRequest(String orgToken,
                                                        String product,
                                                        String productVersion,
                                                        String userKey,
                                                        String requesterEmail) {
        return (ConfigurationRequest) prepareRequest(new ConfigurationRequest(), orgToken, requesterEmail, product, productVersion, userKey,
                false, false, null, null, null);
    }

    public ConfigurationRequest newConfigurationRequest(String orgToken,
                                                        String product,
                                                        String productVersion,
                                                        String userKey) {
        return newConfigurationRequest(orgToken, product, productVersion, userKey, null);
    }

    /* --- Protected methods --- */

    protected <R> BaseRequest<R> prepareRequest(BaseRequest<R> request, String orgToken, String requesterEmail, String product, String productVersion, String userKey,
                                                boolean aggregateModules, boolean preserveModuleStructure, String aggregateProjectName,
                                                String aggregateProjectToken, String logData) {
        request.setAgent(agent);
        request.setAgentVersion(agentVersion);
        request.setPluginVersion(pluginVersion);
        request.setOrgToken(orgToken);
        request.setProduct(product);
        request.setProductVersion(productVersion);
        request.setRequesterEmail(requesterEmail);
        request.setUserKey(userKey);
        request.setAggregateModules(aggregateModules);
        request.setPreserveModuleStructure(preserveModuleStructure);
        request.setAggregateProjectName(aggregateProjectName);
        request.setAggregateProjectToken(aggregateProjectToken);
        request.setLogData(logData);
        return request;
    }

}
