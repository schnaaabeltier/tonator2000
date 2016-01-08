package ui.utils;

import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class NumberInputField extends TextField
{
	private float minValue;
	private float maxValue;

	public NumberInputField(float maxValue, float minValue)
	{
		this.maxValue = maxValue;
		this.minValue = minValue;

		setStyle("-fx-focus-color: transparent;");
		textProperty().addListener((observable, oldValue, newValue) ->
		{
			try
			{
				if (Float.valueOf(newValue) < this.minValue || Float.valueOf(newValue) > this.maxValue)
				{
					DropShadow borderGlow = new DropShadow();
					borderGlow.setColor(Color.RED);
					borderGlow.setOffsetX(0f);
					borderGlow.setOffsetY(0f);
					setEffect(borderGlow);
					return;
				}

				setEffect(null);
			}
			catch(NumberFormatException e)
			{
				DropShadow borderGlow = new DropShadow();
				borderGlow.setColor(Color.RED);
				borderGlow.setOffsetX(0f);
				borderGlow.setOffsetY(0f);
				setEffect(borderGlow);
			}
		});
	}

	@Override
	public void replaceText(int start, int end, String text)
	{
		if (validate(text))
		{
			super.replaceText(start, end, text);
		}
	}

	@Override
	public void replaceSelection(String text)
	{
		if (validate(text))
		{
			super.replaceSelection(text);
		}
	}

	private boolean validate(String text)
	{
		return ("".equals(text) || text.matches("[0-9]|\\.|-"));
	}
}