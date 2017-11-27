import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Display all the cards on canvas and the instructions of the game.
 * 
 * Ching Ching Huang
 * 14/11/2016
 */
public class Concentration extends WindowController implements ActionListener
{
    private JButton newButton = new JButton ("New Game"); // new game button
    private JButton cheatButton = new JButton ("Cheat"); //cheat button
    private JButton hideButton = new JButton ("Hide"); // the hide button
    private JPanel buttonPanel; // panel for button 
    private CardCollection cardCollection; //pass in CardCollection class
    private Card firstCard, secondCard; //two cards that the user clicks
    private static final int CARD_TOTAL = 36; //total of the card 
    private char symbolArray [] = new char [36];//the array to hold symbols
    public void begin(){
        setSize (410, 490);// set canvas size
        buttonPanel = button(); //call the button
        add(buttonPanel, BorderLayout.NORTH); //display button on top of canvas
        setSymbols(); //shuffle symbols
        newGame(); //show the game
    }

    public void onMouseClick(Location loc){        
        if(firstCard == null && secondCard == null){//two cards face down
            firstCard = cardCollection.getCardAt(loc); 
            if (firstCard != null){
                firstCard.showText();}
            //if user clicks the first card, show the text of the card
        }else if(secondCard == null){//first card faces up and second faces down
            secondCard = cardCollection.getCardAt(loc);            
            if(secondCard != firstCard && secondCard != null){
                //check if first and second cards are different
                secondCard.showText();
            }else{
                //if they are the same, set second card bakc to null
                secondCard = null;
            }
            //if user clicks the second card, show the text of the card
        } else{ //when both card face up
            if (firstCard.getText() != secondCard.getText()){ //two ifferent cards
                //if two cards are different, both cards facedown on the third click
                firstCard.hideText(); //hide first card's text
                secondCard.hideText(); //hide second card's text
                firstCard = null; //set first card back to null
                secondCard = null;//set second card bakc to null
            }else{ //two same cards
                firstCard.removeACard(); //remove first card from canvas
                secondCard.removeACard(); //remove second card from canvas
                cardCollection.removeCards(firstCard); //remove first card from array
                cardCollection.removeCards(secondCard); //remove second card from array
                firstCard = null; //set first card back to null
                secondCard = null; //set second card bakc to null
            }
        }
    }

    public JPanel button(){
        buttonPanel = new JPanel();// panel for button 
        buttonPanel.add(newButton);
        buttonPanel.add(cheatButton); //add cheat button on panel
        buttonPanel.add(hideButton);//add hide button on panel
        cheatButton.addActionListener (this); //Give chate button instruction
        hideButton.addActionListener (this); //Give hide button instruction
        newButton.addActionListener (this); //Give hide button instruction
        return buttonPanel; 
    }

    public void actionPerformed (ActionEvent event){
        if(event.getSource() == cheatButton){//if cheat button is clicked
            cardCollection.showSymbol(); //show all the symbol
        }else if (event.getSource() == hideButton){//if hide button is clicked
            cardCollection.hideSymbol(); //show all the symbol
        } else{//if new game button is clicked
            newGame(); //create the new game
        }
    }

    public void shuffleSymbol(){
        RandomIntGenerator shuffle = new RandomIntGenerator(0, 35); 
        //random numbers to shuffle
        for (int k = 0; k < 100; k ++){
            //swop symbols
            int charindex1 = shuffle.nextValue();
            int charindex2 = shuffle.nextValue();
            char temp = symbolArray [charindex1];
            symbolArray [charindex1] = symbolArray [charindex2];
            symbolArray [charindex2] = temp;
        }
    }

    public void setSymbols(){
        char c = '\u03B1'; //change the unicode
        for (int j = 0; j < symbolArray.length; j += 2){
            //set two cards to same symbols
            symbolArray [j] = c;
            symbolArray [j+1] = c; //set two same symbols
            c ++;
        }
    }

    public void newGame(){
        shuffleSymbol(); //shuffle all the symbols
        cardCollection = new CardCollection(CARD_TOTAL); //36 cards in the array
        //display all the cards
        int index = 0;
        for (int row = 0; row < 6; row ++){
            for (int col = 0; col < 6; col ++){ 
                char c = symbolArray[index];
                cardCollection.addACard (new Card (c, row,  col, canvas));
                index ++;
            }
        }    
        firstCard = null;
        secondCard = null;
    }
}