package cn.xidian.thread;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.concurrent.Executor;

class Pages {
    private URL[] urls;
    private String[] fileNames;
    private Executor executor;

    public Pages(URL[] urls, String[] fileNames, Executor executor) {
        this.urls = urls;
        this.fileNames = fileNames;
        this.executor = executor;
    }

    /**
     * 下载网页内容的方法
     */
    public void download() {
        for (int i = 0; i < urls.length; i++) {
            final URL url = urls[i];
            final String fileName = fileNames[i];
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        dump(url.openStream(),new FileOutputStream(fileName));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    /**
     * 获取网页内容
     * @param src 网页内容输入流
     * @param dest 指定输出文件的输出流
     * @throws IOException
     */
    private void dump(InputStream src, OutputStream dest) throws IOException {
        byte[] data = new byte[1024];
        int length;
        while ((length = src.read(data)) != -1) {
            dest.write(data,0,length);
        }
    }
}

/**
 * 下载执行器
 */
class DownloadExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
/**
 * 文件描述：线程并发API的Executor类测试
 * 创建作者：陈苗
 * 创建时间：2016年7月3日 15:23
 */
public class ThreadExecutorTest {
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) throws Exception {
        URL[] urls = {
            new URL("http://openhome.cc/Gossip/Encoding/"),
            new URL("http://openhome.cc/Gossip/Scala/"),
            new URL("http://openhome.cc/Gossip/JavaScript/"),
            new URL("http://openhome.cc/Gossip/Python/")
        };
        String[] fileNames = {
            "Encoding.html",
            "Scala.html",
            "JavaScript.html",
            "Python.html",
        };
        new Pages(urls,fileNames,new DownloadExecutor()).download();
    }
}
