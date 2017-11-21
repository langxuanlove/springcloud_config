package com.key.api.model.login;

import java.io.Serializable;

/**
 * 城市字典表
 * @author zhang
 *
 */
public class City implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;

	City() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + "]";
	}

}