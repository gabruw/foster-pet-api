using Domain.Entity;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Repository.Config
{
    public class AnimalConfiguration : IEntityTypeConfiguration<Animal>
    {
        public void Configure(EntityTypeBuilder<Animal> builder)
        {
            builder.HasKey(a => a.Id);
            builder.Property(a => a.Idade).IsRequired().HasColumnType("INT(2)");
            builder.Property(a => a.Sexo).IsRequired().HasColumnType("BOOLEAN");
            builder.Property(a => a.PortadorDeficiencia).IsRequired().HasColumnType("BOOLEAN");
            builder.Property(a => a.PortadorCongenita).IsRequired().HasColumnType("BOOLEAN");
            builder.Property(a => a.Nome).IsRequired().HasColumnType("VARCHAR(40)");
            builder.Property(a => a.Especie).IsRequired().HasColumnType("VARCHAR(40)");
            builder.Property(a => a.DescricaoDeficiencia).IsRequired().HasColumnType("VARCHAR(256)");
            builder.Property(a => a.DescricaoCongenita).IsRequired().HasColumnType("VARCHAR(256)");
            builder.Property(a => a.Foto).IsRequired().HasColumnType("VARCHAR(128)");
        }
    }
}
