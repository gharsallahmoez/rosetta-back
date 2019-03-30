package gharsallahmoez.rosetta.web;
import gharsallahmoez.rosetta.entities.Collection;
import gharsallahmoez.rosetta.services.CollectionService;
import gharsallahmoez.rosetta.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/collection")
@CrossOrigin
public class CollectionController {

    @Autowired
    private CollectionService collectionService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;


    @PostMapping("")
    public ResponseEntity<?> createNewCollection(@Valid @RequestBody Collection collection , BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(errorMap != null) return  errorMap;

        Collection collection1 = collectionService.saveOrUpdateCollection(collection);
        return new ResponseEntity<Collection>(collection1,HttpStatus.CREATED);
    }


    @GetMapping("/{collectionId}")
    public ResponseEntity<?> getCollectionById(@PathVariable String collectionId){
        Collection collection = collectionService.findCollectionByIdentifier(collectionId);
        return new ResponseEntity<Collection>(collection, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Collection> getAllCollections(){return collectionService.findAllCollections();}


    @DeleteMapping("/{collectionId}")
    public ResponseEntity<?> deleteCollection(@PathVariable String collectionId){
        collectionService.deleteCollectionByIdentifier(collectionId);
        return new ResponseEntity<String>("collectionId with Id : '"+collectionId+"' was deleted",HttpStatus.OK);
    }
}
