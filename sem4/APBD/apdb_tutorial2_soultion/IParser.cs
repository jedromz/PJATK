using System;
using System.Collections;
using System.Collections.Generic;

namespace apdb_tutorial2_soultion
{
    public interface IParser
    {
        string Serialize(ICollection collection);
    }
}
