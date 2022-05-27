/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package responsi;

/**
 *
 * @author achmadmutawazin
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MovieView view = new MovieView();
        MovieModel model = new MovieModel();
        MovieController controller = new MovieController(model,view);
    }
    
}
