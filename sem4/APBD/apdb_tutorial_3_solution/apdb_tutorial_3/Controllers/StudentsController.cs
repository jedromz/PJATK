using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using apdb_tutorial_3.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace apdb_tutorial_3.Controllers
{
 
    
    [Route("api/[controller]")]
    [ApiController]
    public class StudentsController : ControllerBase
    {
        private static StudentService studentService = new StudentService();

        

        [HttpPost]
        public ActionResult<Student> PostStudent(Student student)
        {
            try
            {
                studentService.Save(student);


                return CreatedAtAction(nameof(GetStudent), new { id = student.IndexNumber }, student);
            }catch(Exception e)
            {
                return BadRequest(e.Message);
            }
        }
        [HttpPut("{id}")]
        public ActionResult<Student> PutStudent(string id, Student student)
        {

            if (student.IndexNumber != id)
            {
                return BadRequest("Index Number from request and body do not match");
            }
            try
            {
                studentService.Update(student);
                return CreatedAtAction(nameof(GetStudent), new { id = student.IndexNumber }, student);
            }catch(Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpGet("{id}")]
        public ActionResult<Student> GetStudent(string id)
        {
            var student = studentService.FindById(id);
            if(student == null)
            {
                return NotFound();
            }
            return student;
        }
        [HttpGet]
        public ActionResult<List<Student>> GetStudents()
        {
            return studentService.FindAll();
        }

        [HttpDelete("{id}")]
        public ActionResult<Student> DeleteStudent(string id)
        {
            var student = studentService.FindById(id);
            if (student == null)
            {
                return NotFound();
            }
            return studentService.Delete(id);
        }

    }


}