package gharsallahmoez.rosetta.exceptions;

public class CollectionIdExceptionResponse {
    private String collectionIdentifier;

    public CollectionIdExceptionResponse(String collectionIdentifier) {
        this.collectionIdentifier = collectionIdentifier;
    }

    public String getCollectionIdentifier() {
        return collectionIdentifier;
    }

    public void setCollectionIdentifier(String collectionIdentifier) {
        this.collectionIdentifier = collectionIdentifier;
    }
}
