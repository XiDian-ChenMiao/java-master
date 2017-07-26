package cn.xidian.other;

/**
 * 文件描述：MD5加密字符串
 * 创建作者：陈苗
 * 创建时间：2016/10/31 22:01
 */
public class MD5Test {

    public static String get12Char(String uuid) {
        String arr[] = ShortText(uuid);
        String rst = (arr[0] + arr[1]).toUpperCase();
        return rst.substring(0, 4) + rst.substring(4, 8) + rst.substring(8, 12);
    }

    private static String[] ShortText(String string) {
        String key = "GuangDa";
        String[] chars = new String[] {
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B",
                "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

        String hex = new MD5().getMD5ofStr(key + string);
        int hexLen = hex.length();
        int subHexLen = hexLen / 8;
        String[] ShortStr = new String[4];

        for (int i = 0; i < subHexLen; i++) {
            String outChars = "";
            int j = i + 1;
            String subHex = hex.substring(i * 8, j * 8);
            long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);

            for (int k = 0; k < 6; k++) {
                int index = (int) (Long.valueOf("0000003D", 16) & idx);
                outChars += chars[index];
                idx = idx >> 5;
            }
            ShortStr[i] = outChars;
        }
        return ShortStr;
    }

    public static void main(String[] args) {
        System.out.println(MD5Test.get12Char("陈苗陈苗陈苗陈苗陈苗陈苗"));
        System.out.println(MD5Test.get12Char("陈苗陈苗"));
    }
}
