
package ut_presentation.Connections;

import lib_domain.Entities.Products;
import lib_repositories.Implementations.Connection;
import lib_repositories.Interfaces.IConnection;
import java.util.List;
import org.junit.Test;
import org.springframework.util.Assert;
import ut_presentation.Core.EntitiesCore;

public class ProductsTest 
{
    private IConnection IConnection = null;
    private List<Products> list = null;
    private Products entity = null;

    public ProductsTest() throws Exception
    {
        IConnection = new Connection();
    }
    
    @Test
    public void Execute()
    {
        Assert.isTrue(Insert());
        Assert.isTrue(Update());
        Assert.isTrue(Select());
        Assert.isTrue(Delete());
    }
    
    private boolean Insert()
    {
        this.entity = EntitiesCore.Products();
        this.entity = (Products)this.IConnection.Insert(this.entity);
        return true;
    }
    
    private boolean Update()
    {
        this.entity.setActive(false);
        this.entity = (Products)this.IConnection.Update(this.entity);
        return true;
    }
    
    private boolean Select()
    {
        this.list = this.IConnection.Select(Products.class);
        return !list.isEmpty();
    }
    
    private boolean Delete()
    {
        this.entity = (Products)this.IConnection.Delete(
            Products.class, this.entity.getId());
        return true;
    }
}