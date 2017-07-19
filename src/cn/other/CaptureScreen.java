package cn.other;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
 
/**
 * 屏幕截图类
 */
import javax.imageio.ImageIO;
/**
 * 截屏代码
 * @ClassName: CaptureScreen 
 * @author 陈苗 
 * @date 2016年4月10日 下午2:15:22
 */
public class CaptureScreen {
 
    public static void captureScreen(String fileName, String folder) throws Exception {
 
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        //保存路径
        File screenFile = new File(fileName);
        if (!screenFile.exists()) {
            screenFile.mkdir();
        }
        File f = new File(screenFile, folder);
        ImageIO.write(image, "png", f);
        //自动打开
        if (Desktop.isDesktopSupported()&& Desktop.getDesktop().isSupported(Desktop.Action.OPEN))
        	Desktop.getDesktop().open(f);
    }
 
    public static void main(String[] args) {
        try {
            captureScreen("D:\\","截图.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
