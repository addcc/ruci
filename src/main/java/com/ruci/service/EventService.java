package com.ruci.service;

import com.ruci.dao.EventMapper;
import com.ruci.domain.Event;
import com.ruci.param.EventParam;
import com.ruci.param.ItemParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private ItemService itemService;

    //根据id获取秒杀信息
    public EventParam selectById(int id){
        //获取商品信息和秒杀信息
        ItemParam itemParam=itemService.selectById(id);

        Event event=eventMapper.selectById(id);

        EventParam param=new EventParam();

        BeanUtils.copyProperties(itemParam,param);

        if(event!=null){
           BeanUtils.copyProperties(event,param);
        }



        return param;
    }
}
