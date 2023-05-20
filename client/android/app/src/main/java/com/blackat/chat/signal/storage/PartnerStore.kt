package com.blackat.chat.signal.storage

import com.blackat.chat.data.dao.MessageWithE164
import com.blackat.chat.data.dao.PartnerDao
import com.blackat.chat.data.dao.PrivateMessageDao
import com.blackat.chat.data.model.*

class PartnerStore(
        private val store: PartnerDao,
) {
    suspend fun upsert(e164: String, deviceId: Int) {
        val partner = store.getByE164(e164)
        partner?.let {
            partner.deviceId = deviceId
            store.update(partner)
        } ?: store.insert(Partner(e164,deviceId))
    }

    suspend fun getByE164(e164: String): Partner? {
        return store.getByE164(e164)
    }


//    suspend fun getMessageWithE164List(): List<MessageWithE164> {
//        return store.getMessagesWithState(MessageState.SENDING)
//    }
//
//    suspend fun markAsSent(id: Int) {
//        return store.changeState(MessageState.SENT,id)
//    }
}