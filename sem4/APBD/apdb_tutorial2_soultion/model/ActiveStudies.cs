using System;
namespace apdb_tutorial2_soultion.model
{
    [Serializable]
    public partial class ActiveStudies
    { 
        private string _name;
        private int _numberOfStudents;
        public ActiveStudies()
        {
        }

        public ActiveStudies(string name, int numberOfStudents)
        {
            _name = name;
            _numberOfStudents = numberOfStudents;
        }
        public string Name
        {
            get => _name;
            set => _name = value;
        }
        public int NumberOfStudents
        {
            get => _numberOfStudents;
            set => _numberOfStudents = value;
        }
    }
}
