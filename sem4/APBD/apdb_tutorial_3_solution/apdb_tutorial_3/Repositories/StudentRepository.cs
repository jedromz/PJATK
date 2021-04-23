using System;
using System.Collections.Generic;
using apdb_tutorial_3.Models;
using apdb_tutorial_3.Services;
using apdb_tutorial_3.Mappers;
using System.IO;

namespace apdb_tutorial_3.Repositories
{
    public class StudentRepository
    {
        private static List<Student> students;
        private static String pathTofile;


        static StudentRepository()

        {
            pathTofile = "/Users/jedrzejromankiewicz/Desktop/PJATK/semestr4/APBD/Tutorial3/tutorial-3-ihord-s21088jr/apdb_tutorial_3_solution/apdb_tutorial_3/bin/Debug/static/data.csv";
            students = (List<Student>)CsvReadingService<Student>.ReadEntries(pathTofile, new Mapper<Student>());
            

        }
        public void Save(Student student)
        {

            var studentToUpdate = FindById(student.IndexNumber);
            var index = students.IndexOf(studentToUpdate);
            Console.WriteLine(index);
            if (index != -1)
            {

                
                students[index] = student;

                
            }
            else
            {
                students.Add(student);
            }
            SerializeChanges();
        }
       
        public List<Student> FindAll()
        {
            
           
            return students;
        }
        public Student FindById(string sNumber)
        {
            return students.Find(student => student.IndexNumber.Equals(sNumber));

        }
       

        public Student Delete(string id)
        {
            var student = FindById(id);
            students.Remove(student);
            SerializeChanges();
            return student;
        }

        private void SerializeChanges()
        {

            using (TextWriter tw = new StreamWriter(pathTofile))
            {
                
                foreach (var s in students)
                { 
                    tw.WriteLine(s);
                }
            }
        }

    }
}
