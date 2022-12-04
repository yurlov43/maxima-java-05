package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class DynamicPage {
    public void createPage(String filename) throws IOException, IncorrectCatWeightException, TemplateException {
        FileWriter fileWriter = new FileWriter(filename);
        String resourcesPath = "templates";
        Configuration config = new Configuration(Configuration.VERSION_2_3_31);
        config.setDirectoryForTemplateLoading(new File(resourcesPath));
        config.setDefaultEncoding("UTF-8");

        Map<String, Cat> root = new HashMap<String, Cat>();
        Cat murka = new Cat("Murka", 5, false);
        root.put(murka.getName(), murka);
        Cat barsik = new Cat("Barsik", 10, true);
        root.put(barsik.getName(), barsik);
        Cat felix = new Cat("Felix", 15, false);
        root.put(felix.getName(), felix);

        Template template = config.getTemplate("index.html");
        template.process(root, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }


}
