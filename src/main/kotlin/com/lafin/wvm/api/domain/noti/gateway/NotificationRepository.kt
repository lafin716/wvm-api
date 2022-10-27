package com.lafin.wvm.api.domain.noti.gateway

import com.lafin.wvm.api.domain.noti.model.Notification
import com.lafin.wvm.api.shared.domain.gateway.Condition
import com.lafin.wvm.api.shared.domain.gateway.CrudRepository
import com.lafin.wvm.api.shared.domain.gateway.Order

interface NotificationRepository : CrudRepository<Notification, Condition, Order>{
}