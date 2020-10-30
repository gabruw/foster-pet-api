using Microsoft.EntityFrameworkCore;

namespace Repository.Context
{
    public class FosterPetContext : DbContext
    {
        public FosterPetContext(DbContextOptions options) : base(options)
        {

        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
        }
    }
}
