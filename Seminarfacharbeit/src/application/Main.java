
package application;

import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.xml.stream.events.EndElement;

import modules.Oscillator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import containers.StandardModuleContainer;
import engine.ModuleContainer;
import engine.SynthesizerEngine;


public class Main extends Application {

	private BorderPane root;
	private Scene scene;


	@Override
	public void start(Stage primaryStage) throws InterruptedException{
		try {

			initLayout();
			createOscillatorInputs();
			initScene();

			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	private void initLayout()
	{
		root = new BorderPane();
	}

	private void initScene()
	{
		scene = new Scene(root, 400, 400);
	}

	private void createOscillatorInputs() throws Exception
	{
		HBox hbox = new HBox();
		TextField frequency = new TextField();
		hbox.getChildren().add(frequency);
		Button applyButton = new Button("Apply");
		hbox.getChildren().add(applyButton);
		root.setTop(hbox);

		SynthesizerEngine parent = new SynthesizerEngine();
		ModuleContainer container = new StandardModuleContainer(parent);

		Oscillator osci = container.getToneModule();
		osci.setFrequency((float) 440);
		osci.setAmplitude(10000);
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 44100; i++)
		{
			System.out.println("I:" + i);
			osci.processSample((short) 1);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);

	}


public static void main(String[] args) {
	launch(args);
}
}
