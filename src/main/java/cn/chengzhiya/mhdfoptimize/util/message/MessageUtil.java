package cn.chengzhiya.mhdfoptimize.util.message;

public final class MessageUtil {
    /**
     * 拼接数组文本
     *
     * @param strings 文本数组
     * @param origin  数组起点
     * @param append  拼接内容
     * @return 拼接后的文本
     */
    public static String mergeString(String[] strings, int origin, String append) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = origin; i < strings.length; i++) {
            stringBuilder.append(strings[i]);
            if (i < strings.length - 1) {
                stringBuilder.append(append);
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 拼接数组文本
     *
     * @param strings 文本数组
     * @param append  拼接内容
     * @return 拼接后的文本
     */
    public static String mergeString(String[] strings, String append) {
        return mergeString(strings, 0, append);
    }

    /**
     * 拼接数组文本
     *
     * @param strings 文本数组
     * @param origin  数组起点
     * @return 拼接后的文本
     */
    public static String mergeString(String[] strings, int origin) {
        return mergeString(strings, origin, " ");
    }

    /**
     * 拼接数组文本
     *
     * @param strings 文本数组
     * @return 拼接后的文本
     */
    public static String mergeString(String[] strings) {
        return mergeString(strings, 0, " ");
    }
}
