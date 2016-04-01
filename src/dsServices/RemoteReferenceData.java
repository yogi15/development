package dsServices;

import java.rmi.RemoteException;

import beans.Attribute;
import beans.B2BConfig;
import beans.BaseBean;
import beans.Book;
import beans.Country;
import beans.CurrencyDefault;
import beans.CurrencyPair;
import beans.CurrencySplitConfig;
import beans.DateRule;
import beans.Favorities;
import beans.Folder;
import beans.Holiday;
import beans.JTableMapping;
import beans.LeContacts;
import beans.CounterParty;
import beans.LiquidationConfig;
import beans.MenuConfiguration;
import beans.MessageConfig;
import beans.NettingConfig;
import beans.Sdi;
import beans.StartUPData;
import beans.Task;
import beans.Users;
import beans.WFConfig;
import beans.WindowSheet;
import beans.WindowTableModelMapping;

import java.rmi.Remote;
import java.sql.Connection;
import java.util.Collection;
import java.util.Vector;

import dbSQL.BaseSQL;

public interface RemoteReferenceData extends Remote{
	
	
	public BaseBean insertSQL( String sql  ,String beanName) throws RemoteException;
	  public boolean updateSQL( String sql  ,String beanName)throws RemoteException;
	  public boolean deleteSQL( String sql  ,String beanName)throws RemoteException;

	  public BaseBean insertSQL(  BaseBean sql  ,String beanName)throws RemoteException;
	  public boolean updateSQL( BaseBean sql  ,String beanName)throws RemoteException;
	  public boolean deleteSQL( BaseBean sql  ,String beanName)throws RemoteException;
	  public BaseBean select( int id  ,String beanName)throws RemoteException;
	  public BaseBean select( String name  ,String beanName)throws RemoteException;
	  public Collection selectWhere( String where  ,String beanName)throws RemoteException;
	  public Collection selectALLData(  String beanName)throws RemoteException;
	  
	  
	
	public Collection selectLEContactOnWhereClause(String whereClause) throws RemoteException;
	public int saveMessageConfig(MessageConfig messConfig)throws RemoteException;
	public MessageConfig selectMessageConfig(MessageConfig messConfig)throws RemoteException;
	public boolean deleteMessageConfig(MessageConfig messConfig)throws RemoteException;
	public boolean updateMessageConfig(MessageConfig messConfig) throws RemoteException;
	public boolean updateHoliday(Holiday holiday) throws RemoteException;
	
	public Collection  selectALLMessageConfigs() throws RemoteException;
	public Collection  getMessageConfigsonProductype(String productype,String productSubtype) throws RemoteException;
	public Collection  getMessageConfigsonProductype(String productype,String productSubtype,int poID) throws RemoteException;
	public int saveDateRule(DateRule dateRule) throws RemoteException;
	public boolean updateDateRule(DateRule dateRule) throws RemoteException;
	public DateRule getDateRule(int dateRuleID)  throws RemoteException;
	
	public int getHolidayonCurrencyPair(String currencyPair,String date) throws RemoteException;
	public Country selectCountryOnISO(String isocode) throws RemoteException;
	public Country selectCountryName(String countryName) throws RemoteException;
	public Collection selectALLCountry()  throws RemoteException;
	public int saveBook(Book book)throws RemoteException;
	public Book selectBook(Book book)throws RemoteException;
	public boolean deleteBook(Book book)throws RemoteException;
	public boolean updateBook(Book book) throws RemoteException;
	public Collection  selectALLBooks() throws RemoteException;
	
	public LiquidationConfig saveLiqConfig(LiquidationConfig LiqConfig)throws RemoteException;
	public Collection selectLiqConfig(LiquidationConfig LiqConfig)throws RemoteException;
	public boolean deleteLiqConfig(LiquidationConfig LiqConfig)throws RemoteException;
	public boolean updateLiqConfig(LiquidationConfig LiqConfig) throws RemoteException;
	public Collection  selectALLLiqConfigs() throws RemoteException;
    public LiquidationConfig getLiquidationConfigOn(int bookId,String productType,String productSubType) throws RemoteException;
	
	public int saveFolder(Folder folder)throws RemoteException;
	public Folder selectFolder(Folder folder)throws RemoteException;
	public boolean deleteFolder(Folder folder)throws RemoteException;
	public  Vector<Folder>  selectALLFolders() throws RemoteException;
	
	public CurrencySplitConfig saveCurrencySplitConfig(CurrencySplitConfig currencySPlit)throws RemoteException;
	public Vector selectCurrencySplitConfig(int splitConfigID)  throws RemoteException;
	public Vector getCurrencySplitConfig(int bookid,String currencypair)  throws RemoteException;
	public boolean deleteCurrencySplitConfig(CurrencySplitConfig currencySPlit)throws RemoteException;
	public Collection  selectALCurrencySplitConfig() throws RemoteException;
	public boolean updateCurrencySplitConfig(CurrencySplitConfig currencySPlit) throws RemoteException;
	
