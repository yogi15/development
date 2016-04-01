package dsServices;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import util.AccessMechanismUtil;
import util.ClassInstantiateUtil;
import util.commonUTIL;

import dbSQL.AttributSQL;
import dbSQL.B2BConfigSQL;
import dbSQL.BaseSQL;
import dbSQL.BookSQL;
import dbSQL.CountrySQL;
import dbSQL.CurrencyDefaultSQL;
import dbSQL.CurrencyPairSQL;
import dbSQL.CurrencySplitSQL;
import dbSQL.DateRuleSQL;
import dbSQL.FavouritiesSQL;
import dbSQL.FolderSQL;
import dbSQL.HolidaySQL;
import dbSQL.JTableMappingSQL;
import dbSQL.LeContactsSql;
import dbSQL.LegalEntitySQL;
import dbSQL.LiquidationConfigSQL;
import dbSQL.MenuConfigSQL;
import dbSQL.MessageConfigSQL;
import dbSQL.MessageSQL;
import dbSQL.NettingSQL;
import dbSQL.SdiSQL;
import dbSQL.SearchCriteriaSQL;
import dbSQL.StartUPDataSQL;
import dbSQL.TaskSQL;
import dbSQL.UsersSQL;
import dbSQL.WFConfigSQL;
import dbSQL.WindowSheetSQL;
import dbSQL.WindowTableModelMappingSQL;
import dbSQL.dsSQL;
import beans.Attribute;
import beans.B2BConfig;
import beans.BaseBean;
import beans.Book;
import beans.BookAttribute;
import beans.Country;
import beans.CurrencyDefault;
import beans.CurrencyPair;
import beans.CurrencySplitConfig;
import beans.DateRule;
import beans.Favorities;
import beans.Folder;
import beans.Holiday;
import beans.JTableMapping;
import beans.LEAttribute;
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

public class ReferenceDataImp implements RemoteReferenceData {

	private Vector<LEAttribute> processLEAttribues(CounterParty le, int id) {

		Vector<LEAttribute> leAttrVec = new Vector<LEAttribute>();

		String attributes = le.getAttributes();

		if (!commonUTIL.isEmpty(attributes)) {

			String atttoken[] = attributes.trim().split(";");

			for (int i = 0; i < atttoken.length; i++) {
				String att = (String) atttoken[i];
				if (att.contains("=")) {
					String attvalue = att.substring(att.indexOf('=') + 1,
							att.length());
					String attnameName = att.substring(0, att.indexOf('='));
					LEAttribute leAttrBean = new LEAttribute();
					leAttrBean.setId(id);
					leAttrBean.setName(attnameName);
					leAttrBean.setValue(attvalue);

					leAttrVec.add(leAttrBean);
				}
			}

		}

		return leAttrVec;
	}

	private Vector<BookAttribute> processBookAttribues(Book book, int id) {

		Vector<BookAttribute> bookAttrVec = new Vector<BookAttribute>();

		String attributes = book.getAttributes();

		if (!commonUTIL.isEmpty(attributes)) {

			String atttoken[] = attributes.trim().split(";");

			for (int i = 0; i < atttoken.length; i++) {
				String att = (String) atttoken[i];
				if (att.contains("=")) {

					String attvalue = att.substring(att.indexOf('=') + 1,
							att.length());
					String attnameName = att.substring(0, att.indexOf('='));

					BookAttribute bookAttrBean = new BookAttribute();
					bookAttrBean.setId(id);
					bookAttrBean.setName(attnameName);
					bookAttrBean.setValue(attvalue);

					bookAttrVec.add(bookAttrBean);
				}
			}

		}
		return bookAttrVec;
	}

	private String getBookAttributesString(Collection<BookAttribute> bookAttrVec) {

		Iterator<BookAttribute> itr = bookAttrVec.iterator();
		StringBuffer attribute = new StringBuffer();

		while (itr.hasNext()) {

			BookAttribute element = (BookAttribute) itr.next();

			attribute.append(element.getName()).append("=")
					.append(element.getValue()).append(";");

		}

		return attribute.toString();
	}

	private String getLEAttributesString(Collection<LEAttribute> leAttrVec) {

		Iterator<LEAttribute> itr = leAttrVec.iterator();
		StringBuffer attribute = new StringBuffer();

		while (itr.hasNext()) {

			LEAttribute element = (LEAttribute) itr.next();

			attribute.append(element.getName()).append("=")
					.append(element.getValue()).append(";");

		}

		return attribute.toString();
	}

	@Override
	public void removeLe(CounterParty le) throws RemoteException {
		// TODO Auto-generated method stub

		Connection con = dsSQL.getConn();
		boolean isDeleted = LegalEntitySQL.delete(le, con);
		int i = 0;

		if (isDeleted) {

			Vector<LEAttribute> leAttrVec = processLEAttribues(le, le.getId());

			boolean isAttributeDeleted = AttributSQL.deleteLEAttribute(
					leAttrVec, con);

			if (!isAttributeDeleted) {

				i = -2;

			}
		} else {

			i = -2;
		}

	}

	@Override
	public Vector getSearchCriteria() throws RemoteException {
		// TODO Auto-generated method stub

		return (Vector) SearchCriteriaSQL.selectSearchCriteria(dsSQL.getConn());

	}

	@Override
	public Vector getSearchColumn(String type) throws RemoteException {
		// TODO Auto-generated method stub

		return (Vector) SearchCriteriaSQL.selectSearchColumn(type,
				dsSQL.getConn());

	}

	@Override
	public int saveBook(Book book) throws RemoteException {

		Connection con = dsSQL.getConn();
		int id = BookSQL.save(book, con);

		if (id > 0 && !book.getAttributes().equals("")) {

			boolean isAttributeSaved = false;
			Vector<BookAttribute> bookAttrVec = processBookAttribues(book, id);

			isAttributeSaved = AttributSQL.saveBookAttribute(bookAttrVec, con);

			if (!isAttributeSaved) {
				id = -2;
			}

		}

		return id;
	}

