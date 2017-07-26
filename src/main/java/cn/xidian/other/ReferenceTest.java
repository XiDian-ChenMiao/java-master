package cn.xidian.other;

import org.junit.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class SoftReferenceTest {
    public static ReferenceQueue<CustomObject> softQueue = new ReferenceQueue<CustomObject>();
    public static class CustomObject {
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("CustomObject的finalize方法被调用。");
        }

        @Override
        public String toString() {
            return "CustomObject";
        }
    }
    public static class CheckRefQueue implements Runnable {
        Reference<CustomObject> obj = null;
        @SuppressWarnings("unchecked")
		@Override
        public void run() {
            try {
                obj = (Reference<CustomObject>) softQueue.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (obj != null)
                    System.out.println("对象的软引用是：" + obj.get());
            }
        }
    }
}
class WeakReferenceTest {
    public static ReferenceQueue<MyObject> weakQueue = new ReferenceQueue<MyObject>();
    public static class MyObject {
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("CustomObject的finalize方法被调用。");
        }

        @Override
        public String toString() {
            return "CustomObject";
        }
    }
    public static class CheckWeakRefQueue implements Runnable {
        Reference<MyObject> obj = null;
        @SuppressWarnings("unchecked")
		@Override
        public void run() {
            try {
                obj = (Reference<MyObject>) weakQueue.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(obj != null) {
                System.out.println("删除的弱引用：" + obj + "，获取弱引用的对象：" + obj.get());
            }
        }
    }
}

/**
 * 虚引用测试类
 */
class PlantomReferenceTest {
    public static ReferenceQueue<PObject> plantomQueue = new ReferenceQueue<PObject>();
    public static class PObject {
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("CustomObject的finalize方法被调用。");
        }

        @Override
        public String toString() {
            return "CustomObject";
        }
    }
    public static class CheckPRefQueue implements Runnable {
        Reference<PObject> obj = null;
        @SuppressWarnings("unchecked")
		@Override
        public void run() {
            try {
                obj = (Reference<PObject>) plantomQueue.remove();
                System.out.println("删除的弱引用：" + obj + "，获取弱引用的对象：" + obj.get());
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/**
 * Bitmap图像的管理器
 */
class BitmapManager {
    private class Bitmap {

    }
    private static class BitmapFactory {
        public static Bitmap decodeFile(String path) {
            return null;
        }
    }
    private Map<String,SoftReference<Bitmap>> imageCache = new Hashtable<String, SoftReference<Bitmap>>();

    public void saveBitmapToCache(String path) {

        Bitmap bitmap = BitmapFactory.decodeFile(path);//强引用的Bitmap对象
        SoftReference<Bitmap> bitmapSoftReference = new SoftReference<Bitmap>(bitmap);//软引用的Bitmap对象
        imageCache.put(path,bitmapSoftReference);//将软引用对象添加到Map中使其缓存
        bitmap = null;//使用完后手动将位图对象置为null
    }

    public Bitmap getBitmapByPath(String path) {
        // 从缓存中取软引用的Bitmap对象
        SoftReference<Bitmap> softBitmap = imageCache.get(path);
        // 判断是否存在软引用
        if (softBitmap == null) {
            return null;
        }
        // 取出Bitmap对象，如果由于内存不足Bitmap被回收，将取得空
        Bitmap bitmap = softBitmap.get();
        return bitmap;
    }
}
/**
 * 文件描述：Java引用测试
 * 创建作者：陈苗
 * 创建时间：2016年5月24日 21:27
 */
public class ReferenceTest {
    /**
     * 测试软引用
     */
    @Test
    public void testSoftReference() {
        SoftReferenceTest.CustomObject object = new SoftReferenceTest.CustomObject();
        SoftReference<SoftReferenceTest.CustomObject> softReference = new SoftReference<SoftReferenceTest.CustomObject>(object,SoftReferenceTest.softQueue);
        new Thread(new SoftReferenceTest.CheckRefQueue()).start();

        object = null;//删除强引用
        System.gc();

        System.out.println("GC之后：softReference的get方法值为：" + softReference.get());
        System.out.println("分配大块内存：");
        @SuppressWarnings("unused")
		byte[] b = new byte[5 * 1024 * 928];
        System.out.println("再分配完内存后，softReference的get方法值为：" + softReference.get());
        System.gc();
    }

    /**
     * 测试弱引用
     */
    @Test
    public void testWeakReference() {
        WeakReferenceTest.MyObject object = new WeakReferenceTest.MyObject();
        Reference<WeakReferenceTest.MyObject> weakReference = new WeakReference<WeakReferenceTest.MyObject>(object,WeakReferenceTest.weakQueue);
        new Thread(new SoftReferenceTest.CheckRefQueue()).start();

        object = null;//删除强引用
        System.out.println("在GC之前，弱引用weakReference的get方法值为：" + weakReference.get());
        System.gc();
        System.out.println("在GC之后，弱引用weakReference的get方法值为：" + weakReference.get());
    }
    @Test
    public void testPlantomReference() throws InterruptedException {
        PlantomReferenceTest.PObject object = new PlantomReferenceTest.PObject();
        Reference<PlantomReferenceTest.PObject> planRef = new WeakReference<PlantomReferenceTest.PObject>(object,PlantomReferenceTest.plantomQueue);
        System.out.println("创建的虚引用为：" + planRef);
        new Thread(new PlantomReferenceTest.CheckPRefQueue()).start();

        object = null;
        TimeUnit.SECONDS.sleep(1);
        int i = 1;
        while (true) {
            System.out.println("第" + i++ + "次GC");
            System.gc();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
