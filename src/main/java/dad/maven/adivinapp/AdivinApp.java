package dad.maven.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {
	
	private int intentos = 0;
	private Button comprobarButton;
	private Label textoPrincipio;
	private TextField introduccionNumero;
	private int numeroAleatorio = (int) ((Math.random() * (100 - 0)) + 0);


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Botones
		comprobarButton = new Button("Comprobar");
		comprobarButton.setDefaultButton(true);
		comprobarButton.setOnAction(this::onComprobarAction);
			
		
		Label textoPrincipio = new Label();
		textoPrincipio.setText("Introduce un número del 1 al 100");
		
		introduccionNumero = new TextField();
		
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		root.getChildren().addAll(textoPrincipio, introduccionNumero, comprobarButton);
		root.setFillWidth(false);
		

		
		//BorderPane root = new BorderPane();
		//root.setCenter(textoPrincipio);
		//root.setSpacing(15);
		//root.getChildren().addAll(textoPrincipio);
		
		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(new Scene(root, 360, 240));
		primaryStage.show();
	}
	
	private void onComprobarAction(ActionEvent e) {
		
		//Conversion TextField -> String
		String numeroTexto = introduccionNumero.getText();
		
		//Comprobaciones variable
        boolean resultado;

        try {
            Integer.parseInt(numeroTexto);
            resultado = true;
        } catch (NumberFormatException excepcion) {
        	System.out.println("Letras no permitidas");
            resultado = false;
        }
	
		if (resultado = true && numeroTexto.length() != 0){
			
			//Conversion String -> Int
			int numeroComparacion =  Integer.parseInt(numeroTexto);
			
			if (numeroComparacion > 0 && numeroComparacion < 100) {
				
				if (numeroComparacion == numeroAleatorio) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("AdivinApp");
					alert.setHeaderText("¡Has ganado!");
					alert.setContentText("Sólo has necesitado " + intentos + " intentos."
							+ "Vuelve a jugar y hazlo mejor. Numero Aleatorio --> " + numeroAleatorio);
					ButtonType aceptarButton = new ButtonType("Aceptar");
					alert.getButtonTypes().setAll(aceptarButton);
					alert.showAndWait();
					
					this.intentos = 0;
					this.numeroAleatorio = (int) ((Math.random() * (100 - 0)) + 1);
					}
				
				else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("AdivinApp");
					alert.setHeaderText("¡Has fallado!");
					
						if (numeroComparacion > numeroAleatorio) {
							alert.setContentText("El número a adivinar es menor que " + numeroComparacion + "." + 
									" "
									+ "Vuelve a intentarlo. Numero Aleatorio --> " + numeroAleatorio);
						this.intentos++;
						}
						
						else {
							alert.setContentText("El número a adivinar es mayor que " + numeroComparacion + "." + 
									" "
									+ "Vuelve a intentarlo. Numero Aleatorio --> " + numeroAleatorio);
						}
						
					ButtonType aceptarButton = new ButtonType("Aceptar");
					alert.getButtonTypes().setAll(aceptarButton);
					alert.showAndWait();
					this.intentos++;
				}
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("Error");
				alert.setContentText("El número introducido no es válido");
				ButtonType aceptarButton = new ButtonType("Aceptar");
				alert.getButtonTypes().setAll(aceptarButton);
				alert.showAndWait();
				this.intentos++;
			}	
		}	
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("AdivinApp");
			alert.setHeaderText("Error");
			alert.setContentText("El número introducido no es válido");
			ButtonType aceptarButton = new ButtonType("Aceptar");
			alert.getButtonTypes().setAll(aceptarButton);
			alert.showAndWait();
			this.intentos++;
		}
}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
