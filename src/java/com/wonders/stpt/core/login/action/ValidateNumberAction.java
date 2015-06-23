package com.wonders.stpt.core.login.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

@SuppressWarnings("serial")
@ParentPackage("cuteframework-default")
@Namespace(value="/")
public class ValidateNumberAction extends BaseAjaxAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Log logger = LogFactory.getLog(getClass());

	//给定范围获得随机颜色
	Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

	@Action(value="validateNumber")
	public String execute() {
		// 在内存中创建图象   
	    int width = 60, height = 20;
	    BufferedImage image = new BufferedImage(width, height,
	            BufferedImage.TYPE_INT_RGB);
	    // 获取图形上下文   
	    Graphics g = image.getGraphics();
	    //生成随机类   
	    Random random = new Random();
	    // 设定背景色   
	    g.setColor(getRandColor(200, 250));
	    g.fillRect(0, 0, width, height);
	    //设定字体   
	    g.setFont(new Font("Times New Roman", Font.PLAIN, 18));  
	    // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到   
	    g.setColor(getRandColor(160, 200));
	    for (int i = 0; i < 155; i++) {
	        int x = random.nextInt(width);
	        int y = random.nextInt(height);
	        int xl = random.nextInt(12);
	        int yl = random.nextInt(12);
	        g.drawLine(x, y, x + xl, y + yl);
	    }
	    // 取随机产生的认证码(4位数字)   
	    String sRand = "";
	    for (int i = 0; i < 4; i++) {
	        String rand = String.valueOf(random.nextInt(10));
	        sRand += rand;
	        // 将认证码显示到图象中   
	        g.setColor(new Color(20 + random.nextInt(110), 20 + random
	        .nextInt(110), 20 + random.nextInt(110)));
	        //调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成   
	        g.drawString(rand, 13 * i + 6, 16);
	    }
	    // 将认证码存入SESSION
	    super.getServletRequest().getSession().setAttribute("rand", sRand);
	    // 图象生效 
	    g.dispose();
	    // 输出图象到页面
	    OutputStream out;
		try {
			out = super.getServletResponse().getOutputStream();
		    ImageIO.write(image, "JPEG", out);
		    out.close();
		} catch (IOException e) {
			logger.warn("创建图片失败", e);
		}
		return NONE;
	}

}
