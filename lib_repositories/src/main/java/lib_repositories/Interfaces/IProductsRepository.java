
package lib_repositories.Interfaces;

import lib_domain.Entities.Products;
import java.util.List;
import javax.persistence.EntityManagerFactory;

public interface IProductsRepository 
{
    void setEntityManagerFactory(EntityManagerFactory entityManagerFactory);
    
    List<Products> Select() throws Exception;
    List<Products> Where(Products entity) throws Exception;
    Products Insert(Products entity) throws Exception;
    Products Update(Products entity) throws Exception;
    Products Delete(Products entity) throws Exception;
}