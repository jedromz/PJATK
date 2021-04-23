using System;
using System.Collections.Generic;
using apdb_tutorial2_soultion.model;
using apdb_tutorial2_soultion.service;

namespace apdb_tutorial2_soultion
{
    public class Exporter<T>
    {
        public ISerializer<T> Serializer { get; set; }
        public string DestFile { get; set; }

        
        public Exporter()
        {
        }

        public Exporter(ISerializer<T> serializer)
        {
            Serializer = serializer;
      
        }

        public void Export(ICollection<T> col)
        {
            Serializer.Serialize(col, DestFile);

        }
    }
}
