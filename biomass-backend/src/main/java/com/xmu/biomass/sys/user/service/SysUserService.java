package com.xmu.biomass.sys.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmu.biomass.common.utils.PaginationUtil;
import com.xmu.biomass.common.utils.PasswordUtil;
import com.xmu.biomass.common.vo.Pagination;
import com.xmu.biomass.sys.user.entity.SysUser;
import com.xmu.biomass.sys.user.mapper.SysUserMapper;
import com.xmu.biomass.sys.user.vo.SysUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysUserService {

    private final SysUserMapper sysUserMapper;

    public Pagination<SysUserVo> selectPage(){
        Page<SysUser> page = sysUserMapper.selectPage(new Page<>(), null);
        return PaginationUtil.convert(page,(sysUser)->{
            SysUserVo vo = new SysUserVo();
            vo.setId(sysUser.getId());
            vo.setUsername(sysUser.getUsername());
            vo.setNickname(sysUser.getNickname());
            vo.setEmail(sysUser.getEmail());
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

}