	public B2BConfig saveB2BConfig(B2BConfig b2bConfig)throws RemoteException;
	public Vector selectB2BConfig(int b2bConfigID)  throws RemoteException;
	public Vector getB2BConfig(int bookid,String currencypair)  throws RemoteException;
	public boolean deleteB2BConfig(B2BConfig currencySPlit)throws RemoteException;
	public Collection  selectALB2BConfig() throws RemoteException;
	public boolean updateB2BConfig(B2BConfig b2BConfig) throws RemoteException;
	
	public int saveNettingConfig(NettingConfig netConfig) throws RemoteException;
	public boolean updateNettingConfig(NettingConfig netConfig)  throws RemoteException;
	public Collection getNettingConfigOnCounterParty(int counterPartyId) throws RemoteException;
	public Collection getNettingConfigOnWhere(String where) throws RemoteException;
	public Collection getALLtNettingConfig() throws RemoteException;
	public boolean saveFavourites(Favorities favourites )throws RemoteException;
	public Collection selectFavourites(Favorities favourites)throws RemoteException;
	public boolean deletesaveFavourites(Favorities favourites)throws RemoteException;
	public Collection  selectALLsaveFavourites() throws RemoteException;
	
	
	public Collection selectAllLs() throws RemoteException;
	
	public boolean saveHoliday(Holiday holiday)throws RemoteException;
	public Holiday selectHoliday(Holiday holiday)throws RemoteException;
	public boolean deleteHoliday(Holiday holiday)throws RemoteException;
	
	public Collection  selectALLHolidays() throws RemoteException;
	
	public boolean saveCurrencyPair(CurrencyPair cp)throws RemoteException;
	public Collection selectCurrencyPair(CurrencyPair cp)throws RemoteException;
	public CurrencyPair updateCurrencyPair(CurrencyPair cp) throws RemoteException;
	public boolean deleteCurrencyPair(CurrencyPair cp)throws RemoteException;
	public Collection  selectALLCurrencyPair() throws RemoteException;
	
	public Collection  getLegalEntityDataOnRole(String role) throws RemoteException;
	public Collection  getALLExchanges() throws RemoteException;
	public boolean deleteLE(CounterParty deleteLegalEntity) throws RemoteException;
	
	public boolean saveAttribue(Attribute att)throws RemoteException;
	public Collection selectAttribute(Attribute att)throws RemoteException;
	public Collection  selectALLAttribute() throws RemoteException;
	public Collection selectWhereAttribute(String sql) throws RemoteException;
	public Collection update(Attribute att) throws RemoteException;
	
	
	public int saveUser(Users user)throws RemoteException;
	public Users selectUser(Users user,String group) throws RemoteException;
	public int updateUser(Users user) throws RemoteException;
	public Book selectUserBook(Users user)throws RemoteException;
	public Collection  selectALLUsers() throws RemoteException;
	public Users selectUser(int i) throws RemoteException;
	public Users selectUser(String username,String password) throws RemoteException;
	public boolean deleteUser(Users deleteUser) throws RemoteException;
	
	
	public void removeBook(Book book) throws RemoteException;
	
	
	
	
	public boolean updateCurrencyDefault(CurrencyDefault currencyD)throws RemoteException;
	public void removeCurrencyDefault(CurrencyDefault currencyD) throws RemoteException;
	
	public CurrencyDefault selectCurrencyDefault(CurrencyDefault currencyD) throws RemoteException;
	public void saveCurrencyDefault(CurrencyDefault currencyD) throws RemoteException;
	
	public Vector selectALLCurrencyDefault() throws RemoteException;
	public WindowSheet saveWindowSheet(WindowSheet windowSheet)throws RemoteException;
	public boolean deleteWindowSheet(WindowSheet windowSheet)throws RemoteException;
	
	public Collection selectWindowSheet(String windowName)throws RemoteException;
	public Collection selectWindowSheet(String windowName,String designType)throws RemoteException;
	public Collection  selectALLWindowSheet( ) throws RemoteException;
	
	public boolean saveStartUPData(StartUPData data)throws RemoteException;
	public Collection selectStartUPData(StartUPData data)throws RemoteException;
	public Collection  selectALLStartUPDatas() throws RemoteException;
	public Collection selectAllStartUPData() throws RemoteException;
	public Vector<String> getStartUPData(String name) throws RemoteException;
	public Collection getStartUPDataName() throws RemoteException;
	
