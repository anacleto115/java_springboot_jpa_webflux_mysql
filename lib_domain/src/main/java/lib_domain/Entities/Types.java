
package lib_domain.Entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "types")
public class Types 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id = 0;
    
    @Column(name = "name", nullable = false, length = 150)
    private String Name = null;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Type")
    private List<Products> Products = null;
    
    
    public Types() { }

    
    public void setId(int v) { Id = v; }
    public int getId() { return Id; }
    
    public void setName(String v) { Name = v; }
    public String getName() { return Name; }
    
    
    public void setProducts(List<Products> v) { Products = v; }
    public List<Products> getProducts() { return Products; }
}
