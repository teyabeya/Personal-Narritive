import org.code.theater.*;
import org.code.media.*;
public class MyStory extends Scene{

  private String[][] titles;
  private Color[][] colors;
  private ImageFilter[] pics;

  /*
  constructor, inputs titles, colors, and pictures
  */
  public MyStory(String[][] titles, Color[][] colors, ImageFilter[] pics){
    this.titles = titles;
    this.colors = colors;
    this.pics = pics;
  }

  /*
  determines the indent of the second line of the bottom caption
  by using iterations and boolean expressions, based on the difference of Strings
  */
  public int indent(int index){
    for(int row = 0; row< pics.length; row++){
      String first = titles[row][1];
      String second = titles[row][2];
      int diff = first.compareTo(second);
      diff = Math.abs(diff);
      if((row==index)&&(diff<10)){
        diff+=10;
        diff*=10;
      }else if ((row==index)&&(diff<35)){
        diff*=5;
    }
      if ((row==index)&&(diff>=300)){
        return diff-50;
      }else if (row==index){
        return diff;
      }
    
  }
    return 0;
  }

  /*
  draws scene by calling all following methods sequentially
  */
  public void drawScene(){
    cutImage();
    captureImage();
    collectImage();
    scrapbook();
  }

  /*
  method for the "CUT" image
  sets the stage and other variables,
  then draws image, then redraws with motion blur effect at diagonal length 10,
  draws captions and plays sound
  */
  public void cutImage(){
    ImageFilter cut = pics[0];
    String top = titles[0][0].toUpperCase();
    
    setTextStyle(Font.SANS, FontStyle.BOLD_ITALIC);
    setTextHeight(40);
    setTextColor(colors[0][0]);
    
    clear(colors[0][1]);
    pause(0.5);
    drawImage(cut,0,45,cut.getWidth());
    pause(0.7);
    playSound("camera1.wav");
    cut.motionBlur(10,"diagonal");
    drawImage(cut,0,50,cut.getWidth());
    drawText(top, 40,60);

    setTextStyle(Font.SERIF, FontStyle.NORMAL);
    setTextHeight(30);
    pause(1.0);
    drawText(titles[0][1], 20,350);
    pause(0.2);
    drawText(titles[0][2], indent(0),380);
    pause(2.0);
  }

  /*
  method for the "CAPTURE" image
  sets the stage and other variables,
  then draws image, then redraws with threshold using tan and brown with 100 intensity,
  draws captions and plays sound
  */
  public void captureImage(){
    ImageFilter capture = pics[1];
    String top = titles[1][0].toUpperCase();
    setTextStyle(Font.SANS, FontStyle.BOLD_ITALIC);
    setTextHeight(50);
    setTextColor(colors[1][0]);
    
    clear(colors[1][1]);
    pause(0.5);
    drawImage(capture,0,50,capture.getWidth());
    pause(0.7);
    playSound("camera1.wav");
    capture.threshold(100,Color.TAN, Color.CHOCOLATE);
    drawImage(capture,0,50,capture.getWidth());
    pause(0.2);
    drawText(top, 50,75);

    setTextStyle(Font.SERIF, FontStyle.NORMAL);
    setTextHeight(30);
    pause(1.0);
    drawText(titles[1][1], 20,350);
    pause(0.2);
    drawText(titles[1][2], indent(1),380);
    pause(2.0);
  }

  /*
  method for the "COLLECT" image
  sets the stage and other variables,
  then draws image, then redraws with pixelate with 5x5 dimensions,
  draws captions and plays sound
  */
  public void collectImage(){
    ImageFilter collect = pics[2];
    String top = titles[2][0].toUpperCase();
    setTextStyle(Font.SANS, FontStyle.BOLD_ITALIC);
    setTextHeight(60);
    setTextColor(colors[2][0]);
    
    clear(colors[2][1]);
    pause(0.5);
    drawImage(collect,0,50,collect.getWidth());
    pause(0.7);
    playSound("camera1.wav");
    collect.pixelate(5);
    drawImage(collect,0,50,collect.getWidth());
    pause(0.2);
    drawText(top, 60,90);

    setTextStyle(Font.SERIF, FontStyle.NORMAL);
    setTextHeight(30);
    pause(1.0);
    drawText(titles[2][1], 20,350);
    pause(0.2);
    // drawText(titles[2][2], 150,380);
    drawText(titles[2][2], indent(2),380);
    pause(2.0);
  }

  /*
  method for the "CREATE" image
  sets the stage and other variables,
  then draws image, then redraws with rain effect with length 20,
  draws captions and plays sound and ending fanfare
  */
  public void scrapbook(){
    ImageFilter scrapbook = pics[3];
    String top = titles[3][0].toUpperCase();
    setTextStyle(Font.SANS, FontStyle.BOLD_ITALIC);
    setTextHeight(70);
    setTextColor(colors[3][0]);
    
    clear(colors[3][1]);
    pause(0.5);
    drawImage(scrapbook,0,50,scrapbook.getWidth());
    pause(0.7);
    playSound("camera1.wav");
    scrapbook.rain(20);
    drawImage(scrapbook,0,50,scrapbook.getWidth());
    pause(0.2);
    drawText(top, 80,100);

    setTextStyle(Font.SERIF, FontStyle.NORMAL);
    setTextHeight(30);
    pause(1.0);
    drawText(titles[3][1], 80,360);
    pause(2.0);
    playSound("fanfare_x.wav");
  }
  
}