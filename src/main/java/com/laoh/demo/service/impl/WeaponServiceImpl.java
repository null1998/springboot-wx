package com.laoh.demo.service.impl;

import com.laoh.demo.entity.Weapon;
import com.laoh.demo.dao.WeaponDao;
import com.laoh.demo.service.WeaponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 武器表(Weapon)表服务实现类
 *
 * @author makejava
 * @since 2021-05-19 00:40:09
 */
@Service("weaponService")
public class WeaponServiceImpl implements WeaponService {
    @Resource
    private WeaponDao weaponDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Weapon queryById(Long id) {
        return this.weaponDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Weapon> queryAllByLimit(int offset, int limit) {
        return this.weaponDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param weapon 实例对象
     * @return 实例对象
     */
    @Override
    public Weapon insert(Weapon weapon) {
        this.weaponDao.insert(weapon);
        return weapon;
    }

    /**
     * 修改数据
     *
     * @param weapon 实例对象
     * @return 实例对象
     */
    @Override
    public Weapon update(Weapon weapon) {
        this.weaponDao.update(weapon);
        return this.queryById(weapon.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.weaponDao.deleteById(id) > 0;
    }
}
