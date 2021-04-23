using System;
using apdb_tutorial_3.Attributes;
using apdb_tutorial_3.Exceptions;

namespace apdb_tutorial_3.Mappers
{
    public class Mapper<T>
    {
        public Mapper()
        {
        }

        public T Map(string[] attributes)
        {
            // creates instance of class T
            var type = typeof(T);
            var target = (T)Activator.CreateInstance(type);
            var numberOfArguments = type.GetProperties().Length;
            var properties = type.GetProperties();

            //decrease number of attributes by the number of MapperIgnored fields
            foreach (var propertyInfo in properties)
            {
                if (Attribute.IsDefined(propertyInfo, typeof(MapperIgnore)))
                {
                    numberOfArguments--;
                }
            }
            // check if entry has correct amount of attributes
            if (attributes.Length != numberOfArguments)
            {
                throw new AttributesException(
                    $"Wrong Number of attributes. Should be {numberOfArguments}, got {attributes.Length} instead");
            }
            else
            {
                // set properties of target to attributes

                for (var i = 0; i < numberOfArguments; i++)
                {

                    if (attributes[i] != "")
                    {

                        properties[i].SetValue(target, attributes[i]);
                    }
                    else
                    {
                        throw new AttributesException("Empty argument for property: " + properties[i]);
                    }

                }
            }
            return target;

        }
    }
}
