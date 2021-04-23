using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace apdb_tutorial2_soultion.service
{
    public interface ISerializer<T>
    {
        void Serialize(ICollection<T> col,string destFile);
        
    }
}
