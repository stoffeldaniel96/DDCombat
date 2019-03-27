package creature;

public enum Size {
    TINY, SMALL, MEDIUM, LARGE, HUGE, GARGANTUAN;

    @Override
    public String toString() {
        String[] words = this.name().split("_");
        String size = "";
        for (String word : words) {
            size += word.substring(0, 1) + word.substring(1).toLowerCase() + " ";
        }
        size = size.substring(0, size.length() - 1);
        return size;
    }
}
