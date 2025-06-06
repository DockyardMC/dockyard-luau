package io.github.dockyardmc.dockyardLuau

import net.hollowcube.luau.LuaState

internal val LuaState.params: Array<out Any>
    get() {
        val notImmutableList = mutableListOf<Any>()

        for (param in 1..this.top) {
            notImmutableList.add(param)
        }

        return notImmutableList.toTypedArray()
    }
