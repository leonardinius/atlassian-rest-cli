package com.blogspot.leonardinius.actions;

import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.blogspot.leonardinius.api.LanguageUtils;
import com.blogspot.leonardinius.api.ScriptService;
import com.google.common.collect.Lists;

import javax.script.ScriptEngineFactory;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: leonidmaslov
 * Date: 3/13/11
 * Time: 11:34 AM
 */
public class CliAction extends JiraWebActionSupport
{
// ------------------------------ FIELDS ------------------------------

    private final ScriptService scriptService;

// --------------------------- CONSTRUCTORS ---------------------------

    public CliAction(ScriptService scriptService)
    {
        this.scriptService = scriptService;
    }

// -------------------------- OTHER METHODS --------------------------

    public List<LanguageBean> getRegisteredLanguages()
    {
        List<LanguageBean> list = Lists.newArrayList();
        for (ScriptEngineFactory factory : scriptService.getRegisteredScriptEngines())
        {
            list.add(new LanguageBean(LanguageUtils.getLanguageName(factory), LanguageUtils.getVersionString(factory)));
        }

        Collections.sort(list, new Comparator<LanguageBean>()
        {
            @Override
            public int compare(LanguageBean o1, LanguageBean o2)
            {
                return String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
            }
        });
        return list;
    }

    // -------------------------- INNER CLASSES --------------------------

    public static class LanguageBean
    {
        private final String name;

        private final String version;

        public LanguageBean(String name, String version)
        {
            this.name = name;
            this.version = version;
        }

        public String getName()
        {
            return name;
        }

        public String getVersion()
        {
            return version;
        }
    }
}