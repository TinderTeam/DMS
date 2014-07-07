package cn.fuego.dms.ui.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Dialog.ModalityType;
import java.awt.Toolkit;
import java.awt.Window.Type;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import cn.fuego.dms.ui.constant.UIConstant;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WarningDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public WarningDialog(String msg)
	{
		setTitle("警告");
		this.setSize(450,300);
		this.setLocation((int)( UIConstant.SCR_WIDTH -450)/2, (int)(UIConstant.SCR_HEIGHT-300)/2);
		setIconImage(Toolkit.getDefaultToolkit().getImage(WarningDialog.class.getResource("/resource/icon.png")));
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel(msg);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("关闭");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		
	}
	public void close(){
		this.dispose();
	}

}
