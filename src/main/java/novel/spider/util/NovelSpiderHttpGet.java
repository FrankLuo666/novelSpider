package novel.spider.util;

import java.net.URI;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

public class NovelSpiderHttpGet extends HttpGet {

    public NovelSpiderHttpGet() {
    }

    public NovelSpiderHttpGet(URI uri) {
        super(uri);
    }

    public NovelSpiderHttpGet(String uri) {
        super(uri);
        setDefaultConfig();
    }

    /**
     * 设置默认的请求参数
     */
    private void setDefaultConfig() {
        this.setConfig(RequestConfig.custom()
                .setSocketTimeout(2_000)
                .setConnectTimeout(10_000)	//设置连接服务器的超时时间
                .setConnectionRequestTimeout(10_000)	//设置从服务器读取数据的超时时间
                .build());
        this.setHeader("User-Agent", "NovelSpider");	//设置请求头
    }

}
