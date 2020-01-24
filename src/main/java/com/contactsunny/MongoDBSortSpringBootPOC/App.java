package com.contactsunny.MongoDBSortSpringBootPOC;

import com.contactsunny.MongoDBSortSpringBootPOC.utils.MongoUtil;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    @Autowired
    private MongoUtil mongoUtil;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        FindIterable<Document> documents = this.mongoUtil.getLowestDouble1Records();

        for (Document document : documents) {

            if (document != null) {
                logger.info("Fetched document: " + document.toJson());
            }
        }
    }
}
