package util;

import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
 
import com.jidesoft.icons.IconsFactory;

public class ImageUploader {
	public static Icon getIcon(String iconFileName) {
        try {
            ImageIcon imageIcon = IconsFactory.findImageIcon(ImageUploader.class, "/resource/icon/" + iconFileName);
            return imageIcon;
         } catch (IOException e) {
            // return null if the icon is not found
            return null;
        }
    }

}
