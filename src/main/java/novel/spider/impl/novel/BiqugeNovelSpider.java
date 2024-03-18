package novel.spider.impl.novel;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.spider.NovelSiteEnum;
import novel.spider.entitys.Novel;
import novel.spider.util.NovelSpiderUtil;

/**
 * 笔趣阁网站的书籍列表爬取
 */
public class BiqugeNovelSpider extends AbstractNovelSpider {
    public BiqugeNovelSpider() {
    }

    @Override
    public List<Novel> getsNovel(String url) {
        List<Novel> novels = new ArrayList<>();
        try {
            // 获取所有的li标签
            Elements lis = super.getsTr(url, 2);
            // 遍历章节信息
            for (int index = 0, size = lis.size(); index < size; index++) {
                Element li = lis.get(index);
                Elements span = li.getElementsByTag("span");

                // 获取每个小说的信息
                Novel novel = new Novel();
                // 小说类型
                novel.setType(span.first().text());
                // 书名
                novel.setName(span.get(1).text());
                // 小说的url（章节列表url）
                String novelUrl = span.get(1).getElementsByTag("a").first().absUrl("href");
                novel.setUrl(novelUrl);
                // 小说的最新更新章节名
                novel.setLastUpdateChapter(span.get(2).text());
                // 小说的最新更新章节url
                novel.setLastUpdateChapterUrl(span.get(2).getElementsByTag("a").first().absUrl("href"));
                // 作者名
                novel.setAuthor(span.get(3).text());
                // 小说最后的更新时间
                novel.setLastUpdateTime(NovelSpiderUtil.getDate(span.get(4).text(), "MM-dd"));//2016-10-14 yyyy-MM-dd
                // 小说的状态：1 连载 2 完结
                novel.setStatus(NovelSpiderUtil.getNovelStatusByChapterName(novel.getLastUpdateChapter()));

                // 小说平台的ID(枚举中相应的小说ID)
                novel.setPlatformId(NovelSiteEnum.getEnumByUrl(url).getId());
                novels.add(novel);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return novels;
    }
}