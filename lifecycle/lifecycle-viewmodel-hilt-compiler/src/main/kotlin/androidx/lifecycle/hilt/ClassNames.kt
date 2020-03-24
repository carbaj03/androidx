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

package androidx.lifecycle.hilt

import com.squareup.javapoet.ClassName

internal object ClassNames {
    val ACTIVITY_RETAINED_COMPONENT =
        ClassName.get("dagger.hilt.android.components", "ActivityRetainedComponent")
    val BINDS = ClassName.get("dagger", "Binds")
    val NON_NULL = ClassName.get("androidx.annotation", "NonNull")
    val INJECT = ClassName.get("javax.inject", "Inject")
    val INSTALL_IN = ClassName.get("dagger.hilt", "InstallIn")
    val INTO_MAP = ClassName.get("dagger.multibindings", "IntoMap")
    val MODULE = ClassName.get("dagger", "Module")
    val PROVIDER = ClassName.get("javax.inject", "Provider")
    val VIEW_MODEL_ASSISTED_FACTORY =
        ClassName.get("androidx.lifecycle.hilt", "ViewModelAssistedFactory")
    val VIEW_MODEL = ClassName.get("androidx.lifecycle", "ViewModel")
    val VIEW_MODEL_KEY = ClassName.get("androidx.lifecycle.hilt", "ViewModelKey")
    val SAVED_STATE_HANDLE = ClassName.get("androidx.lifecycle", "SavedStateHandle")
}
