package cn.fuego.dms.ui.group.tree;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class BaseSiteTree extends Tree

{

	public BaseSiteTree(Composite parent, int style)
	{
		super(parent, style);
		this.setBounds(10, 20, 200, 650);
		
		itemInit();
		
	}

	private void itemInit()
	{
		TreeItem iItem, jItem, kItem;  
		for (int i=0; i<3; i++) {  
            iItem = new TreeItem (this, 0);  
            iItem.setText ("基站 (0) -" + i);  
            for (int j=0; j<3; j++) {  
                jItem = new TreeItem (iItem, 0);  
                jItem.setText ("基站 (1) -" + j);  
                for (int k=0; k<3; k++) {  
                    kItem = new TreeItem (jItem, 0);  
                    kItem.setText ("基站 (2) -" + k);  
                }  
            }  
        }
	}

}
