package com.duxtinto.intellij.plugin.github.codereviews.helpers.random

import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import io.github.benas.randombeans.registerRandomizersForIdeaClasses
import io.github.benas.randombeans.registerRandomizersForJavaClasses
import io.github.benas.randombeans.registerRandomizersForPluginClasses
import kotlin.reflect.KClass

class RandomGenerator {
    companion object {
        private val random: EnhancedRandom = EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .registerRandomizersForPluginClasses()
                .registerRandomizersForJavaClasses()
                .registerRandomizersForIdeaClasses()
                .build()

        @JvmStatic
        fun <C : Any> nextObject(clazz : KClass<in C>): C {
            return random.nextObject(clazz.java) as C
        }

        @JvmStatic
        fun nextLong(): Long {
            return random.nextLong()
        }
    }
}
