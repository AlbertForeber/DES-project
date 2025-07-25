package com.example.sdo_project.data.remote.dto

import android.util.Log
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.ZoneId
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
data class MaterialDto @OptIn(ExperimentalTime::class) constructor(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("section_id") val sectionId: Int,
    @Serializable(with = InstantSerializer::class)
    @SerialName("access_time") val accessTime: Instant,
    @SerialName("content") val content: String,

    )

@OptIn(ExperimentalTime::class)
object InstantSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Instant", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Instant {
        val string_ = decoder.decodeString() + "Z"
        return Instant.parse(string_)
    }

    override fun serialize(encoder: Encoder, value: Instant) {
        encoder.encodeString(value.toString())
    }

}

@Serializable
data class NewMaterialDto (
    @SerialName("name") val name: String,
    @SerialName("section_id") val sectionId: Int,
    //@Serializable(with = InstantSerializer::class)
    @SerialName("access_time") val accessTime: String,
    @SerialName("content") val content: String,

    )