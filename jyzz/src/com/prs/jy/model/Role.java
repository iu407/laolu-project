package com.prs.jy.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;

@Component 
@Entity  
@Proxy(lazy = false)   
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="PRS_ROLES")
public class Role implements Serializable {
	private static final long serialVersionUID = -3685114416347860209L;

	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO) 
    @Column(name="roleid")
    private Integer roleid;
    
    private String rolename;
	
    private String remark;
	
    private Date updatetime;
    
    @ManyToMany(
    		targetEntity = Resource.class,
    		cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "PRS_ROLERESOURCES", 
    		joinColumns = @JoinColumn(name = "ROLEID"), 
    		inverseJoinColumns = @JoinColumn(name = "RESOURCEID"))
    private Collection<Resource>  resources;

	public Collection<Resource> getResources() {
		return resources;
	}

	public void setResources(Collection<Resource> resources) {
		this.resources = resources;
	}
	
	
	public Role() {
		super();
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
    
	
	
	
	
	

}
