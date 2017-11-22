package cn.dlj.utils;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 通过汉字获取拼音
 */
public class ChineseToSpell {

	/** 返回中文的首字母(只保留英文和数字) **/
	public static String getPinYinHeadChar(String str) {
		if (str != null) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < str.length(); j++) {
				char word = str.charAt(j);
				if ((word >= 'a' && word <= 'z') || word >= '0' && word <= '9' || word >= 'A' && word <= 'Z') {
					sb.append(word);
				} else {
					String[] rr = PinyinHelper.toHanyuPinyinStringArray(word);
					if (null != rr && rr.length != 0) {
						sb.append(rr[0].charAt(0));
					}
				}
			}
			return sb.toString().toLowerCase();
		}
		return null;
	}

}