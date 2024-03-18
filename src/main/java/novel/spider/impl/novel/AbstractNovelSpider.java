package novel.spider.impl.novel;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import novel.spider.NovelSiteEnum;
import novel.spider.impl.AbstractSpider;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NovelSpiderUtil;

/**
 * 一个抽象的小说列表抓取，已经实现了解析tr元素的方法
 */
public abstract class AbstractNovelSpider extends AbstractSpider implements INovelSpider {

    /**
     * 默认的抓取方法，最多会尝试 {@link INovelSpider#MAX_TRY_TIMES} 次下载
     * @param url
     * @return
     * @throws Exception
     */
    protected Elements getsTr(String url) throws Exception {
        return getsTr(url, INovelSpider.MAX_TRY_TIMES);
    }
    /**
     * 以指定次数的形式去解析对应网页
     * @param url
     * @param maxTryTimes 如果为null， 则 默认是 {@link INovelSpider#MAX_TRY_TIMES}
     * @return
     * @throws Exception
     */
    protected Elements getsTr(String url, Integer maxTryTimes) throws Exception {
        maxTryTimes = maxTryTimes == null ? INovelSpider.MAX_TRY_TIMES : maxTryTimes;
        Elements trs = null;
        for (int i = 0; i < maxTryTimes ; i++) {
            try {
                String result = super.crawl(url);
                Map<String, String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
                String novelSelector = context.get("novel-selector");
                if (novelSelector == null) throw new RuntimeException(NovelSiteEnum.getEnumByUrl(url).getUrl() + ",url=" + url + "目前不支持抓取小说列表");
                Document doc = Jsoup.parse(result);
                trs = doc.select(novelSelector);
                return trs;
            } catch (Exception e) {

            }
        }
        throw new RuntimeException(url + ",尝试了" + maxTryTimes + "次依然下载失败了！");
    }
}
