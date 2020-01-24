package com.contactsunny.MongoDBSortSpringBootPOC.utils;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MongoUtil {

    private static final Logger logger = LoggerFactory.getLogger(MongoUtil.class);

    @Autowired
    private MongoClient mongoClient;

    private MongoDatabase mongoDatabase;

    @Value("${mongodb.collections.sampleData.name}")
    private String sampleDataCollectionName;

    @Value("${mongo.database.name}")
    private String mongoDatabaseName;

    @PostConstruct
    private void postConstructor() {
        mongoDatabase = mongoClient.getDatabase(mongoDatabaseName);
    }

    public FindIterable<Document> getLowestDouble1Records() {

        BasicDBObject query = new BasicDBObject();

        BasicDBObject sort = new BasicDBObject();
        sort.put("double1", 1);

        return mongoDatabase.getCollection(sampleDataCollectionName).find(query).sort(sort).limit(2);
    }
}
