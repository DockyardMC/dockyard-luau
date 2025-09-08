package io.github.dockyardmc.dockyardLuau

import evo.lualayer.annotations.LuauFunction
import evo.lualayer.setup.LuauConfig
import evo.lualayer.spawn
import evo.lualayer.wrapper.LuauLib
import evo.lualayer.wrapper.State
import java.io.File

/**
 * Provide an API for using Luau in Dockyard.
 */
public class LuauDockyard(
    private val scriptPaths: Set<String>,
    private val globalRegisteredFunctions: LuauFunctionSet
) {

    private val luauState: State = State(
        LuauConfig(
            scriptPaths,
        ),
    )

    public suspend fun runScript(script: LuauFile) {
        luauState.spawn { thread ->
            thread.load(script.name).run()
        }
    }

    public suspend fun compileRunScript(script: LuauFile) {
        luauState.spawn { thread ->
            thread.hotload(File(script.name))
        }
    }

    /** Register events in the specified `directoryPath` and the specified `regex`. Matches every file in the folder with the lua or luau extension per default.
     *
     * @param directoryPath The path of the directory/folder where to find the lua code files.
     * @param regex An optional regular expression that the files must follow to be included.
     */
    public fun registerEvents(directoryPath: String, regex: String = """(?i)\S*(\.lua)u?""") {
        TODO()
    }
}