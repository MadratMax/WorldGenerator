package root;

public interface ICharacter {

    Direction decideDirection();

    int decideDistance();

    Direction lastDirection();

    void move();

    void cutTree(Sprite treeSprite);

    void lookAround();

    void setCurrentPosition();

    void avoidObstacle();
}
