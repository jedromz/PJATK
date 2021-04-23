using System;
namespace apdb_tutorial2_soultion
{
    public class AttributesException : Exception
    {
        public AttributesException():base()
        {
        }

        public AttributesException(string message) : base(message)
        {
        }
    }
}
