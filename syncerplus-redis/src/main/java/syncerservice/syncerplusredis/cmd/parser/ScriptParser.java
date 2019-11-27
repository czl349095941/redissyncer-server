/*
 * Copyright 2016-2018 Leon Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package syncerservice.syncerplusredis.cmd.parser;

import syncerservice.syncerplusredis.cmd.CommandParser;
import syncerservice.syncerplusredis.cmd.impl.ScriptCommand;
import syncerservice.syncerplusredis.cmd.impl.ScriptFlushCommand;
import syncerservice.syncerplusredis.cmd.impl.ScriptLoadCommand;
import syncerservice.syncerplusredis.cmd.CommandParsers;
import syncerservice.syncerplusredis.util.objectutil.Strings;

import static syncerservice.syncerplusredis.util.objectutil.Strings.isEquals;

/**
 * @author Leon Chen
 * @since 2.1.0
 */
public class ScriptParser implements CommandParser<ScriptCommand> {
    @Override
    public ScriptCommand parse(Object[] command) {
        int idx = 1;
        String keyword = CommandParsers.toRune(command[idx++]);
        if (Strings.isEquals(keyword, "LOAD")) {
            byte[] script = CommandParsers.toBytes(command[idx]);
            idx++;
            return new ScriptLoadCommand(script);
        } else if (Strings.isEquals(keyword, "FLUSH")) {
            return new ScriptFlushCommand();
        }
        throw new AssertionError("SCRIPT " + keyword);
    }


}