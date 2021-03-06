/*
 * Copyright 2011 Leonid Maslov<leonidms@gmail.com>
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

package com.galeoconsulting.leonardinius.api;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

/**
 * User: leonidmaslov
 * Date: 3/12/11
 * Time: 11:01 PM
 */
@SuppressWarnings({"UnusedDeclaration"})
public interface ScriptService {
// -------------------------- OTHER METHODS --------------------------

    void defaultRegistration(ScriptEngineFactory factory);

    /**
     * A class loader that delegates to a list of class loaders. The order is important as classes and resources will be
     * loaded from the first classloader that can load them.
     *
     * @param chainingClassLoaders classloaders to delegate to
     * @return chainig classloader
     */
    ClassLoader getClassLoader(ClassLoader... chainingClassLoaders);

    ScriptEngine getEngineByExtension(String extension);

    ScriptEngine getEngineByLanguage(String language);

    ScriptEngine getEngineByMime(String mime);

    Iterable<ScriptEngineFactory> getRegisteredScriptEngines();

    void registerEngineExtension(String extension, ScriptEngineFactory factory);

    void registerEngineLanguage(String language, ScriptEngineFactory factory);

    void registerEngineMime(String mime, ScriptEngineFactory factory);

    @SuppressWarnings({"UnusedReturnValue"})
    boolean removeEngine(ScriptEngineFactory factory);
}
