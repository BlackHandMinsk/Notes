package com.example.notes

import android.app.Application
import android.app.Service
import android.content.Context
import com.example.notes.room.NotesDatabase
import com.example.notes.room.Repository
import kotlin.reflect.KClass

class App:Application() {


    override fun onCreate() {
        super.onCreate()

        ServiceLocator.register<Context>(this)
        ServiceLocator.register(NotesDatabase.create(locate()))
        ServiceLocator.register(Repository(locate()))
    }

    object ServiceLocator {

        private val instances = mutableMapOf<KClass<*>,Any>()

        inline fun <reified T:Any> register(instance:T) = register(T::class,instance)

        fun <T:Any>register(kClass: KClass<T>,instance:T){
            instances[kClass] = instance
        }

        @Suppress("UNCHECKED_CAST")
        fun<T:Any>get(kClass: KClass<T>):T = instances[kClass] as T
    }

    inline fun <reified T:Any> locate() = ServiceLocator.get(T::class)
}
