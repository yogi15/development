package bo.html;



import java.io.File;
import java.sql.Connection;
import java.util.Vector;

import beans.Message;


public interface HtmlDocumentImporter {
	
	 /**
    *
    * @param  message   The message associated with the document to import
    * @param  file      The file to import as document
    * @param  errors    errors encountered during import
    * @param  dsCon     The Data Server connection
    * @return  the imported document to be stored in system
    */
   public HTMLDocument  importFile(Message message, File file, Vector errors, Connection dsCon);

   /**
    *
    * @param   document document to be expanded
    * @return  expanded document string
    */
   public String editHTML(HTMLDocument document);

}
