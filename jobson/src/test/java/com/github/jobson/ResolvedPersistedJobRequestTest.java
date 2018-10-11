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

package com.github.jobson;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.jobson.api.http.APICreateJobRequest;
import com.github.jobson.api.http.APIUserId;
import com.github.jobson.api.specs.inputs.JobExpectedInput;
import com.github.jobson.api.specs.inputs.JobExpectedInputId;
import com.github.jobson.api.specs.inputs.JobInput;
import com.github.jobson.api.specs.inputs.select.SelectInput;
import com.github.jobson.api.specs.inputs.stringarray.StringArrayExpectedInput;
import com.github.jobson.internal.ValidJobRequest;
import com.github.jobson.api.specs.ExecutionConfiguration;
import com.github.jobson.api.specs.JobSpec;
import com.github.jobson.api.specs.JobSpecId;
import com.github.jobson.other.TestHelpers;
import com.github.jobson.util.Either;
import com.github.jobson.util.Helpers;
import com.github.jobson.util.ValidationError;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;

public final class ResolvedPersistedJobRequestTest {

    private static void testSpecReqPair(
            String specJSONPath,
            String reqJSONPath,
            Consumer<ValidJobRequest> req,
            Consumer<List<ValidationError>> validationErrors) {
        final JobSpec validJobSchema = TestHelpers.readJSONFixture(specJSONPath, JobSpec.class);
        final APIUserId APIUserId = TestHelpers.generateUserId();
        final APICreateJobRequest jobRequestWithInvalidSpecifiedOption =
                TestHelpers.readJSONFixture(reqJSONPath, APICreateJobRequest.class);

        final Either<ValidJobRequest, List<ValidationError>> ret =
                ValidJobRequest.tryCreate(validJobSchema, APIUserId, jobRequestWithInvalidSpecifiedOption);


        ret.handleBoth(req, validationErrors);
    }

    @Test
    public void testValidateDoesNotProduceValidationErrorsIfJobRequestIsValidAgainstSchema() {
        testSpecReqPair(
                "fixtures/specs/1_valid-job-schema.json",
                "fixtures/specs/1_valid-job-request-against-schema.json",
                req -> {},
                validationError -> fail("Has validation errors (should have none)"));
    }

    @Test
    public void testValidateProducesAValidationErrorMessageWhenAnInputIdDoesntHaveACorrespondingInputSchema() {
        testSpecReqPair(
                "fixtures/specs/3_valid-job-schema.json",
                "fixtures/specs/3_request-with-non-existent-input.json",
                req -> fail("Invalid request generated no validation errors"),
                validationErrors -> assertThat(validationErrors.size()).isGreaterThan(0));
    }

    @Test
    public void testValidateProducesAValidationErrorMessageWhenAnSQLInputSpecifiesAnIncorrectColumn() {
        testSpecReqPair(
                "fixtures/specs/4_valid-job-schema.json",
                "fixtures/specs/4_job-request-with-an-invalid-sql-error.json",
                req -> fail("Invalid request generated no validation errors"),
                validationErrors -> assertThat(validationErrors.size()).isGreaterThan(0));
    }

    @Test
    public void testValidateProducesAValidationErrorMessageWhenAnOptionsInputSpecifiesAnInvalidOption() {
        testSpecReqPair(
                "fixtures/specs/5_valid-job-schema.json",
                "fixtures/specs/5_job-request-with-invalid-option.json",
                req -> fail("Invalid request generated no validation errors"),
                validationErrors -> assertThat(validationErrors.size()).isGreaterThan(0));
    }

    @Test
    public void testValidateProducesAValidationErrorWhenFloatSpeccedButStringProvided() {
        testSpecReqPair(
                "fixtures/specs/7_job-spec-with-float.json",
                "fixtures/specs/7_req-with-string.json",
                req -> fail("Invalid request generated no validation errors"),
                validationErrors -> assertThat(validationErrors.size()).isGreaterThan(0));
    }

    @Test
    public void testValidateProducesNoValidationErrorWhenFloatSpeccedAndFloatProvided() {
        testSpecReqPair(
                "fixtures/specs/8_job-spec-with-float.json",
                "fixtures/specs/8_req-with-float.json",
                req -> {},
                validationErrors -> assertThat(validationErrors.size()).isEqualTo(0));
    }

