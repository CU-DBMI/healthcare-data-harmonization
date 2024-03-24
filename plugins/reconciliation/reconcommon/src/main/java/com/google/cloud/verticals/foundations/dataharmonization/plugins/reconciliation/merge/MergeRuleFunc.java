/*
 * Copyright 2021 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.verticals.foundations.dataharmonization.plugins.reconciliation.merge;

import static com.google.cloud.verticals.foundations.dataharmonization.plugins.reconciliation.merge.MergeConstants.RULE_METHOD_SUFFIX;

import com.google.cloud.verticals.foundations.dataharmonization.data.Data;
import com.google.cloud.verticals.foundations.dataharmonization.function.context.RuntimeContext;

/**
 * MergeRuleFunc represents a function that reads the configured merge rule for the given resource
 * type.
 */
@FunctionalInterface
public interface MergeRuleFunc {
  /** Execute a function to get the merge rule. */
  Data getMergeRule(RuntimeContext ctx);

  /** Get the merge rule function name given a resource type. */
  static String getFunctionName(String resourceType) {
    return resourceType + RULE_METHOD_SUFFIX;
  }
}
