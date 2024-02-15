package mail;
import java.util.ArrayList;
import java.util.Iterator;

/**
* Server of our mail services
* @author O.Gunes 
*/
public class MailServer {
    /**
     * ArrayList which contain mails.
     */
    private ArrayList<MailItem> items;

    /**
     * Constructor which initialize Items arraylist
     */
    public MailServer(){
        this.items = new ArrayList<MailItem>();
    }

    /**
     * Allow to post mail at somebody
     * 
     * @param item - The mail
     */
    public void post(MailItem item){
        if(items != null){
            this.items.add(item);
        } else {
            System.out.println("MailServer_post : Item Nulle");
        }
    }

    /**
     * @param who - The persons who wants know how mail he has
     * @return - Number of unread mail
     */
    public int howManyMailItems(String who){
        int compteur = 0;
        if(who == null){
            System.out.println("MailServer_howManyMailItems : Valeur Nulle");
        } else {
            for (MailItem item : items) {
                if(((item.getTo())).equals(who)){
                    compteur++;
                }
            }
        }
        return(compteur);
    }

    /**
    * A method which return you an unread mail
    *
    * @param who - E-mail who had received mail
    * @return - The mail
    */
    public MailItem getNextMailItem(String who){
        MailItem prochainMail = null;

        if(who == null){
            System.out.println("MailServer_getNextMailItem : Valeur Nulle");
        } else {
            if(howManyMailItems(who) > 0){
                boolean found = false;
                Iterator<MailItem> it = this.items.iterator();
                while(it.hasNext() || !found){
                    MailItem nextItem = it.next();
                    if(nextItem.getTo().equals(who)){
                        it.remove();
                        found = true;
                        prochainMail = nextItem;
                    }
                }
            }
        }
        return(prochainMail);
    }
}
