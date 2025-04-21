package com.xmu.biomass.sys.user.controller;

import com.xmu.biomass.common.ro.PageRo;
import com.xmu.biomass.common.utils.ResponseUtil;
import com.xmu.biomass.common.vo.AjaxVo;
import com.xmu.biomass.common.vo.Pagination;
import com.xmu.biomass.sys.user.ro.AddUserRo;
import com.xmu.biomass.sys.user.ro.UpdateUserRo;
import com.xmu.biomass.sys.user.service.SysUserService;
import com.xmu.biomass.sys.user.vo.SysUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    @PostMapping("/page")
    public AjaxVo<Pagination<SysUserVo>> page(@RequestBody PageRo ro){
        return ResponseUtil.success(sysUserService.selectPage(ro));
    }

    @GetMapping("{id}")
    public AjaxVo<SysUserVo> add(@PathVariable Integer id){
        return ResponseUtil.success(sysUserService.selectById(id));
    }

    @PostMapping("add")
    public AjaxVo<Integer> add(@RequestBody AddUserRo addUserRo){
        Integer id = sysUserService.createUser(addUserRo);
        return ResponseUtil.success(id);
    }

    @PutMapping("update")
    public AjaxVo<Integer> update(@RequestBody UpdateUserRo updateUserRo){
        Integer id = sysUserService.updateUser(updateUserRo);
        return ResponseUtil.success(id);
    }
    @DeleteMapping("{id}")
    public AjaxVo<Integer> delete(@PathVariable Integer id){
        Integer count = sysUserService.deleteById(id);
        return ResponseUtil.success(count);
    }
}
