/*
 * Copyright (c) 2018 The Jaxxy Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jaxxy.test;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;

@Getter
public class DefaultJaxrsClientConfig implements JaxrsClientConfig {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final List<Object> providers = new LinkedList<>();

//----------------------------------------------------------------------------------------------------------------------
// JaxrsClientConfig Implementation
//----------------------------------------------------------------------------------------------------------------------


    @Override
    public <P> JaxrsClientConfig withProvider(P provider) {
        providers.add(provider);
        return this;
    }
}