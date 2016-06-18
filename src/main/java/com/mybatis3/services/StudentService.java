package com.mybatis3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybatis3.domain.Student;
import com.mybatis3.mappers.StudentMapper;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentMapper studentMapper;

	public List<Student> findAllStudents() {
		List<Student> studentList = studentMapper.findAllStudents();
		return studentList;
	}

	public int insertStudent(Student student) {
		int num = studentMapper.insertStudent(student);
		return num;
	}

	public int updateStudent(Student student) {
		int num = studentMapper.updateStudent(student);
		return num;
	}

	public int deleteStudent(int id) {
		int num = studentMapper.deleteStudent(id);
		return num;
	}
}
