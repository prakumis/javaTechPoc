package com.nyp.shopping.common.entity.authorization;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usergroups database table.
 * 
 */
@Entity
@Table(name="UserGroups")
@NamedQuery(name="UserGroup.findAll", query="SELECT u FROM UserGroup u")
public class UserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userGroupID;

	//bi-directional many-to-one association to Group
	@ManyToOne
	@JoinColumn(name="GroupID")
	private Group group;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserID")
	private User user;

	public UserGroup() {
	}

	public int getUserGroupID() {
		return this.userGroupID;
	}

	public void setUserGroupID(int userGroupID) {
		this.userGroupID = userGroupID;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}