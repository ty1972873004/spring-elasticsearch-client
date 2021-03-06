package com.github.elasticsearch.client.connection;

import org.elasticsearch.client.RestHighLevelClient;

import java.util.Map;
import java.util.Properties;

/**
 * @author wangl
 * @date 2019-04-30
 */
public class ElasticsearchClientFactory {

    private RestClientPool<RestHighLevelClient> pool;
    private Map<String, String> defaultHeaders;

    public ElasticsearchClientFactory(RestClientConfiguration configuration, RestClientPoolConfig poolConfig) {
        createPool(configuration, poolConfig);
    }

    public void setDefaultHeaders(Map<String, String> defaultHeaders) {
        this.defaultHeaders = defaultHeaders;
    }

    public RestHighLevelClient getResource() {
        return pool.getResource();
    }

    public void returnResource(RestHighLevelClient restHighLevelClient){
        pool.returnResource(restHighLevelClient);
    }

    public void close(){
        if(pool != null){
            pool.close();
        }
    }

    private void createPool(RestClientConfiguration configuration, RestClientPoolConfig poolConfig){
        RestHighLevelClientFactory clientFactory = new RestHighLevelClientFactory(configuration, defaultHeaders);
        pool = new RestClientPool<>(poolConfig, clientFactory);
    }

}