    @Test
    public void testValidateProducesAValidationErrorWhenDoubleSpeccedButStringProvided() {
        testSpecReqPair(
                "fixtures/specs/9_job-spec-with-double.json",
                "fixtures/specs/9_req-with-string.json",
                req -> fail("Invalid request generated no validation errors"),
                validationErrors -> assertThat(validationErrors.size()).isGreaterThan(0));
    }

    @Test
    public void testValidateProducesNoValidationErrorWhenDoubleSpeccedAndDoubleProvided() {
        testSpecReqPair(
                "fixtures/specs/10_job-spec-with-double.json",
                "fixtures/specs/10_req-with-double.json",
                req -> {},
                validationErrors -> assertThat(validationErrors.size()).isEqualTo(0));
    }

    @Test
    public void testValidateProducesAValidationErrorWhenIntSpeccedByStringProvided() {
        testSpecReqPair(
                "fixtures/specs/11_job-spec-with-int.json",
                "fixtures/specs/11_req-with-string.json",
                req -> fail("Invalid request generated no validation errors"),
                validationErrors -> assertThat(validationErrors.size()).isGreaterThan(0));
    }

    @Test
    public void testValidateProducesNoValidationErrorWhenIntSpeccedAndIntProvided() {
        testSpecReqPair(
                "fixtures/specs/12_job-spec-with-int.json",
                "fixtures/specs/12_req-with-int.json",
                req -> {},
                validationErrors -> assertThat(validationErrors.size()).isEqualTo(0));
    }

    @Test
    public void testValidateProducesAValidationErrorWhenLongSpeccedButStringProvided() {
        testSpecReqPair(
                "fixtures/specs/13_job-spec-with-long.json",
                "fixtures/specs/13_req-with-string.json",
                req -> fail("Invalid request generated no validation errors"),
                validationErrors -> assertThat(validationErrors.size()).isGreaterThan(0));
    }

    @Test
    public void testValidateProducesNoValidationErrorWhenLongSpeccedAndLongProvided() {
        testSpecReqPair(
                "fixtures/specs/14_job-spec-with-long.json",
                "fixtures/specs/14_req-with-long.json",
                req -> {},
                validationErros -> assertThat(validationErros.size()).isEqualTo(0));
    }

    @Test
    public void testValidateProducesAValidationErrorWhenSpecContainsIntButALongValueIsProvided() {
        testSpecReqPair(
                "fixtures/specs/15_job-spec-with-int.json",
                "fixtures/specs/15_req-with-long.json",
                req -> fail("Invalid request generated no validation errors"),
                validationErrors -> assertThat(validationErrors.size()).isGreaterThan(0));
    }

    @Test
    public void testValidateReturnsValidationErrorsIfTheRequestContainsTheWrongInputTypesForTheSchema() {
        final JobSpecId jobSpecId = TestHelpers.generateJobSpecId();
        final JobExpectedInputId schemaInputId = TestHelpers.generateJobInputSchemaId();

        final List<JobExpectedInput<?>> expectedInputs = new ArrayList<>();
        expectedInputs.add(
                new StringArrayExpectedInput(
                        schemaInputId,
                        TestHelpers.generateRandomString(),
                        TestHelpers.generateRandomString(),
                        Optional.empty()));

        final JobSpec jobSpec =
                new JobSpec(
                        jobSpecId,
                        TestHelpers.generateRandomString(),
                        TestHelpers.generateRandomString(),
                        expectedInputs,
                        new ExecutionConfiguration(
                                TestHelpers.generateRandomString(),
                                Optional.empty(),
                                Optional.empty()));

        final APIUserId APIUserId = TestHelpers.generateUserId();

        final JobInput inputThatDoesntMatchSchema =
                new SelectInput(TestHelpers.generateRandomString());

        final Map<JobExpectedInputId, JobInput> inputs = new HashMap<>();
        inputs.put(schemaInputId, inputThatDoesntMatchSchema);

        final Map<JobExpectedInputId, JsonNode> genericInputs =
                Helpers.mapValues(inputs, TestHelpers::toJsonNode);

        final APICreateJobRequest apiCreateJobRequest =
                new APICreateJobRequest(
                        jobSpecId.toString(),
                        TestHelpers.generateRandomString(),
                        Helpers.mapKeys(genericInputs, JobExpectedInputId::toString));

        final Either<ValidJobRequest, List<ValidationError>> ret =
                ValidJobRequest.tryCreate(jobSpec, APIUserId, apiCreateJobRequest);

        ret.handleBoth(
                req -> fail("Invalid request generated no validation errors"),
                validationErrors -> assertThat(validationErrors.size()).isGreaterThan(0));
    }
}