using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Text.Json;
using System.Text.Json.Serialization;
using apdb_tutorial2_soultion.model;
using apdb_tutorial2_soultion.service;
using CommandLine;
using static apdb_tutorial2_soultion.Tutorial2Utils;

namespace apdb_tutorial2_soultion
{
    public class Options
    {
        
        [Option('r', "read_file", Required = false, Default = "./static/data.csv", HelpText = "Path of the csv file")]
        public string CsvFile { get; set; }
        [Option('w', "write_file", Required = false, Default = "./static/result.json", HelpText = "Path to destination file")]
        public string DestFile { get; set; }
        [Option("format", Required = false, Default = "json", HelpText = "Format of the destination file")]
        public string DataFormat { get; set; }
    }
    public class Tutorial2Utils
    {

        public static Mapper<Student> mapper = new Mapper<Student>();
        public static CsvReader<Student> reader = new CsvReader<Student>();
       
        
        
    }
    class MainClass
    {


        public static void Main(string[] args)
        {
            
            
            //using flags from program arguments
            Parser.Default.ParseArguments<Options>(args)
                   .WithParsed<Options>(options =>
                   {

                       try
                       {
                           reader.PathToFile = options.CsvFile;
                           reader.Mapper = mapper;

                           var exporter = new Exporter<UniversityWrapper> {DestFile = options.DestFile};


                           var cs = new ActiveStudies("Computer Science", 1);
                           var nma = new ActiveStudies("New media arts", 2);
                           var students = reader.ReadEntries();
                           var university = new University(System.Security.Principal.WindowsIdentity.GetCurrent().Name, students, new List<ActiveStudies> {cs, nma});
                           
                           //wrapper class for university so it is seen in JSON as root object
                           var wrappers = new List<UniversityWrapper> {new UniversityWrapper(university)};



                           var dataFormat = options.DataFormat;
                           switch (dataFormat.ToLowerInvariant())
                           {
                               case "json":
                                   exporter.Serializer = new JsonSerialzierImp<UniversityWrapper>();
                                   break;
                               default:
                                   
                                   throw new DataFormatException(
                                       "Unknow data format.");
                           }

                           exporter.Export(wrappers);
                       }
                       catch (DataFormatException)
                       {
                           
                       }
                       catch (Exception e)
                       {
                           ExceptionLogger.Log(e);
                       }


                   });
            
        }
        
    }
}
