using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Text.Encodings.Web;
using System.Text.Json;
using System.Text.Unicode;
using System.Threading.Tasks;
using static System.Text.Json.JsonSerializer;

namespace apdb_tutorial2_soultion.service
{
    public class JsonSerialzierImp<T> : ISerializer<T>
    {
        public JsonSerialzierImp()
        {
        }

      

        public void Serialize(ICollection<T> col, string destFile)
        {
            var options = new JsonSerializerOptions(){
                WriteIndented = true,
                Encoder = JavaScriptEncoder.UnsafeRelaxedJsonEscaping
            };
            if (!File.Exists(destFile))
            {
                File.Create(destFile).Dispose();
                
            }
            var jsonString = JsonSerializer.Serialize(col,options);
            
            File.WriteAllText(destFile, jsonString);
            
        }

      
    }
}
