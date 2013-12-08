package com.limelight.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.limelight.input.KeyboardHandler;
import com.limelight.input.MouseHandler;
import com.limelight.nvstream.NvConnection;

public class StreamFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private KeyboardHandler keyboard;
	private MouseHandler mouse;
	
	public void build(NvConnection conn) {
		keyboard = new KeyboardHandler(conn);
		mouse = new MouseHandler(conn, this);
		
		this.addKeyListener(keyboard);
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(1280,720);
		//This might break if the screen res is too small...not sure though
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		this.getContentPane().setCursor(blankCursor);
		this.setVisible(true);
	}
}
