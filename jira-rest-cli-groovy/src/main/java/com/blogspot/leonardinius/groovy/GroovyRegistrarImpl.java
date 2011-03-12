package com.blogspot.leonardinius.groovy;

import com.blogspot.leonardinius.api.Registrar;
import com.blogspot.leonardinius.api.ScriptService;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.script.ScriptEngine;

/**
 * User: leonidmaslov
 * Date: 3/13/11
 * Time: 12:37 AM
 */
public class GroovyRegistrarImpl implements Registrar, InitializingBean
{
// ------------------------------ FIELDS ------------------------------

    private final ScriptService scriptService;

// --------------------------- CONSTRUCTORS ---------------------------

    public GroovyRegistrarImpl(ScriptService scriptService)
    {
        this.scriptService = scriptService;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface InitializingBean ---------------------

    @Override
    public void afterPropertiesSet() throws Exception
    {
        GroovyScriptEngineFactory groovy = new GroovyScriptEngineFactory();
        for (String extension : groovy.getExtensions())
        {
            scriptService.registerEngineExtension(extension, groovy);
        }

        for (String mime : groovy.getMimeTypes())
        {
            scriptService.registerEngineMime(mime, groovy);
        }

        scriptService.registerEngineLanguage(groovy.getLanguageName(), groovy);
        scriptService.registerEngineLanguage((String) groovy.getParameter(ScriptEngine.NAME), groovy);
    }
}
