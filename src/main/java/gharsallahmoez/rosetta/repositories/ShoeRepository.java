package gharsallahmoez.rosetta.repositories;
import gharsallahmoez.rosetta.entities.Shoe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShoeRepository  extends CrudRepository<Shoe,Long>{
    List<Shoe> findByCollectionIdentifierOrderByCategorie(String id);
    Shoe findByCollectionSequence(String sequence);
}
