/*****************************************************************************
 * Copyright (C) Codehaus.org                                                *
 * ------------------------------------------------------------------------- *
 * Licensed under the Apache License, Version 2.0 (the "License");           *
 * you may not use this file except in compliance with the License.          *
 * You may obtain a copy of the License at                                   *
 *                                                                           *
 * http://www.apache.org/licenses/LICENSE-2.0                                *
 *                                                                           *
 * Unless required by applicable law or agreed to in writing, software       *
 * distributed under the License is distributed on an "AS IS" BASIS,         *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 * See the License for the specific language governing permissions and       *
 * limitations under the License.                                            *
 *****************************************************************************/
package com.googlecode.lazyparsec;

import com.googlecode.lazyparsec.util.Checks;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Delegates to the {@link Parser} object stored in a {@link AtomicReference}
 * dynamically when parsing.
 *
 * @author Ben Yu
 */
final class LazyParser<T> extends Parser<T> {
    private final AtomicReference<Parser<T>> ref;

    LazyParser(AtomicReference<Parser<T>> ref) {
        this.ref = ref;
    }

    @Override
    boolean apply(ParseContext context) throws Exception {
        return deref().apply(context);
    }

    private Parser<T> deref() {
        Parser<T> p = ref.get();
        Checks.checkNotNullState(p,
                "Uninitialized lazy parser reference. Did you forget to call set() on the reference?");
        return p;
    }

    @Override
    public String toString() {
        return "lazy";
    }
}
