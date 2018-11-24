package ma.sqli.tests.cloudinfrastructure;

class Document {
    private String name;

    public Document(String name) {
        this.name = name;
    }

    public String print() {
        return name;
    }

    public static final double SIZE=0.100;
}
