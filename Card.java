import java.awt.*;
import objectdraw.*;
/**
 *Construct an individual card in this class
 * 
 * Ching Ching Huang
 * 14/11/2016
 */
public class Card
{
    private Text cardText; //the text
    private FilledRect card; //the card
    private char symbol;
    public Card (char symbol, int left, int top, DrawingCanvas canvas){
        //create rect
        int cardSize = 50; //size of cards
        int space = 15; //spacing among cards
        this.symbol = symbol;

        card = new FilledRect (space + (left * (cardSize + space)),
            space + (top * (cardSize + space)), cardSize, cardSize, canvas); 
            //the card

        Color tifBlue = new Color ( 0, 220, 200); //color of card
        card.setColor(tifBlue); //change the color of card

        //create text on the card
        cardText = new Text( symbol, 0, 0, canvas);// create text 
        cardText.setFontSize (20); //make the text larger                   
        cardText.moveTo (card.getX() + card.getWidth()/2 - cardText.getWidth()/2, 
            card.getY() + card.getHeight()/2 - cardText.getHeight()/2); 
        //move the text to the middle of the card

        hideText(); //hide every symbol                 
    }

    public void showText(){
        cardText.show();  //show all the text on the card
    }
    //check if cards contains mouse click
    public boolean contains(Location loc){
        return (card.contains(loc));
    }

    public void hideText(){
        cardText.hide();// hide the text
    }

    public void removeACard(){
        card.removeFromCanvas(); //remove the filledRect from canvas
        cardText.removeFromCanvas(); //remove the text of the card from canvas
    }

    public char getText(){
        //get the text of the card
        return symbol; 
    }
}