package com.duxtinto.intellij.plugin.github.codereviews.helpers.random

import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import io.github.benas.randombeans.registerRandomizersForIdeaClasses
import io.github.benas.randombeans.registerRandomizersForJavaClasses
import io.github.benas.randombeans.registerRandomizersForPluginClasses
import org.jetbrains.debugger.getClassName
import kotlin.reflect.KClass

class RandomGenerator {
    companion object {
        val random: EnhancedRandom = EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .registerRandomizersForPluginClasses()
                .registerRandomizersForJavaClasses()
                .registerRandomizersForIdeaClasses()
                .build()

        @JvmStatic
        inline fun <reified C: Any> next(): C {
            return when {
                C::class.isInstance(Long) -> return random.nextLong() as C
                else -> random.nextObject(C::class.java) as C
            }
        }
    }
}
