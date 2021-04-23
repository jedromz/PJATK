using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;
using apdb_tutorial2_soultion.attributes;

namespace apdb_tutorial2_soultion.model
{
    [Serializable]
    public class Student
    {
        public string  FirstName { get; set; }
        public string LastName { get; set; }
        [JsonIgnore]
        public string StudiesName { get; set; }
        [JsonIgnore]
        public string StudiesMode { get; set; }
        public string IndexNumber { get; set; }
        public string Birthdate { get; set; }
        public string Email { get; set; }
        public string MothersName { get; set; }
        public string FathersName { get; set; }
        [JsonPropertyName("Studies")]
        [MapperIgnore]
        public StudentStudies Studies
        {
            get => new StudentStudies(StudiesName, StudiesMode);
            set => Studies = value ?? throw new ArgumentNullException(nameof(value));
        }
      
        public Student(){ }

        public Student(string firstName, string lastName, string studiesName, string studiesMode, string indexNumber, string birthdate, string email, string mothersName, string fathersName)
        {
            FirstName = firstName;
            LastName = lastName;
            StudiesName = studiesName;
            StudiesMode = studiesMode;
            IndexNumber = indexNumber;
            Birthdate = birthdate;
            Email = email;
            MothersName = mothersName;
            FathersName = fathersName;
        }

        public override bool Equals(object obj)
        {
            return obj is Student student &&
                   FirstName == student.FirstName &&
                   LastName == student.LastName &&
                   IndexNumber == student.IndexNumber;
        }

        public override int GetHashCode()
        {
            var hashCode = 823710296;
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(FirstName);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(LastName);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(IndexNumber);
            return hashCode;
        }
    }
}
