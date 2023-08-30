public class PetItem {

    private String petName;
    private String petAnimalType;

    public PetItem(String petName, String petAnimalType) {
        this.petName = petName;
        this.petAnimalType = petAnimalType;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetAnimalType() {
        return petAnimalType;
    }

    public void setPetAnimalType(String petAnimalType) {
        this.petAnimalType = petAnimalType;
    }
}
