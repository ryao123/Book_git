package com.book.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/codeimg")
public class CodeImgServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		resp.setHeader("Content-Type", "image/jpeg");

		int w = 80;
		int h = 30;

		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	
		Graphics g = img.getGraphics();
	
		g.setColor(Color.white);
		g.fillRect(0, 0, w, h);
	
		g.setFont(new Font("aa", Font.BOLD, 18));

		Random r = new Random();

		String yzm = "";
		for (int i = 0; i < 4; i++) {
			int a = r.nextInt(10);
			int y = 15 + r.nextInt(10);
			
			Color c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
			g.setColor(c);
			g.drawString("" + a, i * 15, y);

			yzm += a;
		}
		
		req.getSession().setAttribute("yzm", yzm);
		
		for (int i = 0; i < 10; i++) {
			
			Color c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
			g.setColor(c);
			g.drawLine(r.nextInt(60), r.nextInt(30), r.nextInt(60), r.nextInt(30));
		}
		g.dispose();
	
		ImageIO.write(img, "JPEG", resp.getOutputStream());
	}
	
}
