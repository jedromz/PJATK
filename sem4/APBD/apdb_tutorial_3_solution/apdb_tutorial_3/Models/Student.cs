using System;
using System.ComponentModel.DataAnnotations;
using apdb_tutorial_3.Attributes;
using apdb_tutorial_3.Exceptions;

namespace apdb_tutorial_3.Models
{
    [Serializable]
    public class Student
    {
        public static int MINIMUM_AGE = 18;
        private DateTime _birthDate;
        [Required(ErrorMessage ="FirstName is required")]
        public string FirstName { get; set; }
        [Required(ErrorMessage = "LastName is required")]
        public string LastName { get; set; }
        [Required(ErrorMessage ="indexNumber is Required")]
        [RegularExpressionAttribute(@"^s[0-9]{4}$",ErrorMessage = "Index number does not fit the format")]
        public string IndexNumber { get; set; }
        [Required(ErrorMessage = "Birthdate is Required")]
        [DataType(DataType.Date)]
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}")]
        public string
            BirthDate
        {
            get { return _birthDate.ToString("yyyy-MM-dd"); }
            set
            {
                var dateTime = DateTime.Parse(value);
   
                _birthDate = dateTime;
            }
        }
        [Required(ErrorMessage = "Course is Required")]
        public string Course { get; set; }
        [Required(ErrorMessage = "Mode is Required")]
        public string Mode { get; set; }
        [Required(ErrorMessage = "Email is Required")]
        [EmailAddress]
        public string Email { get; set; }
        [Required(ErrorMessage = "Father's name is Required")]
        public string FathersName { get; set; }
        [Required(ErrorMessage = "Mother's name is Required")]
        public string MothersName { get; set; }
        
        
        public Student()
        {
        }

        public override string ToString()
        {
            return String.Join(",", FirstName, LastName, IndexNumber, BirthDate, Course, Mode, Email,FathersName,MothersName );
        }
    }
}
