/*
 * see license.txt 
 */
package seventh.game.type.cmd;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import leola.vm.Leola;
import leola.vm.types.LeoObject;
import seventh.game.type.AbstractGameTypeScript;
import seventh.game.type.GameType;
import seventh.math.Vector2f;
import seventh.shared.Cons;

/**
 * Loads an Commander rules script
 * 
 * @author Tony
 *
 */
public class CommanderScript extends AbstractGameTypeScript {

    /**
     * 
     */
    public CommanderScript(Leola runtime) {
        super(runtime);
    }

    /**
     * @param mapFile
     * @param maxScore
     * @param matchTime
     * @return
     * @throws Exception
     */
    public GameType loadGameType(String mapFile, int maxScore, long matchTime) throws Exception {
        
        List<Vector2f> alliedSpawnPoints = new ArrayList<>();
        List<Vector2f> axisSpawnPoints = new ArrayList<Vector2f>();
        
        
        File scriptFile = new File(mapFile + ".cmd.leola");
        if(!scriptFile.exists()) {
            Cons.println("*** ERROR -> No associated script file for team death match game type.  Looking for: " + scriptFile.getName());
        }
        else {
            LeoObject config = getRuntime().eval(scriptFile);
            if(LeoObject.isTrue(config)) {
                                
                alliedSpawnPoints = loadSpawnPoint(config, "alliedSpawnPoints");
                axisSpawnPoints = loadSpawnPoint(config, "axisSpawnPoints");
            }
        }
        
        GameType gameType = new CommanderGameType(getRuntime(), alliedSpawnPoints, axisSpawnPoints, maxScore, matchTime);
        return gameType;
    }
}
