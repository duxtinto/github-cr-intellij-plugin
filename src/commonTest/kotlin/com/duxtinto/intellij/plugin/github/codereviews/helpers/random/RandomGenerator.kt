package com.duxtinto.intellij.plugin.github.codereviews.helpers.random

import io.github.benas.randombeans.*
import io.github.benas.randombeans.api.EnhancedRandom

class RandomGenerator {
    companion object {
        val random: EnhancedRandom = EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .registerRandomizersForDomainClasses()
                .registerRandomizersForNetClasses()
                .registerRandomizersForJavaClasses()
                .registerRandomizersForIdeaClasses()
                .build()

        @JvmStatic
        inline fun <reified C> next(): C {
            return random.nextObject(C::class.java)
        }
    }
}
