package com.prs.jy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.Proxy;

/**
 * one person for prs_users
 * @author laolu
 *
 */
@Entity  
@Proxy(lazy = false)   
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="PRS_RESOURCES")
public class Resource implements Serializable {
	private static final long serialVersionUID = -3285114416347865209L;

	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO) 
    @Column(name="resourceid")
    private Integer resourceid;
    
    private String type;
	
    private String value;
	
    private String resourcename;
    
    @ManyToMany(
    		cascade = {CascadeType.PERSIST, CascadeType.MERGE},//CascadeType.REMOVE级联删除，这个东西要谨慎使用
    		mappedBy = "resources",fetch=FetchType.EAGER,
    		targetEntity = Role.class)//resources表示role中的resources
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Collection<Role> roles;
    
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}    
    
	
    public Resource() {
		super();
	}
    
    @Transient
	public String getRoleAuthorities() {
    	List<String> roleAuthorities = new ArrayList<String>();
    	for(Role role : roles) {
    		roleAuthorities.add(role.getRolename());
    	}
        return StringUtils.join(roleAuthorities.toArray(), ",");
    }

	public Integer getResourceid() {
		return resourceid;
	}

	public void setResourceid(Integer resourceid) {
		this.resourceid = resourceid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getResourcename() {
		return resourcename;
	}

	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}
	


}
