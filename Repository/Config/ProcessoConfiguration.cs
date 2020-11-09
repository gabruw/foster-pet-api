using Domain.Entity;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Repository.Config
{
    public class ProcessoConfiguration : IEntityTypeConfiguration<Processo>
    {
        public void Configure(EntityTypeBuilder<Processo> builder)
        {
            builder.HasKey(p => p.Id);
            builder.Property(p => p.Data).IsRequired().HasColumnType("DATE");
            builder.Property(p => p.Ocorrencia).IsRequired().HasColumnType("VARCHAR(180)");
            builder.Property(p => p.Ocorrido).IsRequired().HasColumnType("VARCHAR(180)");
        }
    }
}