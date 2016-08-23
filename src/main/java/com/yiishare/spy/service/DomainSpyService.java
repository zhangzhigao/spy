package com.yiishare.spy.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiishare.spy.domain.validdomain.IValidDomainRepository;
import com.yiishare.spy.domain.validdomain.ValidDomain;
import com.yiishare.spy.service.api.IDomainSpyService;
import com.yiishare.spy.utils.DateUtil;

/**
 * 
 * 有效域名服务实现类 <br> 
 *  
 * @author zhang.zhigao<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016年8月12日 <br>
 * @since V1.0<br>
 * @see com.yiishare.spy.service <br>
 */
@Service("domainSpyService")
public class DomainSpyService implements IDomainSpyService {
    
    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(DomainSpyService.class);
    
    /**
     * 注入仓储接口
     */
    @Autowired
    private IValidDomainRepository validDomainRepository;

    @Override
    public ValidDomain findValidDomain(String name) throws Exception {
//        logger.warn("name:" + name);
        String url = "http://checkdomain.xinnet.com/domainCheck?callbackparam=jQuery17203990228981646726_"
            + DateUtil.getCurrentTimeLong() + "&searchRandom=6&prefix="
            + name + "&suffix=.com&_=" + (DateUtil.getCurrentTimeLong() + 5);
        // 得到正文内容
        Document document = Jsoup.parse(getHtml(url));
        String result = document.html();
        if (null != result && !result.isEmpty() && result.contains("\"no\":[]")) {
            ValidDomain validDomain = new ValidDomain();
            validDomain.setDomainId(ObjectId.get().toHexString());
            validDomain.setCreateDate(DateUtil.getCurrentTimeString());
            validDomain.setName(name);
            logger.info(name);
            validDomainRepository.save(validDomain);
        }
        return null;
    }
    
    /**
     * 
     * 获取页面的HTML <br> 
     *  
     * @author zhang.zhigao<br>
     * @taskId <br>
     * @param urlStr url地址
     * @return 页面的HTML源码 <br>
     * @exception Exception if has error
     */
    public static String getHtml(String urlStr) throws Exception {
        StringBuffer sb = new StringBuffer();
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url
                .openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        if (connection.getResponseCode() == 200) {
            // 得到输入流
            InputStream input = connection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            while ((len = input.read(b)) != -1) {
                sb.append(new String(b, 0, len, "UTF-8"));
            }

        }
        return sb.toString();

    }

}