	@Override
	public int saveLe(CounterParty le) throws RemoteException {

		Connection con = dsSQL.getConn();
		int id = 0;

		if (!isExistLEwithName(le)) {

			id = LegalEntitySQL.save(le, dsSQL.getConn());

		} else {

			id = -1;
		}

		if (id > 0 && !le.getAttributes().equals("")) {

			Vector<LEAttribute> leAttrVec = processLEAttribues(le, id);

			boolean isAttributeSaved = AttributSQL.saveLEAttribute(leAttrVec,
					con);

			if (!isAttributeSaved) {

				id = -2;

			}
		}

		return id;
	}

	@Override
	public Book selectBook(Book book) throws RemoteException {

		Connection con = dsSQL.getConn();
		Book bookObj = BookSQL.selectBook(book.getBookno(), con);

		if (bookObj != null) {

			Collection<BookAttribute> bookAttrVec = AttributSQL
					.selectBookAttribute(bookObj.getBookno(), con);

			String attributes = getBookAttributesString(bookAttrVec);

			bookObj.setAttributes(attributes);
		}

		return bookObj;
	}

	@Override
	public Vector getBookWhere(String sql) throws RemoteException {

		Connection con = dsSQL.getConn();
		Collection<Book> bookVec = BookSQL.selectbookWhere(sql, con);
		Vector<Book> returnBookVec = new Vector<Book>();

		int size = bookVec.size();

		String attributes = "";

		Iterator<Book> itr = bookVec.iterator();

		while (itr.hasNext()) {

			Book element = (Book) itr.next();

			Collection<BookAttribute> bookAttrVec = AttributSQL
					.selectBookAttribute(element.getBookno(), con);

			attributes = getBookAttributesString(bookAttrVec);

			element.setAttributes(attributes);
			returnBookVec.add(element);
		}

		return returnBookVec;
	}

	@Override
	public CounterParty selectLE(int id) throws RemoteException {

		Connection con = dsSQL.getConn();
		CounterParty le = null;

		Vector legalEntitys = (Vector) LegalEntitySQL
				.selectLegalEntity(id, con);

		if (legalEntitys != null && legalEntitys.size() > 0) {

			le = (CounterParty) legalEntitys.elementAt(0);

			Collection<LEAttribute> leAttrVec = AttributSQL.selectLEAttribute(
					le.getId(), con);

			String attributes = getLEAttributesString(leAttrVec);

			le.setAttributes(attributes);
		}

		return le;

	}

	@Override
	public boolean isExistLEwithName(CounterParty le) throws RemoteException {
		boolean flag = false;
		String sql = " alias = '" + le.getAlias() + "'";
		Vector vec = (Vector) selectLEonWhereClause(sql);
		if (vec == null || vec.isEmpty())
			return flag;
		flag = true;
		return flag;

	}

	@Override
	public boolean updateBook(Book book) throws RemoteException {

		Connection con = dsSQL.getConn();

		boolean isBookUpdated = BookSQL.update(book, con);

		if (isBookUpdated && !book.getAttributes().equals("")) {

			Vector<BookAttribute> updateBookAttribute = processBookAttribues(
					book, book.getBookno());

			Collection<BookAttribute> isAttributeAlreadySaved = AttributSQL
					.selectBookAttribute(book.getBookno(), con);

			if (isAttributeAlreadySaved.size() > 0) {

				boolean isAttributeUpdated = AttributSQL.updateBookAttribute(
						updateBookAttribute, con);

			} else {

				boolean isAttributeSaved = AttributSQL.saveBookAttribute(
						updateBookAttribute, con);
			}

		}

		return isBookUpdated;
	}

	@Override
	public boolean updateLe(CounterParty le) throws RemoteException {

		Connection con = dsSQL.getConn();

		// if(!isExistLEwithName(le))
		boolean isLEUpdated = LegalEntitySQL.update(le, dsSQL.getConn());

		if (isLEUpdated && !le.getAttributes().equals("")) {

			Vector<LEAttribute> updateLEAttribute = processLEAttribues(le,
					le.getId());

			Collection<LEAttribute> isAttributeAlreadySaved = AttributSQL
					.selectLEAttribute(le.getId(), con);

			if (isAttributeAlreadySaved.size() > 0) {

				boolean isAttributeUpdated = AttributSQL.updateLEAttribute(
						updateLEAttribute, con);

			} else {

				boolean isAttributeUpdated = AttributSQL.saveLEAttribute(
						updateLEAttribute, con);

			}

		}

		return isLEUpdated;

		// return false;
	}

	@Override
	public void removeBook(Book book) throws RemoteException {

		Connection con = dsSQL.getConn();

		boolean isBookDeleted = BookSQL.delete(book, con);

		if (isBookDeleted) {

			Vector<BookAttribute> deleteBookAttrVec = processBookAttribues(
					book, book.getBookno());

			boolean isAttributeDeleted = AttributSQL.deleteBookAttribute(
					deleteBookAttrVec, con);
		}

	}

	@Override
	public boolean removeSDI(Sdi sdi) throws RemoteException {

		return SdiSQL.delete(sdi, dsSQL.getConn());
	}

	@Override
	public Sdi saveSDI(Sdi sdi) throws RemoteException {

		if (sdi.getId() == 0)
			return SdiSQL.save(sdi, dsSQL.getConn());
		else
			return updateSDI(sdi);
	}

	@Override
	public Sdi selectSDI(Sdi sdi) throws RemoteException {

		Vector Sdis = (Vector) SdiSQL.selectSDI(sdi.getId(), dsSQL.getConn());
		if (Sdis.isEmpty())
			return null;
		return (Sdi) Sdis.elementAt(0);
	}

	@Override
	public Sdi updateSDI(Sdi sdi) throws RemoteException {

		return SdiSQL.update(sdi, dsSQL.getConn());

	}

	@Override
	public int getMAXLEID() throws RemoteException {

		return BookSQL.selectMaxID(dsSQL.getConn());

	}

