package util.common;

import java.util.Hashtable;
import java.util.Locale;

public class LocalUtil {
	static Hashtable<String, Locale> _basicLocalesHash = null;
	static Hashtable<String, Locale> _availableLocalesHash = null;
	static Hashtable<String, Locale> _allAvailableLocalesHash = null;
	static public Locale CONST_LOCALE=Locale.US;
	
	
	 /**
	   * Returns a Locale givent its name.
	   * <p>
	   * The name can be one of the standard locale names or the display name
	   * of a locale that has been previously set, using
	   * {@link #setDefaultLocale(String) setDefaultLocale}. The standard
	   * locale names are:
	   * "USA", "FRANCE", "UK", "GERMANY", "CANADA(F)",
	   * "CANADA(E)", "ITALY", "CHINA", "JAPAN",
	   * "KOREA", "SIMPL_CHINESE", "TAIWAN", "TRAD_CHINESE".
	   *
	   * @param locale a Locale name (String)
	   */
	  static public Locale getLocale(String locale) {
		  if (_basicLocalesHash == null)
			  makeBasicLocalesHash();
		  Object localeObj = _basicLocalesHash.get(locale);
		  if (localeObj != null)
			  return (Locale)localeObj;
		  else {
			  Hashtable<String, Locale> locs = getAvailableLocalesHash();
			  locale = locale.toLowerCase();
			  localeObj = locs.get(locale);
			  if (localeObj != null)
				  return (Locale)localeObj;
		  }
		  return null;
	  }
		static public final Locale LOC_POLAND= new Locale("pl","pl","");
	  /**
	   * Returns a Locale array containing all standard Locale objects.
	   */
	  static Locale[] getStdLocale() {
		  Locale locs[]= new Locale[21];
		  int i=0;
		  locs[i++]=Locale.CANADA;
		  locs[i++]=Locale.CANADA_FRENCH;
		  locs[i++]=Locale.CHINA;
		  //locs[i++]=Locale.CHINESE;
		  locs[i++]=Locale.ENGLISH;
		  locs[i++]=Locale.FRANCE;
		  locs[i++]=Locale.FRENCH;
		  locs[i++]=Locale.GERMAN;
		  locs[i++]=Locale.GERMANY;
		  locs[i++]=Locale.ITALIAN;
		  locs[i++]=Locale.ITALY;
		  locs[i++]=Locale.JAPAN;
		  locs[i++]=Locale.JAPANESE;
		  locs[i++]=Locale.KOREA;
		  locs[i++]=Locale.KOREAN;
		  locs[i++]=Locale.PRC;
		  locs[i++]=Locale.SIMPLIFIED_CHINESE;
		  locs[i++]=Locale.TAIWAN;
		  locs[i++]=Locale.TRADITIONAL_CHINESE;
		  locs[i++]=Locale.UK;
		  locs[i++]=CONST_LOCALE;
		  locs[i++]=LOC_POLAND;
		  return locs;
	  }

