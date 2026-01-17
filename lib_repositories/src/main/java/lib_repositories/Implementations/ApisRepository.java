
package lib_repositories.Implementations;

import java.util.HashMap;
import javax.persistence.EntityManagerFactory;
import lib_repositories.Interfaces.IConnection;
import lib_repositories.Interfaces.IApisRepository;
import org.springframework.stereotype.Component;

@Component
public class ApisRepository implements IApisRepository
{
    private IConnection IConnection = null;
    
    public ApisRepository(IConnection iConnection)
    {
        this.IConnection = iConnection;
    }

    @Override
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) 
    {
        this.IConnection.setEntityManagerFactory(entityManagerFactory);
    }
    
    @Override
    public HashMap<String, Object> Select() throws Exception 
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("Protocol", "http://");
        response.put("Host", "localhost");
        
        HashMap<String, Object> hash_select = new HashMap<String, Object>();
        hash_select.put("Request-Type", "Get");
        hash_select.put("Types", ":9056/ssb_services/Types/Select");
        hash_select.put("Products", ":9056/ssb_services/Products/Select");
        hash_select.put("Users", ":9056/ssb_services/Users/Select");
        response.put("Select", hash_select);
        
        HashMap<String, Object> hash_where = new HashMap<String, Object>();
        hash_where.put("Request-Type", "Post");
        hash_where.put("Types", ":9056/ssb_services/Types/Where");
        hash_where.put("Products", ":9056/ssb_services/Products/Where");
        hash_where.put("Users", ":9056/ssb_services/Users/Where");
        response.put("Where", hash_where);
        
        HashMap<String, Object> hash_insert = new HashMap<String, Object>();
        hash_insert.put("Request-Type", "Post");
        hash_insert.put("Types", ":9056/ssb_services/Types/Insert");
        hash_insert.put("Products", ":9056/ssb_services/Products/Insert");
        hash_insert.put("Users", ":9056/ssb_services/Users/Insert");
        response.put("Insert", hash_insert);
        
        HashMap<String, Object> hash_update = new HashMap<String, Object>();
        hash_update.put("Request-Type", "Put");
        hash_update.put("Types", ":9056/ssb_services/Types/Update");
        hash_update.put("Products", ":9056/ssb_services/Products/Update");
        hash_update.put("Users", ":9056/ssb_services/Users/Update");
        response.put("Update", hash_update);
        
        HashMap<String, Object> hash_delete = new HashMap<String, Object>();
        hash_delete.put("Request-Type", "Delete");
        hash_delete.put("Types", ":9056/ssb_services/Types/Update");
        hash_delete.put("Products", ":9056/ssb_services/Products/Update");
        hash_delete.put("Users", ":9056/ssb_services/Users/Update");
        response.put("Delete", hash_delete);
        
        return response;
    }
}