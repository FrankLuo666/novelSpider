package novel.spider;

/**
 * 已经被支持的小说网站枚举
 * @author LiuKeFeng
 * @date   2016年10月9日
 */
public enum NovelSiteEnum {
	DingDianXiaoShuo(1, "ddxsss.com"),
	BiQuGe1(2, "bqgll.cc"),
	BiQuGe2(3, "52wx.com");

	private int id;
	private String url;

	private NovelSiteEnum(int id, String url) {
		this.id = id;
		this.url = url;
	}

	public static NovelSiteEnum getEnumById(int id) {
		switch (id) {
			case 1 : return DingDianXiaoShuo;
			case 2 : return BiQuGe1;
			case 3 : return BiQuGe2;
			default :
				throw new RuntimeException("id=" + id + "是不被支持的小说网站");
		}
	}

	public static NovelSiteEnum getEnumByUrl(String url) {
		for (NovelSiteEnum novelSiteEnum : values()) {
			if (url.contains(novelSiteEnum.url)) {
				return novelSiteEnum;
			}
		}
		throw new RuntimeException("url=" + url + "是不被支持的小说网站");
	}
}
