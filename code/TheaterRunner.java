import org.code.theater.*;
import org.code.media.*;

public class TheaterRunner {
  public static void main(String[] args) {
    
    String[][] titles = {{"cut", "pictures out of magazines","or item packaging"},
                           {"capture", "moments with a camera","and print them out"},
                         {"collect", "a lot of pictures, scraps,","and memories"},
                            {"create", "a scrapbook spread!",""}};

    Color[][] colors = {{Color.BLACK, Color.TEAL},
                        {Color.WHITE, Color.BROWN},
                        {Color.BLACK, Color.GRAY},
                        {Color.WHITE, Color.PURPLE}};

    ImageFilter[] pics = {new ImageFilter("cut.jpg"),
                        new ImageFilter("polaroid.jpg"),
                        new ImageFilter("collect.jpg"),
                        new ImageFilter("scrapbook.jpg")};
    
    MyStory scene = new MyStory(titles, colors, pics);

    
    scene.drawScene();
    Theater.playScenes(scene);

  }
}