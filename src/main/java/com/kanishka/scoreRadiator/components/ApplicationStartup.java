package com.kanishka.scoreRadiator.components;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

@Component
public class ApplicationStartup implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
    private static final Collection<String> INPUT_XML_FILES = Arrays.asList("Result_1.xml","Result_2.xml");

    @Autowired
    private MongoTemplate mongoTemplate;

    private void seedData() {
        for (String inputFile : INPUT_XML_FILES) {
            String json = processXML2JSON(inputFile);
            insertToMongo(json);
        }
    }

    private String processXML2JSON(String input) throws JSONException {
        String XML_STRING = null;
        Resource resource=new ClassPathResource(input);
        try {
            InputStream inputStream = resource.getInputStream();
            Scanner scanner = new java.util.Scanner(inputStream,"UTF-8").useDelimiter("\\A");
            XML_STRING = scanner.hasNext() ? scanner.next() : "";
            scanner.close();
        } catch (IOException e) {
            logger.error("Error reading xml file" + e);
        }
        JSONObject xmlJSONObj = XML.toJSONObject(XML_STRING);
        return xmlJSONObj.toString();
    }

    private void insertToMongo(String jsonString){
        Document doc = Document.parse(jsonString);
        mongoTemplate.insert(doc, "benchmarks");
    }

    @Override
    public void run(ApplicationArguments args) {
        seedData();
    }
}
