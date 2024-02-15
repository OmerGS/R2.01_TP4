package mail;
import java.util.ArrayList;
import java.util.Iterator;

public class MailServer {
    private ArrayList<MailItem> items;

    public MailServer(){
        this.items = new ArrayList<MailItem>();
    }

    public void post(MailItem item){
        if(items != null){
            this.items.add(item);
        } else {
            System.out.println("MailServer_post : Item Nulle");
        }
    }

    /**
     * @param who
     * @return
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
