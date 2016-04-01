package bo.swift.bic;

import java.io.Serializable;

import util.commonUTIL;


public class BICSwiftData implements BICData, Serializable  {

	@Override
	public BICSwiftData toBICSwiftData() {
		// TODO Auto-generated method stub
		return null;
	}
	private String _bic = null;
    private String _bicBranch = null;
    private String _institution = null;
    private String _branchInfo = null;
    private String _subtype = null;
    private String _address = null;
    private String _city = null;
    private String _country = null;

    public BICSwiftData(){}
    
    public BICSwiftData(String bic, String bicBranch) {
        if (!commonUTIL.isEmpty(bic))
            _bic = bic.trim();
        if (!commonUTIL.isEmpty(bicBranch))
            _bicBranch = bicBranch.trim();
    }

    public BICSwiftData(String bic,
                        String bicBranch,
                        String institution,
                        String address,
                        String city,
                        String country) {

        if (!commonUTIL.isEmpty(bic))
            _bic = bic.trim();
        if (!commonUTIL.isEmpty(bicBranch))
            _bicBranch = bicBranch.trim();
        if (!commonUTIL.isEmpty(institution))
            _institution = institution.trim();
        if (!commonUTIL.isEmpty(city))
            _city = city.trim();
        if (!commonUTIL.isEmpty(address))
            _address = address.trim();
        if (!commonUTIL.isEmpty(city))
            _city = city.trim();
        if (!commonUTIL.isEmpty(country))
            _country = country.trim();
    }

    public BICSwiftData(String bic,
                        String bicBranch,
                        String institution,
                        String branchInfo,
                        String subtype,
                        String address,
                        String city,
                        String country) {

        if (!commonUTIL.isEmpty(bic))
            _bic = bic.trim();
        if (!commonUTIL.isEmpty(bicBranch))
            _bicBranch = bicBranch.trim();
        if (!commonUTIL.isEmpty(institution))
            _institution = institution.trim();
        if (!commonUTIL.isEmpty(branchInfo))
            _branchInfo = branchInfo.trim();
        if (!commonUTIL.isEmpty(subtype))
            _subtype = subtype.trim();
        if (!commonUTIL.isEmpty(address))
            _address = address.trim();
        if (!commonUTIL.isEmpty(city))
            _city = city.trim();
        if (!commonUTIL.isEmpty(country))
            _country = country.trim();
    }

    public String getBic() {
        return _bic;
    }

    public String getBicBranch() {
        return _bicBranch;
    }

    public String getInstitution() {
        return _institution;
    }

    public String getBranchInfo() {
        return _branchInfo;
    }

    public String getSubtype() {
        return _subtype;
    }

    public String getAddress() {
        return _address;
    }

    public String getCity() {
        return _city;
    }

    public String getCountry() {
        return _country;
    }
    
    private void setBic(String bic) {
        this._bic = bic;
    }

    private void setBicBranch(String bicBranch) {
        this._bicBranch = bicBranch;
    }

    private void setInstitution(String institution) {
       this._institution = institution;
    }

    private void setBranchInfo(String branchInfo) {
        this._branchInfo = branchInfo;
    }

    private void setSubtype(String subtype) {
        this._subtype = subtype;
    }

    private void setAddress(String address) {
        this._address = address;
    }

    private void setCity(String city) {
        this._city = city;
    }

    private void setCountry(String country) {
        this._country = country;
    }

    public String getFullBicCode() {
        if (commonUTIL.isEmpty(_bic))
            return null;
        if (commonUTIL.isEmpty(_bicBranch))
            return _bic;
        return new StringBuffer(_bic).append(_bicBranch).toString();
    }
    
    public boolean equals(Object o) {
    	BICSwiftData sbd=(BICSwiftData)o;
        if (!_bic.equals(sbd._bic)) return false;
        if (!_bicBranch.equals(sbd._bicBranch)) return false;      
        return true;
    }
    
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_bic == null) ? 0 : _bic.hashCode());
		result = prime * result
				+ ((_bicBranch == null) ? 0 : _bicBranch.hashCode());
		return result;
	}

    // Interface BICData
    public BICSwiftData toSwiftBICData() {
        return this;
    }
}
