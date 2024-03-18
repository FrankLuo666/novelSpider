package novel.spider.interfaces;

import java.util.List;

import novel.spider.entitys.Novel;

/**
 * 爬取某个站点的小说列表
 */
public interface INovelSpider {
    /** 抓取某一个页面时最大的尝试次数3 */
    public static final int MAX_TRY_TIMES = 3;
    /**
     * 给我一个URL，我就个你一堆的小说实体
     * @param url
     * @return
     */
    public List<Novel> getsNovel(String url);
}
