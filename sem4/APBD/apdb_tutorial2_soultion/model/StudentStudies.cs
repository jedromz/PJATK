using System;

namespace apdb_tutorial2_soultion.model
{
    [Serializable]
    public class StudentStudies
    {
        public string Name { get; set; }
        public string Mode { get; set; }

        public StudentStudies(string name, string mode)
        {
            Name = name;
            Mode = mode;
        }
    }
    
}