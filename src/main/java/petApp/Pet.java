package petApp;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class Pet {
    //TODO: owner, year, month сделать необязательными, реализовать для этого PetBuilder
    private StringProperty kind;
    private StringProperty name;
    private StringProperty owner;
    private IntegerProperty year;
    private IntegerProperty month;

    public Pet(String kind, String name, String owner, int year, int month) {
        setKind(kind);
        setName(name);
        setOwner(owner);
        setYear(year);
        setMonth(month);
    }

    public Pet(JsonElement jsonPet) throws Exception {
        Gson parser = new Gson();
        HashMap<String, String> pet = parser.fromJson(jsonPet, HashMap.class);
        setKind(pet.get("kind"));
        setName(pet.get("name"));
        setOwner(pet.get("owner"));
        setYear(Integer.parseInt(pet.get("year")));
        setMonth(Integer.parseInt(pet.get("month")));
    }

    public boolean isCorrect() {
        return getName().matches("[A-Za-z]+") &&
                getOwner().matches("[A-Za-z]+") &&
                getKind().matches("[A-Za-z]+");
    }

    public double getAge() {
        return BigDecimal.valueOf(getYear() + getMonth() / 12.0).
                setScale(2, RoundingMode.HALF_UP).doubleValue();
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

    public IntegerProperty yearIntegerProperty() {
        if (year == null)
            year = new SimpleIntegerProperty();
        return year;
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

    public void setYear(int value) {
        yearIntegerProperty().set(value);
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

    public int getYear() {
        return yearIntegerProperty().get();
    }

    public int getMonth() {
        return monthIntegerProperty().get();
    }
}
