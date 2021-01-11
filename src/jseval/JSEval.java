package jseval;

import arc.util.*;
import mindustry.gen.*;
import mindustry.mod.*;

import static mindustry.Vars.*;

public class JSEval extends Plugin {
	@Override
	public void registerClientCommands(CommandHandler handler) {
		handler.<Player>register("js", "<code...>", "Execute JavaScript code.", (args, player) -> {
			if (player.admin) {
				 String output = mods.getScripts().runConsole(args[0]);
				 player.sendMessage("> " + (isError(output) ? "[#ff341c]" + output : output));
			} else {
				player.sendMessage("[scarlet]You must be an admin to use this command.");
			}
		});
	}

	private boolean isError(String output) {
		try {
			String errorName = output.substring(0, output.indexOf(' ') - 1);
			Class.forName("rhino." + errorName);
			return true;
		} catch (Throwable e) {
			return false;
		}
	}
}
