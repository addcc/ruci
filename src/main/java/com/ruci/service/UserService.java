package com.ruci.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.ruci.dao.RiLoginMapper;
import com.ruci.dao.RiUserMapper;
import com.ruci.dao.UserRoleMapper;
import com.ruci.domain.RiLogin;
import com.ruci.domain.RiUser;
import com.ruci.domain.UserRole;
import com.ruci.exception.ParamException;
import com.ruci.param.PageInfo;
import com.ruci.param.UserParam;
import com.ruci.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private RiUserMapper riUserMapper;
    @Autowired
    private RiLoginMapper loginMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;



    /**
     * 根据字段查询多条数据
     * @param name  姓名字段
     * @return 查询到的数据
     */
    public List<UserParam> selectByNameList(String name){
        //构建按字段查询的函数
        QueryWrapper<RiUser> wrapper=new QueryWrapper<>();
        wrapper.like("name",name);
        List<RiUser> userList=riUserMapper.selectList(wrapper);

        List<UserParam> userParams=Lists.newArrayList();
        //遍历
        for (RiUser user : userList) {
            UserParam param=new UserParam();
            BeanUtils.copyProperties(user,param);
            userParams.add(param);
        }
        return userParams;
    }

    /**
     * 更新用户
     * @param userParam 接收传过来的更新数据
     */
    public void update(UserParam userParam){
        if(userParam==null){
            throw new ParamException("用户不存在");
        }
        RiUser before=riUserMapper.selectById(userParam.getId());
        if(before==null){
            throw new ParamException("用户不存在");
        }
        //赋值
        RiUser user=new RiUser();
        BeanUtils.copyProperties(userParam,user);
        user.setUpdateDate(new Date());

        RiLogin login=new RiLogin();
        login.setUid(userParam.getId());
        login.setLoginName(userParam.getUserName());
        login.setPassword(MD5Util.inputPassToDbPass(userParam.getPassword(),"1a2b3c4d"));


        //更新
        riUserMapper.updateById(user);
        loginMapper.updateById(login);

    }

  //删除用户
    @Transactional
    public void delete(Integer id){
        if(id==null){
            throw new ParamException("请选择用户");
        }
        RiUser user=riUserMapper.selectById(id);
        if(user==null){
            throw new ParamException("用户不存在");
        }

        riUserMapper.deleteById(id);
        loginMapper.deleteById(id);
        userRoleMapper.deleteByUid(id);
    }

    /**
     * 简单更新用户资料
     * @param id  用户登录账号id
     */
    public void sava(Integer id){
        RiUser user=new RiUser();
        user.setId(id);
        user.setCreateDate(new Date());
        riUserMapper.insert(user);
    }

    public PageInfo<RiUser> selectPage(long pageNum){
        QueryWrapper<RiUser> wrapper=new QueryWrapper<>();

        //当前为自定义页，一页总共10行
        Page<RiUser> page=new Page<>(pageNum,10);

        IPage<RiUser> iPage= riUserMapper.selectPage(page,wrapper);

        //获取list集合

        PageInfo<RiUser> pageInfo=new PageInfo<>();
        pageInfo.setList(iPage.getRecords());
        pageInfo.setPageNum(pageNum);
        pageInfo.setTotal(iPage.getTotal());
        pageInfo.setPages((int)iPage.getPages());

        return pageInfo;
    }

    //根据id查询用户
    public UserParam selectById(Integer id){
        if(id==null){
            throw new ParamException("用户不存在");
        }
        //获取用户信息和登录账号
        RiUser user=riUserMapper.selectById(id);
        RiLogin login=loginMapper.selectById(id);

        UserParam param=new UserParam();
        BeanUtils.copyProperties(user,param);
        param.setUserName(login.getLoginName());
        param.setPassword(login.getPassword());

        return param;
    }








}


