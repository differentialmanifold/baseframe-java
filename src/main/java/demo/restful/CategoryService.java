package demo.restful;

//JAX-RS Imports
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.mybatis3.domain.Student;
import com.mybatis3.services.StudentService;

@Path("/categoryservice")
@Produces({ "application/json", "application/xml" })
public class CategoryService {
	@Autowired
	private StudentService studentService;

	@GET
	@Path("/findAllStudent")
	@Produces("application/json")
	public List<Student> findAllStudents() {
		List<Student> students = studentService.findAllStudents();
		return students;
	}

	@POST
	@Path("/insertStudent")
	@Consumes({ "application/json" })
	public Response insertStudent(Student student) {
		studentService.insertStudent(student);
		return Response.ok(student).build();
	}

	@POST
	@Path("/updateStudent")
	@Consumes({ "application/json" })
	public Response updateStudent(Student student) {
		studentService.updateStudent(student);
		return Response.ok(student).build();
	}

	@POST
	@Path("/deleteStudent/{id}")
	@Consumes({ "application/json" })
	public Response deleteStudent(@PathParam("id") String id) {
		studentService.deleteStudent(Integer.valueOf(id));
		return Response.ok().build();
	}
}
