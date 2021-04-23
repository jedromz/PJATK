using System;
namespace apdb_tutorial_3.Exceptions
{
    public class ValidationError : Exception
    {
        public ValidationError() : base()
        {
        }

        public ValidationError(string message) : base(message)
        {
        }
    }
}
