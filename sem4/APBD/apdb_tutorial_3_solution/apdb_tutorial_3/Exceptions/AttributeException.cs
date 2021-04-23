using System;
namespace apdb_tutorial_3.Exceptions
{
    public class AttributesException : Exception
    {
        public AttributesException() : base()
        {
        }

        public AttributesException(string message) : base(message)
        {
        }
    }
}
