package io.github.dockyardmc.dockyardLuau

import evo.lualayer.annotations.LuauFunction
import net.hollowcube.luau.LuaFunc
import kotlin.reflect.KFunction

@JvmInline
public value class LuauFunctionSet(public val functions: MutableSet<Pair<String, Any>> = mutableSetOf()) {
    public fun withFunction(functionName: String, func: () -> Any) {
        functions.add(functionName to func)
    }
}