package com.ruci.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.ruci.common.LoginSession;
import com.ruci.dao.EventMapper;
import com.ruci.dao.ItemMapper;
import com.ruci.dao.OrderMapper;
import com.ruci.domain.Event;
import com.ruci.domain.Item;
import com.ruci.domain.OrderInif;
import com.ruci.exception.ParamException;
import com.ruci.param.OrderParam;
import com.ruci.param.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private EventMapper eventMapper;

    //创建订单
    @Transactional
    public OrderParam sava(Integer tid){
        //获取商品信息和活动信息
        Item item=itemMapper.selectById(tid);
        if(item.getSales()<=0){
            throw new ParamException("商品已售完");
        }
        Event event=eventMapper.selectById(tid);

        //判断活动状态
        long startAt = event.getStartDate().getTime();
        long endAt = event.getEndDate().getTime();
        long now = new Date().getTime();

        BigDecimal price;
        if(now < startAt || now>endAt) {//秒杀还没开始或秒杀活动结束
           price=item.getPrice();

        }else {//秒杀进行中
           price=event.getEventPrice();
        }

        Integer uid= LoginSession.getLoginId();


        //传参
        OrderInif order= OrderInif.builder().uid(uid).tid(tid).title(item.getTitle()).count(1)
                .ownerId(item.getUid()).createDate(new Date()).price(price).imgUrl(item.getImgUrl()).build();

        //创建订单
        orderMapper.insert(order);
        //减库存
        itemMapper.updateByTId(tid);
        eventMapper.updateByItermId(tid);


        //返回显示数据
        OrderParam param=new OrderParam();
        BeanUtils.copyProperties(order,param);

        return param;
    }

    //根据用户id返回订单
    public PageInfo<OrderInif> list(long pageNum){

        Integer uid=LoginSession.getLoginId();

        QueryWrapper<OrderInif> wrapper=new QueryWrapper<>();

        wrapper.eq("uid",uid);

        //当前为自定义页，一页总共10行
        Page<OrderInif> page=new Page<>(pageNum,5);
        IPage<OrderInif> iPage=orderMapper.selectPage(page,wrapper);
        PageInfo<OrderInif> pageInfo=new PageInfo<>();
        //获取当前集合
        pageInfo.setList(iPage.getRecords());
        //获取总记录数
        pageInfo.setTotal(iPage.getTotal());
        //获取当前页数
        pageInfo.setPageNum(pageNum);
        //获取总页数
        pageInfo.setPages((int)iPage.getPages());

        return pageInfo;

    }

    //根据订单id返回订单详情
    public OrderParam getOrder(Integer oid){

        OrderInif order=orderMapper.selectById(oid);
        OrderParam param=new OrderParam();

        BeanUtils.copyProperties(order,param);

        return param;
    }

    //根据商家id查询所有订单
    public PageInfo<OrderInif> lists(long pageNum){


        QueryWrapper<OrderInif> wrapper=new QueryWrapper<>();

        Integer ownerId=LoginSession.getLoginId();
        wrapper.eq("owner_id",ownerId);
        //定义排序
        wrapper.orderByDesc("create_date");

        //当前为自定义页，一页总共10行
        Page<OrderInif> page=new Page<>(pageNum,10);
        IPage<OrderInif> iPage=orderMapper.selectPage(page,wrapper);
        PageInfo<OrderInif> pageInfo=new PageInfo<>();
        //获取当前页数
        pageInfo.setPageNum(pageNum);
        //获取总页数
        pageInfo.setPages((int)iPage.getPages());
        //获取当前集合
        pageInfo.setList(iPage.getRecords());
        //获取总记录数
        pageInfo.setTotal(iPage.getTotal());


        return pageInfo;

    }





}
