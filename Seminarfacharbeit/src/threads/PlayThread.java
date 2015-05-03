package threads;

import modules.OutputModule;

public class PlayThread extends Thread 
{

	private OutputModule outputModule;

	public PlayThread(OutputModule module)
	{
		outputModule = module;
	}
	
	public void run()
	{
		outputModule.startPlaying();
	}

}