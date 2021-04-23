using System;
using System.Reflection;
using apdb_tutorial2_soultion.model;

namespace apdb_tutorial2_soultion.service
{
    public class StudentMapper : IMapper<Student>
    {
        public StudentMapper()
        {
        }

        public Student map(string[] attributes)
        {
            Type type = typeof(Student);
            Student student = new Student();
            int numberOfArguments = type.GetProperties().Length;
            if (attributes.Length != numberOfArguments)
            {
                throw new AttributesException(String.Format("Wrong Number of arguments. Should be {0}, got {1} instead",numberOfArguments,attributes.Length));
            }
            else
            {
                PropertyInfo[] properties = type.GetProperties();
                for(int i = 0; i < properties.Length; i++)
                {
                    if (attributes[i].Length != 0)
                    {
                        properties[i].SetValue(student, attributes[i]);
                    }
                    else
                    {
                        throw new AttributesException(String.Format("Empty attrubute for: {property}",properties[i]));
                    }
                }
            }
            return student;
        }
    }
}
