package cn.fuego.dms.ui.control.menu;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import cn.fuego.dms.ui.UIContext;
public class MenuAction 
{

	public static  Action exitAction = new Action("退出系统"){
		@Override
		public void run() {
			UIContext.getInstance().getMainFrame().close();
			
			System.exit(0);
		}
	};
	public static Action aboutAction= new Action("关于我们"){
		@Override
		public void run() {
			MessageDialog.openInformation(new Shell(),"关于我们","深圳市孚思科技有限公司（以下简称孚思科技）注册资本50万元，公司致力于协助企业信息化、智能化快速转型，通过提供全面、稳定、定制化的信息技术服务及产品，挖掘客户核心竞争力，实现信息技术产业核心价值。多次承接大型国有企业资产管理系统项目，有丰富的开发运维经验。官网：www.fuego.cn;售前咨询：market@fuego.cn");
			
		}
	};
	
}
