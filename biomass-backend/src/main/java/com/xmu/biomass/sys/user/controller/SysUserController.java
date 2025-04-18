package com.xmu.biomass.sys.user.controller;

import com.xmu.biomass.common.utils.ResponseUtil;
import com.xmu.biomass.common.vo.AjaxVo;
import com.xmu.biomass.common.vo.Pagination;
import com.xmu.biomass.sys.user.service.SysUserService;
import com.xmu.biomass.sys.user.vo.SysUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    @PostMapping("/page")
    public AjaxVo<Pagination<SysUserVo>> page(){
        return ResponseUtil.success(sysUserService.selectPage());
    }
}