	public void updateStartUPData(StartUPData data)throws RemoteException;
	public void removeStartUPData(StartUPData data) throws RemoteException;
	
	
	public boolean updateWindowSheet(WindowSheet data)throws RemoteException;
	public void removeWindowSheet(WindowSheet data) throws RemoteException;
	
	public int saveLe(CounterParty le)throws RemoteException;
	public int getMAXLEID() throws RemoteException;
	public CounterParty selectLE(int i)throws RemoteException;
	
	public int saveLeContacts(LeContacts le)throws RemoteException;
	public boolean updateLeContacts(LeContacts le) throws RemoteException;
	public boolean deleteLeContacts(LeContacts le) throws RemoteException;
	
	public Collection getALLLecontacts() throws RemoteException;
	public Collection getLeContacts(int leid) throws RemoteException;
	public Collection selectLeContacts(int i)throws RemoteException;
	
	public void removeLe(CounterParty le)throws RemoteException;
	
	public boolean updateLe(CounterParty le)throws RemoteException;
	
	
	public Sdi saveSDI(Sdi sdi)throws RemoteException;
	public Sdi selectSDI(Sdi sdi)throws RemoteException;
	public Sdi selectAgentSdi(int poid,int leid,String format,String ccy,String productype) throws RemoteException;
	
	public Sdi updateSDI(Sdi sdi)throws RemoteException;
	public boolean checkSDIKey(Sdi sdi) throws RemoteException;
	public Vector SDIWhere(String sql) throws RemoteException;
	public boolean removeSDI(Sdi sdi) throws RemoteException;
	
	public int saveWF(WFConfig wfc) throws RemoteException;
	public Collection selectAllWF() throws RemoteException;
	public boolean removeWF(WFConfig wfc) throws RemoteException;
	public Collection  selectWFWhere(String sql) throws RemoteException;
	public boolean updateWFconfig(WFConfig wfc) throws RemoteException;
	public int saveTask(Task t) throws RemoteException;
	public Collection selectALLtasks(Task t) throws RemoteException;
	public Collection selectWhereTask(String whereClause) throws RemoteException;
	
	public Users selectUser(Users user) throws RemoteException;
	public Collection selectLEonWhereClause(String whereClause) throws RemoteException;
	Collection selectALLCurrencyPair(String secondaryCurrency) 
			throws RemoteException;
	public boolean updateFolder(Folder folder) throws RemoteException;
	public boolean deleteNettingConfig(int id) throws RemoteException;
	public Vector getSearchCriteria() throws RemoteException;
	public Vector  getSearchColumn(String type) throws RemoteException;
	public boolean isExistLEwithName(CounterParty le) throws RemoteException;
	public Vector getBookWhere(String sql) throws RemoteException;
	public Collection getMessageConfig(String productType, String productSubType,
			String eventType, int poid) throws RemoteException;
	public Collection getSwiftBICData(String sql) throws RemoteException;
	public Vector getallDateRules() throws RemoteException;
	public boolean deleteDateRules(DateRule id) throws RemoteException;
	
	public Users validateUser(Users user) throws RemoteException;
	public DateRule getDateRule(String dateRuleID) throws RemoteException;
	public int checkHolidayOrWeekend(String currency, String checkDate)
			throws RemoteException;
	public Vector getCurrencySplitConfig(int bookID, String currencyPair,
			String currency) throws RemoteException;
	
	public Vector getSDIONLegalEntityRole(String role,int leID) throws RemoteException;

	public Vector getSDIONLegalEntity( int leID) throws RemoteException;
	
	public Vector getPreferredSDIONLegalEntityRoleCurrencyProduct(String role,String productType,String Currency,int leID) throws RemoteException;
	public CurrencyDefault selectCurrencyDefault(String currencyISO)
			throws RemoteException;
	
	
	public int saveJTableObject(JTableMapping mapObject)  throws RemoteException;
	public Collection selectTable()  throws RemoteException;
	public WindowTableModelMapping saveWindowTableModel(
			WindowTableModelMapping windowtablemodelmapping) throws RemoteException;
	public boolean deleteWindowTableModel(
			WindowTableModelMapping windowtablemodelmapping)
			throws RemoteException;
	boolean updateWindowTableModel(
			WindowTableModelMapping windowtablemodelmapping)
			throws RemoteException;
	public  Collection selectALLWindowTableModel() throws RemoteException;
	public Collection selectWindowTableModel(String windowName) throws RemoteException;
	public MenuConfiguration saveMenuConfig(MenuConfiguration menuconfiguration)  throws RemoteException;
	public boolean deleteMenuConfig(MenuConfiguration menuconfiguration)  throws RemoteException;
	public boolean updateMenuConfig(MenuConfiguration menuconfiguration)
			throws RemoteException;
	public Vector<MenuConfiguration> selectMenuConfig() throws RemoteException;
	
	
	
	

}
