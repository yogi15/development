package apps;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import beans.Deal;
import beans.LeContacts;
import beans.LegalEntity;
import beans.UploaderLeBean;

import dsServices.RemoteReferenceData;

public class DataSender {

	RemoteReferenceData remoteRefernce;

	Hashtable<Integer, String> attributeValue = new Hashtable<Integer, String>();

	public LegalEntity buildLe(Deal bean) {
		
		UploaderLeBean leBean = (UploaderLeBean) bean;
		LegalEntity le = new LegalEntity();
		le.setName(leBean.getName());
		le.setAlias(leBean.getAlias());
		le.setRole(leBean.getRole());
		le.setStatus(leBean.getStatus());
		le.setContact(leBean.getContact());
		le.setCountry(leBean.getCountry());

		setAttributes("ParentEntity", leBean.getParentEntity());
		setAttributes("AccountManager", leBean.getAccountManager());
		setAttributes("PeriodStartDate", leBean.getPeriodStartDate());
		setAttributes("PeriodEndDate", leBean.getPeriodEndDate());
		setAttributes("ISDASubmitted", leBean.getIsdaSubmitted());
		
		le.setAttributes(getAttributes());
		
		return le;
	}

	public LeContacts getLeContacts(Deal bean) {
		UploaderLeBean leBean = (UploaderLeBean) bean;
		return leBean.getLeContactsBean();
	}

/*	private int getBook(String name) {
		int i = 0;
		Book book;
		String sql = " book_name = '" + name.trim() + "'";
		try {

			Vector le = (Vector) remoteRefernce.getBookWhere(sql);
			if (le.size() > 0) {
				i = ((Book) le.elementAt(0)).getBookno();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}

	private int getLEid(String name, int id) {

		int i = 0;
		String sql = "  alias = '" + name.trim() + "'";
		try {
			Vector le = (Vector) remoteRefernce.selectLEonWhereClause(sql);
			if (le.size() > 0) {
				i = ((LegalEntity) le.elementAt(0)).getId();
			} else {
				sql = " id = " + id;
				le = (Vector) remoteRefernce.selectLEonWhereClause(sql);
				if (le.size() > 0) {
					i = ((LegalEntity) le.elementAt(0)).getId();
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}*/

	public void setAttributes(String name, String value) {
		attributeValue.put(attributeValue.size() + 1, name + "=" + value + ";");
	}

	public String getAttributes() {

		String attributes = "";
		// Hashtable attributeValue = new Hashtable();

		
		Collection c = attributeValue.values();
		Iterator itr = c.iterator();
		while (itr.hasNext()) {
			attributes = attributes + (String) itr.next();

		}
		return attributes;
	}

	public RemoteReferenceData getRemoteRefernce() {
		return remoteRefernce;
	}

	public void setRemoteRefernce(RemoteReferenceData remoteRefernce) {
		this.remoteRefernce = remoteRefernce;
	}

}