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

            modelBuilder.Ignore<Processo>();

            modelBuilder.ApplyConfiguration(new ProcessoConfiguration());

            base.OnModelCreating(modelBuilder);
        }

        public DbSet<Login> Login { get; set; }

        public DbSet<Processo> Processo { get; set; }
    }
}
