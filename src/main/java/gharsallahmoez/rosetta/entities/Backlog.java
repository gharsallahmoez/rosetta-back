package gharsallahmoez.rosetta.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Backlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Integer CHSequence = 0 ;

    private String collectionIdentifier;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "collection_id",nullable = false)
    @JsonIgnore
    private Collection collection;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER , mappedBy = "backlog" , orphanRemoval = true)
    private List<Shoe> projectTasks = new ArrayList<>();

    public Backlog() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getCHSequence() {
        return CHSequence;
    }

    public void setCHSequence(Integer CHSequence) {
        this.CHSequence = CHSequence;
    }

    public String getCollectionIdentifier() {
        return collectionIdentifier;
    }

    public void setCollectionIdentifier(String collectionIdentifier) {
        this.collectionIdentifier = collectionIdentifier;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public List<Shoe> getProjectTasks() {
        return projectTasks;
    }

    public void setProjectTasks(List<Shoe> projectTasks) {
        this.projectTasks = projectTasks;
    }
}
