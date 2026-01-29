package com.jsqi.composedemo.utils

import android.os.Parcelable
import com.jsqi.composedemo.MyApp
import com.tencent.mmkv.MMKV
import kotlin.getValue
import kotlin.jvm.java
import kotlin.jvm.javaClass
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


val mmkv: MMKV by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    MMKV.mmkvWithID(MyApp.instance.packageName)
}

class Cache<T : Any>(
    private val default: T,
    private val key: String? = null,
) : ReadWriteProperty<Any?, T> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val actualKey = key ?: property.name
        return when (default) {
            is Boolean -> mmkv.decodeBool(actualKey, default) as T
            is Int -> mmkv.decodeInt(actualKey, default) as T
            is Long -> mmkv.decodeLong(actualKey, default) as T
            is Float -> mmkv.decodeFloat(actualKey, default) as T
            is Double -> mmkv.decodeDouble(actualKey, default) as T
            is String -> mmkv.decodeString(actualKey, default) as T
            is ByteArray -> mmkv.decodeBytes(actualKey, default) as T
            is Set<*> -> mmkv.decodeStringSet(actualKey, default as Set<String>) as T
            is Parcelable -> mmkv.decodeParcelable(
                actualKey,
                default.javaClass as Class<Parcelable>,
                default
            ) as T
            else -> throw kotlin.IllegalArgumentException("Unsupported type: ${default::class.java}")
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        val actualKey = key ?: property.name
        when (value) {
            is Boolean -> mmkv.encode(actualKey, value)
            is Int -> mmkv.encode(actualKey, value)
            is Long -> mmkv.encode(actualKey, value)
            is Float -> mmkv.encode(actualKey, value)
            is Double -> mmkv.encode(actualKey, value)
            is String -> mmkv.encode(actualKey, value)
            is ByteArray -> mmkv.encode(actualKey, value)
            is Set<*> -> mmkv.encode(actualKey, value as Set<String>)
            is Parcelable -> mmkv.encode(actualKey, value)
            else -> throw kotlin.IllegalArgumentException("Unsupported type: ${value::class.java}")
        }
    }
}

inline fun <reified T> cacheNullable(
    default: T? = null,
    key: String? = null
): ReadWriteProperty<Any?, T?> = CacheNullable(T::class.java, default, key)

class CacheNullable<T>(
    private val clazz: Class<T>,
    private val default: T? = null,
    private val key: String? = null,
) : ReadWriteProperty<Any?, T?> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        val actualKey = key ?: property.name
        if (default != null) {
            return decodeWithDefault(actualKey, default)
        }
        return decodeByClass(actualKey, clazz)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <R> decodeWithDefault(key: String, default: R): R {
        return when (default) {
            is Boolean -> mmkv.decodeBool(key, default) as R
            is Int -> mmkv.decodeInt(key, default) as R
            is Long -> mmkv.decodeLong(key, default) as R
            is Float -> mmkv.decodeFloat(key, default) as R
            is Double -> mmkv.decodeDouble(key, default) as R
            is String -> mmkv.decodeString(key, default) as R
            is ByteArray -> mmkv.decodeBytes(key, default) as R
            is Set<*> -> mmkv.decodeStringSet(key, default as Set<String>) as R
            is Parcelable -> mmkv.decodeParcelable(
                key,
                default.javaClass as Class<Parcelable>,
                default
            ) as R
            else -> throw kotlin.IllegalArgumentException("Unsupported type: ${default!!::class.java}")
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <R> decodeByClass(key: String, clazz: Class<R>): R? {
        if (!mmkv.contains(key)) {
            return null
        }
        return when {
            clazz == Boolean::class.java -> mmkv.decodeBool(key, false) as R
            clazz == Int::class.java -> mmkv.decodeInt(key, 0) as R
            clazz == Long::class.java -> mmkv.decodeLong(key, 0L) as R
            clazz == Float::class.java -> mmkv.decodeFloat(key, 0f) as R
            clazz == Double::class.java -> mmkv.decodeDouble(key, 0.0) as R
            clazz == String::class.java -> mmkv.decodeString(key, null) as R
            clazz == ByteArray::class.java -> mmkv.decodeBytes(key, null) as R
            Set::class.java.isAssignableFrom(clazz) -> mmkv.decodeStringSet(key, null) as R
            Parcelable::class.java.isAssignableFrom(clazz) -> {
                mmkv.decodeParcelable(key, clazz as Class<Parcelable>, null) as R
            }
            else -> throw kotlin.IllegalArgumentException("Unsupported type: $clazz")
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        val actualKey = key ?: property.name

        if (value == null) {
            mmkv.removeValueForKey(actualKey)
            return
        }

        when (value) {
            is Boolean -> mmkv.encode(actualKey, value)
            is Int -> mmkv.encode(actualKey, value)
            is Long -> mmkv.encode(actualKey, value)
            is Float -> mmkv.encode(actualKey, value)
            is Double -> mmkv.encode(actualKey, value)
            is String -> mmkv.encode(actualKey, value)
            is ByteArray -> mmkv.encode(actualKey, value)
            is Set<*> -> mmkv.encode(actualKey, value as Set<String>)
            is Parcelable -> mmkv.encode(actualKey, value)
            else -> throw kotlin.IllegalArgumentException("Unsupported type: ${value::class.java}")
        }
    }
}
