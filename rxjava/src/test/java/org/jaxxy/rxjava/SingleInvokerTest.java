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

package org.jaxxy.rxjava;

import javax.ws.rs.client.SyncInvoker;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import io.reactivex.Single;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SingleInvokerTest {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    @Mock
    private SyncInvoker syncInvoker;

    private SingleInvoker invoker;

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Before
    public void setUp() {
        this.invoker = new SingleInvoker(syncInvoker);
    }

    @Test
    public void trace() {
        final Response expected = Response.ok("Hello, RX!").build();
        when(syncInvoker.trace()).thenReturn(expected);
        final Single<Response> single = invoker.trace();
        assertThat(single.blockingGet()).isSameAs(expected);
        verify(syncInvoker).trace();
    }

    @Test
    public void traceWithGenericResponseType() {
        when(syncInvoker.trace(new GenericType<>(String.class))).thenReturn("Hello, RX!");
        final Single<String> single = invoker.trace(new GenericType<>(String.class));
        assertThat(single.blockingGet()).isEqualTo("Hello, RX!");
        verify(syncInvoker).trace(new GenericType<>(String.class));
    }

    @Test
    public void traceWithResponseType() {
        when(syncInvoker.trace(String.class)).thenReturn("Hello, RX!");
        final Single<String> single = invoker.trace(String.class);
        assertThat(single.blockingGet()).isEqualTo("Hello, RX!");
        verify(syncInvoker).trace(String.class);
    }

    @After
    public void verifyMock() {
        verifyNoMoreInteractions(syncInvoker);
    }
}