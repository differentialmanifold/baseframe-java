package com.mybatis3.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Siva
 *
 */
@XmlRootElement(name = "Student")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer studId;
	private String name;
	private String email;

	@Override
	public String toString() {
		return "Student [studId=" + studId + ", name=" + name + ", email="
				+ email;
	}

	public Student() {
	}

	public Student(Integer id) {
		this.studId = id;
	}

	public Integer getStudId() {
		return studId;
	}

	public void setStudId(Integer id) {
		this.studId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
