package com.laoh.demo.service.impl;

import com.laoh.demo.entity.OpenDayTalentMaterial;
import com.laoh.demo.dao.OpenDayTalentMaterialDao;
import com.laoh.demo.service.OpenDayTalentMaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 天赋材料开放日表(OpenDayTalentMaterial)表服务实现类
 *
 * @author makejava
 * @since 2021-05-19 00:39:58
 */
@Service("openDayTalentMaterialService")
public class OpenDayTalentMaterialServiceImpl implements OpenDayTalentMaterialService {
    @Resource
    private OpenDayTalentMaterialDao openDayTalentMaterialDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OpenDayTalentMaterial queryById(Long id) {
        return this.openDayTalentMaterialDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<OpenDayTalentMaterial> queryAllByLimit(int offset, int limit) {
        return this.openDayTalentMaterialDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param openDayTalentMaterial 实例对象
     * @return 实例对象
     */
    @Override
    public OpenDayTalentMaterial insert(OpenDayTalentMaterial openDayTalentMaterial) {
        this.openDayTalentMaterialDao.insert(openDayTalentMaterial);
        return openDayTalentMaterial;
    }

    /**
     * 修改数据
     *
     * @param openDayTalentMaterial 实例对象
     * @return 实例对象
     */
    @Override
    public OpenDayTalentMaterial update(OpenDayTalentMaterial openDayTalentMaterial) {
        this.openDayTalentMaterialDao.update(openDayTalentMaterial);
        return this.queryById(openDayTalentMaterial.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.openDayTalentMaterialDao.deleteById(id) > 0;
    }
}
