package novel.spider.impl.spider.impl;

import novel.spider.impl.spider.NovelSiteEnum;
import novel.spider.impl.spider.entitys.Chapter;
import novel.spider.impl.spider.interfaces.IChapterSpider;
import novel.spider.impl.spider.util.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 抓取任意网站的章节列表
 * @author LiuKeFeng
 * @date   2016年10月11日
 */
public abstract class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {
	@Override
	public List<Chapter> getsChapter(String url) {
		try {
			String result = crawl(url);
			Document doc = Jsoup.parse(result);
			doc.setBaseUri(url);
			Elements as = doc.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
			List<Chapter> chapters = new ArrayList<>();
			for (Element a : as) {
				Chapter chapter = new Chapter();
				chapter.setTitle(a.text());
				chapter.setUrl(a.absUrl("href"));
				chapters.add(chapter);
			}
			return chapters;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
