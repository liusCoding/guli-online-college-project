package com.liuscoding.acl.controller;


import com.liuscoding.acl.entity.Permission;
import com.liuscoding.acl.service.PermissionService;
import com.liuscoding.commonutils.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/permission")
/*@CrossOrigin*/
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public ResultVo indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenuGuli();
        return ResultVo.ok().data("children",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public ResultVo remove(@PathVariable String id) {
        permissionService.removeChildByIdGuli(id);
        return ResultVo.ok();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public ResultVo doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
        return ResultVo.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public ResultVo toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return ResultVo.ok().data("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public ResultVo save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return ResultVo.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public ResultVo updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return ResultVo.ok();
    }

}

