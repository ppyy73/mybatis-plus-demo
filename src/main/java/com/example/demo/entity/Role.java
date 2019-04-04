package com.example.demo.entity;

public class Role {
	private Long id;
	private String roleName;
	private String roleDescribe;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the roleDescribe
	 */
	public String getRoleDescribe() {
		return roleDescribe;
	}

	/**
	 * @param roleDescribe the roleDescribe to set
	 */
	public void setRoleDescribe(String roleDescribe) {
		this.roleDescribe = roleDescribe;
	}
}
