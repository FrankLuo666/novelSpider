package novel.spider.util;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import novel.spider.NovelSiteEnum;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public final class NovelSpiderUtil {
	private static final Map<NovelSiteEnum, Map<String, String>> CONTEXT_MAP = new HashMap<>();
	static {
		init();
	}
	private NovelSpiderUtil() {}

	@SuppressWarnings("unchecked")
	private static void init() {
		SAXReader reader = new SAXReader();
		try {
			// 使用ClassPathResource加载类路径下的资源
			Resource resource = new ClassPathResource("conf/Spider-Rule.xml");
			// 获取资源的输入流
			InputStream inputStream = resource.getInputStream();
			// 使用DOM4J解析XML
			Document doc = reader.read(inputStream);
			Element root = doc.getRootElement();
			List<Element> sites = root.elements("site");
			for (Element site : sites) {
				List<Element> subs = site.elements();
				Map<String, String> subMap = new HashMap<>();
				for (Element sub : subs) {
					String name = sub.getName();
					String text = sub.getTextTrim();
					subMap.put(name, text);
				}
				CONTEXT_MAP.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")), subMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 拿到对应网站的解析规则
	 */
	public static Map<String, String> getContext(NovelSiteEnum novelSiteEnum) {
		return CONTEXT_MAP.get(novelSiteEnum);
	}

	/**
	 * 多个文件合并为一个文件，合并规则：按文件名分割排序
	 * @param path 基础目录，该根目录下的所有文本文件都会被合并到 mergeToFile
	 * @param mergeToFile 被合并的文本文件，这个参数可以为null,合并后的文件保存在path/merge.txt
	 */
	public static void multiFileMerge(String path, String mergeToFile, boolean deleteThisFile) {
		mergeToFile = mergeToFile == null ? path + "/merge.txt" : mergeToFile;
		File[] files = new File(path).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		Arrays.sort(files, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				int o1Index = Integer.parseInt(o1.getName().split("\\-")[0]);
				int o2Index = Integer.parseInt(o2.getName().split("\\-")[0]);
				if (o1Index > o2Index) {
					return 1;
				} else if (o1Index == o2Index){
					return 0;
				} else {
					return -1;
				}
			}
		});
		PrintWriter out = null;
		try {
			// 合并写入到一个文件中，设置编码格式
			out = new PrintWriter(new File(mergeToFile), "UTF-8");
			for (File file : files) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				String line = null;
				while ((line = reader.readLine()) != null) {
					out.println(line);
				}
				reader.close();

				// 写入到合并文件后，删除这个已经被写入的文件
				if (deleteThisFile) {
					file.delete();
				}
			}
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		} finally {
			out.close();
		}
	}
}
