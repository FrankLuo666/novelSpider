package novel.spider.junit;

import java.util.List;

import novel.spider.NovelSiteEnum;
import novel.spider.configuration.Configuration;
import novel.spider.impl.DefaultChapterDetailSpider;
import novel.spider.impl.NovelDownload;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.INovelDownload;
import novel.spider.util.NovelSpiderUtil;
import org.junit.Test;

import novel.spider.entitys.Chapter;
import novel.spider.impl.DefaultChapterSpider;
import novel.spider.interfaces.IChapterSpider;

public class Testcase {
	@Test
	public void testGetsChapter() throws Exception {
		IChapterSpider spider = new DefaultChapterSpider();
		List<Chapter>  chapters  = spider.getsChapter("https://www.bqgll.cc/book/4162/");
//		List<Chapter>  chapters  = spider.getsChapter("https://www.ddxsss.com/book/2521/");
		for (Chapter chapter : chapters) {
			System.out.println(chapter);
		}
	}

	@Test
	public void testGetSiteContext() {
		System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("https://www.bqgll.cc/book/4162/")));
	}

	/**
	 * 该测试方法用于测试是否能拿到笔趣阁网站的章节详细内容
	 */
	@Test
	public void testGetChapterDetail2() {
		IChapterDetailSpider spider = new DefaultChapterDetailSpider();
		System.out.println(spider.getChapterDetail("https://www.ddxsss.com/book/9202/1.html").getContent());
//		System.out.println(spider.getChapterDetail("https://www.52wx.com/0_517/18899144.html"));
	}

	@Test
	public void testDownload() {
		INovelDownload download = new NovelDownload();
		Configuration config = new Configuration();
		config.setPath("/Users/luo/Documents/other/novels");
		config.setSize(100);
		config.setTryTimes(10);
		System.out.println("下载好了，文件保存在：" + download.download("https://www.ddxsss.com/book/9202/", config) + "这里，赶紧去看看吧！");
	}

	@Test
	public void testMultiFileMerge() {
		NovelSpiderUtil.multiFileMerge("/Users/luo/Documents/other/novels", null, true);
	}
}
