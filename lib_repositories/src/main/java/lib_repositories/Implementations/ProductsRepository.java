
package lib_repositories.Implementations;

import java.util.HashMap;
import lib_domain.Entities.Audits;
import lib_domain.Entities.Products;
import lib_repositories.Interfaces.IConnection;
import lib_repositories.Interfaces.IProductsRepository;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import lib_repositories.Interfaces.IAuditsRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductsRepository implements IProductsRepository
{
    private IConnection IConnection = null;
    private IAuditsRepository IAuditsRepository = null;
    
    public ProductsRepository(IConnection iConnection, IAuditsRepository iAuditsRepository)
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
    public List<Products> Select() throws Exception
    {
        HashMap<String, Object> inner = new HashMap<String, Object>();
        inner.put("0", "Type");
        
        List list = this.IConnection.Join(Products.class, inner);
        this.IAuditsRepository.Insert(
            new Audits("Products.Select", ""));
        return list;
    }

    @Override
    public List<Products> Where(Products entity) throws Exception
    {
        if (entity.getName() == null)
            throw new Exception("lbMissingInformation");
        
        String where = "SELECT x FROM Products x WHERE x.Name LIKE CONCAT('%',:name,'%')";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", entity.getName());
        List list = this.IConnection.Where(Products.class, where, parameters);
        this.IAuditsRepository.Insert(
            new Audits("Products.Where", ""));
        return list;
    }

    @Override
    public Products Insert(Products entity) throws Exception
    {
        if (Validate(entity))
            throw new Exception("lbMissingInformation");

        if (entity.getId() != 0)
            throw new Exception("lbWasSaved");

        entity = (Products)this.IConnection.Insert(entity);
        this.IAuditsRepository.Insert(
            new Audits("Products.Insert", String.valueOf(entity.getId())));
        return entity;
    }

    @Override
    public Products Update(Products entity) throws Exception
    {
        if (Validate(entity))
            throw new Exception("lbMissingInformation");

        if (entity.getId() == 0)
            throw new Exception("lbWasNotSaved");

        entity = (Products)this.IConnection.Update(entity);
        this.IAuditsRepository.Insert(
            new Audits("Products.Update", String.valueOf(entity.getId())));
        return entity;
    }

    @Override
    public Products Delete(Products entity) throws Exception
    {
        if (Validate(entity))
            throw new Exception("lbMissingInformation");

        if (entity.getId() == 0)
            throw new Exception("lbWasNotSaved");

        entity = (Products)this.IConnection.Delete(Products.class,entity.getId());
        this.IAuditsRepository.Insert(
            new Audits("Products.Delete", String.valueOf(entity.getId())));
        return entity;
    }

    private boolean Validate(Products entity) 
    {
        return entity == null ||
            entity.getName() == null ||
            entity.getExpire() == null;
    }
}