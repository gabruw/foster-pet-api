using Domain.Entity;
using Domain.IRepository;
using Repository.Context;

namespace Repository.Repository
{
    public class ProcessoRepository : BaseRepository<Processo>, IProcessoRepository
    {
        public ProcessoRepository(FosterPetContext fosterPetContext) : base(fosterPetContext)
        {

        }
    }
}
