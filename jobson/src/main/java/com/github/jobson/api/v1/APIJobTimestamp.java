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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jobson.Constants;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public final class APIJobTimestamp {

    @ApiModelProperty(value = "The new status of the job")
    @JsonProperty
    private APIJobStatus status;

    @ApiModelProperty(value = "When the status change occurred")
    @JsonProperty
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = Constants.API_VISIBLE_TIMESTAMPS_FORMAT,
            timezone = Constants.API_VISIBLE_TIMESTAMPS_TIMEZONE)
    private Date time;

    @ApiModelProperty(value = "(optional) A message associated with the status change")
    @JsonProperty
    private String message = null;


    public APIJobTimestamp(
            @JsonProperty("status") APIJobStatus status,
            @JsonProperty("time") Date time,
            @JsonProperty("message") String message) {
        this.status = status;
        this.time = time;
        this.message = message;
    }


    public APIJobStatus getStatus() {
        return status;
    }

    public Date getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }
}
