import objectdraw.*;
import java.awt.*;
/**
 * Collect all the information of the card array in this class
 * 
 * Ching Ching Huang 
 * 14/11/2016
 */
public class CardCollection
{
    private Card [] cardArray; //array of cards

    private int lastCard; //last created card
    public CardCollection (int cardTotal){
        cardArray = new Card [cardTotal]; //an array of total crads amount
        lastCard = 0; // the last card that created
    }

    public void addACard(Card c){  
        //keep track of the last card that created
        cardArray [lastCard] = c;
        lastCard ++;
    }

    public void showSymbol(){
        //show every text on the card
        for ( int i = 0; i < cardArray.length; i ++){
            if (cardArray [i] != null){ //chekc if the card exists
                cardArray [i].showText();//show the text if the card exists
            }
        }
    }
    //user click the card and show the text
    public Card getCardAt (Location loc) {
        Card returnCard = null;
        for (int index = 0 ; index < cardArray.length; index ++){
            //go through every card to check 
            if(cardArray [index] != null && cardArray [index].contains(loc)){
                cardArray [index].showText(); //if cards contain mouse click, text shows
                returnCard = cardArray [index];                
            }            
        }  
        return returnCard;
    }

    public void removeCards(Card c){        
        for (int j = 0; j < cardArray.length; j ++){     
            if(cardArray [j] == c){
                cardArray [j] = null; //set the removed card to null
            }     
        }   
    }   

    public void hideSymbol(){
        //show every text on the card
        for ( int i = 0; i < cardArray.length; i ++){
            if (cardArray [i] != null){ //chekc if the card exists
                cardArray [i].hideText(); //if the cards exits, hide the text of the card
            }
        }
    }
}
