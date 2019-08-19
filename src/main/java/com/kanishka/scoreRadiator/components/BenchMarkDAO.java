package com.kanishka.scoreRadiator.components;

import com.kanishka.scoreRadiator.domain.Benchmark;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BenchMarkDAO {

    @Autowired
    private MongoTemplate mongoTemplate;


        public List<Benchmark> getDataFromDb() {

            List <Benchmark> benchmarks = new ArrayList<>();
            MongoCollection<Document> collection =    mongoTemplate.getCollection("benchmarks");
            List<Document> documents = collection.find().into(
                    new ArrayList<Document>());

            for(Document document : documents){
                Benchmark benchmark = new Benchmark();
                Document doc = (Document) document.get("benchmark");
                Document docResults = (Document)  doc.get("results");
                List<Document> scores = (List) docResults.get("result");
                for(Document score : scores){
                    if(String.valueOf(score.get("passIndex")).equals("-1")){
                        benchmark.setDigitalContentCreationScore(String.valueOf(score.get("DigitalContentCreationScore")));
                        benchmark.setEssentialsScore(String.valueOf(score.get("EssentialsScore")));
                        benchmark.setPCMark10Score(String.valueOf(score.get("PCMark10Score")));
                        benchmark.setProductivityScore(String.valueOf(score.get("ProductivityScore")));
                    }
                }
                benchmarks.add(benchmark);
            }
            return benchmarks;
        }

}
