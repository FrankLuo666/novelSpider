package novel.spider.entitys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 小说实体
 */

@Data
public class Novel implements Serializable {
    /** 书名 */
    private String name;
    /** 作者名 */
    private String author;
    /** 小说的链接 */
    private String url;
    /** 小说的类别：如武侠修真，都市言情 */
    private String type;
    /** 最后一章的章节名 */
    private String lastUpdateChapter;
    /** 最后一章的链接 */
    private String lastUpdateChapterUrl;
    /** 小说最后更新的时间 */
    private Date lastUpdateTime;
    /** 小说的状态：1 连载 2 完结 */
    private int status;
    /** 书名的首字母 */
    private char firstLetter;
    /** 小说平台的ID(枚举中相应的小说ID) */
    private int platformId;
    /** 这本小说存储到我们数据库的时间 */
    private Date addTime;

    @Override
    public String toString() {
        return "Novel [name=" + name + ", author=" + author + ", url=" + url + ", type=" + type + ", lastUpdateChapter="
                + lastUpdateChapter + ", lastUpdateChapterUrl=" + lastUpdateChapterUrl + ", lastUpdateTime="
                + lastUpdateTime + ", status=" + status + ", firstLetter=" + firstLetter + ", platformId=" + platformId
                + ", addTime=" + addTime + "]";
    }
}
