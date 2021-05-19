package com.laoh.demo.service.impl;

import com.hyd.common.util.IdGenerator;
import com.laoh.demo.entity.WeaponMaterial;
import com.laoh.demo.dao.WeaponMaterialDao;
import com.laoh.demo.service.WeaponMaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 武器材料表(WeaponMaterial)表服务实现类
 *
 * @author makejava
 * @since 2021-05-19 23:59:24
 */
@Service("weaponMaterialService")
public class WeaponMaterialServiceImpl implements WeaponMaterialService {
    @Resource
    private WeaponMaterialDao weaponMaterialDao;
    @Resource
    private IdGenerator idGenerator;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WeaponMaterial queryById(Long id) {
        return this.weaponMaterialDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<WeaponMaterial> queryAllByLimit(int offset, int limit) {
        return this.weaponMaterialDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param weaponMaterial 实例对象
     * @return 实例对象
     */
    @Override
    public WeaponMaterial insert(WeaponMaterial weaponMaterial) {
        weaponMaterial.setId(idGenerator.snowflakeId());
        this.weaponMaterialDao.insert(weaponMaterial);
        return weaponMaterial;
    }

    /**
     * 修改数据
     *
     * @param weaponMaterial 实例对象
     * @return 实例对象
     */
    @Override
    public WeaponMaterial update(WeaponMaterial weaponMaterial) {
        this.weaponMaterialDao.update(weaponMaterial);
        return this.queryById(weaponMaterial.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.weaponMaterialDao.deleteById(id) > 0;
    }
}
