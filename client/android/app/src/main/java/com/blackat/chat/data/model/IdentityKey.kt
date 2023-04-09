package com.blackat.chat.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.signal.libsignal.protocol.SignalProtocolAddress

data class Address(
        val name: String,
        val deviceId: Int,
): SignalProtocolAddress(name, deviceId)

@Entity(tableName = "identity_keys")
data class IdentityKey(
        @PrimaryKey
        @Embedded val address: Address,
        val identityKey: ByteArray,
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as IdentityKey

                if (address != other.address) return false
                if (!identityKey.contentEquals(other.identityKey)) return false

                return true
        }

        override fun hashCode(): Int {
                var result = address.hashCode()
                result = 31 * result + identityKey.contentHashCode()
                return result
        }
}