package petApp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pet {
    //TODO: owner, age, month сделать необязательными, реализовать для этого PetBuilder
    private StringProperty kind;
    private StringProperty name;
    private StringProperty owner;
    private IntegerProperty age;
    private IntegerProperty month;

    public Pet(String kind, String name, String owner, int age, int month) {
        setKind(kind);
        setName(name);
        setOwner(owner);
        setAge(age);
        setMonth(month);
    }

    public StringProperty kindStringProperty() {
        if (kind == null)
            kind = new SimpleStringProperty();
        return kind;
    }
    public StringProperty nameStringProperty() {
        if (name == null)
            name = new SimpleStringProperty();
        return name;
    }
    public StringProperty ownerStringProperty() {
        if (owner == null)
            owner = new SimpleStringProperty();
        return owner;
    }

    public IntegerProperty ageIntegerProperty() {
        if (age == null)
            age = new SimpleIntegerProperty();
        return age;
    }

    public IntegerProperty monthIntegerProperty() {
        if (month == null)
            month = new SimpleIntegerProperty();
        return month;
    }

    public void setKind(String value) {
        kindStringProperty().set(value);
    }

    public void setName(String value) {
        nameStringProperty().set(value);
    }

    public void setOwner(String value) {
        ownerStringProperty().set(value);
    }

    public void setAge(int value) {
        ageIntegerProperty().set(value);
    }

    public void setMonth(int value) {
        monthIntegerProperty().set(value);
    }

    public String getKind() {
        return kindStringProperty().get();
    }

    public String getName() {
        return nameStringProperty().get();
    }

    public String getOwner() {
        return ownerStringProperty().get();
    }

    public int getAge() {
        return ageIntegerProperty().get();
    }

    public int getMonth() {
        return monthIntegerProperty().get();
    }
}
