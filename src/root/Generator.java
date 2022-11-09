package root;

import java.util.ArrayList;

public interface Generator {
    Generator generate(Sprite sprite, int dispersion, int density);
    Generator generate(ArrayList<Sprite> sprites, int dispersion, int density);
    Model getLand();
    void dispose();
}
