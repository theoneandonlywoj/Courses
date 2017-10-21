/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wojciech Orzechowski
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UrlLibrary urlLibrary = new UrlLibrary();
        

        for (String url : urlLibrary) {
            System.out.println(url);
            System.out.println("The length: " + url.length());
            System.out.println("");
        }
    }

}
