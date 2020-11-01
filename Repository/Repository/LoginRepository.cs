using Domain.DTO;
using Domain.IRepository;
using Repository.Context;

namespace Repository.Repository
{
    public class LoginRepository : BaseRepository<Login>, ILoginRepository
    {
        public LoginRepository(FosterPetContext fosterPetContext) : base(fosterPetContext)
        {

        }
    }
}
