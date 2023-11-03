/*
 * Copyright 2021 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.verticals.foundations.dataharmonization.plugins.example.functions;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;

import com.google.cloud.verticals.foundations.dataharmonization.data.Container;
import com.google.cloud.verticals.foundations.dataharmonization.data.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Tests for InstanceFns (i.e. the functions within). */
@RunWith(JUnit4.class)
public class VariadicFnsTest {
  @Test
  public void exampleVariadicFn_noArgs_returnNull() {
    assertThat(VariadicFns.exampleVariadicFn().isNullOrEmpty()).isTrue();
  }

  @Test
  public void exampleVariadicFn_manyArgs_returnsFirst() {
    Container expected = mock(Container.class);

    Data actual =
        VariadicFns.exampleVariadicFn(expected, mock(Container.class), mock(Container.class));

    assertThat(actual).isSameInstanceAs(expected);
  }
}
