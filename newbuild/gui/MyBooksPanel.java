/*
 * Framework written by Brad Johnson
 * NextBooks
 * 2011-2012
 */

package gui;

import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * A panel containing a stack of bookpanels, constructed using details from
 * the user's book collection info.
 * @author Brad
 *
 */
public class MyBooksPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -25250717196528793L;

    /**
     * Construct the panel.
     * THIS IS A PLACEHOLDER
     */
    public MyBooksPanel() {

        /*
         * Stack panels vertically, 1 column
         */
        this.setLayout(new GridLayout(0, 1));

        /*
         * Load the book panels
         */
        this.loadBooks();

    }

    /**
     * Load premade books into the panel.
     * THIS IS A PLACEHOLDER
     */
    private void loadBooks() {

        /*
         * Skeleton implementation
         * In future, this will create book panels from the database/cache
         */
        this.add(new RentedBookPanel("Oxford English Dictionary",
                "Words and stuff.", "some guy", "http://somewhere", 100));
        this.add(new RentedBookPanel("Collins Pocket French Dictionary",
                "French words and stuff.", "un garcon", "http://lesomewherre",
                20));
        this.add(new RentedBookPanel("College Physics", "PHYSICS",
                "Physics person", "httphysics", 3));
        this.add(new RentedBookPanel("A Clockwork Orange",
                "No time for the old in-out in-out love.", "Anthony Burgess",
                "http://droogs", 42));




    }

}
