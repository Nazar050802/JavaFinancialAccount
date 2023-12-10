package cz.cuni.mff.mozharon.financialaccounting.domain.entities;

public class Category  {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category(){}

    public Category(String name){
        setName(name);
    }
}
