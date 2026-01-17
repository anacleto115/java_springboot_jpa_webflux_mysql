
package lib_repositories.Implementations;

import javax.persistence.EntityManagerFactory;
import lib_domain.Entities.Audits;
import lib_repositories.Interfaces.IConnection;
import lib_repositories.Interfaces.IAuditsRepository;
import org.springframework.stereotype.Component;

@Component
public class AuditsRepository implements IAuditsRepository
{
    private IConnection IConnection = null;
    
    public AuditsRepository(IConnection iConnection)
    {
        this.IConnection = iConnection;
    }

    @Override
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) 
    {
        this.IConnection.setEntityManagerFactory(entityManagerFactory);
    }
    
    @Override
    public Audits Insert(Audits entity) throws Exception
    {
        if (entity == null)
            throw new Exception("lbMissingInformation");

        if (entity.getId() != 0)
            throw new Exception("lbWasSaved");

        entity = (Audits)this.IConnection.Insert(entity);
        return entity;
    }
}