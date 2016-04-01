package beans;

public class HelperLeBean implements HelperBean {

	@Override
	public Deal getBean(String[] record) {
		// TODO Auto-generated method stub
		UploaderLeBean uploaderBean = new UploaderLeBean();
		uploaderBean.setName(record[1]);
		uploaderBean.setAlias(record[3]);
		uploaderBean.setRole(record[4]);
		// uploaderBean.setAttributes(record[3]);
		uploaderBean.setStatus(record[7]);
		uploaderBean.setContact(record[6]);
		uploaderBean.setCountry(record[11]);
		uploaderBean.setParentEntity(record[23]);
		uploaderBean.setAccountManager(record[24]);
		uploaderBean.setPeriodStartDate(record[25]);
		uploaderBean.setPeriodEndDate(record[26]);
		uploaderBean.setIsdaSubmitted(record[27]);
		
		LeContacts leContacts = new LeContacts();
		
		leContacts.setLeTitle(record[0]);
		leContacts.setLeFirstName(record[1]);
		leContacts.setLeLastName(record[2]);
		leContacts.setLeRole(record[4]);
		leContacts.setPoId(Integer.parseInt(record[5]));
		leContacts.setContactCategory(record[6]);
		leContacts.setCity(record[8]);
		leContacts.setZipCode(record[9]);
		leContacts.setState(record[10]);
		leContacts.setCountry(record[11]);
		leContacts.setMailingAddress(record[12]);
		leContacts.setEmailAddresss(record[13]);
		leContacts.setPhone(record[14]);
		leContacts.setFax(record[15]);
		leContacts.setTelex(record[16]);
		leContacts.setSwift(record[17]);
		leContacts.setComments(record[18]);
		leContacts.setEffectiveFrom(record[19]);
		leContacts.setEffectiveTo(record[20]);
		leContacts.setExternalRef(record[21]);
		leContacts.setProductType(record[22]);
		
		uploaderBean.setLeContactsBean(leContacts);
		
		return uploaderBean;
	}

}