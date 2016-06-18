package com.mybatis3.mappers;

import java.util.List;

import com.mybatis3.domain.Student;

/**
 * @author Siva
 *
 */
public interface StudentMapper {

	List<Student> findAllStudents();

	int insertStudent(Student student);

	int updateStudent(Student student);

	int deleteStudent(int id);
}
