package net.berinle.hibsearch.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Address {
	private String street1;
	private String street2;
	private String city;
	private String zip;
	private String state;
	private String country;
	
	private String intlCity;
	private String intlPostal;
	private String intlProvince;
	private String intlCountry;
	private Boolean intlAddress = false;
	
	@Column(name="street1")
	@Size(max=50)
	@NotNull
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	
	@Column(name="street2")
	@Size(max=50)
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	
	@NotNull
	@Size(max=40)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@NotNull
	@Size(min=5,max=9)
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@NotNull
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@NotNull
	@Size(max=40)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	@Transient
	@Size(max=40)
	public String getIntlCity() {
		return intlCity;
	}
	public void setIntlCity(String intlCity) {
		this.intlCity = intlCity;
	}
	
	@Transient
	@Size(min=5,max=9)
	public String getIntlPostal() {
		return intlPostal;
	}
	public void setIntlPostal(String intlPostal) {
		this.intlPostal = intlPostal;
	}
	
	@Transient
	@Size(max=40)
	public String getIntlProvince() {
		return intlProvince;
	}
	public void setIntlProvince(String intlProvince) {
		this.intlProvince = intlProvince;
	}
	
	@Transient
	public String getIntlCountry() {
		return intlCountry;
	}
	public void setIntlCountry(String intlCountry) {
		this.intlCountry = intlCountry;
	}
	
	@Column(name="intl_address")
	public Boolean getIntlAddress() {
		return intlAddress;
	}
	public void setIntlAddress(Boolean intlAddress) {
		this.intlAddress = intlAddress;
	}

	public void updateIntlAddress() {
		if ( intlAddress ) {
			city = intlCity;
			state = intlProvince;
			zip = intlPostal;
			country = intlCountry;
		} else {
			country = Country.UNITED_STATES.getName();
		}
	}

}