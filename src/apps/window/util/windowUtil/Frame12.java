package apps.window.util.windowUtil;
import java.awt.*;

public class Frame12 {
	 static Frame _frame = null;
	
	static public Frame getFrame() {
        if (_frame == null) {
            _frame = new Frame();
            _frame.setSize(150, 200);
        }
        return _frame;
    }

}