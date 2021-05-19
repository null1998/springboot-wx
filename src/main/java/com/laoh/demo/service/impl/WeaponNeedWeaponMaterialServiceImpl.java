package com.laoh.demo.service.impl;

import com.hyd.common.util.IdGenerator;
import com.laoh.demo.entity.WeaponNeedWeaponMaterial;
import com.laoh.demo.dao.WeaponNeedWeaponMaterialDao;
import com.laoh.demo.service.WeaponNeedWeaponMaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 武器所需材料表(WeaponNeedWeaponMaterial)表服务实现类
 *
 * @author makejava
 * @since 2021-05-19 00:59:29
 */
@Service("weaponNeedWeaponMaterialService")
public class WeaponNeedWeaponMaterialServiceImpl implements WeaponNeedWeaponMaterialService {
    @Resource
    private WeaponNeedWeaponMaterialDao weaponNeedWeaponMaterialDao;
    @Resource
    private IdGenerator idGenerator;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WeaponNeedWeaponMaterial queryById(Long id) {
        return this.weaponNeedWeaponMaterialDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<WeaponNeedWeaponMaterial> queryAllByLimit(int offset, int limit) {
        return this.weaponNeedWeaponMaterialDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param weaponNeedWeaponMaterial 实例对象
     * @return 实例对象
     */
    @Override
    public WeaponNeedWeaponMaterial insert(WeaponNeedWeaponMaterial weaponNeedWeaponMaterial) {
        weaponNeedWeaponMaterial.setId(idGenerator.snowflakeId());
        this.weaponNeedWeaponMaterialDao.insert(weaponNeedWeaponMaterial);
        return weaponNeedWeaponMaterial;
    }

    /**
     * 修改数据
     *
     * @param weaponNeedWeaponMaterial 实例对象
     * @return 实例对象
     */
    @Override
    public WeaponNeedWeaponMaterial update(WeaponNeedWeaponMaterial weaponNeedWeaponMaterial) {
        this.weaponNeedWeaponMaterialDao.update(weaponNeedWeaponMaterial);
        return this.queryById(weaponNeedWeaponMaterial.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.weaponNeedWeaponMaterialDao.deleteById(id) > 0;
    }
}
