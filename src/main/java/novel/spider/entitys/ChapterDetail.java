package novel.spider.entitys;

import java.io.Serializable;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * 章节详细内容实体
 * @author LiuKeFeng
 * @date   2016年10月10日
 */
@Data
public class ChapterDetail implements Serializable {
    private static final long serialVersionUID = -7303060644500661569L;
    private String title;
    private String content;
    private String prev;
    private String next;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((next == null) ? 0 : next.hashCode());
        result = prime * result + ((prev == null) ? 0 : prev.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChapterDetail other = (ChapterDetail) obj;
        if (next == null) {
            if (other.next != null)
                return false;
        } else if (!next.equals(other.next))
            return false;
        if (prev == null) {
            if (other.prev != null)
                return false;
        } else if (!prev.equals(other.prev))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ChapterDetail [title=" + title + ", content=" + StringUtils.abbreviate(content, 30) + ", prev=" + prev + ", next=" + next + "]";
    }
}
