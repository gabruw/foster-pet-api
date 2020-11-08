using Domain.Entity;
using Microsoft.EntityFrameworkCore;
using Repository.Config;

namespace Repository.Context
{
    public class FosterPetContext : DbContext
    {
        public FosterPetContext(DbContextOptions options) : base(options)
        {

        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Ignore<Login>();

            modelBuilder.ApplyConfiguration(new LoginConfiguration());

            modelBuilder.Ignore<Animal>();

            modelBuilder.ApplyConfiguration(new AnimalConfiguration());


            base.OnModelCreating(modelBuilder);
        }

        public DbSet<Login> Login { get; set; }
        public DbSet<Animal> Animal { get; set; }

    }
}
