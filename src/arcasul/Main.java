package arcasul;

/**
 *
 * @author Lydya0103
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
                
        Joc thread = new Joc();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ex) {
        }
    }
}
