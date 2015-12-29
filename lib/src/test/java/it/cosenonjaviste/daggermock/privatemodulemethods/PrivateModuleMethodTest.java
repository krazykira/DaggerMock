/*
 *  Copyright 2016 Fabio Collini.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.cosenonjaviste.daggermock.privatemodulemethods;

import org.junit.Test;
import org.mockito.Mock;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class PrivateModuleMethodTest {

    @Mock MyService myService;

    @Test
    public void testErrorOnNotPublicMethods() throws Throwable {
        try {
            DaggerMockRule<MyComponent> rule = new DaggerMockRule<>(MyComponent.class, new MyModule());
            rule.apply(null, null, this).evaluate();
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("java.lang.RuntimeException: The following methods has to be public:\n" +
                    "it.cosenonjaviste.daggermock.privatemodulemethods.MyService it.cosenonjaviste.daggermock.privatemodulemethods.MyModule.provideMyService()");
        }
    }
}
