package gharsallahmoez.rosetta.web;
import gharsallahmoez.rosetta.entities.Shoe;
import gharsallahmoez.rosetta.services.MapValidationErrorService;
import gharsallahmoez.rosetta.services.ShoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class ShoeController {
    @Autowired
    private ShoesService shoesService ;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addShoetoBacklog(@Valid @RequestBody Shoe shoe, BindingResult result ,
                                              @PathVariable String backlog_id){

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(errorMap != null) return errorMap;
        Shoe shoe1 = shoesService.addShoe(backlog_id,shoe);
        return new ResponseEntity<Shoe>(shoe1,HttpStatus.CREATED);
    }
    @GetMapping("/{backlog_id}")
    public Iterable<Shoe> getCollectionBacklog(@PathVariable String backlog_id){
        return shoesService.findBacklogById(backlog_id);

    }

    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getShoe(@PathVariable String backlog_id , @PathVariable String pt_id){
        Shoe shoe = shoesService.findByCollectionSequence(backlog_id,pt_id);
        return new ResponseEntity<Shoe>(shoe,HttpStatus.OK);

    }
    @PatchMapping("{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateShoe(@Valid @RequestBody Shoe shoe , BindingResult result,
                                               @PathVariable String backlog_id , @PathVariable String pt_id){
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(errorMap != null) return errorMap;
        Shoe updatedShoe = shoesService.updateByCollectionSequence(shoe,backlog_id,pt_id);
        return new ResponseEntity<Shoe>(updatedShoe,HttpStatus.OK);

    }

    @DeleteMapping("{backlog_id}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id ,@PathVariable String pt_id ){
        shoesService.deleteShoeByCollectionSequence(backlog_id,pt_id);
        return new ResponseEntity<String>("shoe "+pt_id+" was deleted successfully", HttpStatus.OK);
    }
}
