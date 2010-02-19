package net.berinle.hibsearch.model;

import java.security.acl.Group;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.ClassBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.Email;
import org.hibernate.validator.NotEmpty;

import com.sun.jndi.cosnaming.IiopUrl.Address;

@Entity
@Table(name="CONTACT")

@Indexed
@Analyzer(impl=StandardAnalyzer.class)
/*@ClassBridge(name="",
			index = Index.TOKENIZED,
			store = Store.YES,
			impl = org.hibernate.search.bridge.builtin.ClassBridge.class
		)*/
public class Contact extends BaseEntity {
	private String firstName;
	private String initial;
	private String lastName;
	private String company;
	private String title;
	private Address address;
	private String phone1;
	private String phone2;
	private String ext1;
	private String ext2;
	private String fax;
	private String email1;
	private String email2;
	private String salutation;
	private String source;
	private String otherSourceDescription;
	
	private Set<Group> serviceGroupInterest;
	//private Set<ServiceCode> serviceCodeInterest;
	//private Set<Seminar> seminars;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@DocumentId
	public Long getId() {
		return super.getId();
	}
	
	@Version
	@Column(name="version")
	public Integer getVersion(){
		return super.getVersion();
	}
	
	@NotNull
	@NotEmpty
	@Size(max=30)
	@Column(name="first_name")
	@Field(index=Index.TOKENIZED, store=Store.YES)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Size(max=1)
	public String getInitial() {
		return initial;
	}	
	public void setInitial(String initial) {
		this.initial = initial;
	}
	
	@NotNull
	@NotEmpty
	@Size(max=30)
	@Column(name="last_name")
	@Field(index=Index.TOKENIZED, store=Store.YES)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Field(index=Index.TOKENIZED, store=Store.YES)
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Embedded
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Size(max=10)
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	
	@Size(max=10)
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	
	@Transient
	@Size(max=5)
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	@Transient
	@Size(max=5)
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	@Size(max=10)
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@NotNull
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	@Size(max=50)
	@Column(name="source_desc")
	public String getOtherSourceDescription() {
		return otherSourceDescription;
	}

	public void setOtherSourceDescription(String otherSourceDescription) {
		this.otherSourceDescription = otherSourceDescription;
	}

	@Size(max=50)
	@Email
	@Column(name="email")
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	
	@Size(max=50)
	@Email
	@Column(name="url")
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	@NotNull
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	/*@ManyToMany
	@JoinTable(
			name="SEMINAR_CONTACTS",
			joinColumns=@JoinColumn(name="contact_id"),
			inverseJoinColumns=@JoinColumn(name="seminar_id")
			)
	public Set<Seminar> getSeminars() {
		return seminars;
	}
	public void setSeminars(Set<Seminar> seminars) {
		this.seminars = seminars;
	}*/

	@Transient
	public Set<Group> getServiceGroupInterest() {
		return serviceGroupInterest;
	}

	public void setServiceGroupInterest(Set<Group> serviceGroupInterest) {
		this.serviceGroupInterest = serviceGroupInterest;
	}

	/*@Transient
	public Set<ServiceCode> getServiceCodeInterest() {
		return serviceCodeInterest;
	}
	public void setServiceCodeInterest(Set<ServiceCode> serviceCodeInterest) {
		this.serviceCodeInterest = serviceCodeInterest;
	}*/
	
	public void resolvePhoneExtentions() {
		phone1 += ext1;
		phone2 += ext2;
	}
}
