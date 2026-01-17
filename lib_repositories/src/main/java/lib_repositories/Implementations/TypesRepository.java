
package lib_repositories.Implementations;

import java.util.HashMap;
import lib_domain.Entities.Audits;
import lib_domain.Entities.Types;
import lib_repositories.Interfaces.IConnection;
import lib_repositories.Interfaces.ITypesRepository;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import lib_repositories.Interfaces.IAuditsRepository;
import org.springframework.stereotype.Component;

@Component
public class TypesRepository implements ITypesRepository
{
    private IConnection IConnection = null;
    private IAuditsRepository IAuditsRepository = null;
    
    public TypesRepository(IConnection iConnection, IAuditsRepository iAuditsRepository)
    {
        this.IConnection = iConnection;
        this.IAuditsRepository = iAuditsRepository;
    }

    @Override
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) 
    {
        this.IConnection.setEntityManagerFactory(entityManagerFactory);
    }

    @Override
    public List<Types> Select() throws Exception
    {
        List list = this.IConnection.Select(Types.class);
        this.IAuditsRepository.Insert(
            new Audits("Types.Select", ""));
        return list;
    }

    @Override
    public List<Types> Where(Types entity) throws Exception
    {
        if (entity.getName() == null)
            throw new Exception("lbMissingInformation");
        
        String where = "SELECT x FROM Types x WHERE x.Name LIKE CONCAT('%',:name,'%')";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", entity.getName());
        List list = this.IConnection.Where(Types.class, where, parameters);
        this.IAuditsRepository.Insert(
            new Audits("Types.Where", ""));
        return list;
    }

    @Override
    public Types Insert(Types entity) throws Exception
    {
        if (Validate(entity))
            throw new Exception("lbMissingInformation");

        if (entity.getId() != 0)
            throw new Exception("lbWasSaved");

        entity = (Types)this.IConnection.Insert(entity);
        this.IAuditsRepository.Insert(
            new Audits("Types.Insert", String.valueOf(entity.getId())));
        return entity;
    }

    @Override
    public Types Update(Types entity) throws Exception
    {
        if (Validate(entity))
            throw new Exception("lbMissingInformation");

        if (entity.getId() == 0)
            throw new Exception("lbWasNotSaved");

        entity = (Types)this.IConnection.Update(entity);
        this.IAuditsRepository.Insert(
            new Audits("Types.Update", String.valueOf(entity.getId())));
        return entity;
    }

    @Override
    public Types Delete(Types entity) throws Exception
    {
        if (Validate(entity))
            throw new Exception("lbMissingInformation");

        if (entity.getId() == 0)
            throw new Exception("lbWasNotSaved");

        entity = (Types)this.IConnection.Delete(Types.class,entity.getId());
        this.IAuditsRepository.Insert(
            new Audits("Types.Delete", String.valueOf(entity.getId())));
        return entity;
    }

    private boolean Validate(Types entity) 
    {
        return entity == null ||
            entity.getName() == null;
    }
}