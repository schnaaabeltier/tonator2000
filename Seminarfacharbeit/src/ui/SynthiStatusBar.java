package ui;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import modules.listener.EngineListener;

import org.controlsfx.control.StatusBar;

import resources.Strings;
import engine.SynthesizerEngine;

public class SynthiStatusBar extends StatusBar implements EngineListener
{

	private Button sampleRateLabel;
	private Button latencyLabel;
	private Button midiDeviceLabel;
	private Button currProgramLabel;
	private Button engineRunningLabel;

	private SynthesizerEngine engine;

	private MainApplication parent;

	public SynthiStatusBar(SynthesizerEngine engine, MainApplication parent)
	{
		super();
		this.engine = engine;
		this.parent = parent;
		engine.addListener(this);
		initElements();
		update();
	}

	public void update()
	{
		sampleRateLabel.setText(Float.toString(engine.getSamplingRate()));
		latencyLabel.setText(Double.toString(engine.getBufferTime()) + "s");

		if (engine.getConnectedMidiDevice() != null)
		{
			midiDeviceLabel.setText(engine.getConnectedMidiDevice().getDeviceInfo().getName());
			midiDeviceLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(2), new Insets(4))));
		}
		else 
		{
			midiDeviceLabel.setText(Strings.STATUSBAR_NO_DEVICE_STRING);
			midiDeviceLabel.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(2), new Insets(4))));
		}

		currProgramLabel.setText(engine.getProgramManager().getInstrumentName(parent.getCurrProgram()));

		if (engine.isRunning())
		{
			engineRunningLabel.setText(Strings.STATUSBAR_RUNNING_STRING);
			engineRunningLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(2), new Insets(4))));
		}
		else 
		{
			engineRunningLabel.setText(Strings.STATUSBAR_NOT_RUNNING_STRING);
			engineRunningLabel.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(2), new Insets(4))));
		}
	}

	private void initElements()
	{
		sampleRateLabel = new Button();
		engineRunningLabel = new Button();
		latencyLabel = new Button();
		midiDeviceLabel = new Button();
		currProgramLabel = new Button();

		sampleRateLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(2), new Insets(4))));
		latencyLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(2), new Insets(4))));
		midiDeviceLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(2), new Insets(4))));
		currProgramLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(2), new Insets(4))));
		engineRunningLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(2), new Insets(4))));
		
		getLeftItems().add(sampleRateLabel);
		getLeftItems().add(new Separator(Orientation.VERTICAL));
		getLeftItems().add(latencyLabel);
		getLeftItems().add(new Separator(Orientation.VERTICAL));
		getLeftItems().add(engineRunningLabel);
		getLeftItems().add(new Separator(Orientation.VERTICAL));

		getRightItems().add(new Separator(Orientation.VERTICAL));
		getRightItems().add(midiDeviceLabel);
		getRightItems().add(new Separator(Orientation.VERTICAL));
		getRightItems().add(currProgramLabel);
		
		setText("");
	}

	@Override
	public void onValueChanged() 
	{
		update();
	}

}
