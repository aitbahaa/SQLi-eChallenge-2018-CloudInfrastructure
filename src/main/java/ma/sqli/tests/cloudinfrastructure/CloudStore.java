package ma.sqli.tests.cloudinfrastructure;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class CloudStore {
    private String name;
    private List<Document> documents = new LinkedList<>();

    public CloudStore(String name) {
        this.name = name;
    }

    public void addDocument(Document document){
        this.documents.add(document);

    }

    public String getName() {
        return name;
    }

    public void clearDocuments(){
        this.documents.clear();
    }

    public String printStore(){
        String storeDescription,documentsDescription ;

        if(this.documents.size()>0){
            documentsDescription = documents.stream().map(Document::print).collect(Collectors.joining(", "));
        }
        else{
            documentsDescription = "empty";
        }
        storeDescription = name + ":" + documentsDescription;

        return storeDescription;
    }

    public double usedDisk() {
        return  this.documents.size()*Document.SIZE;
    }
}
