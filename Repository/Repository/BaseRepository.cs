using Domain.IRepository;
using Repository.Context;
using System.Collections.Generic;
using System.Linq;

namespace Repository.Repository
{
    public class BaseRepository<TEntity> : IBaseRepository<TEntity> where TEntity : class
    {
        protected readonly FosterPetContext FosterPetContext;

        public BaseRepository(FosterPetContext fosterPetContext)
        {
            FosterPetContext = fosterPetContext;
        }

        public void Incluid(TEntity entity)
        {
            FosterPetContext.Set<TEntity>().Add(entity);
            FosterPetContext.SaveChanges();
        }

        public void Update(TEntity entity)
        {
            FosterPetContext.Set<TEntity>().Update(entity);
            FosterPetContext.SaveChanges();
        }

        public void Remove(TEntity entity)
        {
            FosterPetContext.Remove(entity);
            FosterPetContext.SaveChanges();
        }

        public IEnumerable<TEntity> GetAll()
        {
            return FosterPetContext.Set<TEntity>().ToList();
        }

        public TEntity GetbyId(long Id)
        {
            return FosterPetContext.Set<TEntity>().Find(Id);
        }

        public void Dispose()
        {
            FosterPetContext.Dispose();
        }
    }
}
