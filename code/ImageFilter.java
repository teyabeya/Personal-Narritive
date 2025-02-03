import org.code.theater.*;
import org.code.media.*;

public class ImageFilter extends ImagePlus{

  /*
  constructor method
  sets the superclass filename to the specified filename
  */
  public ImageFilter(String filename){
    super (filename);
  }

  /*
  pixelate filter
  gets the average colors of a gridSize x gridSize square on the image
  sets each pixel in each square to that color
  */
   public void pixelate(int gridSize) {

    Pixel[][] pixels = getImagePixels();
    for (int row = 0; row < pixels.length; row += gridSize) {
      for (int col = 0; col < pixels[0].length; col += gridSize) {
        int endRow = Math.min(row + gridSize, pixels.length);
        int endCol = Math.min(col + gridSize, pixels[0].length);
        int avgRed = 0;
        int avgGreen = 0;
        int avgBlue = 0;

        for (int regionRow = row; regionRow < endRow; regionRow++) {
          for (int regionCol = col; regionCol < endCol; regionCol++) {
            avgRed += pixels[regionRow][regionCol].getRed();
            avgGreen += pixels[regionRow][regionCol].getGreen();
            avgBlue += pixels[regionRow][regionCol].getBlue();
          }
        }

        int totalPixelsInRegion = (endRow - row) * (endCol - col);
        avgRed /= totalPixelsInRegion;
        avgGreen /= totalPixelsInRegion;
        avgBlue /= totalPixelsInRegion;

        for (int regionRow = row; regionRow < endRow; regionRow++) {
          for (int regionCol = col; regionCol < endCol; regionCol++) {
            pixels[regionRow][regionCol].setRed(avgRed);
            pixels[regionRow][regionCol].setGreen(avgGreen);
            pixels[regionRow][regionCol].setBlue(avgBlue);
          }
        }
      }
    }
    
  }

  /*
  motion blur filter
  gets average colors in length size line of horizontal, vertical, or horizontal pixels
  sets average colors to current pixel
  */
  public void motionBlur(int length, String direction) {

    Pixel[][]pixels = getPixelsFromImage();

    for (int row = 0; row< pixels.length;row++){
      for (int col = 0; col<pixels[0].length;col++){
        
        int reds = 0;
        int blues =0;
        int greens =0;
        int count = 0;
        int x = col;
        int y = row;
        while ((count<length)&&(x<pixels[0].length) && (y<pixels.length)){
        Pixel currentPixel = pixels[y][x];
        reds += currentPixel.getRed();
        blues += currentPixel.getBlue();
        greens += currentPixel.getGreen();
          count++;
        
        if (direction.equals("horizontal")){
           x++;
        } else if (direction.equals("vertical")){
          y++;
        } else if (direction.equals("diagonal")){
          x++;
          y++;
        }
        }
        int avgRed = reds/count;
        int avgGreen = greens/count;
        int avgBlue = blues/count;

        Pixel currentPixel = pixels[row][col];
          currentPixel.setRed(avgRed);
          currentPixel.setGreen(avgGreen);
          currentPixel.setBlue(avgBlue);
          
      }
    }
  }

  /*
  threshold filter - (tweaked after lesson: parameters instead of black and white)
  gets the average color number for each pixel
  if it is under the value, then pixel is color1, otherwise pixel is color2
  */
  public void threshold(int value, Color color1, Color color2) {
    
    Pixel[][]pixels = getPixelsFromImage();
    int average = 0;

    for (int row = 0; row< pixels.length;row++){
      for (int col = 0; col<pixels[0].length;col++){
        Pixel currentPixel = pixels[row][col];

        average = (int)(currentPixel.getRed() + currentPixel.getBlue() + currentPixel.getGreen())/3;

        if (average<value){
          currentPixel.setColor(color2);
          
        }else{
          currentPixel.setColor(color1);
        }
        
      }
    }
  }


  /*
  rain filter (drags color form pixel down specified size)
  randomly chooses pixel in a column
  color above it is now the color of the current pixel and the rest until length is the correct size
  thickness of 2 pixels (same is repeated for the line of pixels directly next to it for thickness)
  */
  public void rain(int size){
    Pixel[][]pixels = getPixelsFromImage();
    int half = (int)((pixels.length/2.0));
    int amt = (int)(pixels.length/half);
    int counter = 0;

    for (int col = 0; col< pixels[0].length-1;col+=(int)(Math.random()*10)){
      for (int i = 0; i < amt; i++){
        int random = (int)(Math.random()*pixels.length);
        if (((random-1)>=0) && ((random+size)<pixels.length)){
          //current pixel must be >=0 and less than thelength
          Pixel current1 = pixels[random][col];
          Pixel current2 = pixels[random][col+1];
          // current.setColor(Color.BLACK);
          Pixel before1 = pixels[random-1][col];
          Pixel before2 = pixels[random-1][col+1];
          counter=0;
          while(counter<size){
            
            current1.setColor(before1.getColor());
            current2.setColor(before2.getColor());
            random++;
            current1 = pixels[random][col];
            current2 = pixels[random][col+1];
            counter++;
          }
        }
      }
    }
  }
}