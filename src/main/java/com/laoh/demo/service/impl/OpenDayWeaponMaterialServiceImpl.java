package com.laoh.demo.service.impl;

import com.laoh.demo.entity.OpenDayWeaponMaterial;
import com.laoh.demo.dao.OpenDayWeaponMaterialDao;
import com.laoh.demo.service.OpenDayWeaponMaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 武器材料开放日表(OpenDayWeaponMaterial)表服务实现类
 *
 * @author makejava
 * @since 2021-05-19 00:40:05
 */
@Service("openDayWeaponMaterialService")
public class OpenDayWeaponMaterialServiceImpl implements OpenDayWeaponMaterialService {
    @Resource
    private OpenDayWeaponMaterialDao openDayWeaponMaterialDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OpenDayWeaponMaterial queryById(Long id) {
        return this.openDayWeaponMaterialDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<OpenDayWeaponMaterial> queryAllByLimit(int offset, int limit) {
        return this.openDayWeaponMaterialDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param openDayWeaponMaterial 实例对象
     * @return 实例对象
     */
    @Override
    public OpenDayWeaponMaterial insert(OpenDayWeaponMaterial openDayWeaponMaterial) {
        this.openDayWeaponMaterialDao.insert(openDayWeaponMaterial);
        return openDayWeaponMaterial;
    }

    /**
     * 修改数据
     *
     * @param openDayWeaponMaterial 实例对象
     * @return 实例对象
     */
    @Override
    public OpenDayWeaponMaterial update(OpenDayWeaponMaterial openDayWeaponMaterial) {
        this.openDayWeaponMaterialDao.update(openDayWeaponMaterial);
        return this.queryById(openDayWeaponMaterial.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.openDayWeaponMaterialDao.deleteById(id) > 0;
    }
}
