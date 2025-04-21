package com.xmu.biomass.sys.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmu.biomass.common.context.UserContext;
import com.xmu.biomass.common.ro.PageRo;
import com.xmu.biomass.common.utils.PaginationUtil;
import com.xmu.biomass.common.vo.Pagination;
import com.xmu.biomass.sys.user.config.UserBaseProperties;
import com.xmu.biomass.sys.user.entity.SysUser;
import com.xmu.biomass.sys.user.mapper.SysUserMapper;
import com.xmu.biomass.sys.user.ro.AddUserRo;
import com.xmu.biomass.sys.user.ro.UpdateUserRo;
import com.xmu.biomass.sys.user.vo.SysUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SysUserService {

    private final SysUserMapper sysUserMapper;
    private final UserBaseProperties userBaseProperties;


    public Pagination<SysUserVo> selectPage(PageRo ro){
        Page<SysUser> page = new Page<>(ro.getCurrent(),ro.getSize());
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUser::getDeleteAt,0);
        sysUserMapper.selectPage(page, queryWrapper);
        return PaginationUtil.convert(page,(sysUser)->{
            SysUserVo vo = new SysUserVo();
            vo.setId(sysUser.getId());
            vo.setUsername(sysUser.getUsername());
            vo.setMobile(sysUser.getMobile());
            vo.setNickname(sysUser.getNickname());
            vo.setRealName(sysUser.getRealName());
            vo.setEmail(sysUser.getEmail());
            vo.setAvatar(sysUser.getAvatar());
            return vo;
        });
    }

    public SysUser findUserByUserName(String username){
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysUser::getUsername,username);
        return sysUserMapper.selectOne(wrapper);
    }

    public SysUser findUserById(Integer id){
        return sysUserMapper.selectById(id);
    }

    public Integer createUser(AddUserRo addUserRo) {
        SysUser  sysUser = new SysUser();
        sysUser.setUsername(addUserRo.getUsername());
        sysUser.setMobile(addUserRo.getMobile());
        sysUser.setNickname(addUserRo.getNickname());
        sysUser.setRealName(addUserRo.getRealName());
        sysUser.setEmail(addUserRo.getEmail());
        sysUser.setAvatar(addUserRo.getAvatar());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(userBaseProperties.getPassword());
        sysUser.setPassword(password);
        sysUser.setCreateBy(UserContext.getUser().getId().toString());
        sysUser.setUpdateBy(UserContext.getUser().getId().toString());
        return sysUserMapper.insert(sysUser);
    }

    public SysUserVo selectById(Integer id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        SysUserVo vo = new SysUserVo();
        vo.setId(sysUser.getId());
        vo.setUsername(sysUser.getUsername());
        vo.setMobile(sysUser.getMobile());
        vo.setNickname(sysUser.getNickname());
        vo.setRealName(sysUser.getRealName());
        vo.setEmail(sysUser.getEmail());
        vo.setAvatar(sysUser.getAvatar());
        vo.setLockFlag(0);
        return vo;
    }

    public Integer updateUser(UpdateUserRo updateUserRo) {
        SysUser sysUser = sysUserMapper.selectById(updateUserRo.getId());
        Objects.requireNonNull(sysUser,"用户不存在");
        sysUser.setUsername(updateUserRo.getUsername());
        sysUser.setMobile(updateUserRo.getMobile());
        sysUser.setNickname(updateUserRo.getNickname());
        sysUser.setRealName(updateUserRo.getRealName());
        sysUser.setEmail(updateUserRo.getEmail());
        sysUser.setAvatar(updateUserRo.getAvatar());
        return sysUserMapper.updateById(sysUser);
    }

    public Integer deleteById(Integer id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        Objects.requireNonNull(sysUser,"用户不存在");
        sysUser.setUpdateBy(UserContext.getUser().getId().toString());
        sysUser.setDeleteAt(1D);
        return sysUserMapper.updateById(sysUser);
    }
}
