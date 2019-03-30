package gharsallahmoez.rosetta.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Shoe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false , unique = true)
    private String collectionSequence ;

    @NotBlank(message = "le nom du chaussure est obligatoire")
    private String shoeName;
    private String shoeDescription;
    // EN_STOCK , CONSTRUCTION , LIVRAISON
    private String status;
    //categorie enfant , homme , femme
    private Integer categorie ;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="backlog_id", updatable = false , nullable = false)
    @JsonIgnore
    private Backlog backlog ;
    @Column(updatable = false)
    private String collectionIdentifier;
    private Date create_At;
    private Date update_At;

    public Shoe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollectionSequence() {
        return collectionSequence;
    }

    public void setCollectionSequence(String collectionSequence) {
        this.collectionSequence = collectionSequence;
    }

    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }

    public String getShoeDescription() {
        return shoeDescription;
    }

    public void setShoeDescription(String shoeDescription) {
        this.shoeDescription = shoeDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCategorie() {
        return categorie;
    }

    public void setCategorie(Integer categorie) {
        this.categorie = categorie;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    public String getCollectionIdentifier() {
        return collectionIdentifier;
    }

    public void setCollectionIdentifier(String collectionIdentifier) {
        this.collectionIdentifier = collectionIdentifier;
    }

    public Date getCreate_At() {
        return create_At;
    }

    public void setCreate_At(Date create_At) {
        this.create_At = create_At;
    }

    public Date getUpdate_At() {
        return update_At;
    }

    public void setUpdate_At(Date update_At) {
        this.update_At = update_At;
    }
}
