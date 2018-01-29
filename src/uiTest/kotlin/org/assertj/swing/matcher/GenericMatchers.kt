package org.assertj.swing.matcher

import org.assertj.swing.core.GenericTypeMatcher
import java.awt.Component

inline fun <reified T: Component> withName(name: String): GenericTypeMatcher<T> {
   return object : GenericTypeMatcher<T>(T::class.java) {
       override fun isMatching(component: T): Boolean {
           return component.name == name
       }
   }
}