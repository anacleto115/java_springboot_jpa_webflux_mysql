
package ut_presentation.Core;

import java.util.Date;
import lib_domain.Entities.Audits;
import lib_domain.Entities.Products;
import lib_domain.Entities.Types;

public class EntitiesCore 
{
    public static Audits Audits()
    {
        Audits entity = new Audits();
        entity.setAction("Unit.Tests");
        entity.setDescription("Pruebas-" + (new Date()).toString());
        entity.setDate(new Date());
        return entity;
    }
        
    public static Types Types()
    {
        Types entity = new Types();
        entity.setName("Pruebas-" + (new Date()).toString());
        return entity;
    }
    
    public static Products Products()
    {
        Types type = new Types();
        type.setId(1);
        
        Products entity = new Products();
        entity.setName("Pruebas-" + (new Date()).toString());
        entity.setPrice(5.0);
        entity.setExpire(new Date());
        entity.setType(type);
        entity.setActive(true);
        entity.setImage("654fgJHGjhdgdhfyi3432d1fg6sd7fg98BFjhdgfsdk5345");
        return entity;
    }
}
