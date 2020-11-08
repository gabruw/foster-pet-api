using Domain.Entity;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Repository.Config
{
    public class LoginConfiguration : IEntityTypeConfiguration<Login>
    {
        public void Configure(EntityTypeBuilder<Login> builder)
        {
            builder.HasKey(l => l.Id);
            builder.Property(l => l.Email).IsRequired().HasColumnType("VARCHAR(180)");
            builder.Property(l => l.Senha).IsRequired().HasColumnType("VARCHAR(40)");
        }
    }
}
