package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet("/cat")
public class CatServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        responseGenerate(request);
        responseSend(response);
    }

    private void responseGenerate(HttpServletRequest request) throws IOException {
        String name = request.getParameter("name");
        int weight = Integer.parseInt(request.getParameter("weight"));
        boolean isAngry = Boolean.parseBoolean(request.getParameter("isAngry"));

        FileWriter fileWriter = new FileWriter("result.html");
        String resourcesPath = Objects.requireNonNull(CatServlet.class.getClassLoader().getResource("templates")).getPath();
        Configuration config = new Configuration(Configuration.VERSION_2_3_31);
        config.setDirectoryForTemplateLoading(new File(resourcesPath));
        config.setDefaultEncoding("UTF-8");

        Map<String, Cat> root = new HashMap<>();
        Cat cat;
        try {
            cat = new Cat(name, weight, isAngry);
        } catch (IncorrectCatWeightException e) {
            throw new RuntimeException(e);
        }
        root.put("Cat", cat);

        Template template = config.getTemplate("cat.html");
        try {
            template.process(root, fileWriter);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }

        fileWriter.flush();
        fileWriter.close();
    }

    private void responseSend(HttpServletResponse response) throws IOException {
        FileReader fileReader = new FileReader("result.html");
        BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();

        StringBuilder result = new StringBuilder();

        while(line != null) {
            result.append(line);
            line = reader.readLine();
        }

        fileReader.close();

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.write(result.toString());
        printWriter.close();
    }
}
