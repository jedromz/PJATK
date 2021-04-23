using System;
namespace apdb_tutorial2_soultion
{
    public interface IMapper<T>
    {
        T map(string[] attributes);
    }
}
