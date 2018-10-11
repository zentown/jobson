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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApiModel(description = "Details about an output produced by a job")
public final class APIGetJobOutputResponse {

    @JsonProperty
    @NotNull
    private String id;

    @JsonProperty
    private long sizeInBytes;

    @JsonProperty
    @NotNull
    private String href;

    @JsonProperty
    @NotNull
    private Optional<String> mimeType = Optional.empty();

    @JsonProperty
    private Optional<String> name = Optional.empty();

    @JsonProperty
    private Optional<String> description = Optional.empty();

    @JsonProperty
    private Map<String, String> metadata = new HashMap<>();


    public APIGetJobOutputResponse(
            @JsonProperty("id") String id,
            @JsonProperty("sizeInBytes") long sizeInBytes,
            @JsonProperty("href") String href,
            @JsonProperty("mimeType") Optional<String> mimeType,
            @JsonProperty("name") Optional<String> name,
            @JsonProperty("description") Optional<String> description,
            @JsonProperty("metadata") Map<String, String> metadata) {
        this.id = id;
        this.sizeInBytes = sizeInBytes;
        this.href = href;
        this.mimeType = mimeType;
        this.name = name;
        this.description = description;
        this.metadata = metadata;
    }


    public String getId() {
        return this.id;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    public String getHref() {
        return href;
    }

    public Optional<String> getMimeType() {
        return mimeType;
    }

    public Optional<String> getName() {
        return name;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }
}
