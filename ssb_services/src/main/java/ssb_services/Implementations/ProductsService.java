
package ssb_services.Implementations;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import lib_domain.Entities.Products;
import lib_repositories.Interfaces.IProductsRepository;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssb_services.Core.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Products")
public class ProductsService
{      
    private Environment environment = null;
    private IProductsRepository IRepository = null;
    
    public ProductsService(Environment environment, IProductsRepository iRepository) 
    {
        this.environment = environment;
        this.IRepository = iRepository;;
        
        EntityManagerFactory entityManagerFactory = Configuration.getEntityManagerFactory(environment);
        this.IRepository.setEntityManagerFactory(entityManagerFactory);
    }
    
    @GetMapping("/Select") 
    public Flux<Products> Select(@RequestBody Products entity) throws Exception
    { 
        List<Products> response = new ArrayList<Products>();
        response = this.IRepository.Select();
        response.forEach((item) -> {
            item.getType().setProducts(new ArrayList<Products>());
        });
        return Flux.fromIterable(response);
    }
    
    @PostMapping("/Where")
    public Mono<List<Products>> Where(@RequestBody Products entity) throws Exception
    {
        List<Products> response = new ArrayList<Products>();
        response = this.IRepository.Where(entity);
        return Mono.just(response);
    }
    
    @PostMapping("/Insert") 
    public Flux<Products> Insert(@RequestBody Products entity) throws Exception 
    { 
        entity = this.IRepository.Insert(entity);
        return Flux.just(entity);
    }
    
    @PutMapping("/Update") 
    public Flux<Products> Update(@RequestBody Products entity) throws Exception
    { 
        entity = this.IRepository.Update(entity);
        return Flux.just(entity);
    }
    
    @DeleteMapping("/Delete") 
    public Flux<Products> Delete(@RequestBody Products entity) throws Exception
    { 
        entity = this.IRepository.Delete(entity);
        return Flux.just(entity);
    }
}