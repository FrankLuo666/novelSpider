package novel.spider.impl.spider.interfaces;

import novel.spider.impl.spider.entitys.Chapter;

import java.util.List;

public interface IChapterSpider {
	/**
	 * 给我们一个完整的url，我们就给你返回所有的章节列表
	 * @param url
	 * @return
	 */
	public List<Chapter> getsChapter(String url);
}
