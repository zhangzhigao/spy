package com.yiishare.spy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.yiishare.spy.service.api.IDomainSpyService;
import com.yiishare.spy.utils.DateUtil;

/**
 * <Description> <br>
 * 
 * @author zhang.zhigao<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016年8月13日 <br>
 * @since V1.0<br>
 * @see com.yiishare.spy <br>
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.yiishare")
@EnableAspectJAutoProxy
public class Application implements CommandLineRunner {

    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(Application.class);
    
    /**
     * 注入domainSpyService
     */
    @Autowired
    @Qualifier("domainSpyService")
    private IDomainSpyService domainSpyService;

    /**
     * 定义初始字符
     */
    private static char[] codes = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z'
    };

    /**
     * 查找深度
     */
    private static int maxHeap;

    /**
     * 基串长度
     */
    private static int codesLength;
    
    /**
     * 线程池
     */
    private ExecutorService executorService;

    /**
     * 程序主入口 <br>
     * 
     * @author zhang.zhigao<br>
     * @taskId <br>
     * @param args <br>
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        executorService = Executors.newFixedThreadPool(1000);
        int heap = 3;
        if (arg0.length > 0) {
            heap = Integer.valueOf(arg0[0]);
            start(codes, heap);
        }
        else {
            logger.error("请输入需要爬取的域名位数");
            System.exit(0);
        }
    }

    /**
     * 解码，使用自定义的字符集作为查询的基串 <br>
     * 
     * @author zhang.zhigao<br>
     * @param codes 自定义的编码
     * @param heap 查询的位数
     * @throws Exception if has error
     */
    public void start(char[] codes, int heap) throws Exception {
        codesLength = codes.length;
        maxHeap = heap;
        for (int i = 0; i < codesLength; i++) {
            search("", i);
        }
    }

    /**
     * 递归查询密码 <br>
     * 
     * @author zhang.zhigao<br>
     * @param strPrefix 当前层的串前缀
     * @param index 指示位数 <br>
     * @throws Exception if has error
     */
    private void search(String strPrefix, int index) throws Exception {
        // 设置返回条件，如果前缀已经大于等于搜索深度，那么返回到上一层，因为大于等于搜索深度的数据我们不再拼接
        if (strPrefix.length() >= maxHeap) {
            return;
        }
        else {
            // 拼接字符串
            String value = strPrefix + codes[index];
            executorService.execute(new SpyThread(value));
            for (int i = 0; i < codesLength; i++) {
                search(value, i);
            }
        }
    }
    
    /**
     * 
     * 爬取线程 <br> 
     *  
     * @author zhang.zhigao<br>
     * @version 1.0<br>
     * @taskId <br>
     * @CreateDate 2016年8月13日 <br>
     * @since V1.0<br>
     * @see com.yiishare.spy <br>
     */
    private class SpyThread implements Runnable {
        
        /**
         * domainName
         */
        private String domainName;
        
        /**
         * constructor
         * @param domainName domainName
         */
        public SpyThread(String domainName) {
            super();
            this.domainName = domainName;
        }

        @Override
        public void run() {
            try {
                logger.debug("开始时间：" + DateUtil.getCurrentTimeString());
                domainSpyService.findValidDomain(domainName);
                logger.debug("结束时间：" + DateUtil.getCurrentTimeString());
                logger.debug("结束时间：" + DateUtil.getCurrentTimeString());
            }
            catch (Exception e) {
                logger.error(e);
            }
        }
        
    }

}
