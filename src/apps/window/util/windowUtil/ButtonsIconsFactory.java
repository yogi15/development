package apps.window.util.windowUtil;
import com.jidesoft.icons.IconsFactory;
import javax.swing.*;

public class ButtonsIconsFactory {
	public static class Buttons {
		public static final String COPY = "/resources/icon/hide12.png";
		public static final String CUT = "/resources/icon/hide12.png";
		public static final String PASTE = "/resources/icon/hide12.png";
		public static final String DELETE = "/resources/icon/delete1.png";
		public static final String REFRESH = "/resources/icon/execute2.png";
		public static final String HISTORY = "/resources/icon/hide12.png";
		public static final String REDO = "/resources/icon/hide12.png";
		public static final String UNDO = "/resources/icon/hide12.png";
		public static final String CLEAR = "/resources/icon/clearfilter.png";
		public static final String ADD = "/resources/icon/add.png";
		public static final String CLEARALL = "/resources/icon/ClearAllFilter.png";
		public static final String SMALLSAVE = "/resources/icon/saveSmall.png";
		public static final String EXPORTTOEXCEL = "/resources/icon/report_export_excel.png";
		public static final String SAVESHEET = "/resources/icon/saveSheet.png";
		public static final String SWITCHLAYOUT = "/resources/icon/switchLayout.png";
		public static final String LOADLAYOUT = "/resources/icon/layout_configuration.png";
		public static final String SAVELAYOUT = "/resources/icon/saveLayout.png";
		public static final String FILTER = "/resources/icon/report_filter.png";
		public static final String LOGCOSMOS = "/resources/icon/sql.jpg";
		public static final String SMALLOPEN = "/resources/icon/open16.gif";
		public static final String SAVEASNEW = "/resources/icon/save_as_tb.gif";
		public static final String MEDIUMSAVE = "/resources/icon/save16.gif";
		public static final String NEW = "/resources/icon/new_toolbar.gif";
		public static final String CASHFLOW = "/resources/icon/cashflow.gif";
		public static final String LOGS = "/resources/icon/logs_new_tb.gif";
		public static final String ROLLOVER = "/resources/icon/refresh.png";
		public static final String AUDIT = "/resources/icon/audit.gif";
		
	}

	public static ImageIcon getImageIcon(String name) {
		if (name != null)
			return IconsFactory.getImageIcon(ButtonsIconsFactory.class, name);
		else
			return null;
	}

	public static void main(String[] argv) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				IconsFactory.generateHTML(ButtonsIconsFactory.class);
			}
		});
	}

}