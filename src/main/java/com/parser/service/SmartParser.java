package com.parser.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SmartParser {
    private static File originHTMLFile;
    private static File otherHTMLFile;
    private static ArrayList<String> originAttributeValues = new ArrayList<>();
    private static ArrayList<Element> otherPageElements = new ArrayList<>();
    private static final String TARGET_ATTRIBUTE_ID = "make-everything-ok-button";
    private static final String CHARSET_NAME = "utf8";

    public SmartParser(String originPath, String otherPath) {
        originHTMLFile = new File(originPath);
        otherHTMLFile = new File(otherPath);
    }

    public String outputResult() throws IOException {
        parseAttributesFromDocuments();
        ArrayList<String> path = buildPath(findSimilarElement());
        String resultPath = formatOutput(path);
        return resultPath;
    }

    private void parseAttributesFromDocuments() throws IOException {
        Document originPage = Jsoup.parse(originHTMLFile, CHARSET_NAME, originHTMLFile.getAbsolutePath());
        Document otherPage = Jsoup.parse(otherHTMLFile, CHARSET_NAME, otherHTMLFile.getAbsolutePath());
        Element targetElement = originPage.getElementById(TARGET_ATTRIBUTE_ID);
        Attributes attributes = targetElement.attributes();

        for (Attribute a : attributes) {
            originAttributeValues.add(a.getValue());
        }

        for (Attribute s : attributes) {
            for (Element e : otherPage.getElementsByAttributeValue(s.getKey(), s.getValue())) {
                otherPageElements.add(e);
            }
        }
    }

    private Element findSimilarElement() {
        int maxCoincidence = 0;
        int counter = 0;
        Element targetElement = null;
        for (Element element : otherPageElements) {
            for (Attribute attr : element.attributes()) {
                if (originAttributeValues.contains(attr.getValue())) {
                    counter++;
                }
            }
            if (counter != 0 && counter > maxCoincidence) {
                maxCoincidence = counter;
                targetElement = element;
            }
            counter = 0;
        }
        return targetElement;
    }

    private ArrayList<String> buildPath(Element element) {
        ArrayList<String> arrayList = new ArrayList<>();
        Elements parentsPath = element.parents();
        arrayList.add(element.nodeName() + "[" + element.elementSiblingIndex() + "]");
        for (Element e : parentsPath) {
            arrayList.add(e.nodeName() + "[" + e.elementSiblingIndex() + "]");
        }
        return arrayList;
    }

    private String formatOutput(ArrayList<String> path) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = path.size() - 1; i >= 0; i--) {
            stringBuilder.append(path.get(i));
            if (i >= 1) {
                stringBuilder.append(" > ");
            }
        }
        return stringBuilder.toString();
    }
}

