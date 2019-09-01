package com.ruci.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruci.common.LoginSession;
import com.ruci.dao.ItemMapper;
import com.ruci.domain.Item;
import com.ruci.exception.ParamException;
import com.ruci.param.ItemParam;
import com.ruci.param.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ItemService {

    @Autowired
    private ItemMapper itemMapper;



    //查询所有记录并分页显示
    public PageInfo<Item> list(long pageNum){
        QueryWrapper<Item> wrapper=new QueryWrapper<>();

        //当前为自定义页，一页总共10行
        Page<Item> page=new Page<>(pageNum,5);
        IPage<Item> iPage=itemMapper.selectPage(page,wrapper);
        PageInfo<Item> pageInfo=new PageInfo<>();
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

    //根据用户id查询
    public PageInfo<Item> listByName(long pageNum){

        Integer uid= LoginSession.getLoginId();
        QueryWrapper<Item> wrapper=new QueryWrapper<>();
        wrapper.eq("uid",uid);

        //当前为自定义页，一页总共10行
        Page<Item> page=new Page<>(pageNum,5);
        IPage<Item> iPage=itemMapper.selectPage(page,wrapper);
        PageInfo<Item> pageInfo=new PageInfo<>();
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


    //按名称查询
    public PageInfo<Item> selectByName(long pageNum,String name){
        QueryWrapper<Item> wrapper=new QueryWrapper<>();
        wrapper.like("name",name);

        Page<Item> page=new Page<>(pageNum,5);
        IPage<Item> iPage=itemMapper.selectPage(page,wrapper);
        PageInfo<Item> pageInfo=new PageInfo<>();
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

    //商品详情
    public ItemParam selectById(int id){
        if(id==0){
            throw new ParamException("商品不存在");
        }
        Item item=itemMapper.selectById(id);

        ItemParam param=new ItemParam();
        //传参
        BeanUtils.copyProperties(item,param);
        return param;
    }


}
