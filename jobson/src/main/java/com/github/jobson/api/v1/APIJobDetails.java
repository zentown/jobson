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
import com.github.jobson.dao.jobs.JobDetails;
import com.github.jobson.jobs.JobId;
import com.github.jobson.jobs.JobStatus;
import com.github.jobson.jobs.JobTimestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

@ApiModel(description = "Details of a job on the system")
public class APIJobDetails {

    public static APIJobDetails fromJobDetails(
            JobDetails jobDetails,
            Map<String, APIRestLink> restLinks) {

        return new APIJobDetails(
                jobDetails.getId(),
                jobDetails.getName(),
                jobDetails.getOwner(),
                jobDetails.getTimestamps(),
                restLinks);
    }

    @ApiModelProperty(value = "The job's ID")
    @JsonProperty
    private JobId id;

    @ApiModelProperty(value = "A name for the job")
    @JsonProperty
    private String name;

    @ApiModelProperty(value = "The owner of the job.")
    @JsonProperty
    private UserId owner;

    @ApiModelProperty(value = "Timestamps indicating when job status changes occurred")
    @JsonProperty
    private List<JobTimestamp> timestamps;

    @ApiModelProperty(value = "Links to related resources and actions")
    @JsonProperty
    private Map<String, APIRestLink> _links;


    /**
     * @deprecated Used by JSON deserializer
     */
    public APIJobDetails() {}

    public APIJobDetails(
            JobId id,
            String name,
            UserId owner,
            List<JobTimestamp> timestamps,
            Map<String, APIRestLink> _links) {

        this.id = id;
        this.name = name;
        this.owner = owner;
        this.timestamps = timestamps;
        this._links = _links;
    }


    public JobId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserId getOwner() {
        return owner;
    }

    public List<JobTimestamp> getTimestamps() {
        return timestamps;
    }

    @JsonIgnore
    public JobStatus latestStatus() {
        return this.timestamps.get(this.timestamps.size() - 1).getStatus();
    }

    public JobDetails toJobDetails() {
        return new JobDetails(id, name, owner, timestamps);
    }

    @JsonIgnore
    public Map<String, APIRestLink> getLinks() {
        return _links;
    }
}
