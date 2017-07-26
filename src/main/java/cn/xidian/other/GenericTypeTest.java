package cn.xidian.other;

import java.util.Hashtable;
import java.util.Map;

/**
 * 喜好泛型类
 */
class Favorites {
    private Map<Class<?>,Object> favorites = new Hashtable<Class<?>, Object>();
    public <T> void putFavorite(Class<T> type,T instance) {
        if (type == null)
            throw new NullPointerException();
        favorites.put(type,type.cast(instance));
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }
}
/**
 * 文件描述：泛型测试类
 * 创建作者：陈苗
 * 创建时间：2016年5月24日 16:54
 */
public class GenericTypeTest {
    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorite(String.class,"Java");
        f.putFavorite(Integer.class,0xcafebabe);
        f.putFavorite(Class.class,Favorites.class);

        System.out.printf("%s %x %s%n",f.getFavorite(String.class),f.getFavorite(Integer.class),f.getFavorite(Class.class).getName());
    }
}
