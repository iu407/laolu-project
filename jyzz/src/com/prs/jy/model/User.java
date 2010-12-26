package com.prs.jy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


/**
 * one person for prs_users
 * 
 * @author laolu
 * 
 */
@Component
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PRS_USERS")
public class User implements UserDetails ,Serializable{
	private static final long serialVersionUID = -3685114416347865209L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userid")
	private Integer userid;

	private String username;

	private String openid;

	private String email;

	private Date createtime;

	private String realname;

	private String password;

	@Type(type="true_false")
	private Boolean disable = false;
	
	@ManyToMany(
			targetEntity = Role.class, 
			fetch = FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "PRS_USEROLES", 
			joinColumns = @JoinColumn(name = "userid"), 
			inverseJoinColumns = @JoinColumn(name = "roleid"))
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Collection<Role> roles = new ArrayList<Role>();
	
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	
	
	@Transient
	private Map<String, List<Resource>> roleResources;

	public User() {
		super();
	}

	/**
	 * 权限
	 */
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(
				roles.size());
		for (Role role : roles) {
			grantedAuthorities.add(new GrantedAuthorityImpl(role.getRolename()));
		}
//		return grantedAuthorities.toArray(new GrantedAuthority[roles.size()]);
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * 账号不允许终止
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 凭证不允许终止
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return !disable;
	}

	public String getAuthoritiesString() {
		List<String> authorities = new ArrayList<String>();
		for (GrantedAuthority authority : this.getAuthorities()) {
			authorities.add(authority.getAuthority());
		}
		return StringUtils.join(authorities.toArray(), ",");
	}

	public Map<String, List<Resource>> getRoleResources() {
		if (this.roleResources == null) {

			this.roleResources = new HashMap<String, List<Resource>>();

			for (Role role : this.roles) {
				String roleName = role.getRolename();
				Collection<Resource> resources = role.getResources();
				for (Resource resource : resources) {
					String key = roleName + "_" + resource.getType();
					if (!this.roleResources.containsKey(key)) {
						this.roleResources.put(key, new ArrayList<Resource>());
					}
					this.roleResources.get(key).add(resource);
				}
			}

		}
		return this.roleResources;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}
	public void setRoleResources(Map<String, List<Resource>> roleResources) {
		this.roleResources = roleResources;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		this.password = md5.encodePassword(password, null);
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 增加角色
	 * @param role
	 */
	public void addRole(Role role){
		Collection<Role> roles = getRoles();
		if(roles == null){
			roles = new ArrayList<Role>();
			roles.add(role);
		}else {
			roles.add(role);
		}
	}

}
