package com.lxg.springboot;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * Created by admin on 2017/2/6.
 */
@Configuration
public class HttpClient {



    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager demo(){
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        httpClientConnectionManager.setMaxTotal(100);
        httpClientConnectionManager.setDefaultMaxPerRoute(20);
        return httpClientConnectionManager;
    }

    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder demo2(@Qualifier("httpClientConnectionManager")PoolingHttpClientConnectionManager httpClientConnectionManager){

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        httpClientBuilder.setConnectionManager(httpClientConnectionManager);

        return httpClientBuilder;
    }

    @Bean
    public CloseableHttpClient demo3(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder){
        return httpClientBuilder.build();
    }


    @Bean(name = "builder")
    public RequestConfig.Builder demo4(){
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.setConnectTimeout(1000).setSocketTimeout(10000).setStaleConnectionCheckEnabled(true);
    }

    @Bean
    public RequestConfig demo5(@Qualifier("builder") RequestConfig.Builder builder){
        return builder.build();
    }

}
