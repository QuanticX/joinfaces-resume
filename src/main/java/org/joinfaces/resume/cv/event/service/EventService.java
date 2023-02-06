package org.joinfaces.resume.cv.event.service;

import org.joinfaces.resume.common.service.CoreService;
import org.joinfaces.resume.cv.event.dto.EventDto;
import org.joinfaces.resume.cv.event.entity.EventEntity;
import org.springframework.stereotype.Component;

@Component
public class EventService extends CoreService<EventEntity, EventDto> {
}
