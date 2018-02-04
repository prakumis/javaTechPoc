package com.nyp.shopping.common.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the groups database table.
 * 
 */
@Entity
@Table(name = "Functions")
@NamedQuery(name = "Functions.findAll", query = "SELECT g FROM Functions g")
public class Functions extends AbstractCommonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String functionName;

	@OneToMany(mappedBy = "functions")
	private List<GroupFunctions> groupFunctions;

	public List<GroupFunctions> getGroupFunctions() {
		return groupFunctions;
	}

	public void setGroupFunctions(List<GroupFunctions> groupFunctions) {
		this.groupFunctions = groupFunctions;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Functions() {
		// no argument constructor
	}

}