	  /**
	   * Returns a Hashtable containing all available Locale objects.
	   */
	  static Hashtable<String, Locale> getAvailableLocalesHash() {
		  if (_availableLocalesHash == null)
			  initAvailableLocales();
		  return _availableLocalesHash;
	  }
	  static Locale[] _availableLocales=null;
	  static void initAvailableLocales() {
		  _availableLocales = getStdLocale();
		  _availableLocalesHash = getStdLocaleHash();
	  }
	  /**
	   * Sets the list of available Locale objects.
	   *
	   * @param locs a Locale array containing Locale objects
	   */
	  static public void setAvailableLocales(Locale[] locs) {
		  _availableLocales = locs;
		  _availableLocalesHash = makeHashFromArray(locs);
	  }
	  static Hashtable<String, Locale> makeHashFromArray(Locale[] locs) {
		  Hashtable<String, Locale> t = new Hashtable<String, Locale>();
		  for (int i=0; i<locs.length; i++) {
			  t.put(getDisplayName(locs[i]).toLowerCase(), locs[i]);
		  }
		  return t;
	  }
	  /**
	   * Returns the String representation of a Locale.
	   *
	   * @param locale a Locale
	   *
	   * @return locale.getDisplayName(CONST_LOCALE)
	   */
	  static public String getDisplayName(Locale locale) {
		  return locale.getDisplayName(CONST_LOCALE);
	  }
	  /**
	   * Returns a Hashtable containing all standard Locale objects.
	   */
	  static Hashtable<String, Locale> getStdLocaleHash() {
		  Hashtable<String, Locale> t = new Hashtable<String, Locale>();
		  t.put(getDisplayName(Locale.CANADA).toLowerCase(), Locale.CANADA);
		  t.put(getDisplayName(Locale.CANADA_FRENCH).toLowerCase(), Locale.CANADA_FRENCH);
		  t.put(getDisplayName(Locale.CHINA).toLowerCase(), Locale.CHINA);
		  t.put(getDisplayName(Locale.ENGLISH).toLowerCase(), Locale.ENGLISH);
		  t.put(getDisplayName(Locale.FRANCE).toLowerCase(), Locale.FRANCE);
		  t.put(getDisplayName(Locale.FRENCH).toLowerCase(), Locale.FRENCH);
		  t.put(getDisplayName(Locale.GERMAN).toLowerCase(), Locale.GERMAN);
		  t.put(getDisplayName(Locale.GERMANY).toLowerCase(), Locale.GERMANY);
		  t.put(getDisplayName(Locale.ITALIAN).toLowerCase(), Locale.ITALIAN);
		  t.put(getDisplayName(Locale.ITALY).toLowerCase(), Locale.ITALY);
		  t.put(getDisplayName(Locale.JAPAN).toLowerCase(), Locale.JAPAN);
		  t.put(getDisplayName(Locale.JAPANESE).toLowerCase(), Locale.JAPANESE);
		  t.put(getDisplayName(Locale.KOREA).toLowerCase(), Locale.KOREA);
		  t.put(getDisplayName(Locale.KOREAN).toLowerCase(), Locale.KOREAN);
		  t.put(getDisplayName(Locale.PRC).toLowerCase(), Locale.PRC);
		  t.put(getDisplayName(Locale.SIMPLIFIED_CHINESE).toLowerCase(), Locale.SIMPLIFIED_CHINESE);
		  t.put(getDisplayName(Locale.TAIWAN).toLowerCase(), Locale.TAIWAN);
		  t.put(getDisplayName(Locale.TRADITIONAL_CHINESE).toLowerCase(), Locale.TRADITIONAL_CHINESE);
		  t.put(getDisplayName(Locale.UK).toLowerCase(), Locale.UK);
		  t.put(getDisplayName(CONST_LOCALE).toLowerCase(), CONST_LOCALE);
		  t.put(getDisplayName(LOC_POLAND).toLowerCase(), LOC_POLAND);
		  return t;
	  }
	 /**
	   * Returns a Hashtable to be used in <code>getLocale()</code>
	   * for faster access.
	   */
	  static private void makeBasicLocalesHash() {
		  _basicLocalesHash = new Hashtable<String, Locale>();
		  _basicLocalesHash.put("USA", CONST_LOCALE);
		  _basicLocalesHash.put("FRANCE", Locale.FRANCE);
		  _basicLocalesHash.put("UK", Locale.UK);
		  _basicLocalesHash.put("GERMANY", Locale.GERMANY);
		  _basicLocalesHash.put("CANADA(F)", Locale.CANADA_FRENCH);
		  _basicLocalesHash.put("CANADA(E)", Locale.CANADA);
		  _basicLocalesHash.put("ITALY", Locale.ITALY);
		  _basicLocalesHash.put("CHINA", Locale.CHINA);
		  _basicLocalesHash.put("JAPAN", Locale.JAPAN);
		  _basicLocalesHash.put("KOREA", Locale.KOREA);
		  _basicLocalesHash.put("SIMPL_CHINESE", Locale.SIMPLIFIED_CHINESE);
		  _basicLocalesHash.put("TAIWAN", Locale.TAIWAN);
		  _basicLocalesHash.put("TRAD_CHINESE", Locale.TRADITIONAL_CHINESE);
	  }


}
