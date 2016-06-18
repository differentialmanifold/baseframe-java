package com.mybatis3.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mybatis3.domain.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StudentServiceTest {
	@Autowired
	private StudentService studentService;

	@BeforeClass
	public static void setup() {
		TestDataPopulator.initDatabase();
	}

	@Test
	public void testFindAllStudents() {
		List<Student> students = studentService.findAllStudents();
		assertNotNull(students);
		for (Student student : students) {
			assertNotNull(student);
			// System.out.println(student);
		}
	}

	@Test
	public void testInsertStudent() {
		List<Student> students = studentService.findAllStudents();
		int originNum = students.size();

		Student newStudent = new Student();
		newStudent.setName("newName");
		newStudent.setEmail("newName@gmail.com");
		studentService.insertStudent(newStudent);
		students = studentService.findAllStudents();
		assertEquals(originNum + 1, students.size());
		for (Student student : students) {
			System.out.println(student);
		}
	}

	@Test
	public void testUpdateStudent() {
		List<Student> students = studentService.findAllStudents();
		Student updateStudent = students.get(students.size() - 1);
		updateStudent.setName("updateName");
		updateStudent.setEmail("updateName@gmail.com");
		studentService.updateStudent(updateStudent);

		students = studentService.findAllStudents();
		for (Student student : students) {
			if (student.getStudId() == students.size()) {
				assertEquals("updateName", student.getName());
			}
			System.out.println(student);
		}
	}

	@Test
	public void testDeleteStudent() {
		List<Student> students = studentService.findAllStudents();
		int originNum = students.size();

		int studId = students.get(students.size() - 1).getStudId();
		studentService.deleteStudent(studId);

		students = studentService.findAllStudents();
		assertEquals(originNum - 1, students.size());
		for (Student student : students) {
			System.out.println(student);
		}

	}
}
