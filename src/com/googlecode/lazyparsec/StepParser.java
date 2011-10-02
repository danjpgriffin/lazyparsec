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

final class StepParser<T> extends Parser<T> {
    private final Parser<T> parser;

    private final int n;

    StepParser(Parser<T> parser, int n) {
        this.parser = parser;
        this.n = n;
    }

    @Override
    boolean apply(ParseContext context) {
        int step = context.step;
        if (!parser.run(context)) return false;
        context.step = step + n;
        return true;
    }

    @Override
    public String toString() {
        return parser.toString();
    }
}