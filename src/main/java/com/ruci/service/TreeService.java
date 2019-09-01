package com.ruci.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.ruci.dao.PermissionMapper;
import com.ruci.dao.RiMenuMapper;
import com.ruci.domain.Permission;
import com.ruci.domain.RiMenu;
import com.ruci.dto.MenuDto;
import com.ruci.dto.PermissionDto;
import com.ruci.util.LevelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class TreeService {

    @Autowired
    private RiMenuMapper riMenuMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    //生成权限菜单树
    public List<PermissionDto> permissionTree(){
        //
        List<Permission> permissions=permissionMapper.selectByIdFormList();
        List<PermissionDto> dtoList=Lists.newArrayList();
        for (Permission permission : permissions) {
            dtoList.add(PermissionDto.adapt(permission));
        }

        return permissionToTree(dtoList);
    }

    private List<PermissionDto> permissionToTree(List<PermissionDto> dtoList){
        if(dtoList==null){
            return Lists.newArrayList();
        }

        //确定排序
        Multimap<String ,PermissionDto> multimap=ArrayListMultimap.create();
        List<PermissionDto> rootList = Lists.newArrayList();

        for (PermissionDto dto : dtoList) {
          //以level为key，Permission为value
            multimap.put(dto.getLevel(),dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }
        Collections.sort(rootList, permissionDtoComparator);
        transformPermissionTree(rootList, LevelUtil.ROOT, multimap);
        return rootList;
    }

    private void transformPermissionTree(List<PermissionDto> dtoList,String level,Multimap<String ,PermissionDto> multimap){
        for (int i = 0; i < dtoList.size(); i++) {
            PermissionDto dto = dtoList.get(i);
            String nextLevel = LevelUtil.calculateLevel(level, dto.getPid());
            List<PermissionDto> permissionDtos = (List<PermissionDto>)multimap.get(nextLevel);
            //判断树形是否为空
            if (CollectionUtils.isNotEmpty(permissionDtos)) {
                Collections.sort(permissionDtos, permissionDtoComparator);
                dto.setPermissionDtoList(permissionDtos);
                transformPermissionTree(permissionDtos, nextLevel, multimap);
            }
        }
    }

    //生成菜单树
    public List<MenuDto> aclModuleTree() {
        //获取所有菜单
        List<RiMenu> riMenus = riMenuMapper.selectFormList();
        List<MenuDto> dtoList = Lists.newArrayList();
        //把菜单的值赋给dtoList
        for (RiMenu riMenu : riMenus) {
            dtoList.add(MenuDto.adapt(riMenu));
        }
        return aclModuleListToTree(dtoList);
    }

    private List<MenuDto> aclModuleListToTree(List<MenuDto> dtoList) {
        //判断传进来的数据是否为空
        if (CollectionUtils.isEmpty(dtoList)) {
            return Lists.newArrayList();
        }
        // level -> [aclmodule1, aclmodule2, ...] Map<String, List<Object>>
        Multimap<String, MenuDto> menuDtoMultimap = ArrayListMultimap.create();
        List<MenuDto> rootList = Lists.newArrayList();

        for (MenuDto dto : dtoList) {
            menuDtoMultimap.put(dto.getLeven(), dto);
            if (LevelUtil.ROOT.equals(dto.getLeven())) {
                rootList.add(dto);
            }
        }
        Collections.sort(rootList, menuSeqComparator);
        transformAclModuleTree(rootList, LevelUtil.ROOT, menuDtoMultimap);
        return rootList;
    }

    private void transformAclModuleTree(List<MenuDto> dtoList, String level, Multimap<String, MenuDto> levelAclModuleMap) {
        for (int i = 0; i < dtoList.size(); i++) {
            MenuDto dto = dtoList.get(i);
            String nextLevel = LevelUtil.calculateLevel(level, dto.getId());
            List<MenuDto> tempList = (List<MenuDto>) levelAclModuleMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempList)) {
                Collections.sort(tempList, menuSeqComparator);
                dto.setAclModuleList(tempList);
                transformAclModuleTree(tempList, nextLevel, levelAclModuleMap);
            }
        }
    }

    private Comparator<PermissionDto> permissionDtoComparator=new Comparator<PermissionDto>() {
        @Override
        public int compare(PermissionDto o1, PermissionDto o2) {
            return o1.getSort()-o2.getSort();
        }
    };

    private Comparator<MenuDto> menuSeqComparator = new Comparator<MenuDto>() {
        public int compare(MenuDto o1, MenuDto o2) {
            return o1.getSort() - o2.getSort();
        }
    };
}
