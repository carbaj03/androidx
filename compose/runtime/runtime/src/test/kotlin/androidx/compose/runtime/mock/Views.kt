/*
 * Copyright 2019 The Android Open Source Project
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

package androidx.compose.runtime.mock

import androidx.compose.runtime.Composable
import androidx.compose.runtime.emit
import androidx.compose.runtime.key

@Composable
fun <T : Any> Repeated(
    of: Iterable<T>,
    block: @Composable (value: T) -> Unit
) {
    for (value in of) {
        key(value) {
            block(value)
        }
    }
}

@Composable
fun Linear(content: @Composable () -> Unit) {
    emit<View, ViewApplier>(
        ctor = { View().also { it.name = "linear" } },
        update = { }
    ) {
        content()
    }
}

@Composable
fun Text(value: String) {
    emit<View, ViewApplier>(
        ctor = { View().also { it.name = "text" } },
        update = { set(value) { text = it } }
    )
}

@Composable
fun Edit(value: String) {
    emit<View, ViewApplier>(
        ctor = { View().also { it.name = "edit" } },
        update = { set(value) { this.value = it } }
    )
}

@Composable
fun SelectBox(
    selected: Boolean,
    content: @Composable () -> Unit
) {
    if (selected) {
        emit<View, ViewApplier>(
            ctor = { View().also { it.name = "box" } },
            update = { },
            content = { content() }
        )
    } else {
        content()
    }
}