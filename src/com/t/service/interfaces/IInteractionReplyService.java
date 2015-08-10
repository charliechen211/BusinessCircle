package com.t.service.interfaces;

import java.util.List;

import com.t.core.entities.InterReplyBean;
import com.t.core.entities.InteractionReply;

public interface IInteractionReplyService {
    public int addReply(InteractionReply ele);
    public List<InterReplyBean> getReplies(int topicId/*,Integer pageId,Integer pageSize*/);
    
}