	@Override
	public Collection selectAllLs() throws RemoteException {

		Connection con = dsSQL.getConn();
		Vector<CounterParty> returnLE = new Vector<CounterParty>();

		Collection<CounterParty> allBooks = LegalEntitySQL.selectALL(con);

		Iterator<CounterParty> itr = allBooks.iterator();

		while (itr.hasNext()) {

			CounterParty element = (CounterParty) itr.next();

			Collection<LEAttribute> leAttrVec = AttributSQL
					.selectALLLEAttribute(con);

			element.setAttributes(getLEAttributesString(leAttrVec));

			returnLE.add(element);

		}

		return returnLE;

	}

	@Override
	public Collection selectALLBooks() throws RemoteException {

		Connection con = dsSQL.getConn();
		Vector<Book> returnBooks = new Vector<Book>();
		Collection<Book> allBooks = BookSQL.selectALL(con);

		Iterator<Book> itr = allBooks.iterator();

		while (itr.hasNext()) {

			Book element = (Book) itr.next();

			Collection<BookAttribute> bookAttrVec = AttributSQL
					.selectBookAttribute(element.getBookno(), con);

			element.setAttributes(getBookAttributesString(bookAttrVec));

			returnBooks.add(element);

		}

		return returnBooks;
	}

	@Override
	public void removeStartUPData(StartUPData data) throws RemoteException {
		// TODO Auto-generated method stub
		StartUPDataSQL.delete(data, dsSQL.getConn());

	}

	@Override
	public boolean saveStartUPData(StartUPData data) throws RemoteException {
		// TODO Auto-generated method stub
		StartUPDataSQL.save(data, dsSQL.getConn());
		return false;
	}

	@Override
	public Collection selectALLStartUPDatas() throws RemoteException {
		// TODO Auto-generated method stub
		return StartUPDataSQL.selectALL(dsSQL.getConn());

	}

	@Override
	public Collection selectAllStartUPData() throws RemoteException {
		// TODO Auto-generated method stub

		return StartUPDataSQL.selectALL(dsSQL.getConn());
	}

	@Override
	public Collection selectStartUPData(StartUPData data)
			throws RemoteException {
		// TODO Auto-generated method stub
		return StartUPDataSQL
				.selectStartUPData(data.getName(), dsSQL.getConn());
	}

	@Override
	public void updateStartUPData(StartUPData data) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection getStartUPDataName() throws RemoteException {
		// TODO Auto-generated method stub
		return StartUPDataSQL.getStartUPDataName(dsSQL.getConn());
	}

	@Override
	public Vector<String> getStartUPData(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return StartUPDataSQL.getStartUPData(name, dsSQL.getConn());
	}

