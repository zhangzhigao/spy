package com.yiishare.spy.domain.validdomain;

/**
 * 
 * ValidDoamin仓储接口 <br> 
 *  
 * @author zhang.zhigao<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016年8月12日 <br>
 * @since V1.0<br>
 * @see com.yiishare.spy.domain <br>
 */
public interface IValidDomainRepository {

    /**
     * 
     * 保存有效的域名对象 <br> 
     *  
     * @author zhang.zhigao<br>
     * @taskId <br>
     * @param validDomain <br>
     */
    public void save(ValidDomain validDomain);
}
