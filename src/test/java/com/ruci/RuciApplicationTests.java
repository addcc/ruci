package com.ruci;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruci.dao.ItemMapper;
import com.ruci.dao.RiUserMapper;
import com.ruci.domain.Item;
import com.ruci.domain.RiUser;
import com.ruci.util.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuciApplicationTests {

    @Autowired
    private RiUserMapper riUserMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Test
    public void contextLoads() {
        String pass= MD5Util.inputPassToDbPass("123456","1a2b3c4d");
        System.out.println(pass);
    }

    @Test
    public void select(){
        QueryWrapper<RiUser> wrapper=new QueryWrapper<>();

        //当前为第一页，总共10行
        Page<RiUser> page=new Page<>(2,10);

        IPage<RiUser> iPage=riUserMapper.selectPage(page,wrapper);

        System.out.println(iPage.getCurrent());
        System.out.println("返回查询的总页数"+iPage.getPages());
        System.out.println("总记录数"+iPage.getTotal());
        List<RiUser> users=iPage.getRecords();
        for (RiUser user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void item(){
        List<Item> items=itemMapper.selectByIdFormList();
        for (Item item : items) {
            System.out.println(item);
        }
    }
}