	@Override
	public int saveUser(Users user) throws RemoteException {
		// TODO Auto-generated method stub

		String salt = null;
		try {
			salt = AccessMechanismUtil.getSalt();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		user.setPassword(AccessMechanismUtil.getPasswordWithSalt(
				user.getPassword(), salt));
		return UsersSQL.save(user, salt, dsSQL.getConn());
	}

	@Override
	public Collection selectALLUsers() throws RemoteException {
		// TODO Auto-generated method stub
		return UsersSQL.selectALL(dsSQL.getConn());
	}

	// can be handel thorugh procedure

	@Override
	public int saveWF(WFConfig wfc) throws RemoteException {
		// TODO Auto-generated method stub
		// if(!wfc.getCurrentStatus().equalsIgnoreCase(wfc.getOrgStatus()))

		// if(checkReverseTransitionOnWfc(wfc,dsSQL.getConn()) == 1)
		// return -1;

		return WFConfigSQL.save(wfc, dsSQL.getConn());
		// return -1;
	}

	private int checkReverseTransitionOnWfc(WFConfig wfc, Connection conn) {
		// TODO Auto-generated method stub
		int i = 0;
		String sql = " orgstatus='" + wfc.getOrgStatus()
				+ "'  and currentstatus='" + wfc.getCurrentStatus()
				+ "' and productsubtype='" + wfc.getProductSubType()
				+ "' and producttype='" + wfc.getProductType()
				+ "' and type ='" + wfc.getType() + "'";
		Vector checkRev = (Vector) WFConfigSQL
				.selectWhere(sql, dsSQL.getConn());
		if (checkRev == null || checkRev.isEmpty()) {
			i = 0;
		} else {
			i = 1;
		}
		return i;

	}

	@Override
	public Users selectUser(int i) throws RemoteException {
		// TODO Auto-generated method stub
		return UsersSQL.selectUsers(i, dsSQL.getConn());
	}

	@Override
	public Collection selectAllWF() throws RemoteException {
		// TODO Auto-generated method stub
		return WFConfigSQL.selectALL(dsSQL.getConn());
	}

	@Override
	public Users selectUser(String username, String password)
			throws RemoteException {
		// TODO Auto-generated method stub
		return UsersSQL.selectUsers(username, password, dsSQL.getConn());
	}

	@Override
	public int saveTask(Task t) throws RemoteException {
		// TODO Auto-generated method stub
		return TaskSQL.save(t, dsSQL.getConn());
	}

	@Override
	public Collection selectALLtasks(Task t) throws RemoteException {
		// TODO Auto-generated method stub
		return TaskSQL.selectTask(t.getId(), dsSQL.getConn());
	}

	@Override
	public Users selectUser(Users user) throws RemoteException {
		// TODO Auto-generated method stub
		return UsersSQL.selectUsers(user.getUser_name(), user.getPassword(),
				dsSQL.getConn());
	}

	@Override
	public Users selectUser(Users user, String group) throws RemoteException {
		// TODO Auto-generated method stub
		return UsersSQL.selectUsers(user.getUser_name(), user.getPassword(),
				group, dsSQL.getConn());
	}

	@Override
	public Collection selectWhereTask(String whereClause)
			throws RemoteException {
		// TODO Auto-generated method stub
		return TaskSQL.selectTaskWhere(whereClause, dsSQL.getConn());
	}

	@Override
	public int updateUser(Users user) throws RemoteException {
		// TODO Auto-generated method stub
		return UsersSQL.update(user, dsSQL.getConn());
	}

	@Override
	public boolean saveAttribue(Attribute att) throws RemoteException {
		// TODO Auto-generated method stub
		return AttributSQL.save(att, dsSQL.getConn());
	}

	@Override
	public Collection selectALLAttribute() throws RemoteException {
		// TODO Auto-generated method stub
		return AttributSQL.selectALL(dsSQL.getConn());
	}

	@Override
	public Collection selectAttribute(Attribute att) throws RemoteException {
		// TODO Auto-generated method stub
		return AttributSQL.selectAttribute(att.getId(), dsSQL.getConn());
	}

	@Override
	public Collection selectLEonWhereClause(String whereClause)
			throws RemoteException {

		Connection con = dsSQL.getConn();
		Vector<CounterParty> returnLE = new Vector<CounterParty>();

		Collection<CounterParty> le = LegalEntitySQL.selectLEOnWhereClause(
				whereClause, con);

		Iterator<CounterParty> itr = le.iterator();

		while (itr.hasNext()) {

			CounterParty element = (CounterParty) itr.next();

			Collection<LEAttribute> leAttrVec = AttributSQL
					.selectALLLEAttribute(con);

			element.setAttributes(getLEAttributesString(leAttrVec));

			returnLE.add(element);

		}

		return returnLE;

	}

	@Override
	public Collection selectWhereAttribute(String sql) throws RemoteException {
		// TODO Auto-generated method stub
		return AttributSQL.selectWhereClause(sql, dsSQL.getConn());
	}

	@Override
	public Collection update(Attribute att) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteLE(CounterParty deleteLegalEntity)
			throws RemoteException {

		Connection con = dsSQL.getConn();

		boolean isLeDeleted = LegalEntitySQL.delete(deleteLegalEntity, con);

		if (isLeDeleted && !deleteLegalEntity.getAttributes().equals("")) {

			Vector<LEAttribute> deleteLEAttrVec = processLEAttribues(
					deleteLegalEntity, deleteLegalEntity.getId());

			boolean isAttributeDeleted = AttributSQL.deleteLEAttribute(
					deleteLEAttrVec, con);

		}
		return isLeDeleted;
	}

	@Override
	public boolean deleteBook(Book book) throws RemoteException {

		Connection con = dsSQL.getConn();

		boolean isBookDeleted = BookSQL.delete(book, con);

		if (isBookDeleted && !book.getAttributes().equals("")) {

			Vector<BookAttribute> deleteBookAttrVec = processBookAttribues(
					book, book.getBookno());

			boolean isAttributeDeleted = AttributSQL.deleteBookAttribute(
					deleteBookAttrVec, con);
		}
		return isBookDeleted;
	}

	@Override
	public boolean deleteUser(Users deleteUsers) throws RemoteException {
		// TODO Auto-generated method stub

		boolean isUserDeleted = UsersSQL.delete(deleteUsers, dsSQL.getConn());

		return isUserDeleted;
	}

	// can be handled through procedure.

	@Override
	public boolean removeWF(WFConfig deleteWFConfig) throws RemoteException {
		return WFConfigSQL.delete(deleteWFConfig, dsSQL.getConn());
	}

	@Override
	public Collection selectWFWhere(String sql) throws RemoteException {
		// TODO Auto-generated method stub
		// String sqls = sql;
		Vector v1 = (Vector) WFConfigSQL.selectWhere(sql, dsSQL.getConn());
		//

		return v1;
	}

	@Override
	public boolean updateCurrencyDefault(CurrencyDefault currencyD)
			throws RemoteException {
		// TODO Auto-generated method stub
		return CurrencyDefaultSQL.update(currencyD, dsSQL.getConn());
	}

	@Override
	public void removeCurrencyDefault(CurrencyDefault currencyD)
			throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public CurrencyDefault selectCurrencyDefault(CurrencyDefault currencyD)
			throws RemoteException {
		// TODO Auto-generated method stub
		return CurrencyDefaultSQL.selectcurrencyDefault(
				currencyD.getCurrency_code(), dsSQL.getConn());

	}

	@Override
	public CurrencyDefault selectCurrencyDefault(String currencyISO)
			throws RemoteException {
		// TODO Auto-generated method stub
		return CurrencyDefaultSQL.selectcurrencyDefault(currencyISO,
				dsSQL.getConn());

	}

	@Override
	public void saveCurrencyDefault(CurrencyDefault currencyD)
			throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector selectALLCurrencyDefault() throws RemoteException {
		// TODO Auto-generated method stub
		return (Vector) CurrencyDefaultSQL.selectALL(dsSQL.getConn());

	}

	@Override
	public boolean saveHoliday(Holiday holiday) throws RemoteException {
		// TODO Auto-generated method stub
		return HolidaySQL.update(holiday, dsSQL.getConn());
	}

	@Override
	public Holiday selectHoliday(Holiday holiday) throws RemoteException {
		// TODO Auto-generated method stub
		return HolidaySQL.selectHoliday(holiday.getCurrency(), dsSQL.getConn());
	}

	@Override
	public boolean deleteHoliday(Holiday holiday) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection selectALLHolidays() throws RemoteException {
		// TODO Auto-generated method stub
		return HolidaySQL.selectALL(dsSQL.getConn());
	}

	@Override
	public int checkHolidayOrWeekend(String currency, String checkDate)
			throws RemoteException {
		// TODO Auto-generated method stub
		return HolidaySQL.checkHolidayOrWeekend(currency, checkDate,
				dsSQL.getConn());
	}

	@Override
	public boolean saveCurrencyPair(CurrencyPair cp) throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " quoting_currency = '" + cp.getQuoting_currency()
				+ "' and primary_currency = '" + cp.getPrimary_currency() + "'";
		CurrencyPair cp1 = CurrencyPairSQL.select(sql, dsSQL.getConn());
		if (cp1 != null)
			return false;
		return CurrencyPairSQL.save(cp, dsSQL.getConn());
	}

	@Override
	public boolean deleteCurrencyPair(CurrencyPair cp) throws RemoteException {
		// TODO Auto-generated method stub
		return CurrencyPairSQL.delete(cp, dsSQL.getConn());
	}

	@Override
	public Collection selectALLCurrencyPair(String secondaryCurrency)
			throws RemoteException {
		// TODO Auto-generated method stub
		String sql = "  quoting_currency = '" + secondaryCurrency + "'";

		return CurrencyPairSQL.selectCurrencyPair(sql, dsSQL.getConn());
	}

