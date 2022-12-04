package org.example;

import freemarker.template.TemplateException;
import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException, TemplateException, IncorrectCatWeightException {
        DynamicPage dynamicPage = new DynamicPage();
        dynamicPage.createPage("result.html");
    }
}
