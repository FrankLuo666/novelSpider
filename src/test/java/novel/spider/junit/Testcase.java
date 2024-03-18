package novel.spider.junit;

import java.util.List;

import novel.spider.NovelSiteEnum;
import novel.spider.configuration.Configuration;
import novel.spider.entitys.Novel;
import novel.spider.impl.chapter.DefaultChapterDetailSpider;
import novel.spider.impl.download.NovelDownload;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.INovelDownload;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NovelSpiderFactory;
import novel.spider.util.NovelSpiderUtil;
import org.junit.Test;

import novel.spider.entitys.Chapter;
import novel.spider.impl.chapter.DefaultChapterSpider;
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
//		System.out.println(spider.getChapterDetail("https://www.ddxsss.com/book/9202/1.html").getContent());
//		spider.getChapterDetail("https://www.52wx.com/0_517/18899144.html");
		System.out.println(spider.getChapterDetail("https://www.52wx.com/0_517/18899144.html").getContent());
	}

	@Test
	public void testDownload() {
		INovelDownload download = new NovelDownload();
		Configuration config = new Configuration();
		config.setPath("/Users/luo/Documents/other/novels");
		config.setSize(100);
		config.setTryTimes(10);
//		System.out.println("下载好了，文件保存在：" + download.download("https://www.ddxsss.com/book/9202/", config) + "这里，赶紧去看看吧！");
		// 秦吏
//		System.out.println("下载好了，文件保存在：" + download.download("https://www.bqgll.cc/book/4475/", config) + "这里，赶紧去看看吧！");
		// 间客
		System.out.println("下载好了，文件保存在：" + download.download("https://www.bqgll.cc/book/4154/", config) + "这里，赶紧去看看吧！");
		// 井口战役
//		System.out.println("下载好了，文件保存在：" + download.download("https://www.bqgll.cc/book/9516/", config) + "这里，赶紧去看看吧！");
	}

	@Test
	public void testMultiFileMerge() {
		String url = "bqgll.cc";
		String novelPath = "/Users/luo/Documents/other/novels" + "/" + url;
		NovelSpiderUtil.multiFileMerge(novelPath, novelPath + "/save/间客.txt", true);
	}

	/* 爬取笔趣阁的小说信息 */
	@Test
	public void testBiqugeGetNovels() {
		INovelSpider spider = NovelSpiderFactory.getNovelSpider("https://www.52wx.com/xiuzhenxiaoshuo/");
		List<Novel> novels = spider.getsNovel("https://www.52wx.com/xiuzhenxiaoshuo/");
		for (Novel novel : novels) {
			System.out.println(novel);
		}
	}
}
