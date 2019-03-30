package gharsallahmoez.rosetta.exceptions;

public class CollectionNotFoundExceptionResponse {
    private String collectionNotFound ;

    public CollectionNotFoundExceptionResponse(String collectionNotFound) {
        this.collectionNotFound = collectionNotFound;
    }

    public String getCollectionNotFound() {
        return collectionNotFound;
    }

    public void setCollectionNotFound(String collectionNotFound) {
        this.collectionNotFound = collectionNotFound;
    }
}
