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

package com.github.jobson.execution.old;

import com.github.jobson.api.http.v1.APIUserId;
import com.github.jobson.api.specs.inputs.JobExpectedInputId;
import com.github.jobson.api.specs.inputs.JobInput;
import com.github.jobson.persistence.jobs.JobId;
import com.github.jobson.persistence.jobs.JobStatus;
import com.github.jobson.persistence.jobs.JobTimestamp;
import com.github.jobson.api.specs.JobSpec;
import com.github.jobson.persistence.jobs.PersistedJob;

import java.util.List;
import java.util.Map;

public final class FinalizedJob extends PersistedJob {

    public static FinalizedJob fromExecutingJob(ExecutingJob executingJob, JobStatus finalStatus, String finalMessage) {
        return new FinalizedJob(
                executingJob.getId(),
                executingJob.getOwner(),
                executingJob.getName(),
                executingJob.getInputs(),
                executingJob.getTimestamps(),
                executingJob.getSpec(),
                finalStatus,
                finalMessage);
    }


    private final JobStatus finalStatus;

    private final String finalMessage;

    public FinalizedJob(
            JobId id,
            APIUserId owner,
            String name,
            Map<JobExpectedInputId, JobInput> inputs,
            List<JobTimestamp> timestamps,
            JobSpec spec,
            JobStatus finalStatus,
            String finalMessage) {

        super(id, owner, name, inputs, timestamps, spec);
        this.finalStatus = finalStatus;
        this.finalMessage = finalMessage;
    }

    public JobStatus getFinalStatus() {
        return finalStatus;
    }

    public String getFinalMessage() {
        return finalMessage;
    }
}
