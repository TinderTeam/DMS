package cn.fuego.dms.ui.frame;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

import cn.fuego.dms.ui.constant.UIConstant;

/**
 *
 * @author root
 */
public class LoadWindow extends JWindow {

	public static int WIDTH=455;
	public static int HEIGHT=295;

	public JLabel imgLabel; 
	public JLabel dscrLabel; 
	public JProgressBar progressBar;
	Container contentPanel = this.getContentPane();
	
	public static int stepAll = 8;
	public static int currentStep=0;
	
 
	public LoadWindow()
	{
		
		imgLabel = new JLabel(new ImageIcon(LoadWindow.class.getResource("/resource/icon.png")));
		imgLabel.setBounds(0, 0 , WIDTH, HEIGHT-15);
		
		this.add(imgLabel);
		
		
		progressBar= new JProgressBar();
		progressBar.setStringPainted(false);
		progressBar.setBorderPainted(true);
		progressBar.setForeground(new Color(0,210,40));
		progressBar.setBackground(new Color(188,190,194));
		progressBar.setBounds(0, HEIGHT-15, WIDTH,15);
		
		
		contentPanel.setLayout(new BorderLayout());

		contentPanel.add(progressBar,BorderLayout.SOUTH);
	
		this.setSize(WIDTH,HEIGHT);
		this.setLocation((int)(UIConstant.SCR_WIDTH-WIDTH)/2, (int)(UIConstant.SCR_HEIGHT-HEIGHT)/2);
		this.setVisible(true);
	}
	

	public void nextStep()
	{
		if(currentStep==stepAll)
		{
			close();
		}
		else
		{
			currentStep++;
			System.out.println(currentStep);
			progressBar.setValue(currentStep*(100/stepAll));
		}
	
	}


	private void close()
	{
		this.setVisible(false);
		this.dispose();
	}
}
