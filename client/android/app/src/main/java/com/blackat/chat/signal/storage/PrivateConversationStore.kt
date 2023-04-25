package com.blackat.chat.signal.storage

import com.blackat.chat.data.dao.OneTimePreKeyDao
import com.blackat.chat.data.dao.PrivateConversationDao
import com.blackat.chat.data.dao.PrivateMessageDao
import com.blackat.chat.data.dao.SignedPreKeyDao
import com.blackat.chat.data.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.signal.libsignal.protocol.ecc.ECKeyPair
import org.signal.libsignal.protocol.state.*

class PrivateConversationStore(
        private val conversationStore: PrivateConversationDao,
        private val messageStore: PrivateMessageDao,
) {
    suspend fun contain(e164: String): Boolean {
        return conversationStore.contain(e164)
    }

    suspend fun create(e164: String, firstMessage: Message): Int? {
        if (contain(e164)) return null
        val id = conversationStore.insert(PrivateConversation(e164))
        id ?: return null
        messageStore.insert(PrivateMessage(firstMessage,id.toInt()))
        return id.toInt()
    }

    suspend fun getAll(): List<PrivateConversation> {
        return conversationStore.getAll()
    }

    suspend fun get(e164: String): PrivateConversation? {
        return conversationStore.get(e164)
    }

    suspend fun ting(e164: String) {
        return conversationStore.ting(e164)
    }

    suspend fun getOneWithMessages(e164: String): PrivateConversationWithMessages? {
        return conversationStore.getOneWithMessages(e164)
    }

    fun getAllFlow(): Flow<List<PrivateConversation>> = conversationStore.getAllFlow()
}