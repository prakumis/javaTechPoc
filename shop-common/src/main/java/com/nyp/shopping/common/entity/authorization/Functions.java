package com.nyp.shopping.common.entity.authorization;

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
public class Functions implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int functionId;

	private String functionName;

	@OneToMany(mappedBy = "functions")
	private List<GroupFunctions> groupFunctions;

	public List<GroupFunctions> getGroupFunctions() {
		return groupFunctions;
	}

	public void setGroupFunctions(List<GroupFunctions> groupFunctions) {
		this.groupFunctions = groupFunctions;
	}

	public int getFunctionId() {
		return functionId;
	}

	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Functions() {
	}

}