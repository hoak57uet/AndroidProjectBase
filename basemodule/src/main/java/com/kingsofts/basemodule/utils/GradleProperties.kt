package com.kingsofts.basemodule.utils

import java.io.FileInputStream
import java.io.InputStream
import java.util.*
import kotlin.collections.HashMap

class GradleProperties {
    companion object {
        private var properties: HashMap<String, String>? = null

        private fun instance(): HashMap<String, String> {
            if (properties == null) {
                properties = HashMap()
                var props = Properties()
                var input: InputStream? = null

                try {
                    input = FileInputStream("gradle.properties")
                    props.load(input)
                    props.keys.forEach { key ->
                        run {
                            properties!!.put(
                                key = key as String,
                                value = props[key] as String
                            )
                        }
                    }
                } catch (ex: Throwable) {
                    ex.printStackTrace()
                }
            }
            return properties!!
        }

        fun get(key: String): String? {
            return instance()[key]
        }
    }
}