	@Override
	public CurrencyPair updateCurrencyPair(CurrencyPair cp)
			throws RemoteException {
		// TODO Auto-generated method stub
		return CurrencyPairSQL.update(cp, dsSQL.getConn());
	}

	@Override
	public Collection selectCurrencyPair(CurrencyPair cp)
			throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " quoting_currency = '" + cp.getQuoting_currency() + "'";

		return CurrencyPairSQL.selectCurrencyPair(sql, dsSQL.getConn());
	}

	@Override
	public Collection selectALLCurrencyPair() throws RemoteException {
		// TODO Auto-generated method stub
		return CurrencyPairSQL.selectALL(dsSQL.getConn());
	}

	@Override
	public boolean saveFavourites(Favorities favourites) throws RemoteException {
		// TODO Auto-generated method stub
		Vector fav = (Vector) FavouritiesSQL.getFavorities(favourites,
				dsSQL.getConn());
		if (fav == null || fav.isEmpty())
			return FavouritiesSQL.save(favourites, dsSQL.getConn());
		return false;
	}

	@Override
	public Collection selectFavourites(Favorities favourites)
			throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " userid = " + favourites.getUserId() + "  and type = '"
				+ favourites.getType() + "'";
		return FavouritiesSQL.selectWhere(sql, dsSQL.getConn());
	}

	@Override
	public boolean deletesaveFavourites(Favorities favourites)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection selectALLsaveFavourites() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkSDIKey(Sdi sdi) throws RemoteException {
		// TODO Auto-generated method stub
		if (sdi != null) {
			return SdiSQL.SDIKeyWhere(sdi.getkey(), dsSQL.getConn());
		}
		return false;
	}

	@Override
	public Vector SDIWhere(String sql) throws RemoteException {
		// TODO Auto-generated method stub

		return (Vector) SdiSQL.SDIWhere(sql, dsSQL.getConn());
	}

	@Override
	public int saveFolder(Folder folder) throws RemoteException {
		// TODO Auto-generated method stub
		return FolderSQL.save(folder, dsSQL.getConn());
	}

	@Override
	public boolean updateFolder(Folder folder) throws RemoteException {
		// TODO Auto-generated method stub
		return FolderSQL.update(folder, dsSQL.getConn());
	}

	@Override
	public Folder selectFolder(Folder folder) throws RemoteException {
		// TODO Auto-generated method stub
		return FolderSQL.selectFolder(folder.getId(), dsSQL.getConn());
	}

	@Override
	public boolean deleteFolder(Folder folder) throws RemoteException {
		// TODO Auto-generated method stub
		return FolderSQL.delete(folder, dsSQL.getConn());
	}

	@Override
	public Vector<Folder> selectALLFolders() throws RemoteException {
		// TODO Auto-generated method stub
		return FolderSQL.selectALL(dsSQL.getConn());
	}

	@Override
	public boolean updateWFconfig(WFConfig wfc) throws RemoteException {
		// TODO Auto-generated method stub
		return WFConfigSQL.update(wfc, dsSQL.getConn());
	}

	@Override
	public int saveNettingConfig(NettingConfig netConfig)
			throws RemoteException {
		// TODO Auto-generated method stub
		return NettingSQL.save(netConfig, dsSQL.getConn());
	}

	@Override
	public boolean updateNettingConfig(NettingConfig netConfig)
			throws RemoteException {
		// TODO Auto-generated method stub
		return NettingSQL.update(netConfig, dsSQL.getConn());
	}

	@Override
	public Collection getNettingConfigOnCounterParty(int counterPartyId)
			throws RemoteException {
		// TODO Auto-generated method stub
		return NettingSQL.selectOnCounterParty(counterPartyId, dsSQL.getConn());
	}

	@Override
	public Collection getNettingConfigOnWhere(String where)
			throws RemoteException {
		// TODO Auto-generated method stub
		return NettingSQL.selectWhere(where, dsSQL.getConn());
	}

	@Override
	public Collection getALLtNettingConfig() throws RemoteException {
		// TODO Auto-generated method stub
		return NettingSQL.selectALL(dsSQL.getConn());
	}

	@Override
	public boolean deleteNettingConfig(int id) throws RemoteException {
		// TODO Auto-generated method stub
		NettingConfig netConfig = new NettingConfig();
		netConfig.setId(id);
		return NettingSQL.delete(netConfig, dsSQL.getConn());
	}

	@Override
	public CurrencySplitConfig saveCurrencySplitConfig(
			CurrencySplitConfig currencySPlit) throws RemoteException {
		// TODO Auto-generated method stub
		return CurrencySplitSQL.save(currencySPlit, dsSQL.getConn());
	}

	@Override
	public Vector selectCurrencySplitConfig(int splitConfigID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return CurrencySplitSQL.selectCurrencySplitConfig(splitConfigID,
				dsSQL.getConn());
	}

	@Override
	public boolean deleteCurrencySplitConfig(CurrencySplitConfig currencySPlit)
			throws RemoteException {
		// TODO Auto-generated method stub
		return CurrencySplitSQL.delete(currencySPlit, dsSQL.getConn());
	}

	@Override
	public Collection selectALCurrencySplitConfig() throws RemoteException {
		// TODO Auto-generated method stub
		return CurrencySplitSQL.select(dsSQL.getConn());
	}

	@Override
	public boolean updateCurrencySplitConfig(CurrencySplitConfig currencySPlit)
			throws RemoteException {
		// TODO Auto-generated method stub
		return CurrencySplitSQL.update(currencySPlit, dsSQL.getConn());
	}

	@Override
	public Vector getCurrencySplitConfig(int bookid, String currencypair)
			throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " bookid = " + bookid + " and currencyPair = '"
				+ currencypair + "'";
		return CurrencySplitSQL.selectWhere(sql, dsSQL.getConn());
	}

	@Override
	public Book selectUserBook(Users user) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public B2BConfig saveB2BConfig(B2BConfig b2bConfig) throws RemoteException {
		// TODO Auto-generated method stub
		return B2BConfigSQL.save(b2bConfig, dsSQL.getConn());
	}

	@Override
	public Vector selectB2BConfig(int b2bConfigID) throws RemoteException {
		// TODO Auto-generated method stub
		return B2BConfigSQL.selectB2BConfig(b2bConfigID, dsSQL.getConn());
	}

	@Override
	public Vector getB2BConfig(int bookid, String currencypair)
			throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " bookid = " + bookid + " and currencyPair = '"
				+ currencypair + "'";
		return B2BConfigSQL.selectWhere(sql, dsSQL.getConn());

	}

	@Override
	public boolean deleteB2BConfig(B2BConfig currencySPlit)
			throws RemoteException {
		// TODO Auto-generated method stub
		return B2BConfigSQL.delete(currencySPlit, dsSQL.getConn());
	}

	@Override
	public Collection selectALB2BConfig() throws RemoteException {
		// TODO Auto-generated method stub
		return B2BConfigSQL.select(dsSQL.getConn());
	}

	@Override
	public boolean updateB2BConfig(B2BConfig b2bConfig) throws RemoteException {
		// TODO Auto-generated method stub
		return B2BConfigSQL.update(b2bConfig, dsSQL.getConn());
	}

	@Override
	public LiquidationConfig saveLiqConfig(LiquidationConfig LiqConfig)
			throws RemoteException {
		// TODO Auto-generated method stub
		return LiquidationConfigSQL.save(LiqConfig, dsSQL.getConn());
	}

	@Override
	public Collection selectLiqConfig(LiquidationConfig LiqConfig)
			throws RemoteException {
		// TODO Auto-generated method stub
		String sql = "";
		return (Vector) LiquidationConfigSQL.selectLiquidationConfigOnWhere(
				sql, dsSQL.getConn());
	}

	@Override
	public boolean deleteLiqConfig(LiquidationConfig LiqConfig)
			throws RemoteException {
		// TODO Auto-generated method stub
		return LiquidationConfigSQL.delete(LiqConfig, dsSQL.getConn());
	}

	@Override
	public boolean updateLiqConfig(LiquidationConfig LiqConfig)
			throws RemoteException {
		// TODO Auto-generated method stub
		return LiquidationConfigSQL.update(LiqConfig, dsSQL.getConn());
	}

	@Override
	public Collection selectALLLiqConfigs() throws RemoteException {
		// TODO Auto-generated method stub
		return LiquidationConfigSQL.selectALL(dsSQL.getConn());
	}

	@Override
	public LiquidationConfig getLiquidationConfigOn(int bookId,
			String productType, String productSubType) {
		// TODO Auto-generated method stub
		return LiquidationConfigSQL.getLiquidationConfigOnWhere(bookId,
				productType, productSubType, dsSQL.getConn());
	}

	@Override
	public int saveMessageConfig(MessageConfig messConfig)
			throws RemoteException {
		// TODO Auto-generated method stub
		return MessageConfigSQL.save(messConfig, dsSQL.getConn());
	}

	@Override
	public MessageConfig selectMessageConfig(MessageConfig messConfig)
			throws RemoteException {
		// TODO Auto-generated method stub
		return MessageConfigSQL.selectMessage(messConfig.getId(),
				dsSQL.getConn());
	}

	@Override
	public Collection getMessageConfig(String productType,
			String productSubType, String eventType, int poid) {
		String sql = " producttype ='" + productType
				+ "' and productsubtype = '" + productSubType
				+ "' and eventType = '" + eventType + "' and poid = " + poid;
		return MessageConfigSQL.selectMessageConfigOn(sql, dsSQL.getConn());
	}

	@Override
	public boolean deleteMessageConfig(MessageConfig messConfig)
			throws RemoteException {
		// TODO Auto-generated method stub
		return MessageConfigSQL.delete(messConfig, dsSQL.getConn());
	}

	@Override
	public boolean updateMessageConfig(MessageConfig messConfig)
			throws RemoteException {
		// TODO Auto-generated method stub
		return MessageConfigSQL.update(messConfig, dsSQL.getConn());
	}

	@Override
	public Collection selectALLMessageConfigs() throws RemoteException {
		// TODO Auto-generated method stub
		return MessageConfigSQL.selectALL(dsSQL.getConn());
	}

	@Override
	public Sdi selectAgentSdi(int agentid, int poid, String format, String ccy,
			String productype) throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " cpid = " + agentid + " and  poid = " + poid
				+ " and sdiformat = '" + format + "' and currency = '" + ccy
				+ "' and products = '" + productype + "'";
		Vector agentSdi = (Vector) SdiSQL.SDIWhere(sql, dsSQL.getConn());
		if (agentSdi == null || agentSdi.isEmpty())
			return null;
		else
			return (Sdi) agentSdi.elementAt(0);
	}

	@Override
	public Collection getSwiftBICData(String sql) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateLeContacts(LeContacts le) throws RemoteException {
		// TODO Auto-generated method stub
		return LeContactsSql.update(le, dsSQL.getConn());
	}

	@Override
	public boolean deleteLeContacts(LeContacts le) throws RemoteException {
		// TODO Auto-generated method stub
		return LeContactsSql.delete(le, dsSQL.getConn());
	}

	@Override
	public Collection getALLLecontacts() throws RemoteException {
		// TODO Auto-generated method stub
		return LeContactsSql.selectALL(dsSQL.getConn());
	}

	@Override
	public Collection getLeContacts(int leid) throws RemoteException {
		// TODO Auto-generated method stub
		return LeContactsSql.selectContactsOnLe(leid, dsSQL.getConn());
	}

	@Override
	public Collection selectLeContacts(int leid) throws RemoteException {
		// TODO Auto-generated method stub
		return LeContactsSql.selectContactsOnLe(leid, dsSQL.getConn());
	}

	@Override
	public Country selectCountryOnISO(String isocode) throws RemoteException {
		// TODO Auto-generated method stub
		return CountrySQL.selectCountryOnISO(isocode, dsSQL.getConn());
	}

	@Override
	public Country selectCountryName(String countryName) throws RemoteException {
		// TODO Auto-generated method stub
		return CountrySQL.selectISOCODEOnCountry(countryName, dsSQL.getConn());
	}

	@Override
	public Collection selectALLCountry() throws RemoteException {
		// TODO Auto-generated method stub
		return CountrySQL.select(dsSQL.getConn());
	}

	@Override
	public int saveLeContacts(LeContacts le) throws RemoteException {
		// TODO Auto-generated method stub
		return LeContactsSql.save(le, dsSQL.getConn());
	}

	@Override
	public int saveDateRule(DateRule dateRule) throws RemoteException {
		// TODO Auto-generated method stub
		return DateRuleSQL.save(dateRule, dsSQL.getConn());
	}

	@Override
	public boolean updateDateRule(DateRule dateRule) throws RemoteException {
		// TODO Auto-generated method stub
		return DateRuleSQL.update(dateRule, dsSQL.getConn());
	}

	@Override
	public DateRule getDateRule(int dateRuleID) throws RemoteException {
		// TODO Auto-generated method stub

		return DateRuleSQL.select(dateRuleID, dsSQL.getConn());
	}

	@Override
	public DateRule getDateRule(String dateRuleID) throws RemoteException {
		// TODO Auto-generated method stub

		return DateRuleSQL.select(dateRuleID, dsSQL.getConn());
	}

	@Override
	public Vector getallDateRules() throws RemoteException {
		// TODO Auto-generated method stub
		return DateRuleSQL.getSelectDateRules(dsSQL.getConn());
	}

	@Override
	public boolean deleteDateRules(DateRule id) throws RemoteException {
		// TODO Auto-generated method stub
		return DateRuleSQL.delete(id, dsSQL.getConn());
	}

	@Override
	public Collection getLegalEntityDataOnRole(String role)
			throws RemoteException {
		// TODO Auto-generated method stub
		String roleSQL = " role like '%" + role.trim() + "%'";
		return LegalEntitySQL.selectLEOnWhereClause(roleSQL, dsSQL.getConn());
	}

	@Override
	public Collection getALLExchanges() throws RemoteException {
		// TODO Auto-generated method stub
		return getLegalEntityDataOnRole("Exchange");
	}

	@Override
	public Collection selectLEContactOnWhereClause(String whereClause)
			throws RemoteException {
		// TODO Auto-generated method stub
		return LeContactsSql.selectLEContactOnWhereClause(whereClause,
				dsSQL.getConn());
	}

	@Override
	public Users validateUser(Users user) throws RemoteException {
		// TODO Auto-generated method stub
		String encodeUserPassword = "";
		String userPassword = user.getPassword();

		Users fetchedUser = UsersSQL.selectUsers(user.getUser_name(),
				userPassword, user.getUser_groups(), dsSQL.getConn());

		/*
		 * if (fetchedUser != null) {
		 * 
		 * encodeUserPassword = AccessMechanismUtil
		 * .getPasswordWithSalt(userPassword, UsersSQL.getSalt(fetchedUser,
		 * dsSQL.getConn()));
		 * 
		 * }
		 */

		// if (!encodeUserPassword.equals(fetchedUser.getPassword())) {

		// fetchedUser = null;

		// }

		return fetchedUser;
	}

	@Override
	public Vector getCurrencySplitConfig(int bookID, String currencyPair,
			String currency) throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " bookid = " + bookID + " and CURRENCYTOSPLIT='"
				+ currency + "' and CURRENCYPAIR='" + currencyPair + "'";
		return CurrencySplitSQL.selectWhere(sql, dsSQL.getConn());
	}

	@Override
	public Vector getSDIONLegalEntityRole(String role, int leID)
			throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " role ='" + role + "' and cpid = " + leID;
		return SDIWhere(sql);
	}

	/*
	 * @Override public Vector
	 * getPreferredSDIONLegalEntityRoleCurrencyProduct(String role, int leID)
	 * throws RemoteException { // TODO Auto-generated method stub String sql =
	 * " role ='" + role +"' and cpid = " + leID; return SDIWhere(sql); }
	 */

	@Override
	public Vector getPreferredSDIONLegalEntityRoleCurrencyProduct(String role,
			String productType, String Currency, int leID)
			throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " role ='" + role + "'and products = '" + productType
				+ "' and currency = '" + Currency + "' and cpid = " + leID
				+ " order by priority ";
		return SDIWhere(sql);
	}

	@Override
	public Collection getMessageConfigsonProductype(String productype,
			String productSubtype) throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " producttype = '" + productype
				+ "' and productsubtype ='" + productSubtype + "'";
		Vector messConfig = (Vector) MessageConfigSQL.selectMessageConfigOn(
				sql, dsSQL.getConn());
		return messConfig;
	}

	@Override
	public Collection getMessageConfigsonProductype(String productype,
			String productSubtype, int poID) throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " producttype = '" + productype
				+ "' and productsubtype ='" + productSubtype + "' and poid = "
				+ poID;
		Vector messConfig = (Vector) MessageConfigSQL.selectMessageConfigOn(
				sql, dsSQL.getConn());
		return messConfig;
	}

	@Override
	public boolean updateHoliday(Holiday holiday) throws RemoteException {
		// TODO Auto-generated method stub
		return HolidaySQL.update(holiday, dsSQL.getConn());
	}

	@Override
	public int getHolidayonCurrencyPair(String currencyPair, String date)
			throws RemoteException {
		// TODO Auto-generated method stub
		return HolidaySQL.getHolidaysOnCp(currencyPair, date, dsSQL.getConn());
	}

	@Override
	public Vector getSDIONLegalEntity(int leID) throws RemoteException {
		// TODO Auto-generated method stub
		String sql = "   cpid = " + leID;
		return SDIWhere(sql);
	}

	@Override
	public WindowSheet saveWindowSheet(WindowSheet windowSheet)
			throws RemoteException {
		// TODO Auto-generated method stub
		return WindowSheetSQL.save(windowSheet, dsSQL.getConn());
	}

	@Override
	public Collection selectWindowSheet(String windowName)
			throws RemoteException {
		// TODO Auto-generated method stub
		return WindowSheetSQL.selectWindowSheet(windowName, dsSQL.getConn());
	}

	@Override
	public Collection selectALLWindowSheet() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateWindowSheet(WindowSheet data) throws RemoteException {
		// TODO Auto-generated method stub
		return WindowSheetSQL.update(data, dsSQL.getConn());
	}

	@Override
	public void removeWindowSheet(WindowSheet data) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deleteWindowSheet(WindowSheet windowSheet)
			throws RemoteException {
		// TODO Auto-generated method stub
		return WindowSheetSQL.delete(windowSheet, dsSQL.getConn());
	}

	@Override
	public Collection selectWindowSheet(String windowName, String designType)
			throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " windowName ='" + windowName + "' and designType ='"
				+ designType + "'";
		return WindowSheetSQL.selectWindowSheetWhere(sql, dsSQL.getConn());
	}

	@Override
	public int saveJTableObject(JTableMapping mapObject) throws RemoteException {
		// TODO Auto-generated method stub
		return JTableMappingSQL.save(mapObject, dsSQL.getConn());
	}

	@Override
	public Collection selectALLWindowTableModel() throws RemoteException {
		// TODO Auto-generated method stub
		return WindowTableModelMappingSQL.selectALL(dsSQL.getConn());
	}

	@Override
	public Collection selectWindowTableModel(String windowName)
			throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " WINDOWNAME = '" + windowName + "'";
		return WindowTableModelMappingSQL.selectWindowModel(sql,
				dsSQL.getConn());
	}

	@Override
	public WindowTableModelMapping saveWindowTableModel(
			WindowTableModelMapping windowtablemodelmapping)
			throws RemoteException {
		// TODO Auto-generated method stub
		return WindowTableModelMappingSQL.save(windowtablemodelmapping,
				dsSQL.getConn());

	}

	@Override
	public boolean deleteWindowTableModel(
			WindowTableModelMapping windowtablemodelmapping)
			throws RemoteException {
		// TODO Auto-generated method stub
		return WindowTableModelMappingSQL.delete(windowtablemodelmapping,
				dsSQL.getConn());

	}

	@Override
	public boolean updateWindowTableModel(
			WindowTableModelMapping windowtablemodelmapping)
			throws RemoteException {
		// TODO Auto-generated method stub
		return WindowTableModelMappingSQL.update(windowtablemodelmapping,
				dsSQL.getConn());

	}

	@Override
	public Collection selectTable() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuConfiguration saveMenuConfig(MenuConfiguration menuconfiguration)
			throws RemoteException {
		// TODO Auto-generated method stub
		return MenuConfigSQL.save(menuconfiguration, dsSQL.getConn());
	}

	@Override
	public boolean deleteMenuConfig(MenuConfiguration menuconfiguration)
			throws RemoteException {
		// TODO Auto-generated method stub
		return MenuConfigSQL.delete(menuconfiguration, dsSQL.getConn());
	}

	@Override
	public boolean updateMenuConfig(MenuConfiguration menuconfiguration)
			throws RemoteException {
		// TODO Auto-generated method stub
		return MenuConfigSQL.update(menuconfiguration, dsSQL.getConn());
	}

	@Override
	public Vector<MenuConfiguration> selectMenuConfig() throws RemoteException {
		// TODO Auto-generated method stub
		return (Vector<MenuConfiguration>) MenuConfigSQL.selectALL(dsSQL
				.getConn());
	}

	@Override
	public BaseBean insertSQL(  String sql,String beanName) {
		// TODO Auto-generated method stub
		return makeSQLObject(beanName).insertSQL(sql,dsSQL.getConn() );
	}

	@Override
	public boolean updateSQL(  String sql,String beanName) {
		// TODO Auto-generated method stub
		return makeSQLObject(beanName).updateSQL(sql, dsSQL.getConn());
	}

	@Override
	public boolean deleteSQL(String sql,String beanName) {
		// TODO Auto-generated method stub
		return makeSQLObject(beanName).deleteSQL(sql, dsSQL.getConn());
	}

	@Override
	public BaseBean insertSQL(  BaseBean sql,String beanName) {
		// TODO Auto-generated method stub
		return makeSQLObject(beanName).insertSQL(sql, dsSQL.getConn());
	}

	@Override
	public boolean updateSQL(  BaseBean sql,String beanName) {
		// TODO Auto-generated method stub
		return makeSQLObject(beanName).updateSQL(sql, dsSQL.getConn());
	}

	@Override
	public boolean deleteSQL(   BaseBean sql,String beanName) {
		// TODO Auto-generated method stub
		return makeSQLObject(beanName).deleteSQL(sql, dsSQL.getConn());
	}

	@Override
	public BaseBean select(   int id,String beanName) {
		// TODO Auto-generated method stub
		return makeSQLObject(beanName).select(id, dsSQL.getConn());
	}

	@Override
	public BaseBean select(   String name,String beanName) {
		// TODO Auto-generated method stub
		return makeSQLObject(beanName).select(name, dsSQL.getConn());
	}

	@Override
	public Collection selectWhere(   String where,String beanName) {
		// TODO Auto-generated method stub
		return makeSQLObject(beanName).selectWhere(where, dsSQL.getConn());
	}

	@Override
	public Collection selectALLData(  String beanName) {
		// TODO Auto-generated method stub
		return makeSQLObject(beanName).selectALLData(  dsSQL.getConn());
	}

	static public Hashtable<String, BaseSQL> sqlCache = new Hashtable<String, BaseSQL>();

	private static BaseSQL makeSQLObject(String sqlName) {
		BaseSQL sql = null;
		 
		if (!commonUTIL.isEmpty(sqlName)) {
			String sqlObject = "dbSQL." + sqlName +"SQL";
			try {
				sql = sqlCache.get(sqlName);
				if (sql == null) {
					Class sqlC = ClassInstantiateUtil
							.getClass(sqlObject, false);
					if (sqlC != null) {
						sql = (BaseSQL) sqlC.newInstance();
						sqlCache.put(sqlName.trim(), sql);
					}
				}
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("ReferenceDataImp", "makeSQLObject", e);
			}
		}
		return sql;
	}

}
