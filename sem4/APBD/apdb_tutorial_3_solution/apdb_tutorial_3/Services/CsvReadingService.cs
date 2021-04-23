using System;
using System.IO;
using System.Collections.Generic;
using apdb_tutorial_3.Exceptions;
using apdb_tutorial_3.Models;

namespace apdb_tutorial_3.Services
{
    public class CsvReadingService<T>
    {


        public CsvReadingService()
        {
        }
        public static System.Collections.Generic.IEnumerable<T> ReadEntries(string pathToFile,Mappers.Mapper<T> mapper)
        {
            if (!Uri.IsWellFormedUriString(pathToFile, UriKind.RelativeOrAbsolute))
            {
                throw new ArgumentException("Path is incorrect");
            }
            if (!File.Exists(pathToFile))
            {
                throw new FileNotFoundException("File does not exist");
            }

            var entries = new List<T>();
            using var stream = new StreamReader(File.OpenRead(pathToFile));
            string line = null;
            while ((line = stream.ReadLine()) != null)
            {
                var entryString = line.Split(',');
                try
                {
                   
                    var entry = mapper.Map(entryString);
                    entries.Add(entry);
                }
                catch (AttributesException e)
                {
                    Console.WriteLine(e.Message);
                }
            }

            return entries;
        }

        
    }
}
