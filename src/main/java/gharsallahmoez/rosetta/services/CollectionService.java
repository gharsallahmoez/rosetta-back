package gharsallahmoez.rosetta.services;

import gharsallahmoez.rosetta.entities.Backlog;
import gharsallahmoez.rosetta.entities.Collection;
import gharsallahmoez.rosetta.exceptions.CollectionIdException;
import gharsallahmoez.rosetta.repositories.BacklogRepository;
import gharsallahmoez.rosetta.repositories.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public Collection saveOrUpdateCollection(Collection collection){
        try{
            collection.setCollectionIdentifier(collection.getCollectionIdentifier().toUpperCase());
            if(collection.getId()==null){
                Backlog backlog = new Backlog();
                collection.setBacklog(backlog);
                backlog.setCollection(collection);
                backlog.setCollectionIdentifier((collection.getCollectionIdentifier().toUpperCase()));
            }
            if(collection.getId()!= null){
                collection.setBacklog(backlogRepository.findByCollectionIdentifier(collection.getCollectionIdentifier().toUpperCase()));
            }
            return collectionRepository.save(collection);
        }catch (Exception e){
            throw new CollectionIdException("Collection ID '" + collection.getCollectionIdentifier().toUpperCase()+"' already exist");
        }
    }

    public Collection findCollectionByIdentifier(String collectionId){
        Collection collection =  collectionRepository.findByCollectionIdentifier(collectionId.toUpperCase());
        if(collection==null)throw new CollectionIdException("Collection ID '"+collectionId +"' does not exist");
        return collection;
    }

    public Iterable<Collection> findAllCollections(){
        return collectionRepository.findAll();
    }
    public void deleteCollectionByIdentifier(String collectionid){
        Collection collection = collectionRepository.findByCollectionIdentifier(collectionid.toUpperCase());
        if(collection == null) {
            throw  new CollectionIdException("cannot delete Collection Id '"+ collectionid +"'. This Collection does not exist");
        }
        collectionRepository.delete(collection);
    }

}
