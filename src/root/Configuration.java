package root;

public class Configuration {

    private int density;
    private EntityModel modelType;
    private Sprite sprite;

    public Configuration(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setDensity(int value) {
        density = value;
    }
}
