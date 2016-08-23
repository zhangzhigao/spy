package com.yiishare.spy.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.yiishare.spy.domain.validdomain.IValidDomainRepository;
import com.yiishare.spy.domain.validdomain.ValidDomain;

/**
 * 
 * ValidDomain仓储实现类 <br> 
 *  
 * @author zhang.zhigao<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016年8月12日 <br>
 * @since V1.0<br>
 * @see com.yiishare.spy.repository <br>
 */
@Repository("validDomainRepository")
public class ValidDomainRepository implements IValidDomainRepository {
    
    /**
     * 注入spyMongoTemplate
     */
    @Autowired
    @Qualifier("spyMongoTemplate")
    private MongoTemplate spyMongoTemplate;

    @Override
    public void save(ValidDomain validDomain) {
        spyMongoTemplate.save(validDomain, "VALID_DOMAIN");
    }

}
