import java.util.Iterator;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wojciech Orzechowski
 */
public class UrlLibrary implements Iterable<String> {

    private LinkedList<String> urls = new LinkedList<String>();

    public int index = 0;

    public UrlLibrary() {
        urls.add("http://caveofprogramming.com");
        urls.add("https://github.com/arcyfelix");
        urls.add("https://www.linkedin.com/in/wojciechorzechowski/");
    }

    @Override
    public Iterator<String> iterator() {
        return urls.iterator();
    }

    public boolean hasNext() {
        return urls.size() > index;
    }

    /*
    public String next() {
        try {
            URL url = new URL(urls.get(index));
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            publicStringBuilder sb = new StringBuilder();
            String line = null;
            while (line = br.readLine() != null) {
                sb.append(line);
                sb.append("");

            }
            br.close();
            index++;
            
        } catch (Exception ex) {
            Logger.getLogger(UrlLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }
     */

    public void removeZeroIndex() {
        urls.remove();
    }
}
