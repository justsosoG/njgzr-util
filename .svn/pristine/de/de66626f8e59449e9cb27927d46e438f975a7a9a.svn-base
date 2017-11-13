package com.njgzr.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 *@author Justsoso丶G
 *@description （图片压缩工具）
 *@version 2017年11月8日下午2:50:28
 */
@SuppressWarnings("restriction")
public class ImgUtil {
	
	/**
     * 几种常见的图片格式
     */
    public static String IMAGE_TYPE_GIF = "gif";// 图形交换格式
    public static String IMAGE_TYPE_JPG = "jpg";// 联合照片专家组
    public static String IMAGE_TYPE_JPEG = "jpeg";// 联合照片专家组
    public static String IMAGE_TYPE_BMP = "bmp";// 英文Bitmap（位图）的简写，它是Windows操作系统中的标准图像文件格式
    public static String IMAGE_TYPE_PNG = "png";// 可移植网络图形
    public static String IMAGE_TYPE_PSD = "psd";// Photoshop的专用格式Photoshop
    
    
    
	
	/**
	 * 图片无损压缩
	 * @param oldPath（原图片地址）
	 * @param newPath（新图片地址）
	 */
	public static void compress(String oldPath,String newPath){
		try {
			Thumbnails.of(oldPath).scale(1f).outputQuality(0.5f) 
			.toFile(newPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
     * 缩放图像（按比例缩放）
     * @param srcImageFile 源图像文件地址
     * @param result 缩放后的图像地址
     * @param scale 缩放比例
     * @param flag 缩放选择:true 放大; false 缩小;
     */
    public final static void scale(String srcImageFile, String result,
            int scale, boolean flag) {
        try {
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
            int width = src.getWidth(); // 得到源图宽

            int height = src.getHeight(); // 得到源图长

            if (flag) {// 放大
                width = width * scale;
                height = height * scale;
            } else {// 缩小
                width = width / scale;
                height = height / scale;
            }
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图

            g.dispose();
            ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * 图像切割(按指定起点坐标和宽高切割)
     * @param srcImageFile 源图像地址
     * @param result 切片后的图像地址
     * @param x 目标切片起点坐标X
     * @param y 目标切片起点坐标Y
     * @param width 目标切片宽度
     * @param height 目标切片高度
     */
    public final static void cut(String srcImageFile, String result,
            int x, int y, int width, int height) {
        try {
            // 读取源图像

            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getHeight(); // 源图宽度
            int srcHeight = bi.getWidth(); // 源图高度
            if (srcWidth > 0 && srcHeight > 0) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight,
                        Image.SCALE_DEFAULT);
                // 四个参数分别为图像起点坐标和宽高
                // 即: CropImageFilter(int x,int y,int width,int height)
                ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
                Image img = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(image.getSource(),
                                cropFilter));
                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图

                g.dispose();
                // 输出为文件

                ImageIO.write(tag, "JPEG", new File(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * 图像类型转换：GIF->JPG、GIF->PNG、PNG->JPG、PNG->GIF(X)、BMP->PNG
     * @param srcImageFile 源图像地址
     * @param formatName 包含格式非正式名称的 String：如JPG、JPEG、GIF等

     * @param destImageFile 目标图像地址
     */
    public final static void convert(String srcImageFile, String formatName, String destImageFile) {
        try {
            File f = new File(srcImageFile);
            f.canRead();
            f.canWrite();
            BufferedImage src = ImageIO.read(f);
            ImageIO.write(src, formatName, new File(destImageFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * 彩色转为黑白 
     * @param srcImageFile 源图像地址
     * @param destImageFile 目标图像地址
     */
    public final static void gray(String srcImageFile, String destImageFile) {
        try {
            BufferedImage src = ImageIO.read(new File(srcImageFile));
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            src = op.filter(src, null);
            ImageIO.write(src, "JPEG", new File(destImageFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    /**
     * 给图片添加文字水印

     * @param pressText 水印文字
     * @param srcImageFile 源图像地址
     * @param destImageFile 目标图像地址
     * @param fontName 水印的字体名称

     * @param fontStyle 水印的字体样式

     * @param color 水印的字体颜色

     * @param fontSize 水印的字体大小

     * @param x 修正值

     * @param y 修正值

     * @param alpha 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字

     */
    public final static void pressText(String pressText,
            String srcImageFile, String destImageFile, String fontName,
            int fontStyle, Color color, int fontSize,int x,
            int y, float alpha) {
        try {
            File img = new File(srcImageFile);
            Image src = ImageIO.read(img);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, width, height, null);
            g.setColor(color);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            // 在指定坐标绘制水印文字

            g.drawString(pressText, (width - (getLength(pressText) * fontSize))
                    / 2 + x, (height - fontSize) / 2 + y);
            g.dispose();
            ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));// 输出到文件流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 创建图片缩略图(等比缩放 无失真缩放)
     * @param src 源图片文件完整路径

     * @param dist 目标图片文件完整路径
     * @param width 缩放的宽度

     * @param height 缩放的高度

     * @param flag  true 按照实际长宽输出  如果 false 按照比例进行无失真压缩


     */
    public static boolean createThumbnail(String src, String dist, float width, float height,boolean flag) {
        boolean flag1 = false ;
        try {
            File srcfile = new File(src);
            if (!srcfile.exists()) {
                System.out.println("文件不存在");
                return flag1;
            }
            BufferedImage image = ImageIO.read(srcfile);

            // 获得缩放的比例

            double ratio = 1.0;
            // 判断如果高、宽都不大于设定值，则不处理
            if (image.getHeight() > height || image.getWidth() > width) {
                if (image.getHeight() > image.getWidth()) {
                    ratio = height / image.getHeight();
                } else {
                    ratio = width / image.getWidth();
                }
            }
            int newWidth = flag ? (int) width : (int) (image.getWidth() * ratio);
               int newHeight = flag ? (int)height : (int) (image.getHeight() * ratio);
            BufferedImage bfImage = new BufferedImage(newWidth, newHeight,
                    BufferedImage.TYPE_INT_RGB);
            flag1 = bfImage.getGraphics().drawImage(
                    image.getScaledInstance(newWidth, newHeight,
                            Image.SCALE_SMOOTH), 0, 0, null);

            FileOutputStream os = new FileOutputStream(dist);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
            JPEGEncodeParam jParam = encoder.getDefaultJPEGEncodeParam(bfImage) ;
            jParam.setQuality(1f, false) ;
            encoder.encode(bfImage);
            os.close();
            flag1 = true ;
        } catch (Exception e) {
            flag1 = false ;
        }
        return flag1 ;
    }

    
    
    
    
    
    
    
    
    
    
    /**
     * 计算text的长度（一个中文算两个字符）

     * @param text
     * @return
     */
    public final static int getLength(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            if (new String(text.charAt(i) + "").getBytes().length > 1) {
                length += 2;
            } else {
                length += 1;
            }
        }
        return length / 2;
    }
    
    /**
     * <获取图片宽度>
     * add by jiang_yanyan 2015-01-04
     * @param file  图片文件
     * @return 宽度
     */
    public static int getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getWidth(null); // 得到源图宽
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
      
    /**
     * <获取图片高度>
     * add by jiang_yanyan 2015-01-04
     * @param file  图片文件
     * @return 高度
     */
    public static int getImgHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    
    
    
//    /**
//     * 制作圆角
//     * 
//     * @param srcFile
//     *            原文件
//     * @param destFile
//     *            目标文件
//     * @param cornerRadius
//     *            角度
//     */
//    public static void makeRoundedCorner(File srcFile, File destFile, int cornerRadius) throws Exception {
//        InputStream in = null;
//        OutputStream out = null;
//
//        try {
//            in = new BufferedInputStream(new FileInputStream(srcFile));
//            FolderUtils.mkdirs(destFile.getParentFile().getAbsolutePath());
//            out = new BufferedOutputStream(new FileOutputStream(destFile));
//            makeRoundedCorner(in, out, cornerRadius);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new Exception(e);
//        } finally {
//            IOUtils.closeQuietly(out);
//            IOUtils.closeQuietly(in);
//        }
//
//    }

    /**
     * 制作圆角
     * 
     * @param srcFile
     *            原文件
     * @param destFile
     *            目标文件
     * @param cornerRadius
     *            角度
     */
//    public static void makeRoundedCorner(String srcFile, String destFile, int cornerRadius) throws Exception {
//        makeRoundedCorner(new File(srcFile), new File(destFile), cornerRadius);
//    }

    /**
     * 制作圆角
     * 
     * @param inputStream
     *            原图输入流
     * @param outputStream
     *            目标输出流
     * @param radius
     *            角度
     */
    public static void makeRoundedCorner(final InputStream inputStream,
            final OutputStream outputStream, final int radius) throws Exception {
        BufferedImage sourceImage = null;
        BufferedImage targetImage = null;
        try {
            sourceImage = ImageIO.read(inputStream);
            int w = sourceImage.getWidth();
            int h = sourceImage.getHeight();
            System.out.println(w);

            int cornerRadius = radius < 1 ? w / 4 : radius;

            targetImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2 = targetImage.createGraphics();

            // This is what we want, but it only does hard-clipping, i.e.
            // aliasing
            // g2.setClip(new RoundRectangle2D ...)

            // so instead fake soft-clipping by first drawing the desired clip
            // shape
            // in fully opaque white with antialiasing enabled...
            g2.setComposite(AlphaComposite.Src);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

            // ... then compositing the image on top,
            // using the white shape from above as alpha source
            g2.setComposite(AlphaComposite.SrcAtop);
            g2.drawImage(sourceImage, 0, 0, null);
            g2.dispose();
            ImageIO.write(targetImage, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
    
	
	
	
	public static void main(String[] args) {
		compress("d:/58.jpg", "d:/love.jpg");
//		gray("d:/111576.jpg", "d:/22222.jpg");
//		createThumbnail("d:/111576.jpg", "d:/33333.jpg", 100, 100, false);
//		pressText("测试", "d:/111576.jpg", "d:/44444.jpg", "隶书", 24, Color.RED, 48, 0, 100, 0.5f);
	}
	
}
