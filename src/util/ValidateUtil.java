package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ValidateUtil {
	
	private static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      
	      
	      
	public static boolean EmailValidator(String emailId) {
		
		return (emailId.matches(EMAIL_REGEX));
		
	}
	
	public static boolean validatePhoneNumber(String stringPhoneNumber) {

	   //validate phone numbers of format "1234567890"
        if (stringPhoneNumber.matches("\\d{10}")) return true;
        //validating phone number with -, . or spaces
        else if(stringPhoneNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
        //validating phone number with extension length from 3 to 5
        else if(stringPhoneNumber.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
        //validating phone number where area code is in braces ()
        else if(stringPhoneNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
        //return false if nothing matches the input
        else return false;
		
        
        /*
         * 	Phone number 1234567890 validation result: true
			Phone number 123-456-7890 validation result: true
			Phone number 123-456-7890 x1234 validation result: true
			Phone number 123-456-7890 ext1234 validation result: true
			Phone number (123)-456-7890 validation result: true
			Phone number 123.456.7890 validation result: true
			Phone number 123 456 7890 validation result: true
         */
	}
	
	
}
