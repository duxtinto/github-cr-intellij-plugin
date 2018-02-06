package com.duxtinto.intellij.plugin.github.codereviews.helpers.random

import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import io.github.benas.randombeans.registerRandomizersForIdeaClasses
import io.github.benas.randombeans.registerRandomizersForJavaClasses
import io.github.benas.randombeans.registerRandomizersForPluginClasses

class RandomGenerator {
    companion object {
        val random: EnhancedRandom = EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .registerRandomizersForPluginClasses()
                .registerRandomizersForJavaClasses()
                .registerRandomizersForIdeaClasses()
                .build()

        @JvmStatic
        inline fun <reified C> next(): C {
            return random.nextObject(C::class.java)
        }
    }
}
