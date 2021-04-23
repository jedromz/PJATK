using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using apdb_tutorial_3.Exceptions;
using apdb_tutorial_3.Models;
using apdb_tutorial_3.Repositories;
using apdb_tutorial_3.Services;
using Microsoft.AspNetCore.Mvc;

namespace apdb_tutorial_3
{
    public class StudentService
    {
        private StudentRepository studentRepository;
        public StudentService()
        {
            studentRepository = new StudentRepository();
        }
        
        public List<Student> FindAll()
        {
            return studentRepository.FindAll();
        }

        public void Save(Student student)
        {
           
            try
            {
                StudentValidator.validateNewStudent(student);
                studentRepository.Save(student);
            }
            catch(ValidationError e)
            {
                throw new ValidationError(e.Message);
            }
            
            
        }
       

        public Student FindById(string sNumber)
        {
            return studentRepository.FindById(sNumber);
        }

        public Student Delete(string id)
        {
            var student = studentRepository.FindById(id);
            
            return studentRepository.Delete(id);
        }
        public void Update(Student student_updated)
        {

            try
            {
                StudentValidator.validateUpdatedStudent(student_updated);
                studentRepository.Save(student_updated);
            }
            catch (ValidationError e)
            {
                throw new ValidationError(e.Message);
            }
        }

       
        
    }

}
