package com.nyp.shopping.common.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the groups database table.
 * 
 */
@Entity
@Table(name="Groups")
@NamedQuery(name="Group.findAll", query="SELECT g FROM Group g")
public class Group extends AbstractCommonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Boolean canEditApplications;

	private Boolean canEditNetworks;

	private Boolean canEditServers;

	private String groupName;

	private Boolean userAdmin;

	@OneToMany(mappedBy="group" ,fetch=FetchType.EAGER)
	private List<GroupFunctions> groupFunctions;

	//bi-directional many-to-one association to UserGroup
	@OneToMany(mappedBy="group")
	private List<UserGroup> usergroups;

	public Group() {
		// no argument constructor
	}


	public Boolean getCanEditApplications() {
		return this.canEditApplications;
	}

	public void setCanEditApplications(Boolean canEditApplications) {
		this.canEditApplications = canEditApplications;
	}

	public Boolean getCanEditNetworks() {
		return this.canEditNetworks;
	}

	public void setCanEditNetworks(Boolean canEditNetworks) {
		this.canEditNetworks = canEditNetworks;
	}

	public Boolean getCanEditServers() {
		return this.canEditServers;
	}

	public void setCanEditServers(Boolean canEditServers) {
		this.canEditServers = canEditServers;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Boolean getUserAdmin() {
		return this.userAdmin;
	}

	public void setUserAdmin(Boolean userAdmin) {
		this.userAdmin = userAdmin;
	}
	
	public List<GroupFunctions> getGroupFunctions() {
		return groupFunctions;
	}

	public void setGroupFunctions(List<GroupFunctions> groupFunctions) {
		this.groupFunctions = groupFunctions;
	}

	public List<UserGroup> getUsergroups() {
		return this.usergroups;
	}

	public void setUsergroups(List<UserGroup> usergroups) {
		this.usergroups = usergroups;
	}

	public UserGroup addUsergroup(UserGroup usergroup) {
		getUsergroups().add(usergroup);
		usergroup.setGroup(this);

		return usergroup;
	}

	public UserGroup removeUsergroup(UserGroup usergroup) {
		getUsergroups().remove(usergroup);
		usergroup.setGroup(null);

		return usergroup;
	}
}