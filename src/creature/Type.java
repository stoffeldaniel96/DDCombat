package creature;

public enum Type {
    ABERRATION, BEAST, CELESTIAL, CONTRUCT, DRAGON, ELEMENTAL, FEY, FIEND, GIANT, HUMANOID, MONSTROSITY, OOZE, PLANT, UNDEAD;

    @Override
    public String toString() {
        String[] words = this.name().split("_");
        String type = "";
        for (String word : words) {
            type += word.substring(0, 1) + word.substring(1).toLowerCase() + " ";
        }
        type = type.substring(0, type.length() - 1);
        return type;
    }
}