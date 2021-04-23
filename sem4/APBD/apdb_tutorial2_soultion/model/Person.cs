using System;
namespace apdb_tutorial2_soultion.model
{  
    public class Person
    {
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string motherName { get; set; }
        public string fatherName { get; set; }
        public DateTime birthdate { get; set; }
        public string email { get; set; }

        public Person()
        {
        }
  
        public Person(string firstName, string lastName, string motherName, string fatherName, DateTime birthdate, string email)
        {
            FirstName = firstName;
            LastName = lastName;
            this.motherName = motherName;
            this.fatherName = fatherName;
            this.birthdate = birthdate;
            this.email = email;
        }
        public Person(string firstName, string lastName,string indexNumber, string motherName, string fatherName, string birthdate, string email)
        {
            FirstName = firstName;
            LastName = lastName;
            this.motherName = motherName;
            this.fatherName = fatherName;
            this.birthdate = DateTime.Parse(birthdate);
            this.email = email;
        }


    }
}
