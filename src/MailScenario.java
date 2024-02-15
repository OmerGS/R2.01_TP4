import mail.MailServer;
import mail.MailItem;

public class MailScenario {
    public static void main(String[] args) {
        // Cr\u00E9ation d'une instance de MailServer
        MailServer server = new MailServer();

        // Sc\u00E9nario 1: Envoi d'un mail de John à Alice
        MailItem mail1 = new MailItem("john@example.com", "alice@example.com", "Bonjour Alice, comment vas-tu \uD83D\uDE0A ?");
        server.post(mail1);

        // Sc\u00E9nario 2: Envoi d'un mail de Bob à Alice
        MailItem mail2 = new MailItem("bob@example.com", "alice@example.com", "Salut Alice, as-tu vu le nouveau film ?");
        server.post(mail2);

        // Sc\u00E9nario 3: Envoi d'un mail de Alice à John
        MailItem mail3 = new MailItem("alice@example.com", "john@example.com", "Bonjour John, j'ai bien reçu ton message.");
        server.post(mail3);

        // Sc\u00E9nario 4: Lecture du prochain mail pour Alice
        System.out.println("---------------------------------------------");
        MailItem nextMailForAlice = server.getNextMailItem("alice@example.com");
        if (nextMailForAlice != null) {
            System.out.println("Sc\u00E9nario 4: Prochain mail pour Alice : ");
            nextMailForAlice.print();
        } else {
            System.out.println("Sc\u00E9nario 4: Aucun mail pour Alice.");
        }

        // Sc\u00E9nario 5: R\u00E9ponse à un mail de Alice à John
        MailItem replyMail = new MailItem("alice@example.com", "john@example.com", "Merci John, je vais bien.");
        server.post(replyMail);

        // Sc\u00E9nario 6: Lecture du prochain mail pour John
        System.out.println("---------------------------------------------");
        MailItem nextMailForJohn = server.getNextMailItem("john@example.com");
        if (nextMailForJohn != null) {
            System.out.println("Sc\u00E9nario 6: Prochain mail pour John : ");
            nextMailForJohn.print();
        } else {
            System.out.println("Sc\u00E9nario 6: Aucun mail pour John.");
        }

        // Sc\u00E9nario 7: V\u00E9rification du nombre total de mails
        System.out.println("---------------------------------------------");
        System.out.println("Sc\u00E9nario 7: Nombre total de mails sur le serveur : " + server.howManyMailItems(null));

        // Sc\u00E9nario 8: Lecture de tous les mails pour Alice
        System.out.println("---------------------------------------------");
        System.out.println("Sc\u00E9nario 8: Mails pour Alice : ");
        while (server.howManyMailItems("alice@example.com") > 0) {
            MailItem mailForAlice = server.getNextMailItem("alice@example.com");
            mailForAlice.print();
        }

        // Sc\u00E9nario 9: V\u00E9rification du nombre de mails pour un destinataire nul
        System.out.println("---------------------------------------------");
        System.out.println("Sc\u00E9nario 9: Nombre de mails pour un destinataire nul : " + server.howManyMailItems(null));

        // Sc\u00E9nario 10: Tentative de lecture d'un mail pour un destinataire nul
        System.out.println("---------------------------------------------");
        MailItem nextMailForNull = server.getNextMailItem(null);
        if (nextMailForNull != null) {
            System.out.println("Sc\u00E9nario 10: Prochain mail pour un destinataire nul : ");
            nextMailForNull.print();
        } else {
            System.out.println("Sc\u00E9nario 10: Aucun mail pour un destinataire nul.");
        }

        // Sc\u00E9nario 11: Envoi de plusieurs mails
        System.out.println("---------------------------------------------");
        MailItem[] mails = {
            new MailItem("mallory@example.com", "eve@example.com", "Eve, n'oublie pas notre rendez-vous demain."),
            new MailItem("eve@example.com", "mallory@example.com", "D'accord Mallory, à demain."),
            new MailItem("bob@example.com", "mallory@example.com", "Attention Alice, ne r\u00E9ponds pas à ce mail !")
        };
        for (MailItem mail : mails) {
            server.post(mail);
        }

        // Sc\u00E9nario 12: Lecture de tous les mails pour Mallory
        System.out.println("---------------------------------------------");
        System.out.println("Sc\u00E9nario 12: Mails pour Mallory : ");
        while (server.howManyMailItems("mallory@example.com") > 0) {
            MailItem mailForMallory = server.getNextMailItem("mallory@example.com");
            mailForMallory.print();
        }

        // Sc\u00E9nario 13: V\u00E9rification du nombre total de mails apr\u00E8s lecture
        System.out.println("---------------------------------------------");
        System.out.println("Sc\u00E9nario 13: Nombre total de mails sur le serveur apr\u00E8s lecture : " + server.howManyMailItems(null));

        // Sc\u00E9nario 14: Envoi d'un mail avec message vide
        System.out.println("---------------------------------------------");
        MailItem mailWithEmptyMessage = new MailItem("john@example.com", "alice@example.com", "\uD83D\uDE0A");
        server.post(mailWithEmptyMessage);
        System.out.println("Sc\u00E9nario 14: Mail avec message vide envoy\u00E9.");

        // Sc\u00E9nario 15: Lecture du prochain mail pour Alice apr\u00E8s envoi du mail vide
        System.out.println("---------------------------------------------");
        nextMailForAlice = server.getNextMailItem("alice@example.com");
        if (nextMailForAlice != null) {
            System.out.println("Sc\u00E9nario 15: Prochain mail pour Alice apr\u00E8s envoi du mail vide : ");
            nextMailForAlice.print();
        } else {
            System.out.println("Sc\u00E9nario 15: Aucun mail pour Alice apr\u00E8s envoi du mail vide.");
        }

        // Sc\u00E9nario 16: Envoi d'un mail avec destinataire vide
        System.out.println("---------------------------------------------");
        MailItem mailWithEmptyRecipient = new MailItem("john@example.com", "", "Test destinataire vide");
        server.post(mailWithEmptyRecipient);
        System.out.println("Sc\u00E9nario 16: Mail avec destinataire vide envoy\u00E9.");

        // Sc\u00E9nario 17: V\u00E9rification du nombre total de mails apr\u00E8s envoi du mail avec destinataire vide
        System.out.println("---------------------------------------------");
        System.out.println("Sc\u00E9nario 17: Nombre total de mails sur le serveur apr\u00E8s envoi du mail avec destinataire vide : " + server.howManyMailItems(null));

        // Sc\u00E9nario 18: Envoi d'un mail avec exp\u00E9diteur vide
        System.out.println("---------------------------------------------");
        MailItem mailWithEmptySender = new MailItem("", "alice@example.com", "Test exp\u00E9diteur vide");
        server.post(mailWithEmptySender);
        System.out.println("Sc\u00E9nario 18: Mail avec exp\u00E9diteur vide envoy\u00E9.");

        // Sc\u00E9nario 19: V\u00E9rification du nombre total de mails apr\u00E8s envoi du mail avec exp\u00E9diteur vide
        System.out.println("---------------------------------------------");
        System.out.println("Sc\u00E9nario 19: Nombre total de mails sur le serveur apr\u00E8s envoi du mail avec exp\u00E9diteur vide : " + server.howManyMailItems(null));

        // Sc\u00E9nario 20: Envoi d'un mail avec des caract\u00E8res sp\u00E9ciaux
        System.out.println("---------------------------------------------");
        MailItem mailWithSpecialChars = new MailItem("bob@example.com", "alice@example.com", "Voici un message avec des caract\u00E8res sp\u00E9ciaux : \u00E7\u00E0\u00E8\u00FC");
        server.post(mailWithSpecialChars);
        System.out.println("Sc\u00E9nario 20: Mail avec caract\u00E8res sp\u00E9ciaux envoy\u00E9.");

        // Sc\u00E9nario 21: Lecture du prochain mail pour Alice apr\u00E8s envoi du mail avec caract\u00E8res sp\u00E9ciaux
        System.out.println("---------------------------------------------");
        nextMailForAlice = server.getNextMailItem("alice@example.com");
        if (nextMailForAlice != null) {
            System.out.println("Sc\u00E9nario 21: Prochain mail pour Alice apr\u00E8s envoi du mail avec caract\u00E8res sp\u00E9ciaux : ");
            nextMailForAlice.print();
        } else {
            System.out.println("Sc\u00E9nario 21: Aucun mail pour Alice apr\u00E8s envoi du mail avec caract\u00E8res sp\u00E9ciaux.");
        }

        // Sc\u00E9nario 22: Envoi de plusieurs mails avec des messages vides
        System.out.println("---------------------------------------------");
        MailItem mailWithEmptyMessage1 = new MailItem("mallory@example.com", "bob@example.com", "");
        MailItem mailWithEmptyMessage2 = new MailItem("eve@example.com", "mallory@example.com", "");
        MailItem mailWithEmptyMessage3 = new MailItem("alice@example.com", "eve@example.com", "");
        server.post(mailWithEmptyMessage1);
        server.post(mailWithEmptyMessage2);
        server.post(mailWithEmptyMessage3);
        System.out.println("Sc\u00E9nario 22: Mails avec messages vides envoy\u00E9s.");

        // Sc\u00E9nario 23: Lecture de tous les mails pour Bob
        System.out.println("---------------------------------------------");
        System.out.println("Sc\u00E9nario 23: Mails pour Bob : ");
        while (server.howManyMailItems("bob@example.com") > 0) {
            MailItem mailForBob = server.getNextMailItem("bob@example.com");
            mailForBob.print();
        }

        // Sc\u00E9nario 24: Lecture de tous les mails pour Eve
        System.out.println("---------------------------------------------");
        System.out.println("Sc\u00E9nario 24: Mails pour Eve : ");
        while (server.howManyMailItems("eve@example.com") > 0) {
            MailItem mailForEve = server.getNextMailItem("eve@example.com");
            mailForEve.print();
        }

        // Sc\u00E9nario 25: Tentative de lecture de mails pour un destinataire inexistant
        System.out.println("---------------------------------------------");
        System.out.println("Sc\u00E9nario 25: Mails pour un destinataire inexistant : ");
        while (server.howManyMailItems("inexistant@example.com") > 0) {
            MailItem mailForInexistant = server.getNextMailItem("inexistant@example.com");
            mailForInexistant.print();
        }

        // Sc\u00E9nario 26: Envoi d'un mail avec un message contenant uniquement des espaces
        System.out.println("---------------------------------------------");
        MailItem mailWithSpaces = new MailItem("john@example.com", "bob@example.com", "     ");
        server.post(mailWithSpaces);
        System.out.println("Sc\u00E9nario 26: Mail avec un message contenant uniquement des espaces envoy\u00E9.");

        // Sc\u00E9nario 27: Lecture du prochain mail pour Bob apr\u00E8s envoi du mail avec espaces uniquement
        System.out.println("---------------------------------------------");
        MailItem nextMailForBob = server.getNextMailItem("bob@example.com");
        if (nextMailForBob != null) {
            System.out.println("Sc\u00E9nario 27: Prochain mail pour Bob apr\u00E8s envoi du mail avec espaces uniquement : ");
            nextMailForBob.print();
        } else {
            System.out.println("Sc\u00E9nario 27: Aucun mail pour Bob apr\u00E8s envoi du mail avec espaces uniquement.");
        }

        // Sc\u00E9nario 28: V\u00E9rification du nombre total de mails apr\u00E8s envoi du mail avec espaces uniquement
        System.out.println("---------------------------------------------");
        System.out.println("Sc\u00E9nario 28: Nombre total de mails sur le serveur apr\u00E8s envoi du mail avec espaces uniquement : " + server.howManyMailItems(null));

        // Sc\u00E9nario 29: Lecture de tous les mails sur le serveur apr\u00E8s tous les sc\u00E9narios
        System.out.println("---------------------------------------------");
        System.out.println("Sc\u00E9nario 29: Tous les mails sur le serveur : ");
        while (server.howManyMailItems(null) > 0) {
            MailItem mail = server.getNextMailItem(null);
            mail.print();
        }

        // Sc\u00E9nario 30: V\u00E9rification du nombre total de mails sur le serveur apr\u00E8s tous les sc\u00E9narios
        System.out.println("---------------------------------------------");
        System.out.println("Sc\u00E9nario 30: Nombre total de mails sur le serveur apr\u00E8s tous les sc\u00E9narios : " + server.howManyMailItems(null));
    }
}