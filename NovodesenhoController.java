/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poofx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author gsc_c
 */
public class NovodesenhoController implements Initializable{
    @FXML
    private TextField largura;
    @FXML
    private TextField altura;
    @FXML
    public String getlargura() {
        return largura.getText();
    }
    @FXML
      public String getaltura() {
        return altura.getText();
    }
     
  
    @FXML
    public void okay(){
          
      }
    @FXML
    public void notokay(){
          
    }    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            
    }
    
}
