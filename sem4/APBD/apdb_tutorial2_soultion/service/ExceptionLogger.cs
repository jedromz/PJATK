using System;
using System.Globalization;
using System.IO;
using System.Runtime.CompilerServices;

namespace apdb_tutorial2_soultion.service

{
    //Logs errors to log.txt
    public static class ExceptionLogger
    {
        private static string pathToLogFile = "./static/log.txt";
        public static void Log(Exception exception)
        {
            try
            {
                if (!File.Exists(pathToLogFile)){
                    File.Create(pathToLogFile).Dispose();
                }

                using var sw = File.AppendText(pathToLogFile);
                string error = exception.Message;
                sw.WriteLine("---Exception Details on " + " " + DateTime.Now.ToString(CultureInfo.InvariantCulture) + "----");
                sw.WriteLine(error);
                  
                sw.Flush();
                sw.Close();
            }
            catch (Exception e)
            {
                e.ToString();
            }
        }
    }
}
