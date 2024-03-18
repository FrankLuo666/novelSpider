package novel.spider.util;

import novel.spider.NovelSiteEnum;
import novel.spider.impl.novel.BiqugeNovelSpider;
import novel.spider.interfaces.INovelSpider;

/**
 * 生产书籍列表的实现类
 */

public final class NovelSpiderFactory {
    private NovelSpiderFactory() {}
    public static INovelSpider getNovelSpider(String url) {
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        switch (novelSiteEnum) {
//            case Bxwx : return new BxwxNovelSpider();
            case BiQuGe2 : return new BiqugeNovelSpider();
            default : throw new RuntimeException(url + "暂时不被支持");
        }
    }
}
