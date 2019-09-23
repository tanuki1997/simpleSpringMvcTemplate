package com.meiya.order.vo;

import com.meiya.order.entity.MenuRouter;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.List;

public class MenuRouterVo {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 标识
     */
    private String code;

    /**
     * 根目录加上/,其他都不用
     */
    private String relativePath;

    /**
     * 组件路径
     */
    @Column(name = "component_path")
    private String componentPath;

    /**
     * 父目录Id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 排序用
     */
    @Column(name = "serial_no")
    private Integer serialNo;

    /**
     * 是否禁用
     */
    @Column(name = "has_disable")
    private String hasDisable;

    /**
     * 图标类型
     */
    @Column(name = "icon_class")
    private String iconClass;

    /**
     * 子集
     */
    @Transient
    private List<MenuRouter> children;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取菜单名称
     *
     * @return name - 菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜单名称
     *
     * @param name 菜单名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取标识
     *
     * @return code - 标识
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置标识
     *
     * @param code 标识
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取根目录加上/,其他都不用
     *
     * @return relative_path - 根目录加上/,其他都不用
     */
    public String getRelativePath() {
        return relativePath;
    }

    /**
     * 设置根目录加上/,其他都不用
     *
     * @param relativePath 根目录加上/,其他都不用
     */
    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath == null ? null : relativePath.trim();
    }

    /**
     * 获取组件路径
     *
     * @return component_path - 组件路径
     */
    public String getComponentPath() {
        return componentPath;
    }

    /**
     * 设置组件路径
     *
     * @param componentPath 组件路径
     */
    public void setComponentPath(String componentPath) {
        this.componentPath = componentPath == null ? null : componentPath.trim();
    }

    /**
     * 获取父目录Id
     *
     * @return parent_id - 父目录Id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父目录Id
     *
     * @param parentId 父目录Id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取排序用
     *
     * @return serial_no - 排序用
     */
    public Integer getSerialNo() {
        return serialNo;
    }

    /**
     * 设置排序用
     *
     * @param serialNo 排序用
     */
    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * 获取是否禁用
     *
     * @return has_disable - 是否禁用
     */
    public String getHasDisable() {
        return hasDisable;
    }

    /**
     * 设置是否禁用
     *
     * @param hasDisable 是否禁用
     */
    public void setHasDisable(String hasDisable) {
        this.hasDisable = hasDisable == null ? null : hasDisable.trim();
    }

    /**
     * 获取图标类型
     *
     * @return icon_class - 图标类型
     */
    public String getIconClass() {
        return iconClass;
    }

    /**
     * 设置图标类型
     *
     * @param iconClass 图标类型
     */
    public void setIconClass(String iconClass) {


        this.iconClass = iconClass == null ? null : iconClass.trim();
    }

    public List<MenuRouter> getChildren() {
        return children;
    }

    public void setChildren(List<MenuRouter> children) {
        this.children = children;
    }
}
