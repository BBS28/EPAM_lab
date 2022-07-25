package com.epam.shchehlov.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class LocaleTag extends SimpleTagSupport {

    private static final Logger logger = Logger.getLogger(LocaleTag.class);
    private static final String SELECT_OPEN = "<select class=\"locale-switcher\" id=\"locale-switcher\" onchange=\"location = this.value\">";
    private static final String OPTION_VALUE = "<option value=\"";
    private static final String CLOSE_TAG = "\">";
    private static final String OPTION_CLOSE = "</option>";
    private static final String PARAMETER_LANG = "?lang=";
    private static final String EN = "en";
    private static final String RU = "ru";
    private static final String ENGLISH = "English";
    private static final String RUSSIAN = "Русский";
    private static final String SELECT_CLOSE = "</select>";

    private String localeName;
    private String url;

    public void setLocaleName(String localeName) {
        this.localeName = localeName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append(SELECT_OPEN) //"<select id="locale-switcher" onchange="location = this.value">"
                .append(OPTION_VALUE)
                .append(getCurrentLanguage())
                .append(CLOSE_TAG)
                .append(getCurrentLanguage())
                .append(OPTION_CLOSE) //"<option value=\"" + localeName + "\">" + localeName + "</option>"
                .append(OPTION_VALUE)
                .append(url)
                .append(PARAMETER_LANG)
                .append(EN)
                .append(CLOSE_TAG)
                .append(ENGLISH)
                .append(OPTION_CLOSE) //"<option value=\"" + url + "?lang=" + en + "\">" + English + "</option>"
                .append(OPTION_VALUE)
                .append(url)
                .append(PARAMETER_LANG)
                .append(RU)
                .append(CLOSE_TAG)
                .append(RUSSIAN)
                .append(OPTION_CLOSE) //"<option value=\"" + url + "?lang=" + ru + "\">" + Русский + "</option>"
                .append(SELECT_CLOSE); //"</select>"

        out.print(stringBuilder);
    }

    private String getCurrentLanguage() {
        logger.debug("in Tag localeName ==> " + localeName);
        if (RU.equals(localeName)) {
            return RUSSIAN;
        }
        return ENGLISH;
    }
}
