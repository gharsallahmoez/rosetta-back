package gharsallahmoez.rosetta.services;
import gharsallahmoez.rosetta.entities.Backlog;
import gharsallahmoez.rosetta.entities.Collection;
import gharsallahmoez.rosetta.entities.Shoe;
import gharsallahmoez.rosetta.exceptions.CollectionNotFoundException;
import gharsallahmoez.rosetta.repositories.BacklogRepository;
import gharsallahmoez.rosetta.repositories.CollectionRepository;
import gharsallahmoez.rosetta.repositories.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service
public class ShoesService {
    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ShoeRepository shoeRepository;
    @Autowired
    private CollectionRepository collectionRepository;

    public Shoe addShoe(String collectionIdentifier, Shoe shoe){
        try{
            Backlog backlog = backlogRepository.findByCollectionIdentifier(collectionIdentifier.toUpperCase());
            shoe.setBacklog(backlog);
            Integer BacklogSequence = backlog.getCHSequence();
            BacklogSequence++;
            backlog.setCHSequence(BacklogSequence);
            shoe.setCollectionSequence(collectionIdentifier+"-"+BacklogSequence);
            shoe.setCollectionIdentifier(collectionIdentifier.toUpperCase());
            if(shoe.getCategorie()==null){
                shoe.setCategorie(3);
            }
            if(shoe.getStatus()==""|| shoe.getStatus()==null){
                shoe.setStatus("EN_STOCK");
            }
            return shoeRepository.save(shoe);
        }catch (Exception e){
            throw new CollectionNotFoundException("Collection not found");
        }
    }

    public Iterable<Shoe>findBacklogById(String backlog_id) {
        Collection collection = collectionRepository.findByCollectionIdentifier(backlog_id.toUpperCase());

        if(collection==null){
            throw new CollectionNotFoundException("collection with ID: '"+backlog_id+" 'does not exist");
        }

        return shoeRepository.findByCollectionIdentifierOrderByCategorie(backlog_id.toUpperCase());
    }

    public Shoe findByCollectionSequence(String backlog_id , String pt_id){
        Backlog backlog = backlogRepository.findByCollectionIdentifier(backlog_id.toUpperCase());
        if(backlog == null) {
            throw new CollectionNotFoundException("Collection with ID: '"+backlog_id+" 'does not exist");
        }
        Shoe shoe = shoeRepository.findByCollectionSequence(pt_id);
        if(shoe == null) {
            throw new CollectionNotFoundException("shoe with ID: '"+pt_id+" 'does not exist");
        }
        if(!shoe.getCollectionIdentifier().equals(backlog_id)){
            throw new CollectionNotFoundException("shoe with ID: '"+pt_id+" 'does not exist in project: '"+backlog_id+"'");
        }
        return shoe;
    }


    public Shoe updateByCollectionSequence(Shoe updatedShoe,String backlog_id, String pt_id){
        Shoe shoe = findByCollectionSequence(backlog_id , pt_id);
        shoe = updatedShoe ;
        return shoeRepository.save(shoe);
    }


    public  void deleteShoeByCollectionSequence(String backlog_id,String pt_id){
        Shoe shoe = findByCollectionSequence(backlog_id.toUpperCase() , pt_id);
        shoeRepository.delete(shoe);
    }

}
