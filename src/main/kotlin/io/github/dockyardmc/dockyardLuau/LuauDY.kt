package io.github.dockyardmc.dockyardLuau

import net.hollowcube.luau.LuaFunc
import net.hollowcube.luau.LuaState
import java.lang.reflect.InvocationTargetException
import kotlin.reflect.jvm.ExperimentalReflectionOnLambdas
import kotlin.reflect.jvm.reflect

/**
 * Provide an API for using Luau in Dockyard.
 */
public class LuauDY {
    @Throws(
        java.lang.NoSuchMethodException::class,
        IllegalArgumentException::class,
        IllegalAccessException::class,
        InvocationTargetException::class,
        NullPointerException::class,
        ExceptionInInitializerError::class
    )
    public fun registerMethod(clazz: Class<Any>, methodName: String) {
        val method = clazz.getMethod(methodName)

        val params = method.parameters

        LuaFunc.preallocate { state: LuaState ->
            val instance = state.checkUserDataArg(1, clazz.simpleName)
            if (params.size == state.top) {
                method.invoke(instance, state.params)
            } else throw IllegalArgumentException("Number of parameters are not matching!")

            return@preallocate 1
        }
    }


    public val state: LuaState = LuaState.newState()

    public fun registerFunction(functionName: String, func: () -> Unit) {
        val function = LuaFunc.preallocate { _: LuaState ->
            func()

            return@preallocate 1
        }

        // I have no idea what I'm doing
        state.pushCFunction({ funcState: LuaState ->
            if (state.checkStringArg(2) == functionName)
                funcState.pushCFunction(function, functionName)
            else
                funcState.pushNil()

            return@pushCFunction 1
        }, functionName)
    }

    /** Register an event to dockyard written in a file written in lua.
     * @param path The path of the file.
     * **/
    public fun registerEvent(path: String) {
//        LuaState.newState().checkUserDataArg()
        TODO()
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