package com.alienlab.niit.qm.common;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*******************************************************************************  
 * 缩略图类（�?�用�? 本java类能将jpg、bmp、png、gif图片文件，进行等比或非等比的大小转换�? 具体使用方法  
 * compressPic(大图片路�?,生成小图片路�?,大图片文件名,生成小图片文�?,生成小图片宽�?,生成小图片高�?,是否等比缩放(默认为true))  
 */  
public class CompressPic {    
	private static Logger logger= Logger.getLogger(CompressPic.class);
    private File file = null; // 文件对象    
    private String inputDir; // 输入图路�?   
    private String outputDir; // 输出图路�?   
    private String inputFileName; // 输入图文件名   
    private String outputFileName; // 输出图文件名   
    private int outputWidth = 100; // 默认输出图片�?   
    private int outputHeight = 100; // 默认输出图片�?   
    private boolean proportion = true; // 是否等比缩放标记(默认为等比缩�?)   
    private String imgPath="";
    public CompressPic() { // 初始化变�?   
         inputDir = "";    
         outputDir = "";    
         inputFileName = "";    
         outputFileName = "";    
         outputWidth = 100;    
         outputHeight = 100;    
     }    
     public void setInputDir(String inputDir) {    
         this.inputDir = inputDir;    
    }    
    public void setOutputDir(String outputDir) {    
        this.outputDir = outputDir;    
     }    
     public void setInputFileName(String inputFileName) {    
         this.inputFileName = inputFileName;   
     }    
     public void setOutputFileName(String outputFileName) {    
        this.outputFileName = outputFileName;    
     }    
    public void setOutputWidth(int outputWidth) {   
        this.outputWidth = outputWidth;    
    }    
     public void setOutputHeight(int outputHeight) {    
         this.outputHeight = outputHeight;    
     }    
     public void setWidthAndHeight(int width, int height) {    
       this.outputWidth = width;   
       this.outputHeight = height;    
   }    
       
     /*   
      * 获得图片大小   
      * 传入参数 String path ：图片路�?   
    */    
     public long getPicSize(String path) {    
        file = new File(path);    
         return file.length();    
     }   
   
     // 图片处理    
     public String compressPic() {    
         try {    
             //获得源文�?    
            file = new File(inputDir + inputFileName);    
             if (!file.exists()) {    
                 return "";    
             }    
            Image img = ImageIO.read(file);    
            // 判断图片格式是否正确    
             if (img.getWidth(null) == -1) {   
                 System.out.println(" can't read,retry!" + "<BR>");    
                return "no";    
            } else {    
                 int newWidth; int newHeight;    
                 // 判断是否是等比缩�?    
                 if (this.proportion == true) {    
                     // 为等比缩放计算输出的图片宽度及高�?    
                     double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.00001;    
                     double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.00001;    
                     // 根据缩放比率大的进行缩放控制    
                    double rate = rate1 > rate2 ? rate2 : rate1;    
                     newWidth = (int) (((double) img.getWidth(null)) / rate);    
                     newHeight = (int) (((double) img.getHeight(null)) / rate);    
                 } else {    
                     newWidth = outputWidth; // 输出的图片宽�?    
                     newHeight = outputHeight; // 输出的图片高�?    
                }    
               BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);    
                  
               /*  
                 * Image.SCALE_SMOOTH 的缩略算�? 生成缩略图片的平滑度�?  
                 * 优先级比速度�? 生成的图片质量比较好 但�?�度�?  
                 */    
                tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);   
                FileOutputStream out = new FileOutputStream(outputDir + outputFileName);   
               // JPEGImageEncoder可�?�用于其他图片类型的转换    
               JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);    
                encoder.encode(tag);    
               out.close();    
            }    
        } catch (IOException ex) {    
             ex.printStackTrace();    
         }    
        return "ok";    
   }    
   public String compressPic (String inputDir, String outputDir, String inputFileName, String outputFileName) {    
       // 输入图路�?    
       this.inputDir = inputDir;    
       // 输出图路�?    
        this.outputDir = outputDir;    
        // 输入图文件名    
        this.inputFileName = inputFileName;    
        // 输出图文件名   
        this.outputFileName = outputFileName;    
        return compressPic();    
    }    
    public String compressPic(String inputDir, String outputDir, String inputFileName, String outputFileName, int width, int height, boolean gp) {    
        // 输入图路�?    
        this.inputDir = inputDir;    
        // 输出图路�?    
        this.outputDir = outputDir;    
        // 输入图文件名    
        this.inputFileName = inputFileName;    
        // 输出图文件名    
        this.outputFileName = outputFileName;    
        // 设置图片长宽   
        setWidthAndHeight(width, height);    
        // 是否是等比缩�? 标记    
        this.proportion = gp;    
        return compressPic();    
    }    
    
  
       
   // main测试    
    // compressPic(大图片路�?,生成小图片路�?,大图片文件名,生成小图片文�?,生成小图片宽�?,生成小图片高�?,是否等比缩放(默认为true))   
    public static void main(String[] arg) {    
    	CompressPic mypic = new CompressPic();    
        logger.info("输入的图片大小：" + mypic.getPicSize("E:\\uploads\\document\\image\\20150111\\83731420981233160.jpg")/1024 + "KB");
     	
         mypic.compressPic("E:\\uploads\\document\\image\\20150111\\","e:\\test\\","83731420981233160.jpg","8373142098123316000.jpg", 120, 120, true);  
    }    
 }  

