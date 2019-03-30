package gharsallahmoez.rosetta.repositories;

import gharsallahmoez.rosetta.entities.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog,Long> {

    Backlog findByCollectionIdentifier(String collectionIdentifier);

}
