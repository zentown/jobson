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

package com.github.jobson.api.http;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jobson.api.http.v1.APIRestLink;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

@ApiModel(description = "A root response, which describes sub-resources")
public final class APIGetRootResponse {

    @ApiModelProperty(value = "Links to related resources")
    @JsonProperty
    private Map<String, APIRestLink> _links;

    /**
     * @deprecated Used by JSON deserializer
     */
    public APIGetRootResponse() {}

    public APIGetRootResponse(Map<String, APIRestLink> _links) {
        this._links = _links;
    }

    @JsonIgnore
    public Map<String, APIRestLink> getLinks() {
        return _links;
    }
}
