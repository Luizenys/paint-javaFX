
package poofx;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.imageio.ImageIO;


/**
 *
 * @author gsc_c
 */
public class FXMLDocumentController implements Initializable {
    private ObservableList<String> items =FXCollections.observableArrayList ("Camada 1");
    private double acph =0,acpw = 0;
    private String textodesenho;

    private   ArrayList<Canvas> camadas = new ArrayList();
    private   ArrayList<Canvas> desfazer = new ArrayList();
    private   ArrayList<Canvas> refazer = new ArrayList();
    private int escolhas;
    private int layerchoice = 2;
    @FXML
    private ScrollPane mudemude;
    @FXML
    private ListView laiers;
    @FXML
    private Button teste;
    @FXML
    private Canvas drawble;
    @FXML
    private Label tamanho;
    @FXML
    private Spinner<Integer> siz;
    @FXML
    private Canvas copycanvas;
    @FXML
    private ColorPicker selecionarcor;
    @FXML
    private Canvas previacor;
    @FXML
    private Slider opacidade;
    @FXML
    private ScrollPane scrondo;
    @FXML
    private Label acompanharopacidad;
    @FXML
    private TextField texto;
    @FXML
    private Pane testess;
    @FXML
    private AnchorPane controlandopainel;
    @FXML    
    private void criarcamada(ActionEvent event){
        items.add("Camada "+Integer.toString(layerchoice));
        layerchoice+=1;
        laiers.setItems(items);
    }
    @FXML
    private void deletarcamada(ActionEvent event){
        items.remove(items.size()-1);
        layerchoice-=1;
        laiers.setItems(items);
    }    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       GraphicsContext gc = drawble.getGraphicsContext2D();    
       desenhar(gc);    
  
    }
    @FXML
    private void undo(ActionEvent event){
        if(desfazer.isEmpty()){
            
            
        }else{ 
        add2();        
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);        
        Image a = (desfazer.get(desfazer.size()-1)).snapshot(params, null);
        drawble.getGraphicsContext2D().drawImage(a, 0, 0);    
        desfazer.remove(desfazer.size()-1);
        atualizarprevia();
       
        }
        
       
    }
    @FXML
    private void redo(ActionEvent event){
        if(refazer.isEmpty()){
            
            
        }else{ 
        adicionarnoarray();
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);        
        Image a = (refazer.get(refazer.size()-1)).snapshot(params, null);
        drawble.getGraphicsContext2D().drawImage(a, 0, 0);    
        refazer.remove(refazer.size()-1);
        atualizarprevia();
       
        }
       
    }    
    @FXML
    private void zoommais(){
     
     
    }
    @FXML
    private void zoommenos(){
        
    }
    @FXML //Para o label do lado da caixa de opacidade acompanhe o valor do Slider
    private void acompanharopacidade(MouseEvent eve){
        acompanharopacidad.setText(String.valueOf((int)opacidade.getValue())+"%");

    }
    @FXML //Função para quando mudar o colorpicker o painel de prévia de cor também mude
    private void mudarcor(){
      System.out.println(selecionarcor.getValue());
      GraphicsContext gc2 = previacor.getGraphicsContext2D();
      gc2.setLineWidth(5.0);
      gc2.setFill(selecionarcor.getValue());
      gc2.fillRect(0,0,previacor.getWidth(),previacor.getHeight());
    }

    public void desenhar(GraphicsContext gc){
       
         }    
    @FXML
    private void salvar2(){
        File file = new File("CanvasImage2.png");
        WritableImage wim;
        wim = new WritableImage((int)previacor.getHeight(), (int) previacor.getWidth());
        previacor.snapshot(null, wim);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
        } catch (Exception s) {
        }
    }
    @FXML
    private void salvar(){
        File file = new File("CanvasImage.png");
        WritableImage wim;
        wim = new WritableImage((int)drawble.getWidth(), (int) drawble.getHeight());
        drawble.snapshot(null, wim);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
        } catch (Exception s) {
        }
    }

    @FXML
    private void escolhipincel() {
          escolhas = 1;
          String ala = "oi";
  
    }
    @FXML
    private void escolhiapagar() {
  
          escolhas = 2;
          
    }    
    @FXML
    private void escolhiquadrado(){
        escolhas = 3;
    }
    @FXML
    private void escolhicirculo(){
        escolhas = 4;
    }
    @FXML
    private void escolhireta(){
        escolhas = 5;

    }
    @FXML 
    private void escolhitexto(){
        escolhas = 6;
    }
    @FXML
    private void escolhipintar(){
        escolhas = 7;
    }
    private int[][] preencherarraycor(BufferedImage imagem){
        int h = imagem.getHeight();
        int w = imagem.getWidth();
       
        int[][] pixels = new int[w][h];
        for( int i = 0; i < w; i++ )
            for( int j = 0; j < h; j++ )
                pixels[i][j] = imagem.getRGB( i, j );
        return pixels;
    }
    @FXML
    private void desenharquadrado(MouseEvent eve,GraphicsContext gc, double altura,double largura){
        adicionarnoarray();   
        if(altura>acph && largura>acpw){
            
        gc.strokeRect(acpw, acph, Math.abs(acpw-largura), Math.abs(acph-altura));    
        }
        else if(altura>acph){
        gc.strokeRect(eve.getX(), acph, acpw-largura, altura-acph);           
        }else if(largura>acpw){
        gc.strokeRect(acpw, eve.getY(), largura-acpw, acph-altura);                    
         }
        else{
        gc.strokeRect(eve.getX(), eve.getY(), acpw-largura, acph-altura);    
        }
        
        acph=0;
        acpw=0;        
    }
    private void desenharcirculo(MouseEvent eve,GraphicsContext gc, double altura,double largura){
       adicionarnoarray(); 
     if(altura>acph && largura>acpw){
            System.out.println("oi");
        gc.strokeOval(acpw, acph, Math.abs(acpw-largura), Math.abs(acph-altura));    
        }
        else if(altura>acph){
        gc.strokeOval(eve.getX(), acph, acpw-largura, altura-acph);           
        }else if(largura>acpw){
        gc.strokeOval(acpw, eve.getY(), largura-acpw, acph-altura);                    
         }
        else{
        gc.strokeOval(eve.getX(), eve.getY(), acpw-largura, acph-altura);    
        }
        
        acph=0;
        acpw=0;    
        
    }  
    private void desenharreta(MouseEvent eve,GraphicsContext gc, double altura,double largura){
       adicionarnoarray(); 
     gc.strokeLine(acpw, acph, largura, altura);
        acph=0;
        acpw=0;    
        
    }        
    @FXML void adicionarnoarray(){ //REFAZER
        
        System.out.println("tam"+desfazer.size());
        SnapshotParameters params = new SnapshotParameters();
        Canvas auxiliar = new Canvas();
        auxiliar.setWidth(drawble.getWidth());
        auxiliar.setHeight(drawble.getHeight());
        Image a = drawble.snapshot(params, null);
        auxiliar.getGraphicsContext2D().drawImage(a, 0, 0);
        desfazer.add(auxiliar);
        
 
    }
    @FXML void add2(){ //REFAZER
        
        System.out.println("tamd"+refazer.size());
        SnapshotParameters params = new SnapshotParameters();
        Canvas auxiliar = new Canvas();
        auxiliar.setWidth(drawble.getWidth());
        auxiliar.setHeight(drawble.getHeight());
        Image a = drawble.snapshot(params, null);
        auxiliar.getGraphicsContext2D().drawImage(a, 0, 0);
        refazer.add(auxiliar);
        
 
    }    
 @FXML
    private void quadrado(MouseEvent eve, GraphicsContext gc){
           
        gc.strokeRect(eve.getX(), eve.getY(), 1, 1);
    }
    @FXML
    private void desenhartexto(MouseEvent eve, GraphicsContext gc){
        adicionarnoarray(); 
        
       if("".equals(texto.getText())){
             gc.strokeText("null", eve.getX(), eve.getY(),1300); 
       }
       else{
             gc.strokeText(texto.getText(), eve.getX(), eve.getY(),1300); 
       }
    }  
    @FXML
    private void pintei(MouseEvent eve, GraphicsContext gc) throws IOException{
        SnapshotParameters params = new SnapshotParameters();    
        Image a = drawble.snapshot(params, null);
        int arraycomcor[][];
        salvar();
        File imgPath = new File("CanvasImage.png");
        BufferedImage asd = ImageIO.read(imgPath);
        arraycomcor = preencherarraycor(asd);        
        /* o que eu tenho nesse momento é um array onde cada ponto é uma cor do meu canvas*/       

        int x = (int)eve.getX();
        int y = (int)eve.getY();
        int previacor = arraycomcor[x][y];  
        salvar2();
        File imgPath2 = new File("CanvasImage2.png");
        BufferedImage ass = ImageIO.read(imgPath2);
        int cornova = ass.getRGB(1, 1);        
        pinteifill(arraycomcor,x,y,cornova,previacor);        
        atualizarpintura(asd,arraycomcor);
        Image imagse = SwingFXUtils.toFXImage(asd, null);
        drawble.getGraphicsContext2D().drawImage(imagse, 0, 0);
        
    }
    @FXML
    private void atualizarpintura(BufferedImage asd, int[][] arai){
        int h = asd.getHeight();
        int w = asd.getWidth();
        int[][] pixels = new int[w][h];
        for( int i = 0; i < w; i++ )
            for( int j = 0; j < h; j++ )
                asd.setRGB( i, j, arai[i][j] );
       
    }
    @FXML //TARGET  COR = previacor
    private void pinteifill(int scree[][],int x, int y,int cornova,int previacor){
        if(previacor == cornova) return;
        if(scree[x][y]!=previacor) return;    
        
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(x,y));
        while(!queue.isEmpty()){
            
            Point p = queue.remove();
            if((p.x>0)&& scree[p.x-1][p.y] == previacor){
                scree[p.x-1][p.y] = cornova;
                queue.add(new Point(p.x-1,p.y));
                }
            if((p.x<drawble.getWidth()-15) && scree[p.x+1][p.y] == previacor){
                scree[p.x+1][p.y] = cornova;
                queue.add(new Point(p.x+1,p.y));}
            if((p.y<drawble.getHeight()-15) && scree[p.x][p.y+1] == previacor){
                scree[p.x][p.y+1] = cornova;
                queue.add(new Point(p.x,p.y+1));}
            
            if((p.y>0) && scree[p.x][p.y-1] == previacor){
                scree[p.x][p.y-1] = cornova;
                queue.add(new Point(p.x,p.y-1));  }              
            }
        
        
        //Código abaixo só funciona para figuras pequenas(stackoverflow)
      /*   if (x < 0 || x >= drawble.getWidth() || y < 0 || y >= drawble.getHeight()) return;
           
        scree[x][y] = cornova;
        pinteifill(scree,x+1,y,cornova,previacor);
        pinteifill(scree,x-1,  y, cornova,previacor);
        pinteifill( scree,x, y+1 ,cornova,previacor);
        pinteifill( scree, x, y-1,cornova,previacor);*/
    }
    @FXML
    private void novodesenho(ActionEvent eve){
           try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Novodesenho.fxml"));
    

        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Novo Desenho");
        stage.setScene(scene);
        
        stage.show();
   
        
        
    } catch (IOException e) {  }

    }
  
    
    @FXML
    private void apagar(MouseEvent eve,GraphicsContext gc) {  
               
        System.out.println("aaaaaaaaaaaaaa");
        gc.clearRect(eve.getX(),eve.getY(),2.5*gc.getLineWidth(), 2.5*gc.getLineWidth());         
        
          
    }
    @FXML
    private void pegarteclas(KeyEvent eve){
        if(escolhas == 5)
        textodesenho += eve.getText();
    }
    @FXML
    private void pincel(MouseEvent eve,GraphicsContext gc) {
      
          gc.beginPath();
          gc.moveTo(eve.getX(), eve.getY());
          gc.stroke();
          gc.closePath();              
          
    }       
    @FXML
    private void pincel1(MouseEvent eve,GraphicsContext gc) {     
          adicionarnoarray(); 
          gc.beginPath();
          gc.moveTo(eve.getX(), eve.getY());
          gc.stroke();           
          
    }   
    @FXML
    private void pincel2(MouseEvent eve,GraphicsContext gc) {     
          gc.lineTo(eve.getX(), eve.getY());  
          gc.stroke();
          gc.closePath();
          gc.beginPath();
          gc.moveTo(eve.getX(), eve.getY());
          
    }   
    @FXML
    private void pincel3(MouseEvent eve,GraphicsContext gc) {     
          gc.lineTo(eve.getX(), eve.getY());  
          gc.stroke();
          gc.closePath();  
          
    }   
    @FXML //Para quando o mouse clickar no canvas
    private void desenharmaolivre(MouseEvent eve) throws IOException{
          GraphicsContext gc = drawble.getGraphicsContext2D();
          gc.setLineWidth(siz.getValue());
          gc.setGlobalAlpha(opacidade.getValue()/100);
          gc.setStroke(selecionarcor.getValue());     
          switch (escolhas) {
              case 1:pincel(eve,gc); 
                break;
              case 2:apagar(eve,gc);
                break;
              case 3:quadrado(eve,gc);
              break;
              case 6:
                  desenhartexto(eve,gc);
                  break;
              case 7:
                  pintei(eve,gc);
          }  
          atualizarprevia();
          
    }    
    @FXML //Para quando o mouse pressionar o canvas(diferente de clickar)
    private void desenharmaolivre1(MouseEvent eve){
          System.out.println(eve.getX());
          System.out.println(eve.getY());
          GraphicsContext gc = drawble.getGraphicsContext2D();
          gc.setLineWidth(siz.getValue());
          gc.setGlobalAlpha(opacidade.getValue()/100);
          gc.setStroke(selecionarcor.getValue());     
          switch (escolhas) {
              case 1:pincel1(eve,gc); 
                break;
              case 2:apagar(eve,gc);
                break;
              case 3:acph = eve.getY();
                     acpw=eve.getX();
                break;
              case 4:acph = eve.getY();
                     acpw=eve.getX();
                break;  
              case 5:
                  acph = eve.getY();
                  acpw = eve.getX();
                  break;
                
          }  
        atualizarprevia();
    
    }
    @FXML //Para quando você arrastar com o mouse clickado
    private void desenharmaolivre2(MouseEvent eve){
          GraphicsContext gc = drawble.getGraphicsContext2D();
          gc.setLineWidth(siz.getValue());
          gc.setGlobalAlpha(opacidade.getValue()/100);
          gc.setStroke(selecionarcor.getValue());     
          switch (escolhas) {
              case 1:pincel2(eve,gc); 
                break;
              case 2: apagar(eve,gc);
                  adicionarnoarray(); 
                break;
          }  
          atualizarprevia();
    }
   @FXML //Para quando você soltar o mouse clickado
    private void desenharmaolivre3(MouseEvent eve){
        
          GraphicsContext gc = drawble.getGraphicsContext2D();
          gc.setLineWidth(siz.getValue());
        
          gc.setGlobalAlpha(opacidade.getValue()/100);
          gc.setStroke(selecionarcor.getValue());     
          switch (escolhas) {
              case 1:pincel3(eve,gc); 
                break;
              case 2: 
              apagar(eve,gc);
                break;
              case 3:
                desenharquadrado(eve,gc,eve.getY(),eve.getX());
                break;
              case 4:
                  desenharcirculo(eve,gc,eve.getY(),eve.getX());
                  break;
              case 5:
                  desenharreta(eve,gc,eve.getY(),eve.getX());    
          }   atualizarprevia();
              
    } 
    public Image scale(Image source, int targetWidth, int targetHeight, boolean preserveRatio) {
    ImageView imageView = new ImageView(source);
    imageView.setPreserveRatio(preserveRatio);
    imageView.setFitWidth(targetWidth);
    imageView.setFitHeight(targetHeight);
    return imageView.snapshot(null, null);
    }
    @FXML
    private void atualizarprevia(){
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);        
        Image a = drawble.snapshot(params, null);

        Image ajuda =  scale(a,173,159,true);
        copycanvas.getGraphicsContext2D().drawImage(ajuda, 0, 0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            GraphicsContext copia = copycanvas.getGraphicsContext2D();
            GraphicsContext gc = drawble.getGraphicsContext2D();
            gc.setLineWidth(5.0);
           
            gc.setFill(Color.WHITE);
            gc.fillRect(0,0,drawble.getWidth(),drawble.getHeight());
            copia.setLineWidth(5.0);
            copia.setFill(Color.WHITE);
            copia.fillRect(0,0,drawble.getWidth(),drawble.getHeight());
            GraphicsContext gc2 = previacor.getGraphicsContext2D();
            gc2.setLineWidth(5.0);
            
            gc2.setFill(selecionarcor.getValue());
            gc2.fillRect(0,0,previacor.getWidth(),previacor.getHeight());
            
            opacidade.setMin(0);
            opacidade.setMax(100);
            opacidade.setValue(100);    
            
            escolhipincel();
            selecionarcor.setValue(Color.BLACK);
            mudarcor();
            laiers.setItems(items);
            
// TODO
    }        
}
