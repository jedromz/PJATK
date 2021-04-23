using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;

namespace apdb_tutorial2_soultion.service
{
    //Reads data from .csv to desired IEnumerable
    public class CsvReader<T>
    {
        
        public Mapper<T> Mapper { get; set; }
        public string PathToFile { get; set; }
        public CsvReader()
        {
        }

        public CsvReader(Mapper<T> mapper, string pathToFile)
        {
            this.Mapper = mapper;
            this.PathToFile = pathToFile;
        }
        
        public IEnumerable<T> ReadEntries()
        {
            if (!Uri.IsWellFormedUriString(PathToFile,UriKind.RelativeOrAbsolute))
            {
                throw new ArgumentException("Path is incorrect");
            }
            if (!File.Exists(PathToFile))
            {
                throw new FileNotFoundException("File does not exist");
            }
            
            var entries = new List<T>();
            using var stream = new StreamReader(File.OpenRead(PathToFile));
            string line = null;
            while ((line = stream.ReadLine()) != null) {
                var entryString = line.Split(',');
                try
                {
                    var entry = Mapper.Map(entryString);
                    entries.Add(entry);
                }catch(AttributesException e)
                {
                    ExceptionLogger.Log(e);
                }
            }

            return entries;
        }
    }
}
