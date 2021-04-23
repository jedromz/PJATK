using System;

namespace apdb_tutorial2_soultion
{
    public class DataFormatException : Exception
    {
        public DataFormatException():base()
        {
        }

        public DataFormatException(string message) : base(message)
        {
        }
    }
}