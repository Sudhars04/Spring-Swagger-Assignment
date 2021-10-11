package com.example.assignment.service;

import com.example.assignment.model.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.Objects;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
@Service
public class SequenceGenerator {
    @Autowired
    private MongoOperations mongoOperations;


    public int generateSequence(String seqName){
        Query query=new Query(Criteria.where("seq").is(seqName));
        Update update=new Update().inc("value",1);
        Sequence counter=mongoOperations.findAndModify(query,update,
                options().returnNew(true).upsert(true), Sequence.class);
        return !Objects.isNull(counter) ? counter.getValue() : 1;
    }
}
