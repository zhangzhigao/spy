package com.yiishare.spy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * 
 * <Description> <br> 
 *  
 * @author zhang.zhigao<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016年8月13日 <br>
 * @since V1.0<br>
 * @see com.yiishare.spy.config <br>
 */
@Configuration
public class MongoTemplateConfig {

    /**
     * 
     * spyMongoTemplate <br> 
     *  
     * @author zhang.zhigao<br>
     * @taskId <br>
     * @return <br>
     */
    @Bean
    public MongoTemplate spyMongoTemplate() {
        Mongo mongo = new MongoClient("127.0.0.1", 27017);
        MongoTemplate spyMongoTemplate = new MongoTemplate(mongo , "test");
        return spyMongoTemplate;
    }
}
