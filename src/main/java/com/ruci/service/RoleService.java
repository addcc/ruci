package com.ruci.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruci.dao.*;
import com.ruci.domain.PermissionRole;
import com.ruci.domain.RiRole;
import com.ruci.domain.UserRole;
import com.ruci.exception.ParamException;
import com.ruci.param.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RoleService {

    @Autowired
    private RiRoleMapper riRoleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private PermissionRoleMapper permissionRoleMapper;

    //返回所有角色
    public PageInfo<RiRole> rolelist(long pageNum){
        QueryWrapper<RiRole> wrapper=new QueryWrapper<>();

        //当前为自定义页，一页总共10行
        Page<RiRole> page=new Page<>(pageNum,5);
        IPage<RiRole> iPage=riRoleMapper.selectPage(page,wrapper);
        PageInfo<RiRole> pageInfo=new PageInfo<>();
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

    //删除角色
    @Transactional
    public void delete(Integer rid){

        RiRole riRole=riRoleMapper.selectById(rid);
        if(riRole==null){
            throw new ParamException("角色不存在！");
        }

        riRoleMapper.deleteById(rid);
        //判断映射表是否为空
        QueryWrapper<UserRole> userRoleQueryWrapper=new QueryWrapper<>();
        userRoleQueryWrapper.eq("rid",rid);

        List<UserRole> userRoles=userRoleMapper.selectList(userRoleQueryWrapper);
        if(userRoles!=null){
            userRoleMapper.deleteByRid(rid);
        }

        //判断映射表是否为空
        QueryWrapper<PermissionRole> permissionRoleQueryWrapper=new QueryWrapper<>();
        userRoleQueryWrapper.eq("rid",rid);

        List<PermissionRole> permissionRoles=permissionRoleMapper.selectList(permissionRoleQueryWrapper);
        if(permissionRoles!=null){
            permissionRoleMapper.deleteByRid(rid);
        }


    }



}
