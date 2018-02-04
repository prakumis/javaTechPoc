package com.nyp.shopping.common.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the groups database table.
 * 
 */
@Entity
@Table(name = "GroupFunctions")
@NamedQuery(name = "GroupFunctions.findAll", query = "SELECT g FROM GroupFunctions g")
public class GroupFunctions extends AbstractCommonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="functionId")
	private Functions functions;

	@ManyToOne
	@JoinColumn(name="groupId")
	private Group group;

	public Functions getFunctions() {
		return functions;
	}

	public void setFunctions(Functions functions) {
		this.functions = functions;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public GroupFunctions() {
		// no argument constructor
	}

}
