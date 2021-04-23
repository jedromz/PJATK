using System;
using apdb_tutorial_3.Exceptions;
using apdb_tutorial_3.Models;
using apdb_tutorial_3.Repositories;

namespace apdb_tutorial_3.Services
{
    public class StudentValidator
    {
        private static StudentRepository studentRepository;
        static StudentValidator()
        {
            studentRepository = new StudentRepository();
        }
        public static void validateNewStudent(Student student)
        {
            try
            {
                checkIndexNumberUnique(student.IndexNumber);
                validateBirthDate(DateTime.Parse(student.BirthDate));
            }catch(ValidationError e)
            {
                throw new ValidationError(e.Message);
            }
        }
        public static void validateUpdatedStudent(Student student)
        {
            try
            {
                
                validateBirthDate(DateTime.Parse(student.BirthDate));
            }
            catch (ValidationError e)
            {
                throw new ValidationError(e.Message);
            }
        }
        private static void checkIndexNumberUnique(string indexNmber)
        {
            if(studentRepository.FindById(indexNmber) != null)
            {
                throw new ValidationError("IndexNumber: " + indexNmber + " is already in use");
            }
        }
        private static void validateBirthDate(DateTime birthdate)
        {
            if (DateTime.Today.Year - birthdate.Year < Student.MINIMUM_AGE)
            {
                throw new ValidationError("Student must be " + Student.MINIMUM_AGE + " years old");
            }
        }
        ///...
    }
}
