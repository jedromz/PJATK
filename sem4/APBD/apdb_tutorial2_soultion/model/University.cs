using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Text.Json.Serialization;

namespace apdb_tutorial2_soultion.model
{   [Serializable]

    
    public class University
    {

        public University(string v)
        {
        }
        
        private DateTime _createdAt;
        private string _author;
        private IEnumerable<Student> _students;
        private List<ActiveStudies> _activeStudies;

        public University(string author, IEnumerable<Student> students, List<ActiveStudies> activeStudies)
        {
            _createdAt = DateTime.Now;
            _author = author;
            _students = students;
            _activeStudies = activeStudies;
        }

        public DateTime CreatedAt { get => _createdAt;
            set => _createdAt = value;
        }
        public string Author { get => _author;
            set => _author = value;
        }
        public IEnumerable<Student> Students { get => _students;
            set => _students = value; }
        public List<ActiveStudies> Studies { get => _activeStudies;
            set => _activeStudies = value; }
    }
}
