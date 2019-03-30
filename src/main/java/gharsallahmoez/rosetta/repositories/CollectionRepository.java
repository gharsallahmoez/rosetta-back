package gharsallahmoez.rosetta.repositories;

import gharsallahmoez.rosetta.entities.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends CrudRepository<Collection,Long> {
    Collection findByCollectionIdentifier(String collectionId);

    @Override
    Iterable<Collection> findAll();
}
