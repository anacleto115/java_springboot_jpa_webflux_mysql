
package ut_presentation.Repositories;

import lib_domain.Entities.Products;
import java.util.List;
import lib_repositories.Interfaces.IConnection;
import lib_repositories.Implementations.Connection;
import lib_repositories.Interfaces.IProductsRepository;
import lib_repositories.Implementations.ProductsRepository;
import lib_repositories.Implementations.AuditsRepository;
import org.junit.Test;
import org.springframework.util.Assert;
import ut_presentation.Core.EntitiesCore;

public class ProductsTest 
{
    private IProductsRepository IRepository = null;
    private List<Products> list = null;
    private Products entity = null;

    public ProductsTest() throws Exception
    {
        IConnection IConnection = new Connection();
        IRepository = new ProductsRepository(IConnection, 
            new AuditsRepository(IConnection));
    }
    
    @Test
    public void Execute() throws Exception
    {
        Assert.isTrue(Insert());
        Assert.isTrue(Update());
        Assert.isTrue(Select());
        Assert.isTrue(Where());
        Assert.isTrue(Delete());
    }
    
    private boolean Select() throws Exception
    {
        this.list = this.IRepository.Select();
        return !list.isEmpty();
    }
    
    private boolean Where() throws Exception
    {
        this.list = this.IRepository.Where(this.entity);
        return !list.isEmpty();
    }
    
    private boolean Insert() throws Exception
    {
        this.entity = EntitiesCore.Products();
        this.entity = (Products)this.IRepository.Insert(this.entity);
        return true;
    }
    
    private boolean Update() throws Exception
    {
        this.entity.setActive(false);
        this.entity = (Products)this.IRepository.Update(this.entity);
        return true;
    }
    
    private boolean Delete() throws Exception
    {
        this.entity = (Products)this.IRepository.Delete(this.entity);
        return true;
    }
}