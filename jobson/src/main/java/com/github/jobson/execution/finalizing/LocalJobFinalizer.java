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
package com.github.jobson.execution.finalizing;

import com.github.jobson.api.persistence.JobDetails;
import com.github.jobson.api.specs.JobSpec;

import java.nio.file.Path;

public final class LocalJobFinalizer {

    public LocalJobFinalizer(JobFinalizerIO jobFinalizerIO) {
        throw new RuntimeException("NYI");
    }

    public boolean finalize(JobDetails jobDetails, JobSpec jobSpec, Path workingDir) {
        // TODO: Check `expectedOutputs` exist
        // TODO: Persist expectedOutput metadata, where appropriate (e.g. via detection)
        // TODO: Set job state
        throw new RuntimeException("NYI");
    }
}
