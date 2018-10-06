/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.github.jobson.api.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

@ApiModel(description = "A collection of job spec summaries")
public final class APIGetJobSpecSummariesResponse {

    @ApiModelProperty(value = "The summaries")
    @JsonProperty
    private List<APIJobSpecSummary> entries;

    @ApiModelProperty(value = "Links to related resources and actions")
    @JsonProperty
    private Map<String, APIRestLink> _links;


    public APIGetJobSpecSummariesResponse(
            @JsonProperty("entries") List<APIJobSpecSummary> entries,
            @JsonProperty("_links") Map<String, APIRestLink> _links) {
        this.entries = entries;
        this._links = _links;
    }


    public List<APIJobSpecSummary> getEntries() {
        return entries;
    }

    @JsonIgnore
    public Map<String, APIRestLink> getLinks() {
        return _links;
    }
}
