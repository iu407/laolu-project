package com.prs.jy.utils;

import org.springframework.web.util.HtmlUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Discription:
 * Datetime: 2007-7-6 15:00:22
 *
 * @author Hellfire
 * @version 1.0
 */
public class Html {
    private String tagName;
    private Map<String, String> attributes = new HashMap<String, String>();
    private StringBuffer content = new StringBuffer();

    public Html(String tagName) {
        this.tagName = tagName;
    }

    public Html addAttribute(String name) {
        this.addAttribute(name, null);
        return this;
    }

    public Html addAttribute(String name, String value) {
        this.attributes.put(name, value);
        return this;
    }

    public Html addAttribute(String name, String value, boolean checkNull) {
        if (checkNull) {
            if (null != value) {
                this.attributes.put(name, value);
            }
        } else {
            this.attributes.put(name, value);
        }
        return this;
    }

    public Html addAttributes(Map<String, String> attributes) {
        this.attributes.putAll(attributes);
        return this;
    }

    public Html addHtml(Html html) {
        this.content.append(html);
        return this;
    }

    public Html addContent(String content) {
        this.content.append(HtmlUtils.htmlEscape(content));
        return this;
    }

    public Html addContent(String content, boolean escape) {
        if (escape) {
            return addContent(content);
        } else {
            this.content.append(content);
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuffer h = new StringBuffer();
        h.append("\n<");
        h.append(tagName);
        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            if (null == attribute.getValue()) {
                h.append(" ")
                        .append(attribute.getKey());
            } else {
                h.append(" ")
                        .append(attribute.getKey())
                        .append("=\"")
                        .append(HtmlUtils.htmlEscape(attribute.getValue()))
                        .append("\"");
            }
        }
        if (content.length() == 0) {
            h.append("/>\n");
        } else {
            h.append(">");
            /*if (!"a".equalsIgnoreCase(tagName)) {
                h.append("\n");
            }*/
            h.append(content);
            h.append("</")
                    .append(tagName)
                    .append(">\n");
        }

        return h.toString();
    }
}