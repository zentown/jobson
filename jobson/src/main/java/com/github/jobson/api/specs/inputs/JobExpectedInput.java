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

package com.github.jobson.api.specs.inputs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.jobson.api.specs.inputs.f32.F32ExpectedInput;
import com.github.jobson.api.specs.inputs.f64.F64ExpectedInput;
import com.github.jobson.api.specs.inputs.i32.I32ExpectedInput;
import com.github.jobson.api.specs.inputs.i64.I64ExpectedInput;
import com.github.jobson.api.specs.inputs.select.SelectExpectedInput;
import com.github.jobson.api.specs.inputs.sql.SQLExpectedInput;
import com.github.jobson.api.specs.inputs.string.StringExpectedInput;
import com.github.jobson.api.specs.inputs.stringarray.StringArrayExpectedInput;
import com.github.jobson.util.ValidationError;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@ApiModel(description = "The schema of a job input")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SQLExpectedInput.class, name = "sql"),
        @JsonSubTypes.Type(value = SelectExpectedInput.class, name = "select"),
        @JsonSubTypes.Type(value = StringExpectedInput.class, name = "string"),
        @JsonSubTypes.Type(value = StringArrayExpectedInput.class, name = "string[]"),
        @JsonSubTypes.Type(value = F32ExpectedInput.class, name = "float"),
        @JsonSubTypes.Type(value = F64ExpectedInput.class, name = "double"),
        @JsonSubTypes.Type(value = I32ExpectedInput.class, name = "int"),
        @JsonSubTypes.Type(value = I64ExpectedInput.class, name = "long"),
})
public abstract class JobExpectedInput<TJobInput extends JobInput> {

    @JsonProperty
    @NotNull
    private JobExpectedInputId id;

    @JsonProperty
    @NotNull
    private String name;

    @JsonProperty
    @NotNull
    private String description;

    @JsonProperty("default")
    private Optional<TJobInput> defaultVal = Optional.empty();


    /**
     * @deprecated Used by JSON deserilizer.
     */
    public JobExpectedInput() {}

    public JobExpectedInput(
            JobExpectedInputId id,
            String name,
            String description,
            Optional<TJobInput> defaultVal) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.defaultVal = defaultVal;
    }


    public JobExpectedInputId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Optional<TJobInput> getDefault() {
        return defaultVal;
    }


    @JsonIgnore
    public abstract Class<TJobInput> getExpectedInputClass();

    @JsonIgnore
    public abstract Optional<List<ValidationError>> validate(TJobInput input);

    @JsonIgnore
    public abstract TJobInput generateExampleInput();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobExpectedInput<?> that = (JobExpectedInput<?>) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        return defaultVal != null ? defaultVal.equals(that.defaultVal) : that.defaultVal == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (defaultVal != null ? defaultVal.hashCode() : 0);
        return result;
    }
}
