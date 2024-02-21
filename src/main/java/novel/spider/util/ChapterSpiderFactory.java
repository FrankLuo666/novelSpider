package novel.spider.util;

import novel.spider.NovelSiteEnum;
import novel.spider.impl.BxwxChapterSpider;
import novel.spider.impl.DefaultChapterSpider;
import novel.spider.interfaces.IChapterSpider;

public final class ChapterSpiderFactory {
	private ChapterSpiderFactory() {}

	/**
	 * 通过给定的url，返回一个实现了IChapterSpider接口的实现类
	 * @param url
	 * @return
	 */
	public static IChapterSpider getChapterSpider(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		IChapterSpider chapterSpider = null;
		switch (novelSiteEnum) {
//			case Bxwx :
//				chapterSpider = new BxwxChapterSpider(); break;
			case DingDianXiaoShuo:
			case BiQuGe1:
			case BiQuGe2 :
				chapterSpider = new DefaultChapterSpider(); break;
		}
		return chapterSpider;
	}
}
