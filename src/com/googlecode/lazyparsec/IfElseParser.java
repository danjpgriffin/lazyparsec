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

import com.googlecode.totallylazy.Callable1;

final class IfElseParser<T, C> extends Parser<T> {
    private final Parser<C> cond;
    private final Callable1<? super C, ? extends Parser<? extends T>> consequence;
    private final Parser<? extends T> alternative;

    IfElseParser(Parser<C> cond,
                 Callable1<? super C, ? extends Parser<? extends T>> consequence, Parser<? extends T> alternative) {
        this.cond = cond;
        this.consequence = consequence;
        this.alternative = alternative;
    }

    @Override
    boolean apply(ParseContext context) throws Exception {
        final Object ret = context.result;
        final int step = context.step;
        final int at = context.at;
        if (ParserInternals.runWithoutRecordingError(cond, context)) {
            Parser<? extends T> parser = consequence.call(cond.getReturn(context));
            return parser.run(context);
        }
        context.set(step, at, ret);
        return alternative.run(context);
    }

    @Override
    public String toString() {
        return "ifelse";
    }
}