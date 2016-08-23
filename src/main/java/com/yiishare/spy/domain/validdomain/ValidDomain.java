package com.yiishare.spy.domain.validdomain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

/**
 * 
 * 有效域名 <br> 
 *  
 * @author zhang.zhigao<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016年8月12日 <br>
 * @since V1.0<br>
 * @see com.yiishare.spy.domain <br>
 */
public class ValidDomain implements Serializable {
    
    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = 4975346049739441081L;
    
    /**
     * domainId
     */
    @Id
    private String domainId;

    /**
     * 域名
     */
    private String name;
    
    /**
     * 找到时间
     */
    private String createDate;
    
    /**
     * com
     */
    private String validCom;
    
    /**
     * cn
     */
    private String validCn;
    
    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getValidCom() {
        return validCom;
    }

    public void setValidCom(String validCom) {
        this.validCom = validCom;
    }

    public String getValidCn() {
        return validCn;
    }

    public void setValidCn(String validCn) {
        this.validCn = validCn;
    }
    
}
