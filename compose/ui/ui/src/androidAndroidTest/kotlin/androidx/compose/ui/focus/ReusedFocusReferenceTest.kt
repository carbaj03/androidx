/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.ui.focus

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState.Active
import androidx.compose.ui.focus.FocusState.Inactive
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class ReusedFocusReferenceTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun oneComponent() {
        // Arrange.
        lateinit var focusState: FocusState
        val focusReference = FocusReference()
        rule.setFocusableContent {
            Box(
                modifier = Modifier
                    .onFocusChanged { focusState = it }
                    .focusReference(focusReference)
                    .focusModifier()
            )
        }

        rule.runOnIdle {
            // Act.
            focusReference.requestFocus()

            // Assert.
            assertThat(focusState).isEqualTo(Active)
        }
    }

    @Test
    fun twoComponents() {
        // Arrange.
        lateinit var focusState1: FocusState
        lateinit var focusState2: FocusState
        val focusReference = FocusReference()
        rule.setFocusableContent {
            Box(
                modifier = Modifier
                    .onFocusChanged { focusState1 = it }
                    .focusReference(focusReference)
                    .focusModifier()
            )
            Box(
                modifier = Modifier
                    .onFocusChanged { focusState2 = it }
                    .focusReference(focusReference)
                    .focusModifier()
            )
        }

        rule.runOnIdle {
            // Act.
            focusReference.requestFocus()

            // Assert.
            assertThat(focusState1).isEqualTo(Inactive)
            assertThat(focusState2).isEqualTo(Active)
        }
    }

    @Test
    fun focusReferenceUsedWithThreeComponent() {
        // Arrange.
        lateinit var focusState1: FocusState
        lateinit var focusState2: FocusState
        lateinit var focusState3: FocusState
        val focusReference = FocusReference()
        rule.setFocusableContent {
            Box(
                modifier = Modifier
                    .onFocusChanged { focusState1 = it }
                    .focusReference(focusReference)
                    .focusModifier()
            )
            Box(
                modifier = Modifier
                    .onFocusChanged { focusState2 = it }
                    .focusReference(focusReference)
                    .focusModifier()
            )
            Box(
                modifier = Modifier
                    .onFocusChanged { focusState3 = it }
                    .focusReference(focusReference)
                    .focusModifier()
            )
        }

        rule.runOnIdle {
            // Act.
            focusReference.requestFocus()

            // Assert.
            assertThat(focusState1).isEqualTo(Inactive)
            assertThat(focusState2).isEqualTo(Inactive)
            assertThat(focusState3).isEqualTo(Active)
        }
    }
